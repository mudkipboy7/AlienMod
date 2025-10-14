package com.mudkipboy7.alien.world.block.flora;

import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ThinAlienLog extends Block implements SimpleWaterloggedBlock {
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	public ThinAlienLog(Properties properties) {
		super(properties);
	}

	@Override
	public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
		return Block.box(4.0D, 0.0D, 4.0D, 12.0D, 16.0D, 12.0D);
	}

	public BlockState updateShape(BlockState pState, Direction pDirection, BlockState pNeighborState,
			LevelAccessor pLevel, BlockPos pPos, BlockPos pNeighborPos) {
		if (pState.getValue(WATERLOGGED)) {
			pLevel.scheduleTick(pPos, Fluids.WATER, Fluids.WATER.getTickDelay(pLevel));
		}

		return super.updateShape(pState, pDirection, pNeighborState, pLevel, pPos, pNeighborPos);
	}

	public FluidState getFluidState(BlockState pState) {
		return pState.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(pState);
	}

	@Nullable
	public BlockState getStateForPlacement(BlockPlaceContext pContext) {
		return this.defaultBlockState().setValue(WATERLOGGED,
				Boolean.valueOf(pContext.getLevel().getFluidState(pContext.getClickedPos()).getType() == Fluids.WATER));
	}

	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
		pBuilder.add(WATERLOGGED);
	}

	public ItemStack pickupBlock(@Nullable Player pPlayer, LevelAccessor pLevel, BlockPos pPos, BlockState pState) {
		return pPlayer != null && pPlayer.isCreative()
				? SimpleWaterloggedBlock.super.pickupBlock(pPlayer, pLevel, pPos, pState)
				: ItemStack.EMPTY;
	}

	public boolean canPlaceLiquid(@Nullable Player pPlayer, BlockGetter pLevel, BlockPos pPos, BlockState pState,
			Fluid pFluid) {
		return pPlayer != null && pPlayer.isCreative()
				? SimpleWaterloggedBlock.super.canPlaceLiquid(pPlayer, pLevel, pPos, pState, pFluid)
				: false;
	}
}
