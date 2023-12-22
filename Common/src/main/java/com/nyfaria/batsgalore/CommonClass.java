package com.nyfaria.batsgalore;

import com.nyfaria.batsgalore.block.WoodCollection;
import com.nyfaria.batsgalore.init.BlockInit;
import com.nyfaria.batsgalore.init.entity.EntityInit;
import com.nyfaria.batsgalore.init.ItemInit;
import com.nyfaria.batsgalore.init.TagInit;
import com.nyfaria.batsgalore.init.WorldGenInit;
import com.nyfaria.batsgalore.worldgen.BatsGaloreOverworldRegion;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.structure.Structure;
import terrablender.api.Regions;

import java.util.HashSet;
import java.util.Set;

public class CommonClass {
    public static final ResourceKey<Biome> SPOOKY_OAK_FOREST = ResourceKey.create(Registries.BIOME, new ResourceLocation(Constants.MODID, "spooky_oak_forest"));
    public static final ResourceKey<Biome> CHRISTMAS_FOREST = ResourceKey.create(Registries.BIOME, new ResourceLocation(Constants.MODID, "christmas_forest"));
    public static final ResourceKey<PlacedFeature> SPOOKY_OAK_TREE = ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(Constants.MODID, "spooky_oak"));
    public static final ResourceKey<PlacedFeature> WHITE_PINE_TREE = ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(Constants.MODID, "white_pine"));
    public static final ResourceKey<PlacedFeature> MEGA_WHITE_PINE_TREE = ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(Constants.MODID, "mega_white_pine"));
    public static final ResourceKey<PlacedFeature> DECORATED_WHITE_PINE_TREE = ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(Constants.MODID, "decorated_white_pine"));
    public static final ResourceKey<PlacedFeature> DECORATED_MEGA_WHITE_PINE_TREE = ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(Constants.MODID, "decorated_mega_white_pine"));
    public static final ResourceKey<Structure> ALTAR_OF_TURMOIL = ResourceKey.create(Registries.STRUCTURE, new ResourceLocation(Constants.MODID, "altar_of_turmoil"));
    public static final ResourceKey<ConfiguredFeature<?,?>> SPOOKY_OAK_TREE_CF = ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(Constants.MODID, "spooky_oak"));
    public static final ResourceKey<ConfiguredFeature<?,?>> WHITE_PINE_TREE_CF = ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(Constants.MODID, "white_pine"));
    public static final ResourceKey<ConfiguredFeature<?,?>> DECORATED_WHITE_PINE_TREE_CF = ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(Constants.MODID, "decorated_white_pine"));
    public static final ResourceKey<ConfiguredFeature<?,?>> MEGA_WHITE_PINE_TREE_CF = ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(Constants.MODID, "mega_white_pine"));
    public static final ResourceKey<ConfiguredFeature<?,?>> DECORATED_MEGA_WHITE_PINE_TREE_CF = ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(Constants.MODID, "decorated_mega_white_pine"));


    public static void init() {
        ItemInit.loadClass();
        BlockInit.loadClass();
        EntityInit.loadClass();
        TagInit.loadClass();
        WorldGenInit.loadClass();
    }

    public static void setupTerraBlender(){
        Regions.register(new BatsGaloreOverworldRegion(1));
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