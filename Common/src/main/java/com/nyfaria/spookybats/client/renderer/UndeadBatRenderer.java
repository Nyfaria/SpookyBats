package com.nyfaria.spookybats.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.nyfaria.spookybats.Constants;
import com.nyfaria.spookybats.client.model.SpookyBatModel;
import com.nyfaria.spookybats.client.renderer.api.SpookyBatRenderer;
import com.nyfaria.spookybats.entity.api.SpookyBat;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class UndeadBatRenderer extends SpookyBatRenderer<SpookyBat> {
	public static final ResourceLocation FULL_HEALTH_TEXTURE = new ResourceLocation(Constants.MODID, "textures/entity/undead_bat_full.png");
	public static final ResourceLocation PARTLY_FADED_TEXTURE = new ResourceLocation(Constants.MODID, "textures/entity/undead_bat_part_faded.png");
	public static final ResourceLocation FULLY_FADED_TEXTURE = new ResourceLocation(Constants.MODID, "textures/entity/undead_bat_faded.png");

	public UndeadBatRenderer(EntityRendererProvider.Context context, SpookyBatModel<SpookyBat> model) {
		super(context, model);
	}

	@Override
	protected void scale(SpookyBat pLivingEntity, PoseStack pMatrixStack, float pPartialTickTime) {
		pMatrixStack.pushPose();
		pMatrixStack.scale(1.2f, 1.2f, 1.2f);
		pMatrixStack.popPose();
	}

	@Override
	public ResourceLocation getTextureLocation(SpookyBat bat) {
		return switch((int) (bat.getHealth())) {
			case 6, 5 -> FULL_HEALTH_TEXTURE;
			case 4, 3, 2 -> PARTLY_FADED_TEXTURE;
			default -> FULLY_FADED_TEXTURE;
		};
	}
}
