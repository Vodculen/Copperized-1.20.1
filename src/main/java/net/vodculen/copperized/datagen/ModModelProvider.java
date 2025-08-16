package net.vodculen.copperized.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.item.ArmorItem;
import net.vodculen.copperized.item.ModItems;

public class ModModelProvider extends FabricModelProvider {
	public ModModelProvider(FabricDataOutput output) {
		super(output);
	}

	@Override
	public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

	}

	@Override
	public void generateItemModels(ItemModelGenerator itemModelGenerator) {
		// Tools
		itemModelGenerator.register(ModItems.COPPER_STAFF, Models.HANDHELD);
		itemModelGenerator.register(ModItems.COPPER_SWORD, Models.HANDHELD);
		itemModelGenerator.register(ModItems.COPPER_SHOVEL, Models.HANDHELD);
		itemModelGenerator.register(ModItems.COPPER_PICKAXE, Models.HANDHELD);
		itemModelGenerator.register(ModItems.COPPER_AXE, Models.HANDHELD);
		itemModelGenerator.register(ModItems.COPPER_HOE, Models.HANDHELD);

		// Armor
		itemModelGenerator.registerArmor(((ArmorItem) ModItems.CONDUCTIBLE_COPPER_HELMET));
		itemModelGenerator.registerArmor(((ArmorItem) ModItems.COPPER_HELMET));
		itemModelGenerator.registerArmor(((ArmorItem) ModItems.COPPER_CHESTPLATE));
		itemModelGenerator.registerArmor(((ArmorItem) ModItems.COPPER_LEGGINGS));
		itemModelGenerator.registerArmor(((ArmorItem) ModItems.COPPER_BOOTS));

	}
	
}
