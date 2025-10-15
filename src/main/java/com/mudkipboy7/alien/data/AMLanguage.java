package com.mudkipboy7.alien.data;

import java.util.List;

import com.mudkipboy7.alien.AMConfig;
import com.mudkipboy7.alien.AlienMod;
import com.mudkipboy7.alien.inventory.AMCreativeTabs;
import com.mudkipboy7.alien.world.block.AMBlocks;
import com.mudkipboy7.alien.world.block.AMFluids;
import com.mudkipboy7.alien.world.effect.AMMobEffects;
import com.mudkipboy7.alien.world.entity.AMEntities;
import com.mudkipboy7.alien.world.entity.boss.JovianBossLines;
import com.mudkipboy7.alien.world.item.AMItems;

import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.registries.RegistryObject;

/**
 * Includes stuff related to language names as well as the generators for the
 * language files.
 */
public class AMLanguage {

	// For reusable affixes for component names
	public static class Affixes {
		// Suffixes
		public static String descSuffix = ".desc";
		public static String full = ".full";
		public static String empty = ".empty";
		public static String partFull = ".part_full";
		public static String nearFull = ".near_full";
		public static String nearEmpty = ".near_empty";

		// Prefixes
		public static String messagePrefix = "mesg." + AlienMod.MODID + ".";
		public static String configPrefix = "config." + AlienMod.MODID + ".";
		public static String containerPrefix = "container." + AlienMod.MODID + ".";
	}

	/*
	 * Components
	 */
	public static Component wrongWeaponMessage = Component.translatable(Affixes.messagePrefix + "wrong_weapon");
	public static Component cantBreakMessage = Component.translatable(Affixes.messagePrefix + "cant_break");

	public static Component airPuriferContainerName = Component.translatable(Affixes.containerPrefix + "air_purifier");
	public static Component coalGeneratorContainerName = Component
			.translatable(Affixes.containerPrefix + "coal_generator");
	public static Component energyStorageBlockName = Component
			.translatable(Affixes.containerPrefix + "energy_storage_block");
	public static Component energyBlockName = Component.translatable(Affixes.containerPrefix + "energy_block");

	/*
	 * Generates the language files.
	 */
	static class AmericanEnglishGenerator extends LanguageProvider {

		public AmericanEnglishGenerator(PackOutput packOutput) {
			super(packOutput, AlienMod.MODID, "en_us");
		}

