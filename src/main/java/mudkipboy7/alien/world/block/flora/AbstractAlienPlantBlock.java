package mudkipboy7.alien.world.block.flora;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;

abstract class AbstractAlienPlantBlock extends BushBlock implements IAlienPlantBlock {

	public AbstractAlienPlantBlock(Properties properties) {
		super(properties);
	}

	@Override
	public boolean mayPlaceOn(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
		return alienGrassMayPlaceOn(blockState, blockGetter, blockPos);
	}

	@Override
	public boolean canSurvive(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
		//BlockPos belowPos = blockPos.below();
		// if (blockState.getBlock() == this)
		// return levelReader.getBlockState(blockPos).canSustainPlant(levelReader,
		// blockPos, Direction.UP, this);
		return alienGrassCanSurvive(blockState, levelReader, blockPos);
	}
}
