package net.vodculen.copperized.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Settings;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.vodculen.copperized.Copperized;
import net.vodculen.copperized.item.armor.ModArmorMaterials;
import net.vodculen.copperized.item.armor.custom.ChargeableArmorItem;
import net.vodculen.copperized.item.armor.custom.ConductibleArmorItem;
import net.vodculen.copperized.item.tool.ModToolMaterials;
import net.vodculen.copperized.item.tool.custom.ChargeableAxeItem;
import net.vodculen.copperized.item.tool.custom.ChargeableHoeItem;
import net.vodculen.copperized.item.tool.custom.ChargeablePickaxeItem;
import net.vodculen.copperized.item.tool.custom.ChargeableShovelItem;
import net.vodculen.copperized.item.tool.custom.ChargeableSwordItem;
import net.vodculen.copperized.item.tool.custom.LightningItem;
import net.vodculen.copperized.util.Ticks;


public class ModItems {
	// Tools
	public static final Item COPPER_STAFF = registerItem("copper_staff", new LightningItem(new Settings().maxCount(1).rarity(Rarity.RARE), 3.0, -2.4));
	public static final Item COPPER_SWORD = registerItem("copper_sword", new ChargeableSwordItem(ModToolMaterials.COPPER_MATERIAL, ModToolMaterials.CHARGE_COPPER_MATERIAL, 3, -2.4F, Ticks.getSeconds(3), new Settings()));
	public static final Item COPPER_SHOVEL = registerItem("copper_shovel", new ChargeableShovelItem(ModToolMaterials.COPPER_MATERIAL, ModToolMaterials.CHARGE_COPPER_MATERIAL, 1.5F, -3, Ticks.getSeconds(3), new Settings()));
	public static final Item COPPER_PICKAXE = registerItem("copper_pickaxe", new ChargeablePickaxeItem(ModToolMaterials.COPPER_MATERIAL, ModToolMaterials.CHARGE_COPPER_MATERIAL, 1, -2.8F, Ticks.getSeconds(3), new Settings()));
	public static final Item COPPER_AXE = registerItem("copper_axe", new ChargeableAxeItem(ModToolMaterials.COPPER_MATERIAL, ModToolMaterials.CHARGE_COPPER_MATERIAL, 1, -2.8F, Ticks.getSeconds(3), new Settings()));
	public static final Item COPPER_HOE = registerItem("copper_hoe", new ChargeableHoeItem(ModToolMaterials.COPPER_MATERIAL, ModToolMaterials.CHARGE_COPPER_MATERIAL, -1, -2.0F, Ticks.getSeconds(3), new Settings()));

	// Armor
	public static final Item CONDUCTIBLE_COPPER_HELMET = registerItem("conductible_copper_helmet", new ConductibleArmorItem(ModArmorMaterials.COPPER, ModArmorMaterials.CHARGED_COPPER, ArmorItem.Type.HELMET, new FabricItemSettings()));
	public static final Item COPPER_HELMET = registerItem("copper_helmet", new ChargeableArmorItem(ModArmorMaterials.COPPER, ModArmorMaterials.CHARGED_COPPER, ArmorItem.Type.HELMET, new FabricItemSettings()));
	public static final Item COPPER_CHESTPLATE = registerItem("copper_chestplate", new ChargeableArmorItem(ModArmorMaterials.COPPER, ModArmorMaterials.CHARGED_COPPER, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
	public static final Item COPPER_LEGGINGS = registerItem("copper_leggings", new ChargeableArmorItem(ModArmorMaterials.COPPER, ModArmorMaterials.CHARGED_COPPER, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
	public static final Item COPPER_BOOTS = registerItem("copper_boots", new ChargeableArmorItem(ModArmorMaterials.COPPER, ModArmorMaterials.CHARGED_COPPER, ArmorItem.Type.BOOTS, new FabricItemSettings()));



	// Below are helper classes that make defining Items easier as well as making them accessible to the entry class
	private static Item registerItem(String name, Item item) {
		return Registry.register(Registries.ITEM, Identifier.of(Copperized.MOD_ID, name), item);
	}

	public static void registerModItems() {
		Copperized.LOGGER.info("Registering Mod Items for " + Copperized.MOD_ID);
	}
}
