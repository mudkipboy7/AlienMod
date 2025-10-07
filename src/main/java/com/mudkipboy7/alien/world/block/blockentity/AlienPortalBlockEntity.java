package com.mudkipboy7.alien.world.block.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

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
