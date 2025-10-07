package com.mudkipboy7.alien.data.model;

import com.mudkipboy7.alien.AlienMod;
import com.mudkipboy7.alien.world.block.AMFluids;
import com.mudkipboy7.alien.world.block.fluid.AmmoniaFluidType;
import com.mudkipboy7.alien.world.item.AMItems;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.loaders.SeparateTransformsModelBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

@SuppressWarnings("unused")
public class AMItemModelGen extends ItemModelProvider {

	static String itemFolder = "item/";

	// Stuff for block textures
	private static String blockFolder = AMBlockModelGen.blockFolder;
	private static String plantBlockFolder = AMBlockModelGen.plantBlockFolder;

	private static String doorBlockFolder = AMBlockModelGen.doorBlockFolder;

	private static String handheldFolder = itemFolder + "handheld/";

	public AMItemModelGen(PackOutput packOutput, ExistingFileHelper existingFileHelper) {
		super(packOutput, AlienMod.MODID, existingFileHelper);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void registerModels() {
		/*
		 * Block Items
		 */

		blockItem(AMItems.TEST_BLOCK);
		genItem(AMItems.AMMONIA_LIQUID_ITEM, AmmoniaFluidType.AMMONIA_LIQUID_TEXTURE);
		genItem(AMItems.ALIEN_AIR, AlienMod.location(blockFolder + AMItems.ALIEN_AIR.getId().getPath()));
		blockItem(AMItems.ALIEN_STONE);
		blockItem(AMItems.ALIEN_DIRT);
		blockItem(AMItems.ALIEN_GRASS_BLOCK);
		blockItem(AMItems.ALIEN_COBBLESTONE);
		blockItem(AMItems.ALIEN_COAL_ORE);
		blockItem(AMItems.ALIEN_PLANKS);
		blockItem(AMItems.ALIEN_LOG);
		blockItem(AMItems.ALIEN_LEAVES);
		blockItem(AMItems.ALIEN_STONE_STAIRS);
		blockItem(AMItems.ALIEN_COBBLESTONE_STAIRS);
		blockItem(AMItems.ALIEN_WOOD_STAIRS);
		blockItem(AMItems.ALIEN_STONE_SLAB);
		blockItem(AMItems.ALIEN_COBBLESTONE_SLAB);
		blockItem(AMItems.ALIEN_WOOD_SLAB);
		blockItem(AMItems.ALIEN_BEDROCK);
		blockItem(AMItems.ALIEN_CRAFTING_TABLE);
		blockItem(AMItems.ALIEN_GOLD_ORE);
		genItem(AMItems.ALIEN_PORTAL, AlienMod.location(blockFolder + AMItems.ALIEN_PORTAL.getId().getPath()));
		genItem(AMItems.ALIEN_GRASS, AlienMod.location(plantBlockFolder + "gramen"));
		genItem(AMItems.ALIEN_SAPLING, AlienMod.location(plantBlockFolder + "lignum_sapling"));
		genItem(AMItems.DEAD_PLANT, AlienMod.location(plantBlockFolder + "dead_plant_sapling"));
		genItem(AMItems.ALIEN_DOUBLE_GRASS, AlienMod.location(plantBlockFolder + "tall_gramen_top"));
		genItem(AMItems.ALIEN_WOOD_DOOR);
		withExistingParent(AMItems.ALIEN_WOOD_FENCE.getId().getPath(), "block/fence_inventory").texture("texture",
				AMBlockModelGen.alienPlanks);
		blockItem(AMItems.ALIEN_WOOD_FENCE_GATE);
		blockItem(AMItems.ALIEN_WOOD_TRAPDOOR,
				AlienMod.location(blockFolder + AMItems.ALIEN_WOOD_TRAPDOOR.getId().getPath() + "_bottom"));
		withExistingParent(AMItems.ALIEN_WOOD_BUTTON.getId().getPath(), "block/button_inventory").texture("texture",
				AMBlockModelGen.alienPlanks);
		blockItem(AMItems.ALIEN_WOOD_PRESSURE_PLATE);
		blockItem(AMItems.ALIEN_LOG_ALL_SIDES_SAME);
		genItem(AMItems.ALIEN_WOOD_SIGN);
		genItem(AMItems.ALIEN_WOOD_HANGING_SIGN);
		blockItem(AMItems.STRIPPED_ALIEN_LOG);
		blockItem(AMItems.STRIPPED_ALIEN_LOG_ALL_SIDES_SAME);
		blockItem(AMItems.AIR_PURIFIER);
		withExistingParent(AMItems.ALIEN_WOOD_CHEST.getId().getPath(), "minecraft:item/chest").texture("particle",
				AMBlockModelGen.alienPlanks);

		withExistingParent(AMItems.ALIEN_STONE_BUTTON.getId().getPath(), "block/button_inventory").texture("texture",
				AMBlockModelGen.alienStone);
		blockItem(AMItems.ALIEN_STONE_PRESSURE_PLATE);

		withExistingParent(AMItems.ALIEN_COBBLESTONE_WALL.getId().getPath(), "block/wall_inventory").texture("wall",
				AMBlockModelGen.alienCobbestone);
		blockItem(AMItems.COAL_GENERATOR);
		blockItem(AMItems.ENERGY_STORAGE);
		blockItem(AMItems.ALIEN_METAL_ORE);
		blockItem(AMItems.ALIEN_METAL_BLOCK);
		blockItem(AMItems.RAW_ALIEN_METAL_BLOCK);
		genItem(AMItems.COPPER_WIRE);
		blockItem(AMItems.LAZER_CREATOR);
		blockItem(AMItems.ENERGY_BLOCK);

		/*
		 * Items
		 */

		genItem(AMItems.TEST_ITEM, AlienMod.location(blockFolder + "test_block"));
		// genItem(AMItems.SURVIVAL_HEAD);

		ResourceLocation survivalGear = AlienMod.location(itemFolder + AMItems.SURVIVAL_HEAD.getId().getPath());

		withExistingParent(AMItems.SURVIVAL_HEAD.getId().getPath(), "item/generated")
				.customLoader(SeparateTransformsModelBuilder::begin)
				.base(nested().parent(getExistingFile(new ResourceLocation("item/generated"))).texture("layer0",
						survivalGear))
				.perspective(ItemDisplayContext.HEAD, nested()).end();

		genItem(AMItems.SURVIVAL_TORSO);
		genItem(AMItems.SURVIVAL_LEGS);
		genItem(AMItems.SURVIVAL_FEET);
		genItem(AMItems.OXYGEN_SACK);
		genItem(AMItems.AMMONIA_LIQUID_BUCKET);
		handheldItem(AMItems.WOODEN_ALIEN_SWORD);
		handheldItem(AMItems.WOODEN_ALIEN_PICKAXE);
		handheldItem(AMItems.WOODEN_ALIEN_AXE);
		handheldItem(AMItems.WOODEN_ALIEN_SHOVEL);
		handheldItem(AMItems.WOODEN_ALIEN_HOE);
		handheldItem(AMItems.WOODEN_ALIEN_SWORD);
		handheldItem(AMItems.STONE_ALIEN_SWORD);
		handheldItem(AMItems.STONE_ALIEN_PICKAXE);
		handheldItem(AMItems.STONE_ALIEN_AXE);
		handheldItem(AMItems.STONE_ALIEN_SHOVEL);
		handheldItem(AMItems.STONE_ALIEN_HOE);
		handheldItem(AMItems.STONE_ALIEN_SWORD);
		genItem(AMItems.ANTI_GRAVITY);
		genItem(AMItems.ALIENDIM_CREATIVE_TELEPORTER);
		genItem(AMItems.BATTERY);
		genItem(AMItems.INT_LIMIT_BATTERY);
		genItem(AMItems.ALIEN_METAL_INGOT);
		genItem(AMItems.RAW_ALIEN_METAL);
		genItem(AMItems.ALIEN_METAL_HELMET);
		genItem(AMItems.ALIEN_METAL_CHESTPLATE);
		genItem(AMItems.ALIEN_METAL_LEGGINGS);
		genItem(AMItems.ALIEN_METAL_BOOTS);
		handheldItem(AMItems.ALIEN_METAL_SWORD);
		handheldItem(AMItems.ALIEN_METAL_PICKAXE);
		handheldItem(AMItems.ALIEN_METAL_AXE);
		handheldItem(AMItems.ALIEN_METAL_SHOVEL);
		handheldItem(AMItems.ALIEN_METAL_HOE);
		handheldItem(AMItems.ALIEN_METAL_SWORD);
		spawnEggItem(AMItems.TEST_ENTITY_SPAWN_EGG);
		genItem(AMItems.ALIEN_SEEDS);
		spawnEggItem(AMItems.ALIEN_ZOMBIE_SPAWN_EGG);

	}

	/*
	 * Methods
	 */

	/* Makes a basic block item that can have a different model from it's block. */
	protected ItemModelBuilder blockItem(RegistryObject<? extends Item> item, ResourceLocation modelPath) {
		return withExistingParent(item.getId().getPath(), modelPath);
	}

	/* Makes a basic block item that has the same model as it's block. */
	protected ItemModelBuilder blockItem(RegistryObject<? extends Item> item) {
		return blockItem(item, AlienMod.location(blockFolder + item.getId().getPath()));
	}

	/*
	 * Makes an item that has the "item/generated" model and can have a specified
	 * texture.
	 */
	protected ItemModelBuilder genItem(RegistryObject<? extends Item> item, ResourceLocation texturePath) {
		return withExistingParent(item.getId().getPath(), "item/generated").texture("layer0", texturePath);
	}

	/*
	 * Makes an item that has the "item/generated" model and the texture of it's
	 * name.
	 */
	protected ItemModelBuilder genItem(RegistryObject<? extends Item> item) {
		return genItem(item, AlienMod.location(itemFolder + item.getId().getPath()));

	}

	/*
	 * Makes an item that has the "item/handheld" model and can have a specified
	 * texture.
	 */
	protected ItemModelBuilder handheldItem(RegistryObject<? extends Item> item, ResourceLocation texturePath) {
		return withExistingParent(item.getId().getPath(), "item/handheld").texture("layer0", texturePath);
	}

	/*
	 * Makes an item that has the "item/handheld" model and the texture of it's
	 * name. Also makes it be in the item/handheld folder
	 */
	protected ItemModelBuilder handheldItem(RegistryObject<? extends Item> item) {
		return handheldItem(item, AlienMod.location(handheldFolder + item.getId().getPath()));
	}

	/*
	 * Makes an item that has the "item/template_spawn_egg" model.
	 */
	protected ItemModelBuilder spawnEggItem(RegistryObject<? extends Item> item) {
		return withExistingParent(item.getId().getPath(), "item/template_spawn_egg");
	}

}
