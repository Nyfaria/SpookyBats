package com.nyfaria.spookybats;

import com.nyfaria.spookybats.client.model.EvilBatModel;
import com.nyfaria.spookybats.client.model.ExperienceOrbBatModel;
import com.nyfaria.spookybats.client.model.GhostBatModel;
import com.nyfaria.spookybats.client.model.HatBatModel;
import com.nyfaria.spookybats.client.model.PumpkinBatModel;
import com.nyfaria.spookybats.client.model.SculkBatModel;
import com.nyfaria.spookybats.client.model.SkeletonBatModel;
import com.nyfaria.spookybats.client.model.SlimeBatModel;
import com.nyfaria.spookybats.client.model.UndeadBatModel;
import com.nyfaria.spookybats.client.model.WitchBatModel;
import com.nyfaria.spookybats.client.renderer.CreeperBatRenderer;
import com.nyfaria.spookybats.client.renderer.EmissiveBatRenderer;
import com.nyfaria.spookybats.client.renderer.GhostBatRenderer;
import com.nyfaria.spookybats.client.renderer.PlayerBatRenderer;
import com.nyfaria.spookybats.client.renderer.PumpkinBatRenderer;
import com.nyfaria.spookybats.client.renderer.SculkBatRenderer;
import com.nyfaria.spookybats.client.renderer.SlimeBatRenderer;
import com.nyfaria.spookybats.client.renderer.UndeadBatRenderer;
import com.nyfaria.spookybats.client.renderer.VoidBatRenderer;
import com.nyfaria.spookybats.client.renderer.WitchsBroomRenderer;
import com.nyfaria.spookybats.client.renderer.api.SpookyBatRenderer;
import com.nyfaria.spookybats.entity.MonsterBat;
import com.nyfaria.spookybats.entity.api.SpookyBat;
import com.nyfaria.spookybats.init.BlockInit;
import com.nyfaria.spookybats.init.EntityInit;
import com.nyfaria.spookybats.init.ItemInit;
import com.nyfaria.spookybats.worldgen.SpookyBatsOverworldRegion;
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
import terrablender.api.Regions;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

public class CommonClass {
    public static final ResourceKey<Biome> SPOOKY_OAK_FOREST = ResourceKey.create(Registries.BIOME, new ResourceLocation(Constants.MODID, "spooky_oak_forest"));
    public static final ResourceKey<PlacedFeature> SPOOKY_OAK_TREE = ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(Constants.MODID, "spooky_oak"));
    public static final ResourceKey<ConfiguredFeature<?,?>> SPOOKY_OAK_TREE_CF = ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(Constants.MODID, "spooky_oak"));


    public static void init() {
        ItemInit.loadClass();
        BlockInit.loadClass();
        EntityInit.loadClass();
    }

    public static void setupTerraBlender(){
        Regions.register(new SpookyBatsOverworldRegion(1));
    }

    public static void setupBlockEntities(){
        Set<Block> hangingSignBlocks = new HashSet<>(BlockEntityType.HANGING_SIGN.validBlocks);
        hangingSignBlocks.add(BlockInit.SPOOKY_OAK.hangingSign().get());
        hangingSignBlocks.add(BlockInit.SPOOKY_OAK.hangingWallSign().get());
        BlockEntityType.HANGING_SIGN.validBlocks = hangingSignBlocks;
    }
}