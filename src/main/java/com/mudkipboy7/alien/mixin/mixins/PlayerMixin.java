package com.mudkipboy7.alien.mixin.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.mudkipboy7.alien.data.tags.AMBiomeTags;

import net.minecraft.core.Holder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.biome.Biome;

@Mixin(Player.class)
public abstract class PlayerMixin {

	@Inject(method = "tryToStartFallFlying", at = @At("HEAD"), cancellable = true)
	public void tryToStartFallFlying(CallbackInfoReturnable<Boolean> callbackInfo) {
		Player player = (Player) (Object) this;
		Holder<Biome> biome = player.level().getBiome(player.blockPosition());
		if (biome.is(AMBiomeTags.LOW_GRAVITY) || biome.is(AMBiomeTags.ULTRACOLD)) {
			callbackInfo.cancel();
			callbackInfo.setReturnValue(false);
		}
	}
}
