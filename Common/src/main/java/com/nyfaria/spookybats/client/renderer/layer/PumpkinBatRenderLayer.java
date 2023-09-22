package com.nyfaria.spookybats.client.renderer.layer;

import com.nyfaria.spookybats.Constants;
import com.nyfaria.spookybats.client.model.SpookyBatModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ambient.Bat;

public class PumpkinBatRenderLayer<T extends Bat, M extends SpookyBatModel<T>> extends EyesLayer<T, M> {
    private static final RenderType SPIDER_EYES = RenderType.eyes(new ResourceLocation(Constants.MODID,"textures/entity/pumpkin_bat_head.png"));

    public PumpkinBatRenderLayer(RenderLayerParent<T, M> $$0) {
        super($$0);
    }

    public RenderType renderType() {
        return SPIDER_EYES;
    }
}
