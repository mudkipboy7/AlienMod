package com.mudkipboy7.alien.mixin.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.mudkipboy7.alien.world.worldgen.dimension.AMDimensions;

import net.minecraft.network.protocol.game.ClientboundUpdateRecipesPacket;
import net.minecraft.util.Mth;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.LevelTimeAccess;
import net.minecraft.world.level.dimension.DimensionType;

@Mixin(DimensionType.class)
public abstract class DimensionTypeMixin {

	//@Inject(method = "timeOfDay", at = @At("HEAD"), cancellable = true)
	//public void timeOfDay(long pDayTime, CallbackInfoReturnable<Float> callbackInfo) {
	//	AMDimensions.timeOfDay(null, pDayTime, callbackInfo);
	//}
}
