package com.mudkipboy7.alien.world.entity.boss;

/*
 * This is used to keep track of the current set of things that the boss is trying to do.
 * I'm using and enum instead of just an int because there is gonna be a LOT to keep track of.
 */
enum JovianBossStrategy {
	CHASING("chasing", false, false);

	String name;
	boolean isFleeing;
	boolean isEating;
	JovianBossStrategy(String name,boolean isFleeing, boolean isEating) {
		this.name = name;
		this.isFleeing = isFleeing;
		this.isEating = isEating;
	}

	public String getName() {
		return name;
	}
	
}
