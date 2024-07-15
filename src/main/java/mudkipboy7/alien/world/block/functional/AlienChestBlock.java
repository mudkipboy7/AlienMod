package mudkipboy7.alien.world.block.functional;

import java.util.Optional;
import java.util.function.Supplier;

import javax.annotation.Nullable;

import mudkipboy7.alien.world.block.blockentity.AlienChestBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.CompoundContainer;
import net.minecraft.world.Container;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.DoubleBlockCombiner;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class AlienChestBlock extends ChestBlock {

	public AlienChestBlock(Properties properties,
			Supplier<BlockEntityType<? extends ChestBlockEntity>> blockEntityType) {
		super(properties, blockEntityType);
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
		return new AlienChestBlockEntity(pPos, pState);
	}

	@SuppressWarnings("unused")
	private static final DoubleBlockCombiner.Combiner<ChestBlockEntity, Optional<Container>> CHEST_COMBINER = new DoubleBlockCombiner.Combiner<ChestBlockEntity, Optional<Container>>() {
		@Override
		public Optional<Container> acceptDouble(ChestBlockEntity p_51591_, ChestBlockEntity p_51592_) {
			return Optional.of(new CompoundContainer(p_51591_, p_51592_));
		}

		@Override
		public Optional<Container> acceptSingle(ChestBlockEntity p_51589_) {
			return Optional.of(p_51589_);
		}

		@Override
		public Optional<Container> acceptNone() {
			return Optional.empty();
		}
	};
	private static final DoubleBlockCombiner.Combiner<ChestBlockEntity, Optional<MenuProvider>> MENU_PROVIDER_COMBINER = new DoubleBlockCombiner.Combiner<ChestBlockEntity, Optional<MenuProvider>>() {
		@Override
		public Optional<MenuProvider> acceptDouble(final ChestBlockEntity left, final ChestBlockEntity right) {
			final Container container = new CompoundContainer(left, right);
			return Optional.of(new MenuProvider() {
				@Override
				@Nullable
				public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
					if (left.canOpen(player) && right.canOpen(player)) {
						left.unpackLootTable(playerInventory.player);
						right.unpackLootTable(playerInventory.player);
						return ChestMenu.sixRows(containerId, playerInventory, container);
					} else {
						return null;
					}
				}

				@Override
				public Component getDisplayName() {
					if (left.hasCustomName()) {
						return left.getDisplayName();
					} else {
						return (Component) (right.hasCustomName() ? right.getDisplayName()
								: Component.translatable("container.chestDouble"));
					}
				}
			});
		}

		@Override
		public Optional<MenuProvider> acceptSingle(ChestBlockEntity chest) {
			return Optional.of(chest);
		}

		@Override
		public Optional<MenuProvider> acceptNone() {
			return Optional.empty();
		}
	};

	@Override
	@Nullable
	public MenuProvider getMenuProvider(BlockState pState, Level pLevel, BlockPos pPos) {
		return this.combine(pState, pLevel, pPos, false).apply(MENU_PROVIDER_COMBINER).orElse((MenuProvider) null);
	}

}
