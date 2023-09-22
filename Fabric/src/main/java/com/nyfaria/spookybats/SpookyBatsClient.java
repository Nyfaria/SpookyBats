package com.nyfaria.spookybats;

import com.nyfaria.spookybats.client.model.PumpkinBatModel;
import com.nyfaria.spookybats.client.model.SpookyBatModel;
import com.nyfaria.spookybats.client.model.WitchBatModel;
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
        EntityRendererRegistry.register(EntityInit.WITCH_BAT.get(), context->new SpookyBatRenderer<>(context, new WitchBatModel<>(context.bakeLayer(WitchBatModel.LAYER_LOCATION))));
        EntityModelLayerRegistry.registerModelLayer(PumpkinBatModel.LAYER_LOCATION, ()-> PumpkinBatModel.createBodyLayer(CubeDeformation.NONE));
        EntityModelLayerRegistry.registerModelLayer(WitchBatModel.LAYER_LOCATION, WitchBatModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(PumpkinBatModel.OVERLAY_LOCATION, ()-> PumpkinBatModel.createBodyLayer(new CubeDeformation(0.1f)));
    }
}
