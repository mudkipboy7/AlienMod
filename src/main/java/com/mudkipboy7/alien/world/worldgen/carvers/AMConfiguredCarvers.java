package com.mudkipboy7.alien.world.worldgen.carvers;

import com.mudkipboy7.alien.AlienMod;
import com.mudkipboy7.alien.data.tags.AMBlockTags;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantFloat;
import net.minecraft.util.valueproviders.TrapezoidFloat;
import net.minecraft.util.valueproviders.UniformFloat;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.carver.CanyonCarverConfiguration;
import net.minecraft.world.level.levelgen.carver.CarverConfiguration;
import net.minecraft.world.level.levelgen.carver.CarverDebugSettings;
import net.minecraft.world.level.levelgen.carver.CaveCarverConfiguration;
import net.minecraft.world.level.levelgen.carver.CaveWorldCarver;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.carver.WorldCarver;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.heightproviders.UniformHeight;

public class AMConfiguredCarvers {

	public static final ResourceKey<ConfiguredWorldCarver<?>> CRATER = createKey("crater");

	public static void bootstrap(BootstapContext<ConfiguredWorldCarver<?>> context) {
		HolderGetter<Block> holdergetter = context.lookup(Registries.BLOCK);
		context.register(CRATER, AMCarvers.CRATER.get().configured(new CraterCarverConfiguration(0.1F,
				UniformHeight.of(VerticalAnchor.absolute(10), VerticalAnchor.absolute(67)), ConstantFloat.of(3.0F),
				VerticalAnchor.aboveBottom(8), CarverDebugSettings.of(false, Blocks.WARPED_BUTTON.defaultBlockState()),
				holdergetter.getOrThrow(AMBlockTags.CARVER_CAN_REPLACE))));

	}

	private static ResourceKey<ConfiguredWorldCarver<?>> createKey(String name) {
		return ResourceKey.create(Registries.CONFIGURED_CARVER, AlienMod.location(name));

	}

}
