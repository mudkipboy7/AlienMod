package mudkipboy7.alien.world.block.blockentity.machine.interfaces.lazer;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraftforge.energy.EnergyStorage;

public interface ILazerCreator {

	public BlockPos searchForCharagbles();

	public int sendEnergyTo(BlockPos blockPos, EnergyStorage extractingFrom, Direction directionSendingTowards);

}
