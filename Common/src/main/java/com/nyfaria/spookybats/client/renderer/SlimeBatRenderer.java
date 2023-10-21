package com.nyfaria.spookybats.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.nyfaria.spookybats.client.model.SpookyBatModel;
import com.nyfaria.spookybats.client.renderer.api.SpookyBatRenderer;
import com.nyfaria.spookybats.entity.api.SpookyBat;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import org.jetbrains.annotations.Nullable;

public class SlimeBatRenderer extends SpookyBatRenderer<SpookyBat> {
	public SlimeBatRenderer(EntityRendererProvider.Context p_173929_, SpookyBatModel<SpookyBat> model) {
		super(p_173929_, model);
	}

	@Nullable
	@Override
	protected RenderType getRenderType(SpookyBat $$0, boolean $$1, boolean $$2, boolean $$3) {
		return super.getRenderType($$0, $$1, true, $$3);
	}

	@Override
	protected void scale(SpookyBat pLivingEntity, PoseStack pMatrixStack, float pPartialTickTime) {
		pMatrixStack.scale(0.7f, 0.7f, 0.7f);
	}
}
