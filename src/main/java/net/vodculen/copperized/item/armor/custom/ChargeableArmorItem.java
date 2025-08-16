package net.vodculen.copperized.item.armor.custom;

import java.util.List;
import java.util.UUID;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import net.vodculen.copperized.util.LightningStruckEntity;

public class ChargeableArmorItem extends ArmorItem {
	private final ArmorMaterial baseMaterial;
	private final ArmorMaterial chargedMaterial;
	private final Type type;

	public ChargeableArmorItem(ArmorMaterial baseMaterial, ArmorMaterial chargedMaterial, Type type, Settings settings) {
		super(baseMaterial, type, settings);

		this.baseMaterial = baseMaterial;
		this.chargedMaterial = chargedMaterial;
		this.type = type;
	}

	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
		if (!world.isClient && entity instanceof LightningStruckEntity livingEntity) {
			stack.getOrCreateNbt().putBoolean("Charged", livingEntity.isCharged());
		}

		if (!world.isClient && entity instanceof LivingEntity user && stack.getOrCreateNbt().getBoolean("Charged")) {
			((ServerWorld) world).spawnParticles(ParticleTypes.ELECTRIC_SPARK, user.getX(), user.getY(), user.getZ(), 7, 0.1, 0.5, 0.1, 0.05);
		}
	}

	@Override
	public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
		if (stack.getOrCreateNbt().getBoolean("Charged")) {
			tooltip.add(Text.translatable("text.copperized.charged").formatted(Formatting.YELLOW));
		}
	}

	@Override
	public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(ItemStack stack, EquipmentSlot slot) {
		Multimap<EntityAttribute, EntityAttributeModifier> modifiers = HashMultimap.create();

		if (slot == EquipmentSlot.MAINHAND) {
			ArmorMaterial material = stack.getOrCreateNbt().getBoolean("Charged") ? chargedMaterial : baseMaterial;

			modifiers.put(
				EntityAttributes.GENERIC_ARMOR,
				new EntityAttributeModifier(UUID.fromString("FA2D3E1C-4380-AC65-B01B-BC5E9785A1A3"), "Weapon modifier",
					(double) material.getProtection(type), EntityAttributeModifier.Operation.ADDITION)
			);

			modifiers.put(
				EntityAttributes.GENERIC_ATTACK_SPEED,
				new EntityAttributeModifier(UUID.fromString("FA2FF31C-4C80-1C6D-D01F-B4AE978BA1A3"), "Weapon modifier",
					material.getToughness(), EntityAttributeModifier.Operation.ADDITION)
			);
		}

		return modifiers;
	}
}
