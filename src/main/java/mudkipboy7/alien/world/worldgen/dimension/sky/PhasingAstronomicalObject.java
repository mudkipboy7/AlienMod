package mudkipboy7.alien.world.worldgen.dimension.sky;

import org.joml.Math;

import net.minecraft.resources.ResourceLocation;

public class PhasingAstronomicalObject extends AstronomicalObject {
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
	protected int numberOfPhases = 8;

	public PhasingAstronomicalObject(float minBrightness, float visualSize, float eclipseSize, ResourceLocation texture,
			int numberOfPhases) {
		super(minBrightness, visualSize, eclipseSize, texture);
		this.numberOfPhases = numberOfPhases;
	}

	public int getCurrentPhase(float locationOfLightSource) {
		float distanceToLightSource = AstronomicalFunctions.getAstroDist(this.getLocation(), locationOfLightSource);
		float x = (distanceToLightSource) / (((float) AstronomicalFunctions.DEGREES_IN_CIRC) / this.numberOfPhases);
		// System.out.println(distanceToLightSource);
		return Math.round(x);

	}

	public int getNumberOfPhases() {
		return numberOfPhases;
	}

	@Override
	public float getBrightness(float mul, float sunLocation) {
		float x = Math.max(getMinBrightness(),
				Math.abs(AstronomicalFunctions.getAstroDistNegativePositive(this.getLocation(), sunLocation))
						/ AstronomicalFunctions.DEGREES_IN_CIRC);
		// System.out.println(x);
		return x;
	}

	@Override
	public float getLocation() {
		return 10.0F;
	}
}
