package com.mudkipboy7.alien.world.worldgen.structure.grower;

import com.mudkipboy7.alien.world.worldgen.structure.AMTreeFeatures;

import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class AMTreeGrowers {
	public static AbstractTreeGrower basicAlienTreeGrower = new AbstractTreeGrower() {
		@Override
		protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource randomSource, boolean hive) {
			return AMTreeFeatures.THIN_ALIEN_TREE;
		}
	};
	
	public static AbstractTreeGrower thinTallAlienTreeGrower = new AbstractTreeGrower() {
		@Override
		protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource randomSource, boolean hive) {
			return AMTreeFeatures.THIN_TALL_ALIEN_TREE;
		}
	};

}
