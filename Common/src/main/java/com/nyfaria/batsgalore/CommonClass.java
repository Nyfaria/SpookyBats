package com.nyfaria.batsgalore;

import com.nyfaria.batsgalore.block.WoodCollection;
import com.nyfaria.batsgalore.client.model.EvilBatModel;
import com.nyfaria.batsgalore.client.model.ExperienceOrbBatModel;
import com.nyfaria.batsgalore.client.model.GhostBatModel;
import com.nyfaria.batsgalore.client.model.HatBatModel;
import com.nyfaria.batsgalore.client.model.PumpkinBatModel;
import com.nyfaria.batsgalore.client.model.SculkBatModel;
import com.nyfaria.batsgalore.client.model.SkeletonBatModel;
import com.nyfaria.batsgalore.client.model.SlimeBatModel;
import com.nyfaria.batsgalore.client.model.UndeadBatModel;
import com.nyfaria.batsgalore.client.model.WitchBatModel;
import com.nyfaria.batsgalore.client.renderer.CreeperBatRenderer;
import com.nyfaria.batsgalore.client.renderer.EmissiveBatRenderer;
import com.nyfaria.batsgalore.client.renderer.GhostBatRenderer;
import com.nyfaria.batsgalore.client.renderer.PlayerBatRenderer;
import com.nyfaria.batsgalore.client.renderer.PumpkinBatRenderer;
import com.nyfaria.batsgalore.client.renderer.SculkBatRenderer;
import com.nyfaria.batsgalore.client.renderer.SlimeBatRenderer;
import com.nyfaria.batsgalore.client.renderer.UndeadBatRenderer;
import com.nyfaria.batsgalore.client.renderer.VoidBatRenderer;
import com.nyfaria.batsgalore.client.renderer.WitchsBroomRenderer;
import com.nyfaria.batsgalore.client.renderer.api.SpookyBatRenderer;
import com.nyfaria.batsgalore.entity.MonsterBat;
import com.nyfaria.batsgalore.entity.api.SpookyBat;
import com.nyfaria.batsgalore.init.BlockInit;
import com.nyfaria.batsgalore.init.EntityInit;
import com.nyfaria.batsgalore.init.ItemInit;
import com.nyfaria.batsgalore.init.TagInit;
import com.nyfaria.batsgalore.init.WorldGenInit;
import com.nyfaria.batsgalore.worldgen.SpookyBatsOverworldRegion;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.structure.Structure;
import terrablender.api.Regions;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

public class CommonClass {
    public static final ResourceKey<Biome> SPOOKY_OAK_FOREST = ResourceKey.create(Registries.BIOME, new ResourceLocation(Constants.MODID, "spooky_oak_forest"));
    public static final ResourceKey<PlacedFeature> SPOOKY_OAK_TREE = ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(Constants.MODID, "spooky_oak"));
    public static final ResourceKey<PlacedFeature> WHITE_PINE_TREE = ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(Constants.MODID, "white_pine"));
    public static final ResourceKey<Structure> ALTAR_OF_TURMOIL = ResourceKey.create(Registries.STRUCTURE, new ResourceLocation(Constants.MODID, "altar_of_turmoil"));
    public static final ResourceKey<ConfiguredFeature<?,?>> SPOOKY_OAK_TREE_CF = ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(Constants.MODID, "spooky_oak"));
    public static final ResourceKey<ConfiguredFeature<?,?>> WHITE_PINE_TREE_CF = ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(Constants.MODID, "white_pine"));
    public static final ResourceKey<ConfiguredFeature<?,?>> MEGA_WHITE_PINE_TREE_CF = ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(Constants.MODID, "mega_white_pine"));


    public static void init() {
        ItemInit.loadClass();
        BlockInit.loadClass();
        EntityInit.loadClass();
        TagInit.loadClass();
        WorldGenInit.loadClass();
    }

    public static void setupTerraBlender(){
        Regions.register(new SpookyBatsOverworldRegion(1));
    }

    public static void setupBlockEntities(){
        Set<Block> hangingSignBlocks = new HashSet<>(BlockEntityType.HANGING_SIGN.validBlocks);
        Set<Block> signBlocks = new HashSet<>(BlockEntityType.SIGN.validBlocks);
        WoodCollection.WOOD_COLLECTIONS.forEach(
                woodCollection -> {
                    hangingSignBlocks.add(woodCollection.hangingSign().get());
                    hangingSignBlocks.add(woodCollection.hangingWallSign().get());
                    signBlocks.add(woodCollection.sign().get());
                    signBlocks.add(woodCollection.wallSign().get());
                }

        );
        BlockEntityType.HANGING_SIGN.validBlocks = hangingSignBlocks;
        BlockEntityType.SIGN.validBlocks = signBlocks;
    }
}