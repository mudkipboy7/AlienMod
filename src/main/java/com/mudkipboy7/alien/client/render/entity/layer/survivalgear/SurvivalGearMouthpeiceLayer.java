package com.mudkipboy7.alien.client.render.entity.layer.survivalgear;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mudkipboy7.alien.AlienMod;
import com.mudkipboy7.alien.client.render.entity.SurvivalGearRenderingStuff;
import com.mudkipboy7.alien.client.render.entity.layer.AMEntityLayers;
import com.mudkipboy7.alien.client.render.entity.model.survivalgear.SurvivalGearMouthpeiceModel;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;

@Deprecated
public class SurvivalGearMouthpeiceLayer<T extends LivingEntity, M extends HumanoidModel<T>> extends RenderLayer<T, M> {
	/*
	 * The reason why I decided to not use this one is because it looked weird on
	 * certain player skins so I decided to use something different. But I spent a
	 * ton of time on this so I'm going to still leave it in
	 */
	private final SurvivalGearMouthpeiceModel survivalGearModel;
	private static final ResourceLocation texture = AlienMod.location("textures/models/survivalgear/survival_gear_unused_mouthpeice.png");
	public SurvivalGearMouthpeiceLayer(RenderLayerParent<T, M> pRenderer, EntityModelSet modelSet) {
		super(pRenderer);
		this.survivalGearModel = new SurvivalGearMouthpeiceModel(
				modelSet.bakeLayer(AMEntityLayers.SURVIVAL_GEAR_MOUTHPEICE));
	}

	@Override
	public void render(PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, T livingEntity,
			float pLimbSwing, float pLimbSwingAmount, float pPartialTick, float pAgeInTicks, float pNetHeadYaw,
			float pHeadPitch) {
		EntityModel<LivingEntity> entityModel = this.survivalGearModel;
		Item wornItem = livingEntity.getItemBySlot(EquipmentSlot.HEAD).getItem();
		if (SurvivalGearRenderingStuff.itemInSlotCorrect(livingEntity, EquipmentSlot.HEAD) && false) {
			this.survivalGearModel.prepareMobModel(livingEntity, pLimbSwing, pLimbSwingAmount, pPartialTick);
			this.survivalGearModel.setupAnim(livingEntity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw,
					pHeadPitch);
			VertexConsumer vertexConsumer = pBuffer
					.getBuffer(RenderType.armorCutoutNoCull(texture));
			float yPosModifier = -0.85F;
			float zPosModifier = -0.34F;
			// pPoseStack.scale(1.1F, 1.1F, 1.1F);
			/*
			 * Responsible for the rotation of the model so it looks anchored to the
			 * entity's head.
			 */
			if (entityModel instanceof HumanoidModel && entityModel != null) {
				@SuppressWarnings("rawtypes")
				HumanoidModel humanoidModel = (HumanoidModel) entityModel;
				ModelPart head = humanoidModel.head;

				// This checks if the entity is sneaking, if so it draws the model lower.
				if (livingEntity.isCrouching()) {
					/*
					 * There may be a glitch with this, it should put it at the same position of the
					 * head, but it might not.
					 */
					pPoseStack.translate(0.0F, yPosModifier + 1.11F, 0.0F);
				}
				head.translateAndRotate(pPoseStack);
				pPoseStack.translate(0.0F, yPosModifier, zPosModifier);
			}
			// This actually draws it.
			survivalGearModel.renderToBuffer(pPoseStack, vertexConsumer, pPackedLight, OverlayTexture.NO_OVERLAY, 1.0F,
					1.0F, 1.0F, 1.0F);
		}
	}
}
