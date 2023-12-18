package com.nyfaria.batsgalore.client.renderer;

import com.nyfaria.batsgalore.client.model.SpookyBatModel;
import com.nyfaria.batsgalore.client.renderer.api.SpookyBatRenderer;
import com.nyfaria.batsgalore.client.renderer.layer.EmissiveBatRenderLayer;
import com.nyfaria.batsgalore.entity.api.SpookyBat;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class EmissiveBatRenderer extends SpookyBatRenderer<SpookyBat> {

    public EmissiveBatRenderer(EntityRendererProvider.Context context, SpookyBatModel<SpookyBat> model, String name) {
        super(context, model);
        this.addLayer(new EmissiveBatRenderLayer<>(this, name));
    }
}
