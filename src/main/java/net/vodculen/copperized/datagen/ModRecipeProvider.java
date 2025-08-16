package net.vodculen.copperized.datagen;

import java.util.function.Consumer;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.vodculen.copperized.item.ModItems;

public class ModRecipeProvider extends FabricRecipeProvider {
	public ModRecipeProvider(FabricDataOutput output) {
		super(output);
	}

	@Override
	public void generate(Consumer<RecipeJsonProvider> exporter) {
		// Sword Recipes
		ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.COPPER_SWORD)
			.pattern(" # ")
			.pattern(" # ")
			.pattern(" | ")
			.input('#', Items.COPPER_INGOT)
			.input('|', Items.STICK)
			.criterion(hasItem(Items.COPPER_INGOT), conditionsFromItem(Items.COPPER_INGOT))
			.offerTo(exporter)
			;

		// Shovel Recipe
		ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.COPPER_SHOVEL)
			.pattern(" # ")
			.pattern(" | ")
			.pattern(" | ")
			.input('#', Items.COPPER_INGOT)
			.input('|', Items.STICK)
			.criterion(hasItem(Items.COPPER_INGOT), conditionsFromItem(Items.COPPER_INGOT))
			.offerTo(exporter)
			;

		// Axe Recipe
		ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.COPPER_AXE)
			.pattern(" ##")
			.pattern(" |#")
			.pattern(" | ")
			.input('#', Items.COPPER_INGOT)
			.input('|', Items.STICK)
			.criterion(hasItem(Items.COPPER_INGOT), conditionsFromItem(Items.COPPER_INGOT))
			.offerTo(exporter)
			;

		// Hoe Recipe
		ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.COPPER_HOE)
			.pattern(" ##")
			.pattern(" | ")
			.pattern(" | ")
			.input('#', Items.COPPER_INGOT)
			.input('|', Items.STICK)
			.criterion(hasItem(Items.COPPER_INGOT), conditionsFromItem(Items.COPPER_INGOT))
			.offerTo(exporter)
			;

		// Pickaxe Recipe
		ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.COPPER_PICKAXE)
			.pattern("###")
			.pattern(" | ")
			.pattern(" | ")
			.input('#', Items.COPPER_INGOT)
			.input('|', Items.STICK)
			.criterion(hasItem(Items.COPPER_INGOT), conditionsFromItem(Items.COPPER_INGOT))
			.offerTo(exporter)
			;

		// Copper Armor
		ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.COPPER_HELMET)
			.pattern("###")
			.pattern("# #")
			.pattern("   ")
			.input('#', Items.COPPER_INGOT)
			.criterion(hasItem(Items.COPPER_INGOT), conditionsFromItem(Items.COPPER_INGOT))
			.offerTo(exporter)
			;

		ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.COPPER_CHESTPLATE)
			.pattern("# #")
			.pattern("###")
			.pattern("###")
			.input('#', Items.COPPER_INGOT)
			.criterion(hasItem(Items.COPPER_INGOT), conditionsFromItem(Items.COPPER_INGOT))
			.offerTo(exporter)
			;

		ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.COPPER_LEGGINGS)
			.pattern("###")
			.pattern("# #")
			.pattern("# #")
			.input('#', Items.COPPER_INGOT)
			.criterion(hasItem(Items.COPPER_INGOT), conditionsFromItem(Items.COPPER_INGOT))
			.offerTo(exporter)
			;

		ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.COPPER_BOOTS)
			.pattern("# #")
			.pattern("# #")
			.pattern("   ")
			.input('#', Items.COPPER_INGOT)
			.criterion(hasItem(Items.COPPER_INGOT), conditionsFromItem(Items.COPPER_INGOT))
			.offerTo(exporter)
			;

		ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.CONDUCTIBLE_COPPER_HELMET)
			.pattern(" | ")
			.pattern("###")
			.pattern("# #")
			.input('#', Items.COPPER_INGOT)
			.input('|', Items.LIGHTNING_ROD)
			.criterion(hasItem(Items.COPPER_INGOT), conditionsFromItem(Items.COPPER_INGOT))
			.offerTo(exporter)
			;
	}
}
