package com.mudkipboy7.alien.data.loot;

import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import com.mudkipboy7.alien.AMRegistry;
import com.mudkipboy7.alien.world.block.AMBlocks;
import com.mudkipboy7.alien.world.item.AMItems;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;

public class AMBlockLootGen extends BlockLootSubProvider {

	public AMBlockLootGen() {
		super(Set.of(), FeatureFlags.REGISTRY.allFlags());
	}

	/*
	 * If it ever tells you that you created loot tabled for [minecraft:empty] the
	 * block has the .noLootTable() property set.
	 */
	@SuppressWarnings("deprecation")
	@Override
	protected void generate() {
		this.dropSelf(AMBlocks.TEST_BLOCK.get());
		//this.dropSelf(AMBlocks.AMMONIA_LIQUID_BLOCK.get());

		this.add(AMBlocks.ALIEN_STONE.get(), (block) -> {
			return this.createSingleItemTableWithSilkTouch(block, AMBlocks.ALIEN_COBBLESTONE.get());
		});

		this.dropSelf(AMBlocks.ALIEN_DIRT.get());

		this.add(AMBlocks.ALIEN_GRASS_BLOCK.get(), (block) -> {
			return this.createSingleItemTableWithSilkTouch(block, AMBlocks.ALIEN_DIRT.get());
		});

		this.dropSelf(AMBlocks.ALIEN_COBBLESTONE.get());

		this.add(AMBlocks.ALIEN_COAL_ORE.get(), (block) -> {
			return this.createOreDrop(block, Items.COAL);
		});

		this.dropSelf(AMBlocks.ALIEN_PLANKS.get());
		this.dropSelf(AMBlocks.ALIEN_LOG.get());

		this.add(AMBlocks.ALIEN_LEAVES.get(), (block) -> {
			return this.createLeavesDrops(block, AMItems.ALIEN_SAPLING.get().getBlock(), NORMAL_LEAVES_SAPLING_CHANCES);
		});

		this.dropSelf(AMBlocks.ALIEN_STONE_STAIRS.get());
		this.dropSelf(AMBlocks.ALIEN_COBBLESTONE_STAIRS.get());
		this.dropSelf(AMBlocks.ALIEN_WOOD_STAIRS.get());
		this.dropSelf(AMBlocks.ALIEN_STONE_SLAB.get());
		this.dropSelf(AMBlocks.ALIEN_COBBLESTONE_SLAB.get());
		this.dropSelf(AMBlocks.ALIEN_WOOD_SLAB.get());
		this.dropSelf(AMBlocks.ALIEN_BEDROCK.get());
		this.dropSelf(AMBlocks.ALIEN_CRAFTING_TABLE.get());

		this.add(AMBlocks.ALIEN_GOLD_ORE.get(), (block) -> {
			return this.createOreDrop(block, Items.RAW_GOLD);
		});

		this.add(AMBlocks.ALIEN_GRASS.get(), (block) -> {
			return createShearsDispatchTable(block,
					this.applyExplosionDecay(block,
							LootItem.lootTableItem(AMItems.ALIEN_SEEDS.get())
									.when(LootItemRandomChanceCondition.randomChance(0.125F))
									.apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE, 2))));
		});

		this.dropSelf(AMBlocks.ALIEN_SAPLING.get());
		// this.dropSelf(AMBlocks.DEAD_PLANT.get());
		this.add(AMBlocks.ALIEN_DOUBLE_GRASS.get(), (block) -> {
			return createShearsDispatchTable(block,
					this.applyExplosionDecay(block,
							LootItem.lootTableItem(AMItems.ALIEN_SEEDS.get())
									.when(LootItemRandomChanceCondition.randomChance(0.125F))
									.apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE, 2))));
		});

		this.add(AMBlocks.ALIEN_WOOD_DOOR.get(), (block) -> {
			return this.createDoorTable(block);
		});

		this.dropSelf(AMBlocks.ALIEN_WOOD_FENCE.get());
		this.dropSelf(AMBlocks.ALIEN_WOOD_FENCE_GATE.get());
		this.dropSelf(AMBlocks.ALIEN_WOOD_TRAPDOOR.get());
		this.dropSelf(AMBlocks.ALIEN_WOOD_BUTTON.get());
		this.dropSelf(AMBlocks.ALIEN_WOOD_PRESSURE_PLATE.get());
		this.dropSelf(AMBlocks.ALIEN_LOG_ALL_SIDES_SAME.get());
		this.dropSelf(AMBlocks.ALIEN_WOOD_SIGN.get());
		this.dropSelf(AMBlocks.ALIEN_WOOD_HANGING_SIGN.get());
		this.dropSelf(AMBlocks.STRIPPED_ALIEN_LOG.get());
		this.dropSelf(AMBlocks.STRIPPED_ALIEN_LOG_ALL_SIDES_SAME.get());
		this.dropSelf(AMBlocks.AIR_PURIFIER.get());
		this.dropSelf(AMBlocks.ALIEN_WOOD_CHEST.get());
		this.dropSelf(AMBlocks.ALIEN_STONE_BUTTON.get());
		this.dropSelf(AMBlocks.ALIEN_STONE_PRESSURE_PLATE.get());
		this.dropSelf(AMBlocks.ALIEN_COBBLESTONE_WALL.get());
		this.dropSelf(AMBlocks.COAL_GENERATOR.get());
		this.dropSelf(AMBlocks.ENERGY_STORAGE.get());
		this.dropSelf(AMBlocks.ALIEN_METAL_ORE.get());
		this.dropSelf(AMBlocks.ALIEN_METAL_BLOCK.get());
		this.dropSelf(AMBlocks.RAW_ALIEN_METAL_BLOCK.get());
		this.dropSelf(AMBlocks.COPPER_WIRE.get());
		this.dropSelf(AMBlocks.LAZER_CREATOR.get());
	}

	@Override
	protected Iterable<Block> getKnownBlocks() {
		return AMRegistry.BLOCKS.getEntries().stream().map(Supplier::get).collect(Collectors.toList());
	}
}
