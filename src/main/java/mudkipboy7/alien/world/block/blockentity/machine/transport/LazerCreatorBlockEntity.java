package mudkipboy7.alien.world.block.blockentity.machine.transport;

import mudkipboy7.alien.world.block.blockentity.AMBlockEntities;
import mudkipboy7.alien.world.block.blockentity.machine.interfaces.lazer.ILazerAcceptor;
import mudkipboy7.alien.world.block.blockentity.machine.interfaces.lazer.ILazerCanExtractFrom;
import mudkipboy7.alien.world.block.blockentity.machine.interfaces.lazer.ILazerCreator;
import mudkipboy7.alien.world.block.blockentity.machine.interfaces.lazer.ILazerExtender;
import mudkipboy7.alien.world.block.functional.machine.transport.LazerCreatorBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.energy.EnergyStorage;

public class LazerCreatorBlockEntity extends BlockEntity implements ILazerCreator {
	// private BlockPos connectedTo;
	private static final int MAX_SEARCH_RANGE = 10;
	public int ammountTransferedLastTick = 0;

	public LazerCreatorBlockEntity(BlockPos blockPos, BlockState blockState) {
		super(AMBlockEntities.LAZER_CREATOR.get(), blockPos, blockState);
	}

	public static void tick(Level level, BlockPos blockPos, BlockState blockState,
			LazerCreatorBlockEntity blockEntity) {
		EnergyStorage extractingFrom = blockEntity.findSomethingToExtractFrom();
		if (extractingFrom != null && extractingFrom.getEnergyStored() > 0) {
			BlockPos charging = blockEntity.searchForCharagbles();
			if (charging != null) {
				blockEntity.ammountTransferedLastTick = blockEntity.sendEnergyTo(charging, extractingFrom,
						blockEntity.getBlockState().getValue(LazerCreatorBlock.DIRECTION));
			}
		}
	}

	public EnergyStorage findSomethingToExtractFrom() {
		Direction directionToFind = this.getBlockState().getValue(LazerCreatorBlock.DIRECTION).getOpposite();
		if (this.getLevel()
				.getBlockEntity(this.getBlockPos().relative(directionToFind)) instanceof ILazerCanExtractFrom charger) {
			return charger.getEnergy();
		}
		return null;

	}

	@Override
	public BlockPos searchForCharagbles() {
		Direction direction = this.getBlockState().getValue(LazerCreatorBlock.DIRECTION);
		/*
		 * Tries to find something to charge
		 */
		for (int i = 1; i < MAX_SEARCH_RANGE; i++) {
			BlockPos beingChecked = this.getBlockPos().relative(direction, i);
			// Minecraft.getInstance().player.sendSystemMessage(Component.literal(beingChecked.toString()));
			if (level.getBlockEntity(beingChecked) instanceof ILazerAcceptor
					|| level.getBlockEntity(beingChecked) instanceof ILazerExtender) {
				return beingChecked;
			} else if (level.getBlockState(beingChecked).isAir()) {

			} else {
				return null;
				// break;
			}
		}
		// }
		return null;

	}

	@Override
	public int sendEnergyTo(BlockPos blockPos, EnergyStorage extractingFrom, Direction directionSendingTowards) {
		if (extractingFrom != null && level.getBlockEntity(blockPos) instanceof ILazerExtender extender) {
			return extender.tryExtendLazer(extractingFrom, directionSendingTowards.getOpposite());
		} else if (extractingFrom != null && level.getBlockEntity(blockPos) instanceof ILazerAcceptor charging) {
			return charging.tryAcceptEnergy(extractingFrom);
		} else {
			return 0;
		}
	}
}
