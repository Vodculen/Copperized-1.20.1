package net.vodculen.copperized.item.tool.custom;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableMultimap.Builder;
import com.google.common.collect.Multimap;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Vanishable;
import net.minecraft.world.World;


public class LightningItem extends Item implements Vanishable {
	private final Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;
	private boolean cooldownFinished = true;

	public LightningItem(Settings settings, double damage, double speed) {
		super(settings);
		
		Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
		builder.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(ATTACK_DAMAGE_MODIFIER_ID, "Weapon modifier", damage, EntityAttributeModifier.Operation.ADDITION));
		builder.put(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(ATTACK_SPEED_MODIFIER_ID, "Weapon modifier", speed, EntityAttributeModifier.Operation.ADDITION));
		this.attributeModifiers = builder.build();
	}

	@Override
	public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		World world = attacker.getWorld();

		if (!world.isClient && (world.isThundering() || world.isRaining()) && cooldownFinished) {
			LightningEntity lightningBolt = new LightningEntity(EntityType.LIGHTNING_BOLT, world);

			lightningBolt.setPosition(target.getBlockPos().toCenterPos());

			world.spawnEntity(lightningBolt);
		}

		stack.damage(1, attacker, e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));

		return super.postHit(stack, target, attacker);
	}

	@Override
	public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
		return slot == EquipmentSlot.MAINHAND ? this.attributeModifiers : super.getAttributeModifiers(slot);
	}

	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
		if (entity instanceof PlayerEntity player) {
			if (player.getAttackCooldownProgress(1.0F) > 0.9F) {
				cooldownFinished = true;
			} else {
				cooldownFinished = false;
			}
		}
		
		super.inventoryTick(stack, world, entity, slot, selected);
	}
	
}
