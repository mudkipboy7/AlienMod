package com.mudkipboy7.alien.world.block.functional.machine;

import com.mudkipboy7.alien.world.block.blockentity.machine.AbstractContainerMachineBlockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public abstract class AbstractContainerMachineBlock extends AbstractMachineBlock {

	public AbstractContainerMachineBlock(Properties properties) {
		super(properties);
	}

	@Override
	public RenderShape getRenderShape(BlockState blockState) {
		return RenderShape.MODEL;
	}

	/**
	 * Called by BlockItem after this block has been placed.
	 */
	public void setPlacedBy(Level level, BlockPos blockPos, BlockState blockState, LivingEntity placer,
			ItemStack itemStack) {
		if (itemStack.hasCustomHoverName()) {
			BlockEntity blockentity = level.getBlockEntity(blockPos);
			if (blockentity instanceof AbstractContainerMachineBlockEntity machine) {
				machine.setCustomName(itemStack.getHoverName());
			}
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onRemove(BlockState blockSate, Level level, BlockPos blockPos, BlockState newState, boolean pIsMoving) {
		if (!blockSate.is(newState.getBlock())) {
			if (level.getBlockEntity(blockPos) instanceof AbstractContainerMachineBlockEntity machine) {
				if (level instanceof ServerLevel) {
					Containers.dropContents(level, blockPos, machine);
				}
				super.onRemove(blockSate, level, blockPos, newState, pIsMoving);
				level.updateNeighbourForOutputSignal(blockPos, this);
			} else {
				super.onRemove(blockSate, level, blockPos, newState, pIsMoving);
			}
		}
	}

	@Override
	public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player,
			InteractionHand interationHand, BlockHitResult hitResult) {
		if (level.isClientSide) {
			return InteractionResult.SUCCESS;
		} else {
			player.openMenu(blockState.getMenuProvider(level, blockPos));
			return InteractionResult.CONSUME;
		}
	}
}
