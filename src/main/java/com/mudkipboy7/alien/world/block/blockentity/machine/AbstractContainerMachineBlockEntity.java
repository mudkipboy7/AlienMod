package com.mudkipboy7.alien.world.block.blockentity.machine;

import org.jetbrains.annotations.NotNull;

import com.mudkipboy7.alien.AlienMod;
import com.mudkipboy7.alien.world.block.blockentity.machine.interfaces.IEnergyStoringMachineBlockEntity;
import com.mudkipboy7.alien.world.energy.AMEnergyMethods;
import com.mudkipboy7.alien.world.energy.AMEnergyStorage;
import com.mudkipboy7.alien.world.item.functional.BatteryItem;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.EnergyStorage;

public abstract class AbstractContainerMachineBlockEntity extends BaseContainerBlockEntity
		implements IEnergyStoringMachineBlockEntity {
	// The inventory
	NonNullList<ItemStack> inventory;
	// The energy storage
	AMEnergyStorage energyStorage;
	LazyOptional<AMEnergyStorage> energyOptional = LazyOptional.of(() -> this.energyStorage);

	public AbstractContainerMachineBlockEntity(BlockEntityType<?> type, BlockPos blockPos, BlockState blockState,
			AMEnergyStorage energy, NonNullList<ItemStack> inv) {
		super(type, blockPos, blockState);
		this.inventory = inv;
		this.energyStorage = energy;
	}

	public AbstractContainerMachineBlockEntity(BlockEntityType<?> type, BlockPos blockPos, BlockState blockState,
			AMEnergyStorage energy) {
		this(type, blockPos, blockState, energy, NonNullList.withSize(1, ItemStack.EMPTY));
	}

	@Override
	protected void saveAdditional(CompoundTag nbt) {
		super.saveAdditional(nbt);
		ContainerHelper.saveAllItems(nbt, this.inventory);
		nbt.put(AlienMod.ENERGY, this.energyStorage.serializeNBT());
	}

	@Override
	public void load(CompoundTag nbt) {
		super.load(nbt);
		this.inventory = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
		ContainerHelper.loadAllItems(nbt, this.inventory);
		if (nbt.contains(AlienMod.ENERGY, Tag.TAG_INT)) {
			this.energyStorage.deserializeNBT(nbt.get(AlienMod.ENERGY));
		}
	}

	@Override
	public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability) {
		if (capability == ForgeCapabilities.ENERGY) {
			return this.energyOptional.cast();
		} else {
			return super.getCapability(capability);
		}
	}

	@Override
	public void invalidateCaps() {
		super.invalidateCaps();
		this.energyOptional.invalidate();
	}

	@Override
	public int getContainerSize() {
		return this.inventory.size();
	}

	@Override
	public boolean isEmpty() {
		for (ItemStack itemstack : this.inventory) {
			if (!itemstack.isEmpty()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public ItemStack getItem(int slot) {
		return this.inventory.get(slot);
	}

	@Override
	public ItemStack removeItem(int slotId, int ammountToRemove) {
		return ContainerHelper.removeItem(this.inventory, slotId, ammountToRemove);
	}

	@Override
	public ItemStack removeItemNoUpdate(int slotId) {
		return ContainerHelper.takeItem(this.inventory, slotId);
	}

	@Override
	public void setItem(int slotId, ItemStack itemStack) {
		// ItemStack itemstack = this.inventory.get(pSlot);
		// boolean flag = !pStack.isEmpty() && ItemStack.isSameItemSameTags(itemstack,
		// pStack);
		this.inventory.set(slotId, itemStack);
		if (itemStack.getCount() > this.getMaxStackSize()) {
			itemStack.setCount(this.getMaxStackSize());
		}
	}

	@Override
	public boolean stillValid(Player player) {
		return Container.stillValidBlockEntity(this, player);
	}

	@Override
	public void clearContent() {
		this.inventory.clear();
	}

	public void fillStackedContents(StackedContents helper) {
		for (ItemStack itemstack : this.inventory) {
			helper.accountStack(itemstack);
		}
	}

	public void chargeBatteryItemStack(ItemStack chargingSlot) {
		chargingSlot.getCapability(ForgeCapabilities.ENERGY).ifPresent(batteryEnergy -> {
			if (chargingSlot.getItem() instanceof BatteryItem) {
				AMEnergyMethods.tryExchangeEnergy(batteryEnergy, this.energyStorage);
			}
		});
	}

	public void chargeMachineFromBatteryItemStack(ItemStack chargingSlot) {
		chargingSlot.getCapability(ForgeCapabilities.ENERGY).ifPresent(batteryEnergy -> {
			if (chargingSlot.getItem() instanceof BatteryItem) {
				AMEnergyMethods.tryExchangeEnergy(this.energyStorage, batteryEnergy);
			}
		});
	}

	@Override
	public EnergyStorage getEnergy() {

		return this.energyStorage;
	}

	@Override
	public LazyOptional<AMEnergyStorage> getEnergyOptional() {
		return this.energyOptional;
	}
}
