package com.mudkipboy7.alien.client.gui.screen;

import com.mudkipboy7.alien.AlienMod;
import com.mudkipboy7.alien.inventory.menu.CoalGeneratorMenu;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;

public class CoalGeneratorScreen extends AbstractMachineScreen<CoalGeneratorMenu> {
	protected static final ResourceLocation TEXTURE = AlienMod
			.location("textures/gui/container/coal_generator_menu.png");

	public CoalGeneratorScreen(CoalGeneratorMenu menu, Inventory inventory, Component title) {
		super(menu, inventory, title);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
		int maxEnergy = this.menu.getMaxEnergyStored();
		int energyAmmount = this.menu.getEnergyStored();
		int burnTimeLeft = this.menu.getBurnTimeLeft();
		int burnTimeOfFuel = this.menu.getBurnTimeOfFuel();
		int sliceWidth = this.leftPos;
		int sliceHeight = (this.height - this.imageHeight) / 2;
		int powerHeightMod = Math.round(52.0F * ((((float) energyAmmount) / (float) maxEnergy)));
		int burnTimeHeightMod = Math.round(14.0F * ((((float) burnTimeLeft) / (float) burnTimeOfFuel)));
		/*
		 * Makes it so that it will only display a fully empty one when it's at 0
		 */
		guiGraphics.blit(TEXTURE, sliceWidth, sliceHeight, 0, 0, this.imageWidth, this.imageHeight);
		guiGraphics.blitSprite(POWER_AMMOUNT_SPRITE, 16, 52, 0, 52 - powerHeightMod, sliceWidth + 98,
				sliceHeight + 17 + 52 - powerHeightMod, 16, powerHeightMod);

		guiGraphics.blitSprite(LIT_AMMOUNT_SPRITE, 14, 14, 0, 14 - burnTimeHeightMod, sliceWidth + 80,
				sliceHeight + 36 + 14 - burnTimeHeightMod, 14, burnTimeHeightMod);
	}

	@Override
	public void render(GuiGraphics pGuiGraphics, int mouseX, int mouseY, float pPartialTick) {
		super.render(pGuiGraphics, mouseX, mouseY, pPartialTick);
		renderTooltip(pGuiGraphics, mouseX, mouseY);

	}

	protected void renderTooltip(GuiGraphics graphics, int mouseX, int mouseY) {
		int burnTimeLeft = this.menu.getBurnTimeLeft();
		int burnTimeOfFuel = this.menu.getBurnTimeOfFuel();

		int energyAmmount = this.menu.getEnergyStored();
		int maxEnergy = this.menu.getMaxEnergyStored();

		if (this.menu.getCarried().isEmpty() && this.hoveredSlot != null && this.hoveredSlot.hasItem()) {
			ItemStack itemstack = this.hoveredSlot.getItem();
			graphics.renderTooltip(this.font, this.getTooltipFromContainerItem(itemstack), itemstack.getTooltipImage(),
					itemstack, mouseX, mouseY);
		} else if (this.isHovering(98, 17, 16, 52, mouseX, mouseY)) {
			graphics.renderTooltip(this.font, getValueOfResource(energyAmmount, maxEnergy), mouseX /* 351 */,
					mouseY + 8);
		} else if (this.isHovering(80, 36, 14, 14, mouseX, mouseY)) {
			if (true) {
				graphics.renderTooltip(this.font, getValueOfResource(burnTimeLeft, burnTimeOfFuel), mouseX, mouseY + 6);
			}
		}
	}

}
