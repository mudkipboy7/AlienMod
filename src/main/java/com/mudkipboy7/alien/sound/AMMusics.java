package com.mudkipboy7.alien.sound;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.mudkipboy7.alien.world.worldgen.dimension.AMDimensions;

import net.minecraft.client.Minecraft;
import net.minecraft.client.sounds.MusicManager;
import net.minecraft.core.Holder;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;

public class AMMusics {
	public static final Music ALIEN_MUSIC = createGameMusic(Holder.direct(AMSoundEvents.ALIEN_MUSIC.get()));
	public static final Music ALIEN_MUSIC_NIGHT = createGameMusic(Holder.direct(AMSoundEvents.ALIEN_MUSIC_NIGHT.get()));
	public static final Music JOVIAN_BOSS_MUSIC = new Music(Holder.direct(AMSoundEvents.JOVIAN_BOSS_MUSIC.get()), 0, 0,
			true);

	public static Music createGameMusic(Holder<SoundEvent> pEvent) {
		return new Music(pEvent, 12000, 24000, false);
		// soundevent
	}

}
