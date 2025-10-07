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

	public static final RegistryObject<BlockItem> ALIEN_STONE = simpleBlockItem(AMBlocks.ALIEN_STONE);

	public static final RegistryObject<BlockItem> ALIEN_DIRT = simpleBlockItem(AMBlocks.ALIEN_DIRT);

	public static final RegistryObject<BlockItem> ALIEN_GRASS_BLOCK = simpleBlockItem(AMBlocks.ALIEN_GRASS_BLOCK);

	public static final RegistryObject<BlockItem> ALIEN_COBBLESTONE = simpleBlockItem(AMBlocks.ALIEN_COBBLESTONE);

	public static final RegistryObject<BlockItem> ALIEN_COAL_ORE = simpleBlockItem(AMBlocks.ALIEN_COAL_ORE);

	public static final RegistryObject<BlockItem> ALIEN_PLANKS = simpleBlockItem(AMBlocks.ALIEN_PLANKS);

	public static final RegistryObject<BlockItem> ALIEN_LOG = simpleBlockItem(AMBlocks.ALIEN_LOG);

	public static final RegistryObject<BlockItem> ALIEN_LEAVES = simpleBlockItem(AMBlocks.ALIEN_LEAVES);

	public static final RegistryObject<BlockItem> ALIEN_STONE_STAIRS = simpleBlockItem(AMBlocks.ALIEN_STONE_STAIRS);

	public static final RegistryObject<BlockItem> ALIEN_COBBLESTONE_STAIRS = simpleBlockItem(
			AMBlocks.ALIEN_COBBLESTONE_STAIRS);

	public static final RegistryObject<BlockItem> ALIEN_WOOD_STAIRS = simpleBlockItem(AMBlocks.ALIEN_WOOD_STAIRS);

	public static final RegistryObject<BlockItem> ALIEN_STONE_SLAB = simpleBlockItem(AMBlocks.ALIEN_STONE_SLAB);

	public static final RegistryObject<BlockItem> ALIEN_COBBLESTONE_SLAB = simpleBlockItem(
			AMBlocks.ALIEN_COBBLESTONE_SLAB);

	public static final RegistryObject<BlockItem> ALIEN_WOOD_SLAB = simpleBlockItem(AMBlocks.ALIEN_WOOD_SLAB);

	public static final RegistryObject<BlockItem> ALIEN_BEDROCK = simpleBlockItem(AMBlocks.ALIEN_BEDROCK);

	public static final RegistryObject<BlockItem> ALIEN_CRAFTING_TABLE = simpleBlockItem(AMBlocks.ALIEN_CRAFTING_TABLE);

	public static final RegistryObject<BlockItem> ALIEN_GOLD_ORE = simpleBlockItem(AMBlocks.ALIEN_GOLD_ORE);

	public static final RegistryObject<BlockItem> ALIEN_GRASS = simpleBlockItem(AMBlocks.ALIEN_GRASS);

	public static final RegistryObject<BlockItem> ALIEN_PORTAL = simpleDescBlockItem(AMBlocks.ALIEN_PORTAL);

	public static final RegistryObject<BlockItem> ALIEN_SAPLING = simpleBlockItem(AMBlocks.ALIEN_SAPLING);

	public static final RegistryObject<BlockItem> DEAD_PLANT = simpleDescBlockItem(AMBlocks.DEAD_PLANT);

	public static final RegistryObject<BlockItem> ALIEN_DOUBLE_GRASS = simpleBlockItem(AMBlocks.ALIEN_DOUBLE_GRASS);

	public static final RegistryObject<BlockItem> ALIEN_WOOD_DOOR = simpleBlockItem(AMBlocks.ALIEN_WOOD_DOOR);

	public static final RegistryObject<BlockItem> ALIEN_WOOD_FENCE = simpleBlockItem(AMBlocks.ALIEN_WOOD_FENCE);

	public static final RegistryObject<BlockItem> ALIEN_WOOD_FENCE_GATE = simpleBlockItem(
			AMBlocks.ALIEN_WOOD_FENCE_GATE);

	public static final RegistryObject<BlockItem> ALIEN_WOOD_TRAPDOOR = simpleBlockItem(AMBlocks.ALIEN_WOOD_TRAPDOOR);

	public static final RegistryObject<BlockItem> ALIEN_WOOD_BUTTON = simpleBlockItem(AMBlocks.ALIEN_WOOD_BUTTON);

	public static final RegistryObject<BlockItem> ALIEN_WOOD_PRESSURE_PLATE = simpleBlockItem(
			AMBlocks.ALIEN_WOOD_PRESSURE_PLATE);

	public static final RegistryObject<BlockItem> ALIEN_LOG_ALL_SIDES_SAME = simpleBlockItem(
			AMBlocks.ALIEN_LOG_ALL_SIDES_SAME);

	public static final RegistryObject<SignItem> ALIEN_WOOD_SIGN = ITEMS.register(
			AMBlocks.ALIEN_WOOD_SIGN.getId().getPath(), () -> new SignItem((new Item.Properties()).stacksTo(16),
					AMBlocks.ALIEN_WOOD_SIGN.get(), AMBlocks.ALIEN_WOOD_WALL_SIGN.get()));

	public static final RegistryObject<HangingSignItem> ALIEN_WOOD_HANGING_SIGN = ITEMS.register(
			AMBlocks.ALIEN_WOOD_HANGING_SIGN.getId().getPath(),
			() -> new HangingSignItem(AMBlocks.ALIEN_WOOD_HANGING_SIGN.get(),
					AMBlocks.ALIEN_WOOD_WALL_HANGING_SIGN.get(), (new Item.Properties()).stacksTo(16)));

	public static final RegistryObject<BlockItem> STRIPPED_ALIEN_LOG = simpleBlockItem(AMBlocks.STRIPPED_ALIEN_LOG);

	public static final RegistryObject<BlockItem> STRIPPED_ALIEN_LOG_ALL_SIDES_SAME = simpleBlockItem(
			AMBlocks.STRIPPED_ALIEN_LOG_ALL_SIDES_SAME);

	public static final RegistryObject<BlockItem> AIR_PURIFIER = simpleBlockItem(AMBlocks.AIR_PURIFIER);

	public static final RegistryObject<BlockEntityItem> ALIEN_WOOD_CHEST = ITEMS.register(
			AMBlocks.ALIEN_WOOD_CHEST.getId().getPath(),
			() -> new BlockEntityItem(AMBlocks.ALIEN_WOOD_CHEST.get(), new Item.Properties()));

	public static final RegistryObject<BlockItem> ALIEN_STONE_BUTTON = simpleBlockItem(AMBlocks.ALIEN_STONE_BUTTON);

	public static final RegistryObject<BlockItem> ALIEN_STONE_PRESSURE_PLATE = simpleBlockItem(
			AMBlocks.ALIEN_STONE_PRESSURE_PLATE);

	public static final RegistryObject<BlockItem> ALIEN_COBBLESTONE_WALL = simpleBlockItem(
			AMBlocks.ALIEN_COBBLESTONE_WALL);

	public static final RegistryObject<BlockItem> COAL_GENERATOR = simpleBlockItem(AMBlocks.COAL_GENERATOR);

	public static final RegistryObject<BlockItem> ENERGY_STORAGE = simpleBlockItem(AMBlocks.ENERGY_STORAGE);

	public static final RegistryObject<BlockItem> ALIEN_METAL_ORE = simpleBlockItem(AMBlocks.ALIEN_METAL_ORE);

	public static final RegistryObject<BlockItem> ALIEN_METAL_BLOCK = simpleBlockItem(AMBlocks.ALIEN_METAL_BLOCK);

	public static final RegistryObject<BlockItem> RAW_ALIEN_METAL_BLOCK = simpleBlockItem(
			AMBlocks.RAW_ALIEN_METAL_BLOCK);

	@SuppressWarnings("deprecation")
	public static final RegistryObject<BlockItem> COPPER_WIRE = simpleBlockItem(AMBlocks.COPPER_WIRE);

	public static final RegistryObject<BlockItem> LAZER_CREATOR = simpleDescBlockItem(AMBlocks.LAZER_CREATOR);
	
	public static final RegistryObject<BlockItem> ENERGY_BLOCK = simpleBlockItem(AMBlocks.ENERGY_BLOCK);

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

	public static final RegistryObject<AlienSwordItem> WOODEN_ALIEN_SWORD = ITEMS.register("lignum_sword",
			() -> new AlienSwordItem(AMTeirs.ALIEN_WOOD, 3, -2.4F, new Item.Properties().stacksTo(1)));

	public static final RegistryObject<AlienPickaxeItem> WOODEN_ALIEN_PICKAXE = ITEMS.register("lignum_pickaxe",
			() -> new AlienPickaxeItem(AMTeirs.ALIEN_WOOD, 1, -2.8F, new Item.Properties().stacksTo(1)));

	public static final RegistryObject<AlienAxeItem> WOODEN_ALIEN_AXE = ITEMS.register("lignum_axe",
			() -> new AlienAxeItem(AMTeirs.ALIEN_WOOD, 6.0F, -3.2F, new Item.Properties().stacksTo(1)));

	public static final RegistryObject<AlienShovelItem> WOODEN_ALIEN_SHOVEL = ITEMS.register("lignum_shovel",
			() -> new AlienShovelItem(AMTeirs.ALIEN_WOOD, 1.5F, -3.0F, new Item.Properties().stacksTo(1)));

	public static final RegistryObject<AlienHoeItem> WOODEN_ALIEN_HOE = ITEMS.register("lignum_hoe",
			() -> new AlienHoeItem(AMTeirs.ALIEN_WOOD, 0F, -3.0F, new Item.Properties().stacksTo(1)));

	public static final RegistryObject<AlienSwordItem> STONE_ALIEN_SWORD = ITEMS.register("gelustone_sword",
			() -> new AlienSwordItem(AMTeirs.GELUSTONE, 3, -2.4F, new Item.Properties().stacksTo(1)));

	public static final RegistryObject<AlienPickaxeItem> STONE_ALIEN_PICKAXE = ITEMS.register("gelustone_pickaxe",
			() -> new AlienPickaxeItem(AMTeirs.GELUSTONE, 1, -2.8F, new Item.Properties().stacksTo(1)));

	public static final RegistryObject<AlienAxeItem> STONE_ALIEN_AXE = ITEMS.register("gelustone_axe",
			() -> new AlienAxeItem(AMTeirs.GELUSTONE, 7.0F, -3.2F, new Item.Properties().stacksTo(1)));

	public static final RegistryObject<AlienShovelItem> STONE_ALIEN_SHOVEL = ITEMS.register("gelustone_shovel",
			() -> new AlienShovelItem(AMTeirs.GELUSTONE, 1.5F, -3.0F, new Item.Properties().stacksTo(1)));

	public static final RegistryObject<AlienHoeItem> STONE_ALIEN_HOE = ITEMS.register("gelustone_hoe",
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

	public static final RegistryObject<Item> ALIEN_METAL_INGOT = simpleItem("atsali_ingot");

	public static final RegistryObject<Item> RAW_ALIEN_METAL = simpleItem("raw_atsali");

	public static final RegistryObject<SurvivalArmorItem> ALIEN_METAL_HELMET = ITEMS.register("atsali_helmet",
			() -> new SurvivalArmorItem(AMArmorMaterials.ATSALI, ArmorItem.Type.HELMET,
					new Item.Properties().stacksTo(1), SURVIVAL_HEAD.get()));

	public static final RegistryObject<SurvivalArmorItem> ALIEN_METAL_CHESTPLATE = ITEMS.register("atsali_chestplate",
			() -> new SurvivalArmorItem(AMArmorMaterials.ATSALI, ArmorItem.Type.CHESTPLATE,
					new Item.Properties().stacksTo(1), SURVIVAL_TORSO.get()));

	public static final RegistryObject<SurvivalArmorItem> ALIEN_METAL_LEGGINGS = ITEMS.register("atsali_leggings",
			() -> new SurvivalArmorItem(AMArmorMaterials.ATSALI, ArmorItem.Type.LEGGINGS,
					new Item.Properties().stacksTo(1), SURVIVAL_LEGS.get()));

	public static final RegistryObject<SurvivalArmorItem> ALIEN_METAL_BOOTS = ITEMS.register("atsali_boots",
			() -> new SurvivalArmorItem(AMArmorMaterials.ATSALI, ArmorItem.Type.BOOTS,
					new Item.Properties().stacksTo(1), SURVIVAL_FEET.get()));

	public static final RegistryObject<AlienSwordItem> ALIEN_METAL_SWORD = ITEMS.register("atsali_sword",
			() -> new AlienSwordItem(AMTeirs.ATSALI, 3, -2.4F, new Item.Properties().stacksTo(1)));

	public static final RegistryObject<AlienPickaxeItem> ALIEN_METAL_PICKAXE = ITEMS.register("atsali_pickaxe",
			() -> new AlienPickaxeItem(AMTeirs.ATSALI, 1, -2.8F, new Item.Properties().stacksTo(1)));

	public static final RegistryObject<AlienAxeItem> ALIEN_METAL_AXE = ITEMS.register("atsali_axe",
			() -> new AlienAxeItem(AMTeirs.ATSALI, 6.0F, -3.1F, new Item.Properties().stacksTo(1)));

	public static final RegistryObject<AlienShovelItem> ALIEN_METAL_SHOVEL = ITEMS.register("atsali_shovel",
			() -> new AlienShovelItem(AMTeirs.ATSALI, 1.5F, -3.0F, new Item.Properties().stacksTo(1)));

	public static final RegistryObject<AlienHoeItem> ALIEN_METAL_HOE = ITEMS.register("atsali_hoe",
			() -> new AlienHoeItem(AMTeirs.ATSALI, -2, -1.0F, new Item.Properties().stacksTo(1)));

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
