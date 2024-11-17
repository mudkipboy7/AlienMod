package com.mudkipboy7.alien.client.render.entity.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;

public class TestEntityModel<T extends Mob> extends EntityModel<T> {

	private ModelPart body;
	private ModelPart rightLeg;
	private ModelPart leftLeg;

	public TestEntityModel(ModelPart root) {
		super();
		this.body = root.getChild("body");
		this.rightLeg = body.getChild("right_leg");
		this.leftLeg = body.getChild("left_leg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body",
				CubeListBuilder.create().texOffs(10, 10)
						.addBox(-2.0F, -7.0F, -3.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(0, 10)
						.addBox(-2.0F, -6.0F, 2.0F, 4.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(0, 0)
						.addBox(-2.0F, -8.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 24.0F, 0.0F));

		body.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(8, 13).addBox(-2.0F, 2.0F, -1.0F, 2.0F,
				2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, 0.0F));

		body.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(12, 0).addBox(0.0F, 2.0F, -1.0F, 2.0F,
				2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 20, 18);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay,
			float red, float green, float blue, float alpha) {
		this.body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		// this.rightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay,
		// red, green, blue, alpha);
		/// this.leftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay,
		// red, green, blue, alpha);

	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch) {
		float f = 2.2F;
		//this.rightLeg.offsetRotation(new Vector3f(-1, 0, 0));
		//this.rightLeg.xRot
		this.rightLeg.xRot = (Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount / f);
		this.leftLeg.xRot = (Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount / f);
	}
}
