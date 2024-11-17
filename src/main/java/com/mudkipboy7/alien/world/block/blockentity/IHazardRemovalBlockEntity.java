package com.mudkipboy7.alien.world.block.blockentity;

import java.util.List;

import com.mudkipboy7.alien.data.tags.AMBiomeTags;
import com.mudkipboy7.alien.data.tags.AMEntityTypeTags;
import com.mudkipboy7.alien.data.tags.AMItemTags;
import com.mudkipboy7.alien.world.WorldFuncs;
import com.mudkipboy7.alien.world.effect.AMMobEffects;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

public interface IHazardRemovalBlockEntity {

	public default boolean needsProtection(LivingEntity livingEntity) {
		boolean hasSurvivalGear = livingEntity.getItemBySlot(EquipmentSlot.HEAD).is(AMItemTags.SURVIVAL_HEAD)
				&& livingEntity.getItemBySlot(EquipmentSlot.CHEST).is(AMItemTags.SURVIVAL_TORSO)
				&& livingEntity.getItemBySlot(EquipmentSlot.LEGS).is(AMItemTags.SURVIVAL_LEGS);
		return !(hasSurvivalGear || livingEntity.getType().is(AMEntityTypeTags.COLD_BLOODED))
				&& !(livingEntity instanceof Player player && (player.isSpectator() || player.isCreative()));
	}

	public default boolean shouldCheck(Level level, BlockPos blockPos, BlockState blockState) {
		return WorldFuncs.inUltracoldBiome(level, blockPos);
	}

	/**
	 * Runs whenever it is finished with one cycle of applying effects
	 * 
	 * @param level
	 * @param blockPos
	 * @param blockState
	 */
	public default void onEffectsApplied(Level level, BlockPos blockPos, BlockState blockState) {
	}

	default boolean applyEffects(Level level, BlockPos blockPos, BlockState blockState) {
		boolean appliedEffects = false;
		//System.out.println("ewfwef");
		if ((!level.isClientSide) && shouldCheck(level, blockPos, blockState)) {
			
			AABB aabb = (new AABB(blockPos)).inflate(this.getEffectRadius());
			List<LivingEntity> list = level.getEntitiesOfClass(LivingEntity.class, aabb);
			// Goes over each entity and applies the effects,
			for (LivingEntity livingEntity : list) {
				if (this.needsProtection(livingEntity)) {
					appliedEffects = true;
					livingEntity.addEffect(
							new MobEffectInstance(AMMobEffects.HAZARD_PROTECTION.get(), 60, 0, true, true, true));
				}
			}
			if (appliedEffects) {
				onEffectsApplied(level, blockPos, blockState);
			}
		}
		return appliedEffects;
	}

	public double getEffectRadius();
}
