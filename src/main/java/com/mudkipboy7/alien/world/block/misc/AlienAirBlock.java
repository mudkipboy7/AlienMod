package com.mudkipboy7.alien.world.block.misc;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class AlienAirBlock extends Block {

	// public static final IntegerProperty DISTANCE = BlockStateProperties.DISTANCE;

	public AlienAirBlock(BlockBehaviour.Properties prop) {
		super(prop);
		// this.canBeReplaced(null, null)
	}

	@Override
	public void onRemove(BlockState blockState, Level level, BlockPos blockPos, BlockState newState,
			boolean pMovedByPiston) {
		//level.setBlockAndUpdate(blockPos, blockState);
	}

	@Override
	public boolean canBeReplaced(BlockState pState, Fluid pFluid) {
		return false;
	}

	public RenderShape getRenderShape(BlockState pState) {
		return RenderShape.MODEL;
	}

	public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
		return Shapes.empty();
	}

	@Override
	public void entityInside(BlockState blockState, Level level, BlockPos blockPos, Entity entity) {
		//if (entity instanceof LivingEntity livingEntity && HazardRemovalMachineBlockEntity.needsProtection(livingEntity)) {
		//	livingEntity
		////			.addEffect(new MobEffectInstance(AMMobEffects.HAZARD_PROTECTION.get(), 60, 0, true, true, true));
		//}
		super.entityInside(blockState, level, blockPos, entity);
	}
}