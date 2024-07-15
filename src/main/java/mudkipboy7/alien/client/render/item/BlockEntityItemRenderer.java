package mudkipboy7.alien.client.render.item;

import java.util.function.Supplier;

import com.google.common.base.Suppliers;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(value = Dist.CLIENT)
public class BlockEntityItemRenderer<T extends BlockEntity> extends BlockEntityWithoutLevelRenderer {

	private final Supplier<T> supplier;

	public BlockEntityItemRenderer(Supplier<T> supplier) {
		super(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
		this.supplier = Suppliers.memoize(supplier::get);
	}

	@Override
	public void renderByItem(ItemStack stack, ItemDisplayContext context, PoseStack poseStack, MultiBufferSource buffer,
			int combinedLight, int combinedOverlay) {
		T te = this.supplier.get();
		Minecraft.getInstance().getBlockEntityRenderDispatcher().getRenderer(te).render(te, 0, poseStack, buffer,
				combinedLight, combinedOverlay);
	}

}
