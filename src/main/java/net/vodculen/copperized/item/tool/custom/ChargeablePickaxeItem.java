package net.vodculen.copperized.item.tool.custom;

import java.util.List;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.Text;
import net.minecraft.world.World;


public class ChargeablePickaxeItem extends PickaxeItem {
	private int attackDamage;
	private float attackSpeed;
	private final ToolMaterial baseMaterial;
	private final ToolMaterial chargedMaterial;

	public ChargeablePickaxeItem(ToolMaterial baseMaterial, ToolMaterial chargedMaterial, int attackDamage, float attackSpeed, int chargedTicks, Settings settings) {
		super(baseMaterial, attackDamage, attackSpeed, settings);

		this.baseMaterial = baseMaterial;
		this.chargedMaterial = chargedMaterial;
		this.attackDamage = attackDamage;
		this.attackSpeed = attackSpeed;
	}

	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
		ChargeableToolItem.inventoryTick(stack, world, entity, slot, selected);

		super.inventoryTick(stack, world, entity, slot, selected);
	}

	@Override
	public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
		ChargeableToolItem.appendTooltip(stack, world, tooltip, context);

		super.appendTooltip(stack, world, tooltip, context);
	}

	@Override
	public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(ItemStack stack, EquipmentSlot slot) {
		Multimap<EntityAttribute, EntityAttributeModifier> modifiers = HashMultimap.create();

		if (slot == EquipmentSlot.MAINHAND) {
			ToolMaterial material = stack.getOrCreateNbt().getBoolean("Charged") ? chargedMaterial : baseMaterial;

			modifiers.put(
				EntityAttributes.GENERIC_ATTACK_DAMAGE,
				new EntityAttributeModifier(ATTACK_DAMAGE_MODIFIER_ID, "Weapon modifier",
					(double) material.getAttackDamage() + this.attackDamage, EntityAttributeModifier.Operation.ADDITION)
			);
			
			modifiers.put(
				EntityAttributes.GENERIC_ATTACK_SPEED,
				new EntityAttributeModifier(ATTACK_SPEED_MODIFIER_ID, "Weapon modifier",
					this.attackSpeed, EntityAttributeModifier.Operation.ADDITION)
			);
		}

		return modifiers;
	}
}
