package mudkipboy7.alien.inventory.menu;

import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerListener;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public abstract class AbstractMachineMenu extends AbstractContainerMenu {
	protected Container container;
	protected ContainerData containerData;

	public AbstractMachineMenu(MenuType<?> menuType, int containerId, Container blockContainer, ContainerData data) {
		super(menuType, containerId);
		this.container = blockContainer;
		this.containerData = data;
		this.addDataSlots(data);
	}

	@Override
	public boolean stillValid(Player player) {
		return this.container.stillValid(player);
	}

	@Override
	public void addSlotListener(ContainerListener listener) {
		listener.dataChanged(this, QUICKCRAFT_HEADER_CONTINUE, CARRIED_SLOT_SIZE);
		super.addSlotListener(listener);
	}

	public int getEnergyStored() {
		return this.containerData.get(0);
	}

	public int getMaxEnergyStored() {
		return this.containerData.get(1);
	}

	@Override
	public ItemStack quickMoveStack(Player player, int index) {
		ItemStack itemStack = ItemStack.EMPTY;
		Slot slot = this.slots.get(index);
		if (slot != null && slot.hasItem()) {
			ItemStack stackBeingMoved = slot.getItem();
			itemStack = stackBeingMoved.copy();
			// Checks if the item being moved is in any of the custom slots
			if (index >= getNumCustomSlots() && index <= getNumCustomSlots() + 36 && this.isValidSlotIndex(index)) {
				// Checks if the item can be placed in any of the custom slots
				if (canBePlacedInAnyCustomSlots(stackBeingMoved)) {
					// If it can it
					if (moveToCustomSlots(stackBeingMoved, index)) {
						// Makes the slot it's being moved from empty
						return ItemStack.EMPTY;
					}
				}
				// If it can't be put in a custom slot tries to move it around the inventory
				else if (index >= getNumCustomSlots()) {
					// checks if the is in the hotbar or not
					if (index < (27 + getNumCustomSlots())) {
						// If it is it tries to move it to the inventory
						if (!this.moveItemStackTo(stackBeingMoved, (27 + getNumCustomSlots()),
								(36 + getNumCustomSlots()), false)) {
							return ItemStack.EMPTY;
						}
						/*
						 * If it isn't in the hotbar tries to move it to the inventory.
						 */
					} else if (index < (36 + getNumCustomSlots()) && !this.moveItemStackTo(stackBeingMoved,
							getNumCustomSlots(), (27 + getNumCustomSlots()), false)) {
						return ItemStack.EMPTY;
					}
					// int numOf

				}
			} else if (!this.moveItemStackTo(stackBeingMoved, getNumCustomSlots(), (36 + getNumCustomSlots()), false))
				return ItemStack.EMPTY;
			/*
			 * Checks if the slot thats trying to be moved is empty.
			 */
			if (stackBeingMoved.isEmpty())
				slot.setByPlayer(ItemStack.EMPTY);
			else
				slot.setChanged();
			if (stackBeingMoved.getCount() == itemStack.getCount())
				return ItemStack.EMPTY;
			slot.onTake(player, stackBeingMoved);
		}
		return itemStack;
	}

	// Makes the player's inventory and the hotbar
	protected void makePlayerInventory(Inventory playerInventory) {
		// Makes the inventory
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 9; ++j) {
				this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}
		// Makes the hotbar
		for (int k = 0; k < 9; ++k) {
			this.addSlot(new Slot(playerInventory, k, 8 + k * 18, 142));
		}
	}

	/**
	 * Checks if the item can be placed in any of the custom slots.
	 * 
	 * @param itemStack The item being checked.
	 * @return If it can.
	 */
	abstract boolean canBePlacedInAnyCustomSlots(ItemStack itemStack);

	/**
	 * Tries to move the item to any of the custom slots.
	 * 
	 * @param stackBeingMoved The item to move.
	 * @param slotIndex       The item's current slot index.
	 * @return
	 */
	abstract boolean moveToCustomSlots(ItemStack stackBeingMoved, int slotIndex);

	/**
	 * @return The total number of custom slots in the container.
	 */
	abstract int getNumCustomSlots();
}
