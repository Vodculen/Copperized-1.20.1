package net.vodculen.copperized.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;
import net.vodculen.copperized.renderer.LightningRodFeatureRenderer;


@Mixin(LivingEntityRenderer.class)
public abstract class LivingEntityRendererMixin<T extends LivingEntity, M extends EntityModel<T>> {

	@Invoker("addFeature")
	protected abstract boolean invokeAddFeature(FeatureRenderer<T, M> feature);

	@Inject(method = "<init>", at = @At("RETURN"))
	private void onInit(EntityRendererFactory.Context context, M model, float shadowRadius, CallbackInfo ci) {
		@SuppressWarnings("unchecked")
		LivingEntityRenderer<T, M> self = (LivingEntityRenderer<T, M>) (Object) this;
		this.invokeAddFeature(new LightningRodFeatureRenderer<T, M>((FeatureRendererContext<T, M>) self));
	}
}
