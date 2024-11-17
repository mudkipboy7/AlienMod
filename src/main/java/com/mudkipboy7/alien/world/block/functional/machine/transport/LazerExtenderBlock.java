package com.mudkipboy7.alien.world.block.functional.machine.transport;

import com.mudkipboy7.alien.world.block.blockentity.machine.transport.LazerExtenderBlockEntity;
import com.mudkipboy7.alien.world.block.functional.machine.AbstractMachineBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class LazerExtenderBlock extends AbstractMachineBlock {
	public LazerExtenderBlock(Properties properties) {
		super(properties);

	}

	@Override
	public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
		return new LazerExtenderBlockEntity(blockPos, blockState);
	}

	@Override
	public RenderShape getRenderShape(BlockState blockState) {
		return RenderShape.MODEL;
	}

}
