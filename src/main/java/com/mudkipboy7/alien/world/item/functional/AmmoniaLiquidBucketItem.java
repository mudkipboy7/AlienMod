package com.mudkipboy7.alien.world.item.functional;

import java.util.List;
import java.util.Optional;

import javax.annotation.Nullable;

import com.mudkipboy7.alien.data.AMLanguage;
import com.mudkipboy7.alien.world.block.AMBlocks;
import com.mudkipboy7.alien.world.block.AMFluids;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlockContainer;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.registries.RegistryObject;

public class AmmoniaLiquidBucketItem extends BucketItem {

	public AmmoniaLiquidBucketItem(Properties properties) {
		super(AMFluids.AMMONIA_LIQUID, properties);
	}

	@Override
	public void appendHoverText(ItemStack itemStack, Level level, List<Component> component, TooltipFlag tooltipFlag) {
		component.add(AMLanguage.getDescription(AMBlocks.AMMONIA_LIQUID_BLOCK.get()));
		super.appendHoverText(itemStack, level, component, tooltipFlag);
	}

	@Override
	public boolean emptyContents(Player pPlayer, Level pLevel, BlockPos pPos, BlockHitResult pResult,
			ItemStack container) {
		if (this.getFluid().getFluidType().isVaporizedOnPlacement(pLevel, pPos, null)) {
			this.getFluid().getFluidType().onVaporize(pPlayer, pLevel, pPos, null);
			return true;
		} else {
			return super.emptyContents(pPlayer, pLevel, pPos, pResult, container);
		}
	}
}
