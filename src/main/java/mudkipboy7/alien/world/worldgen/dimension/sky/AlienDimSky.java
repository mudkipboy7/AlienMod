package mudkipboy7.alien.world.worldgen.dimension.sky;

import mudkipboy7.alien.world.worldgen.dimension.AMDimensions;
import net.minecraft.CrashReport;
import net.minecraft.client.Minecraft;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

@SuppressWarnings("unused")
public class AlienDimSky {
	// The time that noon occurs at in a Minecraft day. Don't change this.

	private static float noonTime = 6000.0F;

	// The time that midnight occurs at in a Minecraft day. Don't change this.
	private static float midnightTime = 18000.0F;

	// The length of a Minecraft day. Don't change this.
	private static int dayLength = Level.TICKS_PER_DAY;

	// The time that sunrise starts.
	private static int timeOfSunriseStart;

	// The time that sunset ends.
	private static int timeOfSunsetEnd;

	/*
	 * Gets the current time of day because for some reason level doesn't have a
	 * function to do that so this goes off of the world time. This takes in the
	 * value from level.getDayTime().
	 */
	private static long getDayTick(long time) {
		long currentDay = time / ((long) dayLength);
		long timeInCurrentDay = Math.max(0, time - (dayLength * currentDay));
		return timeInCurrentDay;
	}

	/*
	 * Used in math to make the skylight less.
	 */
	public static float brightnessMultiplier = 0.5F;

	/*
	 * This determines the number of phases of the stationary sky object. I think
	 * this number has to be a factor of 24000 (the number of ticks in one Minecraft
	 * day). All phases are the same length and spaced evenly. If there are 8
	 * phases, each phase is 3000 ticks long, the New Moon is the 3rd phase and the
	 * Full Moon is the seventh with each occurring at noon and midnight
	 * respectively. Technically the true number of phases you get from
	 * getCurrentPhase() is going to be one higher then this because there is a 0th
	 * phase. But you can get the actual one from getRealCurrentPhase(), it will
	 * give you the current phases order on the texture.
	 */
	public static int numberOfPhases = 8;

	/*
	 * Determines what phase the stationary sky object should be on. This number
	 * isn't actually the current phase, as there is actually one more phase then
	 * the number set in numberOfPhases and each phase is different from it's place
	 * in the texture, because there is a 0th phase.
	 */
	public static int getCurrentPhase(long time) {
		long timeOfDay = getDayTick(time);
		return Math.round((timeOfDay) / (((float) dayLength) / numberOfPhases));
	}

	/*
	 * Determines the actual current phase, as in how people normally count, so
	 * there's no phase 0. Checks if it is to high and if so makes it the first
	 * phase. Assuming there are 8 phases, the number returned will be one more then
	 * getcurrentPhase(), except for the 0th and 8 phase, which will be 1.
	 */
	public static int getRealCurrentPhase(long time) {
		long timeOfDay = getDayTick(time);
		int currentPhase = getCurrentPhase(timeOfDay);
		currentPhase = ((currentPhase == numberOfPhases) || (currentPhase == 0)) ? 1 : currentPhase + 1;
		return currentPhase;
	}

	/*
	 * Eclipse stuff
	 */

	// The Eclipse settings for the AlienDim
	public static EclipseSettings alienDimEclipseSettings = new EclipseSettings();

	// Determines if there is currently an eclipse at all.
	public static boolean isInEclipse(long time, EclipseSettings settings) {
		long timeOfDay = getDayTick(time);
		return (timeOfDay >= settings.eclipseStartStart()) && (timeOfDay <= settings.eclipseEndEnd());
	}

	// Determines if the sky is fully darkened.
	public static boolean isInFullEclipse(long time, EclipseSettings settings) {
		long timeOfDay = getDayTick(time);
		return (timeOfDay < settings.fullEclipseEnd() && timeOfDay > settings.fullEclipseStart());
	}

	/*
	 * At 0.0F eclipsyness it is in full eclipse, at 1.0F it isn't eclipsing at all.
	 * This goes off of the current tick in the day, not the current tick of the
	 * world, I'm to lazy to re-write it to work off of that. This will always
	 * return at least 0.0F.
	 */
	public static float getEclipsyness(long time, EclipseSettings settings) {
		long timeOfDay = getDayTick(time);
		if (isInEclipse(timeOfDay, settings)) {
			/*
			 * Checks if timeOfDay is greater then or equal to fullEclipseEnd. If it is,
			 * eclipseAmmount is equal to (timeOfDay - fullEclipseEnd). If it isn't
			 * eclipseAmmount is equal to (fullEclipseStart - timeOfDay).
			 */
			float eclipseAmmount = (timeOfDay >= settings.fullEclipseEnd()) ? (timeOfDay - settings.fullEclipseEnd())
					: (settings.fullEclipseStart() - timeOfDay);

			return Math.max(0.0F, eclipseAmmount / settings.eclipseTransitionLength);
		}
		return 1.0F;
	}

	/**
	 * Modifies the skylight for the server won't do anything if called on client
	 * 
	 * @param level
	 * @return if it did anything
	 */
	public static boolean modifySkylightServerSide(Level level) {
		if (!level.isClientSide() && level.dimension() == AMDimensions.ALIENDIM_LEVEL) {
			float eclipsyness = AlienDimSky.getEclipsyness(level.getDayTime(), AlienDimSky.alienDimEclipseSettings);
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

		return false;
	}
}
