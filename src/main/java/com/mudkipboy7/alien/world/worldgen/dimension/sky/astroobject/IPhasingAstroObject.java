package com.mudkipboy7.alien.world.worldgen.dimension.sky.astroobject;

import com.mudkipboy7.alien.world.worldgen.dimension.sky.AstronomicalFunctions;

public interface IPhasingAstroObject {
	public abstract int getCurrentPhase(double locationOfLightSource);

	/*
	 * This determines the number of phases of the sky object. I think this number
	 * has to be a factor of 24000 (the number of ticks in one Minecraft day). All
	 * phases are the same length and spaced evenly. If there are 8 phases, each
	 * phase is 3000 ticks long, the New Moon is the 3rd phase and the Full Moon is
	 * the seventh with each occurring at noon and midnight respectively.
	 * Technically the true number of phases you get from getCurrentPhase() is going
	 * to be one higher then this because there is a 0th phase. But you can get the
	 * actual one from getRealCurrentPhase(), it will give you the current phases
	 * order on the texture.
	 */
	public default byte getNumberOfPhases() {
		return 8;
	}

	public default int calculatePhaseFromSkyLocations(double locationOfLightSource, double thisSkyLocation) {
		double distanceToLightSource = AstronomicalFunctions.distAhead(thisSkyLocation, locationOfLightSource);
		double x = (distanceToLightSource) / (360.0D / this.getNumberOfPhases());
		// System.out.println(distanceToLightSource);
		return (int) Math.round(x);
	}
}
