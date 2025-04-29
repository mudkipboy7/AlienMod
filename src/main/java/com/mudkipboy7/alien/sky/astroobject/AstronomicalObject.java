package com.mudkipboy7.alien.sky.astroobject;

import org.joml.Math;

import com.mudkipboy7.alien.AlienMod;

import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;

public class AstronomicalObject {
	protected float size = 1.0F;

	protected float minBrightness = 1.0F;

	public AstronomicalObject(float minBrightness, float size) {
		this.minBrightness = minBrightness;
		this.size = size;
	}

	public float getBrightness(float brightnessMultiplier, double sunLocation) {
		return 1.0F;
	}

	public float getMinBrightness() {
		return minBrightness;
	}

	public float getSize() {
		return size;
	}

	/**
	 * How large this object appears to be to be in the sky. Size if distance is 1.
	 * 
	 * @return
	 */
	public float getVisualSize() {
		return (float) (this.getSize() / getDistToCamera());
	}
	/**
	 * Gives the current position in the sky in degrees
	 * @return
	 */
	public double getSkyLocation() {
		return 0;
	}

	/**
	 * How far away the object is from the camera. 1 is normal distance
	 * 
	 * @return
	 */
	public double getDistToCamera() {
		return 1.0D;
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
