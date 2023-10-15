package com.nyfaria.spookybats.entity;

import com.nyfaria.spookybats.entity.api.LivingMount;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.PlayerRideable;
import net.minecraft.world.entity.PlayerRideableJumping;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FollowOwnerGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class WitchsBroom extends TamableAnimal implements PlayerRideableJumping, LivingMount {

    protected float playerJumpPendingScale;
    @Nullable
    private UUID owner;
    protected boolean isJumping;

    public WitchsBroom(EntityType<? extends TamableAnimal> $$0, Level $$1) {
        super($$0, $$1);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FollowOwnerGoal(this, 1.3D, 2.5f, 2.0F, true));
    }


    @Override
    protected float getRiddenSpeed(Player player) {
        return onGround() ? 0.3f : player.isSprinting() ? 1.5F : 1.0F;
    }

    @Override
    public InteractionResult mobInteract(Player pPlayer, InteractionHand $$1) {
        this.doPlayerRide(pPlayer);
        return InteractionResult.sidedSuccess(this.level().isClientSide);
    }

    protected void doPlayerRide(Player pPlayer) {
        if (!this.level().isClientSide) {
            pPlayer.setYRot(this.getYRot());
            pPlayer.setXRot(this.getXRot());
            pPlayer.startRiding(this);
        }
    }

    @Nullable
    @Override
    public UUID getOwnerUUID() {
        return owner;
    }

    public void setOwnerUUID(@Nullable UUID pUuid) {
        this.owner = pUuid;
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return null;
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        if (this.owner != null) {
            tag.putUUID("Owner", this.owner);
        }
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        UUID uuid = null;
        if (tag.hasUUID("Owner")) {
            uuid = tag.getUUID("Owner");
        }
        if (uuid != null) {
            setOwnerUUID(uuid);
        }
    }

    @Override
    public boolean shouldFollow() {
        return !this.isVehicle();
    }

    @Override
    public void onPlayerJump(int pJumpPower) {
        if (pJumpPower < 0) {
            pJumpPower = 0;
        }

        if (pJumpPower >= 90) {
            this.playerJumpPendingScale = 1.0F;
        } else {
            this.playerJumpPendingScale = 0.4F + 0.4F * (float) pJumpPower / 90.0F;
        }
    }

    @Override
    public boolean canJump() {
        return true;
    }

    @Override
    public void handleStartJump(int i) {

    }

    @Override
    public void handleStopJump() {

    }

    @Override
    public boolean isControlledByLocalInstance() {
        return super.isControlledByLocalInstance();
    }

    protected Vec2 getRiddenRotation(LivingEntity pEntity) {
        return new Vec2(pEntity.getXRot() * 0.5F, pEntity.getYRot());
    }

    public boolean isJumping() {
        return this.isJumping;
    }

    protected void tickRidden(Player pPlayer, Vec3 pTravelVector) {
        super.tickRidden(pPlayer, pTravelVector);
        Vec2 vec2 = this.getRiddenRotation(pPlayer);
        this.setRot(vec2.y, vec2.x);
        this.yRotO = this.yBodyRot = this.yHeadRot = this.getYRot();
        if (this.isControlledByLocalInstance()) {

            if (this.onGround()) {
                this.setIsJumping(false);
                if (this.playerJumpPendingScale > 0.0F && !this.isJumping()) {
                    this.executeRidersJump(this.playerJumpPendingScale, pTravelVector);
                }

                this.playerJumpPendingScale = 0.0F;
            }
        }

    }

    @Override
    protected void checkFallDamage(double $$0, boolean $$1, BlockState $$2, BlockPos $$3) {

    }

    @Override
    public boolean causeFallDamage(float $$0, float $$1, DamageSource $$2) {
        return false;
    }

    @Override
    public void tick() {
        super.tick();
        if (!level().isClientSide) {
            if (!hasEffect(MobEffects.SLOW_FALLING)) {
                addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, -1, 0, false, false));
            }
        }
    }

    protected @NotNull Vec3 getRiddenInput(Player pPlayer, Vec3 pTravelVector) {
        float f = pPlayer.xxa * 0.5F;
        float f1 = pPlayer.zza;
        if (f1 <= 0.0F) {
            f1 *= 0.25F;
        }
        return new Vec3((double) f, 0.0D, (double) f1);
    }

    public void setIsJumping(boolean pJumping) {
        this.isJumping = pJumping;
    }

    public double getCustomJump() {
        return this.getAttributeValue(Attributes.JUMP_STRENGTH);
    }

    @Override
    public boolean isInvulnerable() {
        return isVehicle();
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    protected void executeRidersJump(float pPlayerJumpPendingScale, Vec3 pTravelVector) {
        double d0 = this.getCustomJump() * (double) pPlayerJumpPendingScale * (double) this.getBlockJumpFactor();
        double d1 = d0 + (double) this.getJumpBoostPower();
        Vec3 vec3 = this.getDeltaMovement();
        this.setDeltaMovement(vec3.x, d1, vec3.z);
        this.setIsJumping(true);
        this.hasImpulse = true;
        if (pTravelVector.z > 0.0D) {
            float f = Mth.sin(this.getYRot() * ((float) Math.PI / 180F));
            float f1 = Mth.cos(this.getYRot() * ((float) Math.PI / 180F));
            this.setDeltaMovement(this.getDeltaMovement().add((double) (-0.4F * f * pPlayerJumpPendingScale), 0.0D, (double) (0.4F * f1 * pPlayerJumpPendingScale)));
        }

    }

    public LivingEntity getControllingPassenger() {
        Entity entity = this.getFirstPassenger();
        if (entity instanceof Mob) {
            return (Mob) entity;
        } else {
            entity = this.getFirstPassenger();
            if (entity instanceof Player) {
                return (Player) entity;
            }
            return null;
        }
    }

    @Override
    public boolean shouldShowName() {
        return false;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.JUMP_STRENGTH).add(Attributes.MAX_HEALTH, 0.5).add(Attributes.MOVEMENT_SPEED, 0.22499999403953552);
    }
}
