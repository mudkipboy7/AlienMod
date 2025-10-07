package com.mudkipboy7.alien.world.energy;

import org.jetbrains.annotations.NotNull;

import net.minecraftforge.energy.IEnergyStorage;

public class AMEnergyMethods {

	/**
	 * Find the ammount of space a specific energy storage has left.
	 * 
	 * @param energyStorage The energy storage to check.
	 * @return The ammount that was determined to be left in this energy storage.
	 */
	public static int getEnergyStorageLeft(@NotNull IEnergyStorage energyStorage) {
		int spaceLeft = energyStorage.getMaxEnergyStored() - energyStorage.getEnergyStored();
		return spaceLeft;
	}

	/**
	 * Checks to see if a specific energy storage has space for a specified ammount
	 * of energy.
	 * 
	 * @param energyStorage    The energy storage to check.
	 * @param ammountToReceive The ammount to check
	 * @return If the energy storage had space to receive the energy ammount
	 *         specified.
	 */
	public static boolean hasEnergyStorageToReceive(@NotNull IEnergyStorage energyStorage, int ammountToReceive) {
		return ammountToReceive <= getEnergyStorageLeft(energyStorage);
	}

	/**
	 * Checks to see a specific energy storage has has space left to provide a
	 * specified ammount of energy.
	 * 
	 * @param energyStorage    The energy storage to check.
	 * @param ammountToProvide The ammount to check
	 * @return If the energy storage had enough energy left to supply the ammount
	 *         specified.
	 */
	public static boolean hasEnoughEnergyToProvide(@NotNull IEnergyStorage energyStorage, int ammountToProvide) {
		return energyStorage.getEnergyStored() >= ammountToProvide;
	}

	/**
	 * Tries to exchange a certain ammount of energy between two energy storages.
	 * 
	 * @param receiver The energy storage that will be receiving the energy.
	 * @param provider The energy storage that will be providing the energy. 
	 *                 provides for free.
	 * @param ammount  The ammount to try to exchange.
	 * @return The amount of energy that was successfully added to the receiver.
	 */
	public static int tryExchangeEnergy(@NotNull IEnergyStorage reciever, @NotNull IEnergyStorage provider,
			int ammount) {
		/*
		 * Checks if the provider has enough energy to supply. If it doesn't it lowers
		 * the amount to what it does have.
		 */
		int ammountToAdd = AMEnergyMethods.hasEnoughEnergyToProvide(provider, ammount) ? ammount
				: provider.getEnergyStored();
		/*
		 * Checks if the receiver has enough space for the energy, if it doesn't it
		 * lowers it to the space it has left.
		 */
		ammountToAdd = AMEnergyMethods.hasEnergyStorageToReceive(reciever, ammountToAdd) ? ammountToAdd
				: AMEnergyMethods.getEnergyStorageLeft(reciever);

		/*
		 * Checks if the value is higher then either of them can supply
		 */
		if (ammountToAdd > 0) {
			if (reciever.canReceive() && provider.canExtract()) {
				int extractedFromProvider = provider.extractEnergy(ammountToAdd, false);
				reciever.receiveEnergy(extractedFromProvider, false);
				return extractedFromProvider;

			}
		}
		return 0;
	}

	/**
	 * Exchanges energy at the default ammount of 20.
	 * 
	 * @param receiver The energy storage that will be receiving the energy.
	 * @param provider The energy storage that will be providing the energy.
	 *                 provides for free.
	 * @return The amount of energy that was successfully added to the receiver.
	 */
	public static int tryExchangeEnergy(@NotNull IEnergyStorage reciever, @NotNull IEnergyStorage provider) {
		return tryExchangeEnergy(reciever, provider, 20);
	}

}
