package com.mudkipboy7.alien.world.energy;

import com.mudkipboy7.alien.AlienMod;

import net.minecraft.world.item.ItemStack;

public class EnergyItemStorage extends AMEnergyStorage {
	private final ItemStack itemStack;

	public EnergyItemStorage(ItemStack itemStack, int capacity) {
		super(capacity);
		this.itemStack = itemStack;
	}

	@Override
	public int extractEnergy(int maxExtract, boolean simulate) {
		if (!canExtract())
			return 0;
		int energyStored = getEnergyStored();
		int energyExtracted = Math.min(energyStored, Math.min(this.maxExtract, maxExtract));
		if (!simulate)
			setEnergy(energyStored - energyExtracted);
		return energyExtracted;
	}

	@Override
	public int receiveEnergy(int maxReceive, boolean simulate) {
		if (!canReceive())
			return 0;
		int energyStored = getEnergyStored();
		int energyReceived = Math.min(capacity - energyStored, Math.min(this.maxReceive, maxReceive));
		if (!simulate)
			setEnergy(energyStored + energyReceived);
		return energyReceived;
	}

	@Override
	public int getEnergyStored() {
		return this.itemStack.getOrCreateTag().getInt(AlienMod.ENERGY);
	}

	public void setEnergy(int value) {
		this.itemStack.getOrCreateTag().putInt(AlienMod.ENERGY, value);
	}

}
