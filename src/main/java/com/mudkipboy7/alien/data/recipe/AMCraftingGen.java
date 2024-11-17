package com.mudkipboy7.alien.data.recipe;

import com.mudkipboy7.alien.AlienMod;
import com.mudkipboy7.alien.data.tags.AMItemTags;
import com.mudkipboy7.alien.world.item.AMItems;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;

public class AMCraftingGen extends AMRecipeProvider {
	public AMCraftingGen(PackOutput packOutput) {
		super(packOutput);
	}
	@Override
	protected void buildRecipes(RecipeOutput output) {
		buildVanillaCraftingReplacments(output);
		buildSmelting(output);
		/*
		 * Crafting Table
		 */
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AMItems.SURVIVAL_HEAD.get(), 1)
			.pattern("III")
			.pattern("SGS")
			.pattern("III")
			.define('S', AMItems.OXYGEN_SACK.get())
			.define('I', Items.IRON_INGOT)
			.define('G', Items.GLASS)
			.unlockedBy(getHasName(AMItems.OXYGEN_SACK.get()), has(AMItems.OXYGEN_SACK.get()))
			.save(output);
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AMItems.OXYGEN_SACK.get(), 1)
			.pattern(" W ")
			.pattern("WPW")
			.pattern(" W ")
			.define('P', Items.PUFFERFISH)
			.define('W', ItemTags.WOOL)
			.unlockedBy(getHasName(Items.PUFFERFISH), has(Items.PUFFERFISH))
			.save(output);
		basicSword(AMItems.ALIEN_PLANKS.get(), AMItems.WOODEN_ALIEN_SWORD.get(), output);
		basicPickaxe(AMItems.ALIEN_PLANKS.get(), AMItems.WOODEN_ALIEN_PICKAXE.get(), output);
		basicAxe(AMItems.ALIEN_PLANKS.get(), AMItems.WOODEN_ALIEN_AXE.get(), output);
		basicShovel(AMItems.ALIEN_PLANKS.get(), AMItems.WOODEN_ALIEN_SHOVEL.get(), output);
		basicHoe(AMItems.ALIEN_PLANKS.get(), AMItems.WOODEN_ALIEN_HOE.get(), output);
		planksFromLog(AMItemTags.LIGNUM_LOGS, AMItems.ALIEN_PLANKS.get(), "has_alien_log", output);
		basicStairs(AMItems.ALIEN_STONE.get(), AMItems.ALIEN_STONE_STAIRS.get(), output);
		basicStairs(AMItems.ALIEN_COBBLESTONE.get(), AMItems.ALIEN_COBBLESTONE_STAIRS.get(), output);
		basicStairs(AMItems.ALIEN_PLANKS.get(), AMItems.ALIEN_WOOD_STAIRS.get(), output);
		basicSlab(AMItems.ALIEN_STONE.get(), AMItems.ALIEN_STONE_SLAB.get(), output);
		basicSlab(AMItems.ALIEN_COBBLESTONE.get(), AMItems.ALIEN_COBBLESTONE_SLAB.get(), output);
		basicSlab(AMItems.ALIEN_PLANKS.get(), AMItems.ALIEN_WOOD_SLAB.get(), output);
		basicSword(AMItems.ALIEN_COBBLESTONE.get(), AMItems.STONE_ALIEN_SWORD.get(), output);
		basicPickaxe(AMItems.ALIEN_COBBLESTONE.get(), AMItems.STONE_ALIEN_PICKAXE.get(), output);
		basicAxe(AMItems.ALIEN_COBBLESTONE.get(), AMItems.STONE_ALIEN_AXE.get(), output);
		basicShovel(AMItems.ALIEN_COBBLESTONE.get(), AMItems.STONE_ALIEN_SHOVEL.get(), output);
		basicHoe(AMItems.ALIEN_COBBLESTONE.get(), AMItems.STONE_ALIEN_HOE.get(), output);
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AMItems.SURVIVAL_TORSO.get(), 1)
			.pattern("R R")
			.pattern("RWR")
			.pattern("RWR")
			.define('R', Items.RABBIT_HIDE)
			.define('W', ItemTags.WOOL)
			.unlockedBy(getHasName(Items.RABBIT_HIDE), has(Items.RABBIT_HIDE))
			.save(output);
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AMItems.SURVIVAL_LEGS.get(), 1)
			.pattern("WRW")
			.pattern("R R")
			.pattern("R R")
			.define('R', Items.RABBIT_HIDE)
			.define('W', ItemTags.WOOL)
			.unlockedBy(getHasName(Items.RABBIT_HIDE), has(Items.RABBIT_HIDE)).save(output);
		basicDoor(AMItems.ALIEN_PLANKS.get(), AMItems.ALIEN_WOOD_DOOR.get(), output);
		basicFence(AMItems.ALIEN_PLANKS.get(), AMItems.ALIEN_WOOD_FENCE.get(), output);
		basicFenceGate(AMItems.ALIEN_PLANKS.get(), AMItems.ALIEN_WOOD_FENCE_GATE.get(), output);
		basicTrapdoor(AMItems.ALIEN_PLANKS.get(), AMItems.ALIEN_WOOD_TRAPDOOR.get(), output);
		basicButton(AMItems.ALIEN_PLANKS.get(), AMItems.ALIEN_WOOD_BUTTON.get(), output);
		basicPressurePlate(AMItems.ALIEN_PLANKS.get(), AMItems.ALIEN_WOOD_PRESSURE_PLATE.get(), output);
		basicSign(AMItems.ALIEN_PLANKS.get(), AMItems.ALIEN_WOOD_SIGN.get(), output);
		basicHangingSign(AMItems.ALIEN_LOG.get(), AMItems.ALIEN_WOOD_HANGING_SIGN.get(), output);
		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, AMItems.AIR_PURIFIER.get(), 1)
			.pattern("CIC")
			.pattern("OOO")
			.pattern("CIC")
			.define('O', AMItems.OXYGEN_SACK.get())
			.define('C', AMItems.ALIEN_COBBLESTONE.get())
			.define('I', Items.IRON_INGOT)
			.unlockedBy(getHasName(AMItems.OXYGEN_SACK.get()), has(AMItems.OXYGEN_SACK.get()))
			.save(output);
		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, AMItems.SURVIVAL_FEET.get(), 1)
			.pattern("W W")
			.pattern("R R")
			.pattern("L L")
			.define('R', Items.RABBIT_HIDE)
			.define('W', ItemTags.WOOL)
			.define('L', Items.LEATHER)
			.unlockedBy(getHasName(Items.RABBIT_HIDE), has(Items.RABBIT_HIDE))
			.save(output);
		//ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, AMItems.ALIEN_CRAFTING_TABLE.get())
		// 	.pattern("PP")
		// 	.pattern("PP")
		//	.define('P', AMItemTags.LIGNUM_PLANKS)
		// 	.unlockedBy(getHasName(AMItems.ALIEN_PLANKS.get()), has(AMItemTags.LIGNUM_PLANKS))
		 //	.save(output);
		basicButton(AMItems.ALIEN_STONE.get(), AMItems.ALIEN_STONE_BUTTON.get(), output);
		basicPressurePlate(AMItems.ALIEN_STONE.get(), AMItems.ALIEN_STONE_PRESSURE_PLATE.get(), output);
		basicFence(AMItems.ALIEN_COBBLESTONE.get(), AMItems.ALIEN_COBBLESTONE_WALL.get(), output);
		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, AMItems.COAL_GENERATOR.get(), 1)
			.pattern("III")
			.pattern("CFC")
			.pattern("CBC")
			.define('F', Items.FURNACE)
			.define('B', AMItems.BATTERY.get())
			.define('I', Tags.Items.INGOTS_IRON)
			.define('C', Tags.Items.INGOTS_COPPER)
			.unlockedBy(getHasName(AMItems.BATTERY.get()), has(AMItems.BATTERY.get()))
			.save(output);
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AMItems.BATTERY.get(), 1)
			.pattern(" I ")
			.pattern("CRC")
			.pattern("CRC")
			.define('R', Tags.Items.DUSTS_REDSTONE)
			.define('I', Tags.Items.INGOTS_IRON)
			.define('C', Tags.Items.INGOTS_COPPER)
			.unlockedBy(getHasName(Items.COPPER_INGOT), has(Tags.Items.INGOTS_COPPER))
			.save(output);
		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, AMItems.ENERGY_STORAGE.get(), 1)
			.pattern("CGC")
			.pattern("BBB")
			.pattern("CGC")
			.define('B', AMItems.BATTERY.get())
			//.define('R', Tags.Items.STORAGE_BLOCKS_REDSTONE)
			.define('G', Tags.Items.INGOTS_GOLD)
			.define('C', Tags.Items.STORAGE_BLOCKS_COPPER)
			.unlockedBy(getHasName(AMItems.BATTERY.get()), has(AMItems.BATTERY.get()))
			.save(output);
		basicStorageBlock(AMItems.ALIEN_METAL_INGOT.get(), AMItems.ALIEN_METAL_BLOCK.get(), output);
		basicStorageBlock(AMItems.RAW_ALIEN_METAL.get(), AMItems.RAW_ALIEN_METAL_BLOCK.get(), output);
		ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, AMItems.ALIEN_METAL_HELMET.get(), 1)
			.pattern("AAA")
			.pattern("ASA")
			.define('A', AMItems.ALIEN_METAL_INGOT.get())
			.define('S', AMItems.SURVIVAL_HEAD.get())
			.unlockedBy(getHasName(AMItems.ALIEN_METAL_INGOT.get()), has(AMItems.ALIEN_METAL_INGOT.get()))
			.save(output);
		ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, AMItems.ALIEN_METAL_CHESTPLATE.get(), 1)
			.pattern("ASA")
			.pattern("AAA")
			.pattern("AAA")
			.define('A', AMItems.ALIEN_METAL_INGOT.get())
			.define('S', AMItems.SURVIVAL_TORSO.get())
			.unlockedBy(getHasName(AMItems.ALIEN_METAL_INGOT.get()), has(AMItems.ALIEN_METAL_INGOT.get()))
			.save(output);
		ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, AMItems.ALIEN_METAL_LEGGINGS.get(), 1)
			.pattern("AAA")
			.pattern("ASA")
			.pattern("A A")
			.define('A', AMItems.ALIEN_METAL_INGOT.get())
			.define('S', AMItems.SURVIVAL_LEGS.get())
			.unlockedBy(getHasName(AMItems.ALIEN_METAL_INGOT.get()), has(AMItems.ALIEN_METAL_INGOT.get()))
			.save(output);
		ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, AMItems.ALIEN_METAL_BOOTS.get(), 1)
			.pattern("ASA")
			.pattern("A A")
			.define('A', AMItems.ALIEN_METAL_INGOT.get())
			.define('S', AMItems.SURVIVAL_FEET.get())
			.unlockedBy(getHasName(AMItems.ALIEN_METAL_INGOT.get()), has(AMItems.ALIEN_METAL_INGOT.get()))
			.save(output);
		basicSword(AMItems.ALIEN_METAL_INGOT.get(), AMItems.ALIEN_METAL_SWORD.get(), output);
		basicPickaxe(AMItems.ALIEN_METAL_INGOT.get(), AMItems.ALIEN_METAL_PICKAXE.get(), output);
		basicAxe(AMItems.ALIEN_METAL_INGOT.get(), AMItems.ALIEN_METAL_AXE.get(), output);
		basicShovel(AMItems.ALIEN_METAL_INGOT.get(), AMItems.ALIEN_METAL_SHOVEL.get(), output);
		basicHoe(AMItems.ALIEN_METAL_INGOT.get(), AMItems.ALIEN_METAL_HOE.get(), output);
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AMItems.INT_LIMIT_BATTERY.get(), 1)
			.pattern("TNT")
			.pattern("TNT")
			.pattern("TNT")
			.define('N', Tags.Items.STORAGE_BLOCKS_NETHERITE)
			.define('T', AMItems.TEST_ITEM.get())
			.unlockedBy(getHasName(AMItems.TEST_ITEM.get()), has(AMItems.TEST_ITEM.get()))
			.save(output);
		basicStorageBlock(AMItems.TEST_ITEM.get(), AMItems.TEST_BLOCK.get(), output);
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AMItems.COPPER_WIRE.get(), 8)
			.pattern("WW")
			.pattern("CC")
			.pattern("WW")
			.define('C', Tags.Items.INGOTS_COPPER)
			.define('W', ItemTags.WOOL)
			.unlockedBy(getHasName(Items.COPPER_INGOT), has(Tags.Items.INGOTS_COPPER))
			.save(output,  AlienMod.MODID + ":" + getItemName(AMItems.COPPER_WIRE.get()) + "_horizontal");
		
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AMItems.COPPER_WIRE.get(), 8)
			.pattern("WCW")
			.pattern("WCW")
			.define('C', Tags.Items.INGOTS_COPPER)
			.define('W', ItemTags.WOOL)
			.unlockedBy(getHasName(Items.COPPER_INGOT), has(Tags.Items.INGOTS_COPPER))
			.save(output,  AlienMod.MODID + ":" + getItemName(AMItems.COPPER_WIRE.get()) + "_vertical");
	}
	
	private void buildVanillaCraftingReplacments(RecipeOutput output) {
		ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, Items.WOODEN_SWORD)
			.pattern("M")
			.pattern("M")
			.pattern("S")
			.define('M', AMItemTags.WOOD_PLANKS_CRAFTING_MATERIALS)
			.define('S', Tags.Items.RODS_WOODEN)
			.unlockedBy("has_stick", has(Tags.Items.RODS_WOODEN))
			.save(output);
		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, Items.WOODEN_PICKAXE)
			.pattern("MMM")
			.pattern(" S ")
			.pattern(" S ")
			.define('M', AMItemTags.WOOD_PLANKS_CRAFTING_MATERIALS)
			.define('S', Tags.Items.RODS_WOODEN)
			.unlockedBy("has_stick", has(Tags.Items.RODS_WOODEN))
			.save(output);
		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, Items.WOODEN_AXE)
			.pattern("MM")
			.pattern("MS")
			.pattern(" S")
			.define('M', AMItemTags.WOOD_PLANKS_CRAFTING_MATERIALS)
			.define('S', Tags.Items.RODS_WOODEN)
			.unlockedBy("has_stick", has(Tags.Items.RODS_WOODEN))
			.save(output);
		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, Items.WOODEN_SHOVEL)
			.pattern("M")
			.pattern("S")
			.pattern("S")
			.define('M', AMItemTags.WOOD_PLANKS_CRAFTING_MATERIALS)
			.define('S', Tags.Items.RODS_WOODEN)
			.unlockedBy("has_stick", has(Tags.Items.RODS_WOODEN))
			.save(output);
		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, Items.WOODEN_HOE)
			.pattern("MM")
			.pattern(" S")
			.pattern(" S")	
			.define('M', AMItemTags.WOOD_PLANKS_CRAFTING_MATERIALS)
			.define('S', Tags.Items.RODS_WOODEN)
			.unlockedBy("has_stick", has(Tags.Items.RODS_WOODEN))
			.save(output);
		 //ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Blocks.CRAFTING_TABLE)
		 //	.pattern("PP")
		 //	.pattern("PP")
		 //	.define('P', AMItemTags.WOOD_PLANKS_CRAFTING_MATERIALS)
		 //	.unlockedBy("unlock_right_away",
		 //			PlayerTrigger.TriggerInstance.tick())
		 //	.showNotification(false).save(output);
	}
	
	private void buildSmelting(RecipeOutput output) { 
		/*
		 * Furnace
		 */
		basicSmeltingRecipe(AMItems.ALIEN_COAL_ORE.get(), Items.COAL, 0.1F, output);
		basicSmeltingRecipe(AMItems.ALIEN_GOLD_ORE.get(), Items.GOLD_INGOT, 1.0F, output);
		basicSmeltingRecipe(AMItems.RAW_ALIEN_METAL.get(), AMItems.ALIEN_METAL_INGOT.get(), 0.7F, output);
		basicSmeltingRecipe(AMItems.ALIEN_METAL_ORE.get(), AMItems.ALIEN_METAL_INGOT.get(), 0.7F, output);
		
		/*
		 * Blast-Furnace
		 */
		basicBlastingRecipe(AMItems.ALIEN_COAL_ORE.get(), Items.COAL, 0.1F, output);
		basicBlastingRecipe(AMItems.ALIEN_GOLD_ORE.get(), Items.GOLD_INGOT, 1.0F, output);
		basicBlastingRecipe(AMItems.RAW_ALIEN_METAL.get(), AMItems.ALIEN_METAL_INGOT.get(), 0.7F, output);
		basicBlastingRecipe(AMItems.ALIEN_METAL_ORE.get(), AMItems.ALIEN_METAL_INGOT.get(), 0.7F, output);
	}
}
