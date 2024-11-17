package com.mudkipboy7.alien.world.block.misc;

import org.jetbrains.annotations.Nullable;

import com.mudkipboy7.alien.data.tags.AMEntityTypeTags;
import com.mudkipboy7.alien.data.tags.AMItemTags;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class TestBlock extends Block {

	public TestBlock(BlockBehaviour.Properties properties) {
		super(properties);
	}

	@Override
	public float getFriction(BlockState state, LevelReader level, BlockPos pos, @Nullable Entity entity) {
		return super.getFriction();
		//if (entity instanceof LivingEntity livingEntity) {
		//	if (livingEntity.getItemBySlot(EquipmentSlot.FEET).is(AMItemTags.SURVIVAL_FEET)) {
		//		// System.out.println("eewfwe");
		//		return super.getFriction();
		////	}	
		//}
		//return 0.8F;

	}
}