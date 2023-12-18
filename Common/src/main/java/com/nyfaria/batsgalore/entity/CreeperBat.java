package com.nyfaria.batsgalore.entity;

import com.nyfaria.batsgalore.entity.api.SpookyBat;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PowerableMob;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.animal.Ocelot;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;

public class CreeperBat extends MonsterBat implements PowerableMob {

	public CreeperBat(EntityType<? extends SpookyBat> entityType, Level level) {
		super(entityType, level);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Ocelot.class, 6.0F, 1.0, 1.2));
		this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Cat.class, 6.0F, 1.0, 1.2));
	}

	@Override
	public boolean isPowered() {
		return tickCount % 1200 < 600;
	}

	@Override
	protected boolean isSunSensitive() {
		return false;
	}

	@Override
	protected void tickDeath() {
		if (isPowered() && deathTime == 5) {
			boolean isMobExplosionType = this.level().getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING);

			Level.ExplosionInteraction destructionType = isMobExplosionType
				? Level.ExplosionInteraction.MOB
				: Level.ExplosionInteraction.NONE;

			if (!this.level().isClientSide) {
				this.level().explode(this, this.getX(), this.getY(), this.getZ(), 1, destructionType);
			}
		}

		super.tickDeath();
	}
}