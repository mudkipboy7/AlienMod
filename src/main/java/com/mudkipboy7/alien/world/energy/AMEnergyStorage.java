package com.mudkipboy7.alien.world.energy;

import net.minecraftforge.energy.EnergyStorage;

public class AMEnergyStorage extends EnergyStorage {

	public AMEnergyStorage(int capacity) {
		super(capacity);
	}

	public AMEnergyStorage(int capacity, int maxTransfer) {
		super(capacity, maxTransfer);
	}

	public AMEnergyStorage(int capacity, int maxReceive, int maxExtract) {
		super(capacity, maxReceive, maxExtract);
	}

	public AMEnergyStorage(int capacity, int maxReceive, int maxExtract, int energy) {
		super(capacity, maxReceive, maxExtract, energy);
	}

	/**
	 * Find the ammount of space this energy storage has left.
	 * 
	 * @return The ammount that was determined to be left in this energy storage.
	 */
	public int getEnergyStorageLeft() {
		return AMEnergyMethods.getEnergyStorageLeft(this);
	}
	
	/**
	 * Checks to see if this energy storage has space for a specified ammount
	 * of energy.
	 * 
	 * @param ammountToReceive The ammount to check
	 * @return If this energy storage had space to receive the energy ammount specified.
	 */
	public boolean hasEnergyStorageToReceive(int ammountToReceive) {
		return AMEnergyMethods.hasEnergyStorageToReceive(this, ammountToReceive);
	}
	
	/**
	 * Checks to see a this energy storage has has space left to provide a
	 * specified ammount of energy.
	 * 
	 * @param ammountToProvide The ammount to check
	 * @return If this energy storage had enough energy left to supply the ammount specified.
	 */
	public boolean hasEnoughEnergyToProvide(int ammountToProvide) {
		return AMEnergyMethods.hasEnoughEnergyToProvide(this, ammountToProvide);
	}
}
