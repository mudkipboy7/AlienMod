package com.mudkipboy7.alien.world.worldgen.worldobject;

import java.util.List;

import com.mudkipboy7.alien.AlienMod;
import com.mudkipboy7.alien.world.block.AMBlocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.EndFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.BlockPredicateFilter;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.NoiseThresholdCountPlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import net.minecraft.world.level.levelgen.placement.SurfaceWaterDepthFilter;

public class AMPlacedFeatures {

	// Grasses
	public static final ResourceKey<PlacedFeature> TALL_GRAMEN = createKey("tall_gramen");
	public static final ResourceKey<PlacedFeature> DOUBLE_TALL_GRAMEN = createKey("double_tall_gramen");

	// Trees
	public static final ResourceKey<PlacedFeature> TALL_ALIEN_TREE = createKey("tall_lignum_tree");
	public static final ResourceKey<PlacedFeature> THIN_TALL_ALIEN_TREE = createKey("thin_tall_lignum_tree");

	// Misc
	public static final ResourceKey<PlacedFeature> JOVIAN_SMALL_CLOUD = createKey("jovian_small_cloud");

	public static void bootstrap(BootstapContext<PlacedFeature> context) {
		HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);
		context.register(TALL_GRAMEN,
				new PlacedFeature(configuredFeatures.getOrThrow(AMConfiguredFeatures.TALL_GRAMEN),
						List.of(NoiseThresholdCountPlacement.of(-0.8D, 0, 7), InSquarePlacement.spread(),
								PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome())));

		context.register(DOUBLE_TALL_GRAMEN,
				new PlacedFeature(configuredFeatures.getOrThrow(AMConfiguredFeatures.DOUBLE_TALL_GRAMEN),
						List.of(NoiseThresholdCountPlacement.of(-0.8D, 0, 7), RarityFilter.onAverageOnceEvery(5),
								InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome())));

		PlacementModifier placementmodifier = SurfaceWaterDepthFilter.forMaxDepth(0);
		context.register(TALL_ALIEN_TREE,
				new PlacedFeature(configuredFeatures.getOrThrow(AMConfiguredFeatures.THIN_ALIEN_TREE),
						List.of(PlacementUtils.countExtra(1, 0.1F, 1), InSquarePlacement.spread(), placementmodifier,
								PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
								BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(
										AMBlocks.LIGNUM_SAPLING.get().defaultBlockState(), BlockPos.ZERO)),
								BiomeFilter.biome())));
		context.register(THIN_TALL_ALIEN_TREE,
				new PlacedFeature(configuredFeatures.getOrThrow(AMConfiguredFeatures.THIN_TALL_ALIEN_TREE),
						List.of(PlacementUtils.countExtra(15, 0.2F, 1), InSquarePlacement.spread(), placementmodifier,
								PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
								BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(
										AMBlocks.LIGNUM_SAPLING.get().defaultBlockState(), BlockPos.ZERO)),
								BiomeFilter.biome())));
		PlacementUtils.register(context, JOVIAN_SMALL_CLOUD,
				configuredFeatures.getOrThrow(AMConfiguredFeatures.JOVIAN_SMALL_CLOUD),
				RarityFilter.onAverageOnceEvery(14), PlacementUtils.countExtra(1, 0.15F, 1), InSquarePlacement.spread(),
				HeightRangePlacement.uniform(VerticalAnchor.absolute(5), VerticalAnchor.absolute(60)),
				BiomeFilter.biome());

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
