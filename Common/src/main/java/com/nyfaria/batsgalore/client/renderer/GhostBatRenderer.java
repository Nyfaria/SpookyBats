package com.nyfaria.batsgalore.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.nyfaria.batsgalore.Constants;
import com.nyfaria.batsgalore.client.model.ModBatModel;
import com.nyfaria.batsgalore.client.renderer.api.ModBatRenderer;
import com.nyfaria.batsgalore.entity.api.ModBat;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

public class GhostBatRenderer extends ModBatRenderer<ModBat> {
	public static final ResourceLocation FULL_TEXTURE = new ResourceLocation(Constants.MODID, "textures/entity/ghost_bat_full.png");
	public static final ResourceLocation PARTLY_CHARGED_TEXTURE = new ResourceLocation(Constants.MODID, "textures/entity/ghost_bat_partial.png");
	public static final ResourceLocation EMPTY_TEXTURE = new ResourceLocation(Constants.MODID, "textures/entity/ghost_bat_empty.png");

	public GhostBatRenderer(EntityRendererProvider.Context p_173929_, ModBatModel model) {
		super(p_173929_, model);
	}

	@Override
	public ResourceLocation getTextureLocation(ModBat pEntity) {
		return switch((int) pEntity.getHealth()) {
			case 2 -> FULL_TEXTURE;
			case 1 -> PARTLY_CHARGED_TEXTURE;
			default -> EMPTY_TEXTURE;
		};
	}

	@Nullable
	@Override
	protected RenderType getRenderType(ModBat $$0, boolean $$1, boolean $$2, boolean $$3) {
		return super.getRenderType($$0, $$1, true, $$3);
	}

	@Override
	protected void scale(ModBat pLivingEntity, PoseStack pMatrixStack, float pPartialTickTime) {
		pMatrixStack.scale(0.55f, 0.55f, 0.55f);
	}
}
