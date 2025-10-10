package com.mudkipboy7.alien.mixin.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.mudkipboy7.alien.mixin.MixinMethods;
import com.mudkipboy7.alien.world.WorldFuncs;
import com.mudkipboy7.alien.world.block.AMBlocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidType;

@Mixin(FluidType.class)
public abstract class FluidTypeMixin {
	@Inject(method = "isVaporizedOnPlacement", at = @At("RETURN"), cancellable = true)
	public void isVaporizedOnPlacement(Level level, BlockPos blockPos, FluidStack stack,
			CallbackInfoReturnable<Boolean> callbackInfo) {
		Holder<Biome> biome = level.getBiome(blockPos);
		FluidType type = (FluidType) (Object) this;
		if (WorldFuncs.inUltracoldBiome(level, blockPos) && stack != null) {
			boolean isWater = (type == ForgeMod.WATER_TYPE.get())
					|| type.getStateForPlacement(level, blockPos, stack).is(FluidTags.WATER);
			boolean isLava = (type == ForgeMod.LAVA_TYPE.get())
					|| type.getStateForPlacement(level, blockPos, stack).is(FluidTags.LAVA);
			if (isWater || isLava) {
				callbackInfo.setReturnValue(true);
			}
		}
	}

	@Inject(method = "onVaporize", at = @At("HEAD"), cancellable = true)
	public void onVaporize(Player player, Level level, BlockPos blockPos, FluidStack stack, CallbackInfo callbackInfo) {
		Holder<Biome> biome = level.getBiome(blockPos);
		FluidType type = (FluidType) (Object) this;
		// System.out.println(stack.getAmount());
		if (WorldFuncs.inUltracoldBiome(level, blockPos) && stack != null) {
			boolean isWater = (type == ForgeMod.WATER_TYPE.get())
					|| type.getStateForPlacement(level, blockPos, stack).is(FluidTags.WATER);
			boolean isLava = (type == ForgeMod.LAVA_TYPE.get())
					|| type.getStateForPlacement(level, blockPos, stack).is(FluidTags.LAVA);
			if (isLava) {
				level.setBlockAndUpdate(blockPos, Blocks.OBSIDIAN.defaultBlockState());
			}
			if (isWater) {
				level.setBlockAndUpdate(blockPos, AMBlocks.GELUSTONE.get().defaultBlockState());
			}
		}
	}
}
