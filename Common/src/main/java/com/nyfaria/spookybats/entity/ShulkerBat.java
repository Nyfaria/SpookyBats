package com.nyfaria.spookybats.entity;

import com.nyfaria.spookybats.entity.api.SpookyBat;
import net.minecraft.core.NonNullList;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.npc.InventoryCarrier;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ShulkerBoxBlockEntity;

import java.util.EnumSet;
import java.util.List;

public class ShulkerBat extends CoreDroppingBat implements InventoryCarrier {
    private final SimpleContainer inventory = new SimpleContainer(27);
    private static final EntityDataAccessor<Integer> OPEN_TICKS = SynchedEntityData.defineId(ShulkerBat.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> HOLD_OPEN_TICKS = SynchedEntityData.defineId(ShulkerBat.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> OPENING = SynchedEntityData.defineId(ShulkerBat.class, EntityDataSerializers.BOOLEAN);

    public ShulkerBat(EntityType<? extends SpookyBat> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setCanPickUpLoot(canPickUpLoot());
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new ShulkerBatSearchForItemsGoal(100));
    }

    public int getMaxOpenTicks() {
        return 20*2;
    }


    public static AttributeSupplier.Builder createShulkerBatAttributes() {
        return SpookyBat
                .createBatAttributes()
                .add(Attributes.MAX_HEALTH, 14);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(OPEN_TICKS, 0);
        this.entityData.define(OPENING, false);
        this.entityData.define(HOLD_OPEN_TICKS, 0);
    }

    @Override
    public void tick() {
        tickLid();
        super.tick();
    }

    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        readInventoryFromTag(pCompound);

    }

    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        writeInventoryToTag(pCompound);
    }

    @Override
    public SimpleContainer getInventory() {
        return inventory;
    }

    @Override
    protected void pickUpItem(ItemEntity $$0) {
        InventoryCarrier.pickUpItem(this, this, $$0);
    }

    @Override
    public boolean canPickUpLoot() {
        return true;
    }

    @Override
    protected void spawnOnDeath() {
        super.spawnOnDeath();
        if(deathTime==19) {
            ItemStack stack = new ItemStack(Items.SHULKER_BOX);
            CompoundTag tag = new CompoundTag();
            NonNullList<ItemStack> items = NonNullList.withSize(27, ItemStack.EMPTY);
            for (int i = 0; i < items.size(); i++) {
                items.set(i, inventory.getItem(i));
            }
            ContainerHelper.saveAllItems(tag, items);
            BlockItem.setBlockEntityData(stack, BlockEntityType.SHULKER_BOX, tag);
            spawnAtLocation(stack);
        }
    }

    @Override
    public ParticleOptions getAmbientParticle() {
        return ParticleTypes.END_ROD;
    }

    private void tickLid(){
        if(level().isClientSide)return;
        if(tickCount % 20 == 0 && getRandom().nextInt(20) == 0 && this.entityData.get(OPEN_TICKS) == 0){
            this.entityData.set(OPENING, true);
        }
        if(this.entityData.get(OPENING)){
            int openTicks = this.entityData.get(OPEN_TICKS);
            if(openTicks < getMaxOpenTicks()){
                this.entityData.set(OPEN_TICKS, openTicks + 1);
            } else {
                this.entityData.set(OPENING, false);
                this.entityData.set(HOLD_OPEN_TICKS, 20);
            }
        } else if(this.entityData.get(OPEN_TICKS) > 0 && this.entityData.get(HOLD_OPEN_TICKS) == 0){
            int openTicks = this.entityData.get(OPEN_TICKS);
            this.entityData.set(OPEN_TICKS, openTicks - 1);
        } else if(this.entityData.get(HOLD_OPEN_TICKS) > 0){
            int openTicks = this.entityData.get(HOLD_OPEN_TICKS);
            this.entityData.set(HOLD_OPEN_TICKS, openTicks - 1);
        }
    }
    public int getOpenTicks(){
        return this.entityData.get(OPEN_TICKS);
    }
    private class ShulkerBatSearchForItemsGoal extends Goal {
        final double range;
        public ShulkerBatSearchForItemsGoal(double range) {
            this.setFlags(EnumSet.of(Flag.MOVE));
            this.range = range;
        }

        public boolean canUse() {
            if (ShulkerBat.this.getTarget() == null && ShulkerBat.this.getLastHurtByMob() == null) {
                if (ShulkerBat.this.getRandom().nextInt(reducedTickDelay(10)) != 0) {
                    return false;
                } else {
                    List<ItemEntity> $$0 = ShulkerBat.this.level().getEntitiesOfClass(ItemEntity.class, ShulkerBat.this.getBoundingBox().inflate(range), e -> true);
                    return !$$0.isEmpty() && ShulkerBat.this.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty();
                }
            } else {
                return false;
            }
        }

        public void tick() {
            List<ItemEntity> $$0 = ShulkerBat.this.level().getEntitiesOfClass(ItemEntity.class, ShulkerBat.this.getBoundingBox().inflate(range, range, range), e -> true);
            ItemStack $$1 = ShulkerBat.this.getItemBySlot(EquipmentSlot.MAINHAND);
            if ($$1.isEmpty() && !$$0.isEmpty()) {
                ShulkerBat.this.getNavigation().moveTo($$0.get(0), 1.2000000476837158);
            }

        }

        public void start() {
            List<ItemEntity> $$0 = ShulkerBat.this.level().getEntitiesOfClass(ItemEntity.class, ShulkerBat.this.getBoundingBox().inflate(range, range, range), e -> true);
            if (!$$0.isEmpty()) {
                ShulkerBat.this.getNavigation().moveTo($$0.get(0), 1.2000000476837158);
            }

        }
    }
}