package com.nyfaria.batsgalore.entity;

import com.nyfaria.batsgalore.entity.api.ModBat;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.OcelotAttackGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class PlayerBat extends ModBat {
	public PlayerBat(EntityType<? extends ModBat> pEntityType, Level pLevel) {
		super(pEntityType, pLevel);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Monster.class, 7f, 1, 1.4f));
		this.goalSelector.addGoal(0, new OcelotAttackGoal(this));
		this.targetSelector.addGoal(0, new HurtByTargetGoal(this));
	}

	@Override
	public boolean isAggressive() {
		return this.getLastHurtByMob() != null;
	}

	public static AttributeSupplier.Builder createPlayerBatAttributes() {
		return ModBat
			.createBatAttributes()
			.add(Attributes.ATTACK_DAMAGE, 1);
	}
}
