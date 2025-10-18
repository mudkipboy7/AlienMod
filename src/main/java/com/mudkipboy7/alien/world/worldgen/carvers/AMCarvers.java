package com.mudkipboy7.alien.world.worldgen.carvers;

import static com.mudkipboy7.alien.AMRegistry.CARVERS;

import com.mudkipboy7.alien.world.block.misc.TestBlock;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.levelgen.carver.WorldCarver;
import net.minecraftforge.registries.RegistryObject;

public class AMCarvers {
	public static final RegistryObject<WorldCarver<CraterCarverConfiguration>> CRATER = CARVERS.register("crater",
			() -> new CraterCarver(CraterCarverConfiguration.CODEC));
}
