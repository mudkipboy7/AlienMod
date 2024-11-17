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
import com.mudkipboy7.alien.world.worldgen.dimension.sky.astroobject.AstronomicalObject;
import com.mudkipboy7.alien.world.worldgen.dimension.sky.astroobject.IPhasingAstroObject;

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
public class AlienDimSkyRenderer {
	// The sun texture.
	protected static final ResourceLocation SUN_TEXTURE = AlienMod.location("textures/environment/alien_sun.png");

	// The texture of The Sun's glare
	private static final ResourceLocation SUN_GLARE_TEXTURE = AlienMod
			.location("textures/environment/alien_sun_glare.png");

	// The texture for the stationary planet in the sky.
	private static final ResourceLocation JOVIAN_PLANET_TEXTURE = AlienMod
			.location("textures/environment/jovian_planet.png");

	// The texture for the moon
	private static final ResourceLocation SMALL_MOON_TEXURE = AlienMod.location("textures/environment/small_moon.png");

	// The texture for the phases shaders
	private static final ResourceLocation PHASES_0 = AlienMod.location("textures/environment/phases_0.png");
	private static final ResourceLocation PHASES_1 = AlienMod.location("textures/environment/phases_1.png");
	// Star buffer
	private static VertexBuffer starBuffer;

	public AlienDimSkyRenderer() {
		this.createStars();
	}

