package mudkipboy7.alien.world.block;

import mudkipboy7.alien.AlienMod;
import mudkipboy7.alien.world.block.blockentity.AMBlockEntities;
import mudkipboy7.alien.world.block.flora.AlienDoubleTallGrassBlock;
import mudkipboy7.alien.world.block.flora.AlienGrassBlock;
import mudkipboy7.alien.world.block.flora.AlienLeavesBlock;
import mudkipboy7.alien.world.block.flora.AlienSaplingBlock;
import mudkipboy7.alien.world.block.flora.AlienTallGrassBlock;
import mudkipboy7.alien.world.block.flora.DeadPlantBlock;
import mudkipboy7.alien.world.block.fluid.AmmoniaLiquidBlock;
import mudkipboy7.alien.world.block.functional.AlienChestBlock;
import mudkipboy7.alien.world.block.functional.AlienCraftingTableBlock;
import mudkipboy7.alien.world.block.functional.AlienPortalBlock;
import mudkipboy7.alien.world.block.functional.machine.CoalGeneratorBlock;
import mudkipboy7.alien.world.block.functional.machine.EnergyStorageBlock;
import mudkipboy7.alien.world.block.functional.machine.HazardRemovalBlock;
import mudkipboy7.alien.world.block.functional.machine.transport.LazerCreatorBlock;
import mudkipboy7.alien.world.block.functional.machine.transport.LazerExtenderBlock;
import mudkipboy7.alien.world.block.functional.machine.transport.WireBlock;
import mudkipboy7.alien.world.block.functional.sign.AlienCeilingHangingSignBlock;
import mudkipboy7.alien.world.block.functional.sign.AlienStandingSignBlock;
import mudkipboy7.alien.world.block.functional.sign.AlienWallHangingSignBlock;
import mudkipboy7.alien.world.block.functional.sign.AlienWallSignBlock;
import mudkipboy7.alien.world.block.misc.AlienAirBlock;
import mudkipboy7.alien.world.block.misc.TestBlock;
import mudkipboy7.alien.world.worldgen.structure.grower.AMTreeGrowers;
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
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@SuppressWarnings("deprecation")
public class AMBlocks {
	// Loader Stuff
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
			AlienMod.MODID);

	/*
	 * Register Blocks
	 */

	// Block for testing mod functions
	public static final RegistryObject<Block> TEST_BLOCK = BLOCKS.register("test_block",
			() -> new TestBlock(BlockBehaviour.Properties.of().strength(1F, 6.0F)));

	// Block for the ammonia liquid
	public static final RegistryObject<AmmoniaLiquidBlock> AMMONIA_LIQUID_BLOCK = BLOCKS.register("ammonia_liquid",
			() -> new AmmoniaLiquidBlock((AMFluids.AMMONIA_LIQUID_SOURCE), BlockBehaviour.Properties.of()));

	
	public static final RegistryObject<Block> ALIEN_AIR = BLOCKS.register("alien_air", () -> new AlienAirBlock(
			BlockBehaviour.Properties.of().noCollission().noLootTable().noOcclusion().strength(-1.0F, 3600000.0F)));

	// Stone for the Alien Dimension
	public static final RegistryObject<Block> ALIEN_STONE = BLOCKS.register("gelustone",
			() -> new Block(BlockBehaviour.Properties.of().strength(1.5F, 6.0F).sound(SoundType.NETHERRACK)
					.requiresCorrectToolForDrops()));

	// Dirt for the Alien Dimension
	public static final RegistryObject<Block> ALIEN_DIRT = BLOCKS.register("algussoil",
			() -> new Block(BlockBehaviour.Properties.of().strength(0.5F, 6.0F).sound(SoundType.MUD)));

	// Grass for the Alien Dimension
	public static final RegistryObject<Block> ALIEN_GRASS_BLOCK = BLOCKS.register("gramen_block",
			() -> new AlienGrassBlock(
					BlockBehaviour.Properties.of().strength(0.5F, 6.0F).sound(SoundType.WET_GRASS).randomTicks()));

	// Cobblestone for the Alien Dimension
	public static final RegistryObject<Block> ALIEN_COBBLESTONE = BLOCKS.register("cobble_gelustone",
			() -> new Block(BlockBehaviour.Properties.of().strength(1.5F, 6.0F).sound(SoundType.NETHERRACK)
					.requiresCorrectToolForDrops()));

	// Coal ore for the Alien Dimension
	public static final RegistryObject<Block> ALIEN_COAL_ORE = BLOCKS
			.register("gelustone_coal_ore",
					() -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.COAL_ORE)
							.mapColor(MapColor.DEEPSLATE).strength(3.0F, 3.0F).sound(SoundType.NETHERRACK),
							UniformInt.of(0, 2)));

	// Wooden planks for the Alien Dimension
	public static final RegistryObject<Block> ALIEN_PLANKS = BLOCKS.register("lignum_planks",
			() -> new Block(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).strength(2.0F)
					.sound(SoundType.WOOD).ignitedByLava()));

	// Wooden logs for the Alien Dimension
	public static final RegistryObject<RotatedPillarBlock> ALIEN_LOG = makeSimpleLogBlock("lignum_log");

	// Leaves for the Alien Dimension
	public static final RegistryObject<AlienLeavesBlock> ALIEN_LEAVES = BLOCKS.register("lignum_leaves",
			() -> new AlienLeavesBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS)
					.strength(2.0F).sound(SoundType.GRASS).strength(0.2F).ignitedByLava().noOcclusion()
					.isSuffocating(AMBlocks::never).isViewBlocking(AMBlocks::never)
					.pushReaction(PushReaction.DESTROY)));

	// Stairs for the alien stone
	public static final RegistryObject<StairBlock> ALIEN_STONE_STAIRS = makeSimpleStairBlock("gelustone_stairs",
			ALIEN_STONE);

	// Stairs for the alien cobblestone
	public static final RegistryObject<StairBlock> ALIEN_COBBLESTONE_STAIRS = makeSimpleStairBlock(
			"cobble_gelustone_stairs", ALIEN_COBBLESTONE);

	// Stairs for the alien planks
	public static final RegistryObject<StairBlock> ALIEN_WOOD_STAIRS = makeSimpleStairBlock("lignum_stairs",
			ALIEN_PLANKS);

	// Slabs for the alien stone
	public static final RegistryObject<SlabBlock> ALIEN_STONE_SLAB = makeSimpleSlabBlock("gelustone_slab", ALIEN_STONE);

	// Slabs for the alien cobblestone
	public static final RegistryObject<SlabBlock> ALIEN_COBBLESTONE_SLAB = makeSimpleSlabBlock("cobble_gelustone_slab",
			ALIEN_COBBLESTONE);

	// Slabs for the alien planks
	public static final RegistryObject<SlabBlock> ALIEN_WOOD_SLAB = makeSimpleSlabBlock("lignum_slab", ALIEN_PLANKS);

	// Alien bedrock
	public static final RegistryObject<Block> ALIEN_BEDROCK = BLOCKS.register("gelustone_bedrock",
			() -> new Block(Properties.copy(Blocks.BEDROCK)));

	// Alien wood crafting table
	public static final RegistryObject<CraftingTableBlock> ALIEN_CRAFTING_TABLE = BLOCKS.register(
			"lignum_crafting_table", () -> new AlienCraftingTableBlock(Properties.copy(Blocks.CRAFTING_TABLE)));

	// Gold ore for the Alien Dimension
	public static final RegistryObject<Block> ALIEN_GOLD_ORE = BLOCKS
			.register("gelustone_gold_ore",
					() -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.GOLD_ORE)
							.mapColor(MapColor.STONE).strength(3.0F, 3.0F).sound(SoundType.NETHERRACK),
							ConstantInt.of(0)));

	// Tall grass for the Alien Dimension
	public static final RegistryObject<Block> ALIEN_GRASS = BLOCKS.register("gramen",
			() -> new AlienTallGrassBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).replaceable()
					.noCollission().instabreak().sound(SoundType.WET_GRASS).offsetType(BlockBehaviour.OffsetType.XZ)
					.ignitedByLava().pushReaction(PushReaction.DESTROY)));

	// Block for testing mod functions
	public static final RegistryObject<Block> ALIEN_PORTAL = BLOCKS.register("alien_portal", () -> new AlienPortalBlock(
			BlockBehaviour.Properties.of().strength(-1.0F, 3600000.0F).noLootTable().isValidSpawn(AMBlocks::never)));

	// Alien sapling
	public static final RegistryObject<Block> ALIEN_SAPLING = BLOCKS.register("lignum_sapling",
			() -> new AlienSaplingBlock(AMTreeGrowers.basicAlienTreeGrower,
					BlockBehaviour.Properties.of().sound(SoundType.GRASS)));

	// Dead Plant
	public static final RegistryObject<Block> DEAD_PLANT = BLOCKS.register("dead_plant",
			() -> new DeadPlantBlock(BlockBehaviour.Properties.of().sound(SoundType.GRASS).noLootTable().noCollission()
					.pushReaction(PushReaction.DESTROY)));

	// Tall grass for the Alien Dimension
	public static final RegistryObject<Block> ALIEN_DOUBLE_GRASS = BLOCKS.register("tall_gramen",
			() -> new AlienDoubleTallGrassBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).replaceable()
					.noCollission().instabreak().sound(SoundType.WET_GRASS).offsetType(BlockBehaviour.OffsetType.XZ)
					.ignitedByLava().pushReaction(PushReaction.DESTROY)));

	// Alien wood door
	public static final RegistryObject<DoorBlock> ALIEN_WOOD_DOOR = BLOCKS.register("lignum_door",
			() -> new DoorBlock(BlockBehaviour.Properties.of().sound(SoundType.WOOD)
					.mapColor(ALIEN_PLANKS.get().defaultMapColor()).instrument(NoteBlockInstrument.BASS).strength(3.0F)
					.noOcclusion().ignitedByLava().pushReaction(PushReaction.DESTROY), AMBlockSetTypes.LIGNUM));

	// Alien wood fence
	public static final RegistryObject<FenceBlock> ALIEN_WOOD_FENCE = BLOCKS.register("lignum_fence",
			() -> new FenceBlock(BlockBehaviour.Properties.of().mapColor(ALIEN_PLANKS.get().defaultMapColor())
					.forceSolidOn().instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).sound(SoundType.WOOD)
					.ignitedByLava()));

	// Alien wood fence gate
	public static final RegistryObject<FenceGateBlock> ALIEN_WOOD_FENCE_GATE = BLOCKS.register("lignum_fence_gate",
			() -> new FenceGateBlock(
					BlockBehaviour.Properties.of().sound(SoundType.WOOD).mapColor(ALIEN_PLANKS.get().defaultMapColor())
							.forceSolidOn().instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).ignitedByLava(),
					AMBlockSetTypes.LIGNUM_WOOD_TYPE));

	// Alien wood trapdoor
	public static final RegistryObject<TrapDoorBlock> ALIEN_WOOD_TRAPDOOR = BLOCKS.register("lignum_trapdoor",
			() -> new TrapDoorBlock(
					BlockBehaviour.Properties.of().mapColor(ALIEN_PLANKS.get().defaultMapColor())
							.instrument(NoteBlockInstrument.BASS).strength(3.0F).noOcclusion()
							.isValidSpawn(AMBlocks::never).ignitedByLava().sound(SoundType.WOOD),
					AMBlockSetTypes.LIGNUM));

	// Alien wood button
	public static final RegistryObject<ButtonBlock> ALIEN_WOOD_BUTTON = BLOCKS
			.register("lignum_button",
					() -> new ButtonBlock(
							BlockBehaviour.Properties.of().noCollission().strength(0.5F)
									.pushReaction(PushReaction.DESTROY).sound(SoundType.WOOD),
							AMBlockSetTypes.LIGNUM, 30, true));

	// Alien wood presure plate
	public static final RegistryObject<PressurePlateBlock> ALIEN_WOOD_PRESSURE_PLATE = BLOCKS.register(
			"lignum_pressure_plate",
			() -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,
					BlockBehaviour.Properties.of().mapColor(ALIEN_PLANKS.get().defaultMapColor()).forceSolidOn()
							.instrument(NoteBlockInstrument.BASS).noCollission().strength(0.5F).ignitedByLava()
							.pushReaction(PushReaction.DESTROY).sound(SoundType.WOOD),
					AMBlockSetTypes.LIGNUM));

	// Log with side texture on all sides for the alien dim
	public static final RegistryObject<RotatedPillarBlock> ALIEN_LOG_ALL_SIDES_SAME = BLOCKS
			.register("lignum_all_sides_log", () -> new RotatedPillarBlock(Properties.copy(ALIEN_LOG.get())));

	// Alien wood standing sign
	public static final RegistryObject<AlienStandingSignBlock> ALIEN_WOOD_SIGN = BLOCKS.register("lignum_sign",
			() -> new AlienStandingSignBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).forceSolidOn()
					.instrument(NoteBlockInstrument.BASS).noCollission().strength(1.0F).ignitedByLava()
					.sound(SoundType.WOOD), AMBlockSetTypes.LIGNUM_WOOD_TYPE));

	// Alien wood wall sign
	public static final RegistryObject<AlienWallSignBlock> ALIEN_WOOD_WALL_SIGN = BLOCKS.register("lignum_wall_sign",
			() -> new AlienWallSignBlock(
					BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).forceSolidOn()
							.instrument(NoteBlockInstrument.BASS).noCollission().strength(1.0F)
							.dropsLike(ALIEN_WOOD_SIGN.get()).ignitedByLava().sound(SoundType.WOOD),
					AMBlockSetTypes.LIGNUM_WOOD_TYPE));

	// Alien wood ceiling hanging sign
	public static final RegistryObject<AlienCeilingHangingSignBlock> ALIEN_WOOD_HANGING_SIGN = BLOCKS.register(
			"lignum_hanging_sign",
			() -> new AlienCeilingHangingSignBlock(BlockBehaviour.Properties.of()
					.mapColor(ALIEN_LOG.get().defaultMapColor()).forceSolidOn().instrument(NoteBlockInstrument.BASS)
					.noCollission().strength(1.0F).ignitedByLava().sound(SoundType.WOOD),
					AMBlockSetTypes.LIGNUM_WOOD_TYPE));

	// Alien wood wall hanging sign
	public static final RegistryObject<AlienWallHangingSignBlock> ALIEN_WOOD_WALL_HANGING_SIGN = BLOCKS.register(
			"lignum_wall_hanging_sign",
			() -> new AlienWallHangingSignBlock(
					BlockBehaviour.Properties.of().mapColor(ALIEN_LOG.get().defaultMapColor()).forceSolidOn()
							.instrument(NoteBlockInstrument.BASS).noCollission().strength(1.0F).ignitedByLava()
							.dropsLike(ALIEN_WOOD_HANGING_SIGN.get()).sound(SoundType.WOOD),
					AMBlockSetTypes.LIGNUM_WOOD_TYPE));

	// Stripped alien log
	public static final RegistryObject<RotatedPillarBlock> STRIPPED_ALIEN_LOG = makeSimpleLogBlock(
			"stripped_lignum_log");

	// Stripped log log with side texture on all sides for the alien dim
	public static final RegistryObject<RotatedPillarBlock> STRIPPED_ALIEN_LOG_ALL_SIDES_SAME = makeSimpleLogBlock(
			"stripped_lignum_all_sides_log");

	// Protects the player from hazards
	public static final RegistryObject<HazardRemovalBlock> AIR_PURIFIER = BLOCKS.register("air_purifier",
			() -> new HazardRemovalBlock(BlockBehaviour.Properties.of().strength(1F, 6.0F)));

	// Alien wood chest
	public static final RegistryObject<AlienChestBlock> ALIEN_WOOD_CHEST = BLOCKS.register("lignum_chest",
			() -> new AlienChestBlock(Properties.copy(Blocks.CHEST), () -> {
				return AMBlockEntities.ALIEN_WOOD_CHEST.get();
			}));

	// Alien stone button
	public static final RegistryObject<ButtonBlock> ALIEN_STONE_BUTTON = BLOCKS.register("gelustone_button",
			() -> new ButtonBlock(BlockBehaviour.Properties.of().noCollission().strength(0.5F)
					.pushReaction(PushReaction.DESTROY).sound(SoundType.NETHERRACK), AMBlockSetTypes.GELUSTONE, 20,
					false));

	// Alien stone pressure plate
	public static final RegistryObject<PressurePlateBlock> ALIEN_STONE_PRESSURE_PLATE = BLOCKS.register(
			"gelustone_pressure_plate",
			() -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS,
					BlockBehaviour.Properties.of().mapColor(MapColor.STONE).forceSolidOn()
							.instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().noCollission()
							.strength(0.5F).pushReaction(PushReaction.DESTROY).sound(SoundType.NETHERRACK),
					AMBlockSetTypes.GELUSTONE));

	// Alien cobblestone wall
	public static final RegistryObject<WallBlock> ALIEN_COBBLESTONE_WALL = BLOCKS.register("cobble_gelustone_wall",
			() -> new WallBlock(BlockBehaviour.Properties.copy(ALIEN_COBBLESTONE.get()).forceSolidOn()));

	// Makes energy from coal
	public static final RegistryObject<CoalGeneratorBlock> COAL_GENERATOR = BLOCKS.register("coal_generator",
			() -> new CoalGeneratorBlock(BlockBehaviour.Properties.of().strength(1F, 6.0F)));

	// Stores Energy
	public static final RegistryObject<EnergyStorageBlock> ENERGY_STORAGE = BLOCKS.register("energy_storage_block",
			() -> new EnergyStorageBlock(BlockBehaviour.Properties.of().strength(1F, 6.0F)));

	// Ore for the Alien Dimension
	public static final RegistryObject<Block> ALIEN_METAL_ORE = BLOCKS.register("atsali_ore",
			() -> new DropExperienceBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE)
					.instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3.0F, 3.0F)));

	// Metal block for the Alien Dimension
	public static final RegistryObject<Block> ALIEN_METAL_BLOCK = BLOCKS.register("atsali_block",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.METAL)
					.instrument(NoteBlockInstrument.IRON_XYLOPHONE).requiresCorrectToolForDrops().strength(5.0F, 6.0F)
					.sound(SoundType.METAL)));

	// Raw metal block for the Alien Dimension
	public static final RegistryObject<Block> RAW_ALIEN_METAL_BLOCK = BLOCKS.register("raw_atsali_block",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.RAW_IRON)
					.instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(5.0F, 6.0F)));
	@Deprecated
	// Copper wire
	public static final RegistryObject<Block> COPPER_WIRE = BLOCKS.register("copper_wire",
			() -> new WireBlock(BlockBehaviour.Properties.of().sound(SoundType.WOOL)));

	// Lazer wire
	public static final RegistryObject<Block> LAZER_CREATOR = BLOCKS.register("lazer_creator",
			() -> new LazerCreatorBlock(BlockBehaviour.Properties.of()));

	// Lazer extender
	public static final RegistryObject<Block> LAZER_EXTENDER = BLOCKS.register("lazer_extender",
			() -> new LazerExtenderBlock(BlockBehaviour.Properties.of()));

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
	private static final RegistryObject<SlabBlock> makeSimpleSlabBlock(String name,
			RegistryObject<? extends Block> blockin) {
		return BLOCKS.register(name, () -> new SlabBlock(BlockBehaviour.Properties.copy(blockin.get())));
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

	// Makes making log blocks easier
	private static RegistryObject<RotatedPillarBlock> makePillarBlock(String name,
			BlockBehaviour.Properties properties) {
		return BLOCKS.register(name, () -> new RotatedPillarBlock(properties));
	}

	// Makes making log blocks easier
	private static RegistryObject<RotatedPillarBlock> makeSimpleLogBlock(String name) {
		return makePillarBlock(name, BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).strength(2.0F)
				.sound(SoundType.WOOD).ignitedByLava());
	}

}
