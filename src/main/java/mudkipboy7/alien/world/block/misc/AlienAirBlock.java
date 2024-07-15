package mudkipboy7.alien.world.block.misc;

import mudkipboy7.alien.data.tags.AMEntityTypeTags;
import mudkipboy7.alien.data.tags.AMItemTags;
import mudkipboy7.alien.world.block.blockentity.machine.HazardRemoverBlockEntity;
import mudkipboy7.alien.world.effect.AMMobEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
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
	public void onRemove(BlockState blockstate, Level level, BlockPos blockPos, BlockState newstate,
			boolean pMovedByPiston) {
	}

	public RenderShape getRenderShape(BlockState pState) {
		return RenderShape.INVISIBLE;
	}

	public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
		return Shapes.empty();
	}

	@Override
	public void entityInside(BlockState blockState, Level level, BlockPos blockPos, Entity entity) {
		if (entity instanceof LivingEntity livingEntity && HazardRemoverBlockEntity.needsProtection(livingEntity)) {
			livingEntity
					.addEffect(new MobEffectInstance(AMMobEffects.HAZARD_PROTECTION.get(), 60, 0, true, true, true));
		}
		super.entityInside(blockState, level, blockPos, entity);
	}
}