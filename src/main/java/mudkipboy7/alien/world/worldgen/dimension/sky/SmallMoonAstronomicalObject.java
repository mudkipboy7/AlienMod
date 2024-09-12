package mudkipboy7.alien.world.worldgen.dimension.sky;

import org.joml.Math;

import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;

public class SmallMoonAstronomicalObject extends PhasingAstronomicalObject {
	protected float orbitalPeriod = 1.0F;

	public SmallMoonAstronomicalObject(AlienDimSky sky, float minBrightness, float size, ResourceLocation texture,
			float orbitalPeriod) {
		super(sky, minBrightness, size, texture);
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
		// visualSize at closest, visualSize/3 at farthest
		float distFromJovian = Math.abs(AstronomicalFunctions.getAstroDistNegativePositive(this.getLocation(),
				getSky().jovianPlanet.getLocation()));
		float sizeAtFarthest = visualSize / 3.0F;
		float a = visualSize * ((Math.max(0.1F, distFromJovian)) / 160.0F);
		float y = Math.max(visualSize / 3.0F, a);
		return y;
	}

	public float getOrbitalPeriod() {
		return this.orbitalPeriod;
	}

}
