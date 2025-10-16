package com.mudkipboy7.alien.world.block.functional;

import com.mudkipboy7.alien.world.block.blockentity.AMBlockEntities;
import com.mudkipboy7.alien.world.block.blockentity.AlienPortalBlockEntity;
import com.mudkipboy7.alien.world.item.functional.AlienDimCreativeTeleporterItem;
import com.mudkipboy7.alien.world.worldgen.dimension.AMDimensions;
import com.mudkipboy7.alien.world.worldgen.dimension.teleporter.AlienDimTeleporter;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class AlienPortalBlock extends BaseEntityBlock {
	AlienDimTeleporter teleporter = new AlienDimTeleporter();

	public AlienPortalBlock(BlockBehaviour.Properties properties) {
		super(properties);
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
		return new AlienPortalBlockEntity(blockPos, blockState);
	}

	@Override
	public RenderShape getRenderShape(BlockState blockState) {
		return RenderShape.MODEL;
	}

	@Override
	public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player,
			InteractionHand hand, BlockHitResult hit) {
		Item heldItem = player.getItemInHand(hand).getItem();
		ResourceKey<Level> currentDimension = player.level().dimension();
		boolean inValidDim = currentDimension == AMDimensions.ALIENDIM_LEVEL || currentDimension == Level.OVERWORLD;
		if (level instanceof ServerLevel && player.canChangeDimensions()
				&& !(heldItem instanceof BlockItem || heldItem instanceof AlienDimCreativeTeleporterItem)) {
			ResourceKey<Level> dimToSendTo = player.level().dimension() == AMDimensions.ALIENDIM_LEVEL ? Level.OVERWORLD
					: AMDimensions.ALIENDIM_LEVEL;
			ServerLevel serverlevel = player.getCommandSenderWorld().getServer().getLevel(dimToSendTo);
			if (serverlevel != null) {

				player.changeDimension(serverlevel, teleporter);

				return InteractionResult.SUCCESS;
			}
		}
		return InteractionResult.FAIL;
	}

	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState,
			BlockEntityType<T> blockEntityType) {
		return level.isClientSide ? null
				: createTickerHelper(blockEntityType, AMBlockEntities.ALIEN_PORTAL.get(), AlienPortalBlockEntity::tick);
	}
}