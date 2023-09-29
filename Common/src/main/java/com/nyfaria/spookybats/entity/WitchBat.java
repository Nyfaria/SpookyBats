package com.nyfaria.spookybats.entity;

import com.nyfaria.spookybats.entity.api.SpookyBat;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class WitchBat extends SpookyBat {
	public WitchBat(EntityType<? extends SpookyBat> pEntityType, Level pLevel) {
		super(pEntityType, pLevel);
	}

	@Override
	public void tick() {
		super.tick();

		if (this.getHealth() < this.getMaxHealth() / 3) {
			if (this.random.nextInt(1) == 0) {
				this.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 20*2, 0));
			} else {
				this.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 20*2, 0));
			}
		}
	}
}
