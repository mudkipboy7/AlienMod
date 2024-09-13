package mudkipboy7.alien.world.worldgen.dimension.sky;

import org.joml.Math;

import net.minecraft.resources.ResourceLocation;

public class AlienSunAstromicalObject extends AstronomicalObject {
	protected ResourceLocation glareTexture;
	protected float glareSize = 0;

	public AlienSunAstromicalObject(AlienDimSky sky, float minBrightness, float size, ResourceLocation texture,
			ResourceLocation glareTexture) {
		super(sky, minBrightness, size, texture);
		this.glareTexture = glareTexture;
		this.glareSize = this.getVisualSize() * 4.0F;
	}

	public float getGlareVisualSize() {
		return glareSize;
	}

	public ResourceLocation getGlareTexture() {
		return glareTexture;
	}

	@Override
	public float getSkyLocation() {
		return getTimeOfDay() * 360.0F;
	}

	@Override
	public float getBrightness(float mul, float sunLocation) {
		return Math.max(getMinBrightness(), mul);
	}

}
