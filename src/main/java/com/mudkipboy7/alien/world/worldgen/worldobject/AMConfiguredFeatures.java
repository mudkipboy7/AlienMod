package com.mudkipboy7.alien.world.worldgen.worldobject;

import java.util.List;

import com.mudkipboy7.alien.AlienMod;
import com.mudkipboy7.alien.world.block.AMBlocks;
import com.mudkipboy7.alien.world.block.flora.ThinAlienLog;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;

public class AMConfiguredFeatures {
	// Grass
	public static final ResourceKey<ConfiguredFeature<?, ?>> TALL_GRAMEN = createKey("tall_gramen");
	public static final ResourceKey<ConfiguredFeature<?, ?>> DOUBLE_TALL_GRAMEN = createKey("double_tall_gramen");
	// Trees
	public static final ResourceKey<ConfiguredFeature<?, ?>> THIN_ALIEN_TREE = createKey("tall_lignum_tree");
	public static final ResourceKey<ConfiguredFeature<?, ?>> THIN_TALL_ALIEN_TREE = createKey("thin_tall_lignum_tree");

	// Objects
	public static final ResourceKey<ConfiguredFeature<?, ?>> JOVIAN_SMALL_CLOUD = createKey("jovian_small_cloud");

	public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
		// context.register(context, ALIEN_GRASS, new
		// ConfiguredFeature<>(Feature.FLOWER,
		// new
		// SimpleBlockConfiguration(BlockStateProvider.simple(AMBlocks.ALIEN_GRASS.get()));
		context.register(TALL_GRAMEN, new ConfiguredFeature<>(Feature.RANDOM_PATCH,
				grassPatch(BlockStateProvider.simple(AMBlocks.TALL_GRAMEN.get()), 32)));

		context.register(DOUBLE_TALL_GRAMEN,
				new ConfiguredFeature<>(Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(
						Feature.SIMPLE_BLOCK,
						new SimpleBlockConfiguration(BlockStateProvider.simple(AMBlocks.DOUBLE_TALL_GRAMEN.get())))));
		FeatureUtils
				.register(context, THIN_ALIEN_TREE, Feature.TREE,
						createStraightBlobTree(AMBlocks.LIGNUM_LOG.get().defaultBlockState(),
								AMBlocks.LIGNUM_LEAVES.get().defaultBlockState(), 8, 6, 10, 3, 1, 2).ignoreVines()
								.build());

		FeatureUtils.register(context, THIN_TALL_ALIEN_TREE, Feature.TREE,
				// This fixes a stupid glitch where it generated it with waterlogged
				createStraightBlobTree(
						AMBlocks.THIN_LIGNUM_LOG.get().defaultBlockState().setValue(ThinAlienLog.WATERLOGGED, false),
						AMBlocks.LIGNUM_LEAVES.get().defaultBlockState(), 5, 2, 10, 2, 1, 2).ignoreVines().build());
		context.register(JOVIAN_SMALL_CLOUD,
				new ConfiguredFeature<>(Feature.RANDOM_PATCH,
						FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK,
								new SimpleBlockConfiguration(BlockStateProvider.simple(AMBlocks.HARDENED_CLOUD.get())),
								List.of(), 6)));
	}

	private static ResourceKey<ConfiguredFeature<?, ?>> createKey(String name) {
		return ResourceKey.create(Registries.CONFIGURED_FEATURE, AlienMod.location(name));

	}

	// Vanilla copy
	private static RandomPatchConfiguration grassPatch(BlockStateProvider state, int tries) {
		return FeatureUtils.simpleRandomPatchConfiguration(tries,
				PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(state)));
	}

	private static TreeConfiguration.TreeConfigurationBuilder createStraightBlobTree(BlockState log, BlockState leaves,
			int baseHeight, int heightRandA, int heightRandB, int leavesHeight, int leavesOffsett, int leavesRadius) {
		return new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(log),
				new StraightTrunkPlacer(baseHeight, heightRandA, heightRandB), BlockStateProvider.simple(leaves),
				new BlobFoliagePlacer(ConstantInt.of(leavesRadius), ConstantInt.of(leavesOffsett), leavesHeight),
				new TwoLayersFeatureSize(1, 0, 1)).dirt(BlockStateProvider.simple(AMBlocks.GRAMEN_BLOCK.get()));
	}

}
