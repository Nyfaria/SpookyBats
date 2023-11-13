package com.nyfaria.spookybats.datagen;

import com.nyfaria.spookybats.Constants;
import com.nyfaria.spookybats.block.WoodCollection;
import com.nyfaria.spookybats.init.BlockInit;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.CrossCollisionBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.MultiPartBlockStateBuilder;
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
        ).forEach(this::spookyWoodCollection);
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

    public void spookyWoodCollection(WoodCollection collection) {
        ResourceLocation location = modLoc("block/" + collection.name() + "_planks");
        logBlock(collection.log().get());
//        logBlock(collection.strippedLog().get());

        axisBlock(collection.wood().get(), blockTexture(collection.log().get()), blockTexture(collection.log().get()));
//        logBlock(collection.strippedWood().get());
        simpleBlock(collection.planks().get());
        stairsBlock(collection.stairs().get(), location);
        fenceBlock(collection.fence().get());
//        fenceGateBlock(collection.fenceGate().get(), location);
        slabBlock(collection.slab().get(), location, location);
        doorBlockWithRenderType(collection.door().get(), new ResourceLocation(Constants.MODID,"block/" + collection.name() + "_door_bottom"), new ResourceLocation(Constants.MODID,"block/" + collection.name() + "_door_top"),"cutout");
        spookyTrapdoorBlock(collection.trapdoor().get(),getName(collection.trapdoor().get()));
        customButtonBlock(collection.button().get());
//        pressurePlateBlock(collection.pressurePlate().get(), location);
        signBlock(collection.sign().get(), collection.wallSign().get(), location);
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
    public void customButtonBlock(ButtonBlock block) {
        ModelFile button = models().getExistingFile(modLoc(name(block)));
        ModelFile buttonPressed = models().getExistingFile(modLoc(name(block) + "_pressed"));
        customButtonBlock(block, button, buttonPressed);
    }
    public void customButtonBlock(ButtonBlock block, ModelFile button, ModelFile buttonPressed) {
        getVariantBuilder(block).forAllStates(state -> {
            Direction facing = state.getValue(ButtonBlock.FACING);
            AttachFace face = state.getValue(ButtonBlock.FACE);
            boolean powered = state.getValue(ButtonBlock.POWERED);

            return ConfiguredModel.builder()
                    .modelFile(powered ? buttonPressed : button)
                    .rotationX(face == AttachFace.FLOOR ? 0 : (face == AttachFace.WALL ? 90 : 180))
                    .rotationY((int) (face == AttachFace.CEILING ? facing : facing.getOpposite()).toYRot())
                    .build();
        });
    }
    private void spookyTrapdoorBlock(TrapDoorBlock block, String baseName) {
        ModelFile bottom = models().getExistingFile(modLoc(baseName + "_bottom"));
        ModelFile top = models().getExistingFile(modLoc(baseName + "_top"));
        ModelFile topOpen = models().getExistingFile(modLoc(baseName + "_top_open"));
        ModelFile bottomOpen = models().getExistingFile(modLoc(baseName + "_bottom_open"));
        getVariantBuilder(block).forAllStatesExcept(state -> {
            int xRot = 0;
            int yRot = ((int) state.getValue(TrapDoorBlock.FACING).toYRot()) + 180;
            boolean isOpen = state.getValue(TrapDoorBlock.OPEN);
            if (isOpen && state.getValue(TrapDoorBlock.HALF) == Half.TOP) {
                xRot += 180;
                yRot += 180;
            }
            yRot %= 360;
            return ConfiguredModel.builder().modelFile(isOpen ? state.getValue(TrapDoorBlock.HALF) == Half.TOP ? topOpen : bottomOpen : state.getValue(TrapDoorBlock.HALF) == Half.TOP ? top : bottom)
                    .rotationX(xRot)
                    .rotationY(yRot)
                    .build();
        }, TrapDoorBlock.POWERED, TrapDoorBlock.WATERLOGGED);
    }
    public void fenceBlock(FenceBlock block) {
        fourWayBlock(block,
                models().getExistingFile(modLoc(name(block) + "_post")),
                models().getExistingFile(modLoc(name(block) + "_side")));
    }
    public void fourWayBlock(CrossCollisionBlock block, ModelFile post, ModelFile side) {
        MultiPartBlockStateBuilder builder = getMultipartBuilder(block)
                .part().modelFile(post).addModel().end();
        fourWayMultipart(builder, side);
    }

    public void fourWayMultipart(MultiPartBlockStateBuilder builder, ModelFile side) {
        PipeBlock.PROPERTY_BY_DIRECTION.entrySet().forEach(e -> {
            Direction dir = e.getKey();
            if (dir.getAxis().isHorizontal()) {
                builder.part().modelFile(side).rotationY((((int) dir.toYRot()) + 180) % 360).addModel()
                        .condition(e.getValue(), true);
            }
        });
    }
}
