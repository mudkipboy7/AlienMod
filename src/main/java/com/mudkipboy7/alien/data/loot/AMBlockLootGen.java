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
		// this.dropSelf(AMBlocks.AMMONIA_LIQUID_BLOCK.get());

		this.add(AMBlocks.GELUSTONE.get(), (block) -> {
			return this.createSingleItemTableWithSilkTouch(block, AMBlocks.ALIEN_COBBLESTONE.get());
		});

		this.dropSelf(AMBlocks.ALGUSSOIL.get());

		this.add(AMBlocks.GRAMEN_BLOCK.get(), (block) -> {
			return this.createSingleItemTableWithSilkTouch(block, AMBlocks.ALGUSSOIL.get());
		});

		this.dropSelf(AMBlocks.ALIEN_COBBLESTONE.get());

		this.add(AMBlocks.GELUSTONE_COAL_ORE.get(), (block) -> {
			return this.createOreDrop(block, Items.COAL);
		});

		this.dropSelf(AMBlocks.LIGNUM_PLANKS.get());
		this.dropSelf(AMBlocks.LIGNUM_LOG.get());

		this.add(AMBlocks.LIGNUM_LEAVES.get(), (block) -> {
			return this.createLeavesDrops(block, AMItems.LIGNUM_SAPLING.get().getBlock(), NORMAL_LEAVES_SAPLING_CHANCES);
		});

		this.dropSelf(AMBlocks.GLELUSTONE_STAIRS.get());
		this.dropSelf(AMBlocks.COBBLE_GELUSTONE_STAIRS.get());
		this.dropSelf(AMBlocks.LIGNUM_STAIRS.get());
		this.dropSelf(AMBlocks.GELUSTONE_SLAB.get());
		this.dropSelf(AMBlocks.COBBLE_GELUSTONE_SLAB.get());
		this.dropSelf(AMBlocks.LIGNUM_SLAB.get());
		this.dropSelf(AMBlocks.GELUSTONE_BEDROCK.get());
		this.dropSelf(AMBlocks.LIGNUM_CRAFTING_TABLE.get());

		this.add(AMBlocks.GELUSTONE_GOLD_ORE.get(), (block) -> {
			return this.createOreDrop(block, Items.RAW_GOLD);
		});

		this.add(AMBlocks.TALL_GRAMEN.get(), (block) -> {
			return createShearsDispatchTable(block,
					this.applyExplosionDecay(block,
							LootItem.lootTableItem(AMItems.ALIEN_SEEDS.get())
									.when(LootItemRandomChanceCondition.randomChance(0.125F))
									.apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE, 2))));
		});

		this.dropSelf(AMBlocks.LIGNUM_SAPLING.get());
		// this.dropSelf(AMBlocks.DEAD_PLANT.get());
		this.add(AMBlocks.DOUBLE_TALL_GRAMEN.get(), (block) -> {
			return createShearsDispatchTable(block,
					this.applyExplosionDecay(block,
							LootItem.lootTableItem(AMItems.ALIEN_SEEDS.get())
									.when(LootItemRandomChanceCondition.randomChance(0.125F))
									.apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE, 2))));
		});

		this.add(AMBlocks.LIGNUM_DOOR.get(), (block) -> {
			return this.createDoorTable(block);
		});

		this.dropSelf(AMBlocks.LIGNUM_FENCE.get());
		this.dropSelf(AMBlocks.LIGNUM_FENCE_GATE.get());
		this.dropSelf(AMBlocks.LIGNUM_TRAPDOOR.get());
		this.dropSelf(AMBlocks.LIGNUM_BUTTON.get());
		this.dropSelf(AMBlocks.LIGNUM_PRESSURE_PLATE.get());
		this.dropSelf(AMBlocks.LIGNUM_LOG_ALL_SIDES_SAME.get());
		this.dropSelf(AMBlocks.LIGNUM_SIGN.get());
		this.dropSelf(AMBlocks.LIGNUM_HANGING_SIGN.get());
		// this.dropSelf(AMBlocks.STRIPPED_ALIEN_LOG.get());
		// this.dropSelf(AMBlocks.STRIPPED_ALIEN_LOG_ALL_SIDES_SAME.get());
		this.dropSelf(AMBlocks.AIR_PURIFIER.get());
		this.dropSelf(AMBlocks.LIGNUM_CHEST.get());
		this.dropSelf(AMBlocks.GELUSTONE_BUTTON.get());
		this.dropSelf(AMBlocks.GELUSTONE_PRESSURE_PLATE.get());
		this.dropSelf(AMBlocks.COBBLE_GELUSTONE_WALL.get());
		this.dropSelf(AMBlocks.COAL_GENERATOR.get());
		this.dropSelf(AMBlocks.ENERGY_STORAGE.get());
		this.add(AMBlocks.APSIUS_ORE.get(), (block) -> {
			return this.createOreDrop(block, AMItems.APSIUS_GEM.get());
		});
		this.dropSelf(AMBlocks.APSIUS_BLOCK.get());
		this.dropSelf(AMBlocks.COPPER_WIRE.get());
		this.dropSelf(AMBlocks.LAZER_CREATOR.get());
		this.dropSelf(AMBlocks.ENERGY_BLOCK.get());
		this.dropSelf(AMBlocks.THIN_LIGNUM_LOG.get());
		this.add(AMBlocks.GELUSTONE_IRON_ORE.get(), (block) -> {
			return this.createOreDrop(block, Items.RAW_GOLD);
		});
		this.dropSelf(AMBlocks.REGOLITH.get());
		this.dropSelf(AMBlocks.HARDENED_CLOUD.get());
		
	}

	@Override
	protected Iterable<Block> getKnownBlocks() {
		return AMRegistry.BLOCKS.getEntries().stream().map(Supplier::get).collect(Collectors.toList());
	}
}
