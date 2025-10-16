package com.mudkipboy7.alien.sky.astroobject;

import com.mudkipboy7.alien.sky.AstronomicalFunctions;

import net.minecraft.world.level.Level;

public class SatiliteAstroObject extends AstronomicalObject {

	/**
	 * * The Length of the object's Orbital period measured in ticks.
	 */
	protected double orbitalPeriod = Level.TICKS_PER_DAY;

	// How many ticks should be ahead of it's otherwise normal orbit location if
	// this is zero.
	protected double headStart = 0;

	public SatiliteAstroObject(float minBrightness, float size, double orbitalPeriod, double headStart) {
		super(minBrightness, size);
		this.orbitalPeriod = orbitalPeriod;
		this.headStart = headStart;
	}

	/**
	 * The object's position in it's current orbit in radians
	 * 
	 * @return
	 */
	public double getPosition() {
		// double x = AstronomicalFunctions.convertToPositiveDegrees(360.0F *
		// this.getOrbitsCompleted());
		return AstronomicalFunctions
				.normalizeDegreees(AstronomicalFunctions.rotationsToDegrees(this.getOrbitCompletion()));
	}

	/**
	 * The object's position in it's current orbit in rotations
	 * 
	 * @return
	 */
	public double getOrbitCompletion() {
		// double x = AstronomicalFunctions.convertToPositiveDegrees(360.0F *
		// this.getOrbitsCompleted());
		return this.getOrbitsCompleted() % 1.0D;
	}

	@Override
	public double getSkyLocation() {
		return 180.0D;
	}

	public double getOrbitalAltitude() {
		return AstronomicalFunctions.SMAFromPeriod(orbitalPeriod);
	}

	public double getOrbitalPeriod() {
		return this.orbitalPeriod;
	}

	private double getOrbitsCompleted() {
		if (this.getLevel() != null)
			return (this.getLevel().getDayTime() + headStart) / this.getOrbitalPeriod();
		return 0;

	}
}
