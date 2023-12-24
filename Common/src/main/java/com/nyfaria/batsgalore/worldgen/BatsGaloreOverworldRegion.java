package com.nyfaria.batsgalore.worldgen;

import com.mojang.datafixers.util.Pair;
import com.nyfaria.batsgalore.CommonClass;
import com.nyfaria.batsgalore.Constants;
import com.nyfaria.batsgalore.config.CommonConfig;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import terrablender.api.ParameterUtils;
import terrablender.api.Region;
import terrablender.api.RegionType;
import terrablender.api.VanillaParameterOverlayBuilder;

import java.util.function.Consumer;

public class BatsGaloreOverworldRegion extends Region {
    public static final ResourceLocation LOCATION = new ResourceLocation(Constants.MODID, "main_overworld");

    public BatsGaloreOverworldRegion(int weight) {
        super(LOCATION, RegionType.OVERWORLD, weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        VanillaParameterOverlayBuilder builder = new VanillaParameterOverlayBuilder();
        if(CommonConfig.INSTANCE.spookyOakForestGeneration.get()) {
            new ParameterUtils.ParameterPointListBuilder()
                    .temperature(ParameterUtils.Temperature.span(ParameterUtils.Temperature.COOL, ParameterUtils.Temperature.WARM))
                    .humidity(ParameterUtils.Humidity.span(ParameterUtils.Humidity.HUMID, ParameterUtils.Humidity.WET))
                    .continentalness(ParameterUtils.Continentalness.FAR_INLAND)
                    .erosion(ParameterUtils.Erosion.EROSION_0, ParameterUtils.Erosion.EROSION_1)
                    .depth(ParameterUtils.Depth.SURFACE, ParameterUtils.Depth.FLOOR)
                    .weirdness(ParameterUtils.Weirdness.MID_SLICE_NORMAL_ASCENDING, ParameterUtils.Weirdness.MID_SLICE_NORMAL_DESCENDING)
                    .build().forEach(point -> builder.add(point, CommonClass.SPOOKY_OAK_FOREST));
        }
        if(CommonConfig.INSTANCE.christmasForestGeneration.get()) {
            new ParameterUtils.ParameterPointListBuilder()
                    .temperature(ParameterUtils.Temperature.span(ParameterUtils.Temperature.FROZEN, ParameterUtils.Temperature.FROZEN))
                    .humidity(ParameterUtils.Humidity.span(ParameterUtils.Humidity.WET, ParameterUtils.Humidity.WET))
                    .continentalness(ParameterUtils.Continentalness.FAR_INLAND)
                    .erosion(ParameterUtils.Erosion.EROSION_0, ParameterUtils.Erosion.EROSION_1)
                    .depth(ParameterUtils.Depth.SURFACE, ParameterUtils.Depth.FLOOR)
                    .weirdness(ParameterUtils.Weirdness.MID_SLICE_NORMAL_ASCENDING, ParameterUtils.Weirdness.MID_SLICE_NORMAL_DESCENDING)
                    .build().forEach(point -> builder.add(point, CommonClass.CHRISTMAS_FOREST));
        }
        // Add our points to the mapper
        builder.build().forEach(mapper);

    }
}