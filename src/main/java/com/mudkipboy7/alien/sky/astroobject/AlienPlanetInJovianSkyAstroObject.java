package com.mudkipboy7.alien.sky.astroobject;

public class AlienPlanetInJovianSkyAstroObject extends AstronomicalObject implements IPhasingAstroObject{

	public AlienPlanetInJovianSkyAstroObject(float minBrightness, float size) {
		super(minBrightness, size);
	}

	@Override
	public int getCurrentPhase(double locationOfLightSource) {
		return 4;
	}
	@Override
	public float getBrightness(float brightnessMultiplier, double sunLocation) {
		return this.minBrightness;
	}
	@Override
	public double getSkyLocation() {
		return 70.0F;
	}
}
