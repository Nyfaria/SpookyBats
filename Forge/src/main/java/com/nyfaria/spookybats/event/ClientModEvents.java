package com.nyfaria.spookybats.event;

import com.nyfaria.spookybats.client.model.SpookyBatModel;
import com.nyfaria.spookybats.client.renderer.CreeperBatRenderer;
import com.nyfaria.spookybats.client.renderer.PumpkinBatRenderer;
import com.nyfaria.spookybats.init.EntityInit;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents {

    @SubscribeEvent
    public static void onEntityRenderers(EntityRenderersEvent.RegisterRenderers event){
        event.registerEntityRenderer(EntityInit.PUMPKIN_BAT.get(), PumpkinBatRenderer::new);
        event.registerEntityRenderer(EntityInit.CREEPER_BAT.get(), CreeperBatRenderer::new);
    }

    @SubscribeEvent
    public static void onEntityRenderLayers(EntityRenderersEvent.RegisterLayerDefinitions event){
        event.registerLayerDefinition(SpookyBatModel.LAYER_LOCATION, ()-> SpookyBatModel.createBodyLayer(CubeDeformation.NONE));
        event.registerLayerDefinition(SpookyBatModel.OVERLAY_LOCATION, ()-> SpookyBatModel.createBodyLayer(new CubeDeformation(0.1f)));
    }
}
