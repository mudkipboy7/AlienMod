package mudkipboy7.alien.world.block.functional;

import mudkipboy7.alien.world.item.functional.AlienDimCreativeTeleporterItem;
import mudkipboy7.alien.world.worldgen.dimension.AMDimensions;
import mudkipboy7.alien.world.worldgen.dimension.teleporter.AlienDimTeleporter;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.util.ITeleporter;

public class AlienPortalBlock extends Block {

	public AlienPortalBlock(BlockBehaviour.Properties properties) {
		super(properties);
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
		ResourceKey<Level> alienDim = AMDimensions.ALIENDIM_LEVEL;
		// ResourceKey<Level> overworld = Level.OVERWORLD;
		boolean inValidDim = currentDimension == alienDim || currentDimension == Level.OVERWORLD;
		if (level instanceof ServerLevel && player.canChangeDimensions()
				&& !(heldItem instanceof BlockItem || heldItem instanceof AlienDimCreativeTeleporterItem)
				&& inValidDim) {
			ResourceKey<Level> dimToSendTo = player.level().dimension() == alienDim ? Level.OVERWORLD : alienDim;
			ServerLevel serverlevel = player.getCommandSenderWorld().getServer().getLevel(dimToSendTo);
			if (serverlevel != null) {
				ITeleporter teleporter = new AlienDimTeleporter();
				player.changeDimension(serverlevel, teleporter);

				return InteractionResult.SUCCESS;
			}
		}
		return InteractionResult.FAIL;
	}
}