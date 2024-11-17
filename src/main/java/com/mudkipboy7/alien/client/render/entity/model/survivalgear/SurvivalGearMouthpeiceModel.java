package com.mudkipboy7.alien.client.render.entity.model.survivalgear;

import com.google.common.collect.ImmutableList;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.world.entity.LivingEntity;

@Deprecated
public class SurvivalGearMouthpeiceModel extends HumanoidModel<LivingEntity> {
	// public static final ResourceLocation TEXTURE_LOCATION = new
	// ResourceLocation(AlienMod.MODID, "survivalgear");
	public final ModelPart resperator;

	public SurvivalGearMouthpeiceModel(ModelPart root) {
		super(root);
		this.resperator = root.getChild("mouthpeice");
	}

	public static LayerDefinition createLayer() {
		CubeDeformation cubeDeformation = CubeDeformation.NONE;
		MeshDefinition meshDefinition = HumanoidModel.createMesh(cubeDeformation, 0.0F);
		PartDefinition partDefinition = meshDefinition.getRoot();
		// The model itself
		partDefinition.addOrReplaceChild("mouthpeice", CubeListBuilder.create()
				// Mouthpeice
				.texOffs(24, 0).addBox(-2.0F, -1.0F, 0.0F, 4.0F, 3.0F, 2.0F, cubeDeformation)

				// Left Air Sack
				.texOffs(24, 5).addBox(2.0F, 0.0F, 1.0F, 3.0F, 2.0F, 1.0F, cubeDeformation)

				// Right Air Sack
				.texOffs(32, 5).addBox(-5.0F, 0.0F, 1.0F, 3.0F, 2.0F, 1.0F, cubeDeformation),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshDefinition, 64, 64);
	}

	@Override
	protected Iterable<ModelPart> headParts() {
		return ImmutableList.of(this.resperator);
	}

	@Override
	protected Iterable<ModelPart> bodyParts() {
		return ImmutableList.of();
	}

}
