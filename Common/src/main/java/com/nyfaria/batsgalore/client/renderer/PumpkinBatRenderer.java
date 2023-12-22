package com.nyfaria.batsgalore.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.nyfaria.batsgalore.client.model.SpookyBatModel;
import com.nyfaria.batsgalore.entity.api.ModBat;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class PumpkinBatRenderer extends EmissiveBatRenderer {
	public PumpkinBatRenderer(EntityRendererProvider.Context context, SpookyBatModel<ModBat> model, String name) {
		super(context, model, name);
	}

	@Override
	protected void scale(ModBat pLivingEntity, PoseStack pMatrixStack, float pPartialTickTime) {
		pMatrixStack.pushPose();
		pMatrixStack.scale(1.2f, 1.2f, 1.2f);
		pMatrixStack.popPose();
	}
}
