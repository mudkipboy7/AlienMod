package com.mudkipboy7.alien.world.energy.networking;

@Deprecated
interface INetworkController extends INetworkable {
	public boolean isCurrentlyController();

	public void clearNetwork();

	public IResourceNetwork getNetwork();
}
