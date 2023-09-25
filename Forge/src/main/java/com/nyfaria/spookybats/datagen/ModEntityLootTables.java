package com.nyfaria.spookybats.datagen;

import com.nyfaria.spookybats.init.EntityInit;
import com.nyfaria.spookybats.init.ItemInit;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.FillPlayerHead;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ModEntityLootTables extends EntityLootSubProvider {

    private List<EntityType<?>> TYPES = new ArrayList<>();

    protected ModEntityLootTables() {
        super(FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    public void generate() {
        dropSingle(EntityInit.PUMPKIN_BAT.get(), ItemInit.PUMPKIN_CHOCOLATE_BAR.get());
        dropSingle(EntityInit.CREEPER_BAT.get(), ItemInit.TNT_LOLLIPOP.get());
        dropSingle(EntityInit.WITCH_BAT.get(), ItemInit.WITCHES_BREW.get());
        dropSingle(EntityInit.SKELETON_BAT.get(), ItemInit.SKULL_CANDY.get());
        dropSingle(EntityInit.WITHER_SKELETON_BAT.get(), ItemInit.WITHER_SKULL_CANDY.get());
        playerHead(EntityInit.PLAYER_BAT.get());
        dropSingle(EntityInit.STEVE_BAT.get(), ItemInit.GENERIC_CANDY.get());
        dropSingle(EntityInit.ALEX_BAT.get(), ItemInit.GENERIC_CANDY.get());
        dropSingle(EntityInit.UNDEAD_BAT.get(), ItemInit.ZOMBIE_FLESH_LOLLIPOP.get());
        dropSingle(EntityInit.HEROBRINE_BAT.get(), ItemInit.SUSPICIOUS_CANDY.get());
    }

    private void multiDrops(EntityType<?> type, LootEntry... entries) {
        LootPool.Builder pool = LootPool.lootPool();
        pool.setRolls(ConstantValue.exactly(1));
        for (LootEntry entry : entries) {
            pool.add(LootItem.lootTableItem(entry.item()).apply(SetItemCountFunction.setCount(entry.numberProvider())));
        }
        this.add(type, LootTable.lootTable().withPool(pool));
    }

    private void dropRange(EntityType<?> entityType, Item item, float min, float max) {
        LootTable.Builder builder = LootTable.lootTable();
        builder.withPool(LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1))
                .add(LootItem.lootTableItem(Items.BONE)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(min, max)))));
        add(entityType, builder);
    }

    private void dropSingle(EntityType<?> entityType, Item item) {
        TYPES.add(entityType);
        dropSetAmount(entityType, item, 1);
    }

    private void dropSetAmount(EntityType<?> entityType, Item item, float amount) {
        LootTable.Builder builder = LootTable.lootTable();
        builder.withPool(LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1))
                .add(LootItem.lootTableItem(item)
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(amount))))
                .add(LootItem.lootTableItem(ItemInit.BAT_WINGS.get())
                        .when(LootItemRandomChanceCondition.randomChance(0.01f))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                )
        );
        add(entityType, builder);
    }

    private void playerHead(EntityType<?> entityType) {
        TYPES.add(entityType);
        LootTable.Builder builder = LootTable.lootTable();
        builder.withPool(LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1))
                .add(LootItem.lootTableItem(Items.PLAYER_HEAD)
                        .apply(FillPlayerHead.fillPlayerHead(LootContext.EntityTarget.KILLER_PLAYER))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                .add(LootItem.lootTableItem(ItemInit.BAT_WINGS.get())
                        .when(LootItemRandomChanceCondition.randomChance(0.01f))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))

                )
        );
        add(entityType, builder);
    }

    @Override
    protected Stream<EntityType<?>> getKnownEntityTypes() {
        return TYPES.stream();
    }

    record LootEntry(Item item, NumberProvider numberProvider) {
    }
}
