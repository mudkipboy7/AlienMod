package mudkipboy7.alien.world.worldgen.dimension.sky;

import net.minecraft.resources.ResourceLocation;

public class SatiliteAstronomicalObject extends PhasingAstronomicalObject {

	protected float orbitalAltitude = 1.0F;

	public SatiliteAstronomicalObject(AlienDimSky sky, float minBrightness, float size, ResourceLocation texture,
			float orbitalAltitude) {
		super(sky, minBrightness, size, texture);
		this.orbitalAltitude = orbitalAltitude;
	}

	public float getActualLocation() {
		float orbitalPeriod = (orbitalAltitude * orbitalAltitude);
		float x = AstronomicalFunctions.convertToPositiveDegrees(
				(this.getLevel().getDayTime() / (200.0F / 3.0F)) / orbitalPeriod,
				AstronomicalFunctions.DEGREES_IN_CIRC);
		return x;
	}

	@Override
	public float getSkyLocation() {
		return 0;
	}

	public float getOrbitalAltitude() {
		return this.orbitalAltitude;
	}

	public float getOrbitalPeriod() {
		return orbitalAltitude * orbitalAltitude;
	}
}
