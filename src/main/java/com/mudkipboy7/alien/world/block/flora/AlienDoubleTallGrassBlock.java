package com.mudkipboy7.alien.world.block.flora;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;

public class AlienDoubleTallGrassBlock extends DoublePlantBlock implements IAlienPlantBlock{

	public AlienDoubleTallGrassBlock(Properties properties) {
		super(properties);
	}

	@Override
	public boolean mayPlaceOn(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
		return alienGrassMayPlaceOn(blockState, blockGetter, blockPos);
	}

	@Override
	public boolean canSurvive(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
		if (blockState.getValue(HALF) != DoubleBlockHalf.UPPER) {
			return alienGrassCanSurvive(blockState, levelReader, blockPos);
		} else {
			BlockState blockstate = levelReader.getBlockState(blockPos.below());
			if (blockState.getBlock() != this) {
				return alienGrassCanSurvive(blockState, levelReader, blockPos);
			}
			return blockstate.is(this) && blockstate.getValue(HALF) == DoubleBlockHalf.LOWER;
		}
	}
}
