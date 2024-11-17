package com.mudkipboy7.alien.world.block.fluid;

import com.mudkipboy7.alien.data.tags.AMEntityTypeTags;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.RegistryObject;

public class AmmoniaLiquidBlock extends LiquidBlock {

	public static final java.util.function.Supplier<? extends LiquidBlock> AMMONIA_FLUID_FLOWING = null;
	public static final BooleanProperty FALLING = BlockStateProperties.FALLING;

	public AmmoniaLiquidBlock(RegistryObject<AmmoniaLiquid> ammoniaLiquidSource) {
		super(ammoniaLiquidSource, BlockBehaviour.Properties.of().mapColor(MapColor.WATER).replaceable().noCollission()
				.strength(100.0F).pushReaction(PushReaction.DESTROY).noLootTable().liquid().sound(SoundType.EMPTY));
		this.registerDefaultState(this.stateDefinition.any());

	}

	public void entityInside(BlockState state, Level level, BlockPos position, Entity entity) {
		if (entity instanceof LivingEntity && (!entity.getType().is(AMEntityTypeTags.COLD_BLOODED))) {
			if (!level.isClientSide) {
				ServerLevel serverlevel = (ServerLevel) level;
				for (int i = 0; i < 1; ++i) {
					serverlevel.sendParticles(ParticleTypes.BUBBLE,
							(double) position.getX() + level.random.nextDouble(), (double) (position.getY() + 0.1),
							(double) position.getZ() + level.random.nextDouble(), 1, 0.0D, 0.7D, 0.0D, 2.0D);
					serverlevel.sendParticles(ParticleTypes.SMOKE, (double) position.getX() + level.random.nextDouble(),
							(double) (position.getY() + 0.5), (double) position.getZ() + level.random.nextDouble(), 0,
							0.0D, 0.1D, 0.0D, 1.0D);

					// serverlevel.sendParticles(ParticleTypes.BUBBLE_POP,
					// (double) p_50978_.getX() + p_50977_.random.nextDouble(), (double)
					// (p_50978_.getY() + 1),
					// (double) p_50978_.getZ() + p_50977_.random.nextDouble(), 0, 0.0D, 0.1D, 0.0D,
					// 0.6D);

				}

				// if (entityin.isAlive()) {
				// entityin.setIsInPowderSnow(true);
				// }

			}
		}

	}
}
