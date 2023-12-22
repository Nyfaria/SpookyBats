package com.nyfaria.batsgalore.entity;

import com.nyfaria.batsgalore.entity.api.Master;
import com.nyfaria.batsgalore.entity.api.ModBat;
import com.nyfaria.batsgalore.init.entity.SpookyBatEntityInit;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.BossEvent;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.tslat.smartbrainlib.api.SmartBrainOwner;
import net.tslat.smartbrainlib.api.core.BrainActivityGroup;
import net.tslat.smartbrainlib.api.core.SmartBrainProvider;
import net.tslat.smartbrainlib.api.core.behaviour.FirstApplicableBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.custom.attack.AnimatableRangedAttack;
import net.tslat.smartbrainlib.api.core.behaviour.custom.look.LookAtTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.move.StrafeTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.InvalidateAttackTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.SetPlayerLookTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.SetRandomLookTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.TargetOrRetaliate;
import net.tslat.smartbrainlib.api.core.sensor.ExtendedSensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.NearbyLivingEntitySensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.NearbyPlayersSensor;

import java.util.List;

public class WingedTurmoil extends ModBat implements SmartBrainOwner<WingedTurmoil>, RangedAttackMob, Master {
    private final ServerBossEvent bossEvent = (ServerBossEvent) (new ServerBossEvent(this.getDisplayName(), BossEvent.BossBarColor.WHITE, BossEvent.BossBarOverlay.PROGRESS)).setDarkenScreen(true);
    private int minionCount = 0;
    public WingedTurmoil(EntityType<? extends ModBat> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        xpReward = 200;
    }

    @Override
    protected float getStandingEyeHeight(Pose pPose, EntityDimensions pSize) {
        return 2.5f;
    }

    public static AttributeSupplier.Builder createWingedTurmoilAttributes() {
        return ModBat
                .createBatAttributes()
                .add(Attributes.ATTACK_DAMAGE, 5)
                .add(Attributes.MAX_HEALTH, 300);
    }


    @Override
    protected Brain.Provider<?> brainProvider() {
        return new SmartBrainProvider<>(this);
    }

    @Override
    protected void customServerAiStep() {
        super.customServerAiStep();
        this.bossEvent.setProgress(this.getHealth() / this.getMaxHealth());
        tickBrain(this);
    }

    public void startSeenByPlayer(ServerPlayer pPlayer) {
        super.startSeenByPlayer(pPlayer);
        this.bossEvent.addPlayer(pPlayer);
    }

    public void stopSeenByPlayer(ServerPlayer pPlayer) {
        super.stopSeenByPlayer(pPlayer);
        this.bossEvent.removePlayer(pPlayer);
    }

    @Override
    public List<? extends ExtendedSensor<? extends WingedTurmoil>> getSensors() {
        return ObjectArrayList.of(
                new NearbyPlayersSensor<WingedTurmoil>().setRadius(100),                            // Keep track of nearby players
                new NearbyLivingEntitySensor<WingedTurmoil>()
                        .setPredicate((target, entity) ->
                                target instanceof Player)
        );
    }

    @Override
    public BrainActivityGroup<? extends WingedTurmoil> getCoreTasks() {
        return BrainActivityGroup.coreTasks(
                new LookAtTarget<>().runFor(entity -> entity.getRandom().nextIntBetweenInclusive(40, 300)),                                                        // Look at the look target
                new StrafeTarget<>().stopStrafingWhen(entity -> entity.distanceTo(this) < 64));
    }

    @Override
    public BrainActivityGroup<? extends WingedTurmoil> getIdleTasks() {
        return BrainActivityGroup.idleTasks(
                new FirstApplicableBehaviour<WingedTurmoil>(                // Run only one of the below behaviours, trying each one in order. Include explicit generic typing because javac is silly
                        new TargetOrRetaliate<>(),                        // Set the attack target
                        new SetPlayerLookTarget<>(),                    // Set the look target to a nearby player if available
                        new SetRandomLookTarget<>())); // Don't walk anywhere
    }

    @Override
    public BrainActivityGroup<? extends WingedTurmoil> getFightTasks() {
        return BrainActivityGroup.fightTasks(
                new InvalidateAttackTarget<>(),     // Invalidate the attack target if it's no longer applicable
                new FirstApplicableBehaviour<>(
                        new AnimatableRangedAttack<>(0).attackRadius(20).attackInterval(e -> 150)
                ));
    }

    @Override
    public void performRangedAttack(LivingEntity livingEntity, float v) {
        if (getMinionCount() == 0) {
            int i = random.nextInt(5);
            for (int i1 = 0; i1 < i; i1++) {
                BlockPos pos = this.blockPosition().offset(random.nextInt(10) - 5, random.nextInt(5) - 2, random.nextInt(10) - 5);
                EvilBat bat = SpookyBatEntityInit.EVIL_BAT.get().spawn((ServerLevel) level(), pos, MobSpawnType.EVENT);
                bat.setTarget(livingEntity);
                bat.setMaster(this);
            }
            setMinionCount(i);
        } else {
            int radius = 4;
            for (int x = -radius; x <= radius; x++) {
                for (int y = -radius; y <= radius; y++) {
                    for (int z = -radius; z <= radius; z++) {
                        BlockPos pos = this.blockPosition().offset(x, y, z);
                        if (level().getBlockState(pos.above()).isAir() && level().getBlockState(pos).isSolidRender(level(), pos)) {
                            if(getRandom().nextInt(5) == 0) {
                                BlockProjectile projectile = new BlockProjectile(level(), this, livingEntity);
                                projectile.setPos(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
                                projectile.setBlockState(level().getBlockState(pos));
                                level().setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
                                level().addFreshEntity(projectile);
                            }
                        }
                    }
                }
            }
        }

    }

    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        pCompound.putInt("minionCount", minionCount);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        minionCount = pCompound.getInt("minionCount");
    }

    @Override
    public boolean canAttack(LivingEntity $$0, TargetingConditions $$1) {
        return super.canAttack($$0, $$1) && ($$0 instanceof Player);
    }

    @Override
    public void minionDeath() {
        minionCount--;
    }

    @Override
    public void setMinionCount(int count) {
        minionCount = count;
    }

    @Override
    public int getMinionCount() {
        return minionCount;
    }
}
