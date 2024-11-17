package com.mudkipboy7.alien.world.item.functional.wearable;

import java.util.List;

import com.mudkipboy7.alien.data.AMLanguage;
import com.mudkipboy7.alien.data.tags.AMItemTags;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Equipable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class SurvivalGearItem extends Item implements Equipable, ISurvivalItem {

	private final EquipmentSlot equipment;
	private final SoundEvent equipSound;

	public SurvivalGearItem(EquipmentSlot slot, SoundEvent sound, Properties properties) {
		super(properties);
		this.equipment = slot;
		this.equipSound = sound;
	}

	@Override
	public EquipmentSlot getEquipmentSlot() {
		return equipment;
	}

	@Override
	public SoundEvent getEquipSound() {
		return equipSound;
	}

	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		return this.swapWithEquipmentSlot(this, level, player, hand);
	}

	@Override
	public void appendHoverText(ItemStack itemStack, Level level, List<Component> component, TooltipFlag tooltipFlag) {
		component.add(AMLanguage.getDescription(this));
	}

	@Override
	public boolean canWalkOnPowderedSnow(ItemStack stack, LivingEntity wearer) {
		return stack.is(AMItemTags.SURVIVAL_FEET);
	}
}
