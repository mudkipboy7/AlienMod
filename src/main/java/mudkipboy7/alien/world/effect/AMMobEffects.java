package mudkipboy7.alien.world.effect;

import mudkipboy7.alien.AlienMod;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class AMMobEffects {
	// Loader Stuff
	public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS,
			AlienMod.MODID);

	/*
	 * Register Effects
	 */

	// Used to make players suffocate while in the Alien Dimension
	public static final RegistryObject<NoOxygenMobEffect> NO_OXYGEN = MOB_EFFECTS.register("no_oxygen",
			() -> new NoOxygenMobEffect(MobEffectCategory.HARMFUL, 793950));

	// Used to make players freeze while in the Alien Dimension
	public static final RegistryObject<ColdMobEffect> COLD = MOB_EFFECTS.register("cold",
			() -> new ColdMobEffect(MobEffectCategory.HARMFUL, 6617850));

	// Used to make players freeze while in the Alien Dimension
	public static final RegistryObject<AlienMobEffect> HAZARD_PROTECTION = MOB_EFFECTS.register("hazard_protection",
			() -> new AlienMobEffect(MobEffectCategory.BENEFICIAL, 7864320));

	// Used to make players freeze while in the Alien Dimension
	public static final RegistryObject<AlienMobEffect> NO_SPRINT = MOB_EFFECTS.register("no_sprint",
			() -> new AlienMobEffect(MobEffectCategory.HARMFUL, 1052198));

	public class AMPotions {

		// Loader Stuff
		public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTIONS,
				AlienMod.MODID);

		// Potion for hazard protection effect
		public static final RegistryObject<Potion> HAZARD_PROTECTION_POTION = POTIONS.register("hazard_protection",
				() -> new Potion(new MobEffectInstance(AMMobEffects.HAZARD_PROTECTION.get(), 1800)));

		// Potion for hazard protection effect
		public static final RegistryObject<Potion> LONG_HAZARD_PROTECTION_POTION = POTIONS
				.register("long_hazard_protection", () -> new Potion("hazard_protection",
						new MobEffectInstance(AMMobEffects.HAZARD_PROTECTION.get(), 4800)));

	}

}
