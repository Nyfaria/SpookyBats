package com.nyfaria.spookybats.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PowerableMob;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.level.Level;

public class CreeperBat extends SpookyBat implements PowerableMob {
    public CreeperBat(EntityType<? extends Bat> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public boolean isPowered() {
        return tickCount % 1200 < 600;
    }
}
