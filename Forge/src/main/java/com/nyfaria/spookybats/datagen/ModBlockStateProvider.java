package com.nyfaria.spookybats.datagen;

import com.nyfaria.spookybats.Constants;
import com.nyfaria.spookybats.block.WoodCollection;
import com.nyfaria.spookybats.init.BlockInit;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.stream.Stream;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput generator, ExistingFileHelper existingFileHelper) {
        super(generator, Constants.MODID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

        // Stream.of(
        //
        //         )
        //         .map(Supplier::get)
        //         .forEach(this::simpleCubeBottomTopBlockState);
        //
        // Stream.of(
        //
        // ).map(Supplier::get)
        //         .forEach(this::simpleBlock);
        Stream.of(
                BlockInit.SPOOKY_OAK
        ).forEach(this::woodCollection);
    }
    private String name(Block block) {
        return key(block).getPath();
    }
    private ResourceLocation key(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block);
    }
    protected void simpleCubeBottomTopBlockState(Block block) {
        simpleBlock(block, blockCubeTopModel(block));
    }

    protected BlockModelBuilder blockCubeTopModel(Block block) {
        String name = getName(block);
        return models().cubeBottomTop(name, modLoc("block/" + name + "_side"), modLoc("block/" + name + "_bottom"), modLoc("block/" + name + "_top"));
    }

    protected String getName(Block item) {
        return ForgeRegistries.BLOCKS.getKey(item).getPath();
    }

    public void woodCollection(WoodCollection collection) {
        ResourceLocation location = modLoc("block/" + collection.name() + "_planks");
        logBlock(collection.log().get());
        logBlock(collection.strippedLog().get());
        logBlock(collection.wood().get());
        logBlock(collection.strippedWood().get());
        simpleBlock(collection.planks().get());
        stairsBlock(collection.stairs().get(), location);
        fenceBlock(collection.fence().get(), location);
        fenceGateBlock(collection.fenceGate().get(), location);
        slabBlock(collection.slab().get(), location, location);
        doorBlockWithRenderType(collection.door().get(), new ResourceLocation(Constants.MODID,"block/" + collection.name() + "_door_bottom"), new ResourceLocation(Constants.MODID,"block/" + collection.name() + "_door_top"),"cutout");
        trapdoorBlockWithRenderType(collection.trapdoor().get(), new ResourceLocation(Constants.MODID,"block/" + collection.name() + "_trapdoor"), true, "cutout");
        buttonBlock(collection.button().get(), location);
        pressurePlateBlock(collection.pressurePlate().get(), location);
        signBlock(collection.sign().get(), collection.wallSign().get(), location);
        leaves(collection.leaves().get());
        simpleLeavesBlockState(collection.leaves().get());

    }
    protected void simpleLeavesBlockState(Block block) {
        simpleBlock(block, leaves(block));
    }
    protected BlockModelBuilder leaves(Block block) {
        String name = getName(block);
        return models().withExistingParent(name, "block/leaves")
                .texture("all", modLoc("block/" + name)
                ).renderType("cut_out");
    }
}
