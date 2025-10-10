package com.mudkipboy7.alien.data.tags;

import java.util.concurrent.CompletableFuture;

import org.jetbrains.annotations.Nullable;

import com.mudkipboy7.alien.AlienMod;
import com.mudkipboy7.alien.world.block.AMBlocks;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class AMBlockTags extends BlockTagsProvider {

	public AMBlockTags(PackOutput output, CompletableFuture<Provider> lookupProvider,
			@Nullable ExistingFileHelper existingFileHelper) {
		super(output, lookupProvider, AlienMod.MODID, existingFileHelper);
	}

	// Block Tags
	public static final TagKey<Block> MINEABLE_WITH_ALIEN_SWORD = makeKey("mineable/alien_sword");
	public static final TagKey<Block> MINEABLE_WITH_ALIEN_PICKAXE = makeKey("mineable/alien_pickaxe");
	public static final TagKey<Block> MINEABLE_WITH_ALIEN_AXE = makeKey("mineable/alien_axe");
	public static final TagKey<Block> MINEABLE_WITH_ALIEN_SHOVEL = makeKey("mineable/alien_shovel");
	public static final TagKey<Block> MINEABLE_WITH_ALIEN_HOE = makeKey("mineable/alien_hoe");
	public static final TagKey<Block> ALIEN_SOIL = makeKey("alien_soil");
	public static final TagKey<Block> ALIEN_PLANT = makeKey("alien_plant");
	public static final TagKey<Block> SUPPORTS_ALIEN_LEAVES = makeKey("supports_alien_leaves");
	public static final TagKey<Block> LIGNUM_LOGS = makeKey("lignum_logs");
	public static final TagKey<Block> LIGNUM_PLANKS = makeKey("lignum_planks");
	public static final TagKey<Block> LIGNUM_STAIRS = makeKey("lignum_stairs");
	public static final TagKey<Block> LIGNUM_SLABS = makeKey("lignum_slabs");
	// Only needs to define blocks that aren't considered air
	public static final TagKey<Block> LAZER_CAN_PASS = makeKey("lazer_can_pass");

	/*
	 * Remember, after doing this you might want to go and copy these tags into
	 * AMItemTags
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void addTags(Provider provider) {
		/*
		 * AlienMod
		 */

		tag(MINEABLE_WITH_ALIEN_SWORD).add(Blocks.COBWEB);

		tag(MINEABLE_WITH_ALIEN_PICKAXE).add(AMBlocks.ALIEN_STONE.get(), AMBlocks.ALIEN_COBBLESTONE.get(),
				AMBlocks.ALIEN_COAL_ORE.get(), AMBlocks.ALIEN_STONE_STAIRS.get(),
				AMBlocks.ALIEN_COBBLESTONE_STAIRS.get(), AMBlocks.ALIEN_STONE_SLAB.get(),
				AMBlocks.ALIEN_COBBLESTONE_SLAB.get(), AMBlocks.ALIEN_GOLD_ORE.get(), AMBlocks.ALIEN_STONE_BUTTON.get(),
				AMBlocks.ALIEN_STONE_PRESSURE_PLATE.get(), AMBlocks.ALIEN_COBBLESTONE_WALL.get(),
				AMBlocks.ALIEN_METAL_ORE.get(), AMBlocks.ALIEN_METAL_BLOCK.get(), AMBlocks.RAW_ALIEN_METAL_BLOCK.get(),

				Blocks.BELL, Blocks.FURNACE, Blocks.BLAST_FURNACE, Blocks.SMOKER, Blocks.BREWING_STAND)
				.addTags(BlockTags.ANVIL, BlockTags.CAULDRONS, Tags.Blocks.OBSIDIAN, Tags.Blocks.CHESTS_ENDER,
						Tags.Blocks.STORAGE_BLOCKS_GOLD, Tags.Blocks.STORAGE_BLOCKS_RAW_GOLD,
						Tags.Blocks.STORAGE_BLOCKS_COAL);

		tag(MINEABLE_WITH_ALIEN_AXE)
				.add(AMBlocks.ALIEN_PLANKS.get(), AMBlocks.ALIEN_WOOD_STAIRS.get(), AMBlocks.ALIEN_WOOD_SLAB.get(),
						AMBlocks.ALIEN_CRAFTING_TABLE.get(), AMBlocks.ALIEN_WOOD_DOOR.get(),
						AMBlocks.ALIEN_WOOD_FENCE.get(), AMBlocks.ALIEN_WOOD_FENCE_GATE.get(),
						AMBlocks.ALIEN_WOOD_TRAPDOOR.get(), AMBlocks.ALIEN_WOOD_BUTTON.get(),
						AMBlocks.ALIEN_WOOD_PRESSURE_PLATE.get(), AMBlocks.ALIEN_WOOD_CHEST.get(),

						Blocks.CRAFTING_TABLE, Blocks.COMPOSTER, Blocks.FLETCHING_TABLE)
				.addTags(AMBlockTags.LIGNUM_LOGS,

						BlockTags.BEDS, Tags.Blocks.CHESTS_WOODEN, Tags.Blocks.BARRELS_WOODEN,
						Tags.Blocks.STORAGE_BLOCKS_COAL);

		tag(MINEABLE_WITH_ALIEN_SHOVEL).add(AMBlocks.ALIEN_DIRT.get(), AMBlocks.ALIEN_GRASS_BLOCK.get());

		tag(MINEABLE_WITH_ALIEN_HOE).add(AMBlocks.ALIEN_LEAVES.get());

		tag(ALIEN_SOIL).add(AMBlocks.ALIEN_DIRT.get(), AMBlocks.ALIEN_GRASS_BLOCK.get());

		tag(ALIEN_PLANT).add(AMBlocks.ALIEN_LEAVES.get(), AMBlocks.ALIEN_GRASS.get(), AMBlocks.ALIEN_SAPLING.get());

		tag(SUPPORTS_ALIEN_LEAVES).addTag(LIGNUM_LOGS);

		tag(LIGNUM_LOGS).add(AMBlocks.ALIEN_LOG.get(), AMBlocks.ALIEN_LOG_ALL_SIDES_SAME.get(),
				AMBlocks.STRIPPED_ALIEN_LOG.get(), AMBlocks.STRIPPED_ALIEN_LOG_ALL_SIDES_SAME.get(),
				AMBlocks.THIN_ALIEN_LOG.get());

		tag(LIGNUM_PLANKS).add(AMBlocks.ALIEN_PLANKS.get());

		tag(LIGNUM_STAIRS).add(AMBlocks.ALIEN_WOOD_STAIRS.get());

		tag(LIGNUM_SLABS).add(AMBlocks.ALIEN_WOOD_SLAB.get());

		tag(LAZER_CAN_PASS).add(Blocks.GLASS);

		/*
		 * Vanilla/Forge/Other
		 */
		tag(BlockTags.NEEDS_IRON_TOOL).add(AMBlocks.ALIEN_GOLD_ORE.get());

		tag(BlockTags.COAL_ORES).add(AMBlocks.ALIEN_COAL_ORE.get());

		tag(BlockTags.GOLD_ORES).add(AMBlocks.ALIEN_GOLD_ORE.get());

		tag(BlockTags.ENDERMAN_HOLDABLE).addTag(ALIEN_SOIL);

		tag(BlockTags.WOODEN_FENCES).add(AMBlocks.ALIEN_WOOD_FENCE.get());

		tag(BlockTags.FENCE_GATES).add(AMBlocks.ALIEN_WOOD_FENCE_GATE.get());

		tag(BlockTags.STAIRS).add(AMBlocks.ALIEN_STONE_STAIRS.get(), AMBlocks.ALIEN_COBBLESTONE_STAIRS.get());

		tag(BlockTags.SLABS).add(AMBlocks.ALIEN_STONE_SLAB.get(), AMBlocks.ALIEN_COBBLESTONE_SLAB.get());

		tag(BlockTags.STANDING_SIGNS).add(AMBlocks.ALIEN_WOOD_SIGN.get());
		tag(BlockTags.WALL_SIGNS).add(AMBlocks.ALIEN_WOOD_WALL_SIGN.get());

		tag(BlockTags.CEILING_HANGING_SIGNS).add(AMBlocks.ALIEN_WOOD_HANGING_SIGN.get());

		tag(BlockTags.WALL_HANGING_SIGNS).add(AMBlocks.ALIEN_WOOD_WALL_HANGING_SIGN.get());

		tag(BlockTags.PLANKS).addTags(AMBlockTags.LIGNUM_PLANKS);

		tag(BlockTags.WOODEN_STAIRS).addTags(AMBlockTags.LIGNUM_STAIRS);

		tag(BlockTags.WOODEN_SLABS).addTags(AMBlockTags.LIGNUM_SLABS);

		tag(BlockTags.LOGS_THAT_BURN).addTags(AMBlockTags.LIGNUM_LOGS);

		tag(BlockTags.WOODEN_BUTTONS).add(AMBlocks.ALIEN_WOOD_BUTTON.get());

		tag(BlockTags.WOODEN_PRESSURE_PLATES).add(AMBlocks.ALIEN_WOOD_PRESSURE_PLATE.get());

		tag(BlockTags.STONE_BUTTONS).add(AMBlocks.ALIEN_STONE_BUTTON.get());

		tag(BlockTags.PRESSURE_PLATES).add(AMBlocks.ALIEN_STONE_PRESSURE_PLATE.get());

		tag(BlockTags.WALLS).add(AMBlocks.ALIEN_COBBLESTONE_WALL.get());

		tag(BlockTags.WOODEN_DOORS).add(AMBlocks.ALIEN_WOOD_DOOR.get());

		tag(BlockTags.WOODEN_TRAPDOORS).add(AMBlocks.ALIEN_WOOD_TRAPDOOR.get());

		tag(Tags.Blocks.CHESTS_WOODEN).add(AMBlocks.ALIEN_WOOD_CHEST.get());

		tag(Tags.Blocks.ORES).add(AMBlocks.ALIEN_METAL_ORE.get());

		tag(Tags.Blocks.STORAGE_BLOCKS).add(AMBlocks.ALIEN_METAL_BLOCK.get(), AMBlocks.RAW_ALIEN_METAL_BLOCK.get());

		tag(BlockTags.MINEABLE_WITH_AXE).remove(LIGNUM_LOGS, LIGNUM_PLANKS, LIGNUM_SLABS, LIGNUM_STAIRS);
	}

	// Methods
	private static TagKey<Block> makeKey(String id, String name) {

		return TagKey.create(Registries.BLOCK, new ResourceLocation(id, name));
	}

	private static TagKey<Block> makeKey(String name) {
		return makeKey(AlienMod.MODID, name);
	}
}
