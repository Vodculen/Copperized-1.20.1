package net.vodculen.copperized.util;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;

public class LightningAttraction {
	public static void register() {
		ServerTickEvents.END_SERVER_TICK.register(server -> {
			for (ServerWorld world : server.getWorlds()) {
				if (world.isThundering()) {
					Iterable<Entity> entities = world.iterateEntities();

					for (Entity entity : entities) {
						if (entity instanceof LivingEntity livingEntity && world.isSkyVisible(livingEntity.getBlockPos())) {
							getConductibility(livingEntity, world);
						}
					}
				}
			}
		});
	}

	private static void getConductibility(LivingEntity entity, ServerWorld world) {
		int copperArmorCount = 0;

		for (ItemStack armorStack : entity.getArmorItems()) {
			if (isCopperArmor(armorStack.getItem())) {
				copperArmorCount++;
			} else if (isConductibleCopperHelmet(armorStack.getItem())) {
				copperArmorCount += 15;
			}
		}

		if (copperArmorCount > 0) {
			double chance = 0.001 * copperArmorCount;

			if (Math.random() < chance) {
				summonLightning(entity, world);
			}
		}
	}

	private static boolean isCopperArmor(Item item) {
		Identifier copperArmor = Registries.ITEM.getId(item);

		return copperArmor != null && copperArmor.getPath().contains("copper");
	}

	private static boolean isConductibleCopperHelmet(Item item) {
		Identifier copperArmor = Registries.ITEM.getId(item);

		return copperArmor != null && copperArmor.getPath().contains("conductible");
	}

	private static void summonLightning(LivingEntity livingEntity, ServerWorld world) {
		LightningEntity lightningBolt = new LightningEntity(EntityType.LIGHTNING_BOLT, world);

		lightningBolt.setPosition(livingEntity.getBlockPos().toCenterPos());

		world.spawnEntity(lightningBolt);
	}
}
