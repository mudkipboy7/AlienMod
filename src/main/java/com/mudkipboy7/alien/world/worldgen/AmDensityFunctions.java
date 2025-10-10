package com.mudkipboy7.alien.world.worldgen;

import com.mudkipboy7.alien.AlienMod;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

@SuppressWarnings("unused")
public class AmDensityFunctions {


	private static String ALIENDIM_FLODER = "alien_planet/";
	//public static final ResourceKey<DensityFunction> ALIENDIM_RIDGES = makeKey(ALIENDIM_FLODER + "alien_planet_ridges");

	public static void bootstrap(BootstapContext<DensityFunction> context) {
		
	      HolderGetter<NormalNoise.NoiseParameters> noiseParameters = context.lookup(Registries.NOISE);
	      HolderGetter<DensityFunction> densityFunction = context.lookup(Registries.DENSITY_FUNCTION);
		
		// context.register(ALIENDIM_RIDGES,
		 //DensityFunctions.add(DensityFunctions.flatCache(DensityFunctions.flatCache(), null));
		

	}

	private static ResourceKey<DensityFunction> makeKey(String id, String name) {
		return ResourceKey.create(Registries.DENSITY_FUNCTION, new ResourceLocation(id, name));
	}

	private static ResourceKey<DensityFunction> makeKey(String name) {
		return makeKey(AlienMod.MODID, name);
	}
}
