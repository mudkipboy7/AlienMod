package com.mudkipboy7.alien.world.item.functional;

import java.util.List;

import javax.annotation.Nonnull;

import org.jetbrains.annotations.Nullable;

import com.mudkipboy7.alien.client.gui.screen.AbstractMachineScreen;
import com.mudkipboy7.alien.data.AMLanguage;
import com.mudkipboy7.alien.world.energy.EnergyItemStorage;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;

public class BatteryItem extends Item {

	// The energy storage
	int storageCapacity = 1;

	public BatteryItem(Properties properties, int capacity) {
		super(properties);
		this.storageCapacity = capacity;
	}

	@Override
	public int getMaxStackSize(ItemStack stack) {
		return 1;
	}

	@Nullable
	@Override
	public ICapabilityProvider initCapabilities(ItemStack itemStack, @Nullable CompoundTag nbt) {
		return new ICapabilityProvider() {
			@Nonnull
			@Override
			public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
				if (cap == ForgeCapabilities.ENERGY) {
					return LazyOptional.of(() -> new EnergyItemStorage(itemStack, storageCapacity)

					).cast();
				}
				return LazyOptional.empty();
			}
		};
	}

	@Override
	public void appendHoverText(ItemStack itemStack, Level level, List<Component> component, TooltipFlag tooltipFlag) {
		if (ForgeCapabilities.ENERGY == null)
			return;
		itemStack.getCapability(ForgeCapabilities.ENERGY).ifPresent(energy -> {
			int energyStored = energy.getEnergyStored();
			int maxEnergy = energy.getMaxEnergyStored();
			component.add(AbstractMachineScreen.getValueOfResource(energyStored, maxEnergy));
		});
	}

	@Override
	public Component getName(ItemStack itemStack) {
		MutableComponent description = Component.empty();
		if (ForgeCapabilities.ENERGY == null)
			return Component.translatable(this.getDescriptionId(itemStack));
		itemStack.getCapability(ForgeCapabilities.ENERGY).ifPresent(energy -> {
			int energyStored = energy.getEnergyStored();
			int maxEnergy = energy.getMaxEnergyStored();
			float percentage = AbstractMachineScreen.calculatePercentage(energyStored, maxEnergy);
			TextColor color = AbstractMachineScreen.getColorFromPercent(percentage);
			MutableComponent comp = AMLanguage.getChargableLangName(asItem(), percentage);
			description.append(comp).withStyle((arg) -> arg.withColor(color));
		});
		return description;
	}

}
