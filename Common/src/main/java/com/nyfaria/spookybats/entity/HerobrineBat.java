package com.nyfaria.spookybats.entity;

import com.nyfaria.spookybats.entity.ai.TeleportAwayGoal;
import com.nyfaria.spookybats.entity.api.SpookyBat;
import com.nyfaria.spookybats.init.EntityInit;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.Nullable;

public class HerobrineBat extends MonsterBat {
	public HerobrineBat(EntityType<? extends SpookyBat> entityType, Level level) {
		super(entityType, level);
		this.xpReward = 7;
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(1, new TeleportAwayGoal(this));
		this.goalSelector.addGoal(0, new MeleeAttackGoal(this, 1, true));
		this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, Player.class, true));
	}

	@Override
	protected boolean isSunSensitive() {
		return false;
	}

	@Override
	protected void populateDefaultEquipmentSlots(RandomSource $$0, DifficultyInstance $$1) {
		if (this.random.nextInt(2) == 0)
			this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.DIAMOND_SWORD));

		super.populateDefaultEquipmentSlots($$0, $$1);
	}

	@Nullable
	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType $$2, @Nullable SpawnGroupData $$3, @Nullable CompoundTag $$4) {
		this.populateDefaultEquipmentSlots(level.getRandom(), difficulty);
		return super.finalizeSpawn(level, difficulty, $$2, $$3, $$4);
	}

	@Override
	protected void tickDeath() {
		if (deathTime == 5 && this.random.nextInt(1) == 0) {
			EvilBat evilBat = EntityInit.EVIL_BAT.get().create(this.level());
			evilBat.moveTo(this.getX(), this.getY(), this.getZ(), this.getYRot(), 0);
			this.level().addFreshEntity(evilBat);
		}

		super.tickDeath();
	}

	@Override
	public boolean spawnsGhostBat() {
		return false;
	}

	@Override
	public boolean isAggressive() {
		return true;
	}

	public static AttributeSupplier.Builder createHerobrineBatAttributes() {
		return SpookyBat
			.createBatAttributes()
			.add(Attributes.ATTACK_DAMAGE, 2);
	}
}