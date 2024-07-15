package mudkipboy7.alien.world.block.blockentity.machine.transport;

import mudkipboy7.alien.world.block.blockentity.AMBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class WireBlockEntity extends BlockEntity /* implements INetworkTransmitter */ {
	// private BlockPos controller;

	@SuppressWarnings("deprecation")
	public WireBlockEntity(BlockPos blockPos, BlockState blockState) {
		super(AMBlockEntities.COPPER_WIRE.get(), blockPos, blockState);
	}

	public static void tick(Level level, BlockPos blockPos, BlockState blockState, WireBlockEntity blockEntity) {
		// level.getBlockStatesIfLoaded(INFINITE_EXTENT_AABB)
	}

	/*
	 * protected void tryAddWireToNetwork(WireBlockEntity blockEntity, Level level,
	 * BlockPos wireToAdd) { if
	 * (level.getBlockEntity(blockEntity.getControllerPos()) instanceof
	 * INetworkController controller && controller.getNetwork() != null) {
	 * controller.getNetwork().addTransmitter(wireToAdd);
	 * Minecraft.getInstance().player.sendSystemMessage(Component.
	 * translatable("Added a wire to a network")); } }
	 */

}
