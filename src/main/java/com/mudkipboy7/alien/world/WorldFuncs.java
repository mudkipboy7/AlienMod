package com.mudkipboy7.alien.world;

import com.mudkipboy7.alien.data.tags.AMBiomeTags;
import com.mudkipboy7.alien.world.worldgen.dimension.AMDimensions;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.biome.Biome;

public class WorldFuncs {

	/*
	 * These three checks for different biome types
	 */
	//@SafeVarargs
	public static boolean isInBiomeType(LevelAccessor level, BlockPos blockPos, TagKey<Biome>... biomeTags) {
		Holder<Biome> biome = level.getBiome(blockPos);
		for (int i = 0; i < biomeTags.length; i++) {
			// System.out.println(i);
			if (biome.containsTag(biomeTags[i])) {
				return true;
			}
		}
		return false;
	}

	public static boolean inUltracoldBiome(LevelAccessor level, BlockPos blockPos) {
		return isInBiomeType(level, blockPos, AMBiomeTags.ULTRACOLD);
	}

	public static boolean inUltracoldOrEndBiome(LevelAccessor level, BlockPos blockPos) {
		return isInBiomeType(level, blockPos, AMBiomeTags.ULTRACOLD, BiomeTags.IS_END);
	}

	public static boolean isInAlienDim(Entity entity) {
		return entity.level().dimension() == AMDimensions.ALIENDIM_LEVEL;
	}
}
