package mudkipboy7.alien.data.advancement;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;

public class AMAdvancements extends ForgeAdvancementProvider {
	public AMAdvancements(PackOutput output, CompletableFuture<Provider> lookupProvider,
			ExistingFileHelper existingFileHelper) {
		super(output, lookupProvider, existingFileHelper,
				List.of(new AMAchievementGen()));
	}

	abstract static class AbstractAlienAdvancementGen implements ForgeAdvancementProvider.AdvancementGenerator {

	}
}
