package com.mudkipboy7.alien.world.worldgen.worldobject.structure;

import java.util.Map;

import com.mudkipboy7.alien.AlienMod;
import com.mudkipboy7.alien.data.tags.AMBiomeTags;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSpawnOverride;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.structures.EndCityStructure;
import net.minecraft.world.level.levelgen.structure.structures.MineshaftStructure;

public class AMStructures {
	public static final ResourceKey<Structure> JOVIAN_PLANET_ARENA = createKey("jovian_arena");

	public static void bootstrap(BootstapContext<Structure> context) {
		HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);
		HolderGetter<StructureTemplatePool> structures = context.lookup(Registries.TEMPLATE_POOL);

		context.register(JOVIAN_PLANET_ARENA,
				new JovianArenaStructure(structure(biomes.getOrThrow(AMBiomeTags.HAS_JOVIAN_ARENA), TerrainAdjustment.NONE)));
	}

	private static ResourceKey<Structure> createKey(String name) {
		return ResourceKey.create(Registries.STRUCTURE, AlienMod.location(name));
	}

	private static Structure.StructureSettings structure(HolderSet<Biome> pBiomes,
			Map<MobCategory, StructureSpawnOverride> pSpawnOverrides, GenerationStep.Decoration pStep,
			TerrainAdjustment pTerrainAdaptation) {
		return new Structure.StructureSettings(pBiomes, pSpawnOverrides, pStep, pTerrainAdaptation);
	}

	private static Structure.StructureSettings structure(HolderSet<Biome> pBiomes, GenerationStep.Decoration pStep,
			TerrainAdjustment pTerrainAdaptation) {
		return structure(pBiomes, Map.of(), pStep, pTerrainAdaptation);
	}

	private static Structure.StructureSettings structure(HolderSet<Biome> pBiomes,
			TerrainAdjustment pTerrainAdaptation) {
		return structure(pBiomes, Map.of(), GenerationStep.Decoration.SURFACE_STRUCTURES, pTerrainAdaptation);
	}
}
