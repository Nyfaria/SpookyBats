package com.nyfaria.spookybats.client.renderer.layer;

import com.nyfaria.spookybats.Constants;
import com.nyfaria.spookybats.client.model.SpookyBatModel;
import com.nyfaria.spookybats.entity.api.SpookyBat;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public class EmissiveBatRenderLayer<T extends SpookyBat, M extends SpookyBatModel<T>> extends EyesLayer<T, M> {
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
