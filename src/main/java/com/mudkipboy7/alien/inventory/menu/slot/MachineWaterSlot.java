package com.mudkipboy7.alien.inventory.menu.slot;

import com.mudkipboy7.alien.data.tags.AMItemTags;

import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class MachineWaterSlot extends Slot {

	public MachineWaterSlot(Container container, int slot, int xPos, int yPos) {
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
		return itemStack.is(AMItemTags.CAN_BE_MADE_INTO_OXYGEN) || isBucket(itemStack);
	}
}
