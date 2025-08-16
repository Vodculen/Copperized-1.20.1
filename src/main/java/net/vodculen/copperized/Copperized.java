package net.vodculen.copperized;

import net.fabricmc.api.ModInitializer;
import net.vodculen.copperized.item.ModItems;
import net.vodculen.copperized.util.LightningAttraction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Copperized implements ModInitializer {
	public static final String MOD_ID = "copperized";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		LightningAttraction.register();
	}
}