package com.mudkipboy7.alien.world.worldgen.biome;

import java.util.stream.Stream;

import com.mojang.serialization.Codec;

import net.minecraft.core.Holder;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.biome.Climate.Sampler;

public class AlienDimBiomeSource extends BiomeSource {

	public AlienDimBiomeSource() {

	}

	@Override
	protected Codec<? extends BiomeSource> codec() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Stream<Holder<Biome>> collectPossibleBiomes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Holder<Biome> getNoiseBiome(int p_204238_, int p_204239_, int p_204240_, Sampler p_204241_) {
		// TODO Auto-generated method stub
		return null;
	}

}
