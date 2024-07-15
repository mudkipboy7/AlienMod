package mudkipboy7.alien.world.item.functional.tool;

import mudkipboy7.alien.data.tags.AMBlockTags;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;

public class AlienPickaxeItem extends DiggerItem {

	public AlienPickaxeItem(Tier tier, float damage, float speed, Item.Properties properties) {
		super((float) damage, speed, tier, AMBlockTags.MINEABLE_WITH_ALIEN_PICKAXE, properties);
	}

}