	public static boolean renderSky(ClientLevel level, int ticks, float partialTick, PoseStack poseStack, Camera camera,
			Matrix4f projectionMatrix, boolean isFoggy, Runnable setupFog) {

		// The sun's current location
		double sunLocation = getAlienDimSky().alienSun.getSkyLocation();

		// The amount that it is eclipsing.
		float eclipsyness = getAlienDimSky().getEclipsyness();
		// Don't know what this was for commenting it out just in case
		// if (eclipsyness == 1) {
		// System.out.println("");
		// getAlienDimSky().getEclipsyness();
		// }

		/*
		 * This determines the brightness of the stars. With baseStarBrightness set to
		 * 0.15 the stars will have a brightness of 0.65 during night time and during
		 * eclipses, and a brightness of 0.15F during daytime.
		 */
		float starBrightness = getAlienDimSky().stars.getBrightness(eclipsyness, sunLocation);

		// System.out.println(jovianBrightness);
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
		skyRed = skyRed * eclipsyness;
		skyGreen = skyGreen * eclipsyness;
		skyBlue = skyBlue * eclipsyness;
		RenderSystem.setShaderColor(skyRed, skyGreen, skyBlue, 1.0F);

		ShaderInstance shaderinstance = RenderSystem.getShader();
		levelRenderer.skyBuffer.bind();
		levelRenderer.skyBuffer.drawWithShader(poseStack.last().pose(), projectionMatrix, shaderinstance);
		VertexBuffer.unbind();
		RenderSystem.enableBlend();

		/*
		 * Draws sunset
		 */
		float[] afloat = level.effects().getSunriseColor(level.getTimeOfDay(partialTick), partialTick);
		if (afloat != null) {
			RenderSystem.setShader(GameRenderer::getPositionColorShader);
			RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
			poseStack.pushPose();
			poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
			float f3 = Mth.sin(level.getSunAngle(partialTick)) < 0.0F ? 180.0F : 0.0F;
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

		/*
		 * Everything here is only drawn if it isn't raining. All of the celestial
		 * bodies are drawn here.
		 */

		if (!level.isRaining()) {

			/*
			 * Draws The Sun
			 */
			poseStack.pushPose();
			float sunRedness = 1.0F;
			float sunGreenness = 1.0F;
			float sunBlueness = 1.0F;
			float sunBrightness = Math.max(0.0F, eclipsyness);

			poseStack.mulPose(Axis.YP.rotationDegrees(-90.0F));

			poseStack.mulPose(Axis.XP.rotationDegrees((float) getAlienDimSky().alienSun.getSkyLocation()));
			Matrix4f matrix4f1 = poseStack.last().pose();
			RenderSystem.setShader(GameRenderer::getPositionTexShader);
			// Glare
			float celestialSize = getAlienDimSky().alienSun.getGlareSize();
			if (eclipsyness > 0) {
				RenderSystem.setShaderColor(sunRedness, sunGreenness, sunBlueness, sunBrightness);
				RenderSystem.setShaderTexture(0, SUN_GLARE_TEXTURE);
				bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
				bufferbuilder.vertex(matrix4f1, -celestialSize, 100.0F, -celestialSize).uv(0.0F, 0.0F).endVertex();
				bufferbuilder.vertex(matrix4f1, celestialSize, 100.0F, -celestialSize).uv(1.0F, 0.0F).endVertex();
				bufferbuilder.vertex(matrix4f1, celestialSize, 100.0F, celestialSize).uv(1.0F, 1.0F).endVertex();
				bufferbuilder.vertex(matrix4f1, -celestialSize, 100.0F, celestialSize).uv(0.0F, 1.0F).endVertex();
				BufferUploader.drawWithShader(bufferbuilder.end());
			}
			// Sun
			sunBrightness = getAlienDimSky().alienSun.getBrightness(eclipsyness, 0);
			celestialSize = getAlienDimSky().alienSun.getVisualSize();
			// System.out.println(celestialSize);
			RenderSystem.setShaderColor(sunRedness, sunGreenness, sunBlueness, sunBrightness);
			RenderSystem.setShaderTexture(0, SUN_TEXTURE);
			bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
			bufferbuilder.vertex(matrix4f1, -celestialSize, 100.0F, -celestialSize).uv(0.0F, 0.0F).endVertex();
			bufferbuilder.vertex(matrix4f1, celestialSize, 100.0F, -celestialSize).uv(1.0F, 0.0F).endVertex();
			bufferbuilder.vertex(matrix4f1, celestialSize, 100.0F, celestialSize).uv(1.0F, 1.0F).endVertex();
			bufferbuilder.vertex(matrix4f1, -celestialSize, 100.0F, celestialSize).uv(0.0F, 1.0F).endVertex();
			BufferUploader.drawWithShader(bufferbuilder.end());

			/*
			 * Draws the stars
			 */

			RenderSystem.setShaderColor(starBrightness, starBrightness, starBrightness, starBrightness);
			FogRenderer.setupNoFog();
			starBuffer.bind();
			starBuffer.drawWithShader(poseStack.last().pose(), projectionMatrix, GameRenderer.getPositionShader());
			VertexBuffer.unbind();
			setupFog.run();
			poseStack.popPose();
			// Draws the moon planet
			drawJovian(partialTick, poseStack, sunLocation, matrix4f1, getAlienDimSky().smallMoon, 1, bufferbuilder,
					skyRed, skyGreen, skyBlue, SMALL_MOON_TEXURE);
			// Draws the jovian planet
			drawJovian(partialTick, poseStack, sunLocation, matrix4f1, getAlienDimSky().jovianPlanet, eclipsyness,
					bufferbuilder, skyRed, skyGreen, skyBlue, JOVIAN_PLANET_TEXTURE);

		}

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

	private void createStars() {
		Tesselator tesselator = Tesselator.getInstance();
		BufferBuilder bufferbuilder = tesselator.getBuilder();
		RenderSystem.setShader(GameRenderer::getPositionShader);

		if (starBuffer != null) {
			starBuffer.close();
		}

		starBuffer = new VertexBuffer(VertexBuffer.Usage.STATIC);
		BufferBuilder.RenderedBuffer bufferbuilder$renderedbuffer = getAlienDimSky().stars.drawStars(bufferbuilder);
		starBuffer.bind();
		starBuffer.upload(bufferbuilder$renderedbuffer);
		VertexBuffer.unbind();
	}

	/**
	 * Determines the brightness of a celestial body based on the normal brightness
	 * of the overworld's stars, while taking into account eclipses. If
	 * brightnessModifier is set to 0.0F, this will return 0.5F during night time
	 * and full eclipses, and 0.0F in broad daylight. If it is set to a negative
	 * number it will get minus that much from the current brightness, but it will
	 * never return a negative number. So passing a number less then -0.5F will
	 * always return 0.0F. It also checks if the brightness is higher then the max
	 * vanilla star brightness added to the brightness modifier, if it is it
	 * decreases it to that;
	 */

	protected static float getStarBrightness(float brightnessModifier, ClientLevel clientLevel, float partialTick) {
		float eclipsyness = getAlienDimSky().getEclipsyness();
		/*
		 * Does math to determine what the brightness should be. It checks that it isn't
		 * negative.
		 */
		float brightness = Math.min(brightnessModifier + 0.5F,
				brightnessModifier + clientLevel.getStarBrightness(partialTick) + ((1.0F - eclipsyness) / 2.0F));
		// Makes sure it's not to high if so returns the max value.
		return Math.max(0.0F, brightness);
	}

	/// @SuppressWarnings("unused")
	private static <T extends AstronomicalObject & IPhasingAstroObject> void drawJovian(float partialTick,
			PoseStack poseStack, double sunLocation, Matrix4f matrix4f1, T jovianPlanet, float eclipsyness,
			BufferBuilder bufferbuilder, float skyRed, float skyGreen, float skyBlue, ResourceLocation texture) {
		/*
		 * Draws the stationary sky object.
		 */
		// The current phase of the sky object
		int jovianCurrentPhase = jovianPlanet.getCurrentPhase(sunLocation);
		// Determines stuff related to the texture.
		float textureThing = 1.0F / jovianPlanet.getNumberOfPhases();

		// I think this specifies where the image stops drawing the image on the x-axis
		float xStop = textureThing * (jovianCurrentPhase + 1);
		// I think this specifies where the image stops drawing the image on the y-axis
		float yStop = 1.0F;
		// I think this specifies where the image starts drawing the image on the x-axis
		float xStart = textureThing * jovianCurrentPhase;
		// I think this specifies where the image starts drawing the image on the y-axis
		float yStart = 0.0F;

		float celestialSize = jovianPlanet.getVisualSize();

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
		poseStack.mulPose(Axis.YP.rotationDegrees(0.0F));
		poseStack.mulPose(Axis.ZP.rotationDegrees((float) jovianPlanet.getSkyLocation()));

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
		float jovianBrightness = jovianPlanet.getBrightness(eclipsyness, sunLocation);
		RenderSystem.setShaderColor(jovianBrightness, jovianBrightness, jovianBrightness, jovianBrightness);
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderTexture(0, texture);
		RenderSystem.setShaderTexture(1, SUN_TEXTURE);
		bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
		bufferbuilder.vertex(matrix4f1, -celestialSize, 100.0F, -celestialSize).uv(0.0F, 0.0F).endVertex();
		bufferbuilder.vertex(matrix4f1, celestialSize, 100.0F, -celestialSize).uv(1.0F, 0.0F).endVertex();
		bufferbuilder.vertex(matrix4f1, celestialSize, 100.0F, celestialSize).uv(1.0F, 1.0F).endVertex();
		bufferbuilder.vertex(matrix4f1, -celestialSize, 100.0F, celestialSize).uv(0.0F, 1.0F).endVertex();
		BufferUploader.drawWithShader(bufferbuilder.end());

		/*
		 * Draws the shaders for the phases
		 */
		if (true) {

			/*
			 * Changes the blending func to be subtractive
			 */
			RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.ZERO, GlStateManager.DestFactor.SRC_ALPHA,
					GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);

			// Draws the first shader
			shaderMod = 0.5F;
			RenderSystem.enableBlend();
			RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, shaderMod);
			RenderSystem.setShader(GameRenderer::getPositionTexShader);
			RenderSystem.setShaderTexture(0, PHASES_1);
			bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
			bufferbuilder.vertex(matrix4f1, -celestialSize, 100.0F, -celestialSize).uv(xStart, yStart).endVertex();
			bufferbuilder.vertex(matrix4f1, celestialSize, 100.0F, -celestialSize).uv(xStop, yStart).endVertex();
			bufferbuilder.vertex(matrix4f1, celestialSize, 100.0F, celestialSize).uv(xStop, yStop).endVertex();
			bufferbuilder.vertex(matrix4f1, -celestialSize, 100.0F, celestialSize).uv(xStart, yStop).endVertex();
			BufferUploader.drawWithShader(bufferbuilder.end());

			// Draws the second shader

			RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, shaderMod / 2.0F);
			RenderSystem.setShaderTexture(0, PHASES_0);
			bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
			bufferbuilder.vertex(matrix4f1, -celestialSize, 100.0F, -celestialSize).uv(xStart, yStart).endVertex();
			bufferbuilder.vertex(matrix4f1, celestialSize, 100.0F, -celestialSize).uv(xStop, yStart).endVertex();
			bufferbuilder.vertex(matrix4f1, celestialSize, 100.0F, celestialSize).uv(xStop, yStop).endVertex();
			bufferbuilder.vertex(matrix4f1, -celestialSize, 100.0F, celestialSize).uv(xStart, yStop).endVertex();
			BufferUploader.drawWithShader(bufferbuilder.end());
		}
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
		RenderSystem.setShaderColor(skyRed * shaderMod, skyGreen * shaderMod, skyBlue * shaderMod, eclipsyness);
		bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION);
		bufferbuilder.vertex(matrix4f1, -celestialSize, 100.0F, -celestialSize).endVertex();
		bufferbuilder.vertex(matrix4f1, celestialSize, 100.0F, -celestialSize).endVertex();
		bufferbuilder.vertex(matrix4f1, celestialSize, 100.0F, celestialSize).endVertex();
		bufferbuilder.vertex(matrix4f1, -celestialSize, 100.0F, celestialSize).endVertex();
		BufferUploader.drawWithShader(bufferbuilder.end());
		poseStack.popPose();
	}
}
