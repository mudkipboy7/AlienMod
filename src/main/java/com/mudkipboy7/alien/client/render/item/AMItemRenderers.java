package com.mudkipboy7.alien.client.render.item;

import com.mudkipboy7.alien.world.block.AMBlocks;
import com.mudkipboy7.alien.world.block.blockentity.AMBlockEntities;
import com.mudkipboy7.alien.world.block.blockentity.AlienChestBlockEntity;

import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

public class AMItemRenderers {
	public static IClientItemExtensions alienChest = new IClientItemExtensions() {
		@Override
		public BlockEntityWithoutLevelRenderer getCustomRenderer() {
			return new BlockEntityItemRenderer<>(() ->
				new AlienChestBlockEntity(AMBlockEntities.LIGNUM_CHEST.get(),
					BlockPos.ZERO, AMBlocks.LIGNUM_CHEST.get().defaultBlockState()));
		}
	};
}
