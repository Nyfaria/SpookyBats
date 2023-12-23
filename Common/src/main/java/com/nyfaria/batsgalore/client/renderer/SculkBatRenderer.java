package com.nyfaria.batsgalore.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.nyfaria.batsgalore.client.model.ModBatModel;
import com.nyfaria.batsgalore.entity.api.ModBat;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class SculkBatRenderer extends EmissiveBatRenderer {
	public SculkBatRenderer(EntityRendererProvider.Context context, ModBatModel<ModBat> model, String name) {
		super(context, model, name);
	}

	@Override
	protected void scale(ModBat pLivingEntity, PoseStack pMatrixStack, float pPartialTickTime) {
		pMatrixStack.scale(0.6f, 0.6f, 0.6f);
	}
}
