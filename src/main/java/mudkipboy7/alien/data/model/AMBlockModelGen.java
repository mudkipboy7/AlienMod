package mudkipboy7.alien.data.model;

import mudkipboy7.alien.AlienMod;
import mudkipboy7.alien.world.block.AMBlocks;
import mudkipboy7.alien.world.block.states.AMBlockStateProperties;
import mudkipboy7.alien.world.block.states.DeadPlantType;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CeilingHangingSignBlock;
import net.minecraft.world.level.block.WallHangingSignBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class AMBlockModelGen extends BlockStateProvider {

	static String blockFolder = "block/";
	static String plantBlockFolder = blockFolder + "plant/";
	static String doorBlockFolder = blockFolder + "door/";
	static String logBlockFolder = blockFolder + "log/";
	static String machineFolder = blockFolder + "machine/";

	static ResourceLocation alienStone = AlienMod.location(AlienMod.MODID, blockFolder + "gelustone"),

			alienCobbestone = AlienMod.location(blockFolder + "cobble_gelustone"),

			alienPlanks = AlienMod.location(blockFolder + "lignum_planks"),
			alienLogSide = AlienMod.location(logBlockFolder + "lignum_log"),
			alienLogTop = AlienMod.location(logBlockFolder + "lignum_log_top"),
			strippedAlienLogSide = AlienMod.location(logBlockFolder + "stripped_lignum_log"),
			strippedAlienLogTop = AlienMod.location(logBlockFolder + "stripped_lignum_log_top");

	public AMBlockModelGen(PackOutput packOutput, ExistingFileHelper exFileHelper) {
		super(packOutput, AlienMod.MODID, exFileHelper);
	}

	@Override
	protected void registerStatesAndModels() {
		simpleBlock(AMBlocks.TEST_BLOCK.get());

		simpleBlock(AMBlocks.AMMONIA_LIQUID_BLOCK.get(),
				models().getBuilder(AMBlocks.AMMONIA_LIQUID_BLOCK.getId().getPath()).texture("particle",
						"alien:block/fluid/ammonia_liquid"));

		simpleBlock(AMBlocks.ALIEN_STONE.get());

		simpleBlock(AMBlocks.ALIEN_DIRT.get(),
				models().withExistingParent(AMBlocks.ALIEN_DIRT.getId().getPath(), "block/cube_all").texture("all",
						blockFolder + "aliendirt/" + AMBlocks.ALIEN_DIRT.getId().getPath()));

		simpleBlock(AMBlocks.ALIEN_GRASS_BLOCK.get(),
				models().cubeBottomTop(AMBlocks.ALIEN_GRASS_BLOCK.getId().getPath(),
						AlienMod.location(
								blockFolder + "aliendirt/" + AMBlocks.ALIEN_GRASS_BLOCK.getId().getPath() + "_side"),
						AlienMod.location(blockFolder + "aliendirt/" + AMBlocks.ALIEN_DIRT.getId().getPath()),
						AlienMod.location(blockFolder + "aliendirt/" + AMBlocks.ALIEN_GRASS_BLOCK.getId().getPath())));

		simpleBlock(AMBlocks.ALIEN_COBBLESTONE.get());
		simpleBlock(AMBlocks.ALIEN_COAL_ORE.get());
		simpleBlock(AMBlocks.ALIEN_PLANKS.get());

		axisBlock(AMBlocks.ALIEN_LOG.get(), alienLogSide, alienLogTop);

		simpleBlock(AMBlocks.ALIEN_LEAVES.get(),
				models().withExistingParent(AMBlocks.ALIEN_LEAVES.getId().getPath(), "block/leaves").texture("all",
						blockFolder + AMBlocks.ALIEN_LEAVES.getId().getPath()));

		stairsBlock(AMBlocks.ALIEN_STONE_STAIRS.get(), alienStone);
		stairsBlock(AMBlocks.ALIEN_COBBLESTONE_STAIRS.get(), alienCobbestone);
		stairsBlock(AMBlocks.ALIEN_WOOD_STAIRS.get(), alienPlanks);

		slabBlock(AMBlocks.ALIEN_STONE_SLAB.get(), alienStone, alienStone);
		slabBlock(AMBlocks.ALIEN_COBBLESTONE_SLAB.get(), alienCobbestone, alienCobbestone);
		slabBlock(AMBlocks.ALIEN_WOOD_SLAB.get(), alienPlanks, alienPlanks);

		simpleBlock(AMBlocks.ALIEN_BEDROCK.get());
		simpleBlock(AMBlocks.ALIEN_CRAFTING_TABLE.get(), models().withExistingParent("lignum_crafting_table", "cube")
				.texture("down", alienPlanks)
				.texture("east", AlienMod.location(blockFolder + "aliencraftingtable/lignum_crafting_table_side"))
				.texture("north", AlienMod.location(blockFolder + "aliencraftingtable/lignum_crafting_table_front"))
				.texture("particle", AlienMod.location(blockFolder + "aliencraftingtable/lignum_crafting_table_front"))
				.texture("south", AlienMod.location(blockFolder + "aliencraftingtable/lignum_crafting_table_side"))
				.texture("up", AlienMod.location(blockFolder + "aliencraftingtable/lignum_crafting_table_top"))
				.texture("west", AlienMod.location(blockFolder + "aliencraftingtable/lignum_crafting_table_front")));

		simpleBlock(AMBlocks.ALIEN_GOLD_ORE.get());

		crossBlock(AMBlocks.ALIEN_GRASS, AlienMod.location(plantBlockFolder + "gramen"));
		simpleBlock(AMBlocks.ALIEN_PORTAL.get());
		crossBlock(AMBlocks.ALIEN_SAPLING, AlienMod.location(plantBlockFolder + "lignum_sapling"));

		getVariantBuilder(AMBlocks.DEAD_PLANT.get()).partialState()
				.with(AMBlockStateProperties.DEAD_PLANT_TYPE, DeadPlantType.SAPLING)
				.addModels(new ConfiguredModel(models().cross(AMBlocks.DEAD_PLANT.getId().getPath() + "_sapling",
						AlienMod.location(plantBlockFolder + "dead_plant_sapling"))))
				.partialState().with(AMBlockStateProperties.DEAD_PLANT_TYPE, DeadPlantType.FLOWER)
				.addModels(new ConfiguredModel(models().cross(AMBlocks.DEAD_PLANT.getId().getPath() + "_flower",
						AlienMod.location(plantBlockFolder + "dead_plant_flower"))))
				.partialState().with(AMBlockStateProperties.DEAD_PLANT_TYPE, DeadPlantType.GRASS)
				.addModels(new ConfiguredModel(models().cross(AMBlocks.DEAD_PLANT.getId().getPath() + "_grass",
						AlienMod.location(plantBlockFolder + "dead_plant_grass"))));

		doubleCrossBlock(AMBlocks.ALIEN_DOUBLE_GRASS, AlienMod.location(plantBlockFolder + "tall_gramen_bottom"),
				AlienMod.location(plantBlockFolder + "tall_gramen_top"));

		doorBlock(AMBlocks.ALIEN_WOOD_DOOR.get(), AlienMod.location(doorBlockFolder + "lignum_door_bottom"),
				AlienMod.location(doorBlockFolder + "lignum_door_top"));

		fenceBlock(AMBlocks.ALIEN_WOOD_FENCE.get(), alienPlanks);

		fenceGateBlock(AMBlocks.ALIEN_WOOD_FENCE_GATE.get(), alienPlanks);

		trapdoorBlock(AMBlocks.ALIEN_WOOD_TRAPDOOR.get(), AlienMod.location(doorBlockFolder + "lignum_trapdoor"),
				false);

		buttonBlock(AMBlocks.ALIEN_WOOD_BUTTON.get(), alienPlanks);

		pressurePlateBlock(AMBlocks.ALIEN_WOOD_PRESSURE_PLATE.get(), alienPlanks);

		axisBlock(AMBlocks.ALIEN_LOG_ALL_SIDES_SAME.get(), alienLogSide, alienLogSide);

		signBlock(AMBlocks.ALIEN_WOOD_SIGN.get(), AMBlocks.ALIEN_WOOD_WALL_SIGN.get(), alienPlanks);
		hangingSignBlock(AMBlocks.ALIEN_WOOD_HANGING_SIGN, AMBlocks.ALIEN_WOOD_WALL_HANGING_SIGN, alienPlanks);

		axisBlock(AMBlocks.STRIPPED_ALIEN_LOG.get(), strippedAlienLogSide, strippedAlienLogTop);

		axisBlock(AMBlocks.STRIPPED_ALIEN_LOG_ALL_SIDES_SAME.get(), strippedAlienLogSide, strippedAlienLogSide);

		simpleBlock(AMBlocks.AIR_PURIFIER.get(),
				models().cubeBottomTop(AMBlocks.AIR_PURIFIER.getId().getPath(),
						AlienMod.location(machineFolder + AMBlocks.AIR_PURIFIER.getId().getPath()),
						AlienMod.location(machineFolder + AMBlocks.AIR_PURIFIER.getId().getPath() + "_bottom"),
						AlienMod.location(machineFolder + AMBlocks.AIR_PURIFIER.getId().getPath() + "_top")));

		simpleBlock(AMBlocks.ALIEN_WOOD_CHEST.get(),
				models().getBuilder(AMBlocks.ALIEN_WOOD_CHEST.getId().getPath()).texture("particle", alienPlanks));

		buttonBlock(AMBlocks.ALIEN_STONE_BUTTON.get(), alienStone);

		pressurePlateBlock(AMBlocks.ALIEN_STONE_PRESSURE_PLATE.get(), alienStone);

		wallBlock(AMBlocks.ALIEN_COBBLESTONE_WALL.get(), alienCobbestone);

		simpleBlock(AMBlocks.COAL_GENERATOR.get(), models().cubeAll(AMBlocks.COAL_GENERATOR.getId().getPath(),
				AlienMod.location(machineFolder + AMBlocks.COAL_GENERATOR.getId().getPath())));

		simpleBlock(AMBlocks.ENERGY_STORAGE.get(),
				models().cubeBottomTop(AMBlocks.ENERGY_STORAGE.getId().getPath(),
						AlienMod.location(machineFolder + AMBlocks.ENERGY_STORAGE.getId().getPath()),
						AlienMod.location(machineFolder + AMBlocks.ENERGY_STORAGE.getId().getPath() + "_bottom"),
						AlienMod.location(machineFolder + AMBlocks.ENERGY_STORAGE.getId().getPath() + "_top")));

		simpleBlock(AMBlocks.ALIEN_METAL_ORE.get());

		simpleBlock(AMBlocks.ALIEN_METAL_BLOCK.get());

		simpleBlock(AMBlocks.RAW_ALIEN_METAL_BLOCK.get());
		
		directionalBlock(AMBlocks.LAZER_CREATOR.get(),
				models().cubeBottomTop(AMBlocks.LAZER_CREATOR.getId().getPath(),
						AlienMod.location(machineFolder + AMBlocks.LAZER_CREATOR.getId().getPath()),
						AlienMod.location(machineFolder + AMBlocks.LAZER_CREATOR.getId().getPath() + "_bottom"),
						AlienMod.location(machineFolder + AMBlocks.LAZER_CREATOR.getId().getPath() + "_top")));
		
		simpleBlock(AMBlocks.LAZER_EXTENDER.get(), models().cubeAll(AMBlocks.LAZER_EXTENDER.getId().getPath(),
				AlienMod.location(machineFolder + AMBlocks.LAZER_EXTENDER.getId().getPath())));

	}

	/*
	 * Methods
	 */
	protected void crossBlock(RegistryObject<? extends Block> block, ResourceLocation texturePath) {
		BlockModelBuilder model = models().cross(block.getId().getPath(), texturePath);
		simpleBlock(block.get(), model);
	}

	protected <T> void doubleCrossBlock(RegistryObject<? extends Block> block, ResourceLocation texturePathBottom,
			ResourceLocation texturePathTop) {
		BlockModelBuilder modelBottom = models().cross(block.getId().getPath() + "_bottom", texturePathBottom);
		BlockModelBuilder modelTop = models().cross(block.getId().getPath() + "_top", texturePathTop);
		getVariantBuilder(block.get()).partialState()
				.with(BlockStateProperties.DOUBLE_BLOCK_HALF, DoubleBlockHalf.LOWER)
				.addModels(new ConfiguredModel(modelBottom)).partialState()
				.with(BlockStateProperties.DOUBLE_BLOCK_HALF, DoubleBlockHalf.UPPER)
				.addModels(new ConfiguredModel(modelTop));
	}

	public void hangingSignBlock(RegistryObject<? extends CeilingHangingSignBlock> celingSignBlock,
			RegistryObject<? extends WallHangingSignBlock> wallSignBlock, ResourceLocation texturePathParticle) {
		BlockModelBuilder model = models().sign(celingSignBlock.getId().getPath(), texturePathParticle);
		simpleBlock(celingSignBlock.get(), model);
		simpleBlock(wallSignBlock.get(), model);
	}

}