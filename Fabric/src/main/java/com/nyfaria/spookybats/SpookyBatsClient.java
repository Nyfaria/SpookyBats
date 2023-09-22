package com.nyfaria.spookybats;

import com.nyfaria.spookybats.client.model.SpookyBatModel;
import com.nyfaria.spookybats.client.renderer.CreeperBatRenderer;
import com.nyfaria.spookybats.client.renderer.PumpkinBatRenderer;
import com.nyfaria.spookybats.client.renderer.api.SpookyBatRenderer;
import com.nyfaria.spookybats.init.EntityInit;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.model.geom.builders.CubeDeformation;

public class SpookyBatsClient implements ClientModInitializer {
    
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(EntityInit.PUMPKIN_BAT.get(), PumpkinBatRenderer::new);
        EntityRendererRegistry.register(EntityInit.CREEPER_BAT.get(), CreeperBatRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(SpookyBatModel.LAYER_LOCATION, ()-> SpookyBatModel.createBodyLayer(CubeDeformation.NONE));
        EntityModelLayerRegistry.registerModelLayer(SpookyBatModel.OVERLAY_LOCATION, ()-> SpookyBatModel.createBodyLayer(new CubeDeformation(0.1f)));
    }
}
