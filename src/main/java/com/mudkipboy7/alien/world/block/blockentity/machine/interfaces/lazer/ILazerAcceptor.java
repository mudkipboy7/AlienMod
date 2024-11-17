package com.mudkipboy7.alien.world.block.blockentity.machine.interfaces.lazer;

import net.minecraftforge.energy.EnergyStorage;

public interface ILazerAcceptor {
	public int tryAcceptEnergy(EnergyStorage acceptingFrom);
}
