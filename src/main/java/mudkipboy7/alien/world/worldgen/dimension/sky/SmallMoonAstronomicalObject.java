package mudkipboy7.alien.world.worldgen.dimension.sky;

import net.minecraft.resources.ResourceLocation;

public class SmallMoonAstronomicalObject extends PhasingAstronomicalObject {
	protected float orbitalPeriod = 1.0F;

	public SmallMoonAstronomicalObject(float minBrightness, float visualSize, ResourceLocation texture,
			int numberOfPhases, float orbitalPeriod) {
		super(minBrightness, visualSize, 0.0F, texture, numberOfPhases);
		this.orbitalPeriod = orbitalPeriod;
	}

	@Override
	public float getLocation() {
		float x = AstronomicalFunctions.convertToPositiveDegrees(
				(this.getLevel().getDayTime() / (200.0F / 3.0F)) / getOrbitalPeriod(),
				AstronomicalFunctions.DEGREES_IN_CIRC);
		return x;
	}

	@Override
	public float getVisualSize() {
		float x = Math.abs(AstronomicalFunctions.getAstroDistNegativePositive(this.getLocation(),
				AlienDimSky.alienDimSky.jovianPlanet.getLocation()));
		float y = Math.max(visualSize / 3.0F, visualSize * (Math.max(0.1F, x)) / 160.0F);
		return y;
	}

	public float getOrbitalPeriod() {
		return orbitalPeriod;
	}
}
