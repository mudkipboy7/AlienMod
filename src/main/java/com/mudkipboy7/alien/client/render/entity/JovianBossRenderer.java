package com.mudkipboy7.alien.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mudkipboy7.alien.AlienMod;
import com.mudkipboy7.alien.client.render.entity.layer.AMEntityLayers;
import com.mudkipboy7.alien.client.render.entity.model.JovianBossModel;
import com.mudkipboy7.alien.world.entity.boss.JovianBossEntity;

import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class JovianBossRenderer extends HumanoidMobRenderer<JovianBossEntity, JovianBossModel<JovianBossEntity>> {

	public JovianBossRenderer(Context context) {
		super(context, new JovianBossModel<>(context.bakeLayer(AMEntityLayers.JOVIAN_BOSS)), 0.15F);
		
	}

	@Override
	protected void scale(JovianBossEntity testEntity, PoseStack poseStack, float partialTickTime) {
		float scale = 1.0F;
		poseStack.scale(scale, scale, scale);
	}

	@Override
	public ResourceLocation getTextureLocation(JovianBossEntity entity) {
		return AlienMod.location("textures/entity/jovian_boss.png");
	}

}
