package com.mudkipboy7.alien.client.render.dim;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.mudkipboy7.alien.AlienMod;
import com.mudkipboy7.alien.sky.AlienDimSky;

import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.ParticleStatus;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(value = Dist.CLIENT)
public class JovianDimSpecialEffects extends DimensionSpecialEffects {

	// private int numberOfPhases = AlienDimSky.numberOfPhases;
	private int rainSoundTime = 0;
	private static final ResourceLocation RAIN_LOCATION = new ResourceLocation("textures/environment/rain.png");

	private final float[] rainSizeX = new float[1024];
	private final float[] rainSizeZ = new float[1024];

	public JovianDimSpecialEffects(float cloudHeight, boolean hasGround, DimensionSpecialEffects.SkyType skytype,
			boolean forceBrightLightmap, boolean constantAmbientLight) {
		super(cloudHeight, hasGround, skytype, forceBrightLightmap, constantAmbientLight);

	}

	@Override
	public Vec3 getBrightnessDependentFogColor(Vec3 biomeFogColor, float daylight) {
		return biomeFogColor;
	}

	@Override
	public boolean isFoggyAt(int p_108874_, int p_108875_) {
		return false;
	}

	@Override
	public boolean renderSky(ClientLevel level, int ticks, float partialTick, PoseStack poseStack, Camera camera,
			Matrix4f projectionMatrix, boolean isFoggy, Runnable setupFog) {
		return JovianDimSkyRenderer.renderSky(level, ticks, partialTick, poseStack, camera, projectionMatrix, isFoggy,
				setupFog);
	}

	@Override
	public boolean renderClouds(ClientLevel level, int ticks, float partialTick, PoseStack poseStack, double camX,
			double camY, double camZ, Matrix4f projectionMatrix) {

		return JovianDimCloudsRenderer.renderClouds(level, ticks, partialTick, poseStack, camX, camY, camZ,
				projectionMatrix);
	}

	/*
	 * Changes lighting of blocks and stuff for eclipses. Okay so this didn't work
	 * I think because I set there to be no skylight and I set ambient light to
	 * always be 1, gotta set it to be just fixxed time and not those
	 */
	@Override
	public void adjustLightmapColors(ClientLevel level, float partialTicks, float skyDarken, float blockLightRedFlicker,
			float skyLight, int pixelX, int pixelY, Vector3f colors) {
		Minecraft minecraft = Minecraft.getInstance();
		float brightnessMultiplier = AlienDimSky.alienDimVisualBrightMul;

		/*
		 * The math.max thing fixes a glitch where eclipses that happen at night would
		 * cause problems. Sets the hard minimum value of skyDarken to be 0.1F.
		 */
		skyDarken = Math.max(0.2F * brightnessMultiplier, skyDarken * brightnessMultiplier);
		// System.out.println(skyDarken);
		float f1;
		if (level.getSkyFlashTime() > 0) {
			f1 = 1.0F;
		} else {
			f1 = skyDarken * 0.95F + 0.05F;
		}

		Vector3f vector3f = (new Vector3f(skyDarken, skyDarken, 1.0F)).lerp(new Vector3f(1.0F, 1.0F, 1.0F), 0.35F);
		float f7 = blockLightRedFlicker + 1.5F;
		Vector3f vector3f1 = new Vector3f();

		float brightY = getBrightness(level.dimensionType(), pixelY) * f1;
		float brightX = getBrightness(level.dimensionType(), pixelX) * f7;

		float f10 = brightX * ((brightX * 0.6F + 0.4F) * 0.6F + 0.4F);
		float f11 = brightX * (brightX * brightX * 0.6F + 0.4F);
		vector3f1.set(brightX, f10, f11);
		boolean flag = level.effects().forceBrightLightmap();
		if (flag) {
			vector3f1.lerp(new Vector3f(0.99F, 1.12F, 1.0F), 0.25F);

			// Does what clampColor() did.
			vector3f1.set(Mth.clamp(vector3f1.x, 0.0F, 1.0F), Mth.clamp(vector3f1.y, 0.0F, 1.0F),
					Mth.clamp(vector3f1.z, 0.0F, 1.0F));
		} else {
			Vector3f vector3f2 = (new Vector3f(vector3f)).mul(brightY);
			vector3f1.add(vector3f2);
			vector3f1.lerp(new Vector3f(0.75F, 0.75F, 0.75F), 0.04F);
			if (minecraft.gameRenderer.getDarkenWorldAmount(partialTicks) > 0.0F) {
				Vector3f vector3f3 = (new Vector3f(vector3f1)).mul(0.7F, 0.6F, 0.6F);
				vector3f1.lerp(vector3f3, minecraft.gameRenderer.getDarkenWorldAmount(partialTicks));
			}
		}
		// Makes sure none of the mul things are 0.
		vector3f1.set(vector3f1.x * AlienDimSky.redMul, vector3f1.y * AlienDimSky.greenMul,
				vector3f1.z * AlienDimSky.blueMul);
		colors.set(vector3f1);
	}

	private static float getBrightness(DimensionType p_234317_, int p_234318_) {
		float dim = (float) p_234318_ / 15.0F;
		float o = dim / (4.0F - 3.0F * dim);
		return Mth.lerp(p_234317_.ambientLight(), o, 1.0F);
	}

	private static AlienDimSky getSky() {
		return AlienMod.getAlienDimSky();
	}

	@Override
	public boolean renderSnowAndRain(ClientLevel level, int ticks, float partialTick, LightTexture lightTexture,
			double camX, double camY, double camZ) {
		level.setRainLevel(0.4F);
		//level.setThunderLevel(100.0F);
		return false;
	}

	@Override
	public boolean tickRain(ClientLevel level, int ticks, Camera camera) {
		level.setRainLevel(0.4F);
		//level.setThunderLevel(100.0F);
		return false;
	}

}