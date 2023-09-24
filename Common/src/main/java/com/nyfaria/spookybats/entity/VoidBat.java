package com.nyfaria.spookybats.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.level.Level;

public class VoidBat extends SpookyBat {
	public VoidBat(EntityType<? extends Bat> entityType, Level level) {
		super(entityType, level);
	}

	@Override
	public void tick() {
		super.tick();

		LivingEntity attacker = this.getLastHurtByMob();
		if (attacker == null) return;
		attacker.changeDimension(this.getServer().getLevel(Level.END));
	}
}
