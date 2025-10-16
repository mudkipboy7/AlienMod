package com.mudkipboy7.alien.mixin.mixins;

import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.world.level.dimension.DimensionType;

@Mixin(DimensionType.class)
public abstract class DimensionTypeMixin {

	//@Inject(method = "timeOfDay", at = @At("HEAD"), cancellable = true)
	//public void timeOfDay(long pDayTime, CallbackInfoReturnable<Float> callbackInfo) {
	//	AMDimensions.timeOfDay(null, pDayTime, callbackInfo);
	//}
}
