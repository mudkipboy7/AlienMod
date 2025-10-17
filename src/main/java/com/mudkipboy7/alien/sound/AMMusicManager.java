package com.mudkipboy7.alien.sound;

import com.mudkipboy7.alien.AlienMod;
import com.mudkipboy7.alien.world.worldgen.dimension.AMDimensions;

import net.minecraft.CrashReport;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.MusicManager;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.Musics;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.RandomSupport;

public class AMMusicManager {
	private static RandomSource random = new LegacyRandomSource(RandomSupport.generateUniqueSeed());
	private MusicSoundInstance currentMusic;

	// Used for debugging
	private int timesMusicHasBeenPlayed = 0;
	// Used to prevent it from ever trying to playing music twice at once.
	private boolean alreadyPlayedThisTick = false;

	public AMMusicManager() {
		if (AlienMod.getMusicManager() != null)
			AlienMod.getMusicManager().stopMusic();

	}

	public void tickMusic() {
		alreadyPlayedThisTick = false;
		// System.out.println(timesMusicHasBeenPlayed);
		// This is used to prevent it from ever not realizing the music has stopped.
		if (!Minecraft.getInstance().getSoundManager().isActive(currentMusic)) {
			currentMusic = null;
		}
		Level level = Minecraft.getInstance().level;
		if (level != null) {
			ResourceKey<DimensionType> dimType = level.dimensionTypeId();
			// tryPlayMusic(AMMusics.JOVIAN_BOSS_MUSIC);
			// tryPlayMusic(AMMusics.JOVIAN_BOSS_MUSIC);
			if (dimType == AMDimensions.ALIENDIM_TYPE) {
				if (currentMusic == null) {

					musicManager().stopPlaying();
					if (random.nextInt(500) == 0)
						tryPlayMusic(AMMusics.ALIEN_MUSIC);

				}
			} else if (dimType == AMDimensions.JOVIANDIM_TYPE) {
				tryPlayMusic(AMMusics.JOVIAN_BOSS_MUSIC);
				musicManager().stopPlaying();
			}
		}
	}

	private void tryPlayMusic(Music music) {
		// Prevents it from ever trying to play music twice at once.
		if (alreadyPlayedThisTick) {
			Minecraft.crash(new CrashReport("Tried to play music at once.",
					new Exception("Tried to play music in " + this.toString() + " twice in one of tickMusic()")));
		}
		cancelNormalMusic();
		if ((currentMusic == null) || (currentMusic != null && currentMusic.music != music)) {

			stopMusic();
			currentMusic = new MusicSoundInstance(music, random);
			Minecraft.getInstance().getSoundManager().play(currentMusic);
			timesMusicHasBeenPlayed++;
			alreadyPlayedThisTick = true;
		}

	}

	private void stopMusic() {
		if (currentMusic != null) {
			Minecraft.getInstance().getSoundManager().stop(currentMusic);
			currentMusic = null;
		}
	}

	private MusicManager musicManager() {
		return Minecraft.getInstance().getMusicManager();
	}

	private void cancelNormalMusic() {
		if (musicManager() != null)
			musicManager().stopPlaying();

	}

}
