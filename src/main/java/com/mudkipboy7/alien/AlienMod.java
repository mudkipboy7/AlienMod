package com.mudkipboy7.alien;

import org.apache.commons.lang3.tuple.Pair;

import com.mudkipboy7.alien.AMConfig.Client;
import com.mudkipboy7.alien.AMConfig.Common;
import com.mudkipboy7.alien.AMConfig.Server;
import com.mudkipboy7.alien.data.AMDataGenerators;
import com.mudkipboy7.alien.sky.AlienDimSky;
import com.mudkipboy7.alien.world.worldgen.AlienChunkGenerator;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegisterEvent;

@Mod(value = AlienMod.MODID)
public final class AlienMod {

	/*
	 * Constants
	 */

	// The mod's Id.
	public static final String MODID = "alien";

	// Enables or changes some things for development use.
	public static final boolean DEVMODE = true;

	// Configs
	public static AMConfig.Server serverConfig;
	public static AMConfig.Client clientConfig;
	public static AMConfig.Common commonConfig;

	// String name for the energy capability
	public static final String ENERGY = "energy";

	// The class for the alienDimSky
	private static AlienDimSky alienDimSky;


	/**
	 * Main method to set up the mod.
	 */
	public AlienMod() {
		
		// b k = null;
		loadCustomJSONStuff();
		// Makes the mod bus
		IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
		// Sets up all of the mod regist
		AMRegistry.init(modBus);
		ModList.get().isLoaded("fossil");
		// Listeners
		modBus.addListener(AMDataGenerators::gatherData);
		modBus.addListener(this::registerStuff);

		// Config
		final Pair<Server, ForgeConfigSpec> serverSpecPair = new ForgeConfigSpec.Builder()
				.configure(AMConfig.Server::new);
		final Pair<Client, ForgeConfigSpec> clientSpecPair = new ForgeConfigSpec.Builder()
				.configure(AMConfig.Client::new);
		final Pair<Common, ForgeConfigSpec> commonSpecPair = new ForgeConfigSpec.Builder()
				.configure(AMConfig.Common::new);
		ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, serverSpecPair.getRight());
		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, clientSpecPair.getRight());
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, commonSpecPair.getRight());
		serverConfig = serverSpecPair.getLeft();
		clientConfig = clientSpecPair.getLeft();
		commonConfig = commonSpecPair.getLeft();
		alienDimSky = new AlienDimSky();
		// Sets up other stuff

	}

	public void registerStuff(RegisterEvent event) {
		if (event.getRegistryKey().equals(Registries.CHUNK_GENERATOR)) {
			Registry.register(BuiltInRegistries.CHUNK_GENERATOR, new ResourceLocation(MODID, "alien_chunk_generator"),
					AlienChunkGenerator.ALIENDIM_CODEC);
		}
	}

	public static ResourceLocation location(String modid, String loc) {
		return new ResourceLocation(modid, loc);
	}

	public static ResourceLocation location(String loc) {
		return location(AlienMod.MODID, loc);
	}

	public static void loadCustomJSONStuff() {
		SpecialPlayers.loadSpecialPlayersStuff();
	}

	public static AlienDimSky getAlienDimSky() {
		return alienDimSky;
	}
}
