package mudkipboy7.alien.world.block.blockentity.machine;

import java.util.List;

import mudkipboy7.alien.data.AMLanguage;
import mudkipboy7.alien.data.tags.AMBiomeTags;
import mudkipboy7.alien.data.tags.AMEntityTypeTags;
import mudkipboy7.alien.data.tags.AMItemTags;
import mudkipboy7.alien.inventory.menu.HazardRemovalMenu;
import mudkipboy7.alien.inventory.menu.slot.MachineBatterySlot;
import mudkipboy7.alien.inventory.menu.slot.MachineWaterSlot;
import mudkipboy7.alien.world.block.blockentity.AMBlockEntities;
import mudkipboy7.alien.world.block.blockentity.machine.interfaces.lazer.ILazerAcceptor;
import mudkipboy7.alien.world.effect.AMMobEffects;
import mudkipboy7.alien.world.energy.AMEnergyMethods;
import mudkipboy7.alien.world.energy.AMEnergyStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.energy.EnergyStorage;

public class HazardRemoverBlockEntity extends AbstractContainerMachineBlockEntity implements ILazerAcceptor {

	private static final int ENERGY_COST_TO_APPLY_EFFECT = 1;

	private static final int TICKS_IN_BETWEEN_CHECKS = 3;
	private int ticksSinceLastCheck = TICKS_IN_BETWEEN_CHECKS;

	public static final double EFFECT_RADIUS = 8.0D;

	public ContainerData dataAccess = new ContainerData() {
		public int get(int index) {
			return switch (index) {
			case 0 -> HazardRemoverBlockEntity.this.getEnergy().getEnergyStored();
			case 1 -> HazardRemoverBlockEntity.this.getEnergy().getMaxEnergyStored();
			default -> throw new IllegalArgumentException("Unexpected value: " + index);
			};
		}

		@Override
		public void set(int index, int valueToSetTo) {
			switch (index) {
			case 0 -> HazardRemoverBlockEntity.this.getEnergy().receiveEnergy(valueToSetTo, remove);
			case 1 -> HazardRemoverBlockEntity.this.getEnergy().extractEnergy(valueToSetTo, remove);
			default -> throw new IllegalArgumentException("Unexpected value: " + index);
			}
			;
		}

		@Override
		public int getCount() {
			return 2;
		}
	};

	public HazardRemoverBlockEntity(BlockPos blockPos, BlockState blockState) {
		super(AMBlockEntities.HAZARD_REMOVER.get(), blockPos, blockState,
				// The energy storage
				new AMEnergyStorage(8000),
				// The inventory
				NonNullList.withSize(2, ItemStack.EMPTY));
	}

	public static void tick(Level level, BlockPos blockPos, BlockState blockState,
			HazardRemoverBlockEntity blockEntity) {
		blockEntity.applyEffects(level, blockPos, blockState);
		ItemStack batteryInputSlot = blockEntity.inventory.get(0);
		blockEntity.chargeMachineFromBatteryItemStack(batteryInputSlot);
	}

	private void applyEffects(Level level, BlockPos blockPos, BlockState blockState) {

		if (!level.isClientSide) {
			// Checks if its in a valid biome
			if (level.getBiome(blockPos).is(AMBiomeTags.ULTRACOLD)) {
				boolean appliedEffects = false;
				// Checks if enough time has passed
				if (this.ticksSinceLastCheck >= TICKS_IN_BETWEEN_CHECKS) {
					// Checks if it has enough energy to apply
					if (this.energyStorage.getEnergyStored() >= ENERGY_COST_TO_APPLY_EFFECT) {
						AABB aabb = (new AABB(blockPos)).inflate(EFFECT_RADIUS);
						List<LivingEntity> list = level.getEntitiesOfClass(LivingEntity.class, aabb);
						// Goes over each entity and applies the effects,
						for (LivingEntity livingEntity : list)
							if (needsProtection(livingEntity)) {
								appliedEffects = true;
								livingEntity.addEffect(new MobEffectInstance(AMMobEffects.HAZARD_PROTECTION.get(), 60,
										0, true, true, true));
							}
						// If any effects were applied it extracts energy
						if (appliedEffects) {
							this.energyStorage.extractEnergy(ENERGY_COST_TO_APPLY_EFFECT, false);
							// Resets the tick counter to 0 and ends code
							this.ticksSinceLastCheck = 0;
						}
					}
					// Ends the code so blockEntity.ticksSinceLastCheck doesn't increase.
					return;
				}
				/*
				 * If there haven't been enough ticks to apply increments
				 * blockEntity.ticksSinceLastCheck by one.
				 */
				else
					this.ticksSinceLastCheck += 1;
			}
		}
	}

	public static boolean needsProtection(LivingEntity livingEntity) {
		boolean hasSurvivalGear = livingEntity.getItemBySlot(EquipmentSlot.HEAD).is(AMItemTags.SURVIVAL_HEAD)
				&& livingEntity.getItemBySlot(EquipmentSlot.CHEST).is(AMItemTags.SURVIVAL_TORSO)
				&& livingEntity.getItemBySlot(EquipmentSlot.LEGS).is(AMItemTags.SURVIVAL_LEGS);

		return !(hasSurvivalGear || livingEntity.getType().is(AMEntityTypeTags.COLD_BLOODED))
				&& !(livingEntity instanceof Player player && (player.isSpectator() || player.isCreative()));
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
}
