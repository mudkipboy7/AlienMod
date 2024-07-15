package mudkipboy7.alien.world.item.misc;

import java.util.function.Consumer;

import mudkipboy7.alien.client.render.item.AMItemRenderers;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

public class BlockEntityItem extends BlockItem {

	public BlockEntityItem(Block block, Properties properties) {
		super(block, properties);
	}

	@Override
	public void initializeClient(Consumer<IClientItemExtensions> consumer) {
		consumer.accept(AMItemRenderers.alienChest);
	}
}
