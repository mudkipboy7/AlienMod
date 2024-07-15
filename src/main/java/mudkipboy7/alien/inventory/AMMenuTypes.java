package mudkipboy7.alien.inventory;

import mudkipboy7.alien.AlienMod;
import mudkipboy7.alien.inventory.menu.AlienCraftingTableMenu;
import mudkipboy7.alien.inventory.menu.CoalGeneratorMenu;
import mudkipboy7.alien.inventory.menu.EnergyStorageMenu;
import mudkipboy7.alien.inventory.menu.HazardRemovalMenu;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class AMMenuTypes {
	// Loader Stuff
	public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(ForgeRegistries.MENU_TYPES,
			AlienMod.MODID);

	// Alien Crafting Table
	public static final RegistryObject<MenuType<AlienCraftingTableMenu>> ALIEN_CRAFTING = MENU_TYPES.register(
			"alien_crafting_menu", () -> new MenuType<>(AlienCraftingTableMenu::new, FeatureFlags.REGISTRY.allFlags()));

	// Hazard Removal Machine
	public static final RegistryObject<MenuType<HazardRemovalMenu>> HAZARD_REMOVAL = MENU_TYPES
			.register("hazard_removal_menu", () -> IForgeMenuType.create(HazardRemovalMenu::new));

	// Coal Generator
	public static final RegistryObject<MenuType<CoalGeneratorMenu>> COAL_GENERATOR = MENU_TYPES
			.register("coal_generator_menu", () -> IForgeMenuType.create(CoalGeneratorMenu::new));

	// Energy Storage
	public static final RegistryObject<MenuType<EnergyStorageMenu>> ENERGY_STORAGE = MENU_TYPES
			.register("energy_storage_menu", () -> IForgeMenuType.create(EnergyStorageMenu::new));

}
