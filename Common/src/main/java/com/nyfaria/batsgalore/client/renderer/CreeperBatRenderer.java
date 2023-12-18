package com.nyfaria.batsgalore.client.renderer;

import com.nyfaria.batsgalore.client.model.PumpkinBatModel;
import com.nyfaria.batsgalore.client.renderer.api.SpookyBatRenderer;
import com.nyfaria.batsgalore.client.renderer.layer.BatPowerLayer;
import com.nyfaria.batsgalore.entity.CreeperBat;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class CreeperBatRenderer extends SpookyBatRenderer<CreeperBat> {

    public CreeperBatRenderer(EntityRendererProvider.Context context) {
        super(context, new PumpkinBatModel<>(context.bakeLayer(PumpkinBatModel.LAYER_LOCATION)));
        this.addLayer(new BatPowerLayer(this, context.getModelSet()));
    }
}
