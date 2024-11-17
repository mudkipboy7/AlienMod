package com.mudkipboy7.alien.world.block.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.HangingSignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class AlienHangingSignBlockEntity extends HangingSignBlockEntity {

	public AlienHangingSignBlockEntity(BlockPos blockPos, BlockState blockState) {
		super(blockPos, blockState);
	}

	@Override
	public BlockEntityType<?> getType() {
		return AMBlockEntities.ALIEN_WOOD_HANGING_SIGN.get();
	}
}
