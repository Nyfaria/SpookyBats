package com.nyfaria.spookybats.event;

import com.nyfaria.spookybats.client.model.*;
import com.nyfaria.spookybats.client.renderer.*;
import com.nyfaria.spookybats.client.renderer.api.SpookyBatRenderer;
import com.nyfaria.spookybats.client.renderer.layer.BatWingsLayer;
import com.nyfaria.spookybats.entity.GhostBat;
import com.nyfaria.spookybats.entity.api.SpookyBat;
import com.nyfaria.spookybats.init.EntityInit;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents {

    @SubscribeEvent
    public static void onEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EntityInit.PUMPKIN_BAT.get(), context -> new PumpkinBatRenderer(context, new PumpkinBatModel<>(context.bakeLayer(PumpkinBatModel.LAYER_LOCATION)), "pumpkin_bat"));
        event.registerEntityRenderer(EntityInit.UNDEAD_BAT.get(), context -> new UndeadBatRenderer(context, new UndeadBatModel<>(context.bakeLayer(UndeadBatModel.LAYER_LOCATION))));
        event.registerEntityRenderer(EntityInit.CREEPER_BAT.get(), CreeperBatRenderer::new);
        event.registerEntityRenderer(EntityInit.WITCH_BAT.get(), context -> new SpookyBatRenderer<>(context, new WitchBatModel<>(context.bakeLayer(WitchBatModel.LAYER_LOCATION))));
        event.registerEntityRenderer(EntityInit.SCULK_BAT.get(), context -> new SculkBatRenderer(context, new SculkBatModel<>(context.bakeLayer(SculkBatModel.LAYER_LOCATION)), "sculk_bat"));
        event.registerEntityRenderer(EntityInit.GHOST_BAT.get(), context -> new GhostBatRenderer(context, new GhostBatModel<>(context.bakeLayer(GhostBatModel.LAYER_LOCATION))));
        event.registerEntityRenderer(EntityInit.STEVE_BAT.get(), context -> new SpookyBatRenderer<>(context, new HatBatModel<>(context.bakeLayer(HatBatModel.LAYER_LOCATION))));
        event.registerEntityRenderer(EntityInit.ALEX_BAT.get(), context -> new SpookyBatRenderer<>(context, new HatBatModel<>(context.bakeLayer(HatBatModel.LAYER_LOCATION))));
        event.registerEntityRenderer(EntityInit.HEROBRINE_BAT.get(), context -> new EmissiveBatRenderer(context, new HatBatModel<>(context.bakeLayer(HatBatModel.LAYER_LOCATION)), "herobrine_bat"));
        event.registerEntityRenderer(EntityInit.SKELETON_BAT.get(), context -> new SpookyBatRenderer<>(context, new SkeletonBatModel<>(context.bakeLayer(SkeletonBatModel.LAYER_LOCATION))));
        event.registerEntityRenderer(EntityInit.WITHER_SKELETON_BAT.get(), context -> new SpookyBatRenderer<>(context, new SkeletonBatModel<>(context.bakeLayer(SkeletonBatModel.LAYER_LOCATION))));
        event.registerEntityRenderer(EntityInit.PLAYER_BAT.get(), context -> new PlayerBatRenderer(context, new HatBatModel<>(context.bakeLayer(HatBatModel.LAYER_LOCATION))));
        event.registerEntityRenderer(EntityInit.EVIL_BAT.get(), context -> new SpookyBatRenderer<>(context, new EvilBatModel<>(context.bakeLayer(EvilBatModel.LAYER_LOCATION))));
        event.registerEntityRenderer(EntityInit.VOID_BAT.get(), context -> new VoidBatRenderer(context, new HatBatModel<>(context.bakeLayer(HatBatModel.LAYER_LOCATION))));

        event.registerEntityRenderer(EntityInit.JACK_O_LANTERN_PROJECTILE.get(), context -> new ThrownItemRenderer<>(context, 1.0f, true));
    }

    @SubscribeEvent
    public static void onEntityRenderLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(PumpkinBatModel.LAYER_LOCATION, () -> PumpkinBatModel.createBodyLayer(CubeDeformation.NONE));
        event.registerLayerDefinition(PumpkinBatModel.OVERLAY_LOCATION, () -> PumpkinBatModel.createBodyLayer(new CubeDeformation(0.1f)));
        event.registerLayerDefinition(WitchBatModel.LAYER_LOCATION, WitchBatModel::createBodyLayer);
        event.registerLayerDefinition(HatBatModel.LAYER_LOCATION, HatBatModel::createBodyLayer);
        event.registerLayerDefinition(SkeletonBatModel.LAYER_LOCATION, SkeletonBatModel::createBodyLayer);
        event.registerLayerDefinition(UndeadBatModel.LAYER_LOCATION, UndeadBatModel::createBodyLayer);
        event.registerLayerDefinition(SculkBatModel.LAYER_LOCATION, SculkBatModel::createBodyLayer);
        event.registerLayerDefinition(GhostBatModel.LAYER_LOCATION, GhostBatModel::createBodyLayer);
        event.registerLayerDefinition(EvilBatModel.LAYER_LOCATION, EvilBatModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void onEntityAddLayers(EntityRenderersEvent.AddLayers event) {
        for (String name : event.getSkins()) {
            PlayerRenderer parent = event.getSkin(name);
            parent.addLayer(new BatWingsLayer<>(parent, event.getEntityModels()));
        }
    }
}
