package com.nyfaria.spookybats.client.renderer;

import com.nyfaria.spookybats.client.model.PumpkinBatModel;
import com.nyfaria.spookybats.client.renderer.api.SpookyBatRenderer;
import com.nyfaria.spookybats.client.renderer.layer.BatPowerLayer;
import com.nyfaria.spookybats.client.renderer.layer.PumpkinBatRenderLayer;
import com.nyfaria.spookybats.entity.CreeperBat;
import com.nyfaria.spookybats.entity.SpookyBat;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class PumpkinBatRenderer extends SpookyBatRenderer<SpookyBat> {

    public PumpkinBatRenderer(EntityRendererProvider.Context context) {
        super(context, new PumpkinBatModel<>(context.bakeLayer(PumpkinBatModel.LAYER_LOCATION)));
        this.addLayer(new PumpkinBatRenderLayer<>(this));
    }
}
