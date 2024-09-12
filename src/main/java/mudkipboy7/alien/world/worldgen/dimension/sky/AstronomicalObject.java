package mudkipboy7.alien.world.worldgen.dimension.sky;

import mudkipboy7.alien.AlienMod;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;

public class AstronomicalObject {
	protected float visualSize = 1.0F;
	protected float eclipseSize = 1.0F;

	protected float minBrightness = 1.0F;
	protected AlienDimSky sky;
	protected ResourceLocation texture;

	/**
	 * 
	 * @param normalBrightness It's default/max brightness
	 * @param size             The size it's drawn with
	 * @param texture          The location of it's texture
	 */
	public AstronomicalObject(AlienDimSky sky, float minBrightness, float size, ResourceLocation texture) {
		this.sky = sky;
		this.minBrightness = minBrightness;
		this.visualSize = size;
		this.eclipseSize = size;
		this.texture = texture;
	}

	public AlienDimSky getSky() {
		return sky;
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

	public void setEclipseSize(float eclipseSize) {
		this.eclipseSize = eclipseSize;
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
			return level.getTimeOfDay(Minecraft.getInstance().getPartialTick());
		return 0.0F;
	}
}
