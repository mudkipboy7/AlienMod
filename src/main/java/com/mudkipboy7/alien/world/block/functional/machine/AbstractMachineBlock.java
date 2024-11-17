package com.mudkipboy7.alien.world.block.functional.machine;

import com.mudkipboy7.alien.world.block.blockentity.machine.interfaces.IEnergyStoringBlockEntity;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public abstract class AbstractMachineBlock extends BaseEntityBlock {
	public AbstractMachineBlock(Properties properties) {
		super(properties);
	}

	@Override
	protected void spawnDestroyParticles(Level pLevel, Player pPlayer, BlockPos pPos, BlockState pState) {
		if (pLevel.getBlockEntity(pPos) instanceof IEnergyStoringBlockEntity entity
				&& entity.getEnergy().getEnergyStored() > 0) {
			Minecraft.getInstance().particleEngine.destroy(pPos, Blocks.YELLOW_WOOL.defaultBlockState());
		}
		super.spawnDestroyParticles(pLevel, pPlayer, pPos, pState);
	}
}
