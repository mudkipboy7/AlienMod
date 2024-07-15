package mudkipboy7.alien.data.tags;

import java.util.concurrent.CompletableFuture;

import mudkipboy7.alien.AlienMod;
import mudkipboy7.alien.data.AMDamageTypes;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.DamageTypeTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraftforge.common.data.ExistingFileHelper;

public class AMDamageTypeTags extends DamageTypeTagsProvider {

	// Tags
	public static final TagKey<DamageType> NO_OXYGEN = makeKey("no_oxygen");

	public AMDamageTypeTags(PackOutput output, CompletableFuture<Provider> lookupProvider,
			ExistingFileHelper existingFileHelper) {
		super(output, lookupProvider, AlienMod.MODID, existingFileHelper);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void addTags(Provider provider) {
		/*
		 * AlienMod
		 */
		this.tag(AMDamageTypeTags.NO_OXYGEN)
			.add(AMDamageTypes.NO_OXYGEN);

		/*
		 * Vanilla/Forge/Other
		 */

	}
	
	// Methods
	private static TagKey<DamageType> makeKey(String id, String name) {
		return TagKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(id, name));
	}

	public static TagKey<DamageType> makeKey(String name) {
		return makeKey(AlienMod.MODID, name);
	}

}
