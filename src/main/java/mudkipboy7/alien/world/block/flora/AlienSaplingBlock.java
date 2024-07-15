package mudkipboy7.alien.world.block.flora;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockState;

public class AlienSaplingBlock extends SaplingBlock implements IAlienPlantBlock {

	public AlienSaplingBlock(AbstractTreeGrower grower, Properties properties) {
		super(grower, properties);
	}

	@Override
	protected boolean mayPlaceOn(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
		return alienGrassMayPlaceOn(blockState, blockGetter, blockPos);
	}

	@Override
	public boolean canSurvive(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
		return alienGrassCanSurvive(blockState, levelReader, blockPos);
	}

}
