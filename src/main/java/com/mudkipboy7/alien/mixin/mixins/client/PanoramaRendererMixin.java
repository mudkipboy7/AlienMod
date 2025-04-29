package com.mudkipboy7.alien.mixin.mixins.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mudkipboy7.alien.AlienMod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.renderer.CubeMap;
import net.minecraft.client.renderer.PanoramaRenderer;
import net.minecraft.network.chat.Component;

@Mixin(PanoramaRenderer.class)
public abstract class PanoramaRendererMixin {
	private final CubeMap cubeMap = new CubeMap(AlienMod.location("textures/environment/phases.png"));

	@Inject(method = "render", at = @At("INVOKE"), cancellable = true)
	protected void render(float pDeltaT, float pAlpha, CallbackInfo callbackInfo) {
		this.cubeMap.render(Minecraft.getInstance(), 0.0F, 0, pAlpha);
		// RenderSystem.setShaderTexture(0,
		// AlienMod.location("textures/block/alien_portal.png"));
		callbackInfo.cancel();
	}

}
