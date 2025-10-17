package com.mudkipboy7.alien.sound;

import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.client.resources.sounds.Sound;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.resources.sounds.TickableSoundInstance;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.client.sounds.WeighedSoundEvents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;

public class MusicSoundInstance extends AbstractTickableSoundInstance  {
	Music music;
	protected MusicSoundInstance(Music music, RandomSource random) {
		super(music.getEvent().value(), SoundSource.MUSIC, random);
		this.music = music;
	}

	@Override
	public void tick() {
		
	}



}
