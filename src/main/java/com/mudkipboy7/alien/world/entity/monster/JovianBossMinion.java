package com.mudkipboy7.alien.world.entity.monster;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class JovianBossMinion extends AlienZombie {
	static final int LIFESPAN = 400;
	int ticksExisted = 0;

	public JovianBossMinion(EntityType<? extends AlienZombie> entityType, Level level) {
		super(entityType, level);
	}

	@Override
	public void tick() {
		if (ticksExisted > LIFESPAN) {
			this.hurt(this.level().damageSources().generic(), 99999999);
		}
		super.tick();
		ticksExisted++;
	}

	@Override
	public void aiStep() {
		// TODO Auto-generated method stub
		super.aiStep();
	}

}
