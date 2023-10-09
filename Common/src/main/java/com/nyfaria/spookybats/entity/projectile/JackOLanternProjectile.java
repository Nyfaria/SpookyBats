package com.nyfaria.spookybats.entity.projectile;

import com.nyfaria.spookybats.init.EntityInit;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

public class JackOLanternProjectile extends ThrowableItemProjectile {
    public JackOLanternProjectile(EntityType<? extends JackOLanternProjectile> $$0, Level $$1) {
        super($$0, $$1);
    }

    public JackOLanternProjectile(Level $$0, LivingEntity $$1) {
        super(EntityInit.JACK_O_LANTERN_PROJECTILE.get(), $$1, $$0);
    }

    public JackOLanternProjectile(Level $$0, double $$1, double $$2, double $$3) {
        super(EntityInit.JACK_O_LANTERN_PROJECTILE.get(), $$1, $$2, $$3, $$0);
    }

    @Override
    protected void onHitEntity(EntityHitResult entityHitResult) {
        super.onHitEntity(entityHitResult);
        entityHitResult.getEntity().setItemSlot(EquipmentSlot.HEAD, new ItemStack(Items.JACK_O_LANTERN));

    }

    @Override
    protected Item getDefaultItem() {
        return Items.JACK_O_LANTERN;
    }
}
