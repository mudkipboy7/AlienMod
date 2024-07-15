package mudkipboy7.alien.world.block;

import mudkipboy7.alien.AlienMod;
import mudkipboy7.alien.world.block.fluid.AmmoniaFluidType;
import mudkipboy7.alien.world.block.fluid.AmmoniaLiquid;
import mudkipboy7.alien.world.item.AMItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.SoundActions;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class AMFluids {
	// Loader Stuff
	public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS,
			AlienMod.MODID);

	public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(ForgeRegistries.FLUID_TYPES,
			AlienMod.MODID);
	
	/*
	 * Liquid Ammonia
	 */

	public static final ResourceLocation AMMONIA_LIQUID_TEXTURE = new ResourceLocation(AlienMod.MODID,
			"block/fluid/ammonia_liquid"),
			AMMONIA_LIQUID_FLOWING_TEXTURE = new ResourceLocation(AlienMod.MODID, "block/fluid/ammonia_liquid_flowing"),
			UNDERWATER_LOCATION = new ResourceLocation("textures/misc/underwater.png"),
			WATER_OVERLAY = new ResourceLocation("block/water_overlay");

	public static final RegistryObject<AmmoniaFluidType> AMMONIA_LIQUID_TYPE = FLUID_TYPES.register(
			"ammonia_liquid_type",
			() -> new AmmoniaFluidType(AmmoniaFluidType.Properties.create().viscosity(500).density(10)
					.canConvertToSource(true).canPushEntity(true).canExtinguish(true).canDrown(true)
					.fallDistanceModifier(0F).lightLevel(2).sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
					.sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
					.sound(SoundActions.FLUID_VAPORIZE, SoundEvents.FIRE_EXTINGUISH)));

	public static final RegistryObject<ForgeFlowingFluid> AMMONIA_LIQUID_SOURCE = FLUIDS.register("ammonia_liquid",
			() -> new AmmoniaLiquid.Source(AMFluids.AMMONIA_LIQUID_PROPERTIES));

	public static final RegistryObject<ForgeFlowingFluid> AMMONIA_LIQUID_FLOWING = FLUIDS
			.register("ammonia_liquid_flowing", () -> new AmmoniaLiquid.Flowing(AMFluids.AMMONIA_LIQUID_PROPERTIES));

	public static final AmmoniaLiquid.Properties AMMONIA_LIQUID_PROPERTIES = new AmmoniaLiquid.Properties(
			() -> AMFluids.AMMONIA_LIQUID_TYPE.get(), AMMONIA_LIQUID_SOURCE, AMMONIA_LIQUID_FLOWING)
			.block(AMBlocks.AMMONIA_LIQUID_BLOCK).levelDecreasePerBlock(1).bucket(AMItems.AMMONIA_LIQUID_BUCKET);

}
