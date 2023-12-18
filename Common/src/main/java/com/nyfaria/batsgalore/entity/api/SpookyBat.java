package com.nyfaria.batsgalore.entity.api;

import com.nyfaria.batsgalore.entity.SlimeBat;
import com.nyfaria.batsgalore.entity.ai.control.BatMoveControl;
import com.nyfaria.batsgalore.init.EntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.data.worldgen.StructureSets;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BiomeTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.structure.BuiltinStructures;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public class SpookyBat extends PathfinderMob {
    public static final float FLAP_DEGREES_PER_TICK = 74.48451F;
    public static final int TICKS_PER_FLAP = Mth.ceil(2.4166098F);
    private static final EntityDataAccessor<Byte> DATA_ID_FLAGS = SynchedEntityData.defineId(SpookyBat.class, EntityDataSerializers.BYTE);
    private static final int FLAG_RESTING = 1;
    private static final TargetingConditions BAT_RESTING_TARGETING = TargetingConditions.forNonCombat().range(4.0D);

    public SpookyBat(EntityType<? extends SpookyBat> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.moveControl = new BatMoveControl(this, 10);
        if (!pLevel.isClientSide) {
            this.setResting(true);
        }

        this.xpReward = 2;
    }

    public boolean isFlapping() {
        return !this.isResting() && this.tickCount % TICKS_PER_FLAP == 0;
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ID_FLAGS, (byte) 0);
    }

    /**
     * Returns the volume for the sounds this mob makes.
     */
    protected float getSoundVolume() {
        return 0.1F;
    }

    /**
     * Gets the pitch of living sounds in living entities.
     */
    public float getVoicePitch() {
        return super.getVoicePitch() * 0.95F;
    }

    @Nullable
    public SoundEvent getAmbientSound() {
        return this.isResting() && this.random.nextInt(4) != 0 ? null : SoundEvents.BAT_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.BAT_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.BAT_DEATH;
    }

    @Override
    protected void tickDeath() {
        spawnOnDeath();
        super.tickDeath();
    }

    protected void spawnOnDeath() {
        if (spawnsGhostBat() && deathTime == 19 && !this.level().isClientSide) {
            if (this.random.nextInt(ghostBatChance()) == 0) {
                EntityInit.GHOST_BAT.get().spawn((ServerLevel) this.level(), blockPosition(), MobSpawnType.CONVERSION);
            }
        }
    }

    public boolean spawnsGhostBat() {
        return true;
    }

    protected int ghostBatChance() {
        return 20;
    }

    /**
     * Returns {@code true} if this entity should push and be pushed by other entities when colliding.
     */
    public boolean isPushable() {
        return false;
    }

    protected void doPush(Entity pEntity) {
    }

    protected void pushEntities() {
    }

    public static AttributeSupplier.Builder createBatAttributes() {
        return Mob.createMobAttributes().add(Attributes.FLYING_SPEED, 0.5f).add(Attributes.MAX_HEALTH, 6.0D).add(Attributes.ATTACK_DAMAGE, 1).add(Attributes.FLYING_SPEED);
    }

    public boolean isResting() {
        return (this.entityData.get(DATA_ID_FLAGS) & FLAG_RESTING) != 0;
    }

    public void setResting(boolean pIsResting) {
        byte b0 = this.entityData.get(DATA_ID_FLAGS);
        if (pIsResting) {
            this.entityData.set(DATA_ID_FLAGS, (byte) (b0 | FLAG_RESTING));
        } else {
            this.entityData.set(DATA_ID_FLAGS, (byte) (b0 & -2));
        }

    }

    /**
     * Called to update the entity's position/logic.
     */
    public void tick() {
        super.tick();
        if (this.isResting()) {
            this.setDeltaMovement(Vec3.ZERO);
            this.setPosRaw(this.getX(), (double) Mth.floor(this.getY()) + 1.0D - (double) this.getBbHeight(), this.getZ());
        } else {
            this.setDeltaMovement(this.getDeltaMovement().multiply(1.0D, 0.6D, 1.0D));
        }

    }

    protected void customServerAiStep() {
        super.customServerAiStep();
        if (getTarget() == null) {
            BlockPos blockpos = this.blockPosition();
            BlockPos blockpos1 = blockpos.above();
            if (this.isResting()) {
                boolean flag = this.isSilent();
                if (this.level().getBlockState(blockpos1).isRedstoneConductor(this.level(), blockpos)) {
                    if (this.random.nextInt(200) == 0) {
                        this.yHeadRot = (float) this.random.nextInt(360);
                    }

                    if (this.level().getNearestPlayer(BAT_RESTING_TARGETING, this) != null) {
                        this.setResting(false);
                        if (!flag) {
                            this.level().levelEvent((Player) null, 1025, blockpos, 0);
                        }
                    }
                } else {
                    this.setResting(false);
                    if (!flag) {
                        this.level().levelEvent((Player) null, 1025, blockpos, 0);
                    }
                }
            }
        }
    }

    @Override
    public MoveControl getMoveControl() {
        return super.getMoveControl();
    }

    @Override
    protected PathNavigation createNavigation(Level pLevel) {
        FlyingPathNavigation flyingpathnavigation = new FlyingPathNavigation(this, pLevel);
        flyingpathnavigation.setCanOpenDoors(true);
        flyingpathnavigation.setCanFloat(true);
        flyingpathnavigation.setCanPassDoors(true);
        return flyingpathnavigation;
    }

    protected Entity.MovementEmission getMovementEmission() {
        return Entity.MovementEmission.EVENTS;
    }

    protected void checkFallDamage(double pY, boolean pOnGround, BlockState pState, BlockPos pPos) {
    }

    /**
     * Return whether this entity should NOT trigger a pressure plate or a tripwire.
     */
    public boolean isIgnoringBlockTriggers() {
        return true;
    }

    /**
     * Called when the entity is attacked.
     */
    public boolean hurt(DamageSource pSource, float pAmount) {
        if (this.isInvulnerableTo(pSource)) {
            return false;
        } else {
            if (!this.level().isClientSide && this.isResting()) {
                this.setResting(false);
            }

            return super.hurt(pSource, pAmount);
        }
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.entityData.set(DATA_ID_FLAGS, pCompound.getByte("BatFlags"));
    }

    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putByte("BatFlags", this.entityData.get(DATA_ID_FLAGS));
    }


    protected float getStandingEyeHeight(Pose pPose, EntityDimensions pSize) {
        return pSize.height / 2.0F;
    }

    public static <T extends Mob> boolean checkSpookyBatSpawnRules(EntityType<T> tEntityType, ServerLevelAccessor serverLevelAccessor, MobSpawnType spawnType, BlockPos pos, RandomSource randomSource) {
        return serverLevelAccessor.getLevel().structureManager().getStructureWithPieceAt(pos,BuiltinStructures.END_CITY) != StructureStart.INVALID_START;
    }
    public static boolean checkSlimeSpawnRules(EntityType<SlimeBat> $$0, LevelAccessor $$1, MobSpawnType $$2, BlockPos $$3, RandomSource $$4) {
        if ($$1.getDifficulty() != Difficulty.PEACEFUL) {
            if ($$1.getBiome($$3).is(BiomeTags.ALLOWS_SURFACE_SLIME_SPAWNS) && $$3.getY() > 50 && $$3.getY() < 70 && $$4.nextFloat() < 0.5F && $$4.nextFloat() < $$1.getMoonBrightness() && $$1.getMaxLocalRawBrightness($$3) <= $$4.nextInt(8)) {
                return checkMobSpawnRules($$0, $$1, $$2, $$3, $$4);
            }

            if (!($$1 instanceof WorldGenLevel)) {
                return false;
            }

            ChunkPos $$5 = new ChunkPos($$3);
            boolean $$6 = WorldgenRandom.seedSlimeChunk($$5.x, $$5.z, ((WorldGenLevel)$$1).getSeed(), 987234911L).nextInt(10) == 0;
            if ($$4.nextInt(10) == 0 && $$6 && $$3.getY() < 40) {
                return checkMobSpawnRules($$0, $$1, $$2, $$3, $$4);
            }
        }

        return false;
    }
}
