package com.nyfaria.batsgalore.client.renderer;

import com.nyfaria.batsgalore.client.model.SpookyBatModel;
import com.nyfaria.batsgalore.client.renderer.api.SpookyBatRenderer;
import com.nyfaria.batsgalore.client.renderer.layer.EmissiveBatRenderLayer;
import com.nyfaria.batsgalore.entity.api.ModBat;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class EmissiveBatRenderer extends SpookyBatRenderer<ModBat> {

    public EmissiveBatRenderer(EntityRendererProvider.Context context, SpookyBatModel<ModBat> model, String name) {
        super(context, model);
        this.addLayer(new EmissiveBatRenderLayer<>(this, name));
    }
}
