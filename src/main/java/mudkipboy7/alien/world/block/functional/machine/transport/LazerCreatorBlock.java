package mudkipboy7.alien.world.block.functional.machine.transport;

import mudkipboy7.alien.world.block.blockentity.AMBlockEntities;
import mudkipboy7.alien.world.block.blockentity.machine.transport.LazerCreatorBlockEntity;
import mudkipboy7.alien.world.block.functional.machine.AbstractMachineBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;

public class LazerCreatorBlock extends AbstractMachineBlock {
	public static final EnumProperty<Direction> DIRECTION = EnumProperty.create("facing", Direction.class);

	public LazerCreatorBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.defaultBlockState().setValue(DIRECTION, Direction.UP));
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
		return new LazerCreatorBlockEntity(blockPos, blockState);
	}

	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState,
			BlockEntityType<T> blockEntityType) {
		return level.isClientSide ? null
				: createTickerHelper(blockEntityType, AMBlockEntities.LAZER_CREATOR.get(),
						LazerCreatorBlockEntity::tick);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(DIRECTION);
	}

	public BlockState getStateForPlacement(BlockPlaceContext context) {
		context.getClickedFace();
		return this.defaultBlockState().setValue(DIRECTION, Direction
				.fromAxisAndDirection(context.getClickedFace().getAxis(), context.getClickedFace().getAxisDirection()));
	}

	@Override
	public RenderShape getRenderShape(BlockState blockState) {
		return RenderShape.MODEL;
	}

}
