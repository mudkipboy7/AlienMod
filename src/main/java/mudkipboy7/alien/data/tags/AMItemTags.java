package mudkipboy7.alien.data.tags;

import java.util.concurrent.CompletableFuture;

import mudkipboy7.alien.AlienMod;
import mudkipboy7.alien.world.item.AMItems;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class AMItemTags<T> extends ItemTagsProvider {
	public AMItemTags(PackOutput output, CompletableFuture<Provider> lookupProvider,
			CompletableFuture<TagLookup<Block>> blockTags, ExistingFileHelper existingFileHelper) {
		super(output, lookupProvider, blockTags, AlienMod.MODID, existingFileHelper);
	}

	
	// Item Tags
	public static final TagKey<Item> ALIEN_WEAPON = makeKey("alien_weapon");
	public static final TagKey<Item> SURVIVAL_HEAD = makeKey("survival_head");
	public static final TagKey<Item> SURVIVAL_TORSO = makeKey("survival_torso");
	public static final TagKey<Item> SURVIVAL_LEGS = makeKey("survival_legs");
	public static final TagKey<Item> SURVIVAL_FEET = makeKey("survival_feet");
	public static final TagKey<Item> WOOD_PLANKS_CRAFTING_MATERIALS = makeKey("wood_tool_materials");
	public static final TagKey<Item> CAN_BE_MADE_INTO_OXYGEN = makeKey("can_be_made_into_oxygen");
	public static final TagKey<Item> BATTERIES = makeKey("batteries");
	
	@SuppressWarnings("unchecked")
	@Override
	protected void addTags(Provider provider) {
		addBlockItemTags(provider);

		/*
		 * AlienMod
		 */
		tag(AMItemTags.ALIEN_WEAPON)
			.add(AMItems.WOODEN_ALIEN_SWORD.get(), AMItems.WOODEN_ALIEN_PICKAXE.get(),
				AMItems.WOODEN_ALIEN_AXE.get(), AMItems.WOODEN_ALIEN_SHOVEL.get(),
				AMItems.WOODEN_ALIEN_HOE.get(), AMItems.STONE_ALIEN_SWORD.get(),
				AMItems.STONE_ALIEN_PICKAXE.get(), AMItems.STONE_ALIEN_AXE.get(),
				AMItems.STONE_ALIEN_SHOVEL.get(),AMItems.STONE_ALIEN_HOE.get(),
				AMItems.ALIEN_METAL_SWORD.get(),AMItems.ALIEN_METAL_PICKAXE.get(),
				AMItems.ALIEN_METAL_AXE.get(),AMItems.ALIEN_METAL_SHOVEL.get(),
				AMItems.ALIEN_METAL_HOE.get());

		tag(AMItemTags.SURVIVAL_HEAD)
			.add(AMItems.SURVIVAL_HEAD.get(), AMItems.ALIEN_METAL_HELMET.get());
		
		tag(AMItemTags.SURVIVAL_TORSO)
			.add(AMItems.SURVIVAL_TORSO.get(), AMItems.ALIEN_METAL_CHESTPLATE.get());
		
		tag(AMItemTags.SURVIVAL_LEGS)
			.add(AMItems.SURVIVAL_LEGS.get(), AMItems.ALIEN_METAL_LEGGINGS.get());
		
		tag(AMItemTags.SURVIVAL_FEET)
			.add(AMItems.SURVIVAL_FEET.get(), AMItems.ALIEN_METAL_BOOTS.get());
		
		tag(AMItemTags.WOOD_PLANKS_CRAFTING_MATERIALS)
			.addTags(ItemTags.PLANKS)
			.remove(LIGNUM_PLANKS);
		
		tag(AMItemTags.CAN_BE_MADE_INTO_OXYGEN).add(Items.WATER_BUCKET);
		
		tag(AMItemTags.BATTERIES)
			.add(AMItems.BATTERY.get(), AMItems.INT_LIMIT_BATTERY.get());

		/*
		 * Vanilla/Forge/Other
		 */
		tag(ItemTags.FREEZE_IMMUNE_WEARABLES)
			.addTags(AMItemTags.SURVIVAL_TORSO, AMItemTags.SURVIVAL_LEGS);
		
		tag(ItemTags.STONE_CRAFTING_MATERIALS).add(AMItems.ALIEN_COBBLESTONE.get());
		
		tag(ItemTags.STONE_TOOL_MATERIALS).remove(AMItems.ALIEN_COBBLESTONE.get());
		
		
		tag(Tags.Items.ARMORS_HELMETS).add(AMItems.ALIEN_METAL_HELMET.get());
		
		tag(Tags.Items.ARMORS_CHESTPLATES).add(AMItems.ALIEN_METAL_CHESTPLATE.get());
		
		tag(Tags.Items.ARMORS_LEGGINGS).add(AMItems.ALIEN_METAL_LEGGINGS.get());
		
		tag(Tags.Items.ARMORS_BOOTS).add(AMItems.ALIEN_METAL_BOOTS.get());


		
	}
	
	// BlockItem tags
	public static final TagKey<Item> ALIEN_SOIL = makeKey("alien_soil");
	public static final TagKey<Item> ALIEN_PLANT = makeKey("alien_plant");
	public static final TagKey<Item> LIGNUM_LOGS = makeKey("lignum_logs");
	public static final TagKey<Item> LIGNUM_PLANKS = makeKey("lignum_planks");
	public static final TagKey<Item> LIGNUM_STAIRS = makeKey("lignum_stairs");
	public static final TagKey<Item> LIGNUM_SLABS = makeKey("lignum_slabs");
	protected void addBlockItemTags(Provider provider) {
		
		/*
		 * AlienMod
		 */
		copy(AMBlockTags.ALIEN_SOIL, AMItemTags.ALIEN_SOIL);
		copy(AMBlockTags.ALIEN_PLANT, AMItemTags.ALIEN_PLANT);
		copy(AMBlockTags.LIGNUM_LOGS, AMItemTags.LIGNUM_LOGS);
		copy(AMBlockTags.LIGNUM_PLANKS, AMItemTags.LIGNUM_PLANKS);
		copy(AMBlockTags.LIGNUM_STAIRS, AMItemTags.LIGNUM_STAIRS);
		copy(AMBlockTags.LIGNUM_SLABS, AMItemTags.LIGNUM_SLABS);

		/*
		 * Vanilla/Forge/Other
		 */
		copy(BlockTags.COAL_ORES, ItemTags.COAL_ORES);
		copy(BlockTags.GOLD_ORES, ItemTags.GOLD_ORES);
		copy(BlockTags.WOODEN_FENCES, ItemTags.WOODEN_FENCES);
		copy(BlockTags.FENCE_GATES, ItemTags.FENCE_GATES);
		copy(BlockTags.PLANKS, ItemTags.PLANKS);
		copy(BlockTags.WOODEN_STAIRS, ItemTags.WOODEN_STAIRS);
		copy(BlockTags.WOODEN_SLABS, ItemTags.WOODEN_SLABS);
		copy(BlockTags.WOODEN_SLABS, ItemTags.WOODEN_SLABS);
		copy(BlockTags.LOGS_THAT_BURN, ItemTags.LOGS_THAT_BURN);
		copy(BlockTags.WOODEN_BUTTONS, ItemTags.WOODEN_BUTTONS);
		copy(BlockTags.WOODEN_PRESSURE_PLATES, ItemTags.WOODEN_PRESSURE_PLATES);
		copy(BlockTags.STONE_BUTTONS, ItemTags.STONE_BUTTONS);
		copy(BlockTags.WALLS, ItemTags.WALLS);
		copy(BlockTags.WOODEN_DOORS, ItemTags.WOODEN_DOORS);
		copy(Tags.Blocks.CHESTS_WOODEN, Tags.Items.CHESTS_WOODEN);
		copy(Tags.Blocks.ORES, Tags.Items.ORES);
		copy(Tags.Blocks.STORAGE_BLOCKS, Tags.Items.STORAGE_BLOCKS);
		
	}
	
	// Methods
	private static TagKey<Item> makeKey(String id, String name) {
		return TagKey.create(Registries.ITEM, new ResourceLocation(id, name));
	}

	public static TagKey<Item> makeKey(String name) {
		return makeKey(AlienMod.MODID, name);
	}

}
