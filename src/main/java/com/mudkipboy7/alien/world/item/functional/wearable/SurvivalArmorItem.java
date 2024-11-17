package com.mudkipboy7.alien.world.item.functional.wearable;

import java.util.function.Consumer;

import com.mudkipboy7.alien.AlienMod;
import com.mudkipboy7.alien.data.tags.AMItemTags;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class SurvivalArmorItem extends ArmorItem implements ISurvivalItem {
	Item becomesUponBreaking;

	public SurvivalArmorItem(ArmorMaterial material, Type type, Properties properties, Item brokenItem) {
		super(material, type, properties);
		this.becomesUponBreaking = brokenItem;
	}

	@Override
	public boolean canWalkOnPowderedSnow(ItemStack stack, LivingEntity wearer) {
		return stack.is(AMItemTags.SURVIVAL_FEET);
	}

	@Override
	public String getArmorTexture(ItemStack itemStack, Entity entity, EquipmentSlot slot, String layer) {
		// System.out.println(layer);
		String lay = slot == EquipmentSlot.LEGS ? "2" : "1";
		return AlienMod.MODID + ":textures/models/armor/" + this.getMaterial().getName() + "_layer_" + lay + ".png";
	}

	@Override
	public <T extends LivingEntity> int damageItem(ItemStack itemStack, int amount, T entity, Consumer<T> onBroken) {
		if (entity instanceof ServerPlayer player) {
			Component originalName = itemStack.getHoverName();

			// player.sendSystemMessage(Component.literal(String.valueOf(itemStack.getDamageValue())));
			/*
			 * Checks if the damage that is incoming would result in it breaking. Also
			 * Checks if it's damage value is it's max value, what it would break at.
			 */
			if (itemStack.getDamageValue() + amount >= itemStack.getMaxDamage()
					&& itemStack.getDamageValue() != itemStack.getMaxDamage()) {
				if (this.getEquipmentSlot() != null) {
					/*
					 * Sets the damage value to it's max damage value so that I can checks if I've
					 * already executed this when I use hurtAndBreak() because it executes this and
					 * I don't want it doing this part again because it will then infinetley loop.
					 */
					itemStack.setDamageValue(itemStack.getMaxDamage());
					int index = this.getEquipmentSlot().getIndex(Inventory.INVENTORY_SIZE);
					/*
					 * Makes try and break.
					 */
					itemStack.hurtAndBreak(amount, entity, onBroken);
					/*
					 * Sets the item to what it breaks into.
					 */
					// if (itemStack.hasCustomHoverName())
					// player.getInventory().setItem(index,
					// this.getBecomesUponBreaking().getDefaultInstance().setHoverName(originalName));
					// else
					player.getInventory().setItem(index, this.getBecomesUponBreaking().getDefaultInstance());
					return amount;
				}
			}
		}
		return super.damageItem(itemStack, amount, entity, onBroken);
	}

	public Item getBecomesUponBreaking() {
		return becomesUponBreaking;
	}
}