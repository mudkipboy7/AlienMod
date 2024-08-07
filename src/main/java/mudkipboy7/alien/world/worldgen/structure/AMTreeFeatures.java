package mudkipboy7.alien.world.worldgen.structure;

import mudkipboy7.alien.AlienMod;
import mudkipboy7.alien.world.block.AMBlocks;
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
	public static final ResourceKey<ConfiguredFeature<?, ?>> ALIEN_TREE = createKey("lignum_tree");

	private static TreeConfiguration.TreeConfigurationBuilder createStraightBlobTree(Block log, Block leaves,
			int baseHeight, int heightRandA, int heightRandB, int leavesRadius) {
		return new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(log),
				new StraightTrunkPlacer(baseHeight, heightRandA, heightRandB), BlockStateProvider.simple(leaves),
				new BlobFoliagePlacer(ConstantInt.of(leavesRadius), ConstantInt.of(0), 3),
				new TwoLayersFeatureSize(1, 0, 1));
	}

	private static TreeConfiguration.TreeConfigurationBuilder createBasicTree() {
		return createStraightBlobTree(AMBlocks.ALIEN_LOG.get(), AMBlocks.ALIEN_LEAVES.get(), 4, 2, 0, 2).ignoreVines()
				.dirt(BlockStateProvider.simple(AMBlocks.ALIEN_DIRT.get()));
	}

	public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
		FeatureUtils.register(context, ALIEN_TREE, Feature.TREE, createBasicTree().build());
	}

	private static ResourceKey<ConfiguredFeature<?, ?>> createKey(String name) {
		return ResourceKey.create(Registries.CONFIGURED_FEATURE, AlienMod.location(name));

	}
}
