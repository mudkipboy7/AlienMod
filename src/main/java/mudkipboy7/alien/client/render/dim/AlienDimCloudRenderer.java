package mudkipboy7.alien.client.render.dim;

import static mudkipboy7.alien.world.worldgen.dimension.sky.AlienDimSky.alienDimSky;

import javax.annotation.Nullable;

import org.joml.Matrix4f;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexBuffer;
import com.mojang.blaze3d.vertex.VertexFormat;

import mudkipboy7.alien.AlienMod;
import mudkipboy7.alien.world.worldgen.dimension.sky.AlienDimSky;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;

public class AlienDimCloudRenderer extends AlienDimSky {

	private static final ResourceLocation CLOUDS_LOCATION = AlienMod.location("textures/environment/alien_clouds.png");

	private static int prevCloudX = Integer.MIN_VALUE;
	private static int prevCloudY = Integer.MIN_VALUE;
	private static int prevCloudZ = Integer.MIN_VALUE;
	private static Vec3 prevCloudColor = Vec3.ZERO;
	private static boolean generateClouds = true;
	@Nullable
	private static VertexBuffer cloudBuffer;

	public static boolean renderClouds(ClientLevel level, int ticks, float partialTick, PoseStack poseStack,
			double camX, double camY, double camZ, Matrix4f projectionMatrix) {
		// The amount that it is eclipsing.
		float eclipsyness = alienDimSky.getEclipsyness(alienDimSky.alienSun.getLocation(),
				alienDimSky.jovianPlanet.getLocation());
		drawCloudsAtHeight(eclipsyness, 110, level, ticks * 3, partialTick, poseStack, camX, camY, camZ,
				projectionMatrix);
		drawCloudsAtHeight(eclipsyness, 120, level, ticks * 2, partialTick, poseStack, camX, camY, camZ,
				projectionMatrix);
		drawCloudsAtHeight(eclipsyness, 140, level, ticks, partialTick, poseStack, camX, camY, camZ, projectionMatrix);
		drawCloudsAtHeight(eclipsyness, 160, level, ticks / 2, partialTick, poseStack, camX, camY, camZ,
				projectionMatrix);
		drawCloudsAtHeight(eclipsyness, 190, level, ticks / 3, partialTick, poseStack, camX, camY, camZ,
				projectionMatrix);
		return true;
	}

	private static void drawCloudsAtHeight(float eclipsyness, int cloudHeight, ClientLevel level, int ticks,
			float partialTick, PoseStack poseStack, double camX, double camY, double camZ, Matrix4f projectionMatrix) {
		if (!Float.isNaN(cloudHeight)) {

			float stuffMul = (cloudHeight / 100);

			RenderSystem.disableCull();
			RenderSystem.enableBlend();
			RenderSystem.enableDepthTest();
			RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA,
					GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE,
					GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
			RenderSystem.depthMask(true);
			double d1 = (double) (((float) ticks + partialTick) * 0.03F);
			double d2 = (camX + d1) / 12.0D;
			double d3 = (double) (cloudHeight - (float) camY + 0.33F);
			double d4 = camZ / 12.0D + (double) 0.33F;
			d2 -= (double) (Mth.floor(d2 / 2048.0D) * 2048);
			d4 -= (double) (Mth.floor(d4 / 2048.0D) * 2048);
			float f3 = (float) (d2 - (double) Mth.floor(d2));
			float f4 = (float) (d3 / 4.0D - (double) Mth.floor(d3 / 4.0D)) * 4.0F;
			float f5 = (float) (d4 - (double) Mth.floor(d4));
			Vec3 vec3 = level.getCloudColor(partialTick);
			int i = (int) Math.floor(d2);
			int j = (int) Math.floor(d3 / 4.0D);
			int k = (int) Math.floor(d4);

			if (i != prevCloudX || j != prevCloudY || k != prevCloudZ || prevCloudColor.distanceToSqr(vec3) > 2.0E-4D) {
				prevCloudX = i;
				prevCloudY = j;
				prevCloudZ = k;
				prevCloudColor = vec3; //
				generateClouds = true;
			}

			if (generateClouds) {
				generateClouds = true;
				BufferBuilder bufferbuilder = Tesselator.getInstance().getBuilder();
				if (cloudBuffer != null) {
					cloudBuffer.close();
				}

				cloudBuffer = new VertexBuffer(VertexBuffer.Usage.STATIC);
				BufferBuilder.RenderedBuffer bufferbuilder$renderedbuffer = buildClouds(eclipsyness, bufferbuilder, d2,
						d3, d4, vec3);
				cloudBuffer.bind();
				cloudBuffer.upload(bufferbuilder$renderedbuffer);
				VertexBuffer.unbind();
			}

			RenderSystem.setShader(GameRenderer::getPositionTexColorNormalShader);
			RenderSystem.setShaderTexture(0, CLOUDS_LOCATION);
			FogRenderer.levelFogColor();
			poseStack.pushPose();
			poseStack.scale(12.0F * stuffMul, 1.0F, 12.0F);
			poseStack.translate(-f3, f4, -f5);
			if (cloudBuffer != null) {
				cloudBuffer.bind();
				int l = 1;

				for (int i1 = l; i1 < 2; ++i1) {
					if (i1 == 0) {
						RenderSystem.colorMask(false, false, false, false);
					} else {
						RenderSystem.colorMask(true, true, true, true);
					}

					ShaderInstance shaderinstance = RenderSystem.getShader();
					cloudBuffer.drawWithShader(poseStack.last().pose(), projectionMatrix, shaderinstance);
				}

				VertexBuffer.unbind();
			}

			poseStack.popPose();
			RenderSystem.enableCull();
			RenderSystem.disableBlend();
			RenderSystem.defaultBlendFunc();
		}
	}

	private static BufferBuilder.RenderedBuffer buildClouds(Float eclipsyness, BufferBuilder pBuilder, double pX,
			double pY, double pZ, Vec3 pCloudColor) {
		float f3 = (float) Mth.floor(pX) * 0.00390625F;
		float f4 = (float) Mth.floor(pZ) * 0.00390625F;
		float cloudColorRed = getCloudEclipseColor(pCloudColor.x, eclipsyness);
		float cloudColorGreen = getCloudEclipseColor(pCloudColor.y, eclipsyness);
		float cloudColorBlue = getCloudEclipseColor(pCloudColor.z, eclipsyness);
		RenderSystem.setShader(GameRenderer::getPositionTexColorNormalShader);
		pBuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX_COLOR_NORMAL);
		float f17 = (float) Math.floor(pY / 4.0D) * 4.0F;
		// System.out.println(f5);
		float trans = 0.5F;
		for (int l1 = -32; l1 < 32; l1 += 32) {
			for (int i2 = -32; i2 < 32; i2 += 32) {
				pBuilder.vertex((double) (l1 + 0), (double) f17, (double) (i2 + 32))
						.uv((float) (l1 + 0) * 0.00390625F + f3, (float) (i2 + 32) * 0.00390625F + f4)
						.color(cloudColorRed, cloudColorGreen, cloudColorBlue, trans).normal(0.0F, -1.0F, 0.0F)
						.endVertex();
				pBuilder.vertex((double) (l1 + 32), (double) f17, (double) (i2 + 32))
						.uv((float) (l1 + 32) * 0.00390625F + f3, (float) (i2 + 32) * 0.00390625F + f4)
						.color(cloudColorRed, cloudColorGreen, cloudColorBlue, trans).normal(0.0F, -1.0F, 0.0F)
						.endVertex();
				pBuilder.vertex((double) (l1 + 32), (double) f17, (double) (i2 + 0))
						.uv((float) (l1 + 32) * 0.00390625F + f3, (float) (i2 + 0) * 0.00390625F + f4)
						.color(cloudColorRed, cloudColorGreen, cloudColorBlue, trans).normal(0.0F, -1.0F, 0.0F)
						.endVertex();
				pBuilder.vertex((double) (l1 + 0), (double) f17, (double) (i2 + 0))
						.uv((float) (l1 + 0) * 0.00390625F + f3, (float) (i2 + 0) * 0.00390625F + f4)
						.color(cloudColorRed, cloudColorGreen, cloudColorBlue, trans).normal(0.0F, -1.0F, 0.0F)
						.endVertex();
			}
		}

		return pBuilder.end();
	}

	private static float getCloudEclipseColor(double color, float eclipsyness) {
		float baseColor = ((float) color) * eclipsyness;

		return Math.max(baseColor * 0.8F, 0.1F);
	}

}
