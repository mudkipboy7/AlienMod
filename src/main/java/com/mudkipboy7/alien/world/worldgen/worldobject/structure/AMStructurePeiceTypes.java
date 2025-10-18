package com.mudkipboy7.alien.world.worldgen.worldobject.structure;

import com.mudkipboy7.alien.AlienMod;
import static com.mudkipboy7.alien.AMRegistry.STRUCTURE_PIECE_TYPES;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraft.world.level.levelgen.structure.structures.MineshaftPieces;
import net.minecraftforge.registries.RegistryObject;

public class AMStructurePeiceTypes {
	public static final RegistryObject<StructurePieceType> JOVIAN_ARENA = STRUCTURE_PIECE_TYPES.register("jovian_arena",
			() -> JovianArenaStructure.JovianArenaPeices::new);

}
