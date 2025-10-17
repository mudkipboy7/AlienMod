package com.mudkipboy7.alien.world.entity.AIgoals.jovianboss;

import com.mudkipboy7.alien.world.entity.boss.JovianBossEntity;

import net.minecraft.world.entity.ai.goal.Goal;

public class JovianBossFleeGoal extends Goal {
	JovianBossEntity jovianBossEntity;

	public JovianBossFleeGoal(JovianBossEntity jovianBossEntity) {
		this.jovianBossEntity = jovianBossEntity;
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean canUse() {
		// TODO Auto-generated method stub
		return false;
	}

}
