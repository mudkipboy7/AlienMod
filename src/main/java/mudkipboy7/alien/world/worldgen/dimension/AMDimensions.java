package mudkipboy7.alien.world.worldgen.dimension;

import java.util.List;
import java.util.OptionalLong;

import com.mojang.serialization.Codec;

import mudkipboy7.alien.AlienMod;
import mudkipboy7.alien.world.block.AMBlocks;
import mudkipboy7.alien.world.worldgen.biome.AMBiomes;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.FixedBiomeSource;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.NoiseSettings;

public class AMDimensions {
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
	

	public static void bootstrapDimensionType(BootstapContext<DimensionType> context) {
		context.register(ALIENDIM_TYPE, new DimensionType(OptionalLong.empty(), // fixedTime
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
				new DimensionType.MonsterSettings(false, false, ConstantInt.of(0), 0) // monsterSettings
		));
	}

	public static void bootstrapLevelStem(BootstapContext<LevelStem> context) {
		HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);
		HolderGetter<DimensionType> dimTypes = context.lookup(Registries.DIMENSION_TYPE);
		//HolderGetter<Codec<? extends ChunkGenerator>> chunkGen = context.lookup(Registries.CHUNK_GENERATOR);
		HolderGetter<NoiseGeneratorSettings> noiseGenSettings = context.lookup(Registries.NOISE_SETTINGS);

		FixedBiomeSource biomeSource = new FixedBiomeSource(biomes.getOrThrow(AMBiomes.ALIEN_PLAINS));
		AlienChunkGenerator wrappedChunkGenerator = new AlienChunkGenerator(biomeSource,
				noiseGenSettings.getOrThrow(ALIEN_DIM_NOISE_SETTINGS));

		LevelStem levelStem = new LevelStem(dimTypes.getOrThrow(AMDimensions.ALIENDIM_TYPE), wrappedChunkGenerator);
		context.register(ALIENDIM_LEVEL_STEM, levelStem);
	}

	public static void bootstrapNoiseGen(BootstapContext<NoiseGeneratorSettings> context) {
		context.register(ALIEN_DIM_NOISE_SETTINGS,
				// The Actual settings
				new NoiseGeneratorSettings(NoiseSettings.create(-64, 384, 1, 2),
						AMBlocks.ALIEN_STONE.get().defaultBlockState(),
						AMBlocks.AMMONIA_LIQUID_BLOCK.get().defaultBlockState(),
						AlienDimNoiseRouter.alienDimNoiseRouter(context.lookup(Registries.DENSITY_FUNCTION),
								context.lookup(Registries.NOISE)),
						AlienDimSurfaceRules.alienDim(), List.of(), 48, // 63
						false, true, true, true));
	}
}
