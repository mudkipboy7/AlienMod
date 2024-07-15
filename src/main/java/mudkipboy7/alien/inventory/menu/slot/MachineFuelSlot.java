package mudkipboy7.alien.inventory.menu.slot;

import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeType;

public class MachineFuelSlot extends Slot {

	public MachineFuelSlot(Container container, int slot, int xPos, int yPos) {
		super(container, slot, xPos, yPos);
	}

	@Override
	public boolean mayPlace(ItemStack itemStack) {
		return canBePlaced(itemStack);
	}

	@Override
	public int getMaxStackSize(ItemStack itemStack) {
		return isBucket(itemStack) ? 1 : super.getMaxStackSize(itemStack);
	}

	public static boolean isBucket(ItemStack itemStack) {
		return itemStack.is(Items.BUCKET);
	}

	public static boolean canBePlaced(ItemStack itemStack) {
		return net.minecraftforge.common.ForgeHooks.getBurnTime(itemStack, RecipeType.SMELTING) > 0
				|| isBucket(itemStack);
	}
}
