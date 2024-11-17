package com.mudkipboy7.alien.mixin.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.mudkipboy7.alien.world.Gravity;

import net.minecraft.world.entity.item.ItemEntity;

@Mixin(ItemEntity.class)
public class ItemEntityMixin {

	@Inject(method = "tick", at = @At("HEAD"))
	public void tick(CallbackInfo callbackInfo) {
		ItemEntity entity = (ItemEntity) (Object) this;
		if (Gravity.entityIsInLowGravityBiome(entity)) {
			Gravity.doSlowFall(entity, Gravity.alienDimItemFallMod);
		}
	}
}
