package com.mudkipboy7.alien.client;

import com.mudkipboy7.alien.AlienMod;
import com.mudkipboy7.alien.client.gui.screen.CoalGeneratorScreen;
import com.mudkipboy7.alien.client.gui.screen.EnergyBlockScreen;
import com.mudkipboy7.alien.client.gui.screen.EnergyStorageScreen;
import com.mudkipboy7.alien.client.gui.screen.HazardRemovalScreen;
import com.mudkipboy7.alien.client.render.dim.AlienDimSkyRenderer;
import com.mudkipboy7.alien.client.render.dim.AlienDimSpecialEffects;
import com.mudkipboy7.alien.client.render.dim.JovianDimSpecialEffects;
import com.mudkipboy7.alien.inventory.AMMenuTypes;
import com.mudkipboy7.alien.sky.AlienDimSky;
import com.mudkipboy7.alien.sound.AMMusicManager;
import com.mudkipboy7.alien.world.block.AMBlocks;
import com.mudkipboy7.alien.world.block.AMFluids;
import com.mudkipboy7.alien.world.worldgen.dimension.AMDimensions;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.DimensionSpecialEffects.SkyType;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterDimensionSpecialEffectsEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.event.ViewportEvent.ComputeFogColor;
import net.minecraftforge.client.event.ViewportEvent.RenderFog;
import net.minecraftforge.event.TickEvent.ClientTickEvent;
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
			ItemBlockRenderTypes.setRenderLayer(AMBlocks.LIGNUM_LEAVES.get(), RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(AMBlocks.TALL_GRAMEN.get(), RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(AMBlocks.LIGNUM_SAPLING.get(), RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(AMBlocks.DEAD_PLANT.get(), RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(AMBlocks.DOUBLE_TALL_GRAMEN.get(), RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(AMBlocks.LIGNUM_DOOR.get(), RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(AMBlocks.LIGNUM_TRAPDOOR.get(), RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(AMBlocks.HARDENED_CLOUD.get(), RenderType.translucent());

			MenuScreens.register(AMMenuTypes.HAZARD_REMOVAL.get(), HazardRemovalScreen::new);
			MenuScreens.register(AMMenuTypes.COAL_GENERATOR.get(), CoalGeneratorScreen::new);
			MenuScreens.register(AMMenuTypes.ENERGY_STORAGE.get(), EnergyStorageScreen::new);
			MenuScreens.register(AMMenuTypes.ENERGY_BLOCK.get(), EnergyBlockScreen::new);
		}

		@SubscribeEvent
		public static void setupDimensionRenderInfo(RegisterDimensionSpecialEffectsEvent event) {
			// Makes the stars
			new AlienDimSkyRenderer();

			// Special effects for the alien planet
			event.register(AMDimensions.ALIENDIM_ID,
					new AlienDimSpecialEffects(110.0F, true, SkyType.NONE, false, false));
			// Special effects for the jovian
			event.register(AMDimensions.JOVIANDIM_ID,
					new JovianDimSpecialEffects(50.0F, true, SkyType.NONE, false, false));

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
	@SubscribeEvent
	public static void onClientTick(ClientTickEvent event) {
		AMMusicManager.tickMusic();
	}
}