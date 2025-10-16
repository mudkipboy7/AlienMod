package com.mudkipboy7.alien.world.entity.boss;

public class JovianBossLines {
	private static final String PREFIX = "jovianboss.";
	private static final String CHAT_PREFIX = PREFIX + "msg.";

	/*
	 * Chat messages
	 */
	public static final String WHEN_SUMMONED_IN_WRONG_DIMENSION = chatMsg("summon_wrong_dim");
	public static final String WHEN_KNOCKED_OFF_SIDE = chatMsg("knocked_off");
	public static final String WHEN_ATTACKED_IN_CREATIVE_MODE = chatMsg("player_in_creative");
	public static final String DEATH = chatMsg("death");
	public static final String TAUNT_1 = chatMsg("taunt_1");
	public static final String TAUNT_2 = chatMsg("taunt_2");
	public static final String TAUNT_3 = chatMsg("taunt_3");
	public static final String TAUNT_4 = chatMsg("taunt_4");
	

	//public static final String KILL_PLAYER_WHOS_IN_CREATIVE = chatMsg("kill_creative_player");

	private static String chatMsg(String langName) {
		return CHAT_PREFIX + langName;
	}

}
