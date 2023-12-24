package com.nyfaria.batsgalore.client;

import com.nyfaria.batsgalore.client.model.HatBatModel;
import com.nyfaria.batsgalore.client.model.ModBatModel;
import com.nyfaria.batsgalore.client.model.ModBoatModel;
import com.nyfaria.batsgalore.client.model.ModChestBoatModel;
import com.nyfaria.batsgalore.client.model.ModHangingSignModel;
import com.nyfaria.batsgalore.client.model.ModSignModel;
import com.nyfaria.batsgalore.client.model.StatueLayers;
import com.nyfaria.batsgalore.client.model.christmas.ChristmasBatLayers;
import com.nyfaria.batsgalore.client.model.christmas.ElfHatModel;
import com.nyfaria.batsgalore.client.model.christmas.ReindeerNoseModel;
import com.nyfaria.batsgalore.client.model.spooky.SpookyBatLayers;
import com.nyfaria.batsgalore.client.model.spooky.WingedTurmoilModel;
import com.nyfaria.batsgalore.client.model.spooky.WitchsBroomModel;
import com.nyfaria.batsgalore.client.renderer.BlockProjectileRenderer;
import com.nyfaria.batsgalore.client.renderer.PoweredBatRenderer;
import com.nyfaria.batsgalore.client.renderer.EmissiveBatRenderer;
import com.nyfaria.batsgalore.client.renderer.GhostBatRenderer;
import com.nyfaria.batsgalore.client.renderer.ModBoatRenderer;
import com.nyfaria.batsgalore.client.renderer.PlayerBatRenderer;
import com.nyfaria.batsgalore.client.renderer.PumpkinBatRenderer;
import com.nyfaria.batsgalore.client.renderer.SculkBatRenderer;
import com.nyfaria.batsgalore.client.renderer.SlimeBatRenderer;
import com.nyfaria.batsgalore.client.renderer.StatueBlockEntityRenderer;
import com.nyfaria.batsgalore.client.renderer.UndeadBatRenderer;
import com.nyfaria.batsgalore.client.renderer.VoidBatRenderer;
import com.nyfaria.batsgalore.client.renderer.WitchsBroomRenderer;
import com.nyfaria.batsgalore.client.renderer.api.ModBatRenderer;
import com.nyfaria.batsgalore.entity.api.ModBoatType;
import com.nyfaria.batsgalore.init.BlockInit;
import com.nyfaria.batsgalore.init.entity.ChristmasBatEntityInit;
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
                new Renderers(SpookyBatEntityInit.PUMPKIN_BAT, (context) -> new PumpkinBatRenderer(context, new ModBatModel<>(context.bakeLayer(SpookyBatLayers.PUMPKIN_BAT_LAYER_LOCATION)), "pumpkin_bat")),
                new Renderers(SpookyBatEntityInit.UNDEAD_BAT, context -> new UndeadBatRenderer(context, new ModBatModel<>(context.bakeLayer(SpookyBatLayers.UNDEAD_BAT_LAYER_LOCATION)))),
                new Renderers(SpookyBatEntityInit.CREEPER_BAT, context -> new PoweredBatRenderer(context, new ModBatModel<>(context.bakeLayer(SpookyBatLayers.PUMPKIN_BAT_LAYER_LOCATION)))),
                new Renderers(SpookyBatEntityInit.WITCH_BAT, context -> new ModBatRenderer<>(context, new ModBatModel<>(context.bakeLayer(SpookyBatLayers.WITCH_BAT_LAYER_LOCATION)))),
                new Renderers(SpookyBatEntityInit.SCULK_BAT, context -> new SculkBatRenderer(context, new ModBatModel<>(context.bakeLayer(SpookyBatLayers.SCULK_BAT_LAYER_LOCATION)), "sculk_bat")),
                new Renderers(SpookyBatEntityInit.GHOST_BAT, context -> new GhostBatRenderer(context, new ModBatModel<>(context.bakeLayer(SpookyBatLayers.GHOST_BAT_LAYER_LOCATION)))),
                new Renderers(SpookyBatEntityInit.STEVE_BAT, context -> new ModBatRenderer<>(context, new HatBatModel<>(context.bakeLayer(SpookyBatLayers.HAT_BAT_LAYER_LOCATION)))),
                new Renderers(SpookyBatEntityInit.ALEX_BAT, context -> new ModBatRenderer<>(context, new HatBatModel<>(context.bakeLayer(SpookyBatLayers.HAT_BAT_LAYER_LOCATION)))),
                new Renderers(SpookyBatEntityInit.HEROBRINE_BAT, context -> new EmissiveBatRenderer(context, new HatBatModel<>(context.bakeLayer(SpookyBatLayers.HAT_BAT_LAYER_LOCATION)), "herobrine_bat")),
                new Renderers(SpookyBatEntityInit.SKELETON_BAT, context -> new ModBatRenderer<>(context, new ModBatModel<>(context.bakeLayer(SpookyBatLayers.SKELETON_BAT_LAYER_LOCATION)))),
                new Renderers(SpookyBatEntityInit.WITHER_SKELETON_BAT, context -> new ModBatRenderer<>(context, new ModBatModel<>(context.bakeLayer(SpookyBatLayers.SKELETON_BAT_LAYER_LOCATION)))),
                new Renderers(SpookyBatEntityInit.PLAYER_BAT, context -> new PlayerBatRenderer(context, new HatBatModel<>(context.bakeLayer(SpookyBatLayers.HAT_BAT_LAYER_LOCATION)))),
                new Renderers(SpookyBatEntityInit.EVIL_BAT, context -> new ModBatRenderer<>(context, new ModBatModel<>(context.bakeLayer(SpookyBatLayers.EVIL_BAT_LAYER_LOCATION)))),
                new Renderers(SpookyBatEntityInit.VOID_BAT, context -> new VoidBatRenderer(context, new HatBatModel<>(context.bakeLayer(SpookyBatLayers.HAT_BAT_LAYER_LOCATION)))),
                new Renderers(SpookyBatEntityInit.SLIME_BAT, context -> new SlimeBatRenderer(context, new ModBatModel<>(context.bakeLayer(SpookyBatLayers.SLIME_BAT_LAYER_LOCATION)))),
                new Renderers(SpookyBatEntityInit.EXPERIENCE_ORB_BAT, context -> new EmissiveBatRenderer(context, new ModBatModel<>(context.bakeLayer(SpookyBatLayers.EXPERIENCE_BAT_LAYER_LOCATION)), "experience_orb_bat")),
                new Renderers(SpookyBatEntityInit.SHULKER_BAT, context -> new ModBatRenderer<>(context, new ModBatModel<>(context.bakeLayer(SpookyBatLayers.SHULKER_BAT_LAYER_LOCATION)))),
                new Renderers(SpookyBatEntityInit.WINGED_TURMOIL, context -> new ModBatRenderer<>(context, new WingedTurmoilModel<>(context.bakeLayer(SpookyBatLayers.WINGED_TURMOIL_LAYER_LOCATION)), 2f)),
                new Renderers(ChristmasBatEntityInit.CANDY_CANE_BAT, context -> new ModBatRenderer<>(context, new ModBatModel<>(context.bakeLayer(ChristmasBatLayers.CANDY_CANE_BAT_LAYER_LOCATION)))),
                new Renderers(ChristmasBatEntityInit.ELF_BAT, context -> new ModBatRenderer<>(context, new ModBatModel<>(context.bakeLayer(ChristmasBatLayers.ELF_BAT_LAYER_LOCATION)))),
                new Renderers(ChristmasBatEntityInit.REINDEER_BAT, context -> new EmissiveBatRenderer(context, new ModBatModel<>(context.bakeLayer(ChristmasBatLayers.REINDEER_BAT_LAYER_LOCATION)),"reindeer_bat")),
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
                new LayerDefinitions(SpookyBatLayers.PUMPKIN_BAT_LAYER_LOCATION, SpookyBatLayers.createPumpkinBatLayer(CubeDeformation.NONE)),
                new LayerDefinitions(SpookyBatLayers.BAT_OVERLAY_LOCATION, SpookyBatLayers.createPumpkinBatLayer(new CubeDeformation(0.1f))),
                new LayerDefinitions(SpookyBatLayers.WITCH_BAT_LAYER_LOCATION, SpookyBatLayers.createWitchBatLayer()),
                new LayerDefinitions(SpookyBatLayers.HAT_BAT_LAYER_LOCATION, SpookyBatLayers.createHatBatLayer()),
                new LayerDefinitions(SpookyBatLayers.SKELETON_BAT_LAYER_LOCATION, SpookyBatLayers.createSkeletonBatLayer()),
                new LayerDefinitions(SpookyBatLayers.UNDEAD_BAT_LAYER_LOCATION, SpookyBatLayers.createUndeadBatLayer()),
                new LayerDefinitions(SpookyBatLayers.SCULK_BAT_LAYER_LOCATION, SpookyBatLayers.createSculkBatLayer()),
                new LayerDefinitions(SpookyBatLayers.GHOST_BAT_LAYER_LOCATION, SpookyBatLayers.createGhostBatLayer()),
                new LayerDefinitions(WitchsBroomModel.LAYER_LOCATION, WitchsBroomModel.createBodyLayer()),
                new LayerDefinitions(SpookyBatLayers.EVIL_BAT_LAYER_LOCATION, SpookyBatLayers.createEvilBatLayer()),
                new LayerDefinitions(SpookyBatLayers.SLIME_BAT_LAYER_LOCATION, SpookyBatLayers.createSlimBatLayer()),
                new LayerDefinitions(SpookyBatLayers.EXPERIENCE_BAT_LAYER_LOCATION, SpookyBatLayers.createExperienceBatLayer()),
                new LayerDefinitions(SpookyBatLayers.SHULKER_BAT_LAYER_LOCATION, SpookyBatLayers.createBodyLayer()),
                new LayerDefinitions(SpookyBatLayers.WINGED_TURMOIL_LAYER_LOCATION, SpookyBatLayers.createWingedTurmoilLayer()),
                new LayerDefinitions(ChristmasBatLayers.CANDY_CANE_BAT_LAYER_LOCATION, ChristmasBatLayers.createCandyCaneBatLayer()),
                new LayerDefinitions(ChristmasBatLayers.REINDEER_BAT_LAYER_LOCATION, ChristmasBatLayers.createReindeerBatLayer()),
                new LayerDefinitions(ChristmasBatLayers.ELF_BAT_LAYER_LOCATION, ChristmasBatLayers.createElfBatLayer()),
                new LayerDefinitions(ModHangingSignModel.LAYER_LOCATION, ModHangingSignModel.createBodyLayer()),
                new LayerDefinitions(ModSignModel.LAYER_LOCATION, ModSignModel.createBodyLayer()),
                new LayerDefinitions(ElfHatModel.LAYER_LOCATION, ElfHatModel.createBodyLayer()),
                new LayerDefinitions(ReindeerNoseModel.LAYER_LOCATION, ReindeerNoseModel.createBodyLayer()),
                new LayerDefinitions(StatueLayers.STATUE_OF_TURMOIL_LAYER_LOCATION, StatueLayers.createStatueOfTurmoilLayer())
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
