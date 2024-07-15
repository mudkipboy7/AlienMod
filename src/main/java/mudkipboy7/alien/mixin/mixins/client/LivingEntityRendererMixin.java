package mudkipboy7.alien.mixin.mixins.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import mudkipboy7.alien.data.tags.AMBiomeTags;
import mudkipboy7.alien.data.tags.AMEntityTypeTags;
import mudkipboy7.alien.world.effect.AMMobEffects;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

@Mixin(LivingEntityRenderer.class)
public class LivingEntityRendererMixin {
	@Inject(method = "Lnet/minecraft/client/renderer/entity/LivingEntityRenderer;isShaking(Lnet/minecraft/world/entity/LivingEntity;)Z", at = @At("RETURN"), cancellable = true)
	private void isShaking(LivingEntity livingEntity, CallbackInfoReturnable<Boolean> callbackInfo) {
		/*
		 * Checks if the entity is cold blooded, if so makes it not shiver ever.
		 */
		if (livingEntity.getType().is(AMEntityTypeTags.COLD_BLOODED))
			callbackInfo.setReturnValue(false);
		/*
		 * Checks if the entity is a player and if he has the COLD mob effect. If so
		 * makes the player shiver.
		 */
		else if (livingEntity instanceof Player player) {
			if (player.hasEffect(AMMobEffects.COLD.get()))
				callbackInfo.setReturnValue(true);
		}
		/*
		 * The reason why it's like this is because there's a glitch(at least I think
		 * it's a glitch) where what I'm doing with the player doesn't work on
		 * non-player mobs. So I make it be based off of the biome and if its cold
		 * blooded. I want to make it where they won't shiver if they're in range of the
		 * hazard remover but that wont work. I think it has something to do with it
		 * only applying the effect every so often but it still does protect them. I'm
		 * confused I don't really know why this is just a fix for the bug.
		 */
		else if (livingEntity.level().getBiome(livingEntity.blockPosition()).is(AMBiomeTags.ULTRACOLD))
			callbackInfo.setReturnValue(true);
	}
}
