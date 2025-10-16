package com.mudkipboy7.alien.sky.astroobject;

import org.joml.Math;

import com.mudkipboy7.alien.sky.AstronomicalFunctions;

public class JovianPlanetAstroObject extends AstronomicalObject implements IPhasingAstroObject {

	public JovianPlanetAstroObject(float minBrightness, float size) {
		super(minBrightness, size);
	}

	@Override
	public float getBrightness(float brightnessMultiplier, double sunLocation) {
		float x = (float) Math.max(getMinBrightness(),
				Math.abs(AstronomicalFunctions.distAheadBehind(this.getSkyLocation(), sunLocation)) / 360.0F);
		return x;
	}

	@Override
	public int getCurrentPhase(double locationOfLightSource) {
		return calculatePhaseFromSkyLocations(locationOfLightSource, this.getSkyLocation());
	}

	@Override
	public double getSkyLocation() {
		return 5.0D;
	}
}
