package mudkipboy7.alien.world.block.fluid;

import java.util.Optional;

import mudkipboy7.alien.world.block.AMFluids;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.fluids.ForgeFlowingFluid;

public class AmmoniaLiquid extends ForgeFlowingFluid {

	protected AmmoniaLiquid(Properties properties) {
		super(properties);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getAmount(FluidState p_164509_) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isSource(FluidState p_76140_) {
		// TODO Auto-generated method stub
		return false;

	}

	@Override
	public Fluid getSource() {
		// TODO Auto-generated method stub
		return AMFluids.AMMONIA_LIQUID_SOURCE.get();
	}

	@Override
	public Fluid getFlowing() {
		// TODO Auto-generated method stub
		return AMFluids.AMMONIA_LIQUID_FLOWING.get();
	}

	@Override
	public ParticleOptions getDripParticle() {
		return ParticleTypes.DRIPPING_WATER;
	}

	@Override
	public void animateTick(Level p_230606_, BlockPos p_230607_, FluidState p_230608_, RandomSource p_230609_) {
		if (!p_230608_.isSource() && !p_230608_.getValue(FALLING)) {
			if (p_230609_.nextInt(64) == 0) {
				p_230606_.playLocalSound((double) p_230607_.getX() + 0.5D, (double) p_230607_.getY() + 0.5D,
						(double) p_230607_.getZ() + 0.5D, SoundEvents.WATER_AMBIENT, SoundSource.BLOCKS,
						p_230609_.nextFloat() * 0.25F + 0.75F, p_230609_.nextFloat() + 0.5F, false);
			}
		} else if (p_230609_.nextInt(10) == 0) {
			p_230606_.addParticle(ParticleTypes.UNDERWATER, (double) p_230607_.getX() + p_230609_.nextDouble(),
					(double) p_230607_.getY() + p_230609_.nextDouble(),
					(double) p_230607_.getZ() + p_230609_.nextDouble(), 0.0D, 0.0D, 0.0D);
		}

	}

	@Override
	public Optional<SoundEvent> getPickupSound() {
		return Optional.of(SoundEvents.BUCKET_FILL);
	}

}
