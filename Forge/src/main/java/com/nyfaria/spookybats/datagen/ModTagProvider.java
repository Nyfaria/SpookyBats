package com.nyfaria.spookybats.datagen;

import com.nyfaria.spookybats.Constants;
import com.nyfaria.spookybats.init.BlockInit;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class ModTagProvider {

    public static class Items extends TagsProvider<Item>{

        public Items(PackOutput p_256596_, CompletableFuture<HolderLookup.Provider> p_256513_, @Nullable ExistingFileHelper existingFileHelper) {
            super(p_256596_, Registries.ITEM, p_256513_, Constants.MODID, existingFileHelper);
        }

        @Override
        protected void addTags(HolderLookup.Provider pProvider) {

        }

        public void populateTag(TagKey<Item> tag, Supplier<Item>... items){
            for (Supplier<Item> item : items) {
                tag(tag).add(ForgeRegistries.ITEMS.getResourceKey(item.get()).get());
            }
        }
    }

    public static class Blocks extends TagsProvider<Block>{

        public Blocks(PackOutput pGenerator, CompletableFuture<HolderLookup.Provider> p_256513_, @Nullable ExistingFileHelper existingFileHelper) {
            super(pGenerator, Registries.BLOCK, p_256513_, Constants.MODID, existingFileHelper);
        }

        @Override
        protected void addTags(HolderLookup.Provider pProvider) {
            populateTag(BlockTags.LOGS, BlockInit.SPOOKY_OAK.log());
            populateTag(BlockTags.LEAVES, BlockInit.SPOOKY_OAK.leaves());
            populateTag(BlockTags.SAPLINGS, BlockInit.SPOOKY_OAK.sapling());
            populateTag(BlockTags.PLANKS, BlockInit.SPOOKY_OAK.planks());
            populateTag(BlockTags.WOODEN_BUTTONS, BlockInit.SPOOKY_OAK.button());
            populateTag(BlockTags.WOODEN_DOORS, BlockInit.SPOOKY_OAK.door());
            populateTag(BlockTags.WOODEN_FENCES, BlockInit.SPOOKY_OAK.fence());
            populateTag(BlockTags.WOODEN_PRESSURE_PLATES, BlockInit.SPOOKY_OAK.pressurePlate());
            populateTag(BlockTags.WOODEN_SLABS, BlockInit.SPOOKY_OAK.slab());
            populateTag(BlockTags.WOODEN_STAIRS, BlockInit.SPOOKY_OAK.stairs());
            populateTag(BlockTags.WOODEN_TRAPDOORS, BlockInit.SPOOKY_OAK.trapdoor());
        }
        public  <T extends Block>void populateTag(TagKey<Block> tag, Supplier<?>... items){
            for (Supplier<?> item : items) {
                tag(tag).add(ForgeRegistries.BLOCKS.getResourceKey((Block)item.get()).get());
            }
        }
    }
}
