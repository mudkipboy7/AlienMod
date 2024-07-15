package mudkipboy7.alien.world.block.functional.machine;

import mudkipboy7.alien.world.block.blockentity.AMBlockEntities;
import mudkipboy7.alien.world.block.blockentity.machine.CoalGeneratorBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

public class CoalGeneratorBlock extends AbstractContainerMachineBlock {

	public static final BooleanProperty LIT = BlockStateProperties.LIT;

	public CoalGeneratorBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(LIT, Boolean.valueOf(false)));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(LIT);
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
		return new CoalGeneratorBlockEntity(blockPos, blockState);
	}

	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState,
			BlockEntityType<T> blockEntityType) {
		return level.isClientSide ? null
				: createTickerHelper(blockEntityType, AMBlockEntities.COAL_GENERATOR.get(),
						CoalGeneratorBlockEntity::tick);
		
	}

	@Override
	public void animateTick(BlockState blockState, Level level, BlockPos blockPos, RandomSource randomSource) {
		// Minecraft.getInstance().player.sendSystemMessage(Component.literal(String.valueOf(blockState.getValue(LIT))));

		if (blockState.getValue(LIT)) {
			double d0 = (double) blockPos.getX() + 0.5D;
			double d1 = (double) blockPos.getY();
			double d2 = (double) blockPos.getZ() + 0.5D;
			if (randomSource.nextDouble() < 0.1D) {
				// Minecraft.getInstance().player.sendSystemMessage(Component.literal("Noise
				// made!"));
				level.playLocalSound(d0, d1, d2, SoundEvents.FURNACE_FIRE_CRACKLE, SoundSource.BLOCKS, 1.0F, 1.0F,
						false);
			}
			//double d3 = 0.52D;
			//double d4 = randomSource.nextDouble() * 0.6D - 0.3D;
			double d5 = 0;
			double d6 = randomSource.nextDouble() * 6.0D / 16.0D;
			double d7 = 0;
			level.addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, d0 + d5, d1 + d6, d2 + d7, 0.0D, 0.1D, 0.0D);
		}
	}

}
