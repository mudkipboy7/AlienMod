package com.mudkipboy7.alien.world.worldgen.biome;

import com.mudkipboy7.alien.AlienMod;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber()
public class AMBiomes extends AMBiomeMaker {
	public static final ResourceKey<Biome> ALIEN_RAINFOREST = makeKey("alien_rainforest");

	public static void bootstrapBiomes(BootstapContext<Biome> context) {
		HolderGetter<PlacedFeature> feature = context.lookup(Registries.PLACED_FEATURE);
		HolderGetter<ConfiguredWorldCarver<?>> carver = context.lookup(Registries.CONFIGURED_CARVER);
		context.register(ALIEN_RAINFOREST, makeBiome(true, 1.0F, 1.0F, alienPlainsGenSettings(feature, carver),
				defaultSpawnSettings(), basicEffects()));
	}

	private static ResourceKey<Biome> makeKey(String id, String name) {
		return ResourceKey.create(Registries.BIOME, new ResourceLocation(id, name));
	}

	private static ResourceKey<Biome> makeKey(String name) {
		return makeKey(AlienMod.MODID, name);
	}
}
