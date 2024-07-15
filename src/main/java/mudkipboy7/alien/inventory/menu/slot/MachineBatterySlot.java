package mudkipboy7.alien.inventory.menu.slot;

import mudkipboy7.alien.data.tags.AMItemTags;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class MachineBatterySlot extends Slot {

	public MachineBatterySlot(Container pContainer, int pSlot, int pX, int pY) {
		super(pContainer, pSlot, pX, pY);
	}

	@Override
	public boolean mayPlace(ItemStack itemStack) {
		return canBePlaced(itemStack);
	}

	@Override
	public int getMaxStackSize(ItemStack itemStack) {
		return 1;
	}

	public static boolean canBePlaced(ItemStack itemStack) {
		return itemStack.is(AMItemTags.BATTERIES);
	}
}
