package com.mudkipboy7.alien.sound;

import com.mudkipboy7.alien.world.worldgen.dimension.AMDimensions;

import net.minecraft.client.Minecraft;
import net.minecraft.client.sounds.MusicManager;

public class AMMusicManager {

	public static void tickMusic() {
		MusicManager musicManager = Minecraft.getInstance().getMusicManager();
		if (!musicManager.isPlayingMusic(AMMusics.JOVIAN_BOSS_MUSIC)) {
			if (Minecraft.getInstance().level != null) {
				if (Minecraft.getInstance().level.dimensionTypeId() == AMDimensions.JOVIANDIM_TYPE) {
					musicManager.startPlaying(AMMusics.JOVIAN_BOSS_MUSIC);
				}
			}
		}
	}
}
