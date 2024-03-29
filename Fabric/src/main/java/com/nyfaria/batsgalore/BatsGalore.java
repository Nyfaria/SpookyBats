package com.nyfaria.batsgalore;

import com.nyfaria.batsgalore.config.CommonConfig;
import com.nyfaria.batsgalore.init.entity.EntityInit;
import fuzs.forgeconfigapiport.api.config.v2.ForgeConfigRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biomes;
import net.minecraftforge.fml.config.ModConfig;

public class BatsGalore implements ModInitializer {

    @Override
    public void onInitialize() {
        ForgeConfigRegistry.INSTANCE.register(Constants.MODID, ModConfig.Type.COMMON, CommonConfig.CONFIG_SPEC);

        CommonClass.init();

        EntityInit.attributeSuppliers.forEach(
                p -> FabricDefaultAttributeRegistry.register(p.entityTypeSupplier().get(), p.factory().get().build())
        );
        CommonSpawning.NETHER_SPAWNS.forEach(spawnerData ->
                BiomeModifications.addSpawn(BiomeSelectors.foundInTheNether(), MobCategory.MONSTER, spawnerData.type, spawnerData.getWeight().asInt(), spawnerData.minCount, spawnerData.maxCount)
        );
        CommonSpawning.SWAMP_SPAWNS.forEach(spawnerData ->
                BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.SWAMP, Biomes.MANGROVE_SWAMP), MobCategory.MONSTER, spawnerData.type, spawnerData.getWeight().asInt(), spawnerData.minCount, spawnerData.maxCount)
        );
        CommonSpawning.MOUNTAIN_SPAWNS.forEach(spawnerData ->
                BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.MEADOW, Biomes.FROZEN_PEAKS, Biomes.JAGGED_PEAKS, Biomes.STONY_PEAKS, Biomes.SNOWY_SLOPES, Biomes.CHERRY_GROVE), MobCategory.MONSTER, spawnerData.type, spawnerData.getWeight().asInt(), spawnerData.minCount, spawnerData.maxCount)
        );
        CommonSpawning.DEEP_DARK_SPAWNS.forEach(spawnerData ->
                BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.DEEP_DARK), MobCategory.MONSTER, spawnerData.type, spawnerData.getWeight().asInt(), spawnerData.minCount, spawnerData.maxCount)
        );
        CommonSpawning.END_SPAWNS.forEach(spawnerData ->
                BiomeModifications.addSpawn(BiomeSelectors.foundInTheEnd(), MobCategory.MONSTER, spawnerData.type, spawnerData.getWeight().asInt(), spawnerData.minCount, spawnerData.maxCount)
        );
        CommonSpawning.OVERWORLD_SPAWNS.forEach(spawnerData ->
                BiomeModifications.addSpawn(BiomeSelectors.foundInOverworld(), MobCategory.MONSTER, spawnerData.type, spawnerData.getWeight().asInt(), spawnerData.minCount, spawnerData.maxCount)
        );

        CommonSpawning.placements();
        CommonClass.setupBlockEntities();
    }
}
