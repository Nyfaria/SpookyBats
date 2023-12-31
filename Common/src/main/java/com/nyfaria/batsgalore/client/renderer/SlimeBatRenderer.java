package com.nyfaria.batsgalore.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.nyfaria.batsgalore.client.model.ModBatModel;
import com.nyfaria.batsgalore.client.renderer.api.ModBatRenderer;
import com.nyfaria.batsgalore.entity.api.ModBat;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import org.jetbrains.annotations.Nullable;

public class SlimeBatRenderer extends ModBatRenderer<ModBat> {
	public SlimeBatRenderer(EntityRendererProvider.Context p_173929_, ModBatModel<ModBat> model) {
		super(p_173929_, model);
	}

	@Nullable
	@Override
	protected RenderType getRenderType(ModBat $$0, boolean $$1, boolean $$2, boolean $$3) {
		return super.getRenderType($$0, $$1, true, $$3);
	}

	@Override
	protected void scale(ModBat pLivingEntity, PoseStack pMatrixStack, float pPartialTickTime) {
		pMatrixStack.scale(0.7f, 0.7f, 0.7f);
	}
}
