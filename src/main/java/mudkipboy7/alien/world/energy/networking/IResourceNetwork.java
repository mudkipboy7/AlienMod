package mudkipboy7.alien.world.energy.networking;

import java.util.ArrayList;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

@Deprecated
public interface IResourceNetwork {
	public Level getLevel();

	public boolean setController(BlockPos blockPos);

	public BlockPos getController();

	public ArrayList<BlockPos> getPotentialControllers();

	public void addTransmitter(BlockPos blockPos);

	public ArrayList<BlockPos> getTransmitters();

	public void addMachine(BlockPos blockPos);

	public ArrayList<BlockPos> getMachines();

	public boolean tryChangeController();

	public void refreshNetwork();

	public void breakController();
}