		// @SuppressWarnings("deprecation")
		@Override
		protected void addTranslations() {
			// The description for the pack.mcmeta file
			add("pack." + AlienMod.MODID + Affixes.descSuffix, "Resources for AlienMod.");

			/*
			 * Jovian Boss messages
			 */
			add(JovianBossLines.WHEN_SUMMONED_IN_WRONG_DIMENSION, "<%s> Coward... Fight me legitimetely.");
			add(JovianBossLines.WHEN_KNOCKED_OFF_SIDE, "<%s> Hah! You think that's gonna work!");
			add(JovianBossLines.WHEN_ATTACKED_IN_CREATIVE_MODE, "<%s> Creative Mode... Really???");
			add(JovianBossLines.DEATH, "<%s> NOOOOOOO!!!!!");
			/*
			 * Config files
			 */
			addConfigDesc(AMConfig.Client.renderFurCoatFirstPerson,
					"Determines if the Fur Coat worn item is displayed over the arm in first person.");
			addConfigDesc(AMConfig.Client.customTitleScreen, "Determines if the custom title screen is rendered.");

			/*
			 * Creative Menu
			 */
			add(AMCreativeTabs.ALIEN_BLOCKS_TAB.get().getDisplayName().getString(), "Alien Mod Blocks");
			add(AMCreativeTabs.ALIEN_ITEMS_TAB.get().getDisplayName().getString(), "Alien Mod Items");

			/*
			 * Menus
			 */
			add(airPuriferContainerName.getString(), "Air Purifier");
			add(coalGeneratorContainerName.getString(), "Coal Generator");
			add(energyStorageBlockName.getString(), "Energy Storage Block");
			add(energyBlockName.getString(), "Energy Block");

			/*
			 * Subtitle
			 */

			/*
			 * Death Message
			 */
			add("death.attack." + AlienMod.MODID + ".no_oxygen", "%1$s's lungs exploded.");
			add("death.attack." + AlienMod.MODID + ".no_oxygen.player",
					"%1$s's lungs exploded while trying to escape %2$s");

			/*
			 * Messages
			 */
			add(wrongWeaponMessage.getString(), "It isn't doing much damage");
			add(cantBreakMessage.getString(), "You can't seem to break the block");

			/*
			 * Effect
			 */
			addEffect(AMMobEffects.NO_OXYGEN, "Hypoxia");
			addEffect(AMMobEffects.COLD, "Freezing");
			addEffect(AMMobEffects.HAZARD_PROTECTION, "Protection Aura");
			addEffect(AMMobEffects.NO_SPRINT, "No Sprinting");

			/*
			 * Potion
			 */
			addPotion(AMMobEffects.AMPotions.HAZARD_PROTECTION_POTION, "Hazard Protection");

			/*
			 * Entity
			 */
			addEntityType(AMEntities.TEST_ENTITY, "Test Entity");
			addEntityType(AMEntities.ALIEN_ZOMBIE, "Alien Zombie");
			addEntityType(AMEntities.JOVIAN_BOSS, "§9§kPlayer");
			addEntityType(AMEntities.JOVIAN_BOSS_MINION, "Jovian Boss Minion");

			/*
			 * Block
			 */
			addBlock(AMBlocks.TEST_BLOCK, "Test Block");
			addItemDesc(AMBlocks.TEST_BLOCK, "Used to test stuff during development.");
			addBlock(AMBlocks.AMMONIA_LIQUID_BLOCK, "Umifer");
			addItemDesc(AMBlocks.AMMONIA_LIQUID_BLOCK,
					"A liquid soultion of ammonia, water, salt, and various other chemicals.");
			add(AMFluids.AMMONIA_LIQUID_TYPE.get().getDescriptionId(), "Umifer");
			addBlock(AMBlocks.ALIEN_AIR, "Alien Air");
			addItemDesc(AMBlocks.ALIEN_AIR, "What! How are you holding air?");
			addBlock(AMBlocks.GELUSTONE, "Gelustone");
			addBlock(AMBlocks.ALGUSSOIL, "Algussoil");
			addBlock(AMBlocks.GRAMEN_BLOCK, "Gramen Block");
			addBlock(AMBlocks.ALIEN_COBBLESTONE, "Cobble Gelustone");
			addBlock(AMBlocks.GELUSTONE_COAL_ORE, "Gelustone Coal Ore");
			addBlock(AMBlocks.LIGNUM_PLANKS, "Lignum Planks");
			addBlock(AMBlocks.LIGNUM_LOG, "Lignum Log");
			addBlock(AMBlocks.LIGNUM_LEAVES, "Lignum Leaves");
			addBlock(AMBlocks.GLELUSTONE_STAIRS, "Gelustone Stairs");
			addBlock(AMBlocks.COBBLE_GELUSTONE_STAIRS, "Cobbled Gelustone Stairs");
			addBlock(AMBlocks.LIGNUM_STAIRS, "Lignum Stairs");
			addBlock(AMBlocks.GELUSTONE_SLAB, "Gelustone Slab");
			addBlock(AMBlocks.COBBLE_GELUSTONE_SLAB, "Cobbled Gelustone Slab");
			addBlock(AMBlocks.LIGNUM_SLAB, "Lignum Slab");
			addBlock(AMBlocks.GELUSTONE_BEDROCK, "Gelustone Bedrock");
			addBlock(AMBlocks.LIGNUM_CRAFTING_TABLE, "Lignum Crafting Table");
			addBlock(AMBlocks.GELUSTONE_GOLD_ORE, "Gelustone Gold Ore");
			addBlock(AMBlocks.TALL_GRAMEN, "Gramen");
			addBlock(AMBlocks.ALIEN_PORTAL, "Alien Dimension Portal");
			addBlock(AMBlocks.LIGNUM_SAPLING, "Lignum Sapling");
			addBlock(AMBlocks.DEAD_PLANT, "Dead Plant");
			addItemDesc(AMBlocks.DEAD_PLANT, "A plant that died from extreme conditions.");
			addBlock(AMBlocks.DOUBLE_TALL_GRAMEN, "Double Tall Gramen");
			addBlock(AMBlocks.LIGNUM_DOOR, "Lignum Door");
			addBlock(AMBlocks.LIGNUM_FENCE, "Lignum Fence");
			addBlock(AMBlocks.LIGNUM_FENCE_GATE, "Lignum Fence Gate");
			addBlock(AMBlocks.LIGNUM_TRAPDOOR, "Lignum Trapdoor");
			addBlock(AMBlocks.LIGNUM_BUTTON, "Lignum Button");
			addBlock(AMBlocks.LIGNUM_PRESSURE_PLATE, "Lignum Pressure Plate");
			addBlock(AMBlocks.LIGNUM_LOG_ALL_SIDES_SAME, "Lignum Wood");
			addBlock(AMBlocks.LIGNUM_SIGN, "Lignum Sign");
			addBlock(AMBlocks.LIGNUM_HANGING_SIGN, "Lignum Hanging Sign");
			// addBlock(AMBlocks.STRIPPED_ALIEN_LOG, "Stripped Lignum Log");
			// addBlock(AMBlocks.STRIPPED_ALIEN_LOG_ALL_SIDES_SAME, "Stripped Lignum Wood");
			addBlock(AMBlocks.AIR_PURIFIER, "Air Purifier");
			addBlock(AMBlocks.LIGNUM_CHEST, "Lignum Chest");
			addBlock(AMBlocks.GELUSTONE_BUTTON, "Gelustone Button");
			addBlock(AMBlocks.GELUSTONE_PRESSURE_PLATE, "Gelustone Pressure Plate");
			addBlock(AMBlocks.COBBLE_GELUSTONE_WALL, "Cobble Gelustone Wall");
			addBlock(AMBlocks.COAL_GENERATOR, "Coal Generator");
			addBlock(AMBlocks.ENERGY_STORAGE, "Energy Storage Block");
			addBlock(AMBlocks.APSIUS_ORE, "Apsius Ore");
			addBlock(AMBlocks.APSIUS_BLOCK, "Block of Apsius");
			addBlock(AMBlocks.COPPER_WIRE, "Copper Wire");
			// addDepricatedItemDesc(AMBlocks.COPPER_WIRE);
			addBlock(AMBlocks.LAZER_CREATOR, "Lazer Creator");
			addItemDesc(AMBlocks.LAZER_CREATOR,
					"Creates a lazer that can be used to transport energy between machines.");
			addBlock(AMBlocks.ENERGY_BLOCK, "Energy Block");
			addBlock(AMBlocks.THIN_LIGNUM_LOG, "Thin Lignum Log");
			addBlock(AMBlocks.GELUSTONE_IRON_ORE, "Gelustone Iron Ore");
			addBlock(AMBlocks.REGOLITH, "Regolith");
			addBlock(AMBlocks.JOVIAN_PORTAL, "Jovian Dimension Portal");
			addBlock(AMBlocks.JOVIAN_RETURN_PORTAL, "Jovian Return Portal");
			addBlock(AMBlocks.JOVIAN_BOSS_SPAWNER, "Jovian Boss Spawner");
			addBlock(AMBlocks.HARDENED_CLOUD, "Hardened Cloud");

			/*
			 * Item
			 */
			addItem(AMItems.TEST_ITEM, "Test Item");
			addItemDesc(AMItems.TEST_ITEM, "Used to test stuff during development.");
			addItem(AMItems.SURVIVAL_HEAD, "Survival Gear");
			addItemDesc(AMItems.SURVIVAL_HEAD, "Protects your lungs from the harsh atmospheres of alien planets.");
			addItem(AMItems.SURVIVAL_TORSO, "Fur Coat");
			addItemDesc(AMItems.SURVIVAL_TORSO, "A warm coat that protects you from frigid tempuratures.");
			addItem(AMItems.SURVIVAL_LEGS, "Fur Pants");
			addItemDesc(AMItems.SURVIVAL_LEGS, "Warm pants that protect you from frigid tempuratures.");
			addItem(AMItems.SURVIVAL_FEET, "Snow Boots");
			addItemDesc(AMItems.SURVIVAL_FEET, "Allows you to run on the cold ground of alien planets.");
			addItem(AMItems.OXYGEN_SACK, "Oxygen Sack");
			addItem(AMItems.AMMONIA_LIQUID_BUCKET, "Umifer Bucket");
			addItemDesc(AMItems.AMMONIA_LIQUID_BUCKET, "A liquid soultion of ammonia and various other chemicals.");
			addItem(AMItems.LIGNUM_SWORD, "Lignum Sword");
			addItem(AMItems.LIGNUM_PICKAXE, "Lignum Pickaxe");
			addItem(AMItems.LIGNUM_AXE, "Lignum Axe");
			addItem(AMItems.LIGNUM_SHOVEL, "Lignum Shovel");
			addItem(AMItems.LIGNUM_HOE, "Lignum Hoe");
			addItem(AMItems.GELUSTONE_SWORD, "Gelustone Sword");
			addItem(AMItems.GELUSTONE_PICKAXE, "Gelustone Pickaxe");
			addItem(AMItems.GELUSTONE_AXE, "Gelustone Axe");
			addItem(AMItems.GELUSTONE_SHOVEL, "Gelustone Shovel");
			addItem(AMItems.GELUSTONE_HOE, "Gelustone Hoe");
			addItem(AMItems.ANTI_GRAVITY, "Anti Gravity Device");
			addItemDesc(AMItems.ANTI_GRAVITY, "Just for fun. When held makes you fly up at a constant rate.");
			addItem(AMItems.ALIENDIM_CREATIVE_TELEPORTER, "Alien Creative Teleporter");
			addItemDesc(AMItems.ALIENDIM_CREATIVE_TELEPORTER,
					"Used to teleport the player to the alien dimension for testing purposes.");
			addChargableItem(AMItems.BATTERY, "Battery", "Full Battery", "Dead Battery", "Partially Filled Battery");
			addChargableItem(AMItems.INT_LIMIT_BATTERY, "Int Limit Battery", "Int Limit Battery", "Int Limit Battery",
					"Int Limit Battery");
			// addItem(AMItems.BATTERY, "Battery");
			// addItem(AMItems.INT_LIMIT_BATTERY, "Integer Limit Battery");
			addItem(AMItems.APSIUS_GEM, "Apsius Gem");
			addItem(AMItems.APSIUS_HELMET, "Apsius Helmet");
			addItem(AMItems.APSIUS_CHESTPLATE, "Apsius Chestplate");
			addItem(AMItems.APSIUS_LEGGINGS, "Apsius Leggings");
			addItem(AMItems.APSIUS_BOOTS, "Apsius Boots");
			addItem(AMItems.APSIUS_SWORD, "Apsius Sword");
			addItem(AMItems.APSIUS_PICKAXE, "Apsius Pickaxe");
			addItem(AMItems.APSIUS_AXE, "Apsius Axe");
			addItem(AMItems.APSIUS_SHOVEL, "Apsius Shovel");
			addItem(AMItems.APSIUS_HOE, "Apsius Hoe");
			addItem(AMItems.TEST_ENTITY_SPAWN_EGG, "Test Entity Spawn Egg");
			addItem(AMItems.ALIEN_SEEDS, "Alien Seeds");
			addItem(AMItems.ALIEN_ZOMBIE_SPAWN_EGG, "Alien Zombie Spawn Egg");

		}

