package com.nyfaria.spookybats;

import com.nyfaria.spookybats.client.model.*;
import com.nyfaria.spookybats.client.renderer.CreeperBatRenderer;
import com.nyfaria.spookybats.client.renderer.EmissiveBatRenderer;
import com.nyfaria.spookybats.client.renderer.PlayerBatRenderer;
import com.nyfaria.spookybats.client.renderer.api.SpookyBatRenderer;
import com.nyfaria.spookybats.init.EntityInit;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.model.geom.builders.CubeDeformation;

public class SpookyBatsClient implements ClientModInitializer {
    
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(EntityInit.PUMPKIN_BAT.get(), context->new EmissiveBatRenderer(context, new PumpkinBatModel<>(context.bakeLayer(PumpkinBatModel.LAYER_LOCATION)), "pumpkin_bat"));
        EntityRendererRegistry.register(EntityInit.CREEPER_BAT.get(), CreeperBatRenderer::new);
        EntityRendererRegistry.register(EntityInit.WITCH_BAT.get(), context->new SpookyBatRenderer<>(context, new WitchBatModel<>(context.bakeLayer(WitchBatModel.LAYER_LOCATION))));
        EntityRendererRegistry.register(EntityInit.STEVE_BAT.get(), context->new SpookyBatRenderer<>(context, new HatBatModel<>(context.bakeLayer(HatBatModel.LAYER_LOCATION))));
        EntityRendererRegistry.register(EntityInit.ALEX_BAT.get(), context->new SpookyBatRenderer<>(context, new HatBatModel<>(context.bakeLayer(HatBatModel.LAYER_LOCATION))));
        EntityRendererRegistry.register(EntityInit.HEROBRINE_BAT.get(), context->new EmissiveBatRenderer(context, new HatBatModel<>(context.bakeLayer(HatBatModel.LAYER_LOCATION)),"herobrine_bat"));
        EntityRendererRegistry.register(EntityInit.SKELETON_BAT.get(), context->new SpookyBatRenderer<>(context, new SkeletonBatModel<>(context.bakeLayer(SkeletonBatModel.LAYER_LOCATION))));
        EntityRendererRegistry.register(EntityInit.UNDEAD_BAT.get(), context->new SpookyBatRenderer<>(context, new UndeadBatModel<>(context.bakeLayer(UndeadBatModel.LAYER_LOCATION))));
        EntityRendererRegistry.register(EntityInit.PLAYER_BAT.get(), context->new PlayerBatRenderer(context, new HatBatModel<>(context.bakeLayer(HatBatModel.LAYER_LOCATION))));

        EntityModelLayerRegistry.registerModelLayer(PumpkinBatModel.LAYER_LOCATION, ()-> PumpkinBatModel.createBodyLayer(CubeDeformation.NONE));
        EntityModelLayerRegistry.registerModelLayer(WitchBatModel.LAYER_LOCATION, WitchBatModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(SkeletonBatModel.LAYER_LOCATION, SkeletonBatModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(HatBatModel.LAYER_LOCATION, HatBatModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(UndeadBatModel.LAYER_LOCATION, UndeadBatModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(PumpkinBatModel.OVERLAY_LOCATION, ()-> PumpkinBatModel.createBodyLayer(new CubeDeformation(0.1f)));
    }
}
