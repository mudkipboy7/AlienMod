package com.mudkipboy7.alien.world.energy.networking;

import java.util.ArrayList;

import com.mudkipboy7.alien.world.block.blockentity.machine.interfaces.IEnergyStoringMachineBlockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

@Deprecated
class ElectricalNetwork implements IResourceNetwork {
	private Level level;
	private BlockPos controller;
	private ArrayList<BlockPos> potentialControllers = new ArrayList<>();
	private ArrayList<BlockPos> transmitters = new ArrayList<>();
	private ArrayList<BlockPos> machines = new ArrayList<>();

	public ElectricalNetwork(Level level, BlockPos controllerPos) {
		this.level = level;
		this.controller = controllerPos;
	}

	@Override
	public Level getLevel() {
		return this.level;
	}

	@Override
	public boolean setController(BlockPos blockPos) {
		if (this.level.getBlockEntity(blockPos) instanceof INetworkController) {
			this.controller = blockPos;
			this.addMachine(blockPos);
			return true;
		}
		return false;
	}

	@Override
	public BlockPos getController() {
		return this.controller;
	}

	@Override
	public ArrayList<BlockPos> getPotentialControllers() {
		return this.potentialControllers;
	}

	@Override
	public void addTransmitter(BlockPos blockPos) {
		if (this.level.getBlockEntity(blockPos) instanceof INetworkTransmitter trans
				&& !this.transmitters.contains(blockPos)) {
			this.transmitters.add(blockPos);
			trans.setControllerPos(this.controller);
		}

	}

	@Override
	public ArrayList<BlockPos> getTransmitters() {
		return transmitters;
	}

	@Override
	public void addMachine(BlockPos blockPos) {
		if (this.level.getBlockEntity(blockPos) instanceof INetworkable machine
				&& this.level.getBlockEntity(blockPos) instanceof IEnergyStoringMachineBlockEntity
				&& !this.machines.contains(blockPos)) {
			this.machines.add(blockPos);
			machine.setControllerPos(this.controller);
			if (level.getBlockEntity(blockPos) instanceof INetworkController
					&& !this.potentialControllers.contains(blockPos)) {
				this.potentialControllers.add(blockPos);
			}
		}
	}

	@Override
	public ArrayList<BlockPos> getMachines() {
		return this.machines;
	}

	@Override
	public boolean tryChangeController() {
		if (this.controller == null)
			for (BlockPos blockPos : potentialControllers) {
				if (!setController(blockPos)) {
					this.potentialControllers.remove(blockPos);
				} else {
					return true;
				}
			}
		return false;
	}

	@Override
	public void refreshNetwork() {
		var controller = level.getBlockEntity(this.controller);
		for (BlockPos blockPos : transmitters)
			if (level.getBlockEntity(blockPos) instanceof INetworkable net)
				net.setControllerPos(null);
		for (BlockPos blockPos : machines)
			if (level.getBlockEntity(blockPos) instanceof INetworkable net)
				net.setControllerPos(null);
		if (controller instanceof INetworkController con) {
			con.clearNetwork();
		}
	}

	@Override
	public void breakController() {
		this.setController(null);
		if (!this.tryChangeController())
			refreshNetwork();
	}
}
