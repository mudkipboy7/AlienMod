package com.mudkipboy7.alien.client.gui.screen;

import com.mudkipboy7.alien.AlienMod;
import com.mudkipboy7.alien.inventory.menu.EnergyBlockMenu;
import com.mudkipboy7.alien.inventory.menu.EnergyStorageMenu;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;

public class EnergyBlockScreen extends AbstractMachineScreen<EnergyBlockMenu> {
	protected static final ResourceLocation TEXTURE = AlienMod.location("textures/gui/container/energy_block_menu.png");

	public EnergyBlockScreen(EnergyBlockMenu menu, Inventory inventory, Component title) {
		super(menu, inventory, title);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
		int maxEnergy = this.menu.getMaxEnergyStored();
		int energyAmmount = this.menu.getEnergyStored();
		int sliceWidth = this.leftPos;
		int sliceHeight = (this.height - this.imageHeight) / 2;
		int powerHeightMod = Math.round(52.0F * ((((float) energyAmmount) / (float) maxEnergy)));
		guiGraphics.blit(TEXTURE, sliceWidth, sliceHeight, 0, 0, this.imageWidth, this.imageHeight);
		guiGraphics.blitSprite(POWER_AMMOUNT_SPRITE, 16, 52, 0, 52 - powerHeightMod, sliceWidth + 98,
				sliceHeight + 17 + 52 - powerHeightMod, 16, powerHeightMod);
	}

	@Override
	public void render(GuiGraphics pGuiGraphics, int mouseX, int mouseY, float pPartialTick) {
		super.render(pGuiGraphics, mouseX, mouseY, pPartialTick);
		renderTooltip(pGuiGraphics, mouseX, mouseY);

	}

	protected void renderTooltip(GuiGraphics graphics, int mouseX, int mouseY) {
		if (this.menu.getCarried().isEmpty() && this.hoveredSlot != null) {
			if (this.hoveredSlot.hasItem()) {
				ItemStack itemstack = this.hoveredSlot.getItem();
				graphics.renderTooltip(this.font, this.getTooltipFromContainerItem(itemstack),
						itemstack.getTooltipImage(), itemstack, mouseX, mouseY);
			}
		} else if (this.isHovering(98, 17, 16, 52, mouseX, mouseY)) {
			graphics.renderTooltip(this.font,
					Component.literal("∞").withStyle((arg) -> arg.withColor(getColorFromPercent(100))),
					mouseX /* 351 */, mouseY + 8);
		}
	}

}
