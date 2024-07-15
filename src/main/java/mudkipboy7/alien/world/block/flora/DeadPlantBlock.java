package mudkipboy7.alien.world.block.flora;

import mudkipboy7.alien.world.block.states.AMBlockStateProperties;
import mudkipboy7.alien.world.block.states.DeadPlantType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class DeadPlantBlock extends BushBlock {
	public static final EnumProperty<DeadPlantType> TYPE = AMBlockStateProperties.DEAD_PLANT_TYPE;

	protected static final float AABB_OFFSET = 6.0F;
	protected static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 12.0D, 14.0D);

	public DeadPlantBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.defaultBlockState().setValue(TYPE, DeadPlantType.SAPLING));
	}

	public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos,
			CollisionContext collisionContext) {
		return SHAPE;
	}

	@Override
	protected boolean mayPlaceOn(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
		return true;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(TYPE);
	}

}
