package com.mudkipboy7.alien.client.render.dim;

import static com.mudkipboy7.alien.AlienMod.getAlienDimSky;

import java.security.spec.ECField;

import org.joml.Math;
import org.joml.Matrix4f;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.BufferUploader;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexBuffer;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.math.Axis;
import com.mudkipboy7.alien.AlienMod;
import com.mudkipboy7.alien.sky.AlienDimSky;
import com.mudkipboy7.alien.sky.AstronomicalFunctions;
import com.mudkipboy7.alien.sky.astroobject.AlienPlanetInJovianSkyAstroObject;
import com.mudkipboy7.alien.sky.astroobject.AstronomicalObject;
import com.mudkipboy7.alien.sky.astroobject.IPhasingAstroObject;

import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(value = Dist.CLIENT)
public class JovianDimSkyRenderer {
	// The texture for the stationary planet in the sky.
	private static final ResourceLocation ALIEN_PLANET_TEXTURE = AlienMod
			.location("textures/environment/alien_planet.png");
	private static final ResourceLocation SMALL_MOON_TEXURE = AlienMod.location("textures/environment/small_moon.png");

	// private static AlienPlanetInJovianSkyAstroObject alienPlanetInJovianSky = new
	// AlienPlanetInJovianSkyAstroObject(
	// 0.4F, 18.0F);

	public static boolean renderSky(ClientLevel level, int ticks, float partialTick, PoseStack poseStack, Camera camera,
			Matrix4f projectionMatrix, boolean isFoggy, Runnable setupFog) {

		// System.out.println(jovianBrightness);
		// The sun's current location
		/*
		 * Draws sky
		 */
		@SuppressWarnings("resource")
		LevelRenderer levelRenderer = Minecraft.getInstance().levelRenderer;
		setupFog.run();
		Vec3 vec3 = level.getSkyColor(camera.getPosition(), partialTick);
		float skyRed = (float) vec3.x;
		float skyGreen = (float) vec3.y;
		float skyBlue = (float) vec3.z;
		FogRenderer.levelFogColor();
		BufferBuilder bufferbuilder = Tesselator.getInstance().getBuilder();
		RenderSystem.depthMask(false);

		// Changes sky color for eclipses.
		skyRed = AlienDimSky.redMul * .2F;
		skyGreen = AlienDimSky.greenMul * .2F;
		skyBlue = AlienDimSky.blueMul * .2F;
		RenderSystem.setShaderColor(skyRed, skyGreen, skyBlue, 1.0F);

		ShaderInstance shaderinstance = RenderSystem.getShader();
		levelRenderer.skyBuffer.bind();
		levelRenderer.skyBuffer.drawWithShader(poseStack.last().pose(), projectionMatrix, shaderinstance);
		VertexBuffer.unbind();
		RenderSystem.enableBlend();

		/*
		 * Draws sunset/sunrise
		 */
		// float[] afloat =
		// level.effects().getSunriseColor(level.getTimeOfDay(partialTick),
		// partialTick);
		float[] afloat = level.effects().getSunriseColor(getAlienDimSky().alienSun.getSkyLocationTurns(), partialTick);
		if (afloat != null) {
			RenderSystem.setShader(GameRenderer::getPositionColorShader);
			RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
			poseStack.pushPose();
			poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
			// float f3 = Mth.sin(level.getSunAngle(partialTick)) < 0.0F ? 180.0F : 0.0F;
			float f3 = Mth.sin((float) getAlienDimSky().alienSun.getSkyLocation()) < 0.0F ? 180.0F : 0.0F;
			poseStack.mulPose(Axis.ZP.rotationDegrees(f3));
			poseStack.mulPose(Axis.ZP.rotationDegrees(90.0F));
			float f4 = afloat[0];
			float f5 = afloat[1];
			float f6 = afloat[2];
			Matrix4f matrix4f = poseStack.last().pose();
			bufferbuilder.begin(VertexFormat.Mode.TRIANGLE_FAN, DefaultVertexFormat.POSITION_COLOR);
			bufferbuilder.vertex(matrix4f, 0.0F, 100.0F, 0.0F).color(f4, f5, f6, afloat[3]).endVertex();

			@SuppressWarnings("unused")
			int i = 16;

			for (int j = 0; j <= 16; ++j) {
				float f7 = (float) j * ((float) Math.PI * 2F) / 16.0F;
				float f8 = Mth.sin(f7);
				float f9 = Mth.cos(f7);
				bufferbuilder.vertex(matrix4f, f8 * 120.0F, f9 * 120.0F, -f9 * 40.0F * afloat[3])
						.color(afloat[0], afloat[1], afloat[2], 0.0F).endVertex();
			}

			BufferUploader.drawWithShader(bufferbuilder.end());
			poseStack.popPose();
		}

		RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE,
				GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);

