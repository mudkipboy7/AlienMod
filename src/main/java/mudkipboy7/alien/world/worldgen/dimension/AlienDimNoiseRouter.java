package mudkipboy7.alien.world.worldgen.dimension;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraft.world.level.levelgen.DensityFunctions;
import net.minecraft.world.level.levelgen.NoiseRouter;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

public class AlienDimNoiseRouter {
	/*
	 * I have no clue what most of this does, I just copied overworld's noise router
	 */

	public static final float GLOBAL_OFFSET = -0.50375F;
	public static final int ISLAND_CHUNK_DISTANCE = 64;
	public static final long ISLAND_CHUNK_DISTANCE_SQR = 4096L;
	private static final ResourceKey<DensityFunction> Y = createKey("y");
	private static final ResourceKey<DensityFunction> SHIFT_X = createKey("shift_x");
	private static final ResourceKey<DensityFunction> SHIFT_Z = createKey("shift_z");
	public static final ResourceKey<DensityFunction> CONTINENTS = createKey("overworld/continents");
	// private static final ResourceKey<DensityFunction> ZERO = createKey("zero");

	public static final ResourceKey<DensityFunction> EROSION = createKey("overworld/erosion");
	public static final ResourceKey<DensityFunction> RIDGES = createKey("overworld/ridges");
	public static final ResourceKey<DensityFunction> RIDGES_FOLDED = createKey("overworld/ridges_folded");
	public static final ResourceKey<DensityFunction> OFFSET = createKey("overworld/offset");
	public static final ResourceKey<DensityFunction> FACTOR = createKey("overworld/factor");
	public static final ResourceKey<DensityFunction> JAGGEDNESS = createKey("overworld/jaggedness");
	public static final ResourceKey<DensityFunction> DEPTH = createKey("overworld/depth");
	private static final ResourceKey<DensityFunction> SLOPED_CHEESE = createKey("overworld/sloped_cheese");
	private static final ResourceKey<DensityFunction> SPAGHETTI_ROUGHNESS_FUNCTION = createKey(
			"overworld/caves/spaghetti_roughness_function");
	private static final ResourceKey<DensityFunction> ENTRANCES = createKey("overworld/caves/entrances");
	private static final ResourceKey<DensityFunction> NOODLE = createKey("overworld/caves/noodle");
	private static final ResourceKey<DensityFunction> PILLARS = createKey("overworld/caves/pillars");
	private static final ResourceKey<DensityFunction> SPAGHETTI_2D = createKey("overworld/caves/spaghetti_2d");

	private static ResourceKey<DensityFunction> createKey(String p_209537_) {
		return ResourceKey.create(Registries.DENSITY_FUNCTION, new ResourceLocation(p_209537_));
	}

	private static DensityFunction getFunction(HolderGetter<DensityFunction> p_256312_,
			ResourceKey<DensityFunction> p_256077_) {
		return new DensityFunctions.HolderHolder(p_256312_.getOrThrow(p_256077_));
	}

	public static float peaksAndValleys(float p_224436_) {
		return -(Math.abs(Math.abs(p_224436_) - 0.6666667F) - 0.33333334F) * 3.0F;
	}

	private static DensityFunction underground(HolderGetter<DensityFunction> p_256548_,
			HolderGetter<NormalNoise.NoiseParameters> p_256236_, DensityFunction p_256658_) {
		DensityFunction densityfunction = getFunction(p_256548_, SPAGHETTI_2D);
		DensityFunction densityfunction1 = getFunction(p_256548_, SPAGHETTI_ROUGHNESS_FUNCTION);
		DensityFunction densityfunction2 = DensityFunctions.noise(p_256236_.getOrThrow(Noises.CAVE_LAYER), 8.0D);
		DensityFunction densityfunction3 = DensityFunctions.mul(DensityFunctions.constant(4.0D),
				densityfunction2.square());
		DensityFunction densityfunction4 = DensityFunctions.noise(p_256236_.getOrThrow(Noises.CAVE_CHEESE),
				0.6666666666666666D);
		DensityFunction densityfunction5 = DensityFunctions
				.add(DensityFunctions.add(DensityFunctions.constant(0.27D), densityfunction4).clamp(-1.0D, 1.0D),
						DensityFunctions
								.add(DensityFunctions.constant(1.5D),
										DensityFunctions.mul(DensityFunctions.constant(-0.64D), p_256658_))
								.clamp(0.0D, 0.5D));
		DensityFunction densityfunction6 = DensityFunctions.add(densityfunction3, densityfunction5);
		DensityFunction densityfunction7 = DensityFunctions.min(
				DensityFunctions.min(densityfunction6, getFunction(p_256548_, ENTRANCES)),
				DensityFunctions.add(densityfunction, densityfunction1));
		DensityFunction densityfunction8 = getFunction(p_256548_, PILLARS);
		DensityFunction densityfunction9 = DensityFunctions.rangeChoice(densityfunction8, -1000000.0D, 0.03D,
				DensityFunctions.constant(-1000000.0D), densityfunction8);
		return DensityFunctions.max(densityfunction7, densityfunction9);
	}

	private static DensityFunction postProcess(DensityFunction p_224493_) {
		DensityFunction densityfunction = DensityFunctions.blendDensity(p_224493_);
		return DensityFunctions.mul(DensityFunctions.interpolated(densityfunction), DensityFunctions.constant(0.64D))
				.squeeze();
	}

