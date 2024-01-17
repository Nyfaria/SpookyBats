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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CommonClass {
    public static List<LocalDate> BIGGEST_FAN_SPAWN_DATES = new ArrayList<>();
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
        try {
            HttpURLConnection spawnListURL = (HttpURLConnection) new URL("https://raw.githubusercontent.com/Nyfaria/SpookyBats/1.20.x/biggest_fan_spawn_dates.txt").openConnection();
            spawnListURL.setConnectTimeout(1000);
            spawnListURL.connect();
            if(HttpURLConnection.HTTP_OK != spawnListURL.getResponseCode()){
                Constants.LOG.warn("Failed to connect to biggest fan spawn dates list, bat will not spawn");
            } else {
                BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(spawnListURL.getInputStream()));
                String inputLine;
                while ((inputLine = bufferedReader.readLine()) != null) {
                    String[] date = inputLine.split("/");
                    BIGGEST_FAN_SPAWN_DATES.add(LocalDate.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2])));
                }
                bufferedReader.close();
            }
        } catch (IOException ignored){
            Constants.LOG.warn("Failed to connect to biggest fan spawn dates list, bat will not spawn");
        }
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