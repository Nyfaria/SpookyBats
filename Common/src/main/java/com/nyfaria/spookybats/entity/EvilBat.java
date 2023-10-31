package com.nyfaria.spookybats.entity;

import com.nyfaria.spookybats.entity.api.Minion;
import com.nyfaria.spookybats.entity.api.SpookyBat;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.Optional;
import java.util.UUID;

public class EvilBat extends MonsterBat implements Enemy, Minion<WingedTurmoil> {
	private static final EntityDataAccessor<Optional<UUID>> MASTER = SynchedEntityData.defineId(EvilBat.class, EntityDataSerializers.OPTIONAL_UUID);

	public EvilBat(EntityType<? extends SpookyBat> entityType, Level level) {
		super(entityType, level);
		this.xpReward = 5;
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new MeleeAttackGoal(this, 1, true));
		this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, Player.class, true));
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(MASTER, Optional.empty());
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

	@Override
	public boolean canAttack(LivingEntity $$0, TargetingConditions $$1) {
		return super.canAttack($$0, $$1) && ($$0 instanceof Player);
	}


	@Override
	public void setMaster(WingedTurmoil master) {
		this.entityData.set(MASTER, Optional.of(master.getUUID()));
	}

	@Override
	public WingedTurmoil getMaster() {
		return (WingedTurmoil) this.entityData.get(MASTER).map(uuid -> ((ServerLevel)this.level()).getEntity(uuid)).orElse(null);
	}

	@Override
	protected void spawnOnDeath() {
		if(!level().isClientSide && this.getMaster() != null && deathTime == 19) {
			this.getMaster().minionDeath();
		} else {
			super.spawnOnDeath();
		}
	}
}
