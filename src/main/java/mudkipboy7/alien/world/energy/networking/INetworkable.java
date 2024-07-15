package mudkipboy7.alien.world.energy.networking;

import net.minecraft.core.BlockPos;

@Deprecated
public interface INetworkable {
	public abstract void setControllerPos(BlockPos blockPos);
	public abstract BlockPos getControllerPos();
	public default boolean canConnectTo(BlockPos blockPos) {
		return true;
	}
}
