package com.nyfaria.spookybats.event;

import com.nyfaria.spookybats.client.model.PumpkinBatModel;
import com.nyfaria.spookybats.client.model.SpookyBatModel;
import com.nyfaria.spookybats.client.model.WitchBatModel;
import com.nyfaria.spookybats.client.renderer.CreeperBatRenderer;
import com.nyfaria.spookybats.client.renderer.PumpkinBatRenderer;
import com.nyfaria.spookybats.client.renderer.api.SpookyBatRenderer;
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
        event.registerEntityRenderer(EntityInit.WITCH_BAT.get(), context->new SpookyBatRenderer<>(context, new WitchBatModel<>(context.bakeLayer(WitchBatModel.LAYER_LOCATION))));
    }

    @SubscribeEvent
    public static void onEntityRenderLayers(EntityRenderersEvent.RegisterLayerDefinitions event){
        event.registerLayerDefinition(PumpkinBatModel.LAYER_LOCATION, ()-> PumpkinBatModel.createBodyLayer(CubeDeformation.NONE));
        event.registerLayerDefinition(PumpkinBatModel.OVERLAY_LOCATION, ()-> PumpkinBatModel.createBodyLayer(new CubeDeformation(0.1f)));
        event.registerLayerDefinition(WitchBatModel.LAYER_LOCATION, WitchBatModel::createBodyLayer);
    }
}
