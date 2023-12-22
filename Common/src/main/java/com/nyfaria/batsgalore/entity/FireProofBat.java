package com.nyfaria.batsgalore.entity;

import com.nyfaria.batsgalore.entity.api.ModBat;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class FireProofBat extends ModBat {
    public FireProofBat(EntityType<? extends ModBat> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public boolean fireImmune() {
        return true;
    }
}
