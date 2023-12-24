package com.nyfaria.batsgalore.init;

import com.nyfaria.batsgalore.Constants;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class TagInit {
    public static void loadClass() {
    }
    public static TagKey<Block> SOT_EXCLUSION  = TagKey.create(Registries.BLOCK, new ResourceLocation(Constants.MODID, "staff_of_turmoil_exclusion"));
    public static TagKey<Block> SPOOKY_OAK_LOGS = TagKey.create(Registries.BLOCK, new ResourceLocation(Constants.MODID, "spooky_oak_logs"));
    public static TagKey<Block> WHITE_PINE_LOGS = TagKey.create(Registries.BLOCK, new ResourceLocation(Constants.MODID, "white_pine_logs"));
    public static TagKey<Item> SPOOKY_OAK_LOGS_ITEM = TagKey.create(Registries.ITEM, new ResourceLocation(Constants.MODID, "spooky_oak_logs"));
    public static TagKey<Item> WHITE_PINE_LOGS_ITEM = TagKey.create(Registries.ITEM, new ResourceLocation(Constants.MODID, "white_pine_oak_logs"));
}
