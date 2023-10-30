package com.nyfaria.spookybats.entity;

import com.nyfaria.spookybats.entity.ai.RaiseBlock;
import com.nyfaria.spookybats.entity.api.SpookyBat;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.BossEvent;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.tslat.smartbrainlib.api.SmartBrainOwner;
import net.tslat.smartbrainlib.api.core.BrainActivityGroup;
import net.tslat.smartbrainlib.api.core.SmartBrainProvider;
import net.tslat.smartbrainlib.api.core.behaviour.FirstApplicableBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.InvalidateAttackTarget;
import net.tslat.smartbrainlib.api.core.sensor.ExtendedSensor;
import net.tslat.smartbrainlib.api.core.sensor.custom.NearbyBlocksSensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.NearbyLivingEntitySensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.NearbyPlayersSensor;

import java.util.List;

public class WingedTurmoil extends SpookyBat implements SmartBrainOwner<WingedTurmoil> {
    private final ServerBossEvent bossEvent = (ServerBossEvent)(new ServerBossEvent(this.getDisplayName(), BossEvent.BossBarColor.WHITE, BossEvent.BossBarOverlay.PROGRESS)).setDarkenScreen(true);
    public WingedTurmoil(EntityType<? extends SpookyBat> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        xpReward = 200;
    }

    @Override
    protected float getStandingEyeHeight(Pose pPose, EntityDimensions pSize) {
        return 2.5f;
    }

    public static AttributeSupplier.Builder createWingedTurmoilAttributes() {
        return SpookyBat
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
                new NearbyPlayersSensor<WingedTurmoil>().setRadius(100),
                new NearbyBlocksSensor<WingedTurmoil>().setPredicate((state, a) -> state.is(Blocks.AIR))
        );
    }
    @Override
    public BrainActivityGroup<? extends WingedTurmoil> getFightTasks() {
        return BrainActivityGroup.fightTasks(
                new InvalidateAttackTarget<>(),     // Invalidate the attack target if it's no longer applicable
                new FirstApplicableBehaviour<>(
                        new RaiseBlock<>()
                ));
    }
}
