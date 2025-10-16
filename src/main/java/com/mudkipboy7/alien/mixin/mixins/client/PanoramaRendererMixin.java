package com.mudkipboy7.alien.mixin.mixins.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.mudkipboy7.alien.AMConfig;
import com.mudkipboy7.alien.client.render.AMPanoramaRenderer;

import net.minecraft.client.renderer.PanoramaRenderer;

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
