package com.nyfaria.spookybats.datagen;

import com.nyfaria.spookybats.CommonSpawning;
import com.nyfaria.spookybats.Constants;
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
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class BatSpawnsProvider extends DatapackBuiltinEntriesProvider {

    private static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(ForgeRegistries.Keys.BIOME_MODIFIERS, BatSpawnsProvider::bootStrap);
    private static final ResourceKey<BiomeModifier> OVERWORLD = ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(Constants.MODID, "overworld_bat_spawns"));
    private static final ResourceKey<BiomeModifier> NETHER = ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(Constants.MODID, "nether_bat_spawns"));
    private static final ResourceKey<BiomeModifier> THE_END = ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(Constants.MODID, "the_end_bat_spawns"));
    private static final ResourceKey<BiomeModifier> SWAMP = ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(Constants.MODID, "swamp_bat_spawns"));
    private static final ResourceKey<BiomeModifier> MOUNTAIN = ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(Constants.MODID, "mountain_bat_spawns"));
    private static final ResourceKey<BiomeModifier> DEEP_DARK = ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(Constants.MODID, "deep_dark_bat_spawns"));

    public BatSpawnsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(Constants.MODID));
    }

    public static void bootStrap(BootstapContext<BiomeModifier> context) {
        context.register(OVERWORLD,
                new ForgeBiomeModifiers.AddSpawnsBiomeModifier(context.lookup(Registries.BIOME).getOrThrow(BiomeTags.IS_OVERWORLD), CommonSpawning.OVERWORLD_SPAWNS)
        );
        context.register(NETHER,
                new ForgeBiomeModifiers.AddSpawnsBiomeModifier(context.lookup(Registries.BIOME).getOrThrow(BiomeTags.IS_NETHER), CommonSpawning.NETHER_SPAWNS)
        );
        context.register(SWAMP,
                new ForgeBiomeModifiers.AddSpawnsBiomeModifier(context.lookup(Registries.BIOME).getOrThrow(Tags.Biomes.IS_SWAMP), CommonSpawning.SWAMP_SPAWNS)
        );
        context.register(MOUNTAIN,
                new ForgeBiomeModifiers.AddSpawnsBiomeModifier(context.lookup(Registries.BIOME).getOrThrow(Tags.Biomes.IS_MOUNTAIN), CommonSpawning.MOUNTAIN_SPAWNS)
        );
        context.register(DEEP_DARK,
                new ForgeBiomeModifiers.AddSpawnsBiomeModifier(HolderSet.direct(context.lookup(Registries.BIOME).get(Biomes.DEEP_DARK).get()), CommonSpawning.DEEP_DARK_SPAWNS)
        );
        context.register(THE_END,
                new ForgeBiomeModifiers.AddSpawnsBiomeModifier(context.lookup(Registries.BIOME).getOrThrow(BiomeTags.IS_END), CommonSpawning.END_SPAWNS)
        );
    }
}
