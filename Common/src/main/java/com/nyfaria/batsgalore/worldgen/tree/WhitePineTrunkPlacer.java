package com.nyfaria.batsgalore.worldgen.tree;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.nyfaria.batsgalore.init.WorldGenInit;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

public class WhitePineTrunkPlacer extends StraightTrunkPlacer {
    public static final Codec<WhitePineTrunkPlacer> CODEC = RecordCodecBuilder.create(($$0) -> {
        return trunkPlacerParts($$0).apply($$0, WhitePineTrunkPlacer::new);
    });
    public WhitePineTrunkPlacer() {
    this(0,0,0);
    }
    public WhitePineTrunkPlacer(int i, int i1, int i2){
        super(0,0,0);
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return WorldGenInit.WHITE_PINE_TRUNK_PLACER.get();
    }

    @Override
    public int getTreeHeight(RandomSource randomSource) {
        return randomSource.nextBoolean() ? 7 : 10;
    }
}
