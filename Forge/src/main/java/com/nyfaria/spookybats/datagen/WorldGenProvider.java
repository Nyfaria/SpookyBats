package com.nyfaria.spookybats.datagen;

import com.nyfaria.spookybats.CommonClass;
import com.nyfaria.spookybats.CommonSpawning;
import com.nyfaria.spookybats.Constants;
import com.nyfaria.spookybats.block.SpookyOakLeaves;
import com.nyfaria.spookybats.init.BlockInit;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.Vec3i;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.CavePlacements;
import net.minecraft.data.worldgen.placement.MiscOverworldPlacements;
import net.minecraft.data.worldgen.placement.OrePlacements;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.util.valueproviders.WeightedListInt;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.ThreeLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.DarkOakFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.DarkOakTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.BlockPredicateFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.HeightmapPlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.SurfaceWaterDepthFilter;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.OptionalInt;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class WorldGenProvider extends DatapackBuiltinEntriesProvider {

    private static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(ForgeRegistries.Keys.BIOME_MODIFIERS, WorldGenProvider::spawns)
            .add(Registries.CONFIGURED_FEATURE, WorldGenProvider::configuredFeature)
            .add(Registries.PLACED_FEATURE, WorldGenProvider::placedFeatures)
            .add(Registries.BIOME, WorldGenProvider::biomes);
    private static final ResourceKey<BiomeModifier> OVERWORLD = ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(Constants.MODID, "overworld_bat_spawns"));
    private static final ResourceKey<BiomeModifier> NETHER = ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(Constants.MODID, "nether_bat_spawns"));
    private static final ResourceKey<BiomeModifier> THE_END = ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(Constants.MODID, "the_end_bat_spawns"));
    private static final ResourceKey<BiomeModifier> SWAMP = ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(Constants.MODID, "swamp_bat_spawns"));
    private static final ResourceKey<BiomeModifier> MOUNTAIN = ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(Constants.MODID, "mountain_bat_spawns"));
    private static final ResourceKey<BiomeModifier> DEEP_DARK = ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(Constants.MODID, "deep_dark_bat_spawns"));

    public WorldGenProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(Constants.MODID));
    }

    public static void spawns(BootstapContext<BiomeModifier> context) {
        context.register(OVERWORLD,
                new ForgeBiomeModifiers.AddSpawnsBiomeModifier(context.lookup(Registries.BIOME).getOrThrow(BiomeTags.IS_OVERWORLD), CommonSpawning.SPOOKY_OAK_FOREST_SPAWNS)
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

    public static void configuredFeature(BootstapContext<ConfiguredFeature<?, ?>> context) {
        context.register(CommonClass.SPOOKY_OAK_TREE_CF, new ConfiguredFeature<>(
                        Feature.TREE,
                        new TreeConfiguration.TreeConfigurationBuilder(
                                BlockStateProvider.simple(BlockInit.SPOOKY_OAK.log().get().defaultBlockState().setValue(RotatedPillarBlock.AXIS, Direction.Axis.Y)),
                                new DarkOakTrunkPlacer(6, 2, 1),
                                new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                                        .add(BlockInit.SPOOKY_OAK.leaves().get().defaultBlockState().setValue(SpookyOakLeaves.DISTANCE, 1), 90)
                                        .add(Blocks.COBWEB.defaultBlockState(), 10)
                                        .build()),
                                new DarkOakFoliagePlacer(UniformInt.of(0, 4), ConstantInt.of(0)),
                                new ThreeLayersFeatureSize(1, 1, 0, 1, 2, OptionalInt.empty())
                        ).build()
                )
        );
    }

    public static void placedFeatures(BootstapContext<PlacedFeature> context) {
        context.register(CommonClass.SPOOKY_OAK_TREE, new PlacedFeature(context.lookup(Registries.CONFIGURED_FEATURE).get(CommonClass.SPOOKY_OAK_TREE_CF).get(),
                        List.of(
                                CountPlacement.of(new WeightedListInt(SimpleWeightedRandomList.<IntProvider>builder()
                                        .add(ConstantInt.of(10), 9)
                                        .add(ConstantInt.of(11), 1)
                                        .build())),
                                InSquarePlacement.spread(),
                                SurfaceWaterDepthFilter.forMaxDepth(0),
                                HeightmapPlacement.onHeightmap(Heightmap.Types.OCEAN_FLOOR),
                                BiomeFilter.biome(),
                                BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(BlockInit.SPOOKY_OAK.sapling().get().defaultBlockState(), Vec3i.ZERO))
                        )
                )
        );
    }

    public static void biomes(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder builder = new MobSpawnSettings.Builder();
        CommonSpawning.SPOOKY_OAK_FOREST_SPAWNS.forEach(spawnerData -> builder.addSpawn(MobCategory.MONSTER, spawnerData));
        context.register(CommonClass.SPOOKY_OAK_FOREST,
                new Biome.BiomeBuilder()
                        .specialEffects(
                                new BiomeSpecialEffects.Builder()
                                        .skyColor(11826993)
                                        .fogColor(14126598)
                                        .waterColor(11948056)
                                        .waterFogColor(11948056)
                                        .grassColorOverride(15237409)
                                        .grassColorModifier(BiomeSpecialEffects.GrassColorModifier.DARK_FOREST)
                                        .build()
                        )
                        .hasPrecipitation(true)
                        .temperature(0.7F)
                        .downfall(0.8F)
                        .mobSpawnSettings(
                                builder.build()
                        )
                        .generationSettings(
                                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER))
                                        .addFeature(GenerationStep.Decoration.LAKES, context.lookup(Registries.PLACED_FEATURE).get(MiscOverworldPlacements.LAKE_LAVA_UNDERGROUND).get())
                                        .addFeature(GenerationStep.Decoration.LAKES, context.lookup(Registries.PLACED_FEATURE).get(MiscOverworldPlacements.LAKE_LAVA_SURFACE).get())
                                        .addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, context.lookup(Registries.PLACED_FEATURE).get(CavePlacements.AMETHYST_GEODE).get())
                                        .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, context.lookup(Registries.PLACED_FEATURE).get(OrePlacements.ORE_DIRT).get())
                                        .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, context.lookup(Registries.PLACED_FEATURE).get(OrePlacements.ORE_GRAVEL).get())
                                        .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, context.lookup(Registries.PLACED_FEATURE).get(OrePlacements.ORE_GRANITE_UPPER).get())
                                        .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, context.lookup(Registries.PLACED_FEATURE).get(OrePlacements.ORE_GRANITE_LOWER).get())
                                        .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, context.lookup(Registries.PLACED_FEATURE).get(OrePlacements.ORE_DIORITE_UPPER).get())
                                        .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, context.lookup(Registries.PLACED_FEATURE).get(OrePlacements.ORE_DIORITE_LOWER).get())
                                        .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, context.lookup(Registries.PLACED_FEATURE).get(OrePlacements.ORE_ANDESITE_UPPER).get())
                                        .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, context.lookup(Registries.PLACED_FEATURE).get(OrePlacements.ORE_ANDESITE_LOWER).get())
                                        .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, context.lookup(Registries.PLACED_FEATURE).get(OrePlacements.ORE_TUFF).get())
                                        .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, context.lookup(Registries.PLACED_FEATURE).get(OrePlacements.ORE_COAL_UPPER).get())
                                        .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, context.lookup(Registries.PLACED_FEATURE).get(OrePlacements.ORE_COAL_LOWER).get())
                                        .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, context.lookup(Registries.PLACED_FEATURE).get(OrePlacements.ORE_IRON_UPPER).get())
                                        .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, context.lookup(Registries.PLACED_FEATURE).get(OrePlacements.ORE_IRON_MIDDLE).get())
                                        .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, context.lookup(Registries.PLACED_FEATURE).get(OrePlacements.ORE_IRON_SMALL).get())
                                        .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, context.lookup(Registries.PLACED_FEATURE).get(OrePlacements.ORE_GOLD).get())
                                        .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, context.lookup(Registries.PLACED_FEATURE).get(OrePlacements.ORE_GOLD_LOWER).get())
                                        .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, context.lookup(Registries.PLACED_FEATURE).get(OrePlacements.ORE_REDSTONE).get())
                                        .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, context.lookup(Registries.PLACED_FEATURE).get(OrePlacements.ORE_REDSTONE_LOWER).get())
                                        .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, context.lookup(Registries.PLACED_FEATURE).get(OrePlacements.ORE_DIAMOND).get())
                                        .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, context.lookup(Registries.PLACED_FEATURE).get(OrePlacements.ORE_DIAMOND_LARGE).get())
                                        .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, context.lookup(Registries.PLACED_FEATURE).get(OrePlacements.ORE_DIAMOND_BURIED).get())
                                        .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, context.lookup(Registries.PLACED_FEATURE).get(OrePlacements.ORE_LAPIS).get())
                                        .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, context.lookup(Registries.PLACED_FEATURE).get(OrePlacements.ORE_LAPIS_BURIED).get())
                                        .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, context.lookup(Registries.PLACED_FEATURE).get(OrePlacements.ORE_COPPER).get())
                                        .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, context.lookup(Registries.PLACED_FEATURE).get(CavePlacements.UNDERWATER_MAGMA).get())
                                        .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, context.lookup(Registries.PLACED_FEATURE).get(MiscOverworldPlacements.DISK_SAND).get())
                                        .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, context.lookup(Registries.PLACED_FEATURE).get(MiscOverworldPlacements.DISK_CLAY).get())
                                        .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, context.lookup(Registries.PLACED_FEATURE).get(MiscOverworldPlacements.DISK_GRAVEL).get())
                                        .addFeature(GenerationStep.Decoration.FLUID_SPRINGS, context.lookup(Registries.PLACED_FEATURE).get(MiscOverworldPlacements.SPRING_WATER).get())
                                        .addFeature(GenerationStep.Decoration.FLUID_SPRINGS, context.lookup(Registries.PLACED_FEATURE).get(MiscOverworldPlacements.SPRING_LAVA).get())
                                        .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, context.lookup(Registries.PLACED_FEATURE).get(CavePlacements.GLOW_LICHEN).get())
                                        .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, context.lookup(Registries.PLACED_FEATURE).get(VegetationPlacements.FOREST_FLOWERS).get())
                                        .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, context.lookup(Registries.PLACED_FEATURE).get(VegetationPlacements.FLOWER_DEFAULT).get())
                                        .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, context.lookup(Registries.PLACED_FEATURE).get(CommonClass.SPOOKY_OAK_TREE).get())
                                        .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, context.lookup(Registries.PLACED_FEATURE).get(VegetationPlacements.PATCH_GRASS_FOREST).get())
                                        .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, context.lookup(Registries.PLACED_FEATURE).get(VegetationPlacements.BROWN_MUSHROOM_NORMAL).get())
                                        .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, context.lookup(Registries.PLACED_FEATURE).get(VegetationPlacements.RED_MUSHROOM_NORMAL).get())
                                        .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, context.lookup(Registries.PLACED_FEATURE).get(VegetationPlacements.PATCH_SUGAR_CANE).get())
                                        .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, context.lookup(Registries.PLACED_FEATURE).get(VegetationPlacements.PATCH_PUMPKIN).get())
                                        .build()
                        ).build()
        );
    }
}
