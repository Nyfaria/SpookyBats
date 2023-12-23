package com.nyfaria.batsgalore.item;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Equipable;
import net.minecraft.world.item.Item;

public class CosmeticItem extends Item implements Equipable {
    private final EquipmentSlot slot;
    public CosmeticItem(Properties properties, EquipmentSlot slot) {
        super(properties);
        this.slot = slot;
    }

    @Override
    public EquipmentSlot getEquipmentSlot() {
        return slot;
    }
}
