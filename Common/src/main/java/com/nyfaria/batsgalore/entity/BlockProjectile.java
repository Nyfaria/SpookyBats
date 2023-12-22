package com.nyfaria.batsgalore.entity;

import com.nyfaria.batsgalore.init.entity.EntityInit;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

import java.util.Optional;

public class BlockProjectile extends AbstractHurtingProjectile {
    private static final EntityDataAccessor<Optional<BlockState>> STATE = SynchedEntityData.defineId(BlockProjectile.class, EntityDataSerializers.OPTIONAL_BLOCK_STATE);

    private LivingEntity target;

    public BlockProjectile(EntityType<? extends BlockProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public BlockProjectile(Level pLevel, LivingEntity pShooter, LivingEntity target) {
        super(EntityInit.BLOCK_PROJECTILE.get(), pLevel);
        setOwner(pShooter);
        this.target = target;
    }

    public boolean isOnFire() {
        return false;
    }


    @Override
    public void tick() {
        super.tick();
        if(level().isClientSide)return;
        if (tickCount < 20) {
            this.setDeltaMovement(0, 0.5, 0);
        }
        if (tickCount == 20) {
            this.xPower = (target.getX() - this.getX()) / 20f;
            this.yPower = (target.getY() - this.getY()) / 20f;
            this.zPower = (target.getZ() - this.getZ()) / 20f;
        }
    }

    /**
     * Called when the arrow hits an entity
     */
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        if (!this.level().isClientSide) {
            Entity entity = pResult.getEntity();
            Entity entity1 = this.getOwner();
            boolean flag;
            if (entity1 instanceof LivingEntity) {
                LivingEntity livingentity = (LivingEntity) entity1;
                flag = entity.hurt(this.damageSources().mobProjectile(getOwner(), livingentity), 8.0F);
                if (flag) {
                    if (entity.isAlive()) {
                        this.doEnchantDamageEffects(livingentity, entity);
                    } else {
                        livingentity.heal(5.0F);
                    }
                }
            } else {
                flag = entity.hurt(this.damageSources().magic(), 5.0F);
            }


        }
    }

    /**
     * Called when this EntityFireball hits a block or entity.
     */
    protected void onHit(HitResult pResult) {
        super.onHit(pResult);
        if (!this.level().isClientSide && this.tickCount < 6 * 20) {
            this.level().explode(this, this.getX(), this.getY(), this.getZ(), 0.5F, false, Level.ExplosionInteraction.NONE);
            this.discard();
        }

    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.put("block", BlockState.CODEC.encodeStart(NbtOps.INSTANCE, this.getBlockState()).get().orThrow());
        tag.putUUID("target", target.getUUID());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.setBlockState(BlockState.CODEC.parse(NbtOps.INSTANCE, tag.get("block")).getOrThrow(false, System.err::println));
        if (level() instanceof ServerLevel serverLevel)
            this.target = (LivingEntity) serverLevel.getEntity(tag.getUUID("target"));
    }

    /**
     * Returns {@code true} if other Entities should be prevented from moving through this Entity.
     */
    public boolean isPickable() {
        return false;
    }

    /**
     * Called when the entity is attacked.
     */
    public boolean hurt(DamageSource pSource, float pAmount) {
        return false;
    }

    protected void defineSynchedData() {
        this.entityData.define(STATE, Optional.empty());
    }

    /**
     * Return whether this skull comes from an invulnerable (aura) wither boss.
     */
    public BlockState getBlockState() {
        return this.entityData.get(STATE).get();
    }

    /**
     * Set whether this skull comes from an invulnerable (aura) wither boss.
     */
    public void setBlockState(BlockState pInvulnerable) {
        this.entityData.set(STATE, Optional.of(pInvulnerable));
    }

    protected boolean shouldBurn() {
        return false;
    }
}