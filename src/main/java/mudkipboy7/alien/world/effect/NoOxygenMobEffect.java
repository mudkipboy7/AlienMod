package mudkipboy7.alien.world.effect;

import mudkipboy7.alien.data.AMDamageTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class NoOxygenMobEffect extends AlienMobEffect {
	int timeSinceLastCough = 0;

	public NoOxygenMobEffect(MobEffectCategory category, int color) {
		super(category, color);
	}

	@Override
	public void applyEffectTick(LivingEntity livingEntity, int amplifier) {
		// if (!entity.hasEffect(AMMobEffects.HAZARD_PROTECTION.get())) {
		// Level level = livingEntity.level();

		/*
		 * if (livingEntity instanceof Player player && false) { timeSinceLastCough +=
		 * 1; if (timeSinceLastCough > 10) {
		 * player.playSound(AMSoundEvents.PLAYER_COUGH.get()); timeSinceLastCough = 0;
		 * 
		 * } }
		 */
		livingEntity.hurt(AMDamageTypes.damageSource(livingEntity.level(), AMDamageTypes.NO_OXYGEN),
				1.0F + (float) amplifier);
		// }
	}

	@Override
	public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
		int i = 10 >> amplifier;
		if (i > 0) {
			return duration % i == 0;
		} else {
			return true;
		}
	}

}
