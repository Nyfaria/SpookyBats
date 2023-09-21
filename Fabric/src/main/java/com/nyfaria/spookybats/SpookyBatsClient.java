package com.nyfaria.spookybats;

import com.nyfaria.spookybats.client.model.PumpkinBat;
import com.nyfaria.spookybats.client.renderer.PumpkinBatRenderer;
import com.nyfaria.spookybats.init.EntityInit;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class SpookyBatsClient implements ClientModInitializer {
    
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(EntityInit.PUMPKIN_BAT.get(), PumpkinBatRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(PumpkinBat.LAYER_LOCATION, PumpkinBat::createBodyLayer);
    }
}
