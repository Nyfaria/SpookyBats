package com.nyfaria.batsgalore.init;

import com.nyfaria.batsgalore.Constants;
import com.nyfaria.batsgalore.registration.RegistrationProvider;
import com.nyfaria.batsgalore.registration.RegistryObject;
import com.nyfaria.batsgalore.worldgen.tree.WhitePineFoliagePlacer;
import com.nyfaria.batsgalore.worldgen.tree.WhitePineTrunkPlacer;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

public class WorldGenInit {

    public static void loadClass(){}
    public static RegistrationProvider<FoliagePlacerType<?>> FOLIAGE_PLACER_TYPES = RegistrationProvider.get(Registries.FOLIAGE_PLACER_TYPE, Constants.MODID);
    public static RegistrationProvider<TrunkPlacerType<?>> TRUNK_PLACER_TYPES = RegistrationProvider.get(Registries.TRUNK_PLACER_TYPE, Constants.MODID);

    public static RegistryObject<FoliagePlacerType<WhitePineFoliagePlacer>> WHITE_PINE_FOLIAGE_PLACER = FOLIAGE_PLACER_TYPES.register("white_pine_foliage_placer", () -> new FoliagePlacerType<>(WhitePineFoliagePlacer.CODEC));
    public static RegistryObject<TrunkPlacerType<WhitePineTrunkPlacer>> WHITE_PINE_TRUNK_PLACER = TRUNK_PLACER_TYPES.register("white_pine_foliage_placer", () -> new TrunkPlacerType<>(WhitePineTrunkPlacer.CODEC));
}
