package com.mudkipboy7.alien.inventory.menu;

import com.mudkipboy7.alien.inventory.AMMenuTypes;
import com.mudkipboy7.alien.inventory.menu.slot.MachineBatterySlot;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.item.ItemStack;

public class EnergyBlockMenu extends AbstractMachineMenu {

	private static int numOfCustomSlots = 1;
	private static int containerDataCount = 1;

	// Client
	public EnergyBlockMenu(int id, Inventory playerInventory, FriendlyByteBuf additionalData) {
		this(id, playerInventory, new SimpleContainer(numOfCustomSlots), new SimpleContainerData(containerDataCount));
	}

	// Server
	public EnergyBlockMenu(int containerId, Inventory playerInventory, Container blockContainer, ContainerData data) {
		super(AMMenuTypes.ENERGY_BLOCK.get(), containerId, blockContainer, data);
		// Energy output slot
		this.addSlot(new MachineBatterySlot(blockContainer, 0, 80, 35));
		// Sets up the player's inventory and hotbar
		makePlayerInventory(playerInventory);

	}

	@Override
	boolean canBePlacedInAnyCustomSlots(ItemStack itemStack) {
		return MachineBatterySlot.canBePlaced(itemStack);
	}

	@Override
	boolean moveToCustomSlots(ItemStack stackBeingMoved, int slotIndex) {
		return !this.moveItemStackTo(stackBeingMoved, 0, numOfCustomSlots, false);
	}

	@Override
	int getNumCustomSlots() {
		return numOfCustomSlots;
	}

	@Override
	public int getMaxEnergyStored() {
		return Integer.MAX_VALUE;
	}

}
