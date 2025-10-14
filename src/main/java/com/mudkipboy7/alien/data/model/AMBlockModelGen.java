package com.mudkipboy7.alien.data.model;

import com.mudkipboy7.alien.AlienMod;
import com.mudkipboy7.alien.world.block.AMBlocks;
import com.mudkipboy7.alien.world.block.functional.machine.transport.WireBlock;
import com.mudkipboy7.alien.world.block.states.AMBlockStateProperties;
import com.mudkipboy7.alien.world.block.states.DeadPlantType;

import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CeilingHangingSignBlock;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraft.world.level.block.WallHangingSignBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.MultiPartBlockStateBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class AMBlockModelGen extends BlockStateProvider {

	static String blockFolder = "block/";
	static String plantBlockFolder = blockFolder + "plant/";
	static String doorBlockFolder = blockFolder + "door/";
	static String logBlockFolder = blockFolder + "log/";
	static String machineFolder = blockFolder + "machine/";

	static ResourceLocation gelustone = AlienMod.location(AlienMod.MODID, blockFolder + "gelustone"),

			cobbleGelustone = AlienMod.location(blockFolder + "cobble_gelustone"),

			lignumPlanks = AlienMod.location(blockFolder + "lignum_planks"),
			lignumLogSide = AlienMod.location(logBlockFolder + "lignum_log"),
			lignumLogTop = AlienMod.location(logBlockFolder + "lignum_log_top"),
			thinLignumLogTop = AlienMod.location(logBlockFolder + "thin_lignum_log_top");

	public AMBlockModelGen(PackOutput packOutput, ExistingFileHelper exFileHelper) {
		super(packOutput, AlienMod.MODID, exFileHelper);
	}

	@Override
	protected void registerStatesAndModels() {
		simpleBlock(AMBlocks.TEST_BLOCK.get());

		simpleBlock(AMBlocks.AMMONIA_LIQUID_BLOCK.get(),
				models().getBuilder(AMBlocks.AMMONIA_LIQUID_BLOCK.getId().getPath()).texture("particle",
						"alien:block/fluid/ammonia_liquid"));

		simpleBlock(AMBlocks.ALIEN_AIR.get());

		simpleBlock(AMBlocks.GELUSTONE.get());

		simpleBlock(AMBlocks.ALGUSSOIL.get(),
				models().withExistingParent(AMBlocks.ALGUSSOIL.getId().getPath(), "block/cube_all").texture("all",
						blockFolder + "aliendirt/" + AMBlocks.ALGUSSOIL.getId().getPath()));

		simpleBlock(AMBlocks.GRAMEN_BLOCK.get(),
				models().cubeBottomTop(AMBlocks.GRAMEN_BLOCK.getId().getPath(),
						AlienMod.location(
								blockFolder + "aliendirt/" + AMBlocks.GRAMEN_BLOCK.getId().getPath() + "_side"),
						AlienMod.location(blockFolder + "aliendirt/" + AMBlocks.ALGUSSOIL.getId().getPath()),
						AlienMod.location(blockFolder + "aliendirt/" + AMBlocks.GRAMEN_BLOCK.getId().getPath())));

		simpleBlock(AMBlocks.ALIEN_COBBLESTONE.get());
		simpleBlock(AMBlocks.GELUSTONE_COAL_ORE.get());
		simpleBlock(AMBlocks.LIGNUM_PLANKS.get());

		axisBlock(AMBlocks.LIGNUM_LOG.get(), lignumLogSide, lignumLogTop);

		simpleBlock(AMBlocks.LIGNUM_LEAVES.get(),
				models().withExistingParent(AMBlocks.LIGNUM_LEAVES.getId().getPath(), "block/leaves").texture("all",
						blockFolder + AMBlocks.LIGNUM_LEAVES.getId().getPath()));

		stairsBlock(AMBlocks.GLELUSTONE_STAIRS.get(), gelustone);
		stairsBlock(AMBlocks.COBBLE_GELUSTONE_STAIRS.get(), cobbleGelustone);
		stairsBlock(AMBlocks.LIGNUM_STAIRS.get(), lignumPlanks);

		slabBlock(AMBlocks.GELUSTONE_SLAB.get(), gelustone, gelustone);
		slabBlock(AMBlocks.COBBLE_GELUSTONE_SLAB.get(), cobbleGelustone, cobbleGelustone);
		slabBlock(AMBlocks.LIGNUM_SLAB.get(), lignumPlanks, lignumPlanks);

		simpleBlock(AMBlocks.GELUSTONE_BEDROCK.get());
		simpleBlock(AMBlocks.LIGNUM_CRAFTING_TABLE.get(), models().withExistingParent("lignum_crafting_table", "cube")
				.texture("down", lignumPlanks)
				.texture("east", AlienMod.location(blockFolder + "aliencraftingtable/lignum_crafting_table_side"))
				.texture("north", AlienMod.location(blockFolder + "aliencraftingtable/lignum_crafting_table_front"))
				.texture("particle", AlienMod.location(blockFolder + "aliencraftingtable/lignum_crafting_table_front"))
				.texture("south", AlienMod.location(blockFolder + "aliencraftingtable/lignum_crafting_table_side"))
				.texture("up", AlienMod.location(blockFolder + "aliencraftingtable/lignum_crafting_table_top"))
				.texture("west", AlienMod.location(blockFolder + "aliencraftingtable/lignum_crafting_table_front")));

		simpleBlock(AMBlocks.GELUSTONE_GOLD_ORE.get());

		crossBlock(AMBlocks.TALL_GRAMEN, AlienMod.location(plantBlockFolder + "tall_gramen"));
		simpleBlock(AMBlocks.ALIEN_PORTAL.get());
		crossBlock(AMBlocks.LIGNUM_SAPLING, AlienMod.location(plantBlockFolder + "lignum_sapling"));

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

		doubleCrossBlock(AMBlocks.DOUBLE_TALL_GRAMEN, AlienMod.location(plantBlockFolder + "double_tall_gramen_bottom"),
				AlienMod.location(plantBlockFolder + "double_tall_gramen_top"));

		doorBlock(AMBlocks.LIGNUM_DOOR.get(), AlienMod.location(doorBlockFolder + "lignum_door_bottom"),
				AlienMod.location(doorBlockFolder + "lignum_door_top"));

		fenceBlock(AMBlocks.LIGNUM_FENCE.get(), lignumPlanks);

		fenceGateBlock(AMBlocks.LIGNUM_FENCE_GATE.get(), lignumPlanks);

		trapdoorBlock(AMBlocks.LIGNUM_TRAPDOOR.get(), AlienMod.location(doorBlockFolder + "lignum_trapdoor"),
				false);

		buttonBlock(AMBlocks.LIGNUM_BUTTON.get(), lignumPlanks);

		pressurePlateBlock(AMBlocks.LIGNUM_PRESSURE_PLATE.get(), lignumPlanks);

		axisBlock(AMBlocks.LIGNUM_LOG_ALL_SIDES_SAME.get(), lignumLogSide, lignumLogSide);

		signBlock(AMBlocks.LIGNUM_SIGN.get(), AMBlocks.LIGNUM_WALL_SIGN.get(), lignumPlanks);
		hangingSignBlock(AMBlocks.LIGNUM_HANGING_SIGN, AMBlocks.LIGNUM_WALL_HANGING_SIGN, lignumPlanks);

		//axisBlock(AMBlocks.STRIPPED_ALIEN_LOG.get(), strippedAlienLogSide, strippedAlienLogTop);

		//axisBlock(AMBlocks.STRIPPED_ALIEN_LOG_ALL_SIDES_SAME.get(), strippedAlienLogSide, strippedAlienLogSide);

		simpleBlock(AMBlocks.AIR_PURIFIER.get(),
				models().cubeBottomTop(AMBlocks.AIR_PURIFIER.getId().getPath(),
						AlienMod.location(machineFolder + AMBlocks.AIR_PURIFIER.getId().getPath()),
						AlienMod.location(machineFolder + AMBlocks.AIR_PURIFIER.getId().getPath() + "_bottom"),
						AlienMod.location(machineFolder + AMBlocks.AIR_PURIFIER.getId().getPath() + "_top")));

		simpleBlock(AMBlocks.LIGNUM_CHEST.get(),
				models().getBuilder(AMBlocks.LIGNUM_CHEST.getId().getPath()).texture("particle", lignumPlanks));

		buttonBlock(AMBlocks.GELUSTONE_BUTTON.get(), gelustone);

		pressurePlateBlock(AMBlocks.GELUSTONE_PRESSURE_PLATE.get(), gelustone);

		wallBlock(AMBlocks.COBBLE_GELUSTONE_WALL.get(), cobbleGelustone);

		simpleBlock(AMBlocks.COAL_GENERATOR.get(), models().cubeAll(AMBlocks.COAL_GENERATOR.getId().getPath(),
				AlienMod.location(machineFolder + AMBlocks.COAL_GENERATOR.getId().getPath())));

		simpleBlock(AMBlocks.ENERGY_STORAGE.get(),
				models().cubeBottomTop(AMBlocks.ENERGY_STORAGE.getId().getPath(),
						AlienMod.location(machineFolder + AMBlocks.ENERGY_STORAGE.getId().getPath()),
						AlienMod.location(machineFolder + AMBlocks.ENERGY_STORAGE.getId().getPath() + "_bottom"),
						AlienMod.location(machineFolder + AMBlocks.ENERGY_STORAGE.getId().getPath() + "_top")));

		simpleBlock(AMBlocks.APSIUS_ORE.get());

		simpleBlock(AMBlocks.APSIUS_BLOCK.get());
		
		wireBlock(AMBlocks.COPPER_WIRE, AlienMod.location(machineFolder + AMBlocks.COPPER_WIRE.getId().getPath()), 4);

		directionalBlock(AMBlocks.LAZER_CREATOR.get(),
				models().cubeBottomTop(AMBlocks.LAZER_CREATOR.getId().getPath(),
						AlienMod.location(machineFolder + AMBlocks.LAZER_CREATOR.getId().getPath()),
						AlienMod.location(machineFolder + AMBlocks.LAZER_CREATOR.getId().getPath() + "_bottom"),
						AlienMod.location(machineFolder + AMBlocks.LAZER_CREATOR.getId().getPath() + "_top")));

		simpleBlock(AMBlocks.ENERGY_BLOCK.get(), models().cubeAll(AMBlocks.ENERGY_BLOCK.getId().getPath(),
				AlienMod.location(machineFolder + AMBlocks.ENERGY_BLOCK.getId().getPath())));

		simpleBlock(AMBlocks.THIN_LIGNUM_LOG.get(),
				models().withExistingParent(AMBlocks.THIN_LIGNUM_LOG.getId().getPath(),
						AlienMod.location(blockFolder + "thin_block")).texture("side", lignumLogSide).texture("top", thinLignumLogTop));
		
		simpleBlock(AMBlocks.GELUSTONE_IRON_ORE.get());
		
		simpleBlock(AMBlocks.REGOLITH.get());
		
		simpleBlock(AMBlocks.JOVIAN_PORTAL.get());
		simpleBlock(AMBlocks.JOVIAN_RETURN_PORTAL.get());
		simpleBlock(AMBlocks.JOVIAN_BOSS_SPAWNER.get());

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

	/**
	 * 
	 * @param block
	 * @param texturePath
	 * @param width       This value should ideally be an even number between 2 and
	 *                    16, but I guess it can be anything.
	 */
	public void wireBlock(RegistryObject<WireBlock> block, ResourceLocation texturePath, float width) {
		/*
		 * Creates the core model
		 */
		String sideName = block.getId().getPath() + "_side";
		String modelParent = blockFolder + "block";
		// Helps determine the size stuff
		float start = 8 - (width / 2.0F);
		float end = 8 + (width / 2.0F);

		BlockModelBuilder modelNormal = models().withExistingParent(block.getId().getPath(), modelParent)
				// Textures
				.texture("texture", texturePath).texture("particle", texturePath)
				// Shape
				.element().from(start, start, start).to(end, end, end)
				.allFaces((direction, faceBuilder) -> faceBuilder.texture("#texture").cullface(direction)).end();

		BlockModelBuilder modelSide = models().withExistingParent(sideName, modelParent)
				// Textures
				.texture("texture", texturePath).texture("particle", texturePath)
				// Shape
				.element().from(start, start, 0).to(end, end, start)
				.allFaces((direction, faceBuilder) -> faceBuilder.texture("#texture")).end();

		getMultipartBuilder(block.get())
				// The main thing
				.part().modelFile(modelNormal).addModel().end()
				// North
				.part().modelFile(modelSide).addModel().condition(WireBlock.NORTH, true).end()
				// East
				.part().modelFile(modelSide).uvLock(true).rotationY(90).addModel().condition(WireBlock.EAST, true).end()
				// South
				.part().modelFile(modelSide).uvLock(true).rotationY(180).addModel().condition(WireBlock.SOUTH, true)
				.end()
				// West
				.part().modelFile(modelSide).uvLock(true).rotationY(270).addModel().condition(WireBlock.WEST, true)
				.end()
				// West
				.part().modelFile(modelSide).uvLock(true).rotationY(270).addModel().condition(WireBlock.WEST, true)
				.end()
				// Up
				.part().modelFile(modelSide).uvLock(true).rotationX(270).addModel().condition(WireBlock.UP, true).end()
				// Down
				.part().modelFile(modelSide).uvLock(true).rotationX(90).addModel().condition(WireBlock.DOWN, true)
				.end();
	}

}