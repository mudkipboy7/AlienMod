package com.mudkipboy7.alien.client;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mudkipboy7.alien.AMConfig;
import com.mudkipboy7.alien.AlienMod;
import com.mudkipboy7.alien.client.gui.screen.CoalGeneratorScreen;
import com.mudkipboy7.alien.client.gui.screen.EnergyStorageScreen;
import com.mudkipboy7.alien.client.gui.screen.HazardRemovalScreen;
import com.mudkipboy7.alien.client.render.dim.AlienDimSkyRenderer;
import com.mudkipboy7.alien.client.render.dim.AlienDimSpecialEffects;
import com.mudkipboy7.alien.client.render.entity.SurvivalGearRenderingStuff;
import com.mudkipboy7.alien.inventory.AMMenuTypes;
import com.mudkipboy7.alien.sky.AlienDimSky;
import com.mudkipboy7.alien.world.WorldFuncs;
import com.mudkipboy7.alien.world.block.AMBlocks;
import com.mudkipboy7.alien.world.block.AMFluids;
import com.mudkipboy7.alien.world.worldgen.dimension.AMDimensions;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.DimensionSpecialEffects.SkyType;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.server.packs.resources.PreparableReloadListener;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimplePreparableReloadListener;
import net.minecraft.server.packs.resources.PreparableReloadListener.PreparationBarrier;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterClientReloadListenersEvent;
import net.minecraftforge.client.event.RegisterDimensionSpecialEffectsEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RenderArmEvent;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.client.event.ViewportEvent.ComputeFogColor;
import net.minecraftforge.client.event.ViewportEvent.RenderFog;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = AlienMod.MODID, value = Dist.CLIENT)
public class AMClientEvents {

	@Mod.EventBusSubscriber(modid = AlienMod.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class ModBus {
		@SuppressWarnings("deprecation")
		@SubscribeEvent
		public static void onClientSetup(FMLClientSetupEvent event) {
			ItemBlockRenderTypes.setRenderLayer(AMFluids.AMMONIA_LIQUID.get(), RenderType.translucent());
			ItemBlockRenderTypes.setRenderLayer(AMFluids.AMMONIA_LIQUID_FLOWING.get(), RenderType.translucent());
			ItemBlockRenderTypes.setRenderLayer(AMBlocks.ALIEN_LEAVES.get(), RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(AMBlocks.ALIEN_GRASS.get(), RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(AMBlocks.ALIEN_SAPLING.get(), RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(AMBlocks.DEAD_PLANT.get(), RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(AMBlocks.ALIEN_DOUBLE_GRASS.get(), RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(AMBlocks.ALIEN_WOOD_DOOR.get(), RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(AMBlocks.ALIEN_WOOD_TRAPDOOR.get(), RenderType.cutout());

			MenuScreens.register(AMMenuTypes.HAZARD_REMOVAL.get(), HazardRemovalScreen::new);
			MenuScreens.register(AMMenuTypes.COAL_GENERATOR.get(), CoalGeneratorScreen::new);
			MenuScreens.register(AMMenuTypes.ENERGY_STORAGE.get(), EnergyStorageScreen::new);
		}

		@SubscribeEvent
		public static void setupDimensionRenderInfo(RegisterDimensionSpecialEffectsEvent event) {
			// Makes the stars
			new AlienDimSkyRenderer();

			// Special effects for the alien planet
			event.register(AMDimensions.ALIENDIM_ID,
					new AlienDimSpecialEffects(110.0F, true, SkyType.NONE, false, false));

		}

		@SubscribeEvent
		public static void registerGuiOverlays(RegisterGuiOverlaysEvent event) {

		}
	}

	@SubscribeEvent
	public static void onRenderGui(RenderGuiOverlayEvent event) {
		event.getOverlay();
	}

	@SubscribeEvent
	public static void onRenderFog(RenderFog event) {
		// event.setCanceled(true);
//System.out.println("fefww");
	}

	@SubscribeEvent
	public static void setFogColors(ComputeFogColor event) {
		AlienDimSky.setFogColors(event);
	}
}