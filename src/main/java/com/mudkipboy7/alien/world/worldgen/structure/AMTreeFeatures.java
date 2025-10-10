package com.mudkipboy7.alien.world.worldgen.structure;

import com.mudkipboy7.alien.AlienMod;
import com.mudkipboy7.alien.world.block.AMBlocks;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;

public class AMTreeFeatures {
	public static final ResourceKey<ConfiguredFeature<?, ?>> THIN_ALIEN_TREE = createKey("tall_lignum_tree");
	public static final ResourceKey<ConfiguredFeature<?, ?>> THIN_TALL_ALIEN_TREE = createKey("thin_tall_lignum_tree");

	private static TreeConfiguration.TreeConfigurationBuilder createStraightBlobTree(Block log, Block leaves,
			int baseHeight, int heightRandA, int heightRandB, int leavesHeight, int leavesOffsett, int leavesRadius) {
		return new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(log),
				new StraightTrunkPlacer(baseHeight, heightRandA, heightRandB), BlockStateProvider.simple(leaves),
				new BlobFoliagePlacer(ConstantInt.of(leavesRadius), ConstantInt.of(leavesOffsett), leavesHeight),
				new TwoLayersFeatureSize(1, 0, 1));
	}

	private static TreeConfiguration.TreeConfigurationBuilder createBasicTree() {
		return createStraightBlobTree(AMBlocks.LIGNUM_LOG.get(), AMBlocks.LIGNUM_LEAVES.get(), 4, 2, 0, 3, 0, 2)
				.ignoreVines().dirt(BlockStateProvider.simple(AMBlocks.ALGUSSOIL.get()));
	}

	public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
		FeatureUtils.register(context, THIN_ALIEN_TREE, Feature.TREE,
				createStraightBlobTree(AMBlocks.LIGNUM_LOG.get(), AMBlocks.LIGNUM_LEAVES.get(), 8, 6, 10, 3, 1, 2)
						.ignoreVines().dirt(BlockStateProvider.simple(AMBlocks.ALGUSSOIL.get())).build());

		FeatureUtils.register(context, THIN_TALL_ALIEN_TREE, Feature.TREE,
				createStraightBlobTree(AMBlocks.THIN_LIGNUM_LOG.get(), AMBlocks.LIGNUM_LEAVES.get(), 5, 2, 10, 2, 1, 2)
						.ignoreVines().dirt(BlockStateProvider.simple(AMBlocks.GRAMEN_BLOCK.get())).build());
	}

	private static ResourceKey<ConfiguredFeature<?, ?>> createKey(String name) {
		return ResourceKey.create(Registries.CONFIGURED_FEATURE, AlienMod.location(name));

	}
}
