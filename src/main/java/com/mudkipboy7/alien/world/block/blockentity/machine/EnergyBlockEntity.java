package com.mudkipboy7.alien.world.block.blockentity.machine;

import com.mudkipboy7.alien.data.AMLanguage;
import com.mudkipboy7.alien.inventory.menu.EnergyBlockMenu;
import com.mudkipboy7.alien.inventory.menu.EnergyStorageMenu;
import com.mudkipboy7.alien.inventory.menu.slot.MachineBatterySlot;
import com.mudkipboy7.alien.world.block.blockentity.AMBlockEntities;
import com.mudkipboy7.alien.world.block.blockentity.machine.interfaces.IEnergyStoringMachineBlockEntity;
import com.mudkipboy7.alien.world.block.blockentity.machine.interfaces.lazer.ILazerAcceptor;
import com.mudkipboy7.alien.world.block.blockentity.machine.interfaces.lazer.ILazerCanExtractFrom;
import com.mudkipboy7.alien.world.energy.AMEnergyMethods;
import com.mudkipboy7.alien.world.energy.AMEnergyStorage;
import com.mudkipboy7.alien.world.energy.AMInfiniteEnergyStorage;
import com.mudkipboy7.alien.world.item.functional.BatteryItem;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.EnergyStorage;

public class EnergyBlockEntity extends AbstractContainerMachineBlockEntity implements ILazerCanExtractFrom {

	public ContainerData dataAccess = new ContainerData() {
		public int get(int index) {
			return switch (index) {
			case 0 -> EnergyBlockEntity.this.getEnergy().getEnergyStored();
			case 1 -> EnergyBlockEntity.this.getEnergy().getMaxEnergyStored();
			default -> throw new IllegalArgumentException("Unexpected value: " + index);
			};
		}

		@Override
		public void set(int index, int valueToSetTo) {
			switch (index) {
			case 0 -> EnergyBlockEntity.this.getEnergy().extractEnergy(valueToSetTo, remove);
			default -> throw new IllegalArgumentException("Unexpected value: " + index);
			}
			;
		}

		@Override
		public int getCount() {
			return 1;
		}
	};

	public EnergyBlockEntity(BlockPos blockPos, BlockState blockState) {
		super(AMBlockEntities.ENERGY_BLOCK.get(), blockPos, blockState,
				// The energy storage
				new AMInfiniteEnergyStorage(),
				// The inventory
				NonNullList.withSize(1, ItemStack.EMPTY));
	}

	public EnergyBlockEntity(BlockEntityType<?> type, BlockPos blockPos, BlockState blockState,
			AMEnergyStorage energy) {
		super(type, blockPos, blockState,
				// The energy storage
				energy,
				// The inventory
				NonNullList.withSize(1, ItemStack.EMPTY));
	}

	public static void tick(Level level, BlockPos blockPos, BlockState blockState, EnergyBlockEntity blockEntity) {
		ItemStack batteryOutputSlot = blockEntity.inventory.get(0);
		// constantly recharges it so it never runs out of energy
		blockEntity.getEnergy().receiveEnergy(Integer.MAX_VALUE, false);
		blockEntity.chargeBatteryItemStack(batteryOutputSlot);
	}

	@Override
	protected Component getDefaultName() {
		return AMLanguage.energyBlockName;
	}

	@Override
	protected AbstractContainerMenu createMenu(int containerId, Inventory inventory) {
		return new EnergyBlockMenu(containerId, inventory, this, this.dataAccess);
	}

	@Override
	public boolean canPlaceItem(int pIndex, ItemStack pStack) {
		if (pIndex == 0 && !MachineBatterySlot.canBePlaced(pStack))
			return false;
		else
			return true;
	}

	@Override
	public void chargeMachineFromBatteryItemStack(ItemStack chargingSlot) {
	}

	@Override
	public boolean canConnectInDirection(Direction direction) {
		return (direction == Direction.DOWN || direction == Direction.UP);

	}
}
