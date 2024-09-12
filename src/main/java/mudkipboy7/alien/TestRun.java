package mudkipboy7.alien;

import java.util.Scanner;

import mudkipboy7.alien.world.worldgen.dimension.sky.AstronomicalFunctions;

public class TestRun {
	public static void main(String[] args) {
		/*while (true) {
			Scanner reader = new Scanner(System.in);
			System.out.println("Input: ");
			Float n = reader.nextFloat();
			if (n instanceof Float)
				System.out.println(getCurrentPhase(n));
		}*/
		System.out.println(getCurrentPhase(180));
	}

	public static float getSunMoonDistance(float sunPos, float moonPos) {
		float sunSunZenithDist = getAstroDist(moonPos, sunPos);
		return sunSunZenithDist;
	}

	public static int getCurrentPhase(float locationOfLightSource) {
		int distanceToLightSource = (int) AstronomicalFunctions.getAstroDist(0.0F, locationOfLightSource);
		// System.out.println((distanceToLightSource) /
		// (AstronomicalFunctions.DEGREES_IN_CIRC / this.numberOfPhases));
		return Math.round((distanceToLightSource) / (360.0F / 8));

	}

	/**
	 * 
	 * 
	 * @param pos1 The degree position of object 1
	 * @param pos2 The degree position of object 2
	 * @return
	 */
	public static float getAstroDist(float pos1, float pos2) {
		float circ = 360.0F;
		float pos1Pos = convertToPositiveDegrees(pos1, circ);
		float pos2Pos = convertToPositiveDegrees(pos2, circ);
		return pos1Pos - pos2Pos;

	}

	/**
	 * 
	 * @param pos  The value
	 * @param circ How many degrees are in a circle 360/1
	 * @return
	 */
	public static float convertToPositiveDegrees(float pos, float circ) {
		float ajustedPos = pos % circ;
		return ajustedPos < 0.0F ? circ + ajustedPos : ajustedPos;

	}
}
