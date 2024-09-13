package mudkipboy7.alien.world.worldgen.dimension.sky;

import org.joml.Math;

import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;

public class SmallMoonAstronomicalObject extends SatiliteAstronomicalObject {

	public SmallMoonAstronomicalObject(AlienDimSky sky, float minBrightness, float size, ResourceLocation texture,
			float orbitalAltitude) {
		super(sky, minBrightness, size, texture, orbitalAltitude);
	}

	@Override
	public float getActualLocation() {
		float orbitalPeriod = (orbitalAltitude * orbitalAltitude);// orbitalAltitude * orbitalAltitude;
		float thisDeg = AstronomicalFunctions.convertToPositiveDegrees(
				((this.getLevel().getDayTime() / (200.0F / 3.0F)) / orbitalPeriod),
				AstronomicalFunctions.DEGREES_IN_CIRC);
		return thisDeg;
	}

	@Override
	public float getSkyLocation() {

		float thisDeg = getActualLocation();

		return thisDeg;
	}

	@Override
	public float getVisualSize() {

		float alienRad = getAlienPlanet().getOrbitalAltitude();
		float maxDist = (getOrbitalAltitude()) + (alienRad);
		float angleDif = AstronomicalFunctions.getAstroDist(this.getActualLocation(), 0);

		float dist = Math.sqrt((alienRad * alienRad) + (getOrbitalAltitude() * getOrbitalAltitude())
				+ (2.0F * alienRad * getOrbitalAltitude() * Math.cos(Math.toRadians(angleDif))));
		// small dist max 2, bigdist max 4
		float sizeMul = 1 / (dist);
		// System.out.println("big:" + bigDist + " small: " + smallDist);
		// System.out.println(sizeMul);
		return this.visualSize * sizeMul;
	}

	public SatiliteAstronomicalObject getAlienPlanet() {
		return this.getSky().alienPlanet;

	}
}
