package mudkipboy7.alien.inventory;

import mudkipboy7.alien.AlienMod;
import mudkipboy7.alien.world.item.AMItems;
import mudkipboy7.alien.world.item.functional.BatteryItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTab.Output;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class AMCreativeTabs {
	public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister
			.create(Registries.CREATIVE_MODE_TAB, AlienMod.MODID);

	// Sets the items to be displayed in the order they are programmed in.
	private static boolean displayInOrderOfAddition = true;

	// Blocks tab
	public static final RegistryObject<CreativeModeTab> ALIEN_BLOCKS_TAB = CREATIVE_TABS.register("alienmod_blocks_tab",
			() -> CreativeModeTab.builder().title(Component.translatable("itemGroup." + AlienMod.MODID + ".blocks_tab"))
					.icon(() -> new ItemStack(AMItems.ALIEN_GRASS_BLOCK.get())).displayItems((parameters, output) -> {
						if (displayInOrderOfAddition)
							AMItems.ITEMS.getEntries().forEach(item -> {
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
							AMItems.ITEMS.getEntries().forEach(item -> {
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
		// Dirt
		output.accept(AMItems.ALIEN_GRASS_BLOCK.get());
		output.accept(AMItems.ALIEN_DIRT.get());

		// Stone
		output.accept(AMItems.ALIEN_STONE.get());
		output.accept(AMItems.ALIEN_STONE_STAIRS.get());
		output.accept(AMItems.ALIEN_STONE_SLAB.get());

		output.accept(AMItems.ALIEN_COBBLESTONE.get());
		output.accept(AMItems.ALIEN_COBBLESTONE_STAIRS.get());
		output.accept(AMItems.ALIEN_COBBLESTONE_SLAB.get());

		output.accept(AMItems.ALIEN_BEDROCK.get());

		output.accept(AMItems.ALIEN_COAL_ORE.get());
		output.accept(AMItems.ALIEN_GOLD_ORE.get());

		// Wood
		output.accept(AMItems.ALIEN_LOG.get());
		output.accept(AMItems.ALIEN_LOG_ALL_SIDES_SAME.get());

		output.accept(AMItems.ALIEN_PLANKS.get());
		output.accept(AMItems.ALIEN_WOOD_STAIRS.get());
		output.accept(AMItems.ALIEN_WOOD_SLAB.get());
		output.accept(AMItems.ALIEN_WOOD_FENCE.get());
		output.accept(AMItems.ALIEN_WOOD_FENCE_GATE.get());
		output.accept(AMItems.ALIEN_WOOD_DOOR.get());
		output.accept(AMItems.ALIEN_WOOD_TRAPDOOR.get());
		output.accept(AMItems.ALIEN_WOOD_PRESSURE_PLATE.get());
		output.accept(AMItems.ALIEN_WOOD_BUTTON.get());
		output.accept(AMItems.ALIEN_WOOD_SIGN.get());
		output.accept(AMItems.ALIEN_WOOD_HANGING_SIGN.get());

		// Plants
		output.accept(AMItems.ALIEN_LEAVES.get());
		output.accept(AMItems.ALIEN_SAPLING.get());

		// output.accept(AMItems.DEAD_PLANT.get());

		output.accept(AMItems.ALIEN_GRASS.get());
		output.accept(AMItems.ALIEN_DOUBLE_GRASS.get());

	}

	/*
	 * The order for the items tab
	 */
	private static void itemsTabNormalContents(Output output) {

		// Non-Armor Survival Gear
		output.accept(AMItems.SURVIVAL_HEAD.get());
		output.accept(AMItems.SURVIVAL_TORSO.get());
		output.accept(AMItems.SURVIVAL_LEGS.get());
		output.accept(AMItems.SURVIVAL_FEET.get());

		// Ingredients
		output.accept(AMItems.OXYGEN_SACK.get());

		// Weapons
		output.accept(AMItems.WOODEN_ALIEN_SWORD.get());
		output.accept(AMItems.STONE_ALIEN_SWORD.get());

		// Armor

		// Tools
		output.accept(AMItems.WOODEN_ALIEN_SHOVEL.get());
		output.accept(AMItems.WOODEN_ALIEN_PICKAXE.get());
		output.accept(AMItems.WOODEN_ALIEN_AXE.get());
		output.accept(AMItems.WOODEN_ALIEN_HOE.get());

		output.accept(AMItems.STONE_ALIEN_SHOVEL.get());
		output.accept(AMItems.STONE_ALIEN_PICKAXE.get());
		output.accept(AMItems.STONE_ALIEN_AXE.get());
		output.accept(AMItems.STONE_ALIEN_HOE.get());

		output.accept(AMItems.AMMONIA_LIQUID_BUCKET.get());

	}

}
