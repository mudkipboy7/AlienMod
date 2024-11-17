package com.mudkipboy7.alien.event;

import com.mudkipboy7.alien.AlienMod;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.level.BlockEvent.BreakEvent;
import net.minecraftforge.event.level.BlockEvent.EntityPlaceEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = AlienMod.MODID)
public class AMLevelEvents {
	@SubscribeEvent
	public static void onEntityBlockPlace(EntityPlaceEvent event) {
		BlockState blockState = event.getPlacedBlock();
		Block block = blockState.getBlock();
		BlockPos pos = event.getPos();
		Holder<Biome> biome = event.getLevel().getBiome(pos);
		/*
		 * if (biome.containsTag(AMBiomeTags.ULTRACOLD)) { if (block ==
		 * Blocks.GRASS_BLOCK) { int l = pos.getX(); int i = pos.getY(); int j =
		 * pos.getZ(); if (event.getEntity() instanceof Player player) {
		 * event.getLevel().playSound(player, pos, SoundEvents.FIRE_EXTINGUISH,
		 * SoundSource.BLOCKS, 0.5F, 2.6F);
		 * event.getLevel().addParticle(ParticleTypes.LARGE_SMOKE, (double)l +
		 * Math.random(), (double)i + Math.random(), (double)j + Math.random(), 0.0D,
		 * 0.0D, 0.0D); } event.getBlockSnapshot().getLevel().setBlock(pos,
		 * Blocks.DIRT.defaultBlockState(), 0); } }
		 */
	}

}
