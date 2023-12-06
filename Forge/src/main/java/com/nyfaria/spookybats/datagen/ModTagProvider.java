package com.nyfaria.spookybats.datagen;

import com.nyfaria.spookybats.Constants;
import com.nyfaria.spookybats.block.WoodCollection;
import com.nyfaria.spookybats.init.BlockInit;
import com.nyfaria.spookybats.init.TagInit;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class ModTagProvider {

    public static class ItemTag extends TagsProvider<Item>{

        public ItemTag(PackOutput p_256596_, CompletableFuture<HolderLookup.Provider> p_256513_, @Nullable ExistingFileHelper existingFileHelper) {
            super(p_256596_, Registries.ITEM, p_256513_, Constants.MODID, existingFileHelper);
        }

        @Override
        protected void addTags(HolderLookup.Provider pProvider) {
            populateTag(TagInit.SPOOKY_OAK_LOGS_ITEM,
                    BlockInit.SPOOKY_OAK.log(),
                    BlockInit.SPOOKY_OAK.strippedLog(),
                    BlockInit.SPOOKY_OAK.wood(),
                    BlockInit.SPOOKY_OAK.strippedWood()
            );
            tag(ItemTags.LOGS_THAT_BURN).addTag(TagInit.SPOOKY_OAK_LOGS_ITEM);
            populateTag(ItemTags.LEAVES, BlockInit.SPOOKY_OAK.leaves());
            populateTag(ItemTags.SAPLINGS, BlockInit.SPOOKY_OAK.sapling());
            populateTag(ItemTags.PLANKS, BlockInit.SPOOKY_OAK.planks());
            populateTag(ItemTags.WOODEN_BUTTONS, BlockInit.SPOOKY_OAK.button());
            populateTag(ItemTags.WOODEN_DOORS, BlockInit.SPOOKY_OAK.door());
            populateTag(ItemTags.WOODEN_FENCES, BlockInit.SPOOKY_OAK.fence());
            populateTag(ItemTags.WOODEN_PRESSURE_PLATES, BlockInit.SPOOKY_OAK.pressurePlate());
            populateTag(ItemTags.WOODEN_SLABS, BlockInit.SPOOKY_OAK.slab());
            populateTag(ItemTags.WOODEN_STAIRS, BlockInit.SPOOKY_OAK.stairs());
            populateTag(ItemTags.WOODEN_TRAPDOORS, BlockInit.SPOOKY_OAK.trapdoor());

        }

        public void populateTag(TagKey<Item> tag, Supplier<? extends ItemLike>... items){
            for(Supplier<? extends ItemLike> item : items) {
                tag(tag).add(ForgeRegistries.ITEMS.getResourceKey(item.get().asItem()).get());
            }
        }
    }

    public static class BlockTag extends TagsProvider<Block>{

        public BlockTag(PackOutput pGenerator, CompletableFuture<HolderLookup.Provider> p_256513_, @Nullable ExistingFileHelper existingFileHelper) {
            super(pGenerator, Registries.BLOCK, p_256513_, Constants.MODID, existingFileHelper);
        }

        @Override
        protected void addTags(HolderLookup.Provider pProvider) {
            populateTag(TagInit.SPOOKY_OAK_LOGS,
                    BlockInit.SPOOKY_OAK.log(),
                    BlockInit.SPOOKY_OAK.strippedLog(),
                    BlockInit.SPOOKY_OAK.wood(),
                    BlockInit.SPOOKY_OAK.strippedWood()
            );
            tag(BlockTags.LOGS_THAT_BURN).addTag(TagInit.SPOOKY_OAK_LOGS);
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
        protected void woodCollection(WoodCollection collection){

        }
        public  <T extends Block>void populateTag(TagKey<Block> tag, Supplier<?>... items){
            for (Supplier<?> item : items) {
                tag(tag).add(ForgeRegistries.BLOCKS.getResourceKey((Block)item.get()).get());
            }
        }
    }
}