		private void addItemDesc(RegistryObject<? extends ItemLike> itemLike, String text) {
			this.add(getDescription(itemLike.get()).getString(), text);
		}

		private void addDepricatedItemDesc(RegistryObject<? extends ItemLike> itemLike) {
			this.add(getDescription(itemLike.get()).getString(), "§4§m§lDEPRICATED§r§r§r");
		}

		private void addConfigDesc(ConfigValue<?> configOption, String text) {
			List<String> list = configOption.getPath();
			this.add(Affixes.configPrefix + list.get(0) + "." + list.get(1), text);
		}

		private void addChargableItem(RegistryObject<? extends ItemLike> itemLike, String normal, String full,
				String empty, String partFull) {
			String itemName = null;

			if (itemLike.get() instanceof Item item)
				itemName = item.getDescriptionId();
			else if (itemLike.get() instanceof Block block)
				itemName = block.getDescriptionId();

			this.add(itemName, normal);
			this.add(itemName + Affixes.full, full);
			this.add(itemName + Affixes.empty, empty);
			this.add(itemName + Affixes.partFull, partFull);
		}

		private void addPotion(RegistryObject<? extends Potion> potion, String normal, String splash, String lingering,
				String arrow) {
			this.add(potion.get().getName("item.minecraft.potion.effect."), normal);
			this.add(potion.get().getName("item.minecraft.splash_potion.effect."), splash);
			this.add(potion.get().getName("item.minecraft.lingering_potion.effect."), lingering);
			this.add(potion.get().getName("item.minecraft.tipped_arrow.effect."), arrow);
		}

