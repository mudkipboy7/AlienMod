package com.mudkipboy7.alien.world.worldgen.structure.placement;

import java.util.List;

import com.mudkipboy7.alien.AlienMod;
import com.mudkipboy7.alien.world.block.AMBlocks;
import com.mudkipboy7.alien.world.worldgen.structure.AMGrassFeatures;
import com.mudkipboy7.alien.world.worldgen.structure.AMTreeFeatures;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.BlockPredicateFilter;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.NoiseThresholdCountPlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import net.minecraft.world.level.levelgen.placement.SurfaceWaterDepthFilter;

public class AMVegetationPlacers {

	// Grasses
	public static final ResourceKey<PlacedFeature> TALL_GRAMEN = createKey("tall_gramen");
	public static final ResourceKey<PlacedFeature> DOUBLE_TALL_GRAMEN = createKey("double_tall_gramen");

	// Trees
	public static final ResourceKey<PlacedFeature> ALIEN_TREE = createKey("lignum_tree");
	public static final ResourceKey<PlacedFeature> THIN_TALL_ALIEN_TREE = createKey("thin_tall_lignum_tree");

	public static void bootstrap(BootstapContext<PlacedFeature> context) {
		HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

		context.register(TALL_GRAMEN,
				new PlacedFeature(configuredFeatures.getOrThrow(AMGrassFeatures.TALL_GRAMEN),
						List.of(NoiseThresholdCountPlacement.of(-0.8D, 0, 7), InSquarePlacement.spread(),
								PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome())));

		context.register(DOUBLE_TALL_GRAMEN,
				new PlacedFeature(configuredFeatures.getOrThrow(AMGrassFeatures.DOUBLE_TALL_GRAMEN),
						List.of(NoiseThresholdCountPlacement.of(-0.8D, 0, 7), RarityFilter.onAverageOnceEvery(5),
								InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome())));

		PlacementModifier placementmodifier = SurfaceWaterDepthFilter.forMaxDepth(0);
		context.register(ALIEN_TREE,
				new PlacedFeature(configuredFeatures.getOrThrow(AMTreeFeatures.ALIEN_TREE),
						List.of(PlacementUtils.countExtra(0, 0.05F, 1), InSquarePlacement.spread(), placementmodifier,
								PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
								BlockPredicateFilter.forPredicate(BlockPredicate
										.wouldSurvive(AMBlocks.LIGNUM_SAPLING.get().defaultBlockState(), BlockPos.ZERO)),
								BiomeFilter.biome())));
		context.register(THIN_TALL_ALIEN_TREE,
				new PlacedFeature(configuredFeatures.getOrThrow(AMTreeFeatures.THIN_TALL_ALIEN_TREE),
						List.of(PlacementUtils.countExtra(15, 0.2F, 1), InSquarePlacement.spread(), placementmodifier,
								PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
								BlockPredicateFilter.forPredicate(BlockPredicate
										.wouldSurvive(AMBlocks.LIGNUM_SAPLING.get().defaultBlockState(), BlockPos.ZERO)),
								BiomeFilter.biome())));

		/*
		 * context.register(ALIEN_TREE, new
		 * PlacedFeature(configuredFeatures.getOrThrow(Configured.ALIEN_TREE),
		 * VegetationPlacements.treePlacement(PlacementUtils.countExtra(10, 0.1F, 1))));
		 */
	}

	private static ResourceKey<PlacedFeature> createKey(String name) {
		return ResourceKey.create(Registries.PLACED_FEATURE, AlienMod.location(name));

	}
}
