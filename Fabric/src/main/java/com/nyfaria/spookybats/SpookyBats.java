package com.nyfaria.spookybats;

import com.nyfaria.spookybats.init.EntityInit;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModification;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biomes;

public class SpookyBats implements ModInitializer {

    @Override
    public void onInitialize() {
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

        CommonSpawning.placements();
        CommonClass.setupBlockEntities();
    }
}
