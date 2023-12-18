package com.nyfaria.batsgalore.worldgen.tree;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.nyfaria.batsgalore.init.WorldGenInit;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

public class WhitePineFoliagePlacer extends FoliagePlacer {
    public static final Codec<WhitePineFoliagePlacer> CODEC = RecordCodecBuilder.create((instance) -> {
        return foliagePlacerParts(instance).apply(instance, WhitePineFoliagePlacer::new);
    });

    public WhitePineFoliagePlacer(IntProvider intProvider, IntProvider intProvider2) {
        super(intProvider, intProvider2);
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return WorldGenInit.WHITE_PINE_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(LevelSimulatedReader level, FoliagePlacer.FoliageSetter blockSetter, RandomSource random, TreeConfiguration config,
                                 int maxFreeTreeHeight, FoliagePlacer.FoliageAttachment attachment, int foliageHeight, int foliageRadius, int offset) {
        BlockPos blockpos = attachment.pos();
        if(attachment.doubleTrunk()){
            for (int l = 1; l <= foliageHeight; l++) {
                int range = 7 - Mth.ceil(l / 3d);
                this.placeLeavesRow(level, blockSetter, random, config, blockpos, range + 1, l - maxFreeTreeHeight, attachment.doubleTrunk());
            }
        } else {
            int upperTrunkHeight = maxFreeTreeHeight == 10 ? 3 : 2;
            for (int l = 1; l <= foliageHeight; l++) {
                int range = 3 - Mth.ceil(l / (double) upperTrunkHeight);
                this.placeLeavesRow(level, blockSetter, random, config, blockpos, range + 1, l - maxFreeTreeHeight, attachment.doubleTrunk());
            }
        }

    }

    @Override
    public int foliageHeight(RandomSource var1, int height, TreeConfiguration config) {

        return  height == 7 ? 9 : height + 3;
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource random, int localX, int localY, int localZ, int range, boolean large) {
        if(localX == range && localZ == range && range > 0)
            return true;
        if(localX == range && localZ == range-1 && range > 1)
            return true;
        if(localX == range-1 && localZ == range && range > 1)
            return true;
        return false;
    }

}
