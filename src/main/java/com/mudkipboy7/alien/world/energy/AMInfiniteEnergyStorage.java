package com.mudkipboy7.alien.world.energy;

public class AMInfiniteEnergyStorage extends AMEnergyStorage {

	public AMInfiniteEnergyStorage() {
		super(Integer.MAX_VALUE);
		energy = Integer.MAX_VALUE;
	}

	@Override
	public int receiveEnergy(int maxReceive, boolean simulate) {
		energy = Integer.MAX_VALUE;
		return 0;
	}

	@Override
	public int extractEnergy(int maxExtract, boolean simulate) {
		energy = Integer.MAX_VALUE;
		if (!canExtract())
			return 0;

		int energyExtracted = Math.min(energy, Math.min(this.maxExtract, maxExtract));
		return energyExtracted;
	}
}
