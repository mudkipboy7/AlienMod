package com.mudkipboy7.alien.world.block;

import static com.mudkipboy7.alien.AMRegistry.FLUIDS;
import static com.mudkipboy7.alien.AMRegistry.FLUID_TYPES;

import com.mudkipboy7.alien.AlienMod;
import com.mudkipboy7.alien.world.block.fluid.AmmoniaFluidType;
import com.mudkipboy7.alien.world.block.fluid.AmmoniaLiquid;
import com.mudkipboy7.alien.world.item.AMItems;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.common.SoundActions;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.RegistryObject;

public class AMFluids {

	/*
	 * Liquid Ammonia
	 */

	public static final RegistryObject<FluidType> AMMONIA_LIQUID_TYPE = FLUID_TYPES.register("ammonia_liquid_type",
			() -> new AmmoniaFluidType());

	public static final RegistryObject<AmmoniaLiquid> AMMONIA_LIQUID = FLUIDS.register("ammonia_liquid",
			() -> new AmmoniaLiquid.Source());

	public static final RegistryObject<AmmoniaLiquid> AMMONIA_LIQUID_FLOWING = FLUIDS
			.register("ammonia_liquid_flowing", () -> new AmmoniaLiquid.Flowing());

}
