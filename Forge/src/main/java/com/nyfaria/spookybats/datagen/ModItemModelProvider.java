package com.nyfaria.spookybats.datagen;

import com.nyfaria.spookybats.Constants;
import com.nyfaria.spookybats.block.WoodCollection;
import com.nyfaria.spookybats.init.BlockInit;
import com.nyfaria.spookybats.init.ItemInit;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;
import java.util.stream.Stream;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput generator, ExistingFileHelper existingFileHelper) {
        super(generator, Constants.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        // Stream.of()
        //         .map(Supplier::get)
        //         .forEach(this::simpleHandHeldModel);

        Stream.of(
                        ItemInit.PUMPKIN_CHOCOLATE_BAR,
                        ItemInit.GENERIC_CANDY,
                        ItemInit.SUSPICIOUS_CANDY,
                        ItemInit.SKULL_CANDY,
                        ItemInit.WITHER_SKULL_CANDY,
                        ItemInit.TNT_LOLLIPOP,
                        ItemInit.ZOMBIE_FLESH_LOLLIPOP,
                        ItemInit.WITCHES_BREW,
                        ItemInit.BAT_WINGS,
                        ItemInit.SCULK_CANDY,
                        ItemInit.WITCHS_BROOM,
                        ItemInit.BITESIZED_JELLY
                )
                .map(Supplier::get)
                .forEach(this::simpleGeneratedModel);
        ItemInit.ITEMS.getEntries().stream().filter(item -> item.get() instanceof SpawnEggItem).map(Supplier::get).forEach(this::spawnEgg);
        Stream.of(
                        BlockInit.SPOOKY_PEDESTAL
                )
                .map(Supplier::get)
                .forEach(this::simpleBlockItemModel);
        woodCollection(BlockInit.SPOOKY_OAK);
    }

    protected ItemModelBuilder simpleBlockItemModel(Block block) {
        String name = getName(block);
        return withExistingParent(name, modLoc("block/" + name));
    }
    protected ItemModelBuilder simpleTrapdoorBlockItemModel(Block block) {
        String name = getName(block);
        return withExistingParent(name, modLoc("block/" + name + "_bottom"));
    }

    protected ItemModelBuilder simpleGeneratedModel(Item item) {
        return simpleModel(item, mcLoc("item/generated"));
    }

    protected ItemModelBuilder simpleHandHeldModel(Item item) {
        return simpleModel(item, mcLoc("item/handheld"));
    }

    protected ItemModelBuilder simpleModel(Item item, ResourceLocation parent) {
        String name = getName(item);
        return singleTexture(name, parent, "layer0", modLoc("item/" + name));
    }

    protected ItemModelBuilder spawnEgg(Item block) {
        String name = getName(block);
        return withExistingParent(name, mcLoc("item/template_spawn_egg"));
    }

    protected String getName(Item item) {
        return ForgeRegistries.ITEMS.getKey(item).getPath();
    }

    protected String getName(Block item) {
        return ForgeRegistries.BLOCKS.getKey(item).getPath();
    }

    protected void woodCollection(WoodCollection collection) {
        Stream.of(
                        collection.leaves(),
                        collection.log(),
                        collection.planks(),
                        collection.slab(),
                        collection.stairs(),
                        collection.wood(),
                        collection.strippedWood(),
                        collection.strippedLog(),
                        collection.pressurePlate()
                )
                .map(Supplier::get)
                .forEach(this::simpleBlockItemModel);
        Stream.of(
                        collection.sign(),
                        collection.hangingSign(),
                        collection.sapling(),
                        collection.door()
                )
                .map(s -> s.get().asItem())
                .forEach(this::simpleGeneratedModel);
        fenceInventory(getName(collection.fence().get()), new ResourceLocation(Constants.MODID, "block/" + getName(collection.planks().get())));
        fenceGate(getName(collection.fenceGate().get()), new ResourceLocation(Constants.MODID, "block/" + getName(collection.planks().get())));
        buttonInventory(getName(collection.button().get()), new ResourceLocation(Constants.MODID, "block/" + getName(collection.planks().get())));
        simpleTrapdoorBlockItemModel(collection.trapdoor().get());
    }
}
