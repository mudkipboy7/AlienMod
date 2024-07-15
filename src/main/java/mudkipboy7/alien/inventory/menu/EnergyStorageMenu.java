package mudkipboy7.alien.inventory.menu;

import mudkipboy7.alien.inventory.AMMenuTypes;
import mudkipboy7.alien.inventory.menu.slot.MachineBatterySlot;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.item.ItemStack;

public class EnergyStorageMenu extends AbstractMachineMenu {

	private static int numOfCustomSlots = 2;
	private static int containerDataCount = 2;

	// Client
	public EnergyStorageMenu(int id, Inventory playerInventory, FriendlyByteBuf additionalData) {
		this(id, playerInventory, new SimpleContainer(numOfCustomSlots), new SimpleContainerData(containerDataCount));
	}

	// Server
	public EnergyStorageMenu(int containerId, Inventory playerInventory, Container blockContainer, ContainerData data) {
		super(AMMenuTypes.ENERGY_STORAGE.get(), containerId, blockContainer, data);
		// Energy input slot
		this.addSlot(new MachineBatterySlot(blockContainer, 0, 80, 53));
		// Energy output slot
		this.addSlot(new MachineBatterySlot(blockContainer, 1, 80, 17));
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

}
