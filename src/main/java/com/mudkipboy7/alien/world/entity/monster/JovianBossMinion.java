package com.mudkipboy7.alien.world.entity.monster;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class JovianBossMinion extends AlienZombie {
	static final int LIFESPAN = 150;
	int ticksExisted = 0;

	public JovianBossMinion(EntityType<? extends AlienZombie> entityType, Level level) {
		super(entityType, level);
	}
	public static AttributeSupplier.Builder createAttributes() {
		return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 10.0D).add(Attributes.FOLLOW_RANGE, 40.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.25D).add(Attributes.ATTACK_DAMAGE, 3.0D);
	}
	@Override
	public void tick() {
		if (!this.hasCustomName()) {
			if (ticksExisted > LIFESPAN) {
				this.hurt(this.level().damageSources().generic(), 99999999);
			}
			ticksExisted++;
		}
		super.tick();
	}

	@Override
	public void aiStep() {
		// TODO Auto-generated method stub
		super.aiStep();
	}

}
