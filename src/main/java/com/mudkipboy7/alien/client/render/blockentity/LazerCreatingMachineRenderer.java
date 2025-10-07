package com.mudkipboy7.alien.client.render.blockentity;

import org.joml.Matrix3f;
import org.joml.Matrix4f;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.mudkipboy7.alien.world.block.blockentity.machine.transport.LazerCreatorBlockEntity;
import com.mudkipboy7.alien.world.block.functional.machine.transport.LazerCreatorBlock;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.entity.BeaconBlockEntity;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LazerCreatingMachineRenderer<T extends LazerCreatorBlockEntity>
		implements BlockEntityRenderer<LazerCreatorBlockEntity> {

	public static final ResourceLocation BEAM_LOCATION = new ResourceLocation("textures/entity/beacon_beam.png");
	public static final int MAX_RENDER_Y = 1024;

	public LazerCreatingMachineRenderer(BlockEntityRendererProvider.Context pContext) {
	}

	public void render(LazerCreatorBlockEntity blockEntity, float partialTick, PoseStack poseStack,
			MultiBufferSource buffer, int packedLight, int packedOverlay) {
		long time = blockEntity.getLevel().getGameTime();
		float[] color = { 1F, 0.0F, 0F };
		int length = blockEntity.getBlockState().getValue(LazerCreatorBlock.LAZER_LENGTH);
		Direction direction = blockEntity.getBlockState().getValue(LazerCreatorBlock.DIRECTION);
		//System.out.println(blockEntity.currentLazersGoingIn);
		renderBeaconBeam(poseStack, buffer, partialTick, time, 0, length, color, direction);

	}

	public static void renderBeaconBeam(PoseStack poseStack, MultiBufferSource bufferSource, float partialTick,
			long gameTime, int yOffset, int length, float[] pColors, Direction direction) {
		float pBeamRadius = 0.20F;
		int i = yOffset + length;
		poseStack.pushPose();
		float f = (float) Math.floorMod(gameTime, 40) + partialTick;

		/*
		 * Sets the rotation based on direction because I'm too lazy to figure out how
		 * to do it without if statements although I definitely can if I wasn't lazy.
		 */
		if (direction == Direction.UP) {
			poseStack.translate(0.5D, 0.5D, 0.5D);
		} else if (direction == Direction.DOWN) {
			poseStack.translate(0.5D, 0.5D, 0.5D);
			poseStack.mulPose(Axis.ZP.rotationDegrees(180));
		} else if (direction == Direction.NORTH) {
			poseStack.translate(0.5D, 0.5D, 0.5D);
			poseStack.mulPose(Axis.XN.rotationDegrees(90));
		} else if (direction == Direction.SOUTH) {
			poseStack.translate(0.5D, 0.5D, 0.5D);
			poseStack.mulPose(Axis.XN.rotationDegrees(270));
		} else if (direction == Direction.EAST) {
			poseStack.translate(0D, 0.5D, 0.5D);
			poseStack.mulPose(Axis.ZP.rotationDegrees(270));
		} else if (direction == Direction.WEST) {
			poseStack.translate(0D, 0.5D, 0.5D);
			poseStack.mulPose(Axis.ZP.rotationDegrees(90));
		}
		poseStack.mulPose(Axis.YP.rotationDegrees(f * 2.25F - 45.0F));
		// float f = (float) Math.floorMod(gameTime, 40) + partialTick;
		float f1 = length < 0 ? f : -f;
		float f2 = Mth.frac(f1 * 0.2F - (float) Mth.floor(f1 * 0.1F));
		float red = pColors[0];
		float green = pColors[1];
		float blue = pColors[2];
		poseStack.pushPose();
		// pPoseStack.mulPose(Axis.YP.rotationDegrees(f * 2.25F - 45.0F));
		float f9 = -pBeamRadius;
		float f12 = -pBeamRadius;
		float f15 = -1.0F + f2;
		float f16 = (float) length * 2.0F * (0.5F / pBeamRadius) + f15;

		renderPart(poseStack, bufferSource.getBuffer(RenderType.beaconBeam(BEAM_LOCATION, false)), red, green, blue,
				1.0F, yOffset, i, 0.0F, pBeamRadius, pBeamRadius, 0.0F, f9, 0.0F, 0.0F, f12, 0.0F, 1.0F, f16, f15);
		// pPoseStack.rotateAround(null, f15, pYOffset, f16);

		poseStack.popPose();

		poseStack.popPose();
	}

	private static void renderPart(PoseStack pPoseStack, VertexConsumer pConsumer, float red, float green, float blue,
			float alpha, int pMinY, int pMaxY, float pX0, float pZ0, float pX1, float pZ1, float pX2, float pZ2,
			float pX3, float pZ3, float pMinU, float pMaxU, float pMinV, float pMaxV) {
		PoseStack.Pose posestack$pose = pPoseStack.last();
		Matrix4f matrix4f = posestack$pose.pose();
		Matrix3f matrix3f = posestack$pose.normal();
		renderQuad(matrix4f, matrix3f, pConsumer, red, green, blue, alpha, pMinY, pMaxY, pX0, pZ0, pX1, pZ1, pMinU,
				pMaxU, pMinV, pMaxV);
		renderQuad(matrix4f, matrix3f, pConsumer, red, green, blue, alpha, pMinY, pMaxY, pX3, pZ3, pX2, pZ2, pMinU,
				pMaxU, pMinV, pMaxV);
		renderQuad(matrix4f, matrix3f, pConsumer, red, green, blue, alpha, pMinY, pMaxY, pX1, pZ1, pX3, pZ3, pMinU,
				pMaxU, pMinV, pMaxV);
		renderQuad(matrix4f, matrix3f, pConsumer, red, green, blue, alpha, pMinY, pMaxY, pX2, pZ2, pX0, pZ0, pMinU,
				pMaxU, pMinV, pMaxV);
	}

	private static void renderQuad(Matrix4f pPose, Matrix3f pNormal, VertexConsumer pConsumer, float pRed, float pGreen,
			float pBlue, float pAlpha, int pMinY, int pMaxY, float pMinX, float pMinZ, float pMaxX, float pMaxZ,
			float pMinU, float pMaxU, float pMinV, float pMaxV) {
		addVertex(pPose, pNormal, pConsumer, pRed, pGreen, pBlue, pAlpha, pMaxY, pMinX, pMinZ, pMaxU, pMinV);
		addVertex(pPose, pNormal, pConsumer, pRed, pGreen, pBlue, pAlpha, pMinY, pMinX, pMinZ, pMaxU, pMaxV);
		addVertex(pPose, pNormal, pConsumer, pRed, pGreen, pBlue, pAlpha, pMinY, pMaxX, pMaxZ, pMinU, pMaxV);
		addVertex(pPose, pNormal, pConsumer, pRed, pGreen, pBlue, pAlpha, pMaxY, pMaxX, pMaxZ, pMinU, pMinV);
	}

	private static void addVertex(Matrix4f pPose, Matrix3f pNormal, VertexConsumer pConsumer, float pRed, float pGreen,
			float pBlue, float pAlpha, int pY, float pX, float pZ, float pU, float pV) {
		pConsumer.vertex(pPose, pX, (float) pY, pZ).color(pRed, pGreen, pBlue, pAlpha).uv(pU, pV)
				.overlayCoords(OverlayTexture.NO_OVERLAY).uv2(15728880).normal(pNormal, 0.0F, 1.0F, 0.0F).endVertex();
	}

	public boolean shouldRenderOffScreen(BeaconBlockEntity pBlockEntity) {
		return true;
	}

	public int getViewDistance() {
		return 256;
	}

	public boolean shouldRender(BeaconBlockEntity pBlockEntity, Vec3 pCameraPos) {
		return Vec3.atCenterOf(pBlockEntity.getBlockPos()).multiply(1.0D, 0.0D, 1.0D)
				.closerThan(pCameraPos.multiply(1.0D, 0.0D, 1.0D), (double) this.getViewDistance());
	}
}
