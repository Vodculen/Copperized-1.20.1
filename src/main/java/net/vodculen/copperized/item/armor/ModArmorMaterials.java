package net.vodculen.copperized.item.armor;

import com.google.common.base.Supplier;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.vodculen.copperized.Copperized;

public enum ModArmorMaterials implements ArmorMaterial {
	COPPER("copper", 20, new int[] { 2, 5, 4, 1 }, 15, 
		SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0F, 0F, () -> Ingredient.ofItems(Items.COPPER_INGOT))
	,
	CHARGED_COPPER("charged_copper", 20, new int[] { 3, 8, 6, 3 }, 15, 
		SoundEvents.ITEM_ARMOR_EQUIP_IRON, 2F, 0F, () -> Ingredient.ofItems(Items.COPPER_INGOT))
	;

	private static final int[] BASE_DURABILITY = {13, 15, 16, 11};
	private final String name;
	private final int durabilityMultiplier;
	private final int[] protectionAmounts;
	private final int enchantability;
	private final SoundEvent equipSound;
	private final float toughness;
	private final float knockbackResistance;
	private final Supplier<Ingredient> repairIngredient;

	ModArmorMaterials(String name, int durabilityMultiplier, int[] protectionAmounts, int enchantability, SoundEvent equipSound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient) {
		this.name = name;
		this.durabilityMultiplier = durabilityMultiplier;
		this.protectionAmounts = protectionAmounts;
		this.enchantability = enchantability;
		this.equipSound = equipSound;
		this.toughness = toughness;
		this.knockbackResistance = knockbackResistance;
		this.repairIngredient = repairIngredient;
	}

	public int getDurability(ArmorItem.Type type) {
		return BASE_DURABILITY[type.ordinal()] * this.durabilityMultiplier;
	}

	public int getProtection(ArmorItem.Type type) {
		return protectionAmounts[type.ordinal()];
	}

	public int getEnchantability() {
		return this.enchantability;
	}

	public SoundEvent getEquipSound() {
		return this.equipSound;
	}

	public Ingredient getRepairIngredient() {
		return this.repairIngredient.get();
	}

	public String getName() {
		return Copperized.MOD_ID + ":" + this.name;
	}

	public float getToughness() {
		return this.toughness;
	}

	public float getKnockbackResistance() {
		return this.knockbackResistance;
	}
}
