package com.nyfaria.spookybats.entity;

import com.nyfaria.spookybats.entity.api.SpookyBat;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

abstract public class CoreDroppingBat extends SpookyBat {
	public CoreDroppingBat(EntityType<? extends SpookyBat> pEntityType, Level pLevel) {
		super(pEntityType, pLevel);
	}

	@Override
	public void aiStep() {
		super.aiStep();

		Level level = this.level();

		if (level.isClientSide && this.tickCount % 10 == 0) {
			level.addParticle(getAmbientParticle(), this.getRandomX(1), this.getRandomY(), this.getRandomZ(0.2), 0.0, 0.05, 0.0);
		}
	}

	@Override
	public void tick() {
		super.tick();
		if (this.tickCount % 60 == 0) this.heal(1);
	}

	abstract public ParticleOptions getAmbientParticle();
}
