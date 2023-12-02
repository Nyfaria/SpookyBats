package com.nyfaria.spookybats;

import com.nyfaria.spookybats.client.CommonClientClass;
import com.nyfaria.spookybats.client.renderer.SpookyPedestalRenderer;
import com.nyfaria.spookybats.client.renderer.layer.BatWingsLayer;
import com.nyfaria.spookybats.init.BlockInit;
import com.nyfaria.spookybats.registration.RegistryObject;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRendererRegistrationCallback;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Block;

public class SpookyBatsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        CommonClientClass.getRenderers().forEach(
                record -> EntityRendererRegistry.register((EntityType) record.type().get(), record.renderer())
        );

        CommonClientClass.getLayerDefinitions().forEach(
                layerdef -> EntityModelLayerRegistry.registerModelLayer(layerdef.layerLocation(), layerdef::supplier)
        );

        LivingEntityFeatureRendererRegistrationCallback.EVENT.register((entityType, entityRenderer, registrationHelper, context) -> {
            if (entityType == EntityType.PLAYER) {
                registrationHelper.register(new BatWingsLayer<>((PlayerRenderer) entityRenderer, context.getModelSet()));
            }
        });

        BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.SPOOKY_PEDESTAL.get(), RenderType.translucent());
        registerCutout(BlockInit.SPOOKY_OAK.sapling());
        BlockEntityRenderers.register(BlockInit.SPOOKY_PEDESTAL_BLOCK_ENTITY.get(), (a) -> new SpookyPedestalRenderer());
    }

    private static void registerCutout(RegistryObject<? extends Block> block) {
        BlockRenderLayerMap.INSTANCE.putBlock(block.get(), RenderType.cutout());
    }
}
