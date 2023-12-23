package com.nyfaria.batsgalore.client.renderer.layer;

import com.nyfaria.batsgalore.Constants;
import com.nyfaria.batsgalore.client.model.ModBatModel;
import com.nyfaria.batsgalore.entity.api.ModBat;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public class EmissiveBatRenderLayer<T extends ModBat, M extends ModBatModel<T>> extends EyesLayer<T, M> {
    private static final Map<String,RenderType> RENDER_TYPE_MAP = new HashMap<>();
    private final String name;

    public EmissiveBatRenderLayer(RenderLayerParent<T, M> $$0, String name) {
        super($$0);
        this.name = name;
    }

    public RenderType renderType() {
        return RENDER_TYPE_MAP.computeIfAbsent(name, n-> RenderType.eyes(new ResourceLocation(Constants.MODID,"textures/entity/"+n+"_emissive.png")));
    }
}
