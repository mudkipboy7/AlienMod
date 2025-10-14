package com.mudkipboy7.alien.world.block.blockentity.machine.transport;

import com.mudkipboy7.alien.world.block.AMBlocks;
import com.mudkipboy7.alien.world.block.blockentity.AMBlockEntities;
import com.mudkipboy7.alien.world.block.blockentity.machine.interfaces.lazer.ILazerAcceptor;
import com.mudkipboy7.alien.world.block.blockentity.machine.interfaces.lazer.ILazerCanExtractFrom;
import com.mudkipboy7.alien.world.block.blockentity.machine.interfaces.lazer.ILazerCreator;
import com.mudkipboy7.alien.world.block.blockentity.machine.interfaces.lazer.ILazerExtender;
import com.mudkipboy7.alien.world.block.functional.machine.transport.LazerCreatorBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.energy.EnergyStorage;

public class LazerCreatorBlockEntity extends BlockEntity implements ILazerCreator {
	// private BlockPos connectedTo;
	private static final int MAX_SEARCH_RANGE = 10;
	public int ammountTransferedLastTick = 0;

	// These are used for animations

	public int currentLazerLength = 0;

	public LazerCreatorBlockEntity(BlockPos blockPos, BlockState blockState) {
		super(AMBlockEntities.LAZER_CREATOR.get(), blockPos, blockState);
	}

	public static void tick(Level level, BlockPos blockPos, BlockState blockState,
			LazerCreatorBlockEntity blockEntity) {
		EnergyStorage extractingFrom = blockEntity.findSomethingToExtractFrom();
		// System.out.println(blockEntity);
		// level.setBlockAndUpdate(blockPos, blockState);
		// if (blockEntity.ammountTransferedLastTick != 0)
		// blockEntity.level.setBlockAndUpdate(blockPos,
		// blockState.setValue(LazerCreatorBlock.LAZER_LENGTH,
		// blockEntity.getCurrentLazerLength()));
		// }
		// I do this to s

		if ((extractingFrom != null) && extractingFrom.getEnergyStored() > 0) {
			BlockPos charging = blockEntity.searchForCharagbles(0);
			if (charging != null) {
				// int x = blockEntity.sendEnergyTo(charging, extractingFrom,
				// blockEntity.getBlockState().getValue(LazerCreatorBlock.DIRECTION));
				blockEntity.ammountTransferedLastTick = blockEntity.sendEnergyTo(charging, extractingFrom,
						blockEntity.getBlockState().getValue(LazerCreatorBlock.DIRECTION));
				if (level.getGameTime() % 80L == 0L)
					level.playSound((Player) null, blockPos, SoundEvents.BEACON_AMBIENT, SoundSource.BLOCKS, 1.0F,
							1.0F);

			} else {
				blockEntity.setCurrentLazerLength(0);
			}
		} else {
			blockEntity.setCurrentLazerLength(0);
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
	public BlockPos searchForCharagbles(int ammountDoneSoFar) {
		Direction direction = this.getBlockState().getValue(LazerCreatorBlock.DIRECTION);
		/*
		 * Tries to find something to charge
		 */
		for (int i = 1; i < MAX_SEARCH_RANGE; i++) {
			BlockPos beingChecked = this.getBlockPos().relative(direction, i);
			BlockEntity blockentity = level.getBlockEntity(beingChecked);
			// Minecraft.getInstance().player.sendSystemMessage(Component.literal(beingChecked.toString()));
			if (blockentity instanceof ILazerAcceptor) {
				this.setCurrentLazerLength(i);
				return beingChecked;

			}
			// Commented out because this causes visual bugs

			/*
			 * else if (blockentity instanceof LazerCreatorBlockEntity otherLazer &&
			 * otherLazer.getBlockState().getValue(LazerCreatorBlock.DIRECTION) !=
			 * direction.getOpposite()) { if (ammountDoneSoFar <= MAX_SEARCH_EXTENTIONS) {
			 * this.currentLazerLength = i; return
			 * otherLazer.searchForCharagbles(ammountDoneSoFar + 1); } }
			 */

			else if (!(level.getBlockState(beingChecked).isSolid() && level.getBlockState(beingChecked).canOcclude())) {

			} else {
				return null;
			}
		}
		return null;

	}

	@Override
	public int sendEnergyTo(BlockPos blockPos, EnergyStorage extractingFrom, Direction directionSendingTowards) {
		if (extractingFrom != null && level.getBlockEntity(blockPos) instanceof ILazerExtender extender) {
			return 0;
		} else if (extractingFrom != null && level.getBlockEntity(blockPos) instanceof ILazerAcceptor charging) {
			return charging.tryAcceptEnergy(extractingFrom);
		} else {
			// this.isTransferingEnergy = false;
			return 0;
		}
	}

	public int getCurrentLazerLength() {
		return currentLazerLength;
	}

	/*
	 * Used to update the client on the lazer length
	 */
	private void setCurrentLazerLength(int length) {
		if (currentLazerLength != length) {
			this.currentLazerLength = length;
			// System.out.println(currentLazerLength);
			level.sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 2);
		}

	}

	@Override
	public CompoundTag getUpdateTag() {
		CompoundTag tag = new CompoundTag();
		// transfers the lazerlength to the client so it can render it
		tag.putInt("lazerlength", this.currentLazerLength);
		return tag;
	}

	@Override
	public Packet<ClientGamePacketListener> getUpdatePacket() {
		return ClientboundBlockEntityDataPacket.create(this);
	}
	@Override
	public void load(CompoundTag tag) {
		super.load(tag);
		this.currentLazerLength = tag.getInt("lazerlength");
	}

}
