package com.mudkipboy7.alien.client.render.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;

import com.mudkipboy7.alien.AlienMod;
import com.mudkipboy7.alien.client.render.entity.layer.AMEntityLayers;
import com.mudkipboy7.alien.client.render.entity.model.AlienZombieModel;
import com.mudkipboy7.alien.world.entity.monster.AlienZombie;

import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class AlienZombieRenderer extends HumanoidMobRenderer<AlienZombie, AlienZombieModel<AlienZombie>> {

	public AlienZombieRenderer(Context context) {
		super(context, new AlienZombieModel<>(context.bakeLayer(AMEntityLayers.ALIEN_ZOMBIE)), 0.5F);
		// this.addLayer(new HumanoidArmorLayer<>(this, pInnerModel, pOuterModel,
		// context.getModelManager()));
	}

	protected boolean isShaking(AlienZombie entity) {
		return super.isShaking(entity);
	}

	@Override
	public ResourceLocation getTextureLocation(AlienZombie entity) {
		return AlienMod.location("textures/entity/alien_zombie.png");

	}

}
