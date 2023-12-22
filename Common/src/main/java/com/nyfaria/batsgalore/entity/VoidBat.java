package com.nyfaria.batsgalore.entity;

import com.nyfaria.batsgalore.entity.api.ModBat;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class VoidBat extends ModBat {
	public VoidBat(EntityType<? extends ModBat> entityType, Level level) {
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