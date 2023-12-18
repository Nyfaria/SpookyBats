package com.nyfaria.batsgalore.entity;

import com.nyfaria.batsgalore.entity.ai.TeleportAwayGoal;
import com.nyfaria.batsgalore.entity.api.SpookyBat;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class WitherSkeletonBat extends MonsterBat {
	public WitherSkeletonBat(EntityType<? extends SpookyBat> pEntityType, Level pLevel) {
		super(pEntityType, pLevel);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(2, new TeleportAwayGoal(this));
		this.goalSelector.addGoal(0, new MeleeAttackGoal(this, 1, true));
		this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, Player.class, true));
	}

	@Override
	public boolean doHurtTarget(Entity target) {
		boolean canAttack = super.canAttack((LivingEntity) target) && target instanceof LivingEntity;
		if (canAttack) ((LivingEntity) target).addEffect(new MobEffectInstance(MobEffects.WITHER, 50, 0), this);
		return canAttack;
	}

	@Override
	public boolean fireImmune() {
		return true;
	}

	public static AttributeSupplier.Builder createWitherSkeletonBatAttributes() {
		return SpookyBat
			.createBatAttributes()
			.add(Attributes.ATTACK_DAMAGE, 1);
	}
}
