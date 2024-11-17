package com.mudkipboy7.alien.client.render.dim;

import java.util.ArrayList;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.SimpleTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.resources.ResourceLocation;

public class PhasingAstroObjectTextureSet {
	private String objectName;
	private ResourceLocation objectTexture;
	private ResourceLocation phasesTexture;
	private DynamicTexture[] texturesList;
	private int numberOfPhases = 0;

	public PhasingAstroObjectTextureSet(String objectName, ResourceLocation objectTexture,
			ResourceLocation phasesTexture, int numberOfPhases) {
		this.objectName = objectName;
		this.objectTexture = objectTexture;
		this.phasesTexture = phasesTexture;
		this.numberOfPhases = numberOfPhases;
	}
	public void f() {
		if (getTextureManager().getTexture(objectTexture) instanceof SimpleTexture tex) {
			tex.setFilter(false, false);
		}
	}
	private static TextureManager getTextureManager() {
		return Minecraft.getInstance().getTextureManager();
	}
}
