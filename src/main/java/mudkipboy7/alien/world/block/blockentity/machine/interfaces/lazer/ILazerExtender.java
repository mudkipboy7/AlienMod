package mudkipboy7.alien.world.block.blockentity.machine.interfaces.lazer;

import net.minecraft.core.Direction;
import net.minecraftforge.energy.EnergyStorage;

public interface ILazerExtender extends ILazerCreator {
	public int tryExtendLazer(EnergyStorage extractingFrom, Direction directionOfSource);
}
