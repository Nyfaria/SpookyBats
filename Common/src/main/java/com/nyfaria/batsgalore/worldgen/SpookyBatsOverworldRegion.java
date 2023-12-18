package com.nyfaria.batsgalore.worldgen;

import com.mojang.datafixers.util.Pair;
import com.nyfaria.batsgalore.CommonClass;
import com.nyfaria.batsgalore.Constants;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import terrablender.api.Region;
import terrablender.api.RegionType;

import java.util.function.Consumer;

public class SpookyBatsOverworldRegion extends Region {
    public static final ResourceLocation LOCATION = new ResourceLocation(Constants.MODID, "main_overworld");

    public SpookyBatsOverworldRegion(int weight) {
        super(LOCATION, RegionType.OVERWORLD, weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        this.addBiome(mapper, new Climate.ParameterPoint(
                Climate.Parameter.point(1.0f),
                Climate.Parameter.point(1.0f),
                Climate.Parameter.point(1.0f),
                Climate.Parameter.point(1.0f),
                Climate.Parameter.point(1.0f),
                Climate.Parameter.point(1.0f)
                ,1000), CommonClass.SPOOKY_OAK_FOREST);
    }
}