package com.nyfaria.spookybats;

import com.nyfaria.spookybats.client.CommonClientClass;
import com.nyfaria.spookybats.client.model.*;
import com.nyfaria.spookybats.client.renderer.*;
import com.nyfaria.spookybats.client.renderer.api.SpookyBatRenderer;
import com.nyfaria.spookybats.client.renderer.layer.BatWingsLayer;
import com.nyfaria.spookybats.init.EntityInit;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRendererRegistrationCallback;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.world.entity.EntityType;

public class SpookyBatsClient implements ClientModInitializer {
    
    @Override
    public void onInitializeClient() {
        CommonClientClass.getRenderers().forEach(
                record -> {
                    EntityRendererRegistry.register((EntityType) record.type().get(), record.renderer());
                }
        );
        CommonClientClass.getLayerDefinitions().forEach(
                layerdef -> EntityModelLayerRegistry.registerModelLayer(layerdef.layerLocation(), layerdef::supplier)
        );


        LivingEntityFeatureRendererRegistrationCallback.EVENT.register((entityType, entityRenderer, registrationHelper, context) -> {
            if (entityType == EntityType.PLAYER) {
                registrationHelper.register(new BatWingsLayer<>((PlayerRenderer)entityRenderer, context.getModelSet()));
            }
        });
    }
}
