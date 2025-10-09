package com.mudkipboy7.alien.mixin.mixins.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mudkipboy7.alien.AMConfig;
import com.mudkipboy7.alien.AlienMod;
import com.mudkipboy7.alien.client.render.AMPanoramaRenderer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.renderer.CubeMap;
import net.minecraft.client.renderer.PanoramaRenderer;
import net.minecraft.network.chat.Component;

@Mixin(PanoramaRenderer.class)
public abstract class PanoramaRendererMixin {

	@Inject(method = "render", at = @At("INVOKE"), cancellable = true)
	protected void render(float pDeltaT, float pAlpha, CallbackInfo callbackInfo) {
		if (AMConfig.Client.customTitleScreen.get()) {
			AMPanoramaRenderer.render(pDeltaT, pAlpha);
			callbackInfo.cancel();
		}
	}

}
