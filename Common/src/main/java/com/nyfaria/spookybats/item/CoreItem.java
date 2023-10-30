package com.nyfaria.spookybats.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public class CoreItem extends Item {
    private Block pedestalBlock;

    public CoreItem(Properties properties, Block block) {
        super(properties);
        this.pedestalBlock = block;
    }
    public Block getPedestalBlock() {
        return this.pedestalBlock;
    }
}
