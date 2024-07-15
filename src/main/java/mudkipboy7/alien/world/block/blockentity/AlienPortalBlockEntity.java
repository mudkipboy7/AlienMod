package mudkipboy7.alien.world.block.blockentity;

import java.util.List;

import mudkipboy7.alien.data.tags.AMBiomeTags;
import mudkipboy7.alien.data.tags.AMEntityTypeTags;
import mudkipboy7.alien.data.tags.AMItemTags;
import mudkipboy7.alien.world.effect.AMMobEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

public class AlienPortalBlockEntity extends BlockEntity {
	private static final int TICKS_IN_BETWEEN_CHECKS = 3;
	private int ticksSinceLastCheck = TICKS_IN_BETWEEN_CHECKS;
	public static final double EFFECT_RADIUS = 4.0D;

	public AlienPortalBlockEntity(BlockPos blockPos, BlockState blockState) {
		super(null, blockPos, blockState);
	}

	public static void tick(Level level, BlockPos blockPos, BlockState blockState, AlienPortalBlockEntity blockEntity) {
		if (!level.isClientSide) {
			
			// Checks if its in a valid biome
			if (level.getBiome(blockPos).is(AMBiomeTags.ULTRACOLD)) {
				boolean appliedEffects = false;
				
				// Checks if enough time has passe
					System.out.println("fefw1wewsacef");
					AABB aabb = (new AABB(blockPos)).inflate(EFFECT_RADIUS);
					List<LivingEntity> list = level.getEntitiesOfClass(LivingEntity.class, aabb);
					// Goes over each entity and applies the effects,
					for (LivingEntity livingEntity : list)
						if (true) {
							appliedEffects = true;
							livingEntity.addEffect(new MobEffectInstance(AMMobEffects.HAZARD_PROTECTION.get(), 60, 0,
									true, true, true));

						}
					if (appliedEffects) {
						// Resets the tick counter to 0 and ends code
						blockEntity.ticksSinceLastCheck = 0;
					

					// Ends the code so blockEntity.ticksSinceLastCheck doesn't increase.
					return;
				}
			}
			/*
			 * If there haven't been enough ticks to apply increments
			 * blockEntity.ticksSinceLastCheck by one.
			 */
			else
				blockEntity.ticksSinceLastCheck += 1;
		}
	}

	private static boolean needsProtection(LivingEntity livingEntity) {
		boolean headItem = livingEntity.getItemBySlot(EquipmentSlot.HEAD).is(AMItemTags.SURVIVAL_HEAD);
		boolean chestItem = livingEntity.getItemBySlot(EquipmentSlot.CHEST).is(AMItemTags.SURVIVAL_TORSO);
		boolean legsItem = livingEntity.getItemBySlot(EquipmentSlot.LEGS).is(AMItemTags.SURVIVAL_LEGS);
		return !livingEntity.getType().is(AMEntityTypeTags.COLD_BLOODED) && !(livingEntity instanceof Player player
				&& (player.isSpectator() || player.isCreative()) && !(headItem && chestItem && legsItem));
	}
}
