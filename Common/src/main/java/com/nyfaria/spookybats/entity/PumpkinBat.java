package com.nyfaria.spookybats.entity;

import com.nyfaria.spookybats.entity.ai.ShootPumpkinGoal;
import com.nyfaria.spookybats.entity.api.SpookyBat;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PowerableMob;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.level.Level;

public class PumpkinBat extends MonsterBat {
	public PumpkinBat(EntityType<? extends SpookyBat> entityType, Level level) {
		super(entityType, level);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new ShootPumpkinGoal(this));
		this.targetSelector.addGoal(0, new HurtByTargetGoal(this));
	}

	@Override
	protected boolean isSunSensitive() {
		return false;
	}
}
