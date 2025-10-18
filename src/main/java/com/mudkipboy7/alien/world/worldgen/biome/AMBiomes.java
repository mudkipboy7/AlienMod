package com.mudkipboy7.alien.world.worldgen.biome;

import java.util.function.Function;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.mudkipboy7.alien.AlienMod;
import com.mudkipboy7.alien.sound.AMMusics;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber()
public class AMBiomes extends AMBiomeMaker {

	// AlienDim
	public static final ResourceKey<Biome> TOWERING_FOREST = makeKey("towering_forrest");
	public static final ResourceKey<Biome> BARREN_CRATERSCAPE = makeKey("barren_craterscape");
	public static final ResourceKey<Biome> JOVIAN_PLANET_BIOME = makeKey("jovian_planet");

	public static void bootstrapBiomes(BootstapContext<Biome> context) {
		HolderGetter<PlacedFeature> feature = context.lookup(Registries.PLACED_FEATURE);
		HolderGetter<ConfiguredWorldCarver<?>> carver = context.lookup(Registries.CONFIGURED_CARVER);
		// AlienDim
		context.register(TOWERING_FOREST,
				makeBiome(true, 1.0F, 1.0F, alienRainforest(feature, carver), defaultSpawnSettings(), basicEffects()));
		context.register(BARREN_CRATERSCAPE,
				makeBiome(true, 0.1F, 3.0F, barrenCraterscape(feature, carver), defaultSpawnSettings(),
						new BiomeSpecialEffects.Builder().waterColor(0xFFFFFF).waterFogColor(0xFFFFFF)
								.skyColor(0xCBA596).grassColorOverride(0xFFFFFF).foliageColorOverride(0xFFFFFF)
								.fogColor(0xBB9C8F).backgroundMusic(AMMusics.ALIEN_MUSIC)));

		context.register(JOVIAN_PLANET_BIOME, makeBiome(true, 1.0F, 1.0F, jovianPlanetBiome(feature, carver),
				new MobSpawnSettings.Builder(), jovianEffects()));
	}

	private static ResourceKey<Biome> makeKey(String id, String name) {
		return ResourceKey.create(Registries.BIOME, new ResourceLocation(id, name));
	}

	private static ResourceKey<Biome> makeKey(String name) {
		return makeKey(AlienMod.MODID, name);
	}

}
