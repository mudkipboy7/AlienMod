package mudkipboy7.alien.world.block.blockentity.machine;

import mudkipboy7.alien.data.AMLanguage;
import mudkipboy7.alien.inventory.menu.CoalGeneratorMenu;
import mudkipboy7.alien.inventory.menu.slot.MachineBatterySlot;
import mudkipboy7.alien.inventory.menu.slot.MachineFuelSlot;
import mudkipboy7.alien.world.block.blockentity.AMBlockEntities;
import mudkipboy7.alien.world.block.functional.machine.CoalGeneratorBlock;
import mudkipboy7.alien.world.energy.AMEnergyStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class CoalGeneratorBlockEntity extends AbstractItemFuelGeneratorBlockEntity {

	public CoalGeneratorBlockEntity(BlockPos blockPos, BlockState blockState) {
		super(AMBlockEntities.COAL_GENERATOR.get(), blockPos, blockState,
				// The energy storage
				new AMEnergyStorage(30000),
				// The inventory
				NonNullList.withSize(2, ItemStack.EMPTY),
				// Conversion rate from fuels to energy
				1.5F,
				// Power produced per tick
				6);
	}
	public static void tick(Level level, BlockPos blockPos, BlockState blockState,
			CoalGeneratorBlockEntity blockEntity) {
		blockState = blockState.setValue(CoalGeneratorBlock.LIT, Boolean.valueOf(blockEntity.isBurning()));
		level.setBlock(blockPos, blockState, 2);
		setChanged(level, blockPos, blockState);
		ItemStack fuelSlot = blockEntity.inventory.get(1);
		blockEntity.tickFuel(fuelSlot);
		ItemStack batteryOutputSlot = blockEntity.inventory.get(0);
		blockEntity.chargeBatteryItemStack(batteryOutputSlot);
	}

	@Override
	protected Component getDefaultName() {
		return AMLanguage.coalGeneratorContainerName;
	}

	@Override
	protected AbstractContainerMenu createMenu(int containerId, Inventory inventory) {
		return new CoalGeneratorMenu(containerId, inventory, this, this.dataAccess);
	}

	@Override
	public boolean canPlaceItem(int index, ItemStack stack) {
		if ((index == 0 && !MachineBatterySlot.canBePlaced(stack))
				|| (index == 1 && !MachineFuelSlot.canBePlaced(stack)))
			return false;
		else
			return true;
	}

}
