package com.mudkipboy7.alien.world.block.blockentity.machine;

import com.mudkipboy7.alien.data.AMLanguage;
import com.mudkipboy7.alien.inventory.menu.HazardRemovalMenu;
import com.mudkipboy7.alien.inventory.menu.slot.MachineBatterySlot;
import com.mudkipboy7.alien.inventory.menu.slot.MachineWaterSlot;
import com.mudkipboy7.alien.world.WorldFuncs;
import com.mudkipboy7.alien.world.block.blockentity.AMBlockEntities;
import com.mudkipboy7.alien.world.block.blockentity.IHazardRemovalBlockEntity;
import com.mudkipboy7.alien.world.block.blockentity.machine.interfaces.lazer.ILazerAcceptor;
import com.mudkipboy7.alien.world.energy.AMEnergyMethods;
import com.mudkipboy7.alien.world.energy.AMEnergyStorage;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.energy.EnergyStorage;

public class HazardRemovalMachineBlockEntity extends AbstractContainerMachineBlockEntity
		implements ILazerAcceptor, IHazardRemovalBlockEntity {

	private static final int ENERGY_COST_TO_APPLY_EFFECT = 1;

	private static final int TICKS_IN_BETWEEN_CHECKS = 3;
	private int ticksSinceLastCheck = TICKS_IN_BETWEEN_CHECKS;

	public static final double EFFECT_RADIUS = 8.0D;

	public ContainerData dataAccess = new ContainerData() {
		public int get(int index) {
			return switch (index) {
			case 0 -> HazardRemovalMachineBlockEntity.this.getEnergy().getEnergyStored();
			case 1 -> HazardRemovalMachineBlockEntity.this.getEnergy().getMaxEnergyStored();
			default -> throw new IllegalArgumentException("Unexpected value: " + index);
			};
		}

		@Override
		public void set(int index, int valueToSetTo) {
			switch (index) {
			case 0 -> HazardRemovalMachineBlockEntity.this.getEnergy().receiveEnergy(valueToSetTo, remove);
			case 1 -> HazardRemovalMachineBlockEntity.this.getEnergy().extractEnergy(valueToSetTo, remove);
			default -> throw new IllegalArgumentException("Unexpected value: " + index);
			}
			;
		}

		@Override
		public int getCount() {
			return 2;
		}
	};

	public HazardRemovalMachineBlockEntity(BlockPos blockPos, BlockState blockState) {
		super(AMBlockEntities.HAZARD_REMOVER.get(), blockPos, blockState,
				// The energy storage
				new AMEnergyStorage(8000),
				// The inventory
				NonNullList.withSize(2, ItemStack.EMPTY));
	}

	public static void tick(Level level, BlockPos blockPos, BlockState blockState,
			HazardRemovalMachineBlockEntity blockEntity) {
		blockEntity.applyEffects(level, blockPos, blockState);
		ItemStack batteryInputSlot = blockEntity.inventory.get(0);
		blockEntity.chargeMachineFromBatteryItemStack(batteryInputSlot);
	}

	@Override
	public boolean shouldCheck(Level level, BlockPos blockPos, BlockState blockState) {
		if (WorldFuncs.inUltracoldBiome(level, blockPos)) {
			if (this.ticksSinceLastCheck >= TICKS_IN_BETWEEN_CHECKS) {
				if (this.energyStorage.getEnergyStored() >= ENERGY_COST_TO_APPLY_EFFECT) {
					return true;
				}
			} else {
				this.ticksSinceLastCheck += 1;
			}
		}
		return false;
	}

	@Override
	public void onEffectsApplied(Level level, BlockPos blockPos, BlockState blockState) {
		this.energyStorage.extractEnergy(ENERGY_COST_TO_APPLY_EFFECT, false);
		this.ticksSinceLastCheck = 0;
	}

	@Override
	protected Component getDefaultName() {
		return AMLanguage.airPuriferContainerName;
	}

	@Override
	protected AbstractContainerMenu createMenu(int containerId, Inventory inventory) {
		return new HazardRemovalMenu(containerId, inventory, this, this.dataAccess);
	}

	@Override
	public boolean canPlaceItem(int index, ItemStack stack) {
		if ((index == 0 && !MachineBatterySlot.canBePlaced(stack))
				|| (index == 1 && !MachineWaterSlot.canBePlaced(stack)))
			return false;
		else
			return true;
	}

	@Override
	public int tryAcceptEnergy(EnergyStorage acceptingFrom) {
		return AMEnergyMethods.tryExchangeEnergy(this.getEnergy(), acceptingFrom, 5);
	}

	@Override
	public double getEffectRadius() {
		return EFFECT_RADIUS;
	}
}
