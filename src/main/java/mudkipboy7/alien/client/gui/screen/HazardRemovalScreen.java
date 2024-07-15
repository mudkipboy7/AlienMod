package mudkipboy7.alien.client.gui.screen;

import mudkipboy7.alien.AlienMod;
import mudkipboy7.alien.inventory.menu.HazardRemovalMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;

public class HazardRemovalScreen extends AbstractMachineScreen<HazardRemovalMenu> {
	protected static final ResourceLocation TEXTURE = AlienMod.location("textures/gui/container/air_purifier_menu.png");

	protected static final ResourceLocation LIT_AMMOUNT_SPRITE = AlienMod.location("container/machine_lit_progress");

	protected static final ResourceLocation POWER_AMMOUNT_SPRITE = AlienMod.location("container/power_ammount");

	protected static final ResourceLocation WATER_AMMOUNT_SPRITE = AlienMod.location("container/water_ammount");

	public HazardRemovalScreen(HazardRemovalMenu menu, Inventory inventory, Component title) {
		super(menu, inventory, title);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
		int maxEnergy = this.menu.getMaxEnergyStored();
		int energyAmmount = this.menu.getEnergyStored();
		int sliceWidth = this.leftPos;
		int sliceHeight = (this.height - this.imageHeight) / 2;
		int powerHeightMod = Math.round(52.0F * ((((float) energyAmmount) / (float) maxEnergy)));
		/*
		 * Makes it so that it will only display a fully empty one when it's at 0
		 */
		guiGraphics.blit(TEXTURE, sliceWidth, sliceHeight, 0, 0, this.imageWidth, this.imageHeight);
		guiGraphics.blitSprite(POWER_AMMOUNT_SPRITE, 16, 52, 0, 52 - powerHeightMod, sliceWidth + 111,
				sliceHeight + 17 + 52 - powerHeightMod, 16, powerHeightMod);
	}

	@Override
	public void render(GuiGraphics pGuiGraphics, int mouseX, int mouseY, float pPartialTick) {
		super.render(pGuiGraphics, mouseX, mouseY, pPartialTick);
		renderTooltip(pGuiGraphics, mouseX, mouseY);

	}

	protected void renderTooltip(GuiGraphics graphics, int mouseX, int mouseY) {
		int energyAmmount = this.menu.getEnergyStored();
		int maxEnergy = this.menu.getMaxEnergyStored();

		if (this.menu.getCarried().isEmpty() && this.hoveredSlot != null && this.hoveredSlot.hasItem()) {
			ItemStack itemstack = this.hoveredSlot.getItem();
			graphics.renderTooltip(this.font, this.getTooltipFromContainerItem(itemstack), itemstack.getTooltipImage(),
					itemstack, mouseX, mouseY);
		} else if (this.isHovering(111, 17, 16, 52, mouseX, mouseY)) {
			graphics.renderTooltip(this.font, getValueOfResource(energyAmmount, maxEnergy), mouseX /* 351 */,
					mouseY + 8);
		}
	}

}
