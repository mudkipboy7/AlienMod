package com.mudkipboy7.alien.world.entity.AIgoals.jovianboss;

import com.mudkipboy7.alien.world.entity.boss.JovianBossEntity;

import net.minecraft.world.entity.ai.goal.Goal;

public class JovianBossCreativeAttackGoal extends Goal {
	JovianBossEntity jovianBossEntity;

	public JovianBossCreativeAttackGoal(JovianBossEntity jovianBossEntity) {
		super();
		this.jovianBossEntity = jovianBossEntity;
	}

	@Override
	public boolean canUse() {
		return false;
	}

}
