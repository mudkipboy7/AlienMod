package mudkipboy7.alien.world.worldgen.biome;

import mudkipboy7.alien.world.entity.AMEntities;
import mudkipboy7.alien.world.worldgen.structure.placement.AMVegetationPlacers;
import net.minecraft.core.HolderGetter;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

class AMBiomeMaker {
	public static BiomeGenerationSettings.Builder alienPlainsGenSettings(HolderGetter<PlacedFeature> feature,
			HolderGetter<ConfiguredWorldCarver<?>> carver) {
		BiomeGenerationSettings.Builder genSettings = new BiomeGenerationSettings.Builder(feature, carver);
		genSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AMVegetationPlacers.ALIEN_GRASS);
		genSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AMVegetationPlacers.ALIEN_DOUBLE_GRASS);
		genSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AMVegetationPlacers.ALIEN_TREE);
		return genSettings;

	}

	public static MobSpawnSettings.Builder defaultSpawnSettings() {
		MobSpawnSettings.Builder builder = new MobSpawnSettings.Builder();
		builder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.ENDERMAN, 5, 1, 1));
		builder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(AMEntities.ALIEN_ZOMBIE.get(), 70, 1, 4));
		return builder;
	}

	public static BiomeSpecialEffects.Builder basicEffects() {
		BiomeSpecialEffects.Builder effectSettings = new BiomeSpecialEffects.Builder();
		effectSettings.waterColor(0xFFFFFF).waterFogColor(0xFFFFFF).skyColor(0x3d0018).grassColorOverride(0xFFFFFF)
				.foliageColorOverride(0xFFFFFF).fogColor(0x24000e);
		return effectSettings;

		// .ambientParticle(AmbientParticleSettings.)
		// .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
	}

	/**
	 * Method that makes a biome.
	 * 
	 * @param context            BootstapContext
	 * @param hasPrecipitation   Boolean
	 * @param downfall           Float
	 * @param temperature        Float
	 * @param generationSettings BiomeGenerationSettings
	 * @param mobSpawnSettings   MobSpawnSettings
	 * @param specialEffects     BiomeSpecialEffects()
	 * @return new Biome
	 */
	public static Biome makeBiome(Boolean hasPrecipitation, Float downfall, Float temperature,
			BiomeGenerationSettings.Builder generationSettings, MobSpawnSettings.Builder mobSpawnSettings,
			BiomeSpecialEffects.Builder specialEffects) {
		return new Biome.BiomeBuilder().hasPrecipitation(hasPrecipitation).downfall(downfall).temperature(temperature)
				.generationSettings(generationSettings.build()).mobSpawnSettings(mobSpawnSettings.build())
				.specialEffects(specialEffects.build()).build();
	}
}
