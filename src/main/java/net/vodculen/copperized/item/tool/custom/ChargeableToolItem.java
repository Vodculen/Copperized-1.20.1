package net.vodculen.copperized.item.tool.custom;

import java.util.List;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import net.vodculen.copperized.util.LightningStruckEntity;

public class ChargeableToolItem {
	public static void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
		if (!world.isClient && entity instanceof LightningStruckEntity livingEntity) {
			stack.getOrCreateNbt().putBoolean("Charged", livingEntity.isCharged());
		}

		if (!world.isClient && entity instanceof LivingEntity user && stack.getOrCreateNbt().getBoolean("Charged")) {
			((ServerWorld) world).spawnParticles(ParticleTypes.ELECTRIC_SPARK, user.getX(), user.getY(), user.getZ(), 7, 0.1, 0.5, 0.1, 0.05);
		}
	}

	public static void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
		if (stack.getOrCreateNbt().getBoolean("Charged")) {
			tooltip.add(Text.translatable("text.copperized.charged").formatted(Formatting.YELLOW));
		}
	}
}
