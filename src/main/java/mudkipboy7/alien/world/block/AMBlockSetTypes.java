package mudkipboy7.alien.world.block;

import mudkipboy7.alien.AlienMod;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

public record AMBlockSetTypes() {

	public static final BlockSetType LIGNUM = blockType("lignum");
	
	public static final BlockSetType GELUSTONE = blockType("gelustone");

	private static BlockSetType blockType(String name) {
		return BlockSetType.register(new BlockSetType(AlienMod.MODID + ":" + name));

	}

	public static final WoodType LIGNUM_WOOD_TYPE = woodType("lignum", AMBlockSetTypes.LIGNUM);

	private static WoodType woodType(String name, BlockSetType blockSetType) {
		return WoodType.register(new WoodType(AlienMod.MODID + ":" + name, blockSetType));
	}
}
