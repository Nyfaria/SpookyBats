package com.nyfaria.spookybats.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.level.Level;

public class SpookyBat extends Bat {
    public SpookyBat(EntityType<? extends Bat> entityType, Level level) {
        super(entityType, level);
    }

}
