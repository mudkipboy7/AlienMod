package mudkipboy7.alien.inventory.menu;

import mudkipboy7.alien.inventory.AMMenuTypes;
import mudkipboy7.alien.inventory.menu.slot.MachineBatterySlot;
import mudkipboy7.alien.inventory.menu.slot.MachineWaterSlot;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.item.ItemStack;

public class HazardRemovalMenu extends AbstractMachineMenu {
	private static int numOfCustomSlots = 2;
	private static int containerDataCount = 2;

	// Client
	public HazardRemovalMenu(int id, Inventory playerInventory, FriendlyByteBuf additionalData) {
		this(id, playerInventory, new SimpleContainer(numOfCustomSlots), new SimpleContainerData(containerDataCount));
	}

	// Server
	public HazardRemovalMenu(int containerId, Inventory playerInventory, Container blockContainer, ContainerData data) {
		super(AMMenuTypes.HAZARD_REMOVAL.get(), containerId, blockContainer, data);
		// Makes the battery slot
		this.addSlot(new MachineBatterySlot(blockContainer, 0, 80, 53));
		// Makes the water slot
		this.addSlot(new MachineWaterSlot(blockContainer, 1, 80, 17));
		// Makes the player's inventory and hotbar
		makePlayerInventory(playerInventory);
	}

	@Override
	boolean canBePlacedInAnyCustomSlots(ItemStack itemStack) {
		return MachineBatterySlot.canBePlaced(itemStack) || MachineWaterSlot.canBePlaced(itemStack);
	}

	@Override
	boolean moveToCustomSlots(ItemStack stackBeingMoved, int slotIndex) {
		if (MachineBatterySlot.canBePlaced(stackBeingMoved)) {
			if (!this.moveItemStackTo(stackBeingMoved, 0, 1, false))
				return true;
		} else if (MachineWaterSlot.canBePlaced(stackBeingMoved))
			if (!this.moveItemStackTo(stackBeingMoved, 1, getNumCustomSlots(), false))
				return true;
		return false;
	}

	@Override
	int getNumCustomSlots() {
		return numOfCustomSlots;
	}

}
