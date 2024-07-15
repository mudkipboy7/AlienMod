package mudkipboy7.alien.world.block.flora;

import mudkipboy7.alien.data.tags.AMBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;

interface IAlienPlantBlock {
	/*
	 * This exists so I can have common things to return when overriding classes
	 * that extend BushBlock, I don't want to have to go through each class every
	 * time I need to change something about placement.
	 */

	// Return this method when overriding the mayPlaceOn() method
	default boolean alienGrassMayPlaceOn(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
		return blockState.is(AMBlockTags.ALIEN_SOIL);
	}

	// Return this when overriding the canSurvive() method
	default boolean alienGrassCanSurvive(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
		BlockPos belowPos = blockPos.below();
		// if (blockState.getBlock() == this)
		// return levelReader.getBlockState(blockPos).canSustainPlant(levelReader,
		// blockPos, Direction.UP, this);
		return this.alienGrassMayPlaceOn(levelReader.getBlockState(belowPos), levelReader, belowPos);
	}
}
