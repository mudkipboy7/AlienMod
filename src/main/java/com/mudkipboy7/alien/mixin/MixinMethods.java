package com.mudkipboy7.alien.mixin;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.mudkipboy7.alien.world.Gravity;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

public class MixinMethods {

	public static void travel(LivingEntity livingEntity, Vec3 vec3, CallbackInfo callbackInfo) {
		if (Gravity.entityIsInLowGravityBiome(livingEntity)) {
			// callbackInfo.cancel();
		}

	}
}
