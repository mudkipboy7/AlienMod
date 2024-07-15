package mudkipboy7.alien.world.block.blockentity.machine;

import mudkipboy7.alien.data.AMLanguage;
import mudkipboy7.alien.inventory.menu.EnergyStorageMenu;
import mudkipboy7.alien.inventory.menu.slot.MachineBatterySlot;
import mudkipboy7.alien.world.block.blockentity.AMBlockEntities;
import mudkipboy7.alien.world.block.blockentity.machine.interfaces.lazer.ILazerAcceptor;
import mudkipboy7.alien.world.block.blockentity.machine.interfaces.lazer.ILazerCanExtractFrom;
import mudkipboy7.alien.world.energy.AMEnergyMethods;
import mudkipboy7.alien.world.energy.AMEnergyStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.energy.EnergyStorage;

public class EnergyStorageBlockEntity extends AbstractContainerMachineBlockEntity
		implements ILazerAcceptor, ILazerCanExtractFrom {

	public ContainerData dataAccess = new ContainerData() {
		public int get(int index) {
			return switch (index) {
			case 0 -> EnergyStorageBlockEntity.this.getEnergy().getEnergyStored();
			case 1 -> EnergyStorageBlockEntity.this.getEnergy().getMaxEnergyStored();
			default -> throw new IllegalArgumentException("Unexpected value: " + index);
			};
		}

		@Override
		public void set(int index, int valueToSetTo) {
			switch (index) {
			case 0 -> EnergyStorageBlockEntity.this.getEnergy().receiveEnergy(valueToSetTo, remove);
			case 1 -> EnergyStorageBlockEntity.this.getEnergy().extractEnergy(valueToSetTo, remove);
			default -> throw new IllegalArgumentException("Unexpected value: " + index);
			}
			;
		}

		@Override
		public int getCount() {
			return 2;
		}
	};

	public EnergyStorageBlockEntity(BlockPos blockPos, BlockState blockState) {
		super(AMBlockEntities.ENERGY_STORAGE.get(), blockPos, blockState,
				// The energy storage
				new AMEnergyStorage(100000),
				// The inventory
				NonNullList.withSize(2, ItemStack.EMPTY));
	}

	public EnergyStorageBlockEntity(BlockEntityType<?> type, BlockPos blockPos, BlockState blockState,
			AMEnergyStorage energy) {
		super(type, blockPos, blockState,
				// The energy storage
				energy,
				// The inventory
				NonNullList.withSize(2, ItemStack.EMPTY));
	}

	public static void tick(Level level, BlockPos blockPos, BlockState blockState,
			EnergyStorageBlockEntity blockEntity) {
		ItemStack batteryInputSlot = blockEntity.inventory.get(0);
		blockEntity.chargeMachineFromBatteryItemStack(batteryInputSlot);
		ItemStack batteryOutputSlot = blockEntity.inventory.get(1);
		blockEntity.chargeBatteryItemStack(batteryOutputSlot);
	}

	@Override
	protected Component getDefaultName() {
		return AMLanguage.energyStorageBlockName;
	}

	@Override
	protected AbstractContainerMenu createMenu(int containerId, Inventory inventory) {
		return new EnergyStorageMenu(containerId, inventory, this, this.dataAccess);
	}

	@Override
	public boolean canPlaceItem(int pIndex, ItemStack pStack) {
		if (pIndex == 0 || pIndex == 1 && !MachineBatterySlot.canBePlaced(pStack))
			return false;
		else
			return true;
	}

	@Override
	public int tryAcceptEnergy(EnergyStorage acceptingFrom) {
		return AMEnergyMethods.tryExchangeEnergy(this.getEnergy(), acceptingFrom, 2);
	}
}
