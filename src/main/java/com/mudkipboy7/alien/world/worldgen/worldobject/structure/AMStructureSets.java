package com.mudkipboy7.alien.world.worldgen.worldobject.structure;

import com.mudkipboy7.alien.AlienMod;
import com.mudkipboy7.alien.data.tags.AMBiomeTags;
import com.mudkipboy7.alien.world.worldgen.biome.AMBiomes;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.structure.BuiltinStructures;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.placement.ConcentricRingsStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

public class AMStructureSets {
	public static final ResourceKey<StructureSet> JOVIAN_PLANET_ARENA = createKey("jovian_arena");
	public static final ResourceKey<StructureSet> OVERWORLD_DUNGEON = createKey("overworld_dungeon");

	public static void bootstrap(BootstapContext<StructureSet> context) {
		HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);
		HolderGetter<Structure> structures = context.lookup(Registries.STRUCTURE);
		// There are 100 so I can lower the chances of that glitch where only part of it
		// generates
		context.register(JOVIAN_PLANET_ARENA,
				new StructureSet(structures.getOrThrow(AMStructures.JOVIAN_PLANET_ARENA),
						new ConcentricRingsStructurePlacement(0, 0, 20,
								HolderSet.direct(biomes.getOrThrow(AMBiomes.JOVIAN_PLANET_BIOME)))));
		context.register(OVERWORLD_DUNGEON, new StructureSet(structures.getOrThrow(AMStructures.OVERWORLD_DUNGEON),
				new RandomSpreadStructurePlacement(32, 8, RandomSpreadType.LINEAR, 14357620)));

	}

	private static ResourceKey<StructureSet> createKey(String name) {
		return ResourceKey.create(Registries.STRUCTURE_SET, AlienMod.location(name));
	}
}
