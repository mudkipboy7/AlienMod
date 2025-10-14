package com.mudkipboy7.alien.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mudkipboy7.alien.AMConfig;
import com.mudkipboy7.alien.AlienMod;
import com.mudkipboy7.alien.client.render.blockentity.AlienChestRenderer2;
import com.mudkipboy7.alien.client.render.blockentity.LazerCreatingMachineRenderer;
import com.mudkipboy7.alien.world.block.blockentity.AMBlockEntities;
import com.mudkipboy7.alien.world.block.blockentity.machine.transport.LazerCreatorBlockEntity;
import com.mudkipboy7.alien.world.entity.AMEntities;
import com.mudkipboy7.alien.world.entity.boss.JovianBossEntity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RenderArmEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = AlienMod.MODID, value = Dist.CLIENT)
public class AMEntityRender {

	@Mod.EventBusSubscriber(modid = AlienMod.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class ModBus {
		@SubscribeEvent
		public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
			event.registerBlockEntityRenderer(AMBlockEntities.ALIEN_WOOD_SIGN.get(), SignRenderer::new);
			event.registerBlockEntityRenderer(AMBlockEntities.ALIEN_WOOD_HANGING_SIGN.get(), HangingSignRenderer::new);
			event.registerBlockEntityRenderer(AMBlockEntities.LIGNUM_CHEST.get(), AlienChestRenderer2::new);
			event.registerBlockEntityRenderer(AMBlockEntities.LAZER_CREATOR.get(), LazerCreatingMachineRenderer::new);
			
			event.registerEntityRenderer(AMEntities.TEST_ENTITY.get(), TestEntityRenderer::new);
			event.registerEntityRenderer(AMEntities.ALIEN_ZOMBIE.get(), AlienZombieRenderer::new);
			event.registerEntityRenderer(AMEntities.JOVIAN_BOSS.get(), JovianBossRenderer::new);
			event.registerEntityRenderer(AMEntities.JOVIAN_BOSS_MINION.get(), AlienZombieRenderer::new);

		}
	}

	@SubscribeEvent
	public static void onPlayerRender(RenderPlayerEvent event) {
		// event.setCanceled(true);
		Player player = event.getEntity();
		PlayerRenderer renderer = event.getRenderer();
		SurvivalGearRenderingStuff.changePlayerVisibility(player, renderer);
		// event.getRenderer().getModel().leftArm.visible = false;
	}

	@SubscribeEvent
	public static void onArmRender(RenderArmEvent event) {
		if (AMConfig.Client.renderFurCoatFirstPerson.get()) {
			PoseStack poseStack = event.getPoseStack();
			MultiBufferSource multiBufferSource = event.getMultiBufferSource();
			AbstractClientPlayer clientPlayer = event.getPlayer();
			int combinedLight = event.getPackedLight();
			PlayerRenderer playerRenderer = (PlayerRenderer) Minecraft.getInstance().getEntityRenderDispatcher()
					.<AbstractClientPlayer>getRenderer(clientPlayer);
			HumanoidArm playerArm = event.getArm();
			if (SurvivalGearRenderingStuff.itemInSlotCorrect(clientPlayer, EquipmentSlot.CHEST)) {
				event.setCanceled(true);
				SurvivalGearRenderingStuff.renderFirstPersonHand(poseStack, multiBufferSource, combinedLight,
						clientPlayer, playerRenderer, playerArm);
			}
		}
	}

}