package com.mudkipboy7.alien.world.block.functional.sign;

import com.mudkipboy7.alien.world.block.blockentity.AlienHangingSignBlockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.WallHangingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;

public class AlienWallHangingSignBlock extends WallHangingSignBlock {
	
	public AlienWallHangingSignBlock(Properties properties, WoodType woodType) {
		super(properties, woodType);
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new AlienHangingSignBlockEntity(pos, state);
	}
}
