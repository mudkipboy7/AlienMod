package mudkipboy7.alien.mixin.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import mudkipboy7.alien.world.effect.AMMobEffects;
import net.minecraft.world.entity.LivingEntity;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

	@Inject(method = "Lnet/minecraft/world/entity/LivingEntity;setSprinting(Z)V", at = @At("HEAD"), cancellable = true)
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
