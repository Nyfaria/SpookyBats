package com.nyfaria.batsgalore.entity;

import com.nyfaria.batsgalore.entity.api.ModBoatType;
import com.nyfaria.batsgalore.init.BlockInit;
import com.nyfaria.batsgalore.init.EntityInit;
import com.nyfaria.batsgalore.init.ItemInit;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class ModBoat extends Boat {
    private static final EntityDataAccessor<Integer> DATA_ID_HURT = SynchedEntityData.defineId(ModBoat.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DATA_ID_HURTDIR = SynchedEntityData.defineId(ModBoat.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Float> DATA_ID_DAMAGE = SynchedEntityData.defineId(ModBoat.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Integer> DATA_ID_TYPE = SynchedEntityData.defineId(ModBoat.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> DATA_ID_PADDLE_LEFT = SynchedEntityData.defineId(ModBoat.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> DATA_ID_PADDLE_RIGHT = SynchedEntityData.defineId(ModBoat.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> DATA_ID_BUBBLE_TIME = SynchedEntityData.defineId(ModBoat.class, EntityDataSerializers.INT);
    public ModBoat(EntityType<? extends ModBoat> $$0, Level $$1) {
        super($$0, $$1);
    }

    public ModBoat(Level pLevel, double pX, double pY, double pZ) {
        this(EntityInit.MOD_BOAT.get(), pLevel);
        this.setPos(pX, pY, pZ);
        this.xo = pX;
        this.yo = pY;
        this.zo = pZ;
    }
    protected void defineSynchedData() {
        this.entityData.define(DATA_ID_HURT, 0);
        this.entityData.define(DATA_ID_HURTDIR, 1);
        this.entityData.define(DATA_ID_DAMAGE, 0.0F);
        this.entityData.define(DATA_ID_TYPE, ModBoatType.SPOOKY_OAK.ordinal());
        this.entityData.define(DATA_ID_PADDLE_LEFT, false);
        this.entityData.define(DATA_ID_PADDLE_RIGHT, false);
        this.entityData.define(DATA_ID_BUBBLE_TIME, 0);
    }
    public void setHurtTime(int pHurtTime) {
        this.entityData.set(DATA_ID_HURT, pHurtTime);
    }
    public int getHurtTime() {
        return this.entityData.get(DATA_ID_HURT);
    }
    public void setHurtDir(int pHurtDirection) {
        this.entityData.set(DATA_ID_HURTDIR, pHurtDirection);
    }

    /**
     * Gets the forward direction of the entity.
     */
    public int getHurtDir() {
        return this.entityData.get(DATA_ID_HURTDIR);
    }

    public void setDamage(float pDamageTaken) {
        this.entityData.set(DATA_ID_DAMAGE, pDamageTaken);
    }

    /**
     * Gets the damage taken from the last hit.
     */
    public float getDamage() {
        return this.entityData.get(DATA_ID_DAMAGE);
    }
    public void setModVariant(ModBoatType pVariant) {
        this.entityData.set(DATA_ID_TYPE, pVariant.ordinal());
    }

    public ModBoatType getModVariant() {
        return ModBoatType.byId(this.entityData.get(DATA_ID_TYPE));
    }
    public void setPaddleState(boolean pLeft, boolean pRight) {
        this.entityData.set(DATA_ID_PADDLE_LEFT, pLeft);
        this.entityData.set(DATA_ID_PADDLE_RIGHT, pRight);
    }
    public boolean getPaddleState(int pSide) {
        return this.entityData.<Boolean>get(pSide == 0 ? DATA_ID_PADDLE_LEFT : DATA_ID_PADDLE_RIGHT) && this.getControllingPassenger() != null;
    }
    public void setBubbleTime(int pBubbleTime) {
        this.entityData.set(DATA_ID_BUBBLE_TIME, pBubbleTime);
    }

    public int getBubbleTime() {
        return this.entityData.get(DATA_ID_BUBBLE_TIME);
    }
    public double getPassengersRidingOffset() {
        return -0.1D;
    }
    public @NotNull Item getDropItem() {
        return switch (this.getModVariant()) {
            case WHITE_PINE -> BlockInit.WHITE_PINE.boat().get();
            default -> BlockInit.SPOOKY_OAK.boat().get();
        };
    }
    protected void addAdditionalSaveData(CompoundTag pCompound) {
        pCompound.putString("Type", this.getModVariant().getSerializedName());
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    protected void readAdditionalSaveData(CompoundTag pCompound) {
        if (pCompound.contains("Type", 8)) {
            this.setModVariant(ModBoatType.byName(pCompound.getString("Type")));
        }
    }
}
