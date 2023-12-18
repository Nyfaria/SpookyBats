package com.nyfaria.batsgalore.entity.api;

import net.minecraft.world.entity.LivingEntity;

public interface Minion<T extends LivingEntity & Master> {
    void setMaster(T master);
    T getMaster();
}
