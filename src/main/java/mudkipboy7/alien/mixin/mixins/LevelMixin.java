package mudkipboy7.alien.mixin.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import mudkipboy7.alien.world.worldgen.dimension.sky.AlienDimSky;
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
	@Inject(method = "Lnet/minecraft/world/level/Level;updateSkyBrightness()V", at = @At("HEAD"), cancellable = true)
	public void updateSkyBrightness(CallbackInfo callBackInfo) {
		Level serverlevel = (Level) (Object) this;
		if (AlienDimSky.alienDimSky.modifySkylightServerSide(serverlevel)) {
			callBackInfo.cancel();
		}
	}
}
