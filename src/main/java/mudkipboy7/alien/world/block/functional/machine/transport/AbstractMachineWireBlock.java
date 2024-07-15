package mudkipboy7.alien.world.block.functional.machine.transport;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

import mudkipboy7.alien.world.block.blockentity.machine.interfaces.IEnergyStoringBlockEntity;
import mudkipboy7.alien.world.block.functional.machine.AbstractMachineBlock;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public abstract class AbstractMachineWireBlock extends AbstractMachineBlock {

	private static final Direction[] DIRECTIONS = Direction.values();
	public static final BooleanProperty NORTH = BlockStateProperties.NORTH;
	public static final BooleanProperty EAST = BlockStateProperties.EAST;
	public static final BooleanProperty SOUTH = BlockStateProperties.SOUTH;
	public static final BooleanProperty WEST = BlockStateProperties.WEST;
	public static final BooleanProperty UP = BlockStateProperties.UP;
	public static final BooleanProperty DOWN = BlockStateProperties.DOWN;
	public static final Map<Direction, BooleanProperty> PROPERTY_BY_DIRECTION = ImmutableMap
			.copyOf(Util.make(Maps.newEnumMap(Direction.class), (map) -> {
				map.put(Direction.NORTH, NORTH);
				map.put(Direction.EAST, EAST);
				map.put(Direction.SOUTH, SOUTH);
				map.put(Direction.WEST, WEST);
				map.put(Direction.UP, UP);
				map.put(Direction.DOWN, DOWN);
			}));
	protected final VoxelShape[] shapeByIndex;

	protected AbstractMachineWireBlock(Properties properties, float apothem) {
		super(properties);
		this.shapeByIndex = this.makeShapes(apothem);

	}

	@Override
	public boolean propagatesSkylightDown(BlockState pState, BlockGetter pReader, BlockPos pPos) {
		return true;
	}

	@Override
	public boolean isPathfindable(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos,
			PathComputationType pathCompType) {
		return false;
	}

	private VoxelShape[] makeShapes(float apothem) {
		float f = 0.5F - apothem;
		float f1 = 0.5F + apothem;
		VoxelShape voxelshape = Block.box((double) (f * 16.0F), (double) (f * 16.0F), (double) (f * 16.0F),
				(double) (f1 * 16.0F), (double) (f1 * 16.0F), (double) (f1 * 16.0F));
		VoxelShape[] avoxelshape = new VoxelShape[DIRECTIONS.length];
		for (int i = 0; i < DIRECTIONS.length; ++i) {
			Direction direction = DIRECTIONS[i];
			avoxelshape[i] = Shapes.box(0.5D + Math.min((double) (-apothem), (double) direction.getStepX() * 0.5D),
					0.5D + Math.min((double) (-apothem), (double) direction.getStepY() * 0.5D),
					0.5D + Math.min((double) (-apothem), (double) direction.getStepZ() * 0.5D),
					0.5D + Math.max((double) apothem, (double) direction.getStepX() * 0.5D),
					0.5D + Math.max((double) apothem, (double) direction.getStepY() * 0.5D),
					0.5D + Math.max((double) apothem, (double) direction.getStepZ() * 0.5D));
		}
		VoxelShape[] avoxelshape1 = new VoxelShape[64];
		for (int k = 0; k < 64; ++k) {
			VoxelShape voxelshape1 = voxelshape;
			for (int j = 0; j < DIRECTIONS.length; ++j) {
				if ((k & 1 << j) != 0) {
					voxelshape1 = Shapes.or(voxelshape1, avoxelshape[j]);
				}
			}
			avoxelshape1[k] = voxelshape1;
		}
		return avoxelshape1;
	}

	@Override
	public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos,
			CollisionContext context) {
		return this.shapeByIndex[this.getAABBIndex(blockState)];
	}

	protected int getAABBIndex(BlockState pState) {
		int i = 0;

		for (int j = 0; j < DIRECTIONS.length; ++j) {
			if (pState.getValue(PROPERTY_BY_DIRECTION.get(DIRECTIONS[j]))) {
				i |= 1 << j;
			}
		}
		return i;
	}

	protected boolean canThisConnectTo(BlockGetter blockGetter, BlockPos blockStateToCheck, BlockPos thisPos) {
		if (blockGetter.getBlockState(blockStateToCheck).is(this)) {
			return true;
		} else if (blockGetter.getBlockEntity(blockStateToCheck) instanceof IEnergyStoringBlockEntity) {
			return true;
		}
		return false;
	}
}
