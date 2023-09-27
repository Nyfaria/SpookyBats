package com.nyfaria.spookybats.entity;

import com.nyfaria.spookybats.entity.api.SpookyBat;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PowerableMob;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.level.Level;

public class CreeperBat extends MonsterBat implements PowerableMob {
    public CreeperBat(EntityType<? extends SpookyBat> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public boolean isPowered() {
        return tickCount % 1200 < 600;
    }

    @Override
    protected boolean isSunSensitive() {
        return false;
    }
}
