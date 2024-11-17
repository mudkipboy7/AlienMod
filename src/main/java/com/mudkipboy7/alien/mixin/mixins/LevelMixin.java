package com.mudkipboy7.alien.mixin.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.mudkipboy7.alien.AlienMod;

import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

@Mixin(Level.class)
public abstract class LevelMixin extends net.minecraftforge.common.capabilities.CapabilityProvider<Level>
		implements LevelAccessor, AutoCloseable, net.minecraftforge.common.extensions.IForgeLevel {
	protected LevelMixin(Class<Level> baseClass) {
		super(baseClass);
	}

	/*
	 * The main reason for this existing is so that I can modify the actual light
	 * levels (not just visual) in the aliendim during eclipses.
	 */
	@Inject(method = "updateSkyBrightness", at = @At("HEAD"), cancellable = true)
	public void updateSkyBrightness(CallbackInfo callBackInfo) {
		Level serverlevel = (Level) (Object) this;
		if (AlienMod.getAlienDimSky().modifySkylightServerSide(serverlevel)) {
			callBackInfo.cancel();
		}
	}
}