		private void addPotion(RegistryObject<? extends Potion> potion, String name) {
			addPotion(potion, "Potion of " + name, "Splash Potion of " + name, "Lingering Potion of " + name,
					"Arrow of " + name);
		}
	}

	// Gives what the language name of a block/item's description should be.
	public static Component getDescription(ItemLike itemLike) {
		if (itemLike instanceof Item item) {
			return Component.translatable(item.getDescriptionId() + Affixes.descSuffix);
		} else if (itemLike instanceof Block block) {
			return Component.translatable(block.getDescriptionId() + Affixes.descSuffix);
		}
		return null;
	}

	/**
	 * Gets the name that a charagible thing should have based on it's percent full.
	 * Set somewhere out of range for default value
	 */
	public static MutableComponent getChargableLangName(Item itemLike, float percentFull) {
		float fullValue = 100.0F;
		float emptyValue = 0.0F;
		if (percentFull == fullValue)
			return Component.translatable(itemLike.getDescriptionId() + Affixes.full);
		else if (percentFull == emptyValue)
			return Component.translatable(itemLike.getDescriptionId() + Affixes.empty);
		else if (percentFull < fullValue && percentFull > emptyValue)
			return Component.translatable(itemLike.getDescriptionId() + Affixes.partFull);
		else
			return Component.translatable(itemLike.getDescriptionId());
	}

}
