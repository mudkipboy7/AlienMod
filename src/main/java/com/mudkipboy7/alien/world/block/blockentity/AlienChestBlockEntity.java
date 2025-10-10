package com.mudkipboy7.alien.world.block.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class AlienChestBlockEntity extends ChestBlockEntity {

	public AlienChestBlockEntity(BlockEntityType<AlienChestBlockEntity> type, BlockPos blockPos, BlockState blockState) {
		super(type, blockPos, blockState);
	}

	public AlienChestBlockEntity(BlockPos blockPos, BlockState blockState) {
		this(AMBlockEntities.LIGNUM_CHEST.get(), blockPos, blockState);
	}

}
