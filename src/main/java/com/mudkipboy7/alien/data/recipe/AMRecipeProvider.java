package com.mudkipboy7.alien.data.recipe;

import com.mudkipboy7.alien.AlienMod;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;

abstract class AMRecipeProvider extends RecipeProvider {
	public AMRecipeProvider(PackOutput packOutput) {
		super(packOutput);
	}
	/*
	 * Crafting Table Methods
	 */
	
	// makes stairs recipes
	protected static void basicStairs(Item ingredient, Item result,
			RecipeOutput output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result, 4)
        	.pattern("M  ")
        	.pattern("MM ")
        	.pattern("MMM")
        	.define('M', ingredient)
        	.unlockedBy(getHasName(ingredient), has(ingredient))
        	.save(output);
	}
	
	// Makes slab recipes
	protected static void basicSlab(Item ingredient, Item result,
			RecipeOutput output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result, 6)
        	.pattern("MMM")
        	.define('M', ingredient)
        	.unlockedBy(getHasName(ingredient), has(ingredient))
        	.save(output);
	}
	
	
	// Makes Sword Recipes
	protected static void basicSword(Item ingredient, Item result,
			RecipeOutput output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result, 1)
        	.pattern("M")
        	.pattern("M")
        	.pattern("S")
        	.define('M', ingredient)
        	.define('S', Tags.Items.RODS_WOODEN)
        	.unlockedBy(getHasName(ingredient), has(ingredient))
        	.save(output);
	}
	
	// Makes Pickaxe Recipes
	protected static void basicPickaxe(Item ingredient, Item result,
			RecipeOutput output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, result, 1)
        	.pattern("MMM")
        	.pattern(" S ")
        	.pattern(" S ")
        	.define('M', ingredient)
        	.define('S', Tags.Items.RODS_WOODEN)
        	.unlockedBy(getHasName(ingredient), has(ingredient))
        	.save(output);
	}
	
	// Makes Axe Recipes
	protected static void basicAxe(Item ingredient, Item result,
			RecipeOutput output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, result, 1)
        	.pattern("MM")
        	.pattern("MS")
        	.pattern(" S")
        	.define('M', ingredient)
        	.define('S', Tags.Items.RODS_WOODEN)
        	.unlockedBy(getHasName(ingredient), has(ingredient))
        	.save(output);
	}
	
	// Makes Shovel Recipes
	protected static void basicShovel(Item ingredient, Item result,
			RecipeOutput output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, result, 1)
        	.pattern("M")
        	.pattern("S")
        	.pattern("S")
        	.define('M', ingredient)
        	.define('S', Tags.Items.RODS_WOODEN)
        	.unlockedBy(getHasName(ingredient), has(ingredient))
        	.save(output);
	}
	
	// Makes Hoe Recipes
	protected static void basicHoe(Item ingredient, Item result,
			RecipeOutput output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, result, 1)
        	.pattern("MM")
        	.pattern(" S")
        	.pattern(" S")
        	.define('M', ingredient)
        	.define('S', Tags.Items.RODS_WOODEN)
        	.unlockedBy(getHasName(ingredient), has(ingredient))
        	.save(output);
	}
	// Planks from log
	protected static void planksFromLog(Item result,
			RecipeOutput output, int count, Item... items) {
		for (int i = 0; i < items.length ; i++) {
			ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, result, count)
			.requires(items[i])
			.unlockedBy(getHasName(items[i]), has(items[i]))
			.save(output, AlienMod.MODID + ":" + getItemName(result) + "_from_" + getItemName(items[i]));
		}
	}
	
	// Makes door recipes
	protected static void basicDoor(Item ingredient, Item result,
			RecipeOutput output) {
		doorBuilder(result, Ingredient.of(ingredient))
			.unlockedBy(getHasName(ingredient), has(ingredient))
			.save(output);
	}
	
	// Makes fence recipes
	protected static void basicFence(Item ingredient, Item result,
			RecipeOutput output) {
		fenceBuilder(result, Ingredient.of(ingredient))
			.unlockedBy(getHasName(ingredient), has(ingredient))
			.save(output);
	}
	
	// Makes fencegate recipes
	protected static void basicFenceGate(Item ingredient, Item result,
			RecipeOutput output) {
		fenceGateBuilder(result, Ingredient.of(ingredient))
			.unlockedBy(getHasName(ingredient), has(ingredient))
			.save(output);
	}
	
	// Makes trapdoor recipes
	protected static void basicTrapdoor(Item ingredient, Item result,
			RecipeOutput output) {
		trapdoorBuilder(result, Ingredient.of(ingredient))
			.unlockedBy(getHasName(ingredient), has(ingredient))
			.save(output);
	}
	
	// Makes button recipes
	protected static void basicButton(Item ingredient, Item result,
			RecipeOutput output) {
		buttonBuilder(result, Ingredient.of(ingredient))
			.unlockedBy(getHasName(ingredient), has(ingredient))
			.save(output);
	}
	
	// Makes pressure plate recipes
	protected static void basicPressurePlate(Item ingredient, Item result,
		RecipeOutput output) {
		pressurePlateBuilder(RecipeCategory.REDSTONE, result, Ingredient.of(ingredient))
			.unlockedBy(getHasName(ingredient), has(ingredient))
			.save(output);
	}
	
	// Makes pressure plate recipes
	protected static void basicSign(Item ingredient, Item result,
			RecipeOutput output) {
		signBuilder(result, Ingredient.of(ingredient))
			.unlockedBy(getHasName(ingredient), has(ingredient))
			.save(output);
	}
	
	// Makes hanging sign Recipes
	protected static void basicHangingSign(Item ingredient, Item result,
			RecipeOutput output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, result, 6)
        	.group("hanging_sign")
        	.pattern("C C")
        	.pattern("MMM")
        	.pattern("MMM")
        	.define('M', ingredient)
        	.define('C', Items.CHAIN)
        	.unlockedBy(getHasName(ingredient), has(ingredient))
        	.save(output);
	}
	
	// Makes storage block Recipes
	protected static void basicStorageBlock(Item item, Item block,
			RecipeOutput output) {
	      ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, item, 9)
	      .requires(block)
	      .group((String)null)
	      .unlockedBy(getHasName(block), has(block))
	      .save(output, AlienMod.MODID + ":" + getItemName(item) + "_from_" + getItemName(block));
	      
	      ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, block)
	      .pattern("MMM")
	      .pattern("MMM")
	      .pattern("MMM")
	      .define('M', item)
	      .group((String)null)
	      .unlockedBy(getHasName(item), has(item))
	      .save(output, AlienMod.MODID + ":" + getItemName(block) + "_from_" + getItemName(item));
	}
	
	/*
	 * Furnace Functions
	 */
	
	// Makes basic recipes for the normal furnace
	protected static void basicSmeltingRecipe(Item ingredient, Item result, float gainedExp,
			RecipeOutput output) {
		SimpleCookingRecipeBuilder.smelting(Ingredient.of(ingredient), RecipeCategory.MISC,
			result,
			gainedExp,
			200)
			.unlockedBy(getHasName(ingredient), has(ingredient))
			.save(output, AlienMod.MODID + ":" + getItemName(result) + "_from_smelting_" + getItemName(ingredient));
	}
	
	/*
	 * Blast-Furnace Functions
	 */
	
	// Makes basic recipes for the blast furnace
	protected static void basicBlastingRecipe(Item ingredient, Item result, float gainedExp,
			RecipeOutput output) {
		SimpleCookingRecipeBuilder.blasting(Ingredient.of(ingredient), RecipeCategory.MISC,
			result,
			gainedExp,
			100)
			.unlockedBy(getHasName(ingredient), has(ingredient))
			.save(output, AlienMod.MODID + ":" + getItemName(result) + "_from_blasting_" + getItemName(ingredient));	
	}
}
