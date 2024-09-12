package mudkipboy7.alien.world.worldgen.dimension.sky;

import org.joml.Math;

import net.minecraft.resources.ResourceLocation;

public class AlienSunAstromicalObject extends AstronomicalObject {
	protected ResourceLocation glareTexture;
	protected float glareSize = 0;

	public AlienSunAstromicalObject(float minBrightness, float visualSize, float eclipseSize, ResourceLocation texture,
			ResourceLocation glareTexture) {
		super(minBrightness, visualSize, eclipseSize, texture);
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
	public float getLocation() {
		return getTimeOfDay() * 360.0F;
	}

	@Override
	public float getBrightness(float mul, float sunLocation) {
		return Math.max(getMinBrightness(), mul);
	}

}
