package mudkipboy7.alien.world.worldgen.dimension.sky;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;

public class AstronomicalFunctions {
	// The time that noon occurs at in a Minecraft day. Don't change this.
	private static final float noonTime = 6000.0F;

	// The time that midnight occurs at in a Minecraft day. Don't change this.
	private static final float midnightTime = 18000.0F;

	// The length of a Minecraft day. Don't change this.
	protected static final int dayLength = Level.TICKS_PER_DAY;

	protected static final int DEGREES_IN_CIRC = 360;

	/*
	 * Gets the current time of day because for some reason level doesn't have a
	 * function to do that so this goes off of the world time. This takes in the
	 * value from level.getDayTime().
	 */
	protected static long getDayTick(long time) {
		long currentDay = time / ((long) dayLength);
		long timeInCurrentDay = Math.max(0, time - (dayLength * currentDay));
		return timeInCurrentDay;
	}

	/**
	 * 
	 * @param pos  The value
	 * @param circ How many degrees are in a circle 360/1
	 * @return
	 */
	public static float convertToPositiveDegrees(float pos, float circ) {
		float ajustedPos = pos % circ;
		// System.out.println(ajustedPos);
		return ajustedPos < 0.0F ? circ + ajustedPos : ajustedPos;
	}

	/**
	 * Gives the distence between two object as a degrees
	 * 
	 * @param pos1
	 * @param pos2
	 * @return
	 */
	public static float getAstroDist(float pos1, float pos2) {
		float pos1Pos = convertToPositiveDegrees(pos1, DEGREES_IN_CIRC);
		float pos2Pos = convertToPositiveDegrees(pos2, DEGREES_IN_CIRC);
		return convertToPositiveDegrees(pos2Pos - pos1Pos, DEGREES_IN_CIRC);

	}

	/**
	 * Gives the distence between two objects with anything higher than 160 being
	 * given as negative.
	 * 
	 * @param pos1 The degree position of object 1
	 * @param pos2 The degree position of object 2
	 * @return
	 */
	public static float getAstroDistNegativePositive(float pos1, float pos2) {
		float dist = getAstroDist(pos1, pos2);
		return dist > (DEGREES_IN_CIRC / 2.0F) ? dist - 360.0F : dist;

	}
}
