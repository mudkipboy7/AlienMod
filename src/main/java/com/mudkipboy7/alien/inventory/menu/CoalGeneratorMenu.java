package com.mudkipboy7.alien.inventory.menu;

import com.mudkipboy7.alien.inventory.AMMenuTypes;
import com.mudkipboy7.alien.inventory.menu.slot.MachineBatterySlot;
import com.mudkipboy7.alien.inventory.menu.slot.MachineFuelSlot;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.item.ItemStack;

public class CoalGeneratorMenu extends AbstractMachineMenu {
	private static int numOfCustomSlots = 2;
	private static int containerDataCount = 4;

	// Client
	public CoalGeneratorMenu(int id, Inventory playerInventory, FriendlyByteBuf additionalData) {
		this(id, playerInventory, new SimpleContainer(numOfCustomSlots), new SimpleContainerData(containerDataCount));
	}

	// Server
	public CoalGeneratorMenu(int containerId, Inventory playerInventory, Container blockContainer, ContainerData data) {
		super(AMMenuTypes.COAL_GENERATOR.get(), containerId, blockContainer, data);

		// Makes the fuel slot
		this.addSlot(new MachineBatterySlot(blockContainer, 0, 80, 17));
		// Makes the fuel slot
		this.addSlot(new MachineFuelSlot(blockContainer, 1, 80, 53));

		// Sets up the player's inventory and hotbar
		makePlayerInventory(playerInventory);

	}

	public int getBurnTimeOfFuel() {
		return this.containerData.get(2);
	}

	public int getBurnTimeLeft() {
		return this.containerData.get(3);
	}

	public int getEfficiency() {
		return this.containerData.get(4);
	}

	public int getSpeed() {
		return this.containerData.get(5);
	}

	@Override
	boolean canBePlacedInAnyCustomSlots(ItemStack itemStack) {
		return MachineBatterySlot.canBePlaced(itemStack) || MachineFuelSlot.canBePlaced(itemStack);
	}

	@Override
	boolean moveToCustomSlots(ItemStack stackBeingMoved, int slotIndex) {
		if (MachineBatterySlot.canBePlaced(stackBeingMoved)) {
			if (!this.moveItemStackTo(stackBeingMoved, 0, 1, false))
				return true;
			// Tries to move it to the fuel slot
		} else if (MachineFuelSlot.canBePlaced(stackBeingMoved)) {
			if (!this.moveItemStackTo(stackBeingMoved, 1, numOfCustomSlots, false))
				return true;
		}
		return false;
	}

	@Override
	int getNumCustomSlots() {
		return numOfCustomSlots;
	}
}
