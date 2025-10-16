package com.mudkipboy7.alien.mixin.mixins.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.mudkipboy7.alien.world.effect.AMMobEffects;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.Gui.HeartType;
import net.minecraft.world.entity.player.Player;

@Mixin(Gui.HeartType.class)
public class HeartGuiMixin {
	@Inject(method = "forPlayer", at = @At("RETURN"), cancellable = true)
	private static void forPlayer(Player player, CallbackInfoReturnable<HeartType> callbackInfo) {
		if (player.hasEffect(AMMobEffects.NO_OXYGEN.get()) || player.hasEffect(AMMobEffects.COLD.get())) {
			callbackInfo.setReturnValue(HeartType.FROZEN);
		}

	}
}
