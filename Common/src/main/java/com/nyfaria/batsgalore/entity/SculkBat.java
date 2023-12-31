package com.nyfaria.batsgalore.entity;

import com.nyfaria.batsgalore.entity.api.ModBat;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class SculkBat extends CoreDroppingBat {
	public SculkBat(EntityType<? extends ModBat> pEntityType, Level pLevel) {
		super(pEntityType, pLevel);
	}

	@Override
	public ParticleOptions getAmbientParticle() {
		return ParticleTypes.SCULK_SOUL;
	}
}
