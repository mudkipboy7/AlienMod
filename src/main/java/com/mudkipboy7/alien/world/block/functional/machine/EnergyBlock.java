package com.mudkipboy7.alien.world.block.functional.machine;

import com.mudkipboy7.alien.world.block.blockentity.AMBlockEntities;
import com.mudkipboy7.alien.world.block.blockentity.machine.EnergyBlockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class EnergyBlock extends AbstractContainerMachineBlock {

	public EnergyBlock(Properties properties) {
		super(properties);
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
		return new EnergyBlockEntity(blockPos, blockState);
	}

	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState,
			BlockEntityType<T> blockEntityType) {
		return level.isClientSide ? null
				: createTickerHelper(blockEntityType, AMBlockEntities.ENERGY_BLOCK.get(),
						EnergyBlockEntity::tick);
	}

}
