package com.nyfaria.batsgalore.client.renderer;

import com.nyfaria.batsgalore.client.model.ModBatModel;
import com.nyfaria.batsgalore.client.renderer.api.ModBatRenderer;
import com.nyfaria.batsgalore.client.renderer.layer.EmissiveBatRenderLayer;
import com.nyfaria.batsgalore.entity.api.ModBat;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class EmissiveBatRenderer extends ModBatRenderer<ModBat> {

    public EmissiveBatRenderer(EntityRendererProvider.Context context, ModBatModel<ModBat> model, String name) {
        super(context, model);
        this.addLayer(new EmissiveBatRenderLayer<>(this, name));
    }
}
