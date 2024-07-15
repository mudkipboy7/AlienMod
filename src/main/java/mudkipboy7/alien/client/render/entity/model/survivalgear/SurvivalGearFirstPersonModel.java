package mudkipboy7.alien.client.render.entity.model.survivalgear;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.world.entity.LivingEntity;

public class SurvivalGearFirstPersonModel<T extends LivingEntity> extends PlayerModel<T> {
	public SurvivalGearFirstPersonModel(ModelPart pRoot, boolean pSlim) {
		super(pRoot, pSlim);
	}

	public static LayerDefinition createLayer(boolean pSlim) {
		/*
		 * Pretty much what this does is it changes all of the texture locations on the
		 * used parts
		 */
		CubeDeformation cubeDeformation = CubeDeformation.NONE;
		MeshDefinition meshdefinition = HumanoidModel.createMesh(cubeDeformation, 0.0F);
		PartDefinition partdefinition = meshdefinition.getRoot();
		/*
		 * While these parts arent used, these still have to be defined so the game
		 * won't crash while trying to copy the properties of the player's hand to this.
		 * I think only the new ones in the PlayerModel that aren't in the base
		 * HumanoidModel need to be defined.
		 */
		partdefinition.addOrReplaceChild("ear", CubeListBuilder.create(), PartPose.ZERO);
		partdefinition.addOrReplaceChild("cloak", CubeListBuilder.create(), PartPose.ZERO);
		partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.ZERO);

		/*
		 * Defines the used parts of the model
		 */
		if (pSlim) {
			partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(48, 32).addBox(-2.0F, -2.0F,
					-2.0F, 3.0F, 12.0F, 4.0F, cubeDeformation), PartPose.offset(-5.0F, 2.5F, 0.0F));
			partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(48, 32).mirror().addBox(-1.0F,
					-2.0F, -2.0F, 3.0F, 12.0F, 4.0F, cubeDeformation), PartPose.offset(5.0F, 2.5F, 0.0F));
			partdefinition.addOrReplaceChild("right_sleeve", CubeListBuilder.create().texOffs(0, 48).addBox(-2.0F,
					-2.0F, -2.0F, 3.0F, 12.0F, 4.0F, cubeDeformation.extend(0.25F)),
					PartPose.offset(-5.0F, 2.5F, 0.0F));
			partdefinition
					.addOrReplaceChild(
							"left_sleeve", CubeListBuilder.create().texOffs(0, 48).mirror().addBox(-1.0F, -2.0F, -2.0F,
									3.0F, 12.0F, 4.0F, cubeDeformation.extend(0.25F)),
							PartPose.offset(5.0F, 2.5F, 0.0F));
		} else {
			partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(16, 32).addBox(-3.0F, -2.0F,
					-2.0F, 4.0F, 12.0F, 4.0F, cubeDeformation), PartPose.offset(-5.0F, 2.0F, 0.0F));

			partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(16, 32).mirror().addBox(-1.0F,
					-2.0F, -2.0F, 4.0F, 12.0F, 4.0F, cubeDeformation), PartPose.offset(5.0F, 2.0F, 0.0F));
			partdefinition.addOrReplaceChild("right_sleeve", CubeListBuilder.create().texOffs(32, 32).addBox(-3.0F,
					-2.0F, -2.0F, 4.0F, 12.0F, 4.0F, cubeDeformation.extend(0.25F)),
					PartPose.offset(-5.0F, 2.0F, 0.0F));
			partdefinition
					.addOrReplaceChild(
							"left_sleeve", CubeListBuilder.create().texOffs(32, 32).mirror().addBox(-1.0F, -2.0F, -2.0F,
									4.0F, 12.0F, 4.0F, cubeDeformation.extend(0.25F)),
							PartPose.offset(5.0F, 2.0F, 0.0F));
		}
		// Defines more things that aren't used but need to be defined to prevent crash
		partdefinition.addOrReplaceChild("left_pants", CubeListBuilder.create(), PartPose.ZERO);
		partdefinition.addOrReplaceChild("right_pants", CubeListBuilder.create(), PartPose.ZERO);
		partdefinition.addOrReplaceChild("jacket", CubeListBuilder.create(), PartPose.ZERO);
		return LayerDefinition.create(meshdefinition, 64, 64);
	}
}
