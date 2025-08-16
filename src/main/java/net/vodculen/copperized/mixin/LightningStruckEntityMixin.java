package net.vodculen.copperized.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.Entity;
import net.vodculen.copperized.util.LightningStruckEntity;
import net.vodculen.copperized.util.Ticks;


@Mixin(Entity.class)
public abstract class LightningStruckEntityMixin implements LightningStruckEntity {
	@Unique
	private int chargedTicks = 0;

	@Inject(method = "onStruckByLightning", at = @At("HEAD"))
	private void charged(CallbackInfo callbackInfo) {
		this.setChargedTicks(this.getChargedTicks() + Ticks.getSeconds(30));
	}

	@Inject(method = "tick", at = @At("HEAD"))
	private void chargingTicks(CallbackInfo callbackInfo) {
		if (chargedTicks > 0) {
			chargedTicks--;
		}
	}

	public boolean isCharged() {
		return chargedTicks > 0;
	}

	public void setChargedTicks(int chargedTicks) {
		this.chargedTicks = chargedTicks;
	}

	public int getChargedTicks() {
		return chargedTicks;
	}
}
