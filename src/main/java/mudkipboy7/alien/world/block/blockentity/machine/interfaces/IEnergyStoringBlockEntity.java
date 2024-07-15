package mudkipboy7.alien.world.block.blockentity.machine.interfaces;

import mudkipboy7.alien.world.energy.AMEnergyStorage;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.EnergyStorage;

public interface IEnergyStoringBlockEntity extends IMachineBlockEntity{
	public abstract EnergyStorage getEnergy();
	public abstract LazyOptional<AMEnergyStorage> getEnergyOptional();

}
