package com.mudkipboy7.alien.inventory;

import static com.mudkipboy7.alien.AMRegistry.CREATIVE_TABS;
import static com.mudkipboy7.alien.AMRegistry.ITEMS;

import com.mudkipboy7.alien.AlienMod;
import com.mudkipboy7.alien.world.item.AMItems;
import com.mudkipboy7.alien.world.item.functional.BatteryItem;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTab.Output;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.registries.RegistryObject;

public class AMCreativeTabs {

	// Sets the items to be displayed in the order they are programmed in.
	private static boolean displayInOrderOfAddition = true;

	// Blocks tab
	public static final RegistryObject<CreativeModeTab> ALIEN_BLOCKS_TAB = CREATIVE_TABS.register("alienmod_blocks_tab",
			() -> CreativeModeTab.builder().title(Component.translatable("itemGroup." + AlienMod.MODID + ".blocks_tab"))
					.icon(() -> new ItemStack(AMItems.GRAMEN_BLOCK.get())).displayItems((parameters, output) -> {
						if (displayInOrderOfAddition)
							ITEMS.getEntries().forEach(item -> {
								if (item.get() instanceof BlockItem)
									output.accept(item.get());
							});
						else
							blocksTabNormalContents(output);
					}).build());
	// Items tab
	public static final RegistryObject<CreativeModeTab> ALIEN_ITEMS_TAB = CREATIVE_TABS.register("alienmod_items_tab",
			() -> CreativeModeTab.builder().title(Component.translatable("itemGroup." + AlienMod.MODID + ".items_tab"))
					.icon(() -> new ItemStack(AMItems.SURVIVAL_HEAD.get()))

					.displayItems((parameters, output) -> {
						if (displayInOrderOfAddition)
							ITEMS.getEntries().forEach(item -> {
								if (!(item.get() instanceof BlockItem)) {
									// Checks if it is a battery if so it adds a second, filled one
									if (item.get() instanceof BatteryItem battery)
										makeBattery(battery, output);
									else
										output.accept(item.get());
								}
							});
						else
							itemsTabNormalContents(output);
					}).build());

	/*
	 * Makes it where the creative tab will have both a battery fully filled and
	 * empty
	 */
	static void makeBattery(BatteryItem batteryItem, Output output) {
		ItemStack batteryItemStackFull = batteryItem.getDefaultInstance();
		batteryItemStackFull.getCapability(ForgeCapabilities.ENERGY).ifPresent(battery -> {
			if (battery.canReceive())
				battery.receiveEnergy(battery.getMaxEnergyStored(), false);
		});
		output.accept(batteryItem.getDefaultInstance());
		output.accept(batteryItemStackFull);
	}

	/*
	 * The order for the blocks tab. The sorting should go dirt first, then stone.
	 * then wood, then plants.
	 */
	private static void blocksTabNormalContents(Output output) {


	}

	/*
	 * The order for the items tab
	 */
	private static void itemsTabNormalContents(Output output) {


	}

}
