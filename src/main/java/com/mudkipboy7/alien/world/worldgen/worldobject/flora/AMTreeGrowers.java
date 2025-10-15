package com.mudkipboy7.alien.world.worldgen.worldobject.flora;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.mudkipboy7.alien.world.worldgen.worldobject.AMConfiguredFeatures;

import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class AMTreeGrowers {
	public static AbstractTreeGrower basicAlienTreeGrower = new AbstractTreeGrower() {
		@Override
		protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource randomSource, boolean hive) {
			List<ResourceKey<ConfiguredFeature<?, ?>>> treesList = new ArrayList<ResourceKey<ConfiguredFeature<?, ?>>>();
			treesList.add(AMConfiguredFeatures.THIN_ALIEN_TREE);
			treesList.add(AMConfiguredFeatures.THIN_TALL_ALIEN_TREE);
			Random random = new Random();

			return treesList.get(random.nextInt(treesList.size()));
		}
	};
}
