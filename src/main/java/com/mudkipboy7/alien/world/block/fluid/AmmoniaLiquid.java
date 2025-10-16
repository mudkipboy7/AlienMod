package com.mudkipboy7.alien.world.block.fluid;

import com.mudkipboy7.alien.world.block.AMBlocks;
import com.mudkipboy7.alien.world.block.AMFluids;
import com.mudkipboy7.alien.world.item.AMItems;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.fluids.ForgeFlowingFluid;

public abstract class AmmoniaLiquid extends ForgeFlowingFluid {

	protected AmmoniaLiquid() {
		super(new AmmoniaLiquid.Properties(() -> //
		AMFluids.AMMONIA_LIQUID_TYPE.get(), // The Type of liquid it is
				AMFluids.AMMONIA_LIQUID, // Its Source block
				AMFluids.AMMONIA_LIQUID_FLOWING)// Its flowing block
				.block(AMBlocks.AMMONIA_LIQUID_BLOCK)//
				.bucket(AMItems.AMMONIA_LIQUID_BUCKET)//
				.levelDecreasePerBlock(1)//

		);
	}

	@Override
	public void animateTick(Level level, BlockPos blockPos, FluidState fluidState, RandomSource random) {
		if (!fluidState.isSource() && !fluidState.getValue(FALLING)) {
			if (random.nextInt(64) == 0) {
				level.playLocalSound((double) blockPos.getX() + 0.5D, (double) blockPos.getY() + 0.5D,
						(double) blockPos.getZ() + 0.5D, SoundEvents.WATER_AMBIENT, SoundSource.BLOCKS,
						random.nextFloat() * 0.25F + 0.75F, random.nextFloat() + 0.5F, false);
			}
		} else if (random.nextInt(10) == 0) {
			level.addParticle(ParticleTypes.UNDERWATER, (double) blockPos.getX() + random.nextDouble(),
					(double) blockPos.getY() + random.nextDouble(), (double) blockPos.getZ() + random.nextDouble(),
					0.0D, 0.0D, 0.0D);
		}
	}

	public static class Flowing extends AmmoniaLiquid {
		public Flowing() {
			super();
			registerDefaultState(getStateDefinition().any().setValue(LEVEL, 7));
		}

		protected void createFluidStateDefinition(StateDefinition.Builder<Fluid, FluidState> builder) {
			super.createFluidStateDefinition(builder);
			builder.add(LEVEL);
		}

		public int getAmount(FluidState state) {
			return state.getValue(LEVEL);
		}

		public boolean isSource(FluidState state) {
			return false;
		}
	}

	public static class Source extends AmmoniaLiquid {
		public Source() {
			super();
		}

		public int getAmount(FluidState state) {
			return 8;
		}

		public boolean isSource(FluidState state) {
			return true;
		}
	}

}
