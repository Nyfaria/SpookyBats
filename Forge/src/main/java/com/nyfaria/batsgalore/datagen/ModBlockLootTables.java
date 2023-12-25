package com.nyfaria.batsgalore.datagen;

import com.nyfaria.batsgalore.init.BlockInit;
import com.nyfaria.batsgalore.registration.RegistryObject;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.LeavesBlock;

import java.util.Set;
import java.util.stream.Stream;

public class ModBlockLootTables extends BlockLootSubProvider {
    protected ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }
    @Override
    protected void generate() {
        add(BlockInit.DECORATED_WHITE_PINE_LEAVES.get(),createLeavesDrops(BlockInit.DECORATED_WHITE_PINE_LEAVES.get(), BlockInit.WHITE_PINE.sapling().get()));
        Stream.of(
                BlockInit.SPOOKY_OAK,
                BlockInit.WHITE_PINE
        ).forEach(collection-> add(collection.leaves().get(),createLeavesDrops(collection.leaves().get(), collection.sapling().get())));
        this.getBlockStream().filter(this::shouldDropSelf).forEach(this::dropSelf);
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return this.getBlockStream().filter(this::shouldGenerateLoot).toList();
    }

    protected Stream<Block> getBlockStream() {
        return BlockInit.BLOCKS.getEntries().stream().map(RegistryObject::get);
    }

    protected boolean shouldDropSelf(Block block) {
        return shouldGenerateLoot(block) && !(block instanceof LeavesBlock);
    }

    protected boolean shouldGenerateLoot(Block block) {
        return block.asItem() != Items.AIR && !(block instanceof DropExperienceBlock);
    }

}
