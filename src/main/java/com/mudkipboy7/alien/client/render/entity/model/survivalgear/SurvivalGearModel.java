package com.mudkipboy7.alien.client.render.entity.model.survivalgear;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class SurvivalGearModel {
	@OnlyIn(Dist.CLIENT)
	public static class InnerModel<T extends LivingEntity> extends HumanoidModel<T> {
		public ModelPart dress;

		public InnerModel(ModelPart root) {
			super(root);
			this.dress = root.getChild("dress");
		}

		public static LayerDefinition createBodyLayer(boolean isSlim) {
			CubeDeformation cubeDeformation = new CubeDeformation(0.01F);
			MeshDefinition meshdefinition = HumanoidModel.createMesh(cubeDeformation, 0.0F);
			PartDefinition partdefinition = meshdefinition.getRoot();

			partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F,
					8.0F, 12.0F, 4.0F, cubeDeformation), PartPose.offset(0.0F, 0.0F, 0.0F));
			if (isSlim) {
				partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(48, 32).addBox(-2.0F,
						-2.0F, -2.0F, 3.0F, 12.0F, 4.0F, cubeDeformation), PartPose.offset(-5.0F, 2.5F, 0.0F));
				partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(48, 32).mirror().addBox(
						-1.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, cubeDeformation), PartPose.offset(5.0F, 2.5F, 0.0F));
			} else {
				partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(16, 32).addBox(-3.0F,
						-2.0F, -2.0F, 4.0F, 12.0F, 4.0F, cubeDeformation), PartPose.offset(-5.0F, 2.0F, 0.0F));
				partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(16, 32).mirror().addBox(
						-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, cubeDeformation), PartPose.offset(5.0F, 2.0F, 0.0F));
			}
			partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F,
					-2.0F, 4.0F, 12.0F, 4.0F, cubeDeformation), PartPose.offset(-1.9F, 12.0F, 0.0F));
			partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-2.0F,
					0.0F, -2.0F, 4.0F, 12.0F, 4.0F, cubeDeformation), PartPose.offset(1.9F, 12.0F, 0.0F));
			/*
			 * This is here for a joke that if notch ever plays this mod he'll be wearing
			 * some trans colors crap because it's funny because of what he said.
			 */
			partdefinition.addOrReplaceChild("dress", CubeListBuilder.create().texOffs(14, 48).addBox(-4.0F, 10.0F,
					-2.0F, 8.0F, 12.0F, 4.0F, cubeDeformation.extend(0.5F)), PartPose.offset(0.0F, 0.0F, 0.0F));
			return LayerDefinition.create(meshdefinition, 64, 64);
		}

		@Override
		public void setAllVisible(boolean pVisible) {
			super.setAllVisible(pVisible);
			this.dress.visible = pVisible;

		}

		@Override
		protected Iterable<ModelPart> bodyParts() {
			return Iterables.concat(super.bodyParts(), ImmutableList.of(this.dress));
		}

	}

	@OnlyIn(Dist.CLIENT)
	public static class OuterModel<T extends LivingEntity> extends HumanoidModel<T> {
		public OuterModel(ModelPart root) {
			super(root);
		}

		public static LayerDefinition createBodyLayer(boolean isSlim) {
			CubeDeformation cubeDeformation = new CubeDeformation(0.30F);
			MeshDefinition meshdefinition = HumanoidModel.createMesh(cubeDeformation, 0.0F);
			PartDefinition partdefinition = meshdefinition.getRoot();
			/*
			 * The reason why the head one is extended is to fix a glitch where the hat
			 * layer of the player will render over the hood, this isn't a problem for the
			 * others because the outer layers of those are made invisible. The reason why
			 * the hat layer needs to stay is because that will impact how the player's face
			 * looks and I don't want people's hair disappearing. But with the other layers
			 * it's moreso fine because that will usually just be parts of clothing and so
			 * it's fine to replace those. In my opinion it looks weird if the outer layer
			 * is far from the inner one so I have to do this to make it closer but the hood
			 * is fine.
			 */
			partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F,
					8.0F, 8.0F, 8.0F, cubeDeformation.extend(0.25F)), PartPose.offset(0.0F, 0.0F, 0.0F));

			partdefinition.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -8.0F, -4.0F,
					8.0F, 8.0F, 8.0F, cubeDeformation.extend(0.5F)), PartPose.offset(0.0F, 0.0F, 0.0F));

			partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(40, 16).addBox(-4.0F, 0.0F, -2.0F,
					8.0F, 12.0F, 4.0F, cubeDeformation), PartPose.ZERO);
			if (isSlim) {
				partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(0, 48).addBox(-2.0F,
						-2.0F, -2.0F, 3.0F, 12.0F, 4.0F, cubeDeformation), PartPose.offset(-5.0F, 2.5F, 0.0F));
				partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(0, 48).mirror().addBox(
						-1.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, cubeDeformation), PartPose.offset(5.0F, 2.5F, 0.0F));
			} else {
				partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(32, 32).addBox(-3.0F,
						-2.0F, -2.0F, 4.0F, 12.0F, 4.0F, cubeDeformation), PartPose.offset(-5.0F, 2.0F, 0.0F));
				partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(32, 32).mirror().addBox(
						-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, cubeDeformation), PartPose.offset(5.0F, 2.0F, 0.0F));
			}
			partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 32).addBox(-2.0F, 0.0F,
					-2.0F, 4.0F, 12.0F, 4.0F, cubeDeformation), PartPose.offset(-1.9F, 12.0F, 0.0F));
			partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 32).mirror().addBox(-2.0F,
					0.0F, -2.0F, 4.0F, 12.0F, 4.0F, cubeDeformation), PartPose.offset(1.9F, 12.0F, 0.0F));
			return LayerDefinition.create(meshdefinition, 64, 64);
		}

	}

}
