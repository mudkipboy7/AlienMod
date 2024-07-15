package mudkipboy7.alien.world.block.flora;

import java.util.OptionalInt;

import mudkipboy7.alien.data.tags.AMBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

@SuppressWarnings("deprecation")
public class AlienLeavesBlock extends Block
		implements SimpleWaterloggedBlock, net.minecraftforge.common.IForgeShearable {
	public static final int DECAY_DISTANCE = 7;
	public static final IntegerProperty DISTANCE = BlockStateProperties.DISTANCE;
	public static final BooleanProperty PERSISTENT = BlockStateProperties.PERSISTENT;
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	//private static final int TICK_DELAY = 1;

	public AlienLeavesBlock(BlockBehaviour.Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(DISTANCE, Integer.valueOf(7))
				.setValue(PERSISTENT, Boolean.valueOf(false)).setValue(WATERLOGGED, Boolean.valueOf(false)));
	}

	public VoxelShape getBlockSupportShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
		return Shapes.empty();
	}

	public boolean isRandomlyTicking(BlockState blockState) {
		return blockState.getValue(DISTANCE) == 7 && !blockState.getValue(PERSISTENT);
	}

	public void randomTick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
		if (this.decaying(blockState)) {
			dropResources(blockState, serverLevel, blockPos);
			serverLevel.removeBlock(blockPos, false);
		}

	}

	protected boolean decaying(BlockState blockState) {
		return !blockState.getValue(PERSISTENT) && blockState.getValue(DISTANCE) == 7;
	}

	public void tick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
		serverLevel.setBlock(blockPos, updateDistance(blockState, serverLevel, blockPos), 3);
	}

	public int getLightBlock(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
		return 1;
	}

	public BlockState updateShape(BlockState p_54440_, Direction p_54441_, BlockState p_54442_, LevelAccessor p_54443_,
			BlockPos p_54444_, BlockPos p_54445_) {
		if (p_54440_.getValue(WATERLOGGED)) {
			p_54443_.scheduleTick(p_54444_, Fluids.WATER, Fluids.WATER.getTickDelay(p_54443_));
		}

		int i = getDistanceAt(p_54442_) + 1;
		if (i != 1 || p_54440_.getValue(DISTANCE) != i) {
			p_54443_.scheduleTick(p_54444_, this, 1);
		}

		return p_54440_;
	}

	private static BlockState updateDistance(BlockState p_54436_, LevelAccessor p_54437_, BlockPos p_54438_) {
		int i = 7;
		BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

		for (Direction direction : Direction.values()) {
			blockpos$mutableblockpos.setWithOffset(p_54438_, direction);
			i = Math.min(i, getDistanceAt(p_54437_.getBlockState(blockpos$mutableblockpos)) + 1);
			if (i == 1) {
				break;
			}
		}

		return p_54436_.setValue(DISTANCE, Integer.valueOf(i));
	}

	private static int getDistanceAt(BlockState p_54464_) {
		return getOptionalDistanceAt(p_54464_).orElse(7);
	}

	public static OptionalInt getOptionalDistanceAt(BlockState blockState) {
		if (blockState.is(AMBlockTags.SUPPORTS_ALIEN_LEAVES)) {
			return OptionalInt.of(0);
		} else {
			return blockState.hasProperty(DISTANCE) ? OptionalInt.of(blockState.getValue(DISTANCE)) : OptionalInt.empty();
		}
	}

	
	public FluidState getFluidState(BlockState p_221384_) {
		return p_221384_.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(p_221384_);
	}

	public void animateTick(BlockState p_221374_, Level p_221375_, BlockPos p_221376_, RandomSource p_221377_) {
		if (p_221375_.isRainingAt(p_221376_.above())) {
			if (p_221377_.nextInt(15) == 1) {
				BlockPos blockpos = p_221376_.below();
				BlockState blockstate = p_221375_.getBlockState(blockpos);
				if (!blockstate.canOcclude() || !blockstate.isFaceSturdy(p_221375_, blockpos, Direction.UP)) {
					ParticleUtils.spawnParticleBelow(p_221375_, p_221376_, p_221377_, ParticleTypes.DRIPPING_WATER);
				}
			}
		}
	}

	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_54447_) {
		p_54447_.add(DISTANCE, PERSISTENT, WATERLOGGED);
	}

	public BlockState getStateForPlacement(BlockPlaceContext p_54424_) {
		FluidState fluidstate = p_54424_.getLevel().getFluidState(p_54424_.getClickedPos());
		BlockState blockstate = this.defaultBlockState().setValue(PERSISTENT, Boolean.valueOf(true))
				.setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER));
		return updateDistance(blockstate, p_54424_.getLevel(), p_54424_.getClickedPos());
	}
}
