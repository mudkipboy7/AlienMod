package com.mudkipboy7.alien.mixin;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

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

public class MixinMethods {
	public static void onVaporize(@Nullable Player player, Level level, BlockPos pos, FluidStack stack,
			CallbackInfo callbackInfo, Object ob) {
		Holder<Biome> biome = level.getBiome(pos);
		FluidType type = (FluidType) ob;
		// System.out.println(stack.getAmount());
		if (WorldFuncs.inUltracoldBiome(level, pos) && stack != null) {
			boolean isWater = (type == ForgeMod.WATER_TYPE.get())
					|| type.getStateForPlacement(level, pos, stack).is(FluidTags.WATER);
			boolean isLava = (type == ForgeMod.LAVA_TYPE.get())
					|| type.getStateForPlacement(level, pos, stack).is(FluidTags.LAVA);
			if (isLava) {
				level.setBlockAndUpdate(pos, Blocks.OBSIDIAN.defaultBlockState());
			}
			if (isWater) {
				level.setBlockAndUpdate(pos, AMBlocks.ALIEN_STONE.get().defaultBlockState());
			}
		}
	}

}
