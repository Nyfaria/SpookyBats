package com.nyfaria.spookybats;

import com.nyfaria.spookybats.init.EntityInit;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.world.entity.MobCategory;

public class SpookyBats implements ModInitializer {

    @Override
    public void onInitialize() {
        CommonClass.init();
        EntityInit.attributeSuppliers.forEach(
                p -> FabricDefaultAttributeRegistry.register(p.entityTypeSupplier().get(), p.factory().get().build())
        );
        EntityInit.ENTITIES.getEntries().forEach(p ->BiomeModifications.addSpawn(BiomeSelectors.foundInOverworld(),MobCategory.MONSTER,p.get(),20,1,5));
        CommonClass.placements();
    }
}
