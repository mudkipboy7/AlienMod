package com.mudkipboy7.alien.world.block.blockentity.machine;

import com.mudkipboy7.alien.world.energy.AMEnergyStorage;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.FurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class ElectricFurnaceBlockEntity extends AbstractContainerMachineBlockEntity {

	public ElectricFurnaceBlockEntity(
			BlockEntityType<?> type, BlockPos blockPos, BlockState blockState,
			AMEnergyStorage energy) {
		super(type, blockPos, blockState, energy);
	}

	@Override
	protected Component getDefaultName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory) {
		// TODO Auto-generated method stub
		return null;
	}



}