	public static NoiseRouter alienDimNoiseRouter(HolderGetter<DensityFunction> densityFunction,
			HolderGetter<NormalNoise.NoiseParameters> noiseParameters) {

		DensityFunction densityfunction = DensityFunctions.noise(noiseParameters.getOrThrow(Noises.AQUIFER_BARRIER),
				0.5D);
		DensityFunction densityfunction1 = DensityFunctions
				.noise(noiseParameters.getOrThrow(Noises.AQUIFER_FLUID_LEVEL_FLOODEDNESS), 0.67D);
		DensityFunction densityfunction2 = DensityFunctions
				.noise(noiseParameters.getOrThrow(Noises.AQUIFER_FLUID_LEVEL_SPREAD), 0.7142857142857143D);
		DensityFunction densityfunction3 = DensityFunctions.noise(noiseParameters.getOrThrow(Noises.AQUIFER_LAVA));
		DensityFunction densityfunction4 = getFunction(densityFunction, SHIFT_X);
		DensityFunction densityfunction5 = getFunction(densityFunction, SHIFT_Z);
		DensityFunction densityfunction6 = DensityFunctions.shiftedNoise2d(densityfunction4, densityfunction5, 0.25D,
				noiseParameters.getOrThrow(Noises.TEMPERATURE));
		DensityFunction densityfunction7 = DensityFunctions.shiftedNoise2d(densityfunction4, densityfunction5, 0.25D,
				noiseParameters.getOrThrow(Noises.VEGETATION));
		DensityFunction densityfunction8 = getFunction(densityFunction, FACTOR);
		DensityFunction densityfunction9 = getFunction(densityFunction, DEPTH);
		DensityFunction densityfunction10 = noiseGradientDensity(DensityFunctions.cache2d(densityfunction8),
				densityfunction9);
		DensityFunction densityfunction11 = getFunction(densityFunction, SLOPED_CHEESE);
		DensityFunction densityfunction12 = DensityFunctions.min(densityfunction11,
				DensityFunctions.mul(DensityFunctions.constant(5.0D), getFunction(densityFunction, ENTRANCES)));
		DensityFunction densityfunction13 = DensityFunctions.rangeChoice(densityfunction11, -1000000.0D, 1.5625D,
				densityfunction12, underground(densityFunction, noiseParameters, densityfunction11));
		DensityFunction densityfunction14 = DensityFunctions.min(postProcess(slideOverworld(densityfunction13)),
				getFunction(densityFunction, NOODLE));
		DensityFunction densityfunction15 = getFunction(densityFunction, Y);
		int i = -60;
		int j = 50;
		DensityFunction densityfunction16 = yLimitedInterpolatable(densityfunction15,
				DensityFunctions.noise(noiseParameters.getOrThrow(Noises.ORE_VEININESS), 1.5D, 1.5D), i, j, 0);
		// float f = 4.0F;
		DensityFunction densityfunction17 = yLimitedInterpolatable(densityfunction15,
				DensityFunctions.noise(noiseParameters.getOrThrow(Noises.ORE_VEIN_A), 4.0D, 4.0D), i, j, 0).abs();
		DensityFunction densityfunction18 = yLimitedInterpolatable(densityfunction15,
				DensityFunctions.noise(noiseParameters.getOrThrow(Noises.ORE_VEIN_B), 4.0D, 4.0D), i, j, 0).abs();
		DensityFunction densityfunction19 = DensityFunctions.add(DensityFunctions.constant((double) -0.08F),
				DensityFunctions.max(densityfunction17, densityfunction18));
		DensityFunction densityfunction20 = DensityFunctions.noise(noiseParameters.getOrThrow(Noises.ORE_GAP));
		return new NoiseRouter(densityfunction, densityfunction1, densityfunction2, densityfunction3, densityfunction6,
				densityfunction7, getFunction(densityFunction, CONTINENTS), getFunction(densityFunction, EROSION),
				densityfunction9, getFunction(densityFunction, RIDGES),
				slideOverworld(DensityFunctions.add(densityfunction10, DensityFunctions.constant(-0.703125D))
						.clamp(-64.0D, 64.0D)),
				densityfunction14, densityfunction16, densityfunction19, densityfunction20);
	}
	

	private static DensityFunction slideOverworld(DensityFunction densityFunction) {
		return slide(densityFunction, -64, 384, 80, 64, -0.078125D, 0, 24, 0.1171875D);
	}

	private static DensityFunction noiseGradientDensity(DensityFunction p_212272_, DensityFunction p_212273_) {
		DensityFunction densityfunction = DensityFunctions.mul(p_212273_, p_212272_);
		return DensityFunctions.mul(DensityFunctions.constant(4.0D), densityfunction.quarterNegative());
	}

	private static DensityFunction yLimitedInterpolatable(DensityFunction p_209472_, DensityFunction p_209473_,
			int p_209474_, int p_209475_, int p_209476_) {
		return DensityFunctions.interpolated(DensityFunctions.rangeChoice(p_209472_, (double) p_209474_,
				(double) (p_209475_ + 1), p_209473_, DensityFunctions.constant((double) p_209476_)));
	}

	private static DensityFunction slide(DensityFunction p_224444_, int p_224445_, int p_224446_, int p_224447_,
			int p_224448_, double p_224449_, int p_224450_, int p_224451_, double p_224452_) {
		DensityFunction densityfunction1 = DensityFunctions.yClampedGradient(p_224445_ + p_224446_ - p_224447_,
				p_224445_ + p_224446_ - p_224448_, 1.0D, 0.0D);
		DensityFunction $$9 = DensityFunctions.lerp(densityfunction1, p_224449_, p_224444_);
		DensityFunction densityfunction2 = DensityFunctions.yClampedGradient(p_224445_ + p_224450_,
				p_224445_ + p_224451_, 0.0D, 1.0D);
		return DensityFunctions.lerp(densityfunction2, p_224452_, $$9);
	}

}