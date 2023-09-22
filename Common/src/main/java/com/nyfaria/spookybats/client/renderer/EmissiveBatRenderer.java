package com.nyfaria.spookybats.client.renderer;

import com.nyfaria.spookybats.client.model.SpookyBatModel;
import com.nyfaria.spookybats.client.renderer.api.SpookyBatRenderer;
import com.nyfaria.spookybats.client.renderer.layer.EmissiveBatRenderLayer;
import com.nyfaria.spookybats.entity.SpookyBat;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class EmissiveBatRenderer extends SpookyBatRenderer<SpookyBat> {

    public EmissiveBatRenderer(EntityRendererProvider.Context context, SpookyBatModel<SpookyBat> model, String name) {
        super(context, model);
        this.addLayer(new EmissiveBatRenderLayer<>(this, name));
    }
}
