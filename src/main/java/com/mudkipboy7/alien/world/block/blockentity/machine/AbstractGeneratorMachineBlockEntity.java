package com.mudkipboy7.alien.world.block.blockentity.machine;

import com.mudkipboy7.alien.world.block.blockentity.machine.interfaces.lazer.ILazerCanExtractFrom;
import com.mudkipboy7.alien.world.energy.AMEnergyStorage;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public abstract class AbstractGeneratorMachineBlockEntity extends AbstractContainerMachineBlockEntity
		implements ILazerCanExtractFrom {
	public AbstractGeneratorMachineBlockEntity(BlockEntityType<?> type, BlockPos blockPos, BlockState blockState,
			AMEnergyStorage energy, NonNullList<ItemStack> inv) {
		super(type, blockPos, blockState, energy, inv);
	}
}
