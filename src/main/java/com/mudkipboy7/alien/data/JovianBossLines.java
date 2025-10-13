package com.mudkipboy7.alien.data;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextColor;

public class JovianBossLines {
	private static final String PREFIX = "jovianboss.";
	private static final String CHAT_PREFIX = PREFIX + "msg.";
	
	/*
	 * Chat messages
	 */
	public static final String WHEN_SUMMONED_IN_WRONG_DIMENSION = chatMsg("summon_wrong_dim");
	
	
	
	
	
	private static String chatMsg(String langName) {
		return CHAT_PREFIX + langName;
	}

}
