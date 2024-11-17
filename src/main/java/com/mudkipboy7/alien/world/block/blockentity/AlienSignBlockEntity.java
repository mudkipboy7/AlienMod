package com.mudkipboy7.alien.world.block.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class AlienSignBlockEntity extends SignBlockEntity {

	public AlienSignBlockEntity(BlockPos blockPos, BlockState blockState) {
		super(blockPos, blockState);
	}

	@Override
	public BlockEntityType<?> getType() {
		return AMBlockEntities.ALIEN_WOOD_SIGN.get();
	}
}
