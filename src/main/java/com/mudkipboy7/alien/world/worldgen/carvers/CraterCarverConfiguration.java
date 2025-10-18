package com.mudkipboy7.alien.world.worldgen.carvers;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryCodecs;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.valueproviders.FloatProvider;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.carver.CarverConfiguration;
import net.minecraft.world.level.levelgen.carver.CarverDebugSettings;
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;

public class CraterCarverConfiguration extends CarverConfiguration {
	   public static final Codec<CraterCarverConfiguration> CODEC = RecordCodecBuilder.mapCodec((p_224839_) -> {
		      return p_224839_.group(Codec.floatRange(0.0F, 1.0F).fieldOf("probability").forGetter((p_159113_) -> {
		         return p_159113_.probability;
		      }), HeightProvider.CODEC.fieldOf("y").forGetter((p_159111_) -> {
		         return p_159111_.y;
		      }), FloatProvider.CODEC.fieldOf("yScale").forGetter((p_159109_) -> {
		         return p_159109_.yScale;
		      }), VerticalAnchor.CODEC.fieldOf("lava_level").forGetter((p_159107_) -> {
		         return p_159107_.lavaLevel;
		      }), CarverDebugSettings.CODEC.optionalFieldOf("debug_settings", CarverDebugSettings.DEFAULT).forGetter((p_190637_) -> {
		         return p_190637_.debugSettings;
		      }), RegistryCodecs.homogeneousList(Registries.BLOCK).fieldOf("replaceable").forGetter((p_224841_) -> {
		         return p_224841_.replaceable;
		      })).apply(p_224839_, CraterCarverConfiguration::new);
		   }).codec();
	public CraterCarverConfiguration(
			float probabbility, HeightProvider y, FloatProvider yScale,
			VerticalAnchor lavaLevel, CarverDebugSettings debugSettings, HolderSet<Block> replacibleBlocks) {
		super(probabbility, y, yScale, lavaLevel, debugSettings, replacibleBlocks);
	}

}
