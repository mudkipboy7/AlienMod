package mudkipboy7.alien.world.energy.networking;

@Deprecated
public interface INetworkController extends INetworkable {
	public boolean isCurrentlyController();
	public void clearNetwork();
	public IResourceNetwork getNetwork();
}
