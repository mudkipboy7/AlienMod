package com.mudkipboy7.alien.mixin.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.mudkipboy7.alien.mixin.MixinMethods;
import com.mudkipboy7.alien.world.Gravity;
import com.mudkipboy7.alien.world.effect.AMMobEffects;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

	@Inject(method = "setSprinting", at = @At("HEAD"), cancellable = true)
	private void setSprinting(boolean sprinting, CallbackInfo callbackInfo) {
		LivingEntity livingEntity = (LivingEntity) (Object) this;
		/*
		 * This checks to see if the living entity is being set to sprint, if it is it
		 * checks if the entity has the no sprint effect and then does setSprinting()
		 * with false and cancels what would've happened.
		 */
		if (sprinting && livingEntity.hasEffect(AMMobEffects.NO_SPRINT.get())) {
			livingEntity.setSprinting(false);
			callbackInfo.cancel();
		}

	}
}
