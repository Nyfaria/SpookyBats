package com.nyfaria.batsgalore.client.renderer;

import com.nyfaria.batsgalore.client.model.SpookyBatModel;
import com.nyfaria.batsgalore.client.renderer.api.SpookyBatRenderer;
import com.nyfaria.batsgalore.client.renderer.layer.BatPlayerHeadLayer;
import com.nyfaria.batsgalore.entity.api.SpookyBat;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class PlayerBatRenderer extends SpookyBatRenderer<SpookyBat> {
    public PlayerBatRenderer(EntityRendererProvider.Context p_173929_, SpookyBatModel<SpookyBat> model) {
        super(p_173929_, model);
        this.addLayer(new BatPlayerHeadLayer(this, p_173929_.getModelSet()));
    }
}