		// Draws the alien planet
		drawMoon(partialTick, poseStack, projectionMatrix, bufferbuilder, 70.0F, -10F, 15.0F, skyRed, skyGreen, skyBlue,
				ALIEN_PLANET_TEXTURE);
		// Draws the stationary moon planet
		drawMoon(partialTick, poseStack, projectionMatrix, bufferbuilder, -50.0F, -5F, 3.0F, skyRed, skyGreen, skyBlue,
				SMALL_MOON_TEXURE);

		/*
		 * I don't exactly know what this does, I've just left it because I don't want
		 * to screw things up. Ok now I think it had something to do with fog
		 */
		poseStack.pushPose();
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.disableBlend();
		RenderSystem.defaultBlendFunc();
		poseStack.popPose();

		/*
		 * I think this has something to do with culling or something.
		 */
		RenderSystem.setShaderColor(0.0F, 0.0F, 0.0F, 1.0F);
		double d0 = camera.getEntity().getEyePosition(partialTick).y - level.getLevelData().getHorizonHeight(level);
		if (d0 < 0.0D) {

		}

		/*
		 * Sets the shader for things like blocks and entities.
		 */
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.depthMask(true);

		// Returns true
		return true;

	}

	/// @SuppressWarnings("unused")
	static void drawMoon(float partialTick, PoseStack poseStack, Matrix4f matrix4f1, BufferBuilder bufferbuilder,
			float locationZ, float locationY, float celestialSize, float skyRed, float skyGreen, float skyBlue,
			ResourceLocation texture) {
		/*
		 * Draws the stationary sky object.
		 */
		float brightness = 0.2F;
		// Starts up it.
		poseStack.pushPose();
		matrix4f1 = poseStack.last().pose();

		/*
		 * This stuff thats commented out would've changed it's location depending on
		 * the player's location
		 */
		// double boarderSize = level.getWorldBorder().getSize()/2.0D;//29999984.0D;
		// float position = (float) ((camera.getPosition().x() * 50.0D) / boarderSize);
		// poseStack.mulPose(Axis.YP.rotationDegrees(0.0F));
		// poseStack.mulPose(Axis.ZN.rotationDegrees(position));

		// Sets it's location
		poseStack.mulPose(Axis.YP.rotationDegrees(locationY));
		poseStack.mulPose(Axis.ZP.rotationDegrees(locationZ));

		/*
		 * The main part that draws the actual texture of the sky object. The blending
		 * must be disabled to prevent it looking see through at night.
		 */
		/*
		 * NativeImage bill = new NativeImage(16, 16, true); AbstractTexture fe =
		 * Minecraft.getInstance().getTextureManager().getTexture(JOVIAN_PLANET_TEXTURE)
		 * ; DynamicTexture b = new DynamicTexture(bill);
		 * Minecraft.getInstance().getTextureManager().register(PHASES_0, fe);
		 */
		Float shaderMod = 0.8F;
		RenderSystem.disableBlend();
		RenderSystem.setShaderColor(brightness, brightness, brightness, brightness);
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderTexture(0, texture);
		bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
		bufferbuilder.vertex(matrix4f1, -celestialSize, 100.0F, -celestialSize).uv(0.0F, 0.0F).endVertex();
		bufferbuilder.vertex(matrix4f1, celestialSize, 100.0F, -celestialSize).uv(1.0F, 0.0F).endVertex();
		bufferbuilder.vertex(matrix4f1, celestialSize, 100.0F, celestialSize).uv(1.0F, 1.0F).endVertex();
		bufferbuilder.vertex(matrix4f1, -celestialSize, 100.0F, celestialSize).uv(0.0F, 1.0F).endVertex();
		BufferUploader.drawWithShader(bufferbuilder.end());

		/*
		 * Draws the shaders for the phases
		 */

		// Changes the blending func back to how it was before
		RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE,
				GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
		/*
		 * Draws a box used to make the planet look like it's being seen behind the sky.
		 */
		shaderMod = 0.8F;
		// RenderSystem.bin
		RenderSystem.enableBlend();
		RenderSystem.setShader(GameRenderer::getPositionShader);
		RenderSystem.setShaderColor(skyRed * shaderMod, skyGreen * shaderMod, skyBlue * shaderMod, 1.0F);
		bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION);
		bufferbuilder.vertex(matrix4f1, -celestialSize, 100.0F, -celestialSize).endVertex();
		bufferbuilder.vertex(matrix4f1, celestialSize, 100.0F, -celestialSize).endVertex();
		bufferbuilder.vertex(matrix4f1, celestialSize, 100.0F, celestialSize).endVertex();
		bufferbuilder.vertex(matrix4f1, -celestialSize, 100.0F, celestialSize).endVertex();
		BufferUploader.drawWithShader(bufferbuilder.end());
		poseStack.popPose();
	}

}
