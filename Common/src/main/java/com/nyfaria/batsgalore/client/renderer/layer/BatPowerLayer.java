package com.nyfaria.batsgalore.client.renderer.layer;

import com.nyfaria.batsgalore.client.model.ModBatModel;
import com.nyfaria.batsgalore.client.model.spooky.SpookyBatLayers;
import com.nyfaria.batsgalore.entity.CreeperBat;
import com.nyfaria.batsgalore.entity.api.ModBat;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EnergySwirlLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.PowerableMob;

public class BatPowerLayer<T extends ModBat & PowerableMob> extends EnergySwirlLayer<T, ModBatModel<T>> {
	private static final ResourceLocation POWER_LOCATION = new ResourceLocation("textures/entity/creeper/creeper_armor.png");
	private final ModBatModel<T> model;

	public BatPowerLayer(RenderLayerParent<T, ModBatModel<T>> pRenderer, EntityModelSet pModelSet) {
		super(pRenderer);
		this.model = new ModBatModel<>(pModelSet.bakeLayer(SpookyBatLayers.BAT_OVERLAY_LOCATION));
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
	protected EntityModel<T> model() {
		model.root().getChild("body").visible = false;
		if(model.getHead().hasChild("right_ear")) {
			model.getHead().getChild("right_ear").visible = false;
		}
		if(model.getHead().hasChild("left_ear")) {
			model.getHead().getChild("left_ear").visible = false;
		}
		return this.model;
	}
}