package com.nyfaria.spookybats.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.nyfaria.spookybats.client.model.SpookyBatModel;
import com.nyfaria.spookybats.entity.api.SpookyBat;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class SculkBatRenderer extends EmissiveBatRenderer {
	public SculkBatRenderer(EntityRendererProvider.Context context, SpookyBatModel<SpookyBat> model, String name) {
		super(context, model, name);
	}

	@Override
	protected void scale(SpookyBat pLivingEntity, PoseStack pMatrixStack, float pPartialTickTime) {
		pMatrixStack.scale(0.6f, 0.6f, 0.6f);
	}
}
