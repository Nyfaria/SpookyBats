package com.nyfaria.spookybats.mixins;

import com.nyfaria.spookybats.item.BatWings;
import net.fabricmc.fabric.api.entity.event.v1.FabricElytraItem;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BatWings.class)
public class BatWingsMixin implements FabricElytraItem {
    @Override
    public boolean useCustomElytra(LivingEntity entity, ItemStack chestStack, boolean tickElytra) {
        return true;
    }
}
