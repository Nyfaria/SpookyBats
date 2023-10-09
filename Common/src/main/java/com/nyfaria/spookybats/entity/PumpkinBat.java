package com.nyfaria.spookybats.entity;

import com.nyfaria.spookybats.entity.api.SpookyBat;
import com.nyfaria.spookybats.entity.projectile.JackOLanternProjectile;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.level.Level;

public class PumpkinBat extends SpookyBat implements RangedAttackMob {
	public PumpkinBat(EntityType<? extends SpookyBat> entityType, Level level) {
		super(entityType, level);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new RangedAttackGoal(this, 1.0D, 20/*(20*60) * 2*/, 15.0F));
		this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, LivingEntity.class, false, entity->entity.getItemBySlot(EquipmentSlot.HEAD).isEmpty()));
	}

	@Override
	public void performRangedAttack(LivingEntity target, float velocity) {
		JackOLanternProjectile abstractarrowentity = new JackOLanternProjectile(this.level(), this);
		double d0 = target.getX() - this.getX();
		double d1 = target.getY(0.3333333333333333D) - abstractarrowentity.getY();
		double d2 = target.getZ() - this.getZ();
		double d3 = Math.sqrt(d0 * d0 + d2 * d2);
		abstractarrowentity.shoot(d0, d1 + d3 * (double) 0.2F, d2, 1.6F, (float) (14 - this.level().getDifficulty().getId() * 4));
		this.playSound(SoundEvents.SKELETON_SHOOT, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
		this.level().addFreshEntity(abstractarrowentity);
		this.setTarget(null);
	}
}
