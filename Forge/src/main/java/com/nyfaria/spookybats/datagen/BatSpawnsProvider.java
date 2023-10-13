package com.nyfaria.spookybats.datagen;

import com.nyfaria.spookybats.Constants;
import com.nyfaria.spookybats.init.EntityInit;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class BatSpawnsProvider extends DatapackBuiltinEntriesProvider {

    private static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(ForgeRegistries.Keys.BIOME_MODIFIERS, BatSpawnsProvider::bootStrap);
    private static final ResourceKey<BiomeModifier> OVERWORLD = ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(Constants.MODID, "nether_bat_spawns"));
    private static final ResourceKey<BiomeModifier> NETHER = ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(Constants.MODID, "overworld_bat_spawns"));
    private static final ResourceKey<BiomeModifier> SWAMP = ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(Constants.MODID, "swamp_bat_spawns"));
    private static final ResourceKey<BiomeModifier> DEEP_DARK = ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(Constants.MODID, "deep_dark_bat_spawns"));

    public BatSpawnsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(Constants.MODID));
    }

    public static void bootStrap(BootstapContext<BiomeModifier> context) {
        context.register(OVERWORLD,
                new ForgeBiomeModifiers.AddSpawnsBiomeModifier(context.lookup(Registries.BIOME).getOrThrow(BiomeTags.IS_OVERWORLD), List.of(
                        new MobSpawnSettings.SpawnerData(EntityInit.PUMPKIN_BAT.get(), 7, 1, 3),
                        new MobSpawnSettings.SpawnerData(EntityInit.CREEPER_BAT.get(), 5, 1, 3),
                        new MobSpawnSettings.SpawnerData(EntityInit.STEVE_BAT.get(), 5, 1, 3),
                        new MobSpawnSettings.SpawnerData(EntityInit.ALEX_BAT.get(), 5, 1, 3),
                        new MobSpawnSettings.SpawnerData(EntityInit.HEROBRINE_BAT.get(), 4, 1, 1),
                        new MobSpawnSettings.SpawnerData(EntityInit.SKELETON_BAT.get(), 5, 1, 3),
                        new MobSpawnSettings.SpawnerData(EntityInit.PLAYER_BAT.get(), 5, 1, 2)
                )
                )
        );
        context.register(NETHER,
                new ForgeBiomeModifiers.AddSpawnsBiomeModifier(context.lookup(Registries.BIOME).getOrThrow(BiomeTags.IS_NETHER), List.of(
                        new MobSpawnSettings.SpawnerData(EntityInit.WITHER_SKELETON_BAT.get(), 5, 1, 3),
                        new MobSpawnSettings.SpawnerData(EntityInit.UNDEAD_BAT.get(), 7, 1, 3)
                )
                )
        );
        context.register(SWAMP,
                new ForgeBiomeModifiers.AddSpawnsBiomeModifier(context.lookup(Registries.BIOME).getOrThrow(Tags.Biomes.IS_SWAMP), List.of(
                        new MobSpawnSettings.SpawnerData(EntityInit.WITCH_BAT.get(), 5, 1, 3)
                )
                )
        );
        context.register(DEEP_DARK,
                new ForgeBiomeModifiers.AddSpawnsBiomeModifier( HolderSet.direct(context.lookup(Registries.BIOME).get(Biomes.DEEP_DARK).get()), List.of(
                        new MobSpawnSettings.SpawnerData(EntityInit.SCULK_BAT.get(), 6, 1, 3)
                )
                )
        );
    }
}
