package com.nyfaria.batsgalore.datagen;

import com.nyfaria.batsgalore.Constants;
import com.nyfaria.batsgalore.block.WoodCollection;
import com.nyfaria.batsgalore.init.BlockInit;
import com.nyfaria.batsgalore.init.TagInit;
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
import java.util.stream.Stream;

public class ModTagProvider {

    public static class ItemTag extends TagsProvider<Item> {

        public ItemTag(PackOutput p_256596_, CompletableFuture<HolderLookup.Provider> p_256513_, @Nullable ExistingFileHelper existingFileHelper) {
            super(p_256596_, Registries.ITEM, p_256513_, Constants.MODID, existingFileHelper);
        }

        @Override
        protected void addTags(HolderLookup.Provider pProvider) {
            Stream.of(
                    BlockInit.SPOOKY_OAK,
                    BlockInit.WHITE_PINE
            ).forEach(this::woodCollection);


        }

        protected void woodCollection(WoodCollection woodCollection) {
            populateTag(woodCollection.logsTag(),
                    woodCollection.log(),
                    woodCollection.strippedLog(),
                    woodCollection.wood(),
                    woodCollection.strippedWood()
            );
            populateTag(ItemTags.LEAVES, woodCollection.leaves());
            populateTag(ItemTags.SAPLINGS, woodCollection.sapling());
            populateTag(ItemTags.PLANKS, woodCollection.planks());
            populateTag(ItemTags.WOODEN_BUTTONS, woodCollection.button());
            populateTag(ItemTags.WOODEN_DOORS, woodCollection.door());
            populateTag(ItemTags.WOODEN_FENCES, woodCollection.fence());
            populateTag(ItemTags.WOODEN_PRESSURE_PLATES, woodCollection.pressurePlate());
            populateTag(ItemTags.WOODEN_SLABS, woodCollection.slab());
            populateTag(ItemTags.WOODEN_STAIRS, woodCollection.stairs());
            populateTag(ItemTags.WOODEN_TRAPDOORS, woodCollection.trapdoor());
            tag(ItemTags.LOGS_THAT_BURN).addTag(woodCollection.logsTag());

        }

        public void populateTag(TagKey<Item> tag, Supplier<? extends ItemLike>... items) {
            for (Supplier<? extends ItemLike> item : items) {
                tag(tag).add(ForgeRegistries.ITEMS.getResourceKey(item.get().asItem()).get());
            }
        }
    }

    public static class BlockTag extends TagsProvider<Block> {

        public BlockTag(PackOutput pGenerator, CompletableFuture<HolderLookup.Provider> p_256513_, @Nullable ExistingFileHelper existingFileHelper) {
            super(pGenerator, Registries.BLOCK, p_256513_, Constants.MODID, existingFileHelper);
        }

        @Override
        protected void addTags(HolderLookup.Provider pProvider) {
            Stream.of(
                    BlockInit.SPOOKY_OAK,
                    BlockInit.WHITE_PINE
            ).forEach(this::woodCollection);

            populateTag(TagInit.SOT_EXCLUSION,
                    () -> Blocks.BEDROCK,
                    () -> Blocks.OBSIDIAN,
                    () -> Blocks.END_PORTAL_FRAME,
                    () -> Blocks.END_PORTAL,
                    () -> Blocks.END_GATEWAY,
                    () -> Blocks.NETHER_PORTAL,
                    () -> Blocks.DRAGON_EGG
            );
        }

        protected void woodCollection(WoodCollection woodCollection) {
            populateTag(woodCollection.logsTagBlock(),
                    woodCollection.log(),
                    woodCollection.strippedLog(),
                    woodCollection.wood(),
                    woodCollection.strippedWood()
            );
            populateTag(BlockTags.LEAVES, woodCollection.leaves());
            populateTag(BlockTags.SAPLINGS, woodCollection.sapling());
            populateTag(BlockTags.PLANKS, woodCollection.planks());
            populateTag(BlockTags.WOODEN_BUTTONS, woodCollection.button());
            populateTag(BlockTags.WOODEN_DOORS, woodCollection.door());
            populateTag(BlockTags.WOODEN_FENCES, woodCollection.fence());
            populateTag(BlockTags.WOODEN_PRESSURE_PLATES, woodCollection.pressurePlate());
            populateTag(BlockTags.WOODEN_SLABS, woodCollection.slab());
            populateTag(BlockTags.WOODEN_STAIRS, woodCollection.stairs());
            populateTag(BlockTags.WOODEN_TRAPDOORS, woodCollection.trapdoor());
            tag(BlockTags.LOGS_THAT_BURN).addTag(woodCollection.logsTagBlock());
        }

        public <T extends Block> void populateTag(TagKey<Block> tag, Supplier<?>... items) {
            for (Supplier<?> item : items) {
                tag(tag).add(ForgeRegistries.BLOCKS.getResourceKey((Block) item.get()).get());
            }
        }
    }
}
