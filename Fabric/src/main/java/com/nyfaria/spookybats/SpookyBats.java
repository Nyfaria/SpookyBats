package com.nyfaria.spookybats;

import com.nyfaria.spookybats.init.EntityInit;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.block.type.BlockSetTypeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biomes;

public class SpookyBats implements ModInitializer {

    @Override
    public void onInitialize() {
        CommonClass.init();
        EntityInit.attributeSuppliers.forEach(
                p -> FabricDefaultAttributeRegistry.register(p.entityTypeSupplier().get(), p.factory().get().build())
        );
        BiomeModifications.addSpawn(BiomeSelectors.foundInOverworld(), MobCategory.MONSTER, EntityInit.PUMPKIN_BAT.get(), 20, 1, 5);
        BiomeModifications.addSpawn(BiomeSelectors.foundInOverworld(), MobCategory.MONSTER, EntityInit.CREEPER_BAT.get(), 20, 1, 5);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.SWAMP,Biomes.MANGROVE_SWAMP), MobCategory.MONSTER, EntityInit.WITCH_BAT.get(), 20, 1, 5);
        BiomeModifications.addSpawn(BiomeSelectors.foundInOverworld(), MobCategory.MONSTER, EntityInit.STEVE_BAT.get(), 20, 1, 5);
        BiomeModifications.addSpawn(BiomeSelectors.foundInOverworld(), MobCategory.MONSTER, EntityInit.ALEX_BAT.get(), 20, 1, 5);
        BiomeModifications.addSpawn(BiomeSelectors.foundInOverworld(), MobCategory.MONSTER, EntityInit.HEROBRINE_BAT.get(), 20, 1, 5);
        BiomeModifications.addSpawn(BiomeSelectors.foundInOverworld(), MobCategory.MONSTER, EntityInit.SKELETON_BAT.get(), 20, 1, 5);
        BiomeModifications.addSpawn(BiomeSelectors.foundInTheNether(), MobCategory.MONSTER, EntityInit.WITHER_SKELETON_BAT.get(), 20, 1, 5);
        BiomeModifications.addSpawn(BiomeSelectors.foundInTheNether(), MobCategory.MONSTER, EntityInit.UNDEAD_BAT.get(), 20, 1, 5);
        BiomeModifications.addSpawn(BiomeSelectors.foundInOverworld(), MobCategory.MONSTER, EntityInit.PLAYER_BAT.get(), 20, 1, 5);

        CommonClass.placements();
    }
}
