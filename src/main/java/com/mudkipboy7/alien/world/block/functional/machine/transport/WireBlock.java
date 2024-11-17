package com.mudkipboy7.alien.world.block.functional.machine.transport;

import com.mudkipboy7.alien.world.block.blockentity.AMBlockEntities;
import com.mudkipboy7.alien.world.block.blockentity.machine.transport.WireBlockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;

public class WireBlock extends AbstractMachineWireBlock {

	public WireBlock(Properties properties) {
		super(properties, 0.15F);
		this.registerDefaultState(this.stateDefinition.any().setValue(NORTH, Boolean.valueOf(false))
				.setValue(EAST, Boolean.valueOf(false)).setValue(SOUTH, Boolean.valueOf(false))
				.setValue(WEST, Boolean.valueOf(false)).setValue(UP, Boolean.valueOf(false))
				.setValue(DOWN, Boolean.valueOf(false)));

	}

	@SuppressWarnings("deprecation")
	@Override
	public void onPlace(BlockState blockState, Level level, BlockPos blockPos, BlockState oldState,
			boolean movedByPiston) {
		super.onPlace(blockState, level, blockPos, oldState, movedByPiston);

	}

	@Override
	public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
		return new WireBlockEntity(blockPos, blockState);
	}

	@SuppressWarnings("deprecation")
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState,
			BlockEntityType<T> blockEntityType) {
		return level.isClientSide ? null
				: createTickerHelper(blockEntityType, AMBlockEntities.COPPER_WIRE.get(), WireBlockEntity::tick);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void neighborChanged(BlockState blockState, Level level, BlockPos blockPos, Block neighborBlock,
			BlockPos neighborPos, boolean movedByPiston) {
		super.neighborChanged(blockState, level, blockPos, neighborBlock, neighborPos, movedByPiston);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onRemove(BlockState blockState, Level level, BlockPos blockPos, BlockState newState,
			boolean movedByPiston) {
		super.onRemove(blockState, level, blockPos, newState, movedByPiston);
	}

	@Override
	public RenderShape getRenderShape(BlockState blockState) {
		return RenderShape.MODEL;
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		BlockGetter blockGetter = context.getLevel();
		BlockPos blockPos = context.getClickedPos();
		return getStateFromSurroundingBlocks(blockGetter, blockPos);
	}

	@Override
	public BlockState updateShape(BlockState blockState, Direction facingDirection, BlockState blockStateFacing,
			LevelAccessor level, BlockPos currentPos, BlockPos posOfBlockStateFacing) {
		// return blockState.setValue(PROPERTY_BY_DIRECTION.get(facingDirection),
		// Boolean.valueOf(blockStateFacing.is(this)));
		return getStateFromSurroundingBlocks(level, currentPos);
	}

	private BlockState getStateFromSurroundingBlocks(BlockGetter level, BlockPos blockPos) {
		return this.defaultBlockState()
				.setValue(DOWN, Boolean.valueOf(this.canConnectInDirection(level, blockPos, Direction.DOWN)))
				.setValue(UP, Boolean.valueOf(this.canConnectInDirection(level, blockPos, Direction.UP)))
				.setValue(NORTH, Boolean.valueOf(this.canConnectInDirection(level, blockPos, Direction.NORTH)))
				.setValue(EAST, Boolean.valueOf(this.canConnectInDirection(level, blockPos, Direction.EAST)))
				.setValue(SOUTH, Boolean.valueOf(this.canConnectInDirection(level, blockPos, Direction.SOUTH)))
				.setValue(WEST, Boolean.valueOf(this.canConnectInDirection(level, blockPos, Direction.WEST)));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(NORTH, EAST, SOUTH, WEST, UP, DOWN);
	}

}
