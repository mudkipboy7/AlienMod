package com.mudkipboy7.alien.world.item;

import static com.mudkipboy7.alien.AMRegistry.ITEMS;

import java.util.List;

import com.mudkipboy7.alien.data.AMLanguage;
import com.mudkipboy7.alien.world.block.AMBlocks;
import com.mudkipboy7.alien.world.block.AMFluids;
import com.mudkipboy7.alien.world.entity.AMEntities;
import com.mudkipboy7.alien.world.item.functional.AlienDimCreativeTeleporterItem;
import com.mudkipboy7.alien.world.item.functional.AmmoniaLiquidBucketItem;
import com.mudkipboy7.alien.world.item.functional.BatteryItem;
import com.mudkipboy7.alien.world.item.functional.tool.AMTeirs;
import com.mudkipboy7.alien.world.item.functional.tool.AlienAxeItem;
import com.mudkipboy7.alien.world.item.functional.tool.AlienHoeItem;
import com.mudkipboy7.alien.world.item.functional.tool.AlienPickaxeItem;
import com.mudkipboy7.alien.world.item.functional.tool.AlienShovelItem;
import com.mudkipboy7.alien.world.item.functional.tool.AlienSwordItem;
import com.mudkipboy7.alien.world.item.functional.wearable.AMArmorMaterials;
import com.mudkipboy7.alien.world.item.functional.wearable.SurvivalArmorItem;
import com.mudkipboy7.alien.world.item.functional.wearable.SurvivalGearItem;
import com.mudkipboy7.alien.world.item.misc.BlockEntityItem;
import com.mudkipboy7.alien.world.item.misc.TestItem;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.HangingSignItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.RegistryObject;

@SuppressWarnings("unused")
public class AMItems {
	/*
	 * Register Block Items
	 */
	public static final RegistryObject<BlockItem> TEST_BLOCK = simpleDescBlockItem(AMBlocks.TEST_BLOCK,
			new Properties().fireResistant());

	public static final RegistryObject<BlockItem> AMMONIA_LIQUID_ITEM = simpleDescBlockItem(
			AMBlocks.AMMONIA_LIQUID_BLOCK, new Properties().fireResistant());

	public static final RegistryObject<BlockItem> ALIEN_AIR = simpleDescBlockItem(AMBlocks.ALIEN_AIR,
			new Properties().fireResistant());

	public static final RegistryObject<BlockItem> GELUSTONE = simpleBlockItem(AMBlocks.GELUSTONE);

	public static final RegistryObject<BlockItem> ALGUSSOIL = simpleBlockItem(AMBlocks.ALGUSSOIL);

	public static final RegistryObject<BlockItem> GRAMEN_BLOCK = simpleBlockItem(AMBlocks.GRAMEN_BLOCK);

	public static final RegistryObject<BlockItem> COBBLE_GELUSTONE = simpleBlockItem(AMBlocks.ALIEN_COBBLESTONE);

	public static final RegistryObject<BlockItem> GELUSTONE_COAL_ORE = simpleBlockItem(AMBlocks.GELUSTONE_COAL_ORE);

	public static final RegistryObject<BlockItem> LIGNUM_PLANKS = simpleBlockItem(AMBlocks.LIGNUM_PLANKS);

	public static final RegistryObject<BlockItem> LIGNUM_LOG = simpleBlockItem(AMBlocks.LIGNUM_LOG);

	public static final RegistryObject<BlockItem> LIGNUM_LEAVES = simpleBlockItem(AMBlocks.LIGNUM_LEAVES);

	public static final RegistryObject<BlockItem> GLELUSTONE_STAIRS = simpleBlockItem(AMBlocks.GLELUSTONE_STAIRS);

	public static final RegistryObject<BlockItem> COBBLE_GELUSTONE_STAIRS = simpleBlockItem(
			AMBlocks.COBBLE_GELUSTONE_STAIRS);

	public static final RegistryObject<BlockItem> LIGNUM_STAIRS = simpleBlockItem(AMBlocks.LIGNUM_STAIRS);

	public static final RegistryObject<BlockItem> GELUSTONE_SLAB = simpleBlockItem(AMBlocks.GELUSTONE_SLAB);

	public static final RegistryObject<BlockItem> COBBLE_GELUSTONE_SLAB = simpleBlockItem(
			AMBlocks.COBBLE_GELUSTONE_SLAB);

	public static final RegistryObject<BlockItem> LIGNUM_SLAB = simpleBlockItem(AMBlocks.LIGNUM_SLAB);

