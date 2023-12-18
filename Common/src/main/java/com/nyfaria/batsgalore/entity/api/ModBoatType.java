package com.nyfaria.batsgalore.entity.api;

import com.nyfaria.batsgalore.init.BlockInit;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.block.Block;

import java.util.function.IntFunction;
import java.util.function.Supplier;

public enum ModBoatType implements StringRepresentable {
    SPOOKY_OAK(BlockInit.SPOOKY_OAK.planks(), "spooky_oak"),
    WHITE_PINE(BlockInit.WHITE_PINE.planks(), "white_pine");

    private final String name;
    private final Supplier<Block> planks;
    public static final StringRepresentable.EnumCodec<ModBoatType> CODEC = StringRepresentable.fromEnum(ModBoatType::values);
    private static final IntFunction<ModBoatType> BY_ID = ByIdMap.continuous(Enum::ordinal, values(), ByIdMap.OutOfBoundsStrategy.ZERO);

    ModBoatType(Supplier<Block> pPlanks, String pName) {
        this.name = pName;
        this.planks = pPlanks;
    }

    public String getSerializedName() {
        return this.name;
    }

    public String getName() {
        return this.name;
    }

    public Block getPlanks() {
        return this.planks.get();
    }

    public String toString() {
        return this.name;
    }

    /**
     * Get a boat type by its enum ordinal
     */
    public static ModBoatType byId(int pId) {
        return BY_ID.apply(pId);
    }

    public static ModBoatType byName(String pName) {
        return CODEC.byName(pName, SPOOKY_OAK);
    }
}
