package com.mudkipboy7.alien.world.worldgen.dimension.sky;

import com.mudkipboy7.alien.world.worldgen.dimension.sky.astroobject.IPhasingAstroObject;

import mezz.jei.common.util.MathUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;

public class AstronomicalFunctions {

	// The orbital period at a semi-major axis of one
	public static final double PERIOD_AT_DISTANCE_ONE = Level.TICKS_PER_DAY;

	/*
	 * Gets the current time of day because for some reason level doesn't have a
	 * function to do that so this goes off of the world time. This takes in the
	 * value from level.getDayTime().
	 */
	protected static long getDayTick(long time) {
		long currentDay = time / ((long) Level.TICKS_PER_DAY);
		return Math.max(0, time - (Level.TICKS_PER_DAY * currentDay));
	}

	public static double normalizeDegreees(double pos) {
		double ajustedPos = pos % 360D;
		return ajustedPos < 0.0D ? 360D + ajustedPos : ajustedPos;
	}

	public static double rotationsToDegrees(double rotations) {
		return normalizeDegreees(rotations * 360.0D);
	}

	public static double distAhead(double objectA, double objectB) {
		return normalizeDegreees(objectB - objectA);
	}

	public static double distAheadBehind(double objectA, double objectB) {
		double dist = distAhead(objectA, objectB);
		return dist > (360.0D / 2.0D) ? dist - 360.0D : dist;
	}

	public static double periodFromSMA(double semiMajorAxis) {
		return PERIOD_AT_DISTANCE_ONE / Math.sqrt(semiMajorAxis * semiMajorAxis * semiMajorAxis);
	}

	public static double SMAFromPeriod(double period) {
		double converted = period / PERIOD_AT_DISTANCE_ONE;
		return Math.cbrt(converted * converted);
	}

	public static double calculateDistTriangle(double lengthA, double lengthB, double angle) {
		double aSqr = lengthA * lengthA;
		double bSqr = lengthB * lengthB;
		double x = 2.0D * lengthA * lengthB * Math.cos(Math.toRadians(angle));
		return Math.sqrt((aSqr + bSqr) - x);
	}
}
