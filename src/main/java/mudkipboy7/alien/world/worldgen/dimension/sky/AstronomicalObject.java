package mudkipboy7.alien.world.worldgen.dimension.sky;

import mudkipboy7.alien.AlienMod;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;

public class AstronomicalObject {
	protected float visualSize = 1.0F;
	protected float eclipseSize = 1.0F;

	protected float minBrightness = 1.0F;

	protected ResourceLocation texture;

	/**
	 * 
	 * @param normalBrightness It's default/max brightness
	 * @param visualSize       The size it's drawn with
	 * @param eclipseSize      The size used to calculate eclipses and lighting
	 * @param texture          The location of it's texture
	 */
	public AstronomicalObject(float minBrightness, float visualSize, float eclipseSize, ResourceLocation texture) {
		this.minBrightness = minBrightness;
		this.visualSize = visualSize;
		this.eclipseSize = eclipseSize;
		this.texture = texture;
	}

	public float getBrightness(float mul, float sunLocation) {
		return 1.0F;
	}

	public float getMinBrightness() {
		return minBrightness;
	}

	public float getVisualSize() {
		return visualSize;

	}

	public float getEclipseSize() {
		return eclipseSize;
	}

	public ResourceLocation getTexture() {
		return texture;
	}

	public float getLocation() {
		return 0.0F;
	}

	protected float getPartialTick() {
		return Minecraft.getInstance().getPartialTick();
	}

	protected Level getLevel() {
		return Minecraft.getInstance().level;
	}

	protected float getTimeOfDay() {
		Level level = getLevel();
		if (level != null)
			return level.getTimeOfDay(getPartialTick());
		return 0.0F;
	}
}
