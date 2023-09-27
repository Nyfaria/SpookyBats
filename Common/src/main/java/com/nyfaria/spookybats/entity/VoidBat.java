package com.nyfaria.spookybats.entity;

import com.nyfaria.spookybats.entity.api.SpookyBat;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.level.Level;

public class VoidBat extends SpookyBat {
	public VoidBat(EntityType<? extends SpookyBat> entityType, Level level) {
		super(entityType, level);
	}

	@Override
	public boolean hurt(DamageSource $$0, float $$1) {
		LivingEntity attacker = this.getLastHurtByMob();
		boolean canTeleport = attacker != null && attacker.level().dimension() != Level.END;
		if (canTeleport)
			attacker.changeDimension(attacker.getServer().getLevel(Level.END));

		return super.hurt($$0, $$1);
	}

}