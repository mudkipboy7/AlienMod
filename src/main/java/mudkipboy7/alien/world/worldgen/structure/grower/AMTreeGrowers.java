package mudkipboy7.alien.world.worldgen.structure.grower;

import mudkipboy7.alien.world.worldgen.structure.AMTreeFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class AMTreeGrowers {
	public static AbstractTreeGrower basicAlienTreeGrower = new AbstractTreeGrower() {
		@Override
		protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource randomSource, boolean hive) {
			return AMTreeFeatures.ALIEN_TREE;
		}
	};

}
