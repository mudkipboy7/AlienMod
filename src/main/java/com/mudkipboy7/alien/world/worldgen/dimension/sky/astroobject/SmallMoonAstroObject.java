package com.mudkipboy7.alien.world.worldgen.dimension.sky.astroobject;

import org.joml.Math;

import com.mudkipboy7.alien.AlienMod;
import com.mudkipboy7.alien.world.worldgen.dimension.sky.AstronomicalFunctions;

import net.minecraft.resources.ResourceLocation;

public class SmallMoonAstroObject extends SatiliteAstroObject implements IPhasingAstroObject {

	public SmallMoonAstroObject(float minBrightness, float size, double orbitalPeriod, double headStart) {
		super(minBrightness, size, orbitalPeriod, headStart);
	}

	@Override
	public double getSkyLocation() {
		double thisDeg = getPosition();
		double x = AstronomicalFunctions.distAhead(thisDeg, this.getAlienPlanet().getPosition());
		double y = AstronomicalFunctions.distAhead(this.getAlienPlanet().getSkyLocation(), x);
		return y;
	}

	public SatiliteAstroObject getAlienPlanet() {
		return AlienMod.getAlienDimSky().alienPlanet;

	}

	@Override
	public double getDistToCamera() {
		double dist = AstronomicalFunctions.calculateDistTriangle(getAlienPlanet().getOrbitalAltitude(),
				getOrbitalAltitude(),
				AstronomicalFunctions.distAheadBehind(getAlienPlanet().getPosition(), getPosition()));
		// System.out.println(dist);
		return dist;
	}

	@Override
	public int getCurrentPhase(double locationOfLightSource) {
		return calculatePhaseFromSkyLocations(locationOfLightSource, this.getSkyLocation());
	}
}
