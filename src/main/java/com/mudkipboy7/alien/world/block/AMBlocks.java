package com.mudkipboy7.alien.world.block;

import static com.mudkipboy7.alien.AMRegistry.BLOCKS;

import com.mudkipboy7.alien.world.block.blockentity.AMBlockEntities;
import com.mudkipboy7.alien.world.block.flora.AlienDoubleTallGrassBlock;
import com.mudkipboy7.alien.world.block.flora.AlienGrassBlock;
import com.mudkipboy7.alien.world.block.flora.AlienLeavesBlock;
import com.mudkipboy7.alien.world.block.flora.AlienSaplingBlock;
import com.mudkipboy7.alien.world.block.flora.AlienTallGrassBlock;
import com.mudkipboy7.alien.world.block.flora.DeadPlantBlock;
import com.mudkipboy7.alien.world.block.flora.ThinAlienLog;
import com.mudkipboy7.alien.world.block.fluid.AmmoniaLiquidBlock;
import com.mudkipboy7.alien.world.block.functional.AlienChestBlock;
import com.mudkipboy7.alien.world.block.functional.AlienCraftingTableBlock;
import com.mudkipboy7.alien.world.block.functional.AlienPortalBlock;
import com.mudkipboy7.alien.world.block.functional.JovianBossSpawnerBlock;
import com.mudkipboy7.alien.world.block.functional.JovianGoToPortalBlock;
import com.mudkipboy7.alien.world.block.functional.JovianReturnPortalBlock;
import com.mudkipboy7.alien.world.block.functional.machine.CoalGeneratorBlock;
import com.mudkipboy7.alien.world.block.functional.machine.EnergyBlock;
import com.mudkipboy7.alien.world.block.functional.machine.EnergyStorageBlock;
import com.mudkipboy7.alien.world.block.functional.machine.HazardRemovalMachineBlock;
import com.mudkipboy7.alien.world.block.functional.machine.transport.LazerCreatorBlock;
import com.mudkipboy7.alien.world.block.functional.machine.transport.WireBlock;
import com.mudkipboy7.alien.world.block.functional.sign.AlienCeilingHangingSignBlock;
import com.mudkipboy7.alien.world.block.functional.sign.AlienStandingSignBlock;
import com.mudkipboy7.alien.world.block.functional.sign.AlienWallHangingSignBlock;
import com.mudkipboy7.alien.world.block.functional.sign.AlienWallSignBlock;
import com.mudkipboy7.alien.world.block.misc.AlienAirBlock;
import com.mudkipboy7.alien.world.block.misc.TestBlock;
import com.mudkipboy7.alien.world.worldgen.worldobject.flora.AMTreeGrowers;

import net.minecraft.core.BlockPos;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.CraftingTableBlock;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.GlassBlock;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.registries.RegistryObject;;

@SuppressWarnings("deprecation")
public final class AMBlocks {

	/*
	 * Register Blocks
	 */

	// Block for testing mod functions
	public static final RegistryObject<Block> TEST_BLOCK = BLOCKS.register("test_block",
			() -> new TestBlock(BlockBehaviour.Properties.of().strength(1F, 6.0F)));

	// Block for the ammonia liquid
	public static final RegistryObject<AmmoniaLiquidBlock> AMMONIA_LIQUID_BLOCK = BLOCKS.register("ammonia_liquid",
			() -> new AmmoniaLiquidBlock(AMFluids.AMMONIA_LIQUID));

	public static final RegistryObject<Block> ALIEN_AIR = BLOCKS.register("alien_air", () -> new AlienAirBlock(
			BlockBehaviour.Properties.of().noCollission().noOcclusion().noLootTable().strength(-1.0F, 3600000.0F)));

	// Stone for the Alien Dimension
	public static final RegistryObject<Block> GELUSTONE = BLOCKS.register("gelustone",
			() -> new Block(BlockBehaviour.Properties.of().strength(1.5F, 6.0F).sound(SoundType.NETHERRACK)
					.requiresCorrectToolForDrops()));

