package mudkipboy7.alien.world.block.fluid;

import java.util.function.Consumer;

import org.joml.Vector3f;

import com.mojang.blaze3d.shaders.FogShape;
import com.mojang.blaze3d.systems.RenderSystem;

import mudkipboy7.alien.world.block.AMFluids;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidType;

public class AmmoniaFluidType extends FluidType {

	public AmmoniaFluidType(Properties properties) {
		super(properties);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
		consumer.accept(new IClientFluidTypeExtensions() {

			@Override
			public ResourceLocation getStillTexture() {
				return AMFluids.AMMONIA_LIQUID_TEXTURE;
			}

			@Override
			public ResourceLocation getFlowingTexture() {
				return AMFluids.AMMONIA_LIQUID_FLOWING_TEXTURE;
			}

			@Override
			public ResourceLocation getOverlayTexture() {
				return AMFluids.WATER_OVERLAY;
			}

			@Override
			public ResourceLocation getRenderOverlayTexture(Minecraft mc) {
				// TODO Auto-generated method stub
				return AMFluids.UNDERWATER_LOCATION;
			}
			/*
			@Override
			public int getTintColor() {
				return 0xFF9ca6c9;
			}         

			@Override
			public int getTintColor(FluidState state, BlockAndTintGetter getter, BlockPos pos) {
				return 0xFF9ca6c9;
			}*/
			
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
