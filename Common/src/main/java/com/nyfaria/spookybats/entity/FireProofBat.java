package com.nyfaria.spookybats.entity;

import com.nyfaria.spookybats.entity.api.SpookyBat;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.level.Level;

public class FireProofBat extends SpookyBat {
    public FireProofBat(EntityType<? extends SpookyBat> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public boolean fireImmune() {
        return true;
    }
}
