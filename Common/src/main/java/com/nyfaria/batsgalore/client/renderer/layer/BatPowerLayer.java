package com.nyfaria.batsgalore.client.renderer.layer;

import com.nyfaria.batsgalore.client.model.spooky.PumpkinBatModel;
import com.nyfaria.batsgalore.client.model.ModBatModel;
import com.nyfaria.batsgalore.entity.CreeperBat;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EnergySwirlLayer;
import net.minecraft.resources.ResourceLocation;

public class BatPowerLayer extends EnergySwirlLayer<CreeperBat, ModBatModel<CreeperBat>> {
	private static final ResourceLocation POWER_LOCATION = new ResourceLocation("textures/entity/creeper/creeper_armor.png");
	private final ModBatModel<CreeperBat> model;

	public BatPowerLayer(RenderLayerParent<CreeperBat, ModBatModel<CreeperBat>> pRenderer, EntityModelSet pModelSet) {
		super(pRenderer);
		this.model = new ModBatModel<>(pModelSet.bakeLayer(PumpkinBatModel.OVERLAY_LOCATION));
	}

	@Override
	protected float xOffset(float pTickCount) {
		return pTickCount * 0.01F;
	}

	@Override
	protected ResourceLocation getTextureLocation() {
		return POWER_LOCATION;
	}

	@Override
	protected EntityModel<CreeperBat> model() {
		model.root().getChild("body").visible = false;
		model.root().getChild("head").getChild("right_ear").visible = false;
		model.root().getChild("head").getChild("left_ear").visible = false;

		return this.model;
	}
}