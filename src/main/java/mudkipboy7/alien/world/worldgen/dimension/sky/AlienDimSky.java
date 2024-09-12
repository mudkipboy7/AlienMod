package mudkipboy7.alien.world.worldgen.dimension.sky;

import com.google.gson.JsonObject;

import mudkipboy7.alien.AMFileMethods;
import mudkipboy7.alien.AlienMod;
import mudkipboy7.alien.SpecialPlayers.SpecialPlayerType;
import mudkipboy7.alien.world.worldgen.dimension.AMDimensions;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;

@SuppressWarnings("unused")
public final class AlienDimSky {
	// The sun texture.
	protected static final ResourceLocation SUN_TEXTURE = AlienMod.location("textures/environment/alien_sun.png");

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
	public float redMul = 1.3F;
	public float greenMul = 0.9F;
	public float blueMul = 0.8F;

	public AlienSunAstromicalObject alienSun = new AlienSunAstromicalObject(this, 0.65F, 5.5F, SUN_TEXTURE,
			SUN_GLARE_TEXTURE);
	public PhasingAstronomicalObject jovianPlanet = new PhasingAstronomicalObject(this, 0.15F, 34.0F,
			JOVIAN_PLANET_TEXTURE);
	public PhasingAstronomicalObject smallMoon = new SmallMoonAstronomicalObject(this, 0.15F, 4.0F, SMALL_MOON_TEXURE,
			2.0F);
	public StarAstronomicalObject stars = new StarAstronomicalObject(this, 0.15F, 94373L, 2000);

	public AlienDimSky() {
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
			float eclipsyness = this.getEclipsyness(this.alienSun.getLocation(), this.jovianPlanet.getLocation());

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
