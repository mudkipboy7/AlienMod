package mudkipboy7.alien.world.worldgen.dimension.sky;

import static mudkipboy7.alien.world.worldgen.dimension.sky.AlienDimSky.alienDimSky;

import mudkipboy7.alien.AlienMod;
import mudkipboy7.alien.world.worldgen.dimension.AMDimensions;
import net.minecraft.CrashReport;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

@SuppressWarnings("unused")
public class AlienDimSky {
	// The sun texture.
	private static final ResourceLocation SUN_TEXTURE = AlienMod.location("textures/environment/alien_sun.png");

	// The texture of The Sun's glare
	private static final ResourceLocation SUN_GLARE_TEXTURE = AlienMod
			.location("textures/environment/alien_sun_glare.png");

	// The texture for the stationary planet in the sky.
	private static final ResourceLocation JOVIAN_PLANET_TEXTURE = AlienMod
			.location("textures/environment/jovian_planet_phases.png");

	// The texture for the moon
	private static final ResourceLocation SMALL_MOON_TEXURE = AlienMod
			.location("textures/environment/small_moon_phases.png");

	/*
	 * Used in math to make the skylight less.
	 */
	public float brightnessMultiplier = 0.5F;

	public static AlienDimSky alienDimSky = new AlienDimSky();

	public AlienSunAstromicalObject alienSun = new AlienSunAstromicalObject(0.65F, 5.5F, 5.5F, SUN_TEXTURE,
			SUN_GLARE_TEXTURE);
	public PhasingAstronomicalObject jovianPlanet = new PhasingAstronomicalObject(0.15F, 34.0F, 34.0F,
			JOVIAN_PLANET_TEXTURE, 8);
	public PhasingAstronomicalObject smallMoon = new SmallMoonAstronomicalObject(0.15F, 4.0F, SMALL_MOON_TEXURE, 8,
			2.0F);

	public AlienDimSky() {
		this.brightnessMultiplier = 0.5F;
	}

	/*
	 * Determines what phase the stationary sky object should be on. This number
	 * isn't actually the current phase, as there is actually one more phase then
	 * the number set in numberOfPhases and each phase is different from it's place
	 * in the texture, because there is a 0th phase.
	 */
	public int getCurrentPhase(long time) {
		long timeOfDay = AstronomicalFunctions.getDayTick(time);
		return Math.round((timeOfDay) / (((float) AstronomicalFunctions.dayLength) / jovianPlanet.numberOfPhases));
	}

	/*
	 * Determines the actual current phase, as in how people normally count, so
	 * there's no phase 0. Checks if it is to high and if so makes it the first
	 * phase. Assuming there are 8 phases, the number returned will be one more then
	 * getcurrentPhase(), except for the 0th and 8 phase, which will be 1.
	 */
	public int getRealCurrentPhase(long time) {
		long timeOfDay = AstronomicalFunctions.getDayTick(time);
		int currentPhase = getCurrentPhase(timeOfDay);
		currentPhase = ((currentPhase == jovianPlanet.numberOfPhases) || (currentPhase == 0)) ? 1 : currentPhase + 1;
		return currentPhase;
	}
	/*
	 * Eclipse stuff
	 */

	/**
	 * Gives the eclipsyness
	 * 
	 * @param sunPos  The pos of the Sun
	 * @param moonPos The pos of the Moon
	 * @return
	 */
	public float getEclipsyness(float sunPos, float moonPos) {
		// The degree sizes of the astronomical objects
		float sunSize = this.alienSun.getEclipseSize();
		float moonSize = this.jovianPlanet.getEclipseSize();
		// Gets the distence between the two
		float sunMoonDist = Math.abs(AstronomicalFunctions.getAstroDistNegativePositive(moonPos, sunPos));

		float sunRad = sunSize / 2.0F;
		float moonRad = moonSize / 2.0F;

		if (sunMoonDist < sunRad + moonRad) {
			// If it's fully inside
			float distToSunFarthest = sunMoonDist + sunRad;
			if (distToSunFarthest < moonRad) {
				return 0.0F;
			} else {
				float distToSunNearest = sunMoonDist - sunRad;
				float x = Math.max(0.0F, Math.min(1.0F, (distToSunFarthest - moonRad) / sunSize));
				// System.out.println(x);
				return x;
			}

		}

		return 1.0F;
	}

	/**
	 * Modifies the skylight for the server, won't do anything if called on client
	 * 
	 * @param level
	 * @return if it did anything
	 */
	public boolean modifySkylightServerSide(Level level) {

		// System.out.println(level.getGameTime());
		if (!level.isClientSide() && level.dimension() == AMDimensions.ALIENDIM_LEVEL) {
			float eclipsyness = alienDimSky.getEclipsyness(alienDimSky.alienSun.getLocation(),
					alienDimSky.jovianPlanet.getLocation());

			if (eclipsyness <= 0.8F) {
				double thunder = 1.0D - (double) (level.getRainLevel(1.0F) * 5.0F) / 16.0D;
				double rain = 1.0D - (double) (level.getThunderLevel(1.0F) * 5.0F) / 16.0D;
				double sun = 0.5D + 2.0D
						* Mth.clamp((double) Mth.cos(level.getTimeOfDay(1.0F) * ((float) Math.PI * 2F)), -0.25D, 0.25D);
				sun = sun * (eclipsyness + 0.2F);
				level.skyDarken = (int) ((1.0D - sun * rain * thunder) * 11.0D);

				return true;
			}
		}
		// System.out.println(1);
		return false;
	}
}
