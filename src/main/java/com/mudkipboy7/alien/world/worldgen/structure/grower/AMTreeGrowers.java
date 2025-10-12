package com.mudkipboy7.alien.world.worldgen.structure.grower;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.mudkipboy7.alien.world.worldgen.structure.AMTreeFeatures;

import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class AMTreeGrowers {
	public static AbstractTreeGrower basicAlienTreeGrower = new AbstractTreeGrower() {
		@Override
		protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource randomSource, boolean hive) {
			List<ResourceKey<ConfiguredFeature<?, ?>>> treesList = new ArrayList<ResourceKey<ConfiguredFeature<?, ?>>>();
			treesList.add(AMTreeFeatures.THIN_ALIEN_TREE);
			treesList.add(AMTreeFeatures.THIN_TALL_ALIEN_TREE);
			Random random = new Random();

			return treesList.get(random.nextInt(treesList.size()));
		}
	};
}
