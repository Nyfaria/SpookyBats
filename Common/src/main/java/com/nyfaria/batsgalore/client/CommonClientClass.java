package com.nyfaria.batsgalore.client;

import com.nyfaria.batsgalore.client.model.EvilBatModel;
import com.nyfaria.batsgalore.client.model.ExperienceOrbBatModel;
import com.nyfaria.batsgalore.client.model.GhostBatModel;
import com.nyfaria.batsgalore.client.model.HatBatModel;
import com.nyfaria.batsgalore.client.model.ModBoatModel;
import com.nyfaria.batsgalore.client.model.ModChestBoatModel;
import com.nyfaria.batsgalore.client.model.PumpkinBatModel;
import com.nyfaria.batsgalore.client.model.SculkBatModel;
import com.nyfaria.batsgalore.client.model.ShulkerBatModel;
import com.nyfaria.batsgalore.client.model.SkeletonBatModel;
import com.nyfaria.batsgalore.client.model.SlimeBatModel;
import com.nyfaria.batsgalore.client.model.SpookyOakHangingSignModel;
import com.nyfaria.batsgalore.client.model.SpookyOakSignModel;
import com.nyfaria.batsgalore.client.model.UndeadBatModel;
import com.nyfaria.batsgalore.client.model.WingedTurmoilModel;
import com.nyfaria.batsgalore.client.model.WitchBatModel;
import com.nyfaria.batsgalore.client.model.WitchsBroomModel;
import com.nyfaria.batsgalore.client.renderer.BlockProjectileRenderer;
import com.nyfaria.batsgalore.client.renderer.CreeperBatRenderer;
import com.nyfaria.batsgalore.client.renderer.EmissiveBatRenderer;
import com.nyfaria.batsgalore.client.renderer.GhostBatRenderer;
import com.nyfaria.batsgalore.client.renderer.ModBoatRenderer;
import com.nyfaria.batsgalore.client.renderer.PlayerBatRenderer;
import com.nyfaria.batsgalore.client.renderer.PumpkinBatRenderer;
import com.nyfaria.batsgalore.client.renderer.SculkBatRenderer;
import com.nyfaria.batsgalore.client.renderer.SlimeBatRenderer;
import com.nyfaria.batsgalore.client.renderer.UndeadBatRenderer;
import com.nyfaria.batsgalore.client.renderer.VoidBatRenderer;
import com.nyfaria.batsgalore.client.renderer.WitchsBroomRenderer;
import com.nyfaria.batsgalore.client.renderer.api.SpookyBatRenderer;
import com.nyfaria.batsgalore.entity.api.ModBoatType;
import com.nyfaria.batsgalore.init.entity.EntityInit;
import com.nyfaria.batsgalore.init.entity.SpookyBatEntityInit;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class CommonClientClass {
    public static <T extends Entity> List<Renderers<?>> getRenderers() {
        return List.of(
                new Renderers(SpookyBatEntityInit.PUMPKIN_BAT, (context) -> new PumpkinBatRenderer(context, new PumpkinBatModel<>(context.bakeLayer(PumpkinBatModel.LAYER_LOCATION)), "pumpkin_bat")),
                new Renderers(SpookyBatEntityInit.UNDEAD_BAT, context -> new UndeadBatRenderer(context, new UndeadBatModel<>(context.bakeLayer(UndeadBatModel.LAYER_LOCATION)))),
                new Renderers(SpookyBatEntityInit.CREEPER_BAT, CreeperBatRenderer::new),
                new Renderers(SpookyBatEntityInit.WITCH_BAT, context -> new SpookyBatRenderer<>(context, new WitchBatModel<>(context.bakeLayer(WitchBatModel.LAYER_LOCATION)))),
                new Renderers(SpookyBatEntityInit.SCULK_BAT, context -> new SculkBatRenderer(context, new SculkBatModel<>(context.bakeLayer(SculkBatModel.LAYER_LOCATION)), "sculk_bat")),
                new Renderers(SpookyBatEntityInit.GHOST_BAT, context -> new GhostBatRenderer(context, new GhostBatModel<>(context.bakeLayer(GhostBatModel.LAYER_LOCATION)))),
                new Renderers(SpookyBatEntityInit.STEVE_BAT, context -> new SpookyBatRenderer<>(context, new HatBatModel<>(context.bakeLayer(HatBatModel.LAYER_LOCATION)))),
                new Renderers(SpookyBatEntityInit.ALEX_BAT, context -> new SpookyBatRenderer<>(context, new HatBatModel<>(context.bakeLayer(HatBatModel.LAYER_LOCATION)))),
                new Renderers(SpookyBatEntityInit.HEROBRINE_BAT, context -> new EmissiveBatRenderer(context, new HatBatModel<>(context.bakeLayer(HatBatModel.LAYER_LOCATION)), "herobrine_bat")),
                new Renderers(SpookyBatEntityInit.SKELETON_BAT, context -> new SpookyBatRenderer<>(context, new SkeletonBatModel<>(context.bakeLayer(SkeletonBatModel.LAYER_LOCATION)))),
                new Renderers(SpookyBatEntityInit.WITHER_SKELETON_BAT, context -> new SpookyBatRenderer<>(context, new SkeletonBatModel<>(context.bakeLayer(SkeletonBatModel.LAYER_LOCATION)))),
                new Renderers(SpookyBatEntityInit.PLAYER_BAT, context -> new PlayerBatRenderer(context, new HatBatModel<>(context.bakeLayer(HatBatModel.LAYER_LOCATION)))),
                new Renderers(SpookyBatEntityInit.EVIL_BAT, context -> new SpookyBatRenderer<>(context, new EvilBatModel<>(context.bakeLayer(EvilBatModel.LAYER_LOCATION)))),
                new Renderers(SpookyBatEntityInit.VOID_BAT, context -> new VoidBatRenderer(context, new HatBatModel<>(context.bakeLayer(HatBatModel.LAYER_LOCATION)))),
                new Renderers(SpookyBatEntityInit.SLIME_BAT, context -> new SlimeBatRenderer(context, new SlimeBatModel<>(context.bakeLayer(SlimeBatModel.LAYER_LOCATION)))),
                new Renderers(SpookyBatEntityInit.EXPERIENCE_ORB_BAT, context -> new EmissiveBatRenderer(context, new ExperienceOrbBatModel<>(context.bakeLayer(ExperienceOrbBatModel.LAYER_LOCATION)), "experience_orb_bat")),
                new Renderers(SpookyBatEntityInit.SHULKER_BAT, context -> new SpookyBatRenderer<>(context, new ShulkerBatModel<>(context.bakeLayer(ShulkerBatModel.LAYER_LOCATION)))),
                new Renderers(SpookyBatEntityInit.WINGED_TURMOIL, context -> new SpookyBatRenderer<>(context, new WingedTurmoilModel<>(context.bakeLayer(WingedTurmoilModel.LAYER_LOCATION)), 2f)),
                new Renderers(SpookyBatEntityInit.JACK_O_LANTERN_PROJECTILE, context -> new ThrownItemRenderer<>(context, 1.0f, true)),
                new Renderers(SpookyBatEntityInit.WITCHS_BROOM, WitchsBroomRenderer::new),
                new Renderers(EntityInit.BLOCK_PROJECTILE, BlockProjectileRenderer::new),
                new Renderers(EntityInit.MOD_BOAT, context -> new ModBoatRenderer(context, false)),
                new Renderers(EntityInit.MOD_CHEST_BOAT, context -> new ModBoatRenderer(context, true))
        );
    }

    public static List<LayerDefinitions> getLayerDefinitions() {
        List<LayerDefinitions> definitions = new ArrayList<>();
        definitions.addAll(List.of(
                new LayerDefinitions(PumpkinBatModel.LAYER_LOCATION, PumpkinBatModel.createBodyLayer(CubeDeformation.NONE)),
                new LayerDefinitions(PumpkinBatModel.OVERLAY_LOCATION, PumpkinBatModel.createBodyLayer(new CubeDeformation(0.1f))),
                new LayerDefinitions(WitchBatModel.LAYER_LOCATION, WitchBatModel.createBodyLayer()),
                new LayerDefinitions(HatBatModel.LAYER_LOCATION, HatBatModel.createBodyLayer()),
                new LayerDefinitions(SkeletonBatModel.LAYER_LOCATION, SkeletonBatModel.createBodyLayer()),
                new LayerDefinitions(UndeadBatModel.LAYER_LOCATION, UndeadBatModel.createBodyLayer()),
                new LayerDefinitions(SculkBatModel.LAYER_LOCATION, SculkBatModel.createBodyLayer()),
                new LayerDefinitions(GhostBatModel.LAYER_LOCATION, GhostBatModel.createBodyLayer()),
                new LayerDefinitions(WitchsBroomModel.LAYER_LOCATION, WitchsBroomModel.createBodyLayer()),
                new LayerDefinitions(EvilBatModel.LAYER_LOCATION, EvilBatModel.createBodyLayer()),
                new LayerDefinitions(SlimeBatModel.LAYER_LOCATION, SlimeBatModel.createBodyLayer()),
                new LayerDefinitions(ExperienceOrbBatModel.LAYER_LOCATION, ExperienceOrbBatModel.createBodyLayer()),
                new LayerDefinitions(ShulkerBatModel.LAYER_LOCATION, ShulkerBatModel.createBodyLayer()),
                new LayerDefinitions(WingedTurmoilModel.LAYER_LOCATION, WingedTurmoilModel.createBodyLayer()),
                new LayerDefinitions(SpookyOakHangingSignModel.LAYER_LOCATION, SpookyOakHangingSignModel.createBodyLayer()),
                new LayerDefinitions(SpookyOakSignModel.LAYER_LOCATION, SpookyOakSignModel.createBodyLayer())
        ));
        LayerDefinition layerdefinition19 = ModBoatModel.createBodyModel();
        LayerDefinition layerdefinition20 = ModChestBoatModel.createBodyModel();
        for (ModBoatType boat$type : ModBoatType.values()) {
            definitions.add(new LayerDefinitions(ModBoatRenderer.createBoatModelName(boat$type), layerdefinition19));
            definitions.add(new LayerDefinitions(ModBoatRenderer.createChestBoatModelName(boat$type), layerdefinition20));
        }
        return definitions;
    }

    public record Renderers<T extends Entity>(Supplier<EntityType<T>> type, EntityRendererProvider<T> renderer) {
    }

    public record LayerDefinitions(ModelLayerLocation layerLocation, LayerDefinition supplier) {
    }
}
