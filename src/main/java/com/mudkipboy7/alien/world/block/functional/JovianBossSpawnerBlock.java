package com.mudkipboy7.alien.world.block.functional;

import com.mudkipboy7.alien.world.entity.AMEntities;
import com.mudkipboy7.alien.world.entity.boss.JovianBossEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class JovianBossSpawnerBlock extends Block {

	public JovianBossSpawnerBlock(Properties pProperties) {
		super(pProperties);
	}

	@Override
	public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player,
			InteractionHand interactionHand, BlockHitResult hit) {
		JovianBossEntity boss = new JovianBossEntity(AMEntities.JOVIAN_BOSS.get(), level);
		boss.setPos(blockPos.above().getCenter());
		level.addFreshEntity(boss);
		level.destroyBlock(blockPos, false);
		return super.use(blockState, level, blockPos, player, interactionHand, hit);
	}
}
