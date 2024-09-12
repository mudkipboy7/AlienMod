package mudkipboy7.alien.world.effect;

import static mudkipboy7.alien.AMRegistry.MOB_EFFECTS;
import static mudkipboy7.alien.AMRegistry.POTIONS;

import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.registries.RegistryObject;

public class AMMobEffects {
	// Loader Stuff

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

	public static class AMPotions {

		// Potion for hazard protection effect
		public static final RegistryObject<Potion> HAZARD_PROTECTION_POTION = POTIONS.register("hazard_protection",
				() -> new Potion(new MobEffectInstance(AMMobEffects.HAZARD_PROTECTION.get(), 1800)));

		// Potion for hazard protection effect
		public static final RegistryObject<Potion> LONG_HAZARD_PROTECTION_POTION = POTIONS
				.register("long_hazard_protection", () -> new Potion("hazard_protection",
						new MobEffectInstance(AMMobEffects.HAZARD_PROTECTION.get(), 4800)));

	}

}
