package com.nyfaria.spookybats.entity;

import com.mojang.blaze3d.shaders.Effect;
import com.nyfaria.spookybats.entity.api.SpookyBat;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.FleeSunGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class GhostBat extends SpookyBat {
	public GhostBat(EntityType<? extends SpookyBat> pEntityType, Level pLevel) {
		super(pEntityType, pLevel);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new FleeSunGoal(this, 1.3f));
		this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Monster.class, 7f, 1, 1.4f));
	}

	public static AttributeSupplier.Builder createGhostBatAttributes() {
		return SpookyBat
			.createBatAttributes()
			.add(Attributes.MAX_HEALTH, 2);
	}

	@Override
	public boolean hurt(DamageSource pSource, float pAmount) {
		if (this.getLastHurtByMob() != null) {
			LivingEntity attacker = this.getLastHurtByMob();
			attacker.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 20 * 2, 0));
		}

		return super.hurt(pSource, pAmount);
	}

	@Override
	public boolean spawnsGhostBat() {
		return false;
	}
}