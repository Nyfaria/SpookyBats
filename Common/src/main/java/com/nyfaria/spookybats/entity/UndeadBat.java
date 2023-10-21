package com.nyfaria.spookybats.entity;

import com.nyfaria.spookybats.entity.api.SpookyBat;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.level.Level;

public class UndeadBat extends FireProofBat implements RangedAttackMob {
	private int shootCooldown = 1;

	public UndeadBat(EntityType<? extends SpookyBat> entityType, Level level) {
		super(entityType, level);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new RangedAttackGoal(this, 1.0D, 20, 15.0F));
		this.targetSelector.addGoal(0, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, Ghast.class, false));
	}

	@Override
	public void performRangedAttack(LivingEntity target, float velocity) {
		if (shootCooldown % 2 == 0) {
			double $$5 = target.getX() - (this.getX() + this.getViewVector(1).x * 4.0);
			double $$6 = target.getY(0.5) - (0.5 + this.getY(0.5));
			double $$7 = target.getZ() - (this.getZ() + this.getViewVector(1).z * 4.0);

			LargeFireball fireball = new LargeFireball(this.level(), this, $$5, $$6, $$7, 0);

			double d0 = target.getX() - this.getX();
			double d1 = target.getY(0.3333333333333333D) - fireball.getY();
			double d2 = target.getZ() - this.getZ();
			double d3 = Math.sqrt(d0 * d0 + d2 * d2);

			fireball.shoot(d0, d1 + d3 * (double) 0.2F, d2, 1.6F, (float) (14 - this.level().getDifficulty().getId() * 4));
			this.playSound(SoundEvents.BLAZE_SHOOT, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
			this.level().addFreshEntity(fireball);

			this.shootCooldown = 1;
		} else {
			this.shootCooldown++;
		}
	}
}
