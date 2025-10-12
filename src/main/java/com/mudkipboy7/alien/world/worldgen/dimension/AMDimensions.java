package com.mudkipboy7.alien.world.worldgen.dimension;

import java.util.List;
import java.util.OptionalLong;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.mojang.serialization.Codec;
import com.mudkipboy7.alien.AlienMod;
import com.mudkipboy7.alien.world.block.AMBlocks;
import com.mudkipboy7.alien.world.worldgen.AlienChunkGenerator;
import com.mudkipboy7.alien.world.worldgen.AlienDimNoiseRouter;
import com.mudkipboy7.alien.world.worldgen.AlienDimSurfaceRules;
import com.mudkipboy7.alien.world.worldgen.biome.AMBiomes;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.SurfaceRuleData;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.FixedBiomeSource;
import net.minecraft.world.level.biome.MultiNoiseBiomeSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraft.world.level.levelgen.DensityFunctions;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.NoiseRouter;
import net.minecraft.world.level.levelgen.NoiseRouterData;
import net.minecraft.world.level.levelgen.NoiseSettings;
import net.minecraft.world.level.levelgen.SurfaceRules;

public class AMDimensions {
	/*
	 * AlienDim
	 */
	public static final ResourceLocation ALIENDIM_ID = new ResourceLocation(AlienMod.MODID, "alien_planet");
	public static final ResourceKey<Level> ALIENDIM_LEVEL = ResourceKey.create(Registries.DIMENSION, ALIENDIM_ID);
	public static final ResourceKey<LevelStem> ALIENDIM_LEVEL_STEM = ResourceKey.create(Registries.LEVEL_STEM,
			ALIENDIM_ID);
	public static final ResourceKey<DimensionType> ALIENDIM_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE,
			ALIENDIM_ID);
	public static final ResourceKey<NoiseGeneratorSettings> ALIEN_DIM_NOISE_SETTINGS = ResourceKey
			.create(Registries.NOISE_SETTINGS, new ResourceLocation(AlienMod.MODID, "alien_planet"));
	public static final ResourceKey<Codec<? extends ChunkGenerator>> ALIEN_DIM_CHUNK_GENERATOR = ResourceKey
			.create(Registries.CHUNK_GENERATOR, new ResourceLocation(AlienMod.MODID, "alien_chunk_generator"));

	/*
	 * JovianDim
	 */
	public static final ResourceLocation JOVIANDIM_ID = new ResourceLocation(AlienMod.MODID, "jovian_planet");
	public static final ResourceKey<Level> JOVIANDIM_LEVEL = ResourceKey.create(Registries.DIMENSION, JOVIANDIM_ID);
	public static final ResourceKey<LevelStem> JOVIANDIM_LEVEL_STEM = ResourceKey.create(Registries.LEVEL_STEM,
			JOVIANDIM_ID);
	public static final ResourceKey<DimensionType> JOVIANDIM_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE,
			JOVIANDIM_ID);
	public static final ResourceKey<NoiseGeneratorSettings> JOVIANDIM_NOISE_SETTINGS = ResourceKey
			.create(Registries.NOISE_SETTINGS, new ResourceLocation(AlienMod.MODID, "jovian_planet"));

	/*
	 * This is used to give access to getDayTime
	 */
	private static DimensionType alienDimDimentionType = new DimensionType(OptionalLong.empty(), // fixedTime
			true, // hasSkyLight
			false, // hasCeiling
			false, // ultraWarm
			true, // natural
			1, // coordinateScale
			true, // bedWorks
			false, // respawnAnchorWorks
			-64, // minY
			384, // height
			384, // logicalHeight
			BlockTags.INFINIBURN_OVERWORLD, // infiniburn
			ALIENDIM_ID, // effectsLocation
			0, // ambientLight
			new DimensionType.MonsterSettings(false, false, ConstantInt.of(0), 0)); // monsterSettings

	private static DimensionType jovianDimDimentionType = new DimensionType(OptionalLong.of(6000), // fixedTime
			true, // hasSkyLight
			false, // hasCeiling
			false, // ultraWarm
			true, // natural
			1, // coordinateScale
			false, // bedWorks
			false, // respawnAnchorWorks
			0, // minY
			128, // height
			128, // logicalHeight
			BlockTags.INFINIBURN_OVERWORLD, // infiniburn
			JOVIANDIM_ID, // effectsLocation
			0, // ambientLight
			new DimensionType.MonsterSettings(false, false, ConstantInt.of(0), 0)); // monsterSettings

	public static void bootstrapDimensionType(BootstapContext<DimensionType> context) {
		context.register(ALIENDIM_TYPE, alienDimDimentionType);
		context.register(JOVIANDIM_TYPE, jovianDimDimentionType);

	}

	public static void bootstrapLevelStem(BootstapContext<LevelStem> context) {
		HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);
		HolderGetter<DimensionType> dimTypes = context.lookup(Registries.DIMENSION_TYPE);
		HolderGetter<NoiseGeneratorSettings> noiseGenSettings = context.lookup(Registries.NOISE_SETTINGS);

		// AlienDim
		FixedBiomeSource alienBiomeSource = new FixedBiomeSource(biomes.getOrThrow(AMBiomes.TOWERING_FOREST));
		AlienChunkGenerator alienChunkGen = new AlienChunkGenerator(alienBiomeSource,
				noiseGenSettings.getOrThrow(ALIEN_DIM_NOISE_SETTINGS));
		LevelStem alienLevelStem = new LevelStem(dimTypes.getOrThrow(AMDimensions.ALIENDIM_TYPE), alienChunkGen);
		context.register(ALIENDIM_LEVEL_STEM, alienLevelStem);

		// AlienDim
		FixedBiomeSource jovianBiomeSource = new FixedBiomeSource(biomes.getOrThrow(AMBiomes.JOVIAN_PLANET_BIOME));
		AlienChunkGenerator jovianChunkGen = new AlienChunkGenerator(jovianBiomeSource,
				noiseGenSettings.getOrThrow(JOVIANDIM_NOISE_SETTINGS));
		LevelStem jovianLevelStem = new LevelStem(dimTypes.getOrThrow(AMDimensions.JOVIANDIM_TYPE), jovianChunkGen);
		context.register(JOVIANDIM_LEVEL_STEM, jovianLevelStem);
	}

	public static void bootstrapNoiseGen(BootstapContext<NoiseGeneratorSettings> context) {
		context.register(ALIEN_DIM_NOISE_SETTINGS,
				// The Actual settings
				new NoiseGeneratorSettings(NoiseSettings.create(-64, 384, 1, 2),
						AMBlocks.GELUSTONE.get().defaultBlockState(),
						AMBlocks.AMMONIA_LIQUID_BLOCK.get().defaultBlockState(),
						AlienDimNoiseRouter.alienDimNoiseRouter(context.lookup(Registries.DENSITY_FUNCTION),
								context.lookup(Registries.NOISE)),
						AlienDimSurfaceRules.alienDim(), List.of(), 48, // 63
						false, true, true, true));
		context.register(JOVIANDIM_NOISE_SETTINGS,
				// The Actual settings
				new NoiseGeneratorSettings(NoiseSettings.create(0, 128, 1, 2),
						Blocks.AIR.defaultBlockState(),
						Blocks.AIR.defaultBlockState(),
						new NoiseRouter(DensityFunctions.zero(), DensityFunctions.zero(), DensityFunctions.zero(),
								DensityFunctions.zero(), DensityFunctions.zero(), DensityFunctions.zero(),
								DensityFunctions.zero(), DensityFunctions.zero(), DensityFunctions.zero(),
								DensityFunctions.zero(), DensityFunctions.zero(), DensityFunctions.zero(),
								DensityFunctions.zero(), DensityFunctions.zero(), DensityFunctions.zero()),
						SurfaceRuleData.air(), List.of(), 0, // 63
						false, false, false, true));

	}
}
