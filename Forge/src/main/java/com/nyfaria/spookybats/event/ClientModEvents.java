package com.nyfaria.spookybats.event;

import com.nyfaria.spookybats.client.model.*;
import com.nyfaria.spookybats.client.renderer.*;
import com.nyfaria.spookybats.client.renderer.api.SpookyBatRenderer;
import com.nyfaria.spookybats.client.renderer.layer.BatWingsLayer;
import com.nyfaria.spookybats.init.EntityInit;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents {

    @SubscribeEvent
    public static void onEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EntityInit.PUMPKIN_BAT.get(), context -> new MascotBatRenderer(context, new PumpkinBatModel<>(context.bakeLayer(PumpkinBatModel.LAYER_LOCATION)), "pumpkin_bat"));
        event.registerEntityRenderer(EntityInit.UNDEAD_BAT.get(), context -> new MascotBatRenderer(context, new UndeadBatModel<>(context.bakeLayer(UndeadBatModel.LAYER_LOCATION)), "undead_bat"));
        event.registerEntityRenderer(EntityInit.CREEPER_BAT.get(), CreeperBatRenderer::new);
        event.registerEntityRenderer(EntityInit.WITCH_BAT.get(), context -> new SpookyBatRenderer<>(context, new WitchBatModel<>(context.bakeLayer(WitchBatModel.LAYER_LOCATION))));
        event.registerEntityRenderer(EntityInit.STEVE_BAT.get(), context -> new SpookyBatRenderer<>(context, new HatBatModel<>(context.bakeLayer(HatBatModel.LAYER_LOCATION))));
        event.registerEntityRenderer(EntityInit.ALEX_BAT.get(), context -> new SpookyBatRenderer<>(context, new HatBatModel<>(context.bakeLayer(HatBatModel.LAYER_LOCATION))));
        event.registerEntityRenderer(EntityInit.HEROBRINE_BAT.get(), context -> new EmissiveBatRenderer(context, new HerobrineBatModel<>(context.bakeLayer(HerobrineBatModel.LAYER_LOCATION)), "herobrine_bat"));
        event.registerEntityRenderer(EntityInit.SKELETON_BAT.get(), context -> new SpookyBatRenderer<>(context, new SkeletonBatModel<>(context.bakeLayer(SkeletonBatModel.LAYER_LOCATION))));
        event.registerEntityRenderer(EntityInit.WITHER_SKELETON_BAT.get(), context -> new SpookyBatRenderer<>(context, new SkeletonBatModel<>(context.bakeLayer(SkeletonBatModel.LAYER_LOCATION))));
        event.registerEntityRenderer(EntityInit.PLAYER_BAT.get(), context -> new PlayerBatRenderer(context, new HatBatModel<>(context.bakeLayer(HatBatModel.LAYER_LOCATION))));
        event.registerEntityRenderer(EntityInit.VOID_BAT.get(), context -> new VoidBatRenderer(context, new HatBatModel<>(context.bakeLayer(HatBatModel.LAYER_LOCATION))));

    }

    @SubscribeEvent
    public static void onEntityRenderLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(PumpkinBatModel.LAYER_LOCATION, () -> PumpkinBatModel.createBodyLayer(CubeDeformation.NONE));
        event.registerLayerDefinition(PumpkinBatModel.OVERLAY_LOCATION, () -> PumpkinBatModel.createBodyLayer(new CubeDeformation(0.1f)));
        event.registerLayerDefinition(WitchBatModel.LAYER_LOCATION, WitchBatModel::createBodyLayer);
        event.registerLayerDefinition(HatBatModel.LAYER_LOCATION, HatBatModel::createBodyLayer);
        event.registerLayerDefinition(SkeletonBatModel.LAYER_LOCATION, SkeletonBatModel::createBodyLayer);
        event.registerLayerDefinition(UndeadBatModel.LAYER_LOCATION, UndeadBatModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void onEntityAddLayers(EntityRenderersEvent.AddLayers event) {
        for (String name : event.getSkins()) {
            PlayerRenderer parent = event.getSkin(name);
            parent.addLayer(new BatWingsLayer<>(parent, event.getEntityModels()));
        }
    }
}
