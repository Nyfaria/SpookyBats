package com.nyfaria.batsgalore.client.renderer;

import com.nyfaria.batsgalore.client.model.SpookyBatModel;
import com.nyfaria.batsgalore.client.renderer.api.SpookyBatRenderer;
import com.nyfaria.batsgalore.client.renderer.layer.BatPlayerHeadLayer;
import com.nyfaria.batsgalore.entity.api.ModBat;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class PlayerBatRenderer extends SpookyBatRenderer<ModBat> {
    public PlayerBatRenderer(EntityRendererProvider.Context p_173929_, SpookyBatModel<ModBat> model) {
        super(p_173929_, model);
        this.addLayer(new BatPlayerHeadLayer(this, p_173929_.getModelSet()));
    }
}
