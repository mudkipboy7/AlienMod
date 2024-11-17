package com.mudkipboy7.alien.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mudkipboy7.alien.AlienMod;
import com.mudkipboy7.alien.SpecialPlayers;
import com.mudkipboy7.alien.SpecialPlayers.SpecialPlayerType;
import com.mudkipboy7.alien.client.render.entity.layer.AMEntityLayers;
import com.mudkipboy7.alien.client.render.entity.model.survivalgear.SurvivalGearFirstPersonModel;
import com.mudkipboy7.alien.world.item.functional.wearable.ISurvivalItem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.PlayerSkin.Model;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;

public class SurvivalGearRenderingStuff {
	public static final ResourceLocation NPC_TEXTURE = AlienMod
			.location("textures/models/survivalgear/survival_gear.png");

	public static boolean itemInSlotCorrect(LivingEntity livingEntity, EquipmentSlot slot) {
		Item wornItem = livingEntity.getItemBySlot(slot).getItem();
		return wornItem instanceof ISurvivalItem;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void renderFirstPersonHand(PoseStack poseStack, MultiBufferSource buffer, int combinedLight,
			AbstractClientPlayer clientPlayer, PlayerRenderer renderer, HumanoidArm playerArm) {
		boolean isSlim = clientPlayer.getSkin().model() == Model.SLIM;
		boolean sinister = (playerArm == HumanoidArm.LEFT);

		ResourceLocation texture = getEntityTexture(clientPlayer);
		PlayerModel playerModel = renderer.getModel();
		// Makes the model for the coat
		SurvivalGearFirstPersonModel coatModel = new SurvivalGearFirstPersonModel(Minecraft.getInstance()
				.getEntityModels().bakeLayer(isSlim ? AMEntityLayers.SLIM_FIRST_PERSON_SURVIVAL_CLOTHES
						: AMEntityLayers.FIRST_PERSON_SURVIVAL_CLOTHES),
				isSlim);
		ModelPart armModel = sinister ? playerModel.leftArm : playerModel.rightArm;
		ModelPart sleveModel = sinister ? playerModel.leftSleeve : playerModel.rightSleeve;
		ModelPart coatInnerModel = sinister ? coatModel.leftArm : coatModel.rightArm;
		ModelPart coatOuterModel = sinister ? coatModel.leftSleeve : coatModel.rightSleeve;
		armModel.visible = true;
		sleveModel.visible = true;
		playerModel.attackTime = 0.0F;
		playerModel.crouching = false;
		playerModel.swimAmount = 0.0F;
		playerModel.setupAnim(clientPlayer, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
		// Resets all of them
		armModel.xRot = 0.0F;
		coatInnerModel.xRot = 0.0F;
		sleveModel.xRot = 0.0F;
		coatOuterModel.xRot = 0.0F;

		// Copies the properties of the player's hand to the jacket's
		coatInnerModel.copyFrom(armModel);
		coatOuterModel.copyFrom(sleveModel);

		/*
		 * Renders the player's arm texture under the glove so if the player uses a
		 * texturepack that makes the sleeve of the coat invisible it will display their
		 * skin instead of steve's skin.
		 */
		armModel.render(poseStack, buffer.getBuffer(RenderType.entitySolid(renderer.getTextureLocation(clientPlayer))),
				combinedLight, OverlayTexture.NO_OVERLAY);
		// Renders the gloves
		coatInnerModel.render(poseStack, buffer.getBuffer(RenderType.entityTranslucent(texture)), combinedLight,
				OverlayTexture.NO_OVERLAY);

		// Renders the coat's sleeve
		coatOuterModel.render(poseStack, buffer.getBuffer(RenderType.entityTranslucent(texture)), combinedLight,
				OverlayTexture.NO_OVERLAY);
	}

	@SuppressWarnings("rawtypes")
	public static void changePlayerVisibility(Player player, PlayerRenderer renderer) {
		PlayerModel model = renderer.getModel();
		if (itemInSlotCorrect(player, EquipmentSlot.CHEST)) {
			model.jacket.visible = false;
			model.leftSleeve.visible = false;
			model.rightSleeve.visible = false;
		}
		if (itemInSlotCorrect(player, EquipmentSlot.LEGS)) {
			model.leftPants.visible = false;
			model.rightPants.visible = false;
		}
	}

	/*
	 * Gets the texture of the survival gear
	 */
	public static ResourceLocation getEntityTexture(LivingEntity livingEntity) {
		if (livingEntity instanceof Player player) {
			SpecialPlayerType specialPlayerType = SpecialPlayers.getSpecialPlayersType(player);
			if (specialPlayerType != null)
				return specialPlayerType.getSurvivalGearTexture();
		}
		return NPC_TEXTURE;
	}
}