	public static final RegistryObject<BlockItem> GELUSTONE_BEDROCK = simpleBlockItem(AMBlocks.GELUSTONE_BEDROCK);

	public static final RegistryObject<BlockItem> LIGNUM_CRAFTING_TABLE = simpleBlockItem(
			AMBlocks.LIGNUM_CRAFTING_TABLE);

	public static final RegistryObject<BlockItem> GELUSTONE_GOLD_ORE = simpleBlockItem(AMBlocks.GELUSTONE_GOLD_ORE);

	public static final RegistryObject<BlockItem> TALL_GRAMEN = simpleBlockItem(AMBlocks.TALL_GRAMEN);

	public static final RegistryObject<BlockItem> ALIEN_PORTAL = simpleBlockItem(AMBlocks.ALIEN_PORTAL);

	public static final RegistryObject<BlockItem> LIGNUM_SAPLING = simpleBlockItem(AMBlocks.LIGNUM_SAPLING);

	public static final RegistryObject<BlockItem> DEAD_PLANT = simpleDescBlockItem(AMBlocks.DEAD_PLANT);

	public static final RegistryObject<BlockItem> DOUBLE_TALL_GRAMEN = simpleBlockItem(AMBlocks.DOUBLE_TALL_GRAMEN);

	public static final RegistryObject<BlockItem> LIGNUM_DOOR = simpleBlockItem(AMBlocks.LIGNUM_DOOR);

	public static final RegistryObject<BlockItem> LIGNUM_FENCE = simpleBlockItem(AMBlocks.LIGNUM_FENCE);

	public static final RegistryObject<BlockItem> LIGNUM_FENCE_GATE = simpleBlockItem(AMBlocks.LIGNUM_FENCE_GATE);

	public static final RegistryObject<BlockItem> LIGNUM_TRAPDOOR = simpleBlockItem(AMBlocks.LIGNUM_TRAPDOOR);

	public static final RegistryObject<BlockItem> LIGNUM_BUTTON = simpleBlockItem(AMBlocks.LIGNUM_BUTTON);

	public static final RegistryObject<BlockItem> LIGNUM_PRESSURE_PLATE = simpleBlockItem(
			AMBlocks.LIGNUM_PRESSURE_PLATE);

	public static final RegistryObject<BlockItem> LIGNUM_LOG_ALL_SIDES_SAME = simpleBlockItem(
			AMBlocks.LIGNUM_LOG_ALL_SIDES_SAME);

	public static final RegistryObject<SignItem> LIGNUM_SIGN = ITEMS.register(AMBlocks.LIGNUM_SIGN.getId().getPath(),
			() -> new SignItem((new Item.Properties()).stacksTo(16), AMBlocks.LIGNUM_SIGN.get(),
					AMBlocks.LIGNUM_WALL_SIGN.get()));

	public static final RegistryObject<HangingSignItem> LIGNUM_HANGING_SIGN = ITEMS.register(
			AMBlocks.LIGNUM_HANGING_SIGN.getId().getPath(),
			() -> new HangingSignItem(AMBlocks.LIGNUM_HANGING_SIGN.get(), AMBlocks.LIGNUM_WALL_HANGING_SIGN.get(),
					(new Item.Properties()).stacksTo(16)));

	// public static final RegistryObject<BlockItem> STRIPPED_ALIEN_LOG =
	// simpleBlockItem(AMBlocks.STRIPPED_ALIEN_LOG);

	// public static final RegistryObject<BlockItem>
	// STRIPPED_ALIEN_LOG_ALL_SIDES_SAME = simpleBlockItem(
	// AMBlocks.STRIPPED_ALIEN_LOG_ALL_SIDES_SAME);

	public static final RegistryObject<BlockItem> AIR_PURIFIER = simpleBlockItem(AMBlocks.AIR_PURIFIER);

	public static final RegistryObject<BlockEntityItem> LIGNUM_CHEST = ITEMS.register(
			AMBlocks.LIGNUM_CHEST.getId().getPath(),
			() -> new BlockEntityItem(AMBlocks.LIGNUM_CHEST.get(), new Item.Properties()));

	public static final RegistryObject<BlockItem> GELUSTONE_BUTTON = simpleBlockItem(AMBlocks.GELUSTONE_BUTTON);

	public static final RegistryObject<BlockItem> GELUSTONE_PRESSURE_PLATE = simpleBlockItem(
			AMBlocks.GELUSTONE_PRESSURE_PLATE);

	public static final RegistryObject<BlockItem> COBBLE_GELUSTONE_WALL = simpleBlockItem(
			AMBlocks.COBBLE_GELUSTONE_WALL);

	public static final RegistryObject<BlockItem> COAL_GENERATOR = simpleBlockItem(AMBlocks.COAL_GENERATOR);

	public static final RegistryObject<BlockItem> ENERGY_STORAGE = simpleBlockItem(AMBlocks.ENERGY_STORAGE);

	public static final RegistryObject<BlockItem> APSIUS_ORE = simpleBlockItem(AMBlocks.APSIUS_ORE);

	public static final RegistryObject<BlockItem> APSIUS_BLOCK = simpleBlockItem(AMBlocks.APSIUS_BLOCK);

	@SuppressWarnings("deprecation")
	public static final RegistryObject<BlockItem> COPPER_WIRE = simpleBlockItem(AMBlocks.COPPER_WIRE);

	public static final RegistryObject<BlockItem> LAZER_CREATOR = simpleDescBlockItem(AMBlocks.LAZER_CREATOR);

	public static final RegistryObject<BlockItem> ENERGY_BLOCK = simpleBlockItem(AMBlocks.ENERGY_BLOCK);

	public static final RegistryObject<BlockItem> THIN_LIGNUM_LOG = simpleBlockItem(AMBlocks.THIN_LIGNUM_LOG);

	// public static final RegistryObject<BlockItem> AMMONIA_SNOW_LAYER =
	// simpleBlockItem(AMBlocks.AMMONIA_SNOW_LAYER);
	public static final RegistryObject<BlockItem> GELUSTONE_IRON_ORE = simpleBlockItem(AMBlocks.GELUSTONE_IRON_ORE);

	public static final RegistryObject<BlockItem> REGOLITH = simpleBlockItem(AMBlocks.REGOLITH);

	public static final RegistryObject<BlockItem> JOVIAN_PORTAL = simpleBlockItem(AMBlocks.JOVIAN_PORTAL);

	/*
	 * Register Normal Items
	 */

	public static final RegistryObject<TestItem> TEST_ITEM = ITEMS.register("test_item",
			() -> new TestItem(new Item.Properties().fireResistant()) {
				public void appendHoverText(ItemStack itemStack, Level level, List<Component> component,
						TooltipFlag tooltipFlag) {
					component.add(AMLanguage.getDescription(this));
				}
			});

	public static final RegistryObject<SurvivalGearItem> SURVIVAL_HEAD = ITEMS.register("survival_gear",
			() -> new SurvivalGearItem(EquipmentSlot.HEAD, SoundEvents.ARMOR_EQUIP_ELYTRA,
					new Item.Properties().stacksTo(1)));

	public static final RegistryObject<SurvivalGearItem> SURVIVAL_TORSO = ITEMS.register("fur_coat",
			() -> new SurvivalGearItem(EquipmentSlot.CHEST, SoundEvents.WOOL_PLACE, new Item.Properties().stacksTo(1)));

	public static final RegistryObject<SurvivalGearItem> SURVIVAL_LEGS = ITEMS.register("fur_pants",
			() -> new SurvivalGearItem(EquipmentSlot.LEGS, SoundEvents.WOOL_PLACE, new Item.Properties().stacksTo(1)));

	// Remember that you need to implement a use for this eventually.
	public static final RegistryObject<SurvivalGearItem> SURVIVAL_FEET = ITEMS.register("survival_boots",
			() -> new SurvivalGearItem(EquipmentSlot.FEET, SoundEvents.WOOL_PLACE, new Item.Properties().stacksTo(1)));

	public static final RegistryObject<Item> OXYGEN_SACK = simpleItem("oxygen_sack");

	public static final RegistryObject<BucketItem> AMMONIA_LIQUID_BUCKET = ITEMS.register("ammonia_liquid_bucket",
			() -> new AmmoniaLiquidBucketItem(new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));

	public static final RegistryObject<AlienSwordItem> LIGNUM_SWORD = ITEMS.register("lignum_sword",
			() -> new AlienSwordItem(AMTeirs.ALIEN_WOOD, 3, -2.4F, new Item.Properties().stacksTo(1)));

	public static final RegistryObject<AlienPickaxeItem> LIGNUM_PICKAXE = ITEMS.register("lignum_pickaxe",
			() -> new AlienPickaxeItem(AMTeirs.ALIEN_WOOD, 1, -2.8F, new Item.Properties().stacksTo(1)));

	public static final RegistryObject<AlienAxeItem> LIGNUM_AXE = ITEMS.register("lignum_axe",
			() -> new AlienAxeItem(AMTeirs.ALIEN_WOOD, 6.0F, -3.2F, new Item.Properties().stacksTo(1)));

	public static final RegistryObject<AlienShovelItem> LIGNUM_SHOVEL = ITEMS.register("lignum_shovel",
			() -> new AlienShovelItem(AMTeirs.ALIEN_WOOD, 1.5F, -3.0F, new Item.Properties().stacksTo(1)));

	public static final RegistryObject<AlienHoeItem> LIGNUM_HOE = ITEMS.register("lignum_hoe",
			() -> new AlienHoeItem(AMTeirs.ALIEN_WOOD, 0F, -3.0F, new Item.Properties().stacksTo(1)));

	public static final RegistryObject<AlienSwordItem> GELUSTONE_SWORD = ITEMS.register("gelustone_sword",
			() -> new AlienSwordItem(AMTeirs.GELUSTONE, 3, -2.4F, new Item.Properties().stacksTo(1)));

	public static final RegistryObject<AlienPickaxeItem> GELUSTONE_PICKAXE = ITEMS.register("gelustone_pickaxe",
			() -> new AlienPickaxeItem(AMTeirs.GELUSTONE, 1, -2.8F, new Item.Properties().stacksTo(1)));

	public static final RegistryObject<AlienAxeItem> GELUSTONE_AXE = ITEMS.register("gelustone_axe",
			() -> new AlienAxeItem(AMTeirs.GELUSTONE, 7.0F, -3.2F, new Item.Properties().stacksTo(1)));

	public static final RegistryObject<AlienShovelItem> GELUSTONE_SHOVEL = ITEMS.register("gelustone_shovel",
			() -> new AlienShovelItem(AMTeirs.GELUSTONE, 1.5F, -3.0F, new Item.Properties().stacksTo(1)));

	public static final RegistryObject<AlienHoeItem> GELUSTONE_HOE = ITEMS.register("gelustone_hoe",
			() -> new AlienHoeItem(AMTeirs.GELUSTONE, -1, -2.0F, new Item.Properties().stacksTo(1)));

	public static final RegistryObject<Item> ANTI_GRAVITY = simpleDescItem("anti_gravity",
			new Item.Properties().stacksTo(1));

	public static final RegistryObject<AlienDimCreativeTeleporterItem> ALIENDIM_CREATIVE_TELEPORTER = ITEMS.register(
			"alien_creative_teleporter",
			() -> new AlienDimCreativeTeleporterItem(new Item.Properties().stacksTo(1).fireResistant()));

	public static final RegistryObject<BatteryItem> BATTERY = ITEMS.register("battery",
			() -> new BatteryItem(new Item.Properties(), 8000));

	public static final RegistryObject<BatteryItem> INT_LIMIT_BATTERY = ITEMS.register("int_limit_battery",
			() -> new BatteryItem(new Item.Properties(), Integer.MAX_VALUE));

	public static final RegistryObject<Item> APSIUS_GEM = simpleItem("apsius_gem");

	public static final RegistryObject<SurvivalArmorItem> APSIUS_HELMET = ITEMS.register("apsius_helmet",
			() -> new SurvivalArmorItem(AMArmorMaterials.APSIUS, ArmorItem.Type.HELMET,
					new Item.Properties().stacksTo(1), SURVIVAL_HEAD.get()));

	public static final RegistryObject<SurvivalArmorItem> APSIUS_CHESTPLATE = ITEMS.register("apsius_chestplate",
			() -> new SurvivalArmorItem(AMArmorMaterials.APSIUS, ArmorItem.Type.CHESTPLATE,
					new Item.Properties().stacksTo(1), SURVIVAL_TORSO.get()));

	public static final RegistryObject<SurvivalArmorItem> APSIUS_LEGGINGS = ITEMS.register("apsius_leggings",
			() -> new SurvivalArmorItem(AMArmorMaterials.APSIUS, ArmorItem.Type.LEGGINGS,
					new Item.Properties().stacksTo(1), SURVIVAL_LEGS.get()));

	public static final RegistryObject<SurvivalArmorItem> APSIUS_BOOTS = ITEMS.register("apsius_boots",
			() -> new SurvivalArmorItem(AMArmorMaterials.APSIUS, ArmorItem.Type.BOOTS,
					new Item.Properties().stacksTo(1), SURVIVAL_FEET.get()));

	public static final RegistryObject<AlienSwordItem> APSIUS_SWORD = ITEMS.register("apsius_sword",
			() -> new AlienSwordItem(AMTeirs.APSIUS, 3, -2.4F, new Item.Properties().stacksTo(1)));

	public static final RegistryObject<AlienPickaxeItem> APSIUS_PICKAXE = ITEMS.register("apsius_pickaxe",
			() -> new AlienPickaxeItem(AMTeirs.APSIUS, 1, -2.8F, new Item.Properties().stacksTo(1)));

	public static final RegistryObject<AlienAxeItem> APSIUS_AXE = ITEMS.register("apsius_axe",
			() -> new AlienAxeItem(AMTeirs.APSIUS, 6.0F, -3.1F, new Item.Properties().stacksTo(1)));

	public static final RegistryObject<AlienShovelItem> APSIUS_SHOVEL = ITEMS.register("apsius_shovel",
			() -> new AlienShovelItem(AMTeirs.APSIUS, 1.5F, -3.0F, new Item.Properties().stacksTo(1)));

	public static final RegistryObject<AlienHoeItem> APSIUS_HOE = ITEMS.register("apsius_hoe",
			() -> new AlienHoeItem(AMTeirs.APSIUS, -2, -1.0F, new Item.Properties().stacksTo(1)));

	public static final RegistryObject<ForgeSpawnEggItem> TEST_ENTITY_SPAWN_EGG = simpleSpawnEggItem(
			AMEntities.TEST_ENTITY, 12332847, 16777215);

	public static final RegistryObject<Item> ALIEN_SEEDS = simpleItem("alien_seeds");

	public static final RegistryObject<ForgeSpawnEggItem> ALIEN_ZOMBIE_SPAWN_EGG = simpleSpawnEggItem(
			AMEntities.ALIEN_ZOMBIE, 1966851, 1333820);

	/*
	 * Methods
	 */

	// Method for making basic block items that can have properties set.
	public static RegistryObject<BlockItem> simpleBlockItem(RegistryObject<? extends Block> block,
			Properties properties) {
		return ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), properties));
	}

	// Method for making basic block items with no properties set.
	public static RegistryObject<BlockItem> simpleBlockItem(RegistryObject<? extends Block> block) {
		return simpleBlockItem(block, new Item.Properties());
	}

	// Method for making basic block items that have descriptions.
	public static RegistryObject<BlockItem> simpleDescBlockItem(RegistryObject<? extends Block> block,
			Properties properties) {
		return ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), properties) {
			@Override
			public void appendHoverText(ItemStack itemStack, Level level, List<Component> component,
					TooltipFlag tooltipFlag) {
				component.add(AMLanguage.getDescription(this));
			}
		});
	}

	// Method for making basic block items that have descriptions.
	public static RegistryObject<BlockItem> simpleDescBlockItem(RegistryObject<? extends Block> block) {
		return simpleDescBlockItem(block, new Item.Properties());
	}

	// Method for making basic items that can have properties set.
	private static RegistryObject<Item> simpleItem(String name, Properties properties) {
		return ITEMS.register(name, () -> new Item(properties));
	}

	// Method for making basic items with no properties set.
	private static RegistryObject<Item> simpleItem(String name) {
		return simpleItem(name, new Item.Properties());
	}

	// Method for making basic items that have descriptions.
	private static RegistryObject<Item> simpleDescItem(String name, Properties properties) {
		return ITEMS.register(name, () -> new Item(properties) {
			@Override
			public void appendHoverText(ItemStack itemStack, Level level, List<Component> component,
					TooltipFlag tooltipFlag) {
				component.add(AMLanguage.getDescription(this));
			}
		});
	}

	// Method for making basic items that have descriptions.
	private static RegistryObject<Item> simpleDescItem(String name) {
		return simpleDescItem(name, new Item.Properties());
	}

	// Method for making basic spawn eggs that can have properties set
	private static RegistryObject<ForgeSpawnEggItem> simpleSpawnEggItem(String name,
			RegistryObject<? extends EntityType<? extends Mob>> entity, int backgroundColor, int highlightColor,
			Properties properties) {
		return ITEMS.register(name, () -> new ForgeSpawnEggItem(entity, backgroundColor, highlightColor, properties));
	}

	// Method for making basic spawn eggs
	private static RegistryObject<ForgeSpawnEggItem> simpleSpawnEggItem(
			RegistryObject<? extends EntityType<? extends Mob>> entity, int backgroundColor, int highlightColor) {
		return simpleSpawnEggItem(entity.getId().getPath() + "_spawn_egg", entity, backgroundColor, highlightColor,
				new Item.Properties());
	}

}
