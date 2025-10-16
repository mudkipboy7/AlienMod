package com.mudkipboy7.alien.sound;

import com.mudkipboy7.alien.world.worldgen.dimension.AMDimensions;

import net.minecraft.client.Minecraft;
import net.minecraft.client.sounds.MusicManager;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.Musics;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;

public class AMMusicManager {

	@SuppressWarnings("resource")
	public static void tickMusic() {
		MusicManager musicManager = Minecraft.getInstance().getMusicManager();
		Level level = Minecraft.getInstance().level;
		if (Minecraft.getInstance().level != null) {
			ResourceKey<DimensionType> dimType = level.dimensionTypeId();
			if (dimType == AMDimensions.ALIENDIM_TYPE) {
				stopNormalGameMusic(musicManager);
			} else if (dimType == AMDimensions.JOVIANDIM_TYPE) {
				if (!musicManager.isPlayingMusic(AMMusics.JOVIAN_BOSS_MUSIC)) {
					stopNormalGameMusic(musicManager);
					musicManager.startPlaying(AMMusics.JOVIAN_BOSS_MUSIC);
				}
				// Stops it from playing non boss music in joviandim

			}
		}
	}

	private static void stopNormalGameMusic(MusicManager musicManager) {
		musicManager.stopPlaying(Musics.CREATIVE);
		musicManager.stopPlaying(Musics.CREDITS);
		musicManager.stopPlaying(Musics.END);
		musicManager.stopPlaying(Musics.END_BOSS);
		musicManager.stopPlaying(Musics.GAME);
		musicManager.stopPlaying(Musics.MENU);
		musicManager.stopPlaying(Musics.UNDER_WATER);
	}
}
