package com.mudkipboy7.alien.world.worldgen;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.Holder;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Aquifer;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;

public class AlienChunkGenerator extends NoiseBasedChunkGenerator {
	public static final Codec<AlienChunkGenerator> ALIENDIM_CODEC = RecordCodecBuilder.create((idk) -> {
		return idk.group(BiomeSource.CODEC.fieldOf("biome_source").forGetter((source) -> {
			return source.biomeSource;
		}), NoiseGeneratorSettings.CODEC.fieldOf("settings").forGetter((settings) -> {
			return settings.generatorSettings();
		})).apply(idk, idk.stable(AlienChunkGenerator::new));
	});

	public AlienChunkGenerator(BiomeSource biomeSource, Holder<NoiseGeneratorSettings> noiseGenSettings) {
		super(biomeSource, noiseGenSettings);
		super.globalFluidPicker = Suppliers.memoize(() -> {
			return createFluidPicker(noiseGenSettings.value());
		});
	}


	@Override
	protected Codec<? extends ChunkGenerator> codec() {
		return CODEC;
	}

	public static Aquifer.FluidPicker createFluidPicker(NoiseGeneratorSettings pSettings) {
		int seaLevel = pSettings.seaLevel();
		int lavaLevel = -54; // -54 by default
		Aquifer.FluidStatus lava = new Aquifer.FluidStatus(lavaLevel, Blocks.LAVA.defaultBlockState());
		Aquifer.FluidStatus water = new Aquifer.FluidStatus(seaLevel, pSettings.defaultFluid());
		// Aquifer.FluidStatus air = new Aquifer.FluidStatus(DimensionType.MIN_Y * 2,
		// Blocks.AIR.defaultBlockState());
		return (x, y, z) -> {
			return y < Math.min(lavaLevel, seaLevel) ? lava : water;
		};
	}
}