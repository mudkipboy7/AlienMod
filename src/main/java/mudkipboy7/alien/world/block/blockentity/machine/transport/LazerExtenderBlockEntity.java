package mudkipboy7.alien.world.block.blockentity.machine.transport;

import mudkipboy7.alien.world.block.blockentity.AMBlockEntities;
import mudkipboy7.alien.world.block.blockentity.machine.interfaces.lazer.ILazerAcceptor;
import mudkipboy7.alien.world.block.blockentity.machine.interfaces.lazer.ILazerExtender;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.energy.EnergyStorage;

public class LazerExtenderBlockEntity extends BlockEntity implements ILazerExtender {
	private static final int MAX_SEARCH_RANGE = 10;
	public int ammountTransferedLastTick = 0;

	public LazerExtenderBlockEntity(BlockPos blockPos, BlockState blockState) {
		super(AMBlockEntities.LAZER_EXTENDER.get(), blockPos, blockState);
	}

	public static void tick(Level level, BlockPos blockPos, BlockState blockState,
			LazerExtenderBlockEntity blockEntity) {
	}

	@Override
	public BlockPos searchForCharagbles() {
		return null;
	}

	@Override
	public int tryExtendLazer(EnergyStorage extractingFrom, Direction directionOfSource) {
		/*
		 * Was originally going to have it go in many directions, but now I'll just make
		 * it make the lazer longer on in the same directions it's going in.
		 */
		int ammountSent = 0;
		ammountSent += trySendToDirection(directionOfSource, directionOfSource, extractingFrom);
		// ammountSent += trySendToDirection(Direction.DOWN, directionOfSource,
		// extractingFrom);
		// ammountSent += trySendToDirection(Direction.NORTH, directionOfSource,
		// extractingFrom);
		// ammountSent += trySendToDirection(Direction.SOUTH, directionOfSource,
		// extractingFrom);
		// ammountSent += trySendToDirection(Direction.WEST, directionOfSource,
		// extractingFrom);
		// ammountSent += trySendToDirection(Direction.EAST, directionOfSource,
		// extractingFrom);
		return ammountSent;

	}

	public BlockPos search(Direction directionToSearch) {
		for (int i = 1; i < MAX_SEARCH_RANGE; i++) {

			BlockPos beingChecked = this.getBlockPos().relative(directionToSearch, i);
			// System.out.println(beingChecked);
			// Minecraft.getInstance().player.sendSystemMessage(Component.literal(beingChecked.toString()));
			if (level.getBlockEntity(beingChecked) instanceof ILazerAcceptor
					|| level.getBlockEntity(beingChecked) instanceof ILazerExtender
							&& beingChecked != this.getBlockPos()) {
				System.out.println("fewf");
				return beingChecked;
			} else if (level.getBlockState(beingChecked).isAir()) {

			} else {
				return null;
				// break;
			}
		}
		return null;
	}

	public int trySendToDirection(Direction directionToSendTo, Direction directionOfSource,
			EnergyStorage extractingFrom) {
		if (directionOfSource != directionToSendTo) {
			BlockPos charging = search(directionToSendTo);
			if (charging != null) {
				return sendEnergyTo(charging, extractingFrom, directionToSendTo);
			}
		}
		return 0;
	}

	@Override
	public int sendEnergyTo(BlockPos blockPos, EnergyStorage extractingFrom, Direction directionSendingTowards) {
		if (extractingFrom != null && level.getBlockEntity(blockPos) instanceof ILazerExtender extender) {
			return extender.tryExtendLazer(extractingFrom, directionSendingTowards.getOpposite());
		}
		if (extractingFrom != null && level.getBlockEntity(blockPos) instanceof ILazerAcceptor charging) {
			return charging.tryAcceptEnergy(extractingFrom);
		} else {
			return 0;
		}
	}
}
