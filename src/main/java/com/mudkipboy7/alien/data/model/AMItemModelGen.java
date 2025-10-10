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
		blockItem(AMItems.GELUSTONE);
		blockItem(AMItems.ALGUSSOIL);
		blockItem(AMItems.GRAMEN_BLOCK);
		blockItem(AMItems.COBBLE_GELUSTONE);
		blockItem(AMItems.GELUSTONE_COAL_ORE);
		blockItem(AMItems.LIGNUM_PLANKS);
		blockItem(AMItems.LIGNUM_LOG);
		blockItem(AMItems.LIGNUM_LEAVES);
		blockItem(AMItems.GLELUSTONE_STAIRS);
		blockItem(AMItems.COBBLE_GELUSTONE_STAIRS);
		blockItem(AMItems.LIGNUM_STAIRS);
		blockItem(AMItems.GELUSTONE_SLAB);
		blockItem(AMItems.COBBLE_GELUSTONE_SLAB);
		blockItem(AMItems.LIGNUM_SLAB);
		blockItem(AMItems.GELUSTONE_BEDROCK);
		blockItem(AMItems.LIGNUM_CRAFTING_TABLE);
		blockItem(AMItems.GELUSTONE_GOLD_ORE);
		genItem(AMItems.ALIEN_PORTAL, AlienMod.location(blockFolder + AMItems.ALIEN_PORTAL.getId().getPath()));
		genItem(AMItems.TALL_GRAMEN, AlienMod.location(plantBlockFolder + "gramen"));
		genItem(AMItems.LIGNUM_SAPLING, AlienMod.location(plantBlockFolder + "lignum_sapling"));
		genItem(AMItems.DEAD_PLANT, AlienMod.location(plantBlockFolder + "dead_plant_sapling"));
		genItem(AMItems.DOUBLE_TALL_GRAMEN, AlienMod.location(plantBlockFolder + "tall_gramen_top"));
		genItem(AMItems.LIGNUM_DOOR);
		withExistingParent(AMItems.LIGNUM_FENCE.getId().getPath(), "block/fence_inventory").texture("texture",
				AMBlockModelGen.lignumPlanks);
		blockItem(AMItems.LIGNUM_FENCE_GATE);
		blockItem(AMItems.LIGNUM_TRAPDOOR,
				AlienMod.location(blockFolder + AMItems.LIGNUM_TRAPDOOR.getId().getPath() + "_bottom"));
		withExistingParent(AMItems.LIGNUM_BUTTON.getId().getPath(), "block/button_inventory").texture("texture",
				AMBlockModelGen.lignumPlanks);
		blockItem(AMItems.LIGNUM_PRESSURE_PLATE);
		blockItem(AMItems.LIGNUM_ALL_SIDES_SAME);
		genItem(AMItems.LIGNUM_SIGN);
		genItem(AMItems.LIGNUM_HANGING_SIGN);
		//blockItem(AMItems.STRIPPED_ALIEN_LOG);
		//blockItem(AMItems.STRIPPED_ALIEN_LOG_ALL_SIDES_SAME);
		blockItem(AMItems.AIR_PURIFIER);
		withExistingParent(AMItems.LIGNUM_CHEST.getId().getPath(), "minecraft:item/chest").texture("particle",
				AMBlockModelGen.lignumPlanks);

		withExistingParent(AMItems.GELUSTONE_BUTTON.getId().getPath(), "block/button_inventory").texture("texture",
				AMBlockModelGen.gelustone);
		blockItem(AMItems.GELUSTONE_PRESSURE_PLATE);

		withExistingParent(AMItems.COBBLE_GELUSTONE_WALL.getId().getPath(), "block/wall_inventory").texture("wall",
				AMBlockModelGen.cobbleGelustone);
		blockItem(AMItems.COAL_GENERATOR);
		blockItem(AMItems.ENERGY_STORAGE);
		blockItem(AMItems.APSIUS_ORE);
		blockItem(AMItems.APSIUS_BLOCK);
		genItem(AMItems.COPPER_WIRE);
		blockItem(AMItems.LAZER_CREATOR);
		blockItem(AMItems.ENERGY_BLOCK);

		withExistingParent(AMItems.THIN_LIGNUM_LOG.getId().getPath(),
				AlienMod.location(blockFolder + "thin_block_inventory")).texture("side", AMBlockModelGen.lignumLogSide)
				.texture("top", AMBlockModelGen.thinLignumLogTop);
		blockItem(AMItems.GELUSTONE_IRON_ORE);

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
		handheldItem(AMItems.LIGNUM_SWORD);
		handheldItem(AMItems.LIGNUM_PICKAXE);
		handheldItem(AMItems.LIGNUM_AXE);
		handheldItem(AMItems.LIGNUM_SHOVEL);
		handheldItem(AMItems.LIGNUM_HOE);
		handheldItem(AMItems.LIGNUM_SWORD);
		handheldItem(AMItems.GELUSTONE_SWORD);
		handheldItem(AMItems.GELUSTONE_PICKAXE);
		handheldItem(AMItems.GELUSTONE_AXE);
		handheldItem(AMItems.GELUSTONE_SHOVEL);
		handheldItem(AMItems.GELUSTONE_HOE);
		handheldItem(AMItems.GELUSTONE_SWORD);
		genItem(AMItems.ANTI_GRAVITY);
		genItem(AMItems.ALIENDIM_CREATIVE_TELEPORTER);
		genItem(AMItems.BATTERY);
		genItem(AMItems.INT_LIMIT_BATTERY);
		genItem(AMItems.APSIUS_GEM);
		genItem(AMItems.APSIUS_HELMET);
		genItem(AMItems.APSIUS_CHESTPLATE);
		genItem(AMItems.APSIUS_LEGGINGS);
		genItem(AMItems.APSIUS_BOOTS);
		handheldItem(AMItems.APSIUS_SWORD);
		handheldItem(AMItems.APSIUS_PICKAXE);
		handheldItem(AMItems.APSIUS_AXE);
		handheldItem(AMItems.APSIUS_SHOVEL);
		handheldItem(AMItems.APSIUS_HOE);
		handheldItem(AMItems.APSIUS_SWORD);
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
