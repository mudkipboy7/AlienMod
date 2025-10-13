package com.mudkipboy7.alien.world.entity.boss;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.world.BossEvent;

public class JovianBossFight {
	JovianBossEntity jovianBossEntity;
	private final ServerBossEvent bossEvent;

	public JovianBossFight(JovianBossEntity jovianBossEntity) {
		this.jovianBossEntity = jovianBossEntity;
		this.bossEvent = (ServerBossEvent) (new ServerBossEvent(jovianBossEntity.getDisplayName(), BossEvent.BossBarColor.WHITE,
				BossEvent.BossBarOverlay.PROGRESS));
	}

	public JovianBossEntity getJovianBossEntity() {
		return jovianBossEntity;
	}

	public ServerBossEvent getBossEvent() {
		return bossEvent;
	}
}
