package com.nyfaria.spookybats.entity;

import com.nyfaria.spookybats.entity.ai.ShootPumpkinGoal;
import com.nyfaria.spookybats.entity.api.SpookyBat;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PowerableMob;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class PumpkinBat extends MonsterBat implements RangedAttackMob {
	public PumpkinBat(EntityType<? extends SpookyBat> entityType, Level level) {
		super(entityType, level);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
//		this.goalSelector.addGoal(0, new ShootPumpkinGoal(this));
		this.targetSelector.addGoal(0, new HurtByTargetGoal(this));
	}

	@Override
	protected boolean isSunSensitive() {
		return false;
	}

	@Override
	public void performRangedAttack(LivingEntity target, float velocity) {
//		ItemStack itemStack = new ItemStack(Items.PUMPKIN);
//		AbstractArrow abstractArrow = ProjectileUtil.getMobArrow(this, itemStack, velocity);
//		double d = target.getX() - this.getX();
//		double e = target.getY(0.3333333333333333) - abstractArrow.getY();
//		double f = target.getZ() - this.getZ();
//		double g = Math.sqrt(d * d + f * f);
//		abstractArrow.shoot(d, e + g * 0.20000000298023224, f, 1.6F, (float)(14 - this.level().getDifficulty().getId() * 4));
//		this.playSound(SoundEvents.SKELETON_SHOOT, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
//		this.level().addFreshEntity(abstractArrow);
	}
}
