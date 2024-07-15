package mudkipboy7.alien.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;

import mudkipboy7.alien.AlienMod;
import mudkipboy7.alien.client.render.entity.layer.AMEntityLayers;
import mudkipboy7.alien.client.render.entity.model.TestEntityModel;
import mudkipboy7.alien.world.entity.misc.TestEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TestEntityRenderer extends MobRenderer<TestEntity, TestEntityModel<TestEntity>> {

	public TestEntityRenderer(Context context) {
		super(context, new TestEntityModel<>(context.bakeLayer(AMEntityLayers.TEST_ENTITY)), 0.15F);
	}

	@Override
	protected void scale(TestEntity testEntity, PoseStack poseStack, float partialTickTime) {
		float scale = 2.5F;
		poseStack.scale(scale, scale, scale);
	}

	@Override
	public ResourceLocation getTextureLocation(TestEntity entity) {
		return AlienMod.location("textures/entity/test_entity.png");
	}

}
