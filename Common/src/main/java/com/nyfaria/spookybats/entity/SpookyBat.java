package com.nyfaria.spookybats.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

public class SpookyBat extends Bat {
    public SpookyBat(EntityType<? extends Bat> entityType, Level level) {
        super(entityType, level);
    }

    public static <T extends Mob> boolean checkSpookyBatSpawnRules(EntityType<T> tEntityType, ServerLevelAccessor serverLevelAccessor, MobSpawnType spawnType, BlockPos pos, RandomSource randomSource) {
        return true;
    }
}
