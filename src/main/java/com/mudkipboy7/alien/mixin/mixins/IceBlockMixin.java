package com.mudkipboy7.alien.mixin.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.mudkipboy7.alien.world.WorldFuncs;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.HalfTransparentBlock;
import net.minecraft.world.level.block.IceBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

@Mixin(IceBlock.class)
public class IceBlockMixin extends HalfTransparentBlock {
	public IceBlockMixin(Properties pProperties) {
		super(pProperties);
	}

	@Inject(method = "playerDestroy", at = @At("HEAD"), cancellable = true)
	public void playerDestroy(Level level, Player player, BlockPos blockPos, BlockState blockState,
			BlockEntity blockEntity, ItemStack itemStack, CallbackInfo callbackInfo) {
		if (WorldFuncs.inUltracoldBiome(level, blockPos)) {
			// itemStack.enchant(Enchantments.SILK_TOUCH, 1);
			callbackInfo.cancel();
			super.playerDestroy(level, player, blockPos, blockState, blockEntity, itemStack);

		}
	}

	@Inject(method = "melt", at = @At("HEAD"), cancellable = true)
	protected void melt(BlockState blockState, Level level, BlockPos blockPos, CallbackInfo callbackInfo) {
		if (WorldFuncs.inUltracoldBiome(level, blockPos))
			callbackInfo.cancel();
	}
}
