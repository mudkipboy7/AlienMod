package com.mudkipboy7.alien.client.render;

import org.joml.Matrix4f;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.blaze3d.vertex.VertexSorting;
import com.mojang.math.Axis;
import com.mudkipboy7.alien.AlienMod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;

public class AMPanoramaRenderer {
	// private static final CubeMap CUBE_MAP = new
	// CubeMap(AlienMod.location("textures/environment/phases.png"));

	public static void render(float deltaT, float alpha) {
	      Tesselator tesselator = Tesselator.getInstance();
	      BufferBuilder bufferbuilder = tesselator.getBuilder();
	      Matrix4f matrix4f = (new Matrix4f()).setPerspective(1.4835298F, (float)Minecraft.getInstance().getWindow().getWidth() / (float)Minecraft.getInstance().getWindow().getHeight(), 0.05F, 10.0F);
	      RenderSystem.backupProjectionMatrix();
	      RenderSystem.setProjectionMatrix(matrix4f, VertexSorting.DISTANCE_TO_ORIGIN);
	      PoseStack posestack = RenderSystem.getModelViewStack();
	      posestack.pushPose();
	      posestack.setIdentity();
	      posestack.mulPose(Axis.XP.rotationDegrees(180.0F));
	      RenderSystem.applyModelViewMatrix();
	      RenderSystem.setShader(GameRenderer::getPositionTexColorShader);
	      RenderSystem.enableBlend();
	      RenderSystem.disableCull();
	      RenderSystem.depthMask(false);
	      int i = 2;

	      for(int j = 0; j < 4; ++j) {
	         posestack.pushPose();
	         float f = ((float)(j % 2) / 2.0F - 0.5F) / 256.0F;
	         float f1 = ((float)(j / 2) / 2.0F - 0.5F) / 256.0F;
	         float f2 = 0.0F;
	         posestack.translate(f, f1, 0.0F);
	        // posestack.mulPose(Axis.XP.rotationDegrees((float) org.joml.Math.random()));
	         //posestack.mulPose(Axis.YP.rotationDegrees((float) org.joml.Math.random()));
	         RenderSystem.applyModelViewMatrix();


	            RenderSystem.setShaderTexture(0, AlienMod.location("textures/environment/alien_sun.png"));
	            bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX_COLOR);
	            int l = Math.round(255.0F * alpha) / (j + 1);

	               bufferbuilder.vertex(-1.0D, -1.0D, 1.0D).uv(0.0F, 0.0F).color(255, 255, 255, l).endVertex();
	               bufferbuilder.vertex(-1.0D, 1.0D, 1.0D).uv(0.0F, 1.0F).color(255, 255, 255, l).endVertex();
	               bufferbuilder.vertex(10.0D, 10.0D, 10.0D).uv(1.0F, 1.0F).color(255, 255, 255, l).endVertex();
	               bufferbuilder.vertex(1.0D, -1.0D, 1.0D).uv(1.0F, 0.0F).color(255, 255, 255, l).endVertex();
	            

	            tesselator.end();
	         

	         posestack.popPose();
	         RenderSystem.applyModelViewMatrix();
	         RenderSystem.colorMask(true, true, true, false);
	      }

	      RenderSystem.colorMask(true, true, true, true);
	      RenderSystem.restoreProjectionMatrix();
	      posestack.popPose();
	      RenderSystem.applyModelViewMatrix();
	      RenderSystem.depthMask(true);
	      RenderSystem.enableCull();
	      RenderSystem.enableDepthTest();
	}
}
