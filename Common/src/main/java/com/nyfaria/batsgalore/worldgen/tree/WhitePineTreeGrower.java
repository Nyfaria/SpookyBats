package com.nyfaria.batsgalore.worldgen.tree;

import com.nyfaria.batsgalore.CommonClass;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractMegaTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

public class WhitePineTreeGrower extends AbstractMegaTreeGrower {

    @Nullable
    @Override
    protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredMegaFeature(RandomSource randomSource) {
        return CommonClass.MEGA_WHITE_PINE_TREE_CF;
    }

    @Nullable
    @Override
    protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource randomSource, boolean b) {
        return CommonClass.WHITE_PINE_TREE_CF;
    }
}
