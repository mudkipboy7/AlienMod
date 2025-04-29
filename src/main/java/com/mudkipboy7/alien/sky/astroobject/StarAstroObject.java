package com.mudkipboy7.alien.sky.astroobject;

import org.joml.Math;

import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.util.RandomSource;

public class StarAstroObject extends AstronomicalObject {
	private long seed = 10842L;
	private int count = 1500;

	public StarAstroObject(float minBrightness, long seed, int count) {
		super(minBrightness, 1.0F);
		this.seed = 94373L;
	}

	@Override
	public float getBrightness(float eclipsyness, double sunLocation) {
		ClientLevel clientLevel = (ClientLevel) this.getLevel();
		/*
		 * Does math to determine what the brightness should be. It checks that it isn't
		 * negative.
		 */
		float brightness = Math.min(this.getMinBrightness() + 0.5F, this.getMinBrightness()
				+ clientLevel.getStarBrightness(this.getPartialTick()) + ((1.0F - eclipsyness) / 2.0F));
		// Makes sure it's not to high if so returns the max value.
		return Math.max(0.0F, brightness);
	}

	public BufferBuilder.RenderedBuffer drawStars(BufferBuilder builderBuffer) {
		RandomSource randomsource = RandomSource.create(seed);
		builderBuffer.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION);

		for (int i = 0; i < (count); ++i) {
			double d0 = (double) (randomsource.nextFloat() * 2.0F - 1.0F);
			double d1 = (double) (randomsource.nextFloat() * 2.0F - 1.0F);
			double d2 = (double) (randomsource.nextFloat() * 2.0F - 1.0F);
			double d3 = (double) (0.15F + randomsource.nextFloat() * 0.1F);
			double d4 = d0 * d0 + d1 * d1 + d2 * d2;
			if (d4 < 1.0D && d4 > 0.01D) {
				d4 = 1.0D / Math.sqrt(d4);
				d0 *= d4;
				d1 *= d4;
				d2 *= d4;
				double d5 = d0 * 100.0D;
				double d6 = d1 * 100.0D;
				double d7 = d2 * 100.0D;
				double d8 = Math.atan2(d0, d2);
				double d9 = Math.sin(d8);
				double d10 = Math.cos(d8);
				double d11 = Math.atan2(Math.sqrt(d0 * d0 + d2 * d2), d1);
				double d12 = Math.sin(d11);
				double d13 = Math.cos(d11);
				double d14 = randomsource.nextDouble() * Math.PI * 2.0D;
				double d15 = Math.sin(d14);
				double d16 = Math.cos(d14);

				for (int j = 0; j < 4; ++j) {
					@SuppressWarnings("unused")
					double d17 = 0.0D;
					double d18 = (double) ((j & 2) - 1) * d3;
					double d19 = (double) ((j + 1 & 2) - 1) * d3;
					@SuppressWarnings("unused")
					double d20 = 0.0D;
					double d21 = d18 * d16 - d19 * d15;
					double d22 = d19 * d16 + d18 * d15;
					double d23 = d21 * d12 + 0.0D * d13;
					double d24 = 0.0D * d12 - d21 * d13;
					double d25 = d24 * d9 - d22 * d10;
					double d26 = d22 * d9 + d24 * d10;
					builderBuffer.vertex(d5 + d25, d6 + d23, d7 + d26).endVertex();
				}
			}
		}

		return builderBuffer.end();
	}
}
