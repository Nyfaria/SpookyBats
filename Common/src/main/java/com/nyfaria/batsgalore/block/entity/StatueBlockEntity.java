package com.nyfaria.batsgalore.block.entity;

import com.nyfaria.batsgalore.init.BlockInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class StatueBlockEntity extends BlockEntity {
    public StatueBlockEntity(BlockPos pos, BlockState state) {
        super(BlockInit.STATUE_BLOCK_ENTITY.get(), pos, state);
    }
}
