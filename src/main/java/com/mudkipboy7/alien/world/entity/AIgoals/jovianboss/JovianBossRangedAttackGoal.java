package com.mudkipboy7.alien.world.entity.AIgoals.jovianboss;

import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.monster.RangedAttackMob;

public class JovianBossRangedAttackGoal extends RangedAttackGoal {

	public JovianBossRangedAttackGoal(
			RangedAttackMob pRangedAttackMob, double pSpeedModifier, int pAttackIntervalMin,
			int pAttackIntervalMax, float pAttackRadius) {
		super(pRangedAttackMob, pSpeedModifier, pAttackIntervalMin, pAttackIntervalMax, pAttackRadius);
	}

}