	// Dirt for the Alien Dimension
	public static final RegistryObject<Block> ALGUSSOIL = BLOCKS.register("algussoil",
			() -> new Block(BlockBehaviour.Properties.of().strength(0.5F, 6.0F).sound(SoundType.MUD)));

	// Grass for the Alien Dimension
	public static final RegistryObject<Block> GRAMEN_BLOCK = BLOCKS.register("gramen_block", () -> new AlienGrassBlock(
			BlockBehaviour.Properties.of().strength(0.5F, 6.0F).sound(SoundType.WET_GRASS).randomTicks()));

	// Cobblestone for the Alien Dimension
	public static final RegistryObject<Block> ALIEN_COBBLESTONE = BLOCKS.register("cobble_gelustone",
			() -> new Block(BlockBehaviour.Properties.of().strength(1.5F, 6.0F).sound(SoundType.NETHERRACK)
					.requiresCorrectToolForDrops()));

	// Coal ore for the Alien Dimension
	public static final RegistryObject<Block> GELUSTONE_COAL_ORE = BLOCKS
			.register("gelustone_coal_ore",
					() -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.COAL_ORE)
							.mapColor(MapColor.DEEPSLATE).strength(3.0F, 3.0F).sound(SoundType.NETHERRACK),
							UniformInt.of(0, 2)));

	// Wooden planks for the Alien Dimension
	public static final RegistryObject<Block> LIGNUM_PLANKS = BLOCKS.register("lignum_planks",
			() -> new Block(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).strength(2.0F)
					.sound(SoundType.WOOD).ignitedByLava()));

	// Wooden logs for the Alien Dimension
	public static final RegistryObject<RotatedPillarBlock> LIGNUM_LOG = simpleLogBlock("lignum_log");

	// Leaves for the Alien Dimension
	public static final RegistryObject<AlienLeavesBlock> LIGNUM_LEAVES = BLOCKS.register("lignum_leaves",
			() -> new AlienLeavesBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS)
					.strength(2.0F).sound(SoundType.GRASS).strength(0.2F).ignitedByLava().noOcclusion()
					.isSuffocating(AMBlocks::never).isViewBlocking(AMBlocks::never)
					.pushReaction(PushReaction.DESTROY)));

	// Stairs for the alien stone
	public static final RegistryObject<StairBlock> GLELUSTONE_STAIRS = makeSimpleStairBlock("gelustone_stairs",
			GELUSTONE);

	// Stairs for the alien cobblestone
	public static final RegistryObject<StairBlock> COBBLE_GELUSTONE_STAIRS = makeSimpleStairBlock(
			"cobble_gelustone_stairs", ALIEN_COBBLESTONE);

	// Stairs for the alien planks
	public static final RegistryObject<StairBlock> LIGNUM_STAIRS = makeSimpleStairBlock("lignum_stairs", LIGNUM_PLANKS);

	// Slabs for the alien stone
	public static final RegistryObject<SlabBlock> GELUSTONE_SLAB = simpleSlabBlock("gelustone_slab", GELUSTONE);

	// Slabs for the alien cobblestone
	public static final RegistryObject<SlabBlock> COBBLE_GELUSTONE_SLAB = simpleSlabBlock("cobble_gelustone_slab",
			ALIEN_COBBLESTONE);

	// Slabs for the alien planks
	public static final RegistryObject<SlabBlock> LIGNUM_SLAB = simpleSlabBlock("lignum_slab", LIGNUM_PLANKS);

	// Alien bedrock
	public static final RegistryObject<Block> GELUSTONE_BEDROCK = BLOCKS.register("gelustone_bedrock",
			() -> new Block(Properties.copy(Blocks.BEDROCK)));

	// Alien wood crafting table
	public static final RegistryObject<CraftingTableBlock> LIGNUM_CRAFTING_TABLE = BLOCKS.register(
			"lignum_crafting_table", () -> new AlienCraftingTableBlock(Properties.copy(Blocks.CRAFTING_TABLE)));

	// Gold ore for the Alien Dimension
	public static final RegistryObject<Block> GELUSTONE_GOLD_ORE = BLOCKS
			.register("gelustone_gold_ore",
					() -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.GOLD_ORE)
							.mapColor(MapColor.STONE).strength(3.0F, 3.0F).sound(SoundType.NETHERRACK),
							ConstantInt.of(0)));

	// Tall grass for the Alien Dimension
	public static final RegistryObject<Block> TALL_GRAMEN = BLOCKS.register("tall_gramen",
			() -> new AlienTallGrassBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).replaceable()
					.noCollission().instabreak().sound(SoundType.WET_GRASS).offsetType(BlockBehaviour.OffsetType.XZ)
					.ignitedByLava().pushReaction(PushReaction.DESTROY)));

	// Block for testing mod functions
	public static final RegistryObject<Block> ALIEN_PORTAL = BLOCKS.register("alien_portal", () -> new AlienPortalBlock(
			BlockBehaviour.Properties.of().strength(-1.0F, 3600000.0F).noLootTable().isValidSpawn(AMBlocks::never)));

	// Alien sapling
	public static final RegistryObject<Block> LIGNUM_SAPLING = BLOCKS.register("lignum_sapling",
			() -> new AlienSaplingBlock(AMTreeGrowers.basicAlienTreeGrower,
					BlockBehaviour.Properties.of().noCollission().sound(SoundType.GRASS)));

	// Dead Plant
	public static final RegistryObject<Block> DEAD_PLANT = BLOCKS.register("dead_plant",
			() -> new DeadPlantBlock(BlockBehaviour.Properties.of().sound(SoundType.GRASS).noLootTable().noCollission()
					.pushReaction(PushReaction.DESTROY)));

	// Tall grass for the Alien Dimension
	public static final RegistryObject<Block> DOUBLE_TALL_GRAMEN = BLOCKS.register("double_tall_gramen",
			() -> new AlienDoubleTallGrassBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).replaceable()
					.noCollission().instabreak().sound(SoundType.WET_GRASS).offsetType(BlockBehaviour.OffsetType.XZ)
					.ignitedByLava().pushReaction(PushReaction.DESTROY)));

	// Alien wood door
	public static final RegistryObject<DoorBlock> LIGNUM_DOOR = BLOCKS.register("lignum_door",
			() -> new DoorBlock(BlockBehaviour.Properties.of().sound(SoundType.WOOD)
					.mapColor(LIGNUM_PLANKS.get().defaultMapColor()).instrument(NoteBlockInstrument.BASS).strength(3.0F)
					.noOcclusion().ignitedByLava().pushReaction(PushReaction.DESTROY), AMBlockSetTypes.LIGNUM));

	// Alien wood fence
	public static final RegistryObject<FenceBlock> LIGNUM_FENCE = BLOCKS.register("lignum_fence",
			() -> new FenceBlock(BlockBehaviour.Properties.of().mapColor(LIGNUM_PLANKS.get().defaultMapColor())
					.forceSolidOn().instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).sound(SoundType.WOOD)
					.ignitedByLava()));

	// Alien wood fence gate
	public static final RegistryObject<FenceGateBlock> LIGNUM_FENCE_GATE = BLOCKS.register("lignum_fence_gate",
			() -> new FenceGateBlock(
					BlockBehaviour.Properties.of().sound(SoundType.WOOD).mapColor(LIGNUM_PLANKS.get().defaultMapColor())
							.forceSolidOn().instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).ignitedByLava(),
					AMBlockSetTypes.LIGNUM_WOOD_TYPE));

	// Alien wood trapdoor
	public static final RegistryObject<TrapDoorBlock> LIGNUM_TRAPDOOR = BLOCKS.register("lignum_trapdoor",
			() -> new TrapDoorBlock(
					BlockBehaviour.Properties.of().mapColor(LIGNUM_PLANKS.get().defaultMapColor())
							.instrument(NoteBlockInstrument.BASS).strength(3.0F).noOcclusion()
							.isValidSpawn(AMBlocks::never).ignitedByLava().sound(SoundType.WOOD),
					AMBlockSetTypes.LIGNUM));

	// Alien wood button
	public static final RegistryObject<ButtonBlock> LIGNUM_BUTTON = BLOCKS
			.register("lignum_button",
					() -> new ButtonBlock(
							BlockBehaviour.Properties.of().noCollission().strength(0.5F)
									.pushReaction(PushReaction.DESTROY).sound(SoundType.WOOD),
							AMBlockSetTypes.LIGNUM, 30, true));

	// Alien wood presure plate
	public static final RegistryObject<PressurePlateBlock> LIGNUM_PRESSURE_PLATE = BLOCKS.register(
			"lignum_pressure_plate",
			() -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,
					BlockBehaviour.Properties.of().mapColor(LIGNUM_PLANKS.get().defaultMapColor()).forceSolidOn()
							.instrument(NoteBlockInstrument.BASS).noCollission().strength(0.5F).ignitedByLava()
							.pushReaction(PushReaction.DESTROY).sound(SoundType.WOOD),
					AMBlockSetTypes.LIGNUM));

	// Log with side texture on all sides for the alien dim
	public static final RegistryObject<RotatedPillarBlock> LIGNUM_LOG_ALL_SIDES_SAME = BLOCKS
			.register("lignum_all_sides_log", () -> new RotatedPillarBlock(Properties.copy(LIGNUM_LOG.get())));

	// Alien wood standing sign
	public static final RegistryObject<AlienStandingSignBlock> LIGNUM_SIGN = BLOCKS.register("lignum_sign",
			() -> new AlienStandingSignBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).forceSolidOn()
					.instrument(NoteBlockInstrument.BASS).noCollission().strength(1.0F).ignitedByLava()
					.sound(SoundType.WOOD), AMBlockSetTypes.LIGNUM_WOOD_TYPE));

	// Alien wood wall sign
	public static final RegistryObject<AlienWallSignBlock> LIGNUM_WALL_SIGN = BLOCKS.register("lignum_wall_sign",
			() -> new AlienWallSignBlock(
					BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).forceSolidOn()
							.instrument(NoteBlockInstrument.BASS).noCollission().strength(1.0F)
							.dropsLike(LIGNUM_SIGN.get()).ignitedByLava().sound(SoundType.WOOD),
					AMBlockSetTypes.LIGNUM_WOOD_TYPE));

	// Alien wood ceiling hanging sign
	public static final RegistryObject<AlienCeilingHangingSignBlock> LIGNUM_HANGING_SIGN = BLOCKS.register(
			"lignum_hanging_sign",
			() -> new AlienCeilingHangingSignBlock(BlockBehaviour.Properties.of()
					.mapColor(LIGNUM_LOG.get().defaultMapColor()).forceSolidOn().instrument(NoteBlockInstrument.BASS)
					.noCollission().strength(1.0F).ignitedByLava().sound(SoundType.WOOD),
					AMBlockSetTypes.LIGNUM_WOOD_TYPE));

	// Alien wood wall hanging sign
	public static final RegistryObject<AlienWallHangingSignBlock> LIGNUM_WALL_HANGING_SIGN = BLOCKS.register(
			"lignum_wall_hanging_sign",
			() -> new AlienWallHangingSignBlock(
					BlockBehaviour.Properties.of().mapColor(LIGNUM_LOG.get().defaultMapColor()).forceSolidOn()
							.instrument(NoteBlockInstrument.BASS).noCollission().strength(1.0F).ignitedByLava()
							.dropsLike(LIGNUM_HANGING_SIGN.get()).sound(SoundType.WOOD),
					AMBlockSetTypes.LIGNUM_WOOD_TYPE));

	// Stripped alien log
	// public static final RegistryObject<RotatedPillarBlock> STRIPPED_ALIEN_LOG =
	// simpleLogBlock("stripped_lignum_log");

	// Stripped log log with side texture on all sides for the alien dim
	// public static final RegistryObject<RotatedPillarBlock>
	// STRIPPED_ALIEN_LOG_ALL_SIDES_SAME = simpleLogBlock(
	// "stripped_lignum_all_sides_log");

	// Protects the player from hazards
	public static final RegistryObject<HazardRemovalMachineBlock> AIR_PURIFIER = BLOCKS.register("air_purifier",
			() -> new HazardRemovalMachineBlock(
					BlockBehaviour.Properties.of().strength(1F, 6.0F).sound(SoundType.METAL)));

	// Alien wood chest
	public static final RegistryObject<AlienChestBlock> LIGNUM_CHEST = BLOCKS.register("lignum_chest",
			() -> new AlienChestBlock(Properties.copy(Blocks.CHEST), () -> {
				return AMBlockEntities.LIGNUM_CHEST.get();
			}));

	// Alien stone button
	public static final RegistryObject<ButtonBlock> GELUSTONE_BUTTON = BLOCKS.register("gelustone_button",
			() -> new ButtonBlock(BlockBehaviour.Properties.of().noCollission().strength(0.5F)
					.pushReaction(PushReaction.DESTROY).sound(SoundType.NETHERRACK), AMBlockSetTypes.GELUSTONE, 20,
					false));

	// Alien stone pressure plate
	public static final RegistryObject<PressurePlateBlock> GELUSTONE_PRESSURE_PLATE = BLOCKS.register(
			"gelustone_pressure_plate",
			() -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS,
					BlockBehaviour.Properties.of().mapColor(MapColor.STONE).forceSolidOn()
							.instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().noCollission()
							.strength(0.5F).pushReaction(PushReaction.DESTROY).sound(SoundType.NETHERRACK),
					AMBlockSetTypes.GELUSTONE));

	// Alien cobblestone wall
	public static final RegistryObject<WallBlock> COBBLE_GELUSTONE_WALL = BLOCKS.register("cobble_gelustone_wall",
			() -> new WallBlock(BlockBehaviour.Properties.copy(ALIEN_COBBLESTONE.get()).forceSolidOn()));

	// Makes energy from coal
	public static final RegistryObject<CoalGeneratorBlock> COAL_GENERATOR = BLOCKS.register("coal_generator",
			() -> new CoalGeneratorBlock(BlockBehaviour.Properties.of().strength(1F, 6.0F).sound(SoundType.METAL)));

	// Stores Energy
	public static final RegistryObject<EnergyStorageBlock> ENERGY_STORAGE = BLOCKS.register("energy_storage_block",
			() -> new EnergyStorageBlock(BlockBehaviour.Properties.of().strength(1F, 6.0F).sound(SoundType.METAL)));

	// Gem ore for the Alien Dimension
	public static final RegistryObject<Block> APSIUS_ORE = BLOCKS.register("apsius_ore",
			() -> new DropExperienceBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE)
					.instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3.0F, 3.0F)));

	// Gemfor the Alien Dimension
	public static final RegistryObject<Block> APSIUS_BLOCK = BLOCKS.register("apsius_block",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.METAL)
					.instrument(NoteBlockInstrument.IRON_XYLOPHONE).requiresCorrectToolForDrops().strength(5.0F, 6.0F)
					.sound(SoundType.METAL)));

	@Deprecated
	// Copper wire
	public static final RegistryObject<WireBlock> COPPER_WIRE = BLOCKS.register("copper_wire",
			() -> new WireBlock(BlockBehaviour.Properties.of().sound(SoundType.METAL)));

	// Lazer wire
	public static final RegistryObject<Block> LAZER_CREATOR = BLOCKS.register("lazer_creator",
			() -> new LazerCreatorBlock(BlockBehaviour.Properties.of()));

	// Infinitely Dispenses energy
	public static final RegistryObject<EnergyBlock> ENERGY_BLOCK = BLOCKS.register("energy_block",
			() -> new EnergyBlock(BlockBehaviour.Properties.of().strength(1F, 6.0F).sound(SoundType.AMETHYST)));

	// Thin Log
	public static final RegistryObject<ThinAlienLog> THIN_LIGNUM_LOG = BLOCKS.register("thin_lignum_log",
			() -> new ThinAlienLog(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).strength(2.0F)
					.sound(SoundType.WOOD).ignitedByLava().noOcclusion()));

	/*
	 * public static final RegistryObject<SnowLayerBlock> AMMONIA_SNOW_LAYER =
	 * BLOCKS.register("ammonia_snow", () -> new
	 * SnowLayerBlock(BlockBehaviour.Properties.of().mapColor(MapColor.SNOW).
	 * replaceable()
	 * .forceSolidOff().randomTicks().strength(0.1F).requiresCorrectToolForDrops().
	 * sound(SoundType.SNOW) .isViewBlocking((p_187417_, p_187418_, p_187419_) -> {
	 * return p_187417_.getValue(SnowLayerBlock.LAYERS) >= 8;
	 * }).pushReaction(PushReaction.DESTROY)));
	 */

	// Dirt for the Alien Dimension
	public static final RegistryObject<Block> REGOLITH = BLOCKS.register("regolith",
			() -> new Block(BlockBehaviour.Properties.of().strength(0.7F, 6.0F).sound(SoundType.GRAVEL)));

	// Iron ore for alien dim
	public static final RegistryObject<Block> GELUSTONE_IRON_ORE = BLOCKS.register("gelustone_iron_ore",
			() -> new DropExperienceBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE)
					.instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3.0F, 3.0F)));

	public static final RegistryObject<Block> JOVIAN_PORTAL = BLOCKS.register("jovian_portal",
			() -> new JovianGoToPortalBlock(BlockBehaviour.Properties.of().strength(-1.0F, 3600000.0F).noLootTable()
					.isValidSpawn(AMBlocks::never)));
	public static final RegistryObject<Block> JOVIAN_RETURN_PORTAL = BLOCKS.register("jovian_return_portal",
			() -> new JovianReturnPortalBlock(BlockBehaviour.Properties.of().strength(-1.0F, 3600000.0F).noLootTable()
					.isValidSpawn(AMBlocks::never)));
	public static final RegistryObject<Block> JOVIAN_BOSS_SPAWNER = BLOCKS.register("jovian_boss_spawner",
			() -> new JovianBossSpawnerBlock(BlockBehaviour.Properties.of().strength(-1.0F, 3600000.0F).noLootTable()
					.isValidSpawn(AMBlocks::never)));

	public static final RegistryObject<Block> HARDENED_CLOUD = BLOCKS.register("hardened_cloud", () -> new GlassBlock(
			BlockBehaviour.Properties.of().strength(0.1F, 3.0F).sound(SoundType.WOOL).noOcclusion().isViewBlocking(AMBlocks::never)));

	/*
	 * Methods
	 */

	// Makes a stair block
	private static final RegistryObject<StairBlock> makeSimpleStairBlock(String name,
			RegistryObject<? extends Block> blockin) {

		return BLOCKS.register(name,
				() -> new StairBlock(blockin.get().defaultBlockState(), BlockBehaviour.Properties.copy(blockin.get())));
	}

	// Makes a slab block
	private static final RegistryObject<SlabBlock> simpleSlabBlock(String name,
			RegistryObject<? extends Block> blockin) {
		return BLOCKS.register(name, () -> new SlabBlock(BlockBehaviour.Properties.copy(blockin.get())));
	}

	// Makes making log blocks easier
	private static RegistryObject<RotatedPillarBlock> simplePillarBlock(String name,
			BlockBehaviour.Properties properties) {
		return BLOCKS.register(name, () -> new RotatedPillarBlock(properties));
	}

	// Makes making log blocks easier
	private static RegistryObject<RotatedPillarBlock> simpleLogBlock(String name) {
		return simplePillarBlock(name, BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS)
				.strength(2.0F).sound(SoundType.WOOD).ignitedByLava());
	}

	// I don't know what this does. Copied from blocks.class
	private static Boolean never(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
		return (boolean) false;
	}

	// I don't know what this does. Copied from blocks.class
	private static Boolean never(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos,
			EntityType<?> entityType) {
		return (boolean) false;
	}

}
