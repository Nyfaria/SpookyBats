package com.nyfaria.batsgalore.datagen;

import com.nyfaria.batsgalore.init.BlockInit;
import com.nyfaria.batsgalore.init.ItemInit;
import com.nyfaria.batsgalore.init.entity.ChristmasBatEntityInit;
import com.nyfaria.batsgalore.init.entity.SpookyBatEntityInit;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
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
        dropSingle(SpookyBatEntityInit.PUMPKIN_BAT.get(), ItemInit.PUMPKIN_CHOCOLATE_BAR.get());
        dropSingle(SpookyBatEntityInit.CREEPER_BAT.get(), ItemInit.TNT_LOLLIPOP.get());
        multiDrops(SpookyBatEntityInit.WITCH_BAT.get(), ConstantValue.exactly(1),
                new LootEntry(ItemInit.WITCHES_BREW.get(), 1.0f, ConstantValue.exactly(1)),
                new LootEntry(ItemInit.WITCHS_BROOM.get(), 0.1f, ConstantValue.exactly(1))
        );
        dropSingle(SpookyBatEntityInit.SKELETON_BAT.get(), ItemInit.SKULL_CANDY.get());
        dropSingle(SpookyBatEntityInit.WITHER_SKELETON_BAT.get(), ItemInit.WITHER_SKULL_CANDY.get());
        playerHead(SpookyBatEntityInit.PLAYER_BAT.get());
        dropSingle(SpookyBatEntityInit.STEVE_BAT.get(), ItemInit.GENERIC_CANDY.get());
        dropSingle(SpookyBatEntityInit.ALEX_BAT.get(), ItemInit.GENERIC_CANDY.get());
        multiDrops(SpookyBatEntityInit.UNDEAD_BAT.get(), ConstantValue.exactly(1),
                new LootEntry(ItemInit.ZOMBIE_FLESH_LOLLIPOP.get(), 1.0f, ConstantValue.exactly(1)),
                new LootEntry(ItemInit.UNDEAD_CORE.get(), 0.01f, ConstantValue.exactly(1))
        );
        dropSingle(SpookyBatEntityInit.HEROBRINE_BAT.get(), ItemInit.SUSPICIOUS_CANDY.get());
        multiDrops(SpookyBatEntityInit.SCULK_BAT.get(), ConstantValue.exactly(1),
                new LootEntry(ItemInit.SCULK_CANDY.get(), 1.0f, ConstantValue.exactly(1)),
                new LootEntry(ItemInit.SCULK_CORE.get(), 0.01f, ConstantValue.exactly(1))
        );
        dropSingle(SpookyBatEntityInit.WITCHS_BROOM.get(), ItemInit.WITCHS_BROOM.get());
        dropSingle(ChristmasBatEntityInit.CANDY_CANE_BAT.get(), ItemInit.CANDY_CANE.get());
        multiDrops(SpookyBatEntityInit.SHULKER_BAT.get(), ConstantValue.exactly(1),
                new LootEntry(ItemInit.SHULKER_CORE.get(), 0.01f, ConstantValue.exactly(1))
        );
        multiDrops(SpookyBatEntityInit.SLIME_BAT.get(), ConstantValue.exactly(1),
                new LootEntry(ItemInit.BITESIZED_JELLY.get(), 1.0f, ConstantValue.exactly(1)),
                new LootEntry(ItemInit.SLIME_CORE.get(), 0.01f, ConstantValue.exactly(1))
        );
        wingedTurmoilDrops();
        dropSetAmountWithChance(ChristmasBatEntityInit.ELF_BAT.get(), ConstantValue.exactly(1f), ItemInit.ELF_HAT.get(), 1, 0.03f);
        dropSetAmountWithChance(ChristmasBatEntityInit.REINDEER_BAT.get(), ConstantValue.exactly(1f), ItemInit.REINDEER_NOSE.get(), 1, 0.03f);
    }

    private void multiDrops(EntityType<?> type, NumberProvider rolls, LootEntry... entries) {
        TYPES.add(type);
        LootPool.Builder pool = LootPool.lootPool();
        pool.setRolls(rolls);
        for (LootEntry entry : entries) {
            pool.add(LootItem.lootTableItem(entry.item())
                    .when(LootItemRandomChanceCondition.randomChance(entry.chance()))
                    .apply(SetItemCountFunction.setCount(entry.numberProvider())));
        }
        pool.add(LootItem.lootTableItem(ItemInit.BAT_WINGS.get())
                .when(LootItemRandomChanceCondition.randomChance(0.01f))
                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
        );
        LootTable.Builder builder = LootTable.lootTable();
        builder.withPool(pool);
        add(type, builder);
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
        dropSingleWithChance(entityType, item, 0.99f);
    }

    private void dropSingleWithChance(EntityType<?> entityType, Item item, float chance) {
        dropSetAmountWithChance(entityType, ConstantValue.exactly(1f), item, 1, chance);
    }

    private void dropSetAmount(EntityType<?> entityType, Item item, float amount) {
        dropSetAmountWithChance(entityType, ConstantValue.exactly(1f), item, amount, 0.99f);
    }

    private void dropSetAmountWithChance(EntityType<?> entityType, NumberProvider rolls, Item item, float amount, float chance) {
        TYPES.add(entityType);
        LootTable.Builder builder = LootTable.lootTable();
        LootPool.Builder pool = LootPool.lootPool()
                .setRolls(rolls);
        LootPoolSingletonContainer.Builder<?> lootItem = LootItem.lootTableItem(item);
        if (chance != 0.99f) {
            lootItem = lootItem.when(LootItemRandomChanceCondition.randomChance(chance));
        }
        pool.add(lootItem
                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(amount))));
        pool.add(LootItem.lootTableItem(ItemInit.BAT_WINGS.get())
                .when(LootItemRandomChanceCondition.randomChance(0.01f))
                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))));
        builder.withPool(pool);
        add(entityType, builder);
    }
    private void wingedTurmoilDrops() {
        TYPES.add(SpookyBatEntityInit.WINGED_TURMOIL.get());
        LootTable.Builder builder = LootTable.lootTable();
        LootPool.Builder pool = LootPool.lootPool().setRolls(ConstantValue.exactly(1));
        LootPoolSingletonContainer.Builder<?> lootItem = LootItem.lootTableItem(ItemInit.STAFF_OF_TURMOIL.get());
        pool.add(lootItem.apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))));
        pool.add(LootItem.lootTableItem(ItemInit.BAT_WINGS.get())
                .when(LootItemRandomChanceCondition.randomChance(0.01f))
                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))));
        builder.withPool(pool);
        LootPool.Builder pool2 = LootPool.lootPool().setRolls(ConstantValue.exactly(1));
        LootPoolSingletonContainer.Builder<?> lootItem2 = LootItem.lootTableItem(BlockInit.STATUE_OF_TURMOIL.get());
        pool2.add(lootItem2.apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))));
        builder.withPool(pool2);
        add(SpookyBatEntityInit.WINGED_TURMOIL.get(), builder);
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

    record LootEntry(Item item, float chance, NumberProvider numberProvider) {
    }

    @Override
    protected boolean canHaveLootTable(EntityType<?> pEntityType) {
        return true;
    }
}
