package com.nyfaria.spookybats.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.nyfaria.spookybats.client.model.SpookyBatModel;
import com.nyfaria.spookybats.client.model.WitchBatModel;
import com.nyfaria.spookybats.client.renderer.api.SpookyBatRenderer;
import com.nyfaria.spookybats.entity.WitchBat;
import com.nyfaria.spookybats.entity.api.SpookyBat;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.monster.Witch;

public class WitchBatRenderer extends SpookyBatRenderer<WitchBat> {
	public WitchBatRenderer(EntityRendererProvider.Context context, WitchBatModel<WitchBat> model) {
		super(context, model);
	}

	@Override
	protected void scale(WitchBat pLivingEntity, PoseStack pMatrixStack, float pPartialTickTime) {
		pMatrixStack.pushPose();
		pMatrixStack.scale(0.45f, 0.45f, 0.45f);
		pMatrixStack.popPose();
	}
}
