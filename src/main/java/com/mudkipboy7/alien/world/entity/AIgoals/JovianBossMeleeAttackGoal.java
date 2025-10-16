package com.mudkipboy7.alien.world.entity.AIgoals;

import com.mudkipboy7.alien.world.entity.boss.JovianBossEntity;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;

public class JovianBossMeleeAttackGoal extends Goal {
	JovianBossEntity jovianBossEntity;

	public JovianBossMeleeAttackGoal(JovianBossEntity jovianBossEntity) {
		super();
		this.jovianBossEntity = jovianBossEntity;
	}

	@Override
	public boolean canUse() {
		return false;
	}

}
