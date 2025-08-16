package net.vodculen.copperized.renderer;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.vodculen.copperized.item.ModItems;

public class LightningRodFeatureRenderer<T extends LivingEntity, M extends EntityModel<T>> extends FeatureRenderer<T, M> {
    public LightningRodFeatureRenderer(FeatureRendererContext<T, M> context) {
        super(context);
    }


	@Override
	public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, T entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
		ItemStack headStack = entity.getEquippedStack(EquipmentSlot.HEAD);

		if (!headStack.isOf(ModItems.CONDUCTIBLE_COPPER_HELMET)) {
			return;
		}

		matrices.push();

		// Safely rotate the head if the model is a BipedEntityModel
		if (getContextModel() instanceof BipedEntityModel<?> bipedModel) {
			bipedModel.head.rotate(matrices);
		}

		// Note: Y: Up/down; X: left/right; Z: front/back
		matrices.translate(-0.5, -0.75, 0);

		MinecraftClient.getInstance().getItemRenderer().renderItem(
				new ItemStack(Items.LIGHTNING_ROD),
				ModelTransformationMode.HEAD,
				light,
				OverlayTexture.DEFAULT_UV,
				matrices,
				vertexConsumers,
				entity.getWorld(),
				entity.getId()
		);

		matrices.pop();
	}
}
