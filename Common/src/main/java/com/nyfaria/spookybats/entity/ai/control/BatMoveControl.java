package com.nyfaria.spookybats.entity.ai.control;

import com.nyfaria.spookybats.entity.api.SpookyBat;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public class BatMoveControl extends MoveControl {
    private final int maxTurn;
    @Nullable
    private BlockPos targetPosition;

    public BatMoveControl(SpookyBat pMob, int pMaxTurn) {
        super(pMob);
        this.maxTurn = pMaxTurn;
    }

    public void tick() {
        if (this.operation == MoveControl.Operation.MOVE_TO && this.mob.getRandom().nextInt(10) < 6) {
            this.operation = MoveControl.Operation.WAIT;
            this.mob.setNoGravity(true);
            double d0 = this.wantedX - this.mob.getX();
            double d1 = this.wantedY - this.mob.getY();
            double d2 = this.wantedZ - this.mob.getZ();
            double d3 = d0 * d0 + d1 * d1 + d2 * d2;
            if (d3 < (double) 2.5000003E-7F) {
                this.mob.setYya(0.0F);
                this.mob.setZza(0.0F);
                return;
            }

            float f = (float) (Mth.atan2(d2, d0) * (double) (180F / (float) Math.PI)) - 90.0F;
            this.mob.setYRot(this.rotlerp(this.mob.getYRot(), f, 90.0F));
            float f1;
            if (this.mob.onGround()) {
                f1 = (float) (this.speedModifier * this.mob.getAttributeValue(Attributes.MOVEMENT_SPEED));
            } else {
                f1 = (float) (this.speedModifier * this.mob.getAttributeValue(Attributes.FLYING_SPEED));
            }

            this.mob.setSpeed(f1);
            double d4 = Math.sqrt(d0 * d0 + d2 * d2);
            if (Math.abs(d1) > (double) 1.0E-5F || Math.abs(d4) > (double) 1.0E-5F) {
                float f2 = (float) (-(Mth.atan2(d1, d4) * (double) (180F / (float) Math.PI)));
                this.mob.setXRot(this.rotlerp(this.mob.getXRot(), f2, (float) this.maxTurn));
                this.mob.setYya(d1 > 0.0D ? f1 : -f1);
            }
        } else {
            randomMovement(this.operation == MoveControl.Operation.MOVE_TO);
        }
    }

    private void randomMovement(boolean hasTarget) {
        BlockPos blockpos = mob.blockPosition();
        BlockPos blockpos1 = blockpos.above();
        if (!hasTarget) {
            if (this.targetPosition != null && (!mob.level().isEmptyBlock(this.targetPosition) || this.targetPosition.getY() <= mob.level().getMinBuildHeight())) {
                this.targetPosition = null;
            }
        } else {
            this.targetPosition = BlockPos.containing(this.wantedX, this.wantedY, this.wantedZ);
        }

        if (this.targetPosition == null || mob.getRandom().nextInt(30) == 0 || this.targetPosition.closerToCenterThan(mob.position(), 2.0D)) {
            this.targetPosition = BlockPos.containing(mob.getX() + (double) mob.getRandom().nextInt(7) - (double) mob.getRandom().nextInt(7), mob.getY() + (double) mob.getRandom().nextInt(6) - 2.0D, mob.getZ() + (double) mob.getRandom().nextInt(7) - (double) mob.getRandom().nextInt(7));
        }

        double d2 = (double) this.targetPosition.getX() + 0.5D - mob.getX();
        double d0 = (double) this.targetPosition.getY() + 0.1D - mob.getY();
        double d1 = (double) this.targetPosition.getZ() + 0.5D - mob.getZ();
        Vec3 vec3 = mob.getDeltaMovement();
        Vec3 vec31 = vec3.add((Math.signum(d2) * 0.5D - vec3.x) * (double) 0.1F, (Math.signum(d0) * (double) 0.7F - vec3.y) * (double) 0.1F, (Math.signum(d1) * 0.5D - vec3.z) * (double) 0.1F);
        mob.setDeltaMovement(vec31);
        float f = (float) (Mth.atan2(vec31.z, vec31.x) * (double) (180F / (float) Math.PI)) - 90.0F;
        float f1 = Mth.wrapDegrees(f - mob.getYRot());
        mob.zza = 0.5F;
        mob.setYRot(mob.getYRot() + f1);
        if (mob.getRandom().nextInt(100) == 0 && mob.level().getBlockState(blockpos1).isRedstoneConductor(mob.level(), blockpos1)) {
            ((SpookyBat) mob).setResting(true);
        }
    }
}