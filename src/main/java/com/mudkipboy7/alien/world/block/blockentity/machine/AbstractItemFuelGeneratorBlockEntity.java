package com.mudkipboy7.alien.world.block.blockentity.machine;

import com.mudkipboy7.alien.world.energy.AMEnergyStorage;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public abstract class AbstractItemFuelGeneratorBlockEntity extends AbstractGeneratorMachineBlockEntity {
	// The burn time remaining
	int burnTimeLeft = 0;
	// The burn time of the fuel last used
	int burnTimeOfFuel = 0;
	// The conversion rate from burn time to power
	float efficiency = 1.0F;
	// The amount of power that can be produced in one tick
	int speed = 1;

	public ContainerData dataAccess = new ContainerData() {
		public int get(int index) {
			return switch (index) {
			case 0 -> AbstractItemFuelGeneratorBlockEntity.this.getEnergy().getEnergyStored();
			case 1 -> AbstractItemFuelGeneratorBlockEntity.this.getEnergy().getMaxEnergyStored();
			case 2 -> AbstractItemFuelGeneratorBlockEntity.this.getBurnTimeOfFuel();
			case 3 -> AbstractItemFuelGeneratorBlockEntity.this.getBurnTimeLeft();
			default -> throw new IllegalArgumentException("Unexpected value: " + index);
			};
		}

		@Override
		public void set(int index, int valueToSetTo) {
			switch (index) {
			case 0 -> AbstractItemFuelGeneratorBlockEntity.this.getEnergy().receiveEnergy(valueToSetTo, remove);
			case 1 -> AbstractItemFuelGeneratorBlockEntity.this.getEnergy().extractEnergy(valueToSetTo, remove);
			default -> throw new IllegalArgumentException("Unexpected value: " + index);
			}
			;
		}

		@Override
		public int getCount() {
			return 4;
		}
	};

	public AbstractItemFuelGeneratorBlockEntity(BlockEntityType<?> type, BlockPos blockPos, BlockState blockState,
			AMEnergyStorage energy, NonNullList<ItemStack> inv, float conversionRate, int powerPerTick) {
		super(type, blockPos, blockState, energy, inv);
		this.efficiency = conversionRate;
		this.speed = powerPerTick;
	}

	@Override
	protected void saveAdditional(CompoundTag nbt) {
		super.saveAdditional(nbt);
		nbt.putInt("BurnTimeofFuel", this.burnTimeOfFuel);
		nbt.putInt("BurnTimeLeft", this.burnTimeLeft);
	}

	@Override
	public void load(CompoundTag nbt) {
		super.load(nbt);
		this.burnTimeOfFuel = nbt.getInt("BurnTimeofFuel");
		this.burnTimeLeft = nbt.getInt("BurnTimeLeft");
	}

	public void tickFuel(ItemStack fuelItemStack) {
		AMEnergyStorage energyStorage = this.energyStorage;
		if (!this.isBurning()) {
			this.setBurnTimeOfFuel(0);
			int burnTimeToAdd = Math.round(
					((float) net.minecraftforge.common.ForgeHooks.getBurnTime(fuelItemStack, RecipeType.SMELTING))
							* this.efficiency);
			if (burnTimeToAdd > 0 && energyStorage.hasEnergyStorageToReceive(burnTimeToAdd)) {

				this.setBurnTimeLeft(burnTimeToAdd);
				this.setBurnTimeOfFuel(burnTimeToAdd);
				if (fuelItemStack.getItem() == Items.LAVA_BUCKET) {
					this.inventory.set(1, Items.BUCKET.getDefaultInstance());
				} else {
					fuelItemStack.shrink(1);
				}
			}
		} else if (this.isBurning()) {
			// Adds the energy
			// System.out.println("ewefwfew");
			energyStorage.receiveEnergy(this.speed, false);
			// tryAddEnergy(energyStorage, this.getSpeed());

			// Checks if the produced value would be negative if it is sets it to 0
			this.setBurnTimeLeft(Math.max(0, this.burnTimeLeft - this.speed));
		}
	}

	protected boolean isBurning() {
		return this.burnTimeLeft > 0;
	}

	public int getBurnTimeOfFuel() {
		return this.burnTimeOfFuel;
	}

	public void setBurnTimeOfFuel(int value) {
		this.burnTimeOfFuel = value;
	}

	public int getBurnTimeLeft() {
		return this.burnTimeLeft;
	}

	public void setBurnTimeLeft(int value) {
		this.burnTimeLeft = value;
	}

	public float getEfficiency() {
		return this.efficiency;
	}

	public int getSpeed() {
		return this.speed;
	}
}
