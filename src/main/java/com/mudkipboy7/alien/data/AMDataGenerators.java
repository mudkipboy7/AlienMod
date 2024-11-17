package com.mudkipboy7.alien.data;

import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

import com.mudkipboy7.alien.AlienMod;
import com.mudkipboy7.alien.data.AMLanguage.Affixes;
import com.mudkipboy7.alien.data.advancement.AMAdvancements;
import com.mudkipboy7.alien.data.loot.AMLootTables;
import com.mudkipboy7.alien.data.model.AMBlockModelGen;
import com.mudkipboy7.alien.data.model.AMItemModelGen;
import com.mudkipboy7.alien.data.recipe.AMCraftingGen;
import com.mudkipboy7.alien.data.tags.AMBiomeTags;
import com.mudkipboy7.alien.data.tags.AMBlockTags;
import com.mudkipboy7.alien.data.tags.AMDamageTypeTags;
import com.mudkipboy7.alien.data.tags.AMEntityTypeTags;
import com.mudkipboy7.alien.data.tags.AMItemTags;
import com.mudkipboy7.alien.world.worldgen.biome.AMBiomes;
import com.mudkipboy7.alien.world.worldgen.dimension.AMDimensions;
import com.mudkipboy7.alien.world.worldgen.dimension.AmDensityFunctions;
import com.mudkipboy7.alien.world.worldgen.structure.AMGrassFeatures;
import com.mudkipboy7.alien.world.worldgen.structure.AMTreeFeatures;
import com.mudkipboy7.alien.world.worldgen.structure.placement.AMVegetationPlacers;

import net.minecraft.DetectedVersion;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.metadata.PackMetadataGenerator;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.metadata.pack.PackMetadataSection;
import net.minecraft.util.InclusiveRange;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = AlienMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class AMDataGenerators {
	/**
	 * This creates datapack/resourcepack stuff thats defined in code. Launch
	 * runData to generate.
	 */
	@SuppressWarnings({ "deprecation", "unchecked", "rawtypes" })
	// @SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		/*
		 * "event.includeClient()" outputs to "src/generated/resources/assets/MODID"
		 * "event.includeServer()" outputs to "src/generated/resources/data/MODID"
		 * "event.includeReports()" outputs to "src/generated/resources/.cache"
		 * "event.includeDev()" Runs some dev tools
		 */
		DataGenerator generator = event.getGenerator();
		PackOutput output = generator.getPackOutput();
		ExistingFileHelper helper = event.getExistingFileHelper();
		CompletableFuture<HolderLookup.Provider> provider = event.getLookupProvider();
		// Registry data
		DatapackBuiltinEntriesProvider datapackBuiltIn = new AMBuiltInData(output, provider);
		CompletableFuture<HolderLookup.Provider> lookupProvider = datapackBuiltIn.getRegistryProvider();
		generator.addProvider(event.includeServer(), datapackBuiltIn);

		// Client
		generator.addProvider(event.includeClient(), new AMBlockModelGen(output, helper));
		generator.addProvider(event.includeClient(), new AMItemModelGen(output, helper));
		generator.addProvider(event.includeClient(), new AMLanguage.AmericanEnglishGenerator(output));
		generator.addProvider(event.includeClient(), new AMSoundDataGen(output, helper));

		// Server
		generator.addProvider(event.includeServer(), new AMCraftingGen(output));
		generator.addProvider(event.includeServer(), new AMLootTables(output));
		generator.addProvider(event.includeServer(), new AMAdvancements(output, lookupProvider, helper));

		// Tags
		AMBlockTags blockTags = new AMBlockTags(output, provider, helper);
		generator.addProvider(event.includeServer(), blockTags);
		generator.addProvider(event.includeServer(),
				new AMItemTags(output, provider, blockTags.contentsGetter(), helper));
		generator.addProvider(event.includeServer(), new AMEntityTypeTags(output, provider, helper));

		// Registry tags
		generator.addProvider(event.includeServer(), new AMDamageTypeTags(output, lookupProvider, helper));
		generator.addProvider(event.includeServer(), new AMBiomeTags(output, lookupProvider, helper));

		// Pack.mcmeta
		PackMetadataGenerator pack = new PackMetadataGenerator(output);
		generator.addProvider(true, pack.add(PackMetadataSection.TYPE, new PackMetadataSection(
				// Desc
				Component.translatable("pack" + AlienMod.MODID + Affixes.descSuffix),
				// Pack Format
				DetectedVersion.BUILT_IN.getPackVersion(PackType.SERVER_DATA),
				// Supported formats. Goes from 0 to integer limit.
				Optional.of(new InclusiveRange<>(0, Integer.MAX_VALUE)))));

	}

	/**
	 * Built in data
	 */
	private static class AMBuiltInData extends DatapackBuiltinEntriesProvider {

		public AMBuiltInData(PackOutput output, CompletableFuture<Provider> provider) {
			super(output, provider, BUILDER, Set.of("minecraft", AlienMod.MODID));
		}

		public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
				.add(Registries.DAMAGE_TYPE, AMDamageTypes::bootstrap)
				.add(Registries.DIMENSION_TYPE, AMDimensions::bootstrapDimensionType)
				.add(Registries.LEVEL_STEM, AMDimensions::bootstrapLevelStem)
				.add(Registries.NOISE_SETTINGS, AMDimensions::bootstrapNoiseGen)
				.add(Registries.BIOME, AMBiomes::bootstrapBiomes).add(Registries.CONFIGURED_FEATURE, context -> {
					AMTreeFeatures.bootstrap(context);
					AMGrassFeatures.bootstrap(context);
				}).add(Registries.PLACED_FEATURE, context -> {
					AMVegetationPlacers.bootstrap(context);
				}).add(Registries.DENSITY_FUNCTION, AmDensityFunctions::bootstrap);

	}
}
