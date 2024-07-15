package mudkipboy7.alien.world.item.functional.tool;

import java.util.Map;
import java.util.Optional;

import com.google.common.collect.ImmutableMap;

import mudkipboy7.alien.data.tags.AMBlockTags;
import mudkipboy7.alien.world.block.AMBlocks;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

public class AlienAxeItem extends DiggerItem {
	protected static final Map<Block, Block> STRIPPABLES = (new ImmutableMap.Builder<Block, Block>())
			.put(AMBlocks.ALIEN_LOG.get(), AMBlocks.STRIPPED_ALIEN_LOG.get())
			.put(AMBlocks.ALIEN_LOG_ALL_SIDES_SAME.get(), AMBlocks.STRIPPED_ALIEN_LOG_ALL_SIDES_SAME.get()).build();

	//private static final ImmutableList<Block> blocksThatCanBeStripped = new ImmutableList.Builder<Block>()
	//		.add(AMBlocks.ALIEN_LOG.get(), AMBlocks.ALIEN_LOG_ALL_SIDES_SAME.get()).build();

	public AlienAxeItem(Tier pTier, float pAttackDamageModifier, float pAttackSpeedModifier,
			Item.Properties pProperties) {
		super(pAttackDamageModifier, pAttackSpeedModifier, pTier, AMBlockTags.MINEABLE_WITH_ALIEN_AXE, pProperties);
	}

	/**
	 * Called when this item is used when targeting a Block
	 */
	public InteractionResult useOn(UseOnContext pContext) {
		Level level = pContext.getLevel();
		BlockPos blockpos = pContext.getClickedPos();
		Player player = pContext.getPlayer();
		BlockState blockstate = level.getBlockState(blockpos);
		BlockState stripped = getAxeStrippingState(blockstate);
		ItemStack itemstack = pContext.getItemInHand();
		if (stripped != null) {
			if (player instanceof ServerPlayer) {
				CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer) player, blockpos, itemstack);
			}
			level.playSound(player, blockpos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1.0F, 1.0F);
			level.setBlock(blockpos, stripped, 11);
			level.gameEvent(GameEvent.BLOCK_CHANGE, blockpos, GameEvent.Context.of(player, blockstate));
			if (player != null) {
				itemstack.hurtAndBreak(1, player, (onBroken) -> {
					onBroken.broadcastBreakEvent(pContext.getHand());
				});
			}
			return InteractionResult.SUCCESS;
		}
		System.out.println(getStripped(blockstate));
		return InteractionResult.PASS;

	}

	@org.jetbrains.annotations.Nullable
	public static BlockState getAxeStrippingState(BlockState originalState) {
		Block block = STRIPPABLES.get(originalState.getBlock());
		return block != null
				? block.defaultBlockState().setValue(RotatedPillarBlock.AXIS,
						originalState.getValue(RotatedPillarBlock.AXIS))
				: null;
	}

	private Optional<BlockState> getStripped(BlockState pUnstrippedState) {
		return Optional.ofNullable(STRIPPABLES.get(pUnstrippedState.getBlock())).map((p_150689_) -> {
			return p_150689_.defaultBlockState().setValue(RotatedPillarBlock.AXIS,
					pUnstrippedState.getValue(RotatedPillarBlock.AXIS));
		});
	}

	@Override
	public boolean canPerformAction(ItemStack stack, net.minecraftforge.common.ToolAction toolAction) {
		return net.minecraftforge.common.ToolActions.DEFAULT_AXE_ACTIONS.contains(toolAction);
	}
}
