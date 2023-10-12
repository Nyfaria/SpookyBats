package com.nyfaria.spookybats.entity;

import com.nyfaria.spookybats.entity.api.SpookyBat;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.level.Level;

public class WitchBat extends SpookyBat implements Enemy {
	public WitchBat(EntityType<? extends SpookyBat> pEntityType, Level pLevel) {
		super(pEntityType, pLevel);
	}

	@Override
	public boolean hurt(DamageSource pSource, float pAmount) {
		if (this.getHealth() < this.getMaxHealth() / 2) {
			if (this.random.nextInt(1) == 0) {
				this.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 20*2, 0));
			} else {
				this.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 20*2, 0));
			}
		}

		if (this.getHealth() < this.getMaxHealth() / 4) {
			if (this.random.nextInt(4) == 0) {
				this.addEffect(new MobEffectInstance(MobEffects.HEALTH_BOOST, 20*2, 3));
			}
		}

		if (this.getHealth() == 1) {
			if (this.random.nextInt(2) == 0) {
				this.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 20*6, 1));
			}
		}

		return super.hurt(pSource, pAmount);
	}

	public static AttributeSupplier.Builder createWitchBatAttributes() {
		return SpookyBat
			.createBatAttributes()
			.add(Attributes.MAX_HEALTH, 14);
	}
}