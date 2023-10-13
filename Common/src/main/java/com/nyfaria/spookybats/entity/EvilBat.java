package com.nyfaria.spookybats.entity;

import com.nyfaria.spookybats.entity.api.SpookyBat;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.level.Level;

public class EvilBat extends MonsterBat implements Enemy {
	public EvilBat(EntityType<? extends SpookyBat> entityType, Level level) {
		super(entityType, level);
		this.xpReward = 5;
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new MeleeAttackGoal(this, 1, true));
		this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, LivingEntity.class, true));
	}

	public static AttributeSupplier.Builder createEvilBatAttributes() {
		return SpookyBat
			.createBatAttributes()
			.add(Attributes.ATTACK_DAMAGE, 1);
	}

	@Override
	protected boolean isSunSensitive() {
		return false;
	}
}
