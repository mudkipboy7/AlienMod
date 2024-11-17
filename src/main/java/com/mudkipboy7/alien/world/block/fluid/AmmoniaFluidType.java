package com.mudkipboy7.alien.world.block.fluid;

import java.util.function.Consumer;

import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

import com.mojang.blaze3d.shaders.FogShape;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mudkipboy7.alien.AlienMod;
import com.mudkipboy7.alien.world.WorldFuncs;

import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.common.SoundActions;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidType;

public class AmmoniaFluidType extends FluidType {
	/*
	 * Textures
	 */
	public static final ResourceLocation AMMONIA_LIQUID_TEXTURE = AlienMod.location("block/fluid/ammonia_liquid");
	private static final ResourceLocation AMMONIA_LIQUID_FLOWING_TEXTURE = AlienMod
			.location("block/fluid/ammonia_liquid_flowing");
	private static final ResourceLocation UNDERWATER_LOCATION = new ResourceLocation("textures/misc/underwater.png");
	private static final ResourceLocation WATER_OVERLAY = new ResourceLocation("block/water_overlay");

	public AmmoniaFluidType() {
		super(AmmoniaFluidType.Properties.create()//
				.viscosity(500)//
				.density(1000)//
				.canConvertToSource(true)//
				.canPushEntity(true)//
				.canExtinguish(true)//
				.canDrown(true)//
				.fallDistanceModifier(0.5F)// Makes it not fully nullify fall damage
				.sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)//
				.sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)//
				.sound(SoundActions.FLUID_VAPORIZE, SoundEvents.FIRE_EXTINGUISH)//
		);
	}

	@Override
	public boolean isVaporizedOnPlacement(Level level, BlockPos pos, FluidStack stack) {
		Holder<Biome> biome = level.getBiome(pos);
		// System.out.println("gewgwge");
		if (!WorldFuncs.inUltracoldOrEndBiome(level, pos)) {
			return true;
		}
		return super.isVaporizedOnPlacement(level, pos, stack);
	}

	@Override
	public void onVaporize(@Nullable Player player, Level level, BlockPos pos, FluidStack stack) {
		SoundEvent sound = this.getSound(player, level, pos, SoundActions.FLUID_VAPORIZE);
		level.playSound(player, pos, sound != null ? sound : SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 0.5F,
				2.6F + (level.random.nextFloat() - level.random.nextFloat()) * 0.8F);

		for (int l = 0; l < 8; ++l)
			level.addAlwaysVisibleParticle(ParticleTypes.LARGE_SMOKE, (double) pos.getX() + Math.random(),
					(double) pos.getY() + Math.random(), (double) pos.getZ() + Math.random(), 0.0D, 0.0D, 0.0D);
	}

	@Override
	public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
		consumer.accept(new IClientFluidTypeExtensions() {

			@Override
			public ResourceLocation getStillTexture() {
				return AMMONIA_LIQUID_TEXTURE;
			}

			@Override
			public ResourceLocation getFlowingTexture() {
				return AMMONIA_LIQUID_FLOWING_TEXTURE;
			}

			@Override
			public ResourceLocation getOverlayTexture() {
				return WATER_OVERLAY;
			}

			@Override
			public ResourceLocation getRenderOverlayTexture(Minecraft mc) {
				// TODO Auto-generated method stub
				return UNDERWATER_LOCATION;
			}
			/*
			 * @Override public int getTintColor() { return 0xFF9ca6c9; }
			 * 
			 * @Override public int getTintColor(FluidState state, BlockAndTintGetter
			 * getter, BlockPos pos) { return 0xFF9ca6c9; }
			 */

			@Override
			public int getTintColor() {
				return 0xFF3F76E4;
			}

			@Override
			public int getTintColor(FluidState state, BlockAndTintGetter getter, BlockPos pos) {
				return BiomeColors.getAverageWaterColor(getter, pos) | 0xFF000000;
			}

			@Override
			public Vector3f modifyFogColor(Camera camera, float partialTick, ClientLevel level, int renderDistance,
					float darkenWorldAmount, Vector3f fluidFogColor) {
				return new Vector3f(53f / 255f, 58f / 255f, 76f / 255f);
			}

			@Override
			public void modifyFogRender(Camera camera, FogRenderer.FogMode mode, float renderDistance,
					float partialTick, float nearDistance, float farDistance, FogShape shape) {
				RenderSystem.setShaderFogStart(1f);
				RenderSystem.setShaderFogEnd(20f); // distance when the fog starts
			}

		});

	}

}
