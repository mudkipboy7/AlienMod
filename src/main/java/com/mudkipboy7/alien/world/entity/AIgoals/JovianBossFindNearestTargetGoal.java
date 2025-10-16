package com.mudkipboy7.alien.world.entity.AIgoals;

import com.mudkipboy7.alien.world.entity.boss.JovianBossEntity;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.player.Player;

public class JovianBossFindNearestTargetGoal extends NearestAttackableTargetGoal<Player> {

	public JovianBossFindNearestTargetGoal(JovianBossEntity entity) {
		super(entity, Player.class, true);
	}

}
