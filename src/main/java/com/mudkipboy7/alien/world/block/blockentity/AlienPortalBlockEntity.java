package com.mudkipboy7.alien.world.block.blockentity;

import java.util.List;

import com.mudkipboy7.alien.data.tags.AMBiomeTags;
import com.mudkipboy7.alien.data.tags.AMEntityTypeTags;
import com.mudkipboy7.alien.data.tags.AMItemTags;
import com.mudkipboy7.alien.world.block.blockentity.machine.HazardRemovalMachineBlockEntity;
import com.mudkipboy7.alien.world.effect.AMMobEffects;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

public class AlienPortalBlockEntity extends BlockEntity implements IHazardRemovalBlockEntity {
	public static final double EFFECT_RADIUS = 2.5D;

	public AlienPortalBlockEntity(BlockPos blockPos, BlockState blockState) {
		super(AMBlockEntities.ALIEN_PORTAL.get(), blockPos, blockState);
	}

	public static void tick(Level level, BlockPos blockPos, BlockState blockState, AlienPortalBlockEntity blockEntity) {
		blockEntity.applyEffects(level, blockPos, blockState);
	}

	@Override
	public double getEffectRadius() {
		return EFFECT_RADIUS;
	}
}
