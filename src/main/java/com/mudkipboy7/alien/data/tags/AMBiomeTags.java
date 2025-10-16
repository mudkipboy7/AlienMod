package com.mudkipboy7.alien.data.tags;

import java.util.concurrent.CompletableFuture;

import com.mudkipboy7.alien.AlienMod;
import com.mudkipboy7.alien.world.worldgen.biome.AMBiomes;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.data.ExistingFileHelper;

public class AMBiomeTags extends BiomeTagsProvider {

	// Tags
	public static final TagKey<Biome> LOW_GRAVITY = makeKey("low_gravity");
	public static final TagKey<Biome> ULTRACOLD = makeKey("ultracold");
	public static final TagKey<Biome> HIGH_GRAVITY = makeKey("high_gravity");
	
	// Structure tags
	public static final TagKey<Biome> HAS_JOVIAN_ARENA = makeKey("has_jovian_arena");

	public AMBiomeTags(PackOutput output, CompletableFuture<Provider> lookupProvider,
			ExistingFileHelper existingFileHelper) {
		super(output, lookupProvider, AlienMod.MODID, existingFileHelper);
	}

	@Override
	protected void addTags(Provider provider) {

		/*
		 * AlienMod
		 */
		tag(AMBiomeTags.LOW_GRAVITY).add(AMBiomes.TOWERING_FOREST, AMBiomes.BARREN_CRATERSCAPE);

		tag(AMBiomeTags.ULTRACOLD).add(AMBiomes.TOWERING_FOREST, AMBiomes.BARREN_CRATERSCAPE,
				AMBiomes.JOVIAN_PLANET_BIOME);
		
		tag(AMBiomeTags.HAS_JOVIAN_ARENA).add(AMBiomes.JOVIAN_PLANET_BIOME);

		/*
		 * Vanilla/Forge/Other
		 */

	}

	// Methods
	private static TagKey<Biome> makeKey(String id, String name) {
		return TagKey.create(Registries.BIOME, new ResourceLocation(id, name));
	}

	public static TagKey<Biome> makeKey(String name) {
		return makeKey(AlienMod.MODID, name);
	}

}
