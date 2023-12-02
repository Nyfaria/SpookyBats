package com.nyfaria.spookybats.datagen;

import com.nyfaria.spookybats.Constants;
import com.nyfaria.spookybats.init.BlockInit;
import com.nyfaria.spookybats.init.TagInit;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ModTagProvider {

    public static class ItemTags extends TagsProvider<Item>{

        public ItemTags(PackOutput p_256596_, CompletableFuture<HolderLookup.Provider> p_256513_, @Nullable ExistingFileHelper existingFileHelper) {
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

    public static class BlockTags extends TagsProvider<Block>{

        public BlockTags(PackOutput pGenerator, CompletableFuture<HolderLookup.Provider> p_256513_, @Nullable ExistingFileHelper existingFileHelper) {
            super(pGenerator, Registries.BLOCK, p_256513_, Constants.MODID, existingFileHelper);
        }

        @Override
        protected void addTags(HolderLookup.Provider pProvider) {
            populateTag(net.minecraft.tags.BlockTags.LOGS, BlockInit.SPOOKY_OAK.log());
            populateTag(net.minecraft.tags.BlockTags.LEAVES, BlockInit.SPOOKY_OAK.leaves());
            populateTag(net.minecraft.tags.BlockTags.SAPLINGS, BlockInit.SPOOKY_OAK.sapling());
            populateTag(net.minecraft.tags.BlockTags.PLANKS, BlockInit.SPOOKY_OAK.planks());
            populateTag(net.minecraft.tags.BlockTags.WOODEN_BUTTONS, BlockInit.SPOOKY_OAK.button());
            populateTag(net.minecraft.tags.BlockTags.WOODEN_DOORS, BlockInit.SPOOKY_OAK.door());
            populateTag(net.minecraft.tags.BlockTags.WOODEN_FENCES, BlockInit.SPOOKY_OAK.fence());
            populateTag(net.minecraft.tags.BlockTags.WOODEN_PRESSURE_PLATES, BlockInit.SPOOKY_OAK.pressurePlate());
            populateTag(net.minecraft.tags.BlockTags.WOODEN_SLABS, BlockInit.SPOOKY_OAK.slab());
            populateTag(net.minecraft.tags.BlockTags.WOODEN_STAIRS, BlockInit.SPOOKY_OAK.stairs());
            populateTag(net.minecraft.tags.BlockTags.WOODEN_TRAPDOORS, BlockInit.SPOOKY_OAK.trapdoor());
            populateTag(TagInit.SOT_EXCLUSION,
                    ()->Blocks.BEDROCK,
                    ()->Blocks.OBSIDIAN,
                    ()->Blocks.END_PORTAL_FRAME,
                    ()->Blocks.END_PORTAL,
                    ()->Blocks.END_GATEWAY,
                    ()->Blocks.NETHER_PORTAL,
                    ()->Blocks.DRAGON_EGG
            );
        }
        public  <T extends Block>void populateTag(TagKey<Block> tag, Supplier<?>... items){
            for (Supplier<?> item : items) {
                tag(tag).add(ForgeRegistries.BLOCKS.getResourceKey((Block)item.get()).get());
            }
        }
    }
}
