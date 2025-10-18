package com.mudkipboy7.alien.world.worldgen.biome;

import com.mudkipboy7.alien.AlienMod;
import com.mudkipboy7.alien.data.tags.AMBlockTags;
import com.mudkipboy7.alien.world.worldgen.carvers.AMCarvers;
import com.mudkipboy7.alien.world.worldgen.carvers.CraterCarverConfiguration;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.ConstantFloat;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MultiNoiseBiomeSourceParameterList;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.carver.CarverDebugSettings;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.heightproviders.UniformHeight;

public class AMBiomeParameterLists {
	public static final ResourceKey<MultiNoiseBiomeSourceParameterList> ALIEN_BIOMES = createKey("alien_biomes");
	public static void bootstrap(BootstapContext<MultiNoiseBiomeSourceParameterList> context) {
		HolderGetter<Biome> lookup = context.lookup(Registries.BIOME);

	}

	private static ResourceKey<MultiNoiseBiomeSourceParameterList> createKey(String name) {
		return ResourceKey.create(Registries.MULTI_NOISE_BIOME_SOURCE_PARAMETER_LIST, AlienMod.location(name));

	}
}
