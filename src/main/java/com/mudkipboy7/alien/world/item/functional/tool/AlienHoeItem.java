package com.mudkipboy7.alien.world.item.functional.tool;

import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;

public class AlienHoeItem extends DiggerItem {

	public AlienHoeItem(Tier tier, float damage, float speed, Item.Properties properties) {
		super((float) damage, speed, tier, null, properties);
	}
}
