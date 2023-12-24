package com.nyfaria.batsgalore.event;

import com.nyfaria.batsgalore.client.CommonClientClass;
import com.nyfaria.batsgalore.client.renderer.SpookyPedestalRenderer;
import com.nyfaria.batsgalore.client.renderer.StatueBlockEntityRenderer;
import com.nyfaria.batsgalore.client.renderer.layer.BatWingsLayer;
import com.nyfaria.batsgalore.client.renderer.layer.CosmeticsLayer;
import com.nyfaria.batsgalore.init.BlockInit;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents {

    @SubscribeEvent
    public static void onEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        CommonClientClass.getRenderers().forEach(
                record -> event.registerEntityRenderer((EntityType) record.type().get(), record.renderer())
        );
        event.registerBlockEntityRenderer(BlockInit.SPOOKY_PEDESTAL_BLOCK_ENTITY.get(), (a)->new SpookyPedestalRenderer());
        event.registerBlockEntityRenderer(BlockInit.STATUE_BLOCK_ENTITY.get(), StatueBlockEntityRenderer::new);
    }

    @SubscribeEvent
    public static void onEntityRenderLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        CommonClientClass.getLayerDefinitions().forEach(
                layerdef -> event.registerLayerDefinition(layerdef.layerLocation(), layerdef::supplier)
        );
    }

    @SubscribeEvent
    public static void onEntityAddLayers(EntityRenderersEvent.AddLayers event) {
        for (String name : event.getSkins()) {
            PlayerRenderer parent = event.getSkin(name);
            parent.addLayer(new BatWingsLayer<>(parent, event.getEntityModels()));
            parent.addLayer(new CosmeticsLayer<>(parent, event.getEntityModels()));
        }
    }
}
