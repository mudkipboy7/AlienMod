package com.mudkipboy7.alien.world.block.flora;

import com.mudkipboy7.alien.world.block.AMBlocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.SpreadingSnowyDirtBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.lighting.LightEngine;

public class AlienGrassBlock extends SpreadingSnowyDirtBlock {

	public AlienGrassBlock(Properties properties) {
		super(properties);
		// TODO Auto-generated constructor stub
	}
	private static boolean canBeGrass(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
		BlockPos blockpos = blockPos.above();
		BlockState blockstate = levelReader.getBlockState(blockpos);
		if (blockstate.is(Blocks.SNOW) && blockstate.getValue(SnowLayerBlock.LAYERS) == 1) {
			return true;
		} else if (blockstate.getFluidState().getAmount() == 8) {
			return false;
		} else {
			int i = LightEngine.getLightBlockInto(levelReader, blockState, blockPos, blockstate, blockpos, Direction.UP,
					blockstate.getLightBlock(levelReader, blockpos));
			return i < levelReader.getMaxLightLevel();
		}
	}

	private static boolean canPropagate(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
		BlockPos blockpos = blockPos.above();
		return canBeGrass(blockState, levelReader, blockPos)
				&& !levelReader.getFluidState(blockpos).is(FluidTags.WATER);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void randomTick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos,
			RandomSource randomSource) {
		if (!canBeGrass(blockState, serverLevel, blockPos)) {
			if (!serverLevel.isAreaLoaded(blockPos, 1))
				return; // Forge: prevent loading unloaded chunks when checking neighbor's light and
						// spreading
			serverLevel.setBlockAndUpdate(blockPos, AMBlocks.ALIEN_DIRT.get().defaultBlockState());
		} else {
			if (!serverLevel.isAreaLoaded(blockPos, 3))
				return; // Forge: prevent loading unloaded chunks when checking neighbor's light and
						// spreading
			if (serverLevel.getMaxLocalRawBrightness(blockPos.above()) >= 9) {
				BlockState blockstate = this.defaultBlockState();

				for (int i = 0; i < 4; ++i) {
					BlockPos blockpos = blockPos.offset(randomSource.nextInt(3) - 1, randomSource.nextInt(5) - 3,
							randomSource.nextInt(3) - 1);

					if (serverLevel.getBlockState(blockpos).is(AMBlocks.ALIEN_DIRT.get())
							&& canPropagate(blockstate, serverLevel, blockpos)) {

						serverLevel.setBlockAndUpdate(blockpos, blockstate.setValue(SNOWY,
								Boolean.valueOf(serverLevel.getBlockState(blockpos.above()).is(Blocks.SNOW))));

					}

				}
			}

		}
	}
}
