package com.mudkipboy7.alien.world.worldgen.dimension.sky;

import com.mudkipboy7.alien.world.worldgen.dimension.AMDimensions;
import com.mudkipboy7.alien.world.worldgen.dimension.sky.astroobject.AlienSunAstroObject;
import com.mudkipboy7.alien.world.worldgen.dimension.sky.astroobject.AstronomicalObject;
import com.mudkipboy7.alien.world.worldgen.dimension.sky.astroobject.JovianPlanetAstroObject;
import com.mudkipboy7.alien.world.worldgen.dimension.sky.astroobject.SatiliteAstroObject;
import com.mudkipboy7.alien.world.worldgen.dimension.sky.astroobject.SmallMoonAstroObject;
import com.mudkipboy7.alien.world.worldgen.dimension.sky.astroobject.StarAstroObject;

import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;

public class AlienDimSky {
	/*
	 * Values used in math for the skylight. Do not set any of these to 0 or to a
	 * really low number, it might screw with UI.
	 */
	// Used in math to make the skylight less bright, only used visually
	public static final float visualBrightMul = 0.5F;

	// Changes the color of the skylight. The three values should add-up to 3.
	public static final float redMul = 1.6F;
	public static final float greenMul = 0.9F;
	public static final float blueMul = 0.5F;

	/*
	 * Sets up the astronomical objects
	 */
	public AlienSunAstroObject alienSun = new AlienSunAstroObject(0.65F, 5.5F);
	public JovianPlanetAstroObject jovianPlanet = new JovianPlanetAstroObject(0.15F, 34.0F);
	public SmallMoonAstroObject smallMoon = new SmallMoonAstroObject(0.15F, 4.0F, Level.TICKS_PER_DAY * 2, 20000);
	public SatiliteAstroObject alienPlanet = new SatiliteAstroObject(0.0F, 0.0F, Level.TICKS_PER_DAY, 0);
	public StarAstroObject stars = new StarAstroObject(0.15F, 94373L, 2000);

	public AlienDimSky() {
	}
	/*
	 * Eclipse stuff
	 */

	public float getEclipsyness() {
		float sunJovian = getTwoObjectEclipsyness(alienSun, jovianPlanet);

		// The minimum ammount the moon can decrease the brightness
		float moonMin = smallMoon.getVisualSize() / alienSun.getVisualSize();
		float sunMoon = 1 - ((1 - getTwoObjectEclipsyness(smallMoon, alienSun)) * moonMin);

		return Math.min(sunJovian, sunMoon);
	}

	private static float getTwoObjectEclipsyness(AstronomicalObject sun, AstronomicalObject moon) {
		float sunSize = sun.getVisualSize();
		float moonSize = moon.getVisualSize();

		// Gets the distence between the two
		double sunMoonDist = Math
				.abs(AstronomicalFunctions.distAheadBehind(moon.getSkyLocation(), sun.getSkyLocation()));

		float sunRad = sunSize / 2.0F;
		float moonRad = moonSize / 2.0F;

		if (sunMoonDist < sunRad + moonRad) {
			// If it's fully inside
			double distToSunFarthest = sunMoonDist + sunRad;
			if (distToSunFarthest < moonRad) {
				return 0.0F;
			} else {
				double x = Math.max(0.0F, Math.min(1.0F, (distToSunFarthest - moonRad) / sunSize));
				return (float) x;
			}
		}
		return 1.0F;
	}

	/**
	 * Modifies the skylight for the server, won't do anything if called on client.
	 * It also should not be called on client.
	 * 
	 * @param level
	 * @return if it did anything
	 */
	public boolean modifySkylightServerSide(Level level) {

		// Checks if the level is client side
		if (level.isClientSide()) {
			// do something here like print an error or something
		}
		/*
		 * If the level is serverside, checks if the dimension is right.
		 */
		else if (level.dimension() == AMDimensions.ALIENDIM_LEVEL) {
			float eclipsyness = getEclipsyness();

			if (eclipsyness <= 0.8F) {
				double thunder = 1.0D - (double) (level.getRainLevel(1.0F) * 5.0F) / 16.0D;
				double rain = 1.0D - (double) (level.getThunderLevel(1.0F) * 5.0F) / 16.0D;
				double sun = 0.5D + 2.0D
						* Mth.clamp((double) Mth.cos(level.getTimeOfDay(1.0F) * ((float) Math.PI * 2F)), -0.25D, 0.25D);
				// Dont know what the + 0.2 was for
				sun = sun * (eclipsyness+ 0.2F );
				level.skyDarken = (int) ((1.0D - sun * rain * thunder) * 11.0D);
				return true;
			}

		}
		return false;
	}
}
