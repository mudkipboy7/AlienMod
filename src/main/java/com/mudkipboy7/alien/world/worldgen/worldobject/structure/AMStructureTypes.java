package com.mudkipboy7.alien.world.worldgen.worldobject.structure;

import com.mudkipboy7.alien.world.block.AMFluids;
import static com.mudkipboy7.alien.AMRegistry.STRUCTURE_TYPES;
import com.mudkipboy7.alien.world.block.fluid.AmmoniaLiquidBlock;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraftforge.registries.RegistryObject;

public class AMStructureTypes {
	
	public static final RegistryObject<StructureType<?>> JOVIAN_ARENA = STRUCTURE_TYPES.register("jovian_arena",
			() -> () -> Structure.simpleCodec(JovianArenaStructure::new));
}
