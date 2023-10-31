package com.nyfaria.spookybats.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.nyfaria.spookybats.Constants;
import com.nyfaria.spookybats.client.model.SpookyBatModel;
import com.nyfaria.spookybats.client.renderer.api.SpookyBatRenderer;
import com.nyfaria.spookybats.entity.api.SpookyBat;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class ShulkerBatRenderer extends SpookyBatRenderer<SpookyBat> {
	public static final ResourceLocation FULL_HEALTH_TEXTURE = new ResourceLocation(Constants.MODID, "textures/entity/undead_bat_full.png");
	public static final ResourceLocation PARTLY_FADED_TEXTURE = new ResourceLocation(Constants.MODID, "textures/entity/undead_bat_part_faded.png");
	public static final ResourceLocation FULLY_FADED_TEXTURE = new ResourceLocation(Constants.MODID, "textures/entity/undead_bat_faded.png");

	public ShulkerBatRenderer(EntityRendererProvider.Context context, SpookyBatModel<SpookyBat> model) {
		super(context, model);
	}

}
