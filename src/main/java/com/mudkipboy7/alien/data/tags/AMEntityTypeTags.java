package com.mudkipboy7.alien.data.tags;

import java.util.concurrent.CompletableFuture;

import com.mudkipboy7.alien.AlienMod;
import com.mudkipboy7.alien.world.entity.AMEntities;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.common.data.ExistingFileHelper;

public class AMEntityTypeTags extends EntityTypeTagsProvider {

	// Tags
	public static final TagKey<EntityType<?>> ALIEN_CREATURE = makeKey("alien_creature");
	public static final TagKey<EntityType<?>> COLD_BLOODED = makeKey("cold_blooded");
	public static final TagKey<EntityType<?>> CANNOT_SURVIVE_OVERWORLD = makeKey("cannot_survive_overworld");
	public static final TagKey<EntityType<?>> ALIEN_WEAPON_VULNERABLE = makeKey("alien_weapon_vulnerable");
	public static final TagKey<EntityType<?>> VANILLA_WEAPON_RESISTANT = makeKey("vanilla_weapon_resistant");

	public AMEntityTypeTags(PackOutput output, CompletableFuture<Provider> lookupProvider,
			ExistingFileHelper existingFileHelper) {
		super(output, lookupProvider, AlienMod.MODID, existingFileHelper);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void addTags(Provider provider) {
		/*
		 * AlienMod
		 */
		tag(AMEntityTypeTags.ALIEN_CREATURE)
			.add(AMEntities.ALIEN_ZOMBIE.get(),
					AMEntities.JOVIAN_BOSS.get());
		
		// Remember, you only need to put LivingEntities here.
		tag(AMEntityTypeTags.COLD_BLOODED)
			.add(AMEntities.TEST_ENTITY.get(),
					
				EntityType.GHAST,  EntityType.IRON_GOLEM, EntityType.SNOW_GOLEM,
				EntityType.ENDERMAN, EntityType.ENDER_DRAGON, EntityType.ENDERMITE,
				EntityType.SLIME, EntityType.ALLAY, EntityType.VEX,
				EntityType.RAVAGER, EntityType.WARDEN, EntityType.SHULKER,
				EntityType.GHAST, EntityType.GUARDIAN, EntityType.ELDER_GUARDIAN,	
				
				EntityType.ZOMBIE, EntityType.ZOMBIE_VILLAGER, EntityType.ZOMBIE_HORSE,
				EntityType.ZOMBIFIED_PIGLIN, EntityType.ZOGLIN, EntityType.PHANTOM,
				EntityType.WITHER, EntityType.DROWNED, EntityType.HUSK)
			.addTags(AMEntityTypeTags.ALIEN_CREATURE, EntityTypeTags.SKELETONS);

		tag(AMEntityTypeTags.CANNOT_SURVIVE_OVERWORLD);

		tag(AMEntityTypeTags.ALIEN_WEAPON_VULNERABLE)
			.add(AMEntities.TEST_ENTITY.get(),

				EntityType.PLAYER, EntityType.ENDERMAN, EntityType.ENDER_DRAGON,
				EntityType.ENDERMITE, EntityType.SHULKER)
			.addTags(AMEntityTypeTags.ALIEN_CREATURE);

		tag(AMEntityTypeTags.VANILLA_WEAPON_RESISTANT)
		.addTags(AMEntityTypeTags.ALIEN_CREATURE);

		/*
		 * Vanilla/Forge/Other
		 */

	}
	// Methods
	private static TagKey<EntityType<?>> makeKey(String id, String name) {
		return TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation(id, name));
	}

	public static TagKey<EntityType<?>> makeKey(String name) {
		return makeKey(AlienMod.MODID, name);
	}
}
