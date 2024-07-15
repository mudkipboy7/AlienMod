package mudkipboy7.alien.world.effect;

import mudkipboy7.alien.data.AMDamageTypes;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class ColdMobEffect extends NoOxygenMobEffect {
	// public boolean shouldShiver;

	public ColdMobEffect(MobEffectCategory category, int color) {
		super(category, color);
		// this.shouldShiver = false;
	}

	@Override
	public void applyEffectTick(LivingEntity entity, int amplifier) {

		entity.hurt(AMDamageTypes.damageSource(entity.level(), DamageTypes.FREEZE), 1.0F + (float) amplifier);
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
