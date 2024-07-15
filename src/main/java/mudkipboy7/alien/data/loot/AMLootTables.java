package mudkipboy7.alien.data.loot;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Sets;

import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

public class AMLootTables extends LootTableProvider {

	public AMLootTables(PackOutput output) {
		super(output, Collections.unmodifiableSet(Sets.newHashSet()),
				List.of(new LootTableProvider.SubProviderEntry(AMBlockLootGen::new, LootContextParamSets.BLOCK),
						new LootTableProvider.SubProviderEntry(AMEntityLootGen::new, LootContextParamSets.ENTITY)));
	}

	@Override
	protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext validationcontext) {

	}
}