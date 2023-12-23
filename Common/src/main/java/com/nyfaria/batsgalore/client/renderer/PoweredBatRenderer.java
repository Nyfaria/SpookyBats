package com.nyfaria.batsgalore.client.renderer;

import com.nyfaria.batsgalore.client.model.ModBatModel;
import com.nyfaria.batsgalore.client.renderer.api.ModBatRenderer;
import com.nyfaria.batsgalore.client.renderer.layer.BatPowerLayer;
import com.nyfaria.batsgalore.entity.api.ModBat;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.PowerableMob;

public class PoweredBatRenderer<T extends ModBat & PowerableMob> extends ModBatRenderer<T> {

    public PoweredBatRenderer(EntityRendererProvider.Context context, ModBatModel<T> model) {
        super(context, model);
        this.addLayer(new BatPowerLayer(this, context.getModelSet()));
    }
}
