package com.mudkipboy7.alien.world.worldgen.structure;

import com.mudkipboy7.alien.AlienMod;
import com.mudkipboy7.alien.world.block.AMBlocks;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public class AMGrassFeatures {

	public static final ResourceKey<ConfiguredFeature<?, ?>> TALL_GRAMEN = createKey("tall_gramen");
	public static final ResourceKey<ConfiguredFeature<?, ?>> DOUBLE_TALL_GRAMEN = createKey("double_tall_gramen");

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
	}

	private static ResourceKey<ConfiguredFeature<?, ?>> createKey(String name) {
		return ResourceKey.create(Registries.CONFIGURED_FEATURE, AlienMod.location(name));
	}

	// Vanilla copy
	private static RandomPatchConfiguration grassPatch(BlockStateProvider state, int tries) {
		return FeatureUtils.simpleRandomPatchConfiguration(tries,
				PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(state)));
	}

}
