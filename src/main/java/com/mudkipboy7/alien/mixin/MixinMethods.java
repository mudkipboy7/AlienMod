package com.mudkipboy7.alien.mixin;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.mudkipboy7.alien.world.Gravity;
import com.mudkipboy7.alien.world.WorldFuncs;
import com.mudkipboy7.alien.world.block.AMBlocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidType;

public class MixinMethods {

	public static void travel(LivingEntity livingEntity, Vec3 vec3, CallbackInfo callbackInfo) {
		if (Gravity.entityIsInLowGravityBiome(livingEntity)) {
			// callbackInfo.cancel();
		}

	}
}
