package com.mudkipboy7.alien.world.block.blockentity.machine.interfaces;

import com.mudkipboy7.alien.world.energy.AMEnergyStorage;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.EnergyStorage;

public interface IEnergyStoringBlockEntity extends IMachineBlockEntity {
	public abstract EnergyStorage getEnergy();

	public abstract LazyOptional<AMEnergyStorage> getEnergyOptional();

	public default boolean canConnectInDirection(Direction direction) {
		return true;
	}
}
