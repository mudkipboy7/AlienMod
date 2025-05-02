package com.mudkipboy7.alien.sky.astroobject;

import org.joml.Math;

import com.mudkipboy7.alien.sky.AstronomicalFunctions;

public class AlienSunAstroObject extends AstronomicalObject {

	public AlienSunAstroObject(float minBrightness, float size) {
		super(minBrightness, size);
	}

	public float getGlareSize() {
		return this.getVisualSize() * 4.0F;
	}

	@Override
	public double getSkyLocation() {
		return AstronomicalFunctions
				.normalizeDegreees(AstronomicalFunctions.rotationsToDegrees(this.getSkyLocationTurns()));
	}

	public float getSkyLocationTurns() {
		return this.getTimeOfDay();
	}

	@Override
	public float getBrightness(float mul, double sunLocation) {
		return Math.max(getMinBrightness(), mul);
	}
}
