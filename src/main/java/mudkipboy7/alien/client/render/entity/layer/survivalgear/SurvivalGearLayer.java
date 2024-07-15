package mudkipboy7.alien.client.render.entity.layer.survivalgear;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import mudkipboy7.alien.SpecialPlayers;
import mudkipboy7.alien.SpecialPlayers.SpecialPlayerType;
import mudkipboy7.alien.client.render.entity.SurvivalGearRenderingStuff;
import mudkipboy7.alien.client.render.entity.model.survivalgear.SurvivalGearModel;
import mudkipboy7.alien.client.render.entity.model.survivalgear.SurvivalGearModel.InnerModel;
import mudkipboy7.alien.client.render.entity.model.survivalgear.SurvivalGearModel.OuterModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SurvivalGearLayer<T extends LivingEntity, M extends HumanoidModel<T>> extends RenderLayer<T, M> {
	private final SurvivalGearModel.InnerModel<T> innerModel;
	private final SurvivalGearModel.OuterModel<T> outerModel;

	public SurvivalGearLayer(RenderLayerParent<T, M> pRenderer, InnerModel<T> pInnerModel, OuterModel<T> pOuterModel,
			ModelManager pModelManager) {
		super(pRenderer);
		this.innerModel = pInnerModel;
		this.outerModel = pOuterModel;
		// this.isSlim = slim;
	}

	public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, T livingEntity, float limbSwing,
			float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		ResourceLocation texture = SurvivalGearRenderingStuff.getEntityTexture(livingEntity);
		this.getParentModel().copyPropertiesTo(this.outerModel);
		this.getParentModel().copyPropertiesTo(this.innerModel);
		// Sets all parts of the model to not be visible
		this.outerModel.setAllVisible(false);
		this.innerModel.setAllVisible(false);

		VertexConsumer vertexconsumer = buffer.getBuffer(RenderType.entityTranslucent(texture));
		// this.getParentModel().head.visible = false;
		if (SurvivalGearRenderingStuff.itemInSlotCorrect(livingEntity, EquipmentSlot.HEAD)) {
			this.outerModel.hat.visible = true;
		}

		if (SurvivalGearRenderingStuff.itemInSlotCorrect(livingEntity, EquipmentSlot.CHEST)) {

			this.innerModel.rightArm.visible = true;
			this.innerModel.leftArm.visible = true;

			this.outerModel.body.visible = true;
			this.outerModel.rightArm.visible = true;
			this.outerModel.leftArm.visible = true;
			this.outerModel.head.visible = true;

		}
		if (SurvivalGearRenderingStuff.itemInSlotCorrect(livingEntity, EquipmentSlot.LEGS)) {
			this.innerModel.body.visible = true;
			this.innerModel.rightLeg.visible = true;
			this.innerModel.leftLeg.visible = true;

		}

		if (SurvivalGearRenderingStuff.itemInSlotCorrect(livingEntity, EquipmentSlot.FEET)) {
			this.outerModel.rightLeg.visible = true;
			this.outerModel.leftLeg.visible = true;
		}
		/*
		 * Renders special stuff for special players
		 */
		if (livingEntity instanceof Player player) {
			String username = player.getName().getString();
			SpecialPlayerType specialPlayerType = SpecialPlayers.getSpecialPlayersType(player);
			if (specialPlayerType != null && specialPlayerType.hasFlag("dress")
					&& SurvivalGearRenderingStuff.itemInSlotCorrect(livingEntity, EquipmentSlot.CHEST)) {
				this.innerModel.dress.visible = true;
				this.innerModel.dress.copyFrom(this.innerModel.body);
				if (this.getParentModel().crouching) {
					this.innerModel.dress.xRot -= 0.5F;
					this.innerModel.dress.z = 4.2F;
					this.innerModel.dress.y -= 1.4F;
				}
			}
		}
		// Actually renders the rest that wasn't rendered before
		this.outerModel.renderToBuffer(poseStack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F,
				1.0F, 1.0F);
		this.innerModel.renderToBuffer(poseStack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F,
				1.0F, 1.0F);

	}
}
