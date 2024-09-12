package mudkipboy7.alien;

import java.util.function.Supplier;

import mudkipboy7.alien.inventory.AMCreativeTabs;
import mudkipboy7.alien.inventory.AMMenuTypes;
import mudkipboy7.alien.sound.AMSoundEvents;
import mudkipboy7.alien.world.block.AMBlocks;
import mudkipboy7.alien.world.block.AMFluids;
import mudkipboy7.alien.world.block.blockentity.AMBlockEntities;
import mudkipboy7.alien.world.effect.AMMobEffects;
import mudkipboy7.alien.world.entity.AMEntities;
import mudkipboy7.alien.world.item.AMItems;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

public final class AMRegistry {
	// All of the deferred registers for the mod
	public static final DeferredRegister<Block> BLOCKS = deferredRegister(ForgeRegistries.BLOCKS);
	public static final DeferredRegister<Item> ITEMS = deferredRegister(ForgeRegistries.ITEMS);
	public static final DeferredRegister<Fluid> FLUIDS = deferredRegister(ForgeRegistries.FLUIDS);
	public static final DeferredRegister<FluidType> FLUID_TYPES = deferredRegister(ForgeRegistries.FLUID_TYPES);
	public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = deferredRegister(
			Registries.CREATIVE_MODE_TAB);
	public static final DeferredRegister<SoundEvent> SOUND_EVENTS = deferredRegister(ForgeRegistries.SOUND_EVENTS);
	public static final DeferredRegister<MobEffect> MOB_EFFECTS = deferredRegister(ForgeRegistries.MOB_EFFECTS);
	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = deferredRegister(
			ForgeRegistries.BLOCK_ENTITY_TYPES);
	public static final DeferredRegister<MenuType<?>> MENU_TYPES = deferredRegister(ForgeRegistries.MENU_TYPES);
	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = deferredRegister(ForgeRegistries.ENTITY_TYPES);
	public static final DeferredRegister<Potion> POTIONS = deferredRegister(ForgeRegistries.POTIONS);

	/**
	 * This sets up all of the deferred registries for the mod
	 * 
	 * @param modBus
	 */
	static final void init(IEventBus modBus) {
		/*
		 * Init all of the classes that use the deferred registers just in case they
		 * aren't already for some reason
		 */
		new AMBlocks();
		new AMItems();
		new AMFluids();

		new AMCreativeTabs();
		new AMSoundEvents();
		new AMMobEffects();
		new AMBlockEntities();
		new AMMenuTypes();
		new AMEntities();
		new AMMobEffects.AMPotions();
		/*
		 * Init all the deferred Registers
		 */
		BLOCKS.register(modBus);
		ITEMS.register(modBus);
		FLUIDS.register(modBus);
		FLUID_TYPES.register(modBus);

		CREATIVE_TABS.register(modBus);
		SOUND_EVENTS.register(modBus);
		MOB_EFFECTS.register(modBus);
		BLOCK_ENTITY_TYPES.register(modBus);
		MENU_TYPES.register(modBus);
		ENTITY_TYPES.register(modBus);
		POTIONS.register(modBus);
	}

	private static <T> DeferredRegister<T> deferredRegister(IForgeRegistry<T> type) {
		return DeferredRegister.create(type, AlienMod.MODID);
	}

	private static <T> DeferredRegister<T> deferredRegister(ResourceKey<Registry<T>> type) {
		return DeferredRegister.create(type, AlienMod.MODID);
	}

	private static <T> DeferredRegister<T> deferredRegister(Supplier<IForgeRegistry<T>> type) {
		return DeferredRegister.create(type, AlienMod.MODID);

	}
}
