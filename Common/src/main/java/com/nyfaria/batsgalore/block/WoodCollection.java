package com.nyfaria.batsgalore.block;

import com.nyfaria.batsgalore.Constants;
import com.nyfaria.batsgalore.entity.api.ModBoatType;
import com.nyfaria.batsgalore.init.BlockInit;
import com.nyfaria.batsgalore.init.ItemInit;
import com.nyfaria.batsgalore.item.ModBoatItem;
import com.nyfaria.batsgalore.registration.RegistryObject;
import net.minecraft.data.BlockFamily;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.HangingSignItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.CeilingHangingSignBlock;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.WallHangingSignBlock;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public record WoodCollection(String name, WoodType woodType, RegistryObject<SaplingBlock> sapling,
                             RegistryObject<RotatedPillarBlock> log, RegistryObject<RotatedPillarBlock> strippedLog,
                             RegistryObject<RotatedPillarBlock> wood, RegistryObject<RotatedPillarBlock> strippedWood,
                             RegistryObject<Block> planks, RegistryObject<StairBlock> stairs,
                             RegistryObject<SlabBlock> slab, RegistryObject<FenceBlock> fence,
                             RegistryObject<FenceGateBlock> fenceGate, RegistryObject<DoorBlock> door,
                             RegistryObject<TrapDoorBlock> trapdoor, RegistryObject<ButtonBlock> button,
                             RegistryObject<PressurePlateBlock> pressurePlate, RegistryObject<StandingSignBlock> sign,
                             RegistryObject<WallSignBlock> wallSign, RegistryObject<CeilingHangingSignBlock> hangingSign,
                             RegistryObject<WallHangingSignBlock> hangingWallSign, RegistryObject<LeavesBlock> leaves,
                             RegistryObject<ModBoatItem> boat, RegistryObject<ModBoatItem> chestBoat, TagKey<Item> logsTag, TagKey<Block> logsTagBlock) {
    public static List<WoodCollection> WOOD_COLLECTIONS = new ArrayList<>();

    public static WoodCollection registerCollection(String name, AbstractTreeGrower grower, Supplier<ModBoatType> boatType, TagKey<Item> logsTag, TagKey<Block> logsTagBlock) {
        BlockSetType blockSetType = BlockSetType.register(new BlockSetType(Constants.MODID + ":" + name));
        WoodType woodType = WoodType.register(new WoodType(Constants.MODID + ":" + name, blockSetType));
        RegistryObject<SaplingBlock> sapling = BlockInit.registerBlock(name + "_sapling", () -> new SaplingBlock(grower, BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));
        RegistryObject<RotatedPillarBlock> log = BlockInit.registerBlock(name + "_log", () -> BlockInit.log(MapColor.WOOD, MapColor.PODZOL));
        RegistryObject<RotatedPillarBlock> strippedLog = BlockInit.registerBlock(name + "_stripped_log", () -> BlockInit.log(MapColor.WOOD, MapColor.PODZOL));
        RegistryObject<RotatedPillarBlock> wood = BlockInit.registerBlock(name + "_wood", () -> BlockInit.log(MapColor.WOOD, MapColor.PODZOL));
        RegistryObject<RotatedPillarBlock> strippedWood = BlockInit.registerBlock(name + "_stripped_wood", () -> BlockInit.log(MapColor.WOOD, MapColor.PODZOL));
        RegistryObject<Block> planks = BlockInit.registerBlock(name + "_planks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.WOOD)));
        RegistryObject<StairBlock> stairs = BlockInit.registerBlock(name + "_stairs", () -> new StairBlock(planks.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.OAK_STAIRS)));
        RegistryObject<SlabBlock> slab = BlockInit.registerBlock(name + "_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SLAB)));
        RegistryObject<FenceBlock> fence = BlockInit.registerBlock(name + "_fence", () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE)));
        RegistryObject<FenceGateBlock> fenceGate = BlockInit.registerBlock(name + "_fence_gate", () -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE_GATE).mapColor(planks.get().defaultMapColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD), woodType));
        RegistryObject<DoorBlock> door = BlockInit.registerBlock(name + "_door", () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_DOOR).mapColor(planks.get().defaultMapColor()).strength(3.0F).sound(SoundType.WOOD).noOcclusion(), blockSetType));
        RegistryObject<TrapDoorBlock> trapdoor = BlockInit.registerBlock(name + "_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_TRAPDOOR).mapColor(MapColor.WOOD).strength(3.0F).sound(SoundType.WOOD).noOcclusion().isValidSpawn((a, b, c, d) -> false), blockSetType));
        RegistryObject<ButtonBlock> button = BlockInit.registerBlock(name + "_button", () -> Blocks.woodenButton(blockSetType));
        RegistryObject<PressurePlateBlock> pressurePlate = BlockInit.registerBlock(name + "_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(Blocks.OAK_PRESSURE_PLATE).mapColor(planks.get().defaultMapColor()).noCollission().strength(0.5F).sound(SoundType.WOOD), blockSetType));
        RegistryObject<StandingSignBlock> sign = BlockInit.registerBlockWithoutItem(name + "_sign", () -> new StandingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN), woodType));
        RegistryObject<WallSignBlock> wallSign = BlockInit.registerBlockWithoutItem(name + "_wall_sign", () -> new WallSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_SIGN), woodType));
        ItemInit.ITEMS.register(name + "_sign", () -> new SignItem(ItemInit.getItemProperties(), sign.get(), wallSign.get()));
        RegistryObject<CeilingHangingSignBlock> hangingSign = BlockInit.registerBlockWithoutItem(name + "_hanging_sign", () -> new CeilingHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN), woodType));
        RegistryObject<WallHangingSignBlock> wallHangingSign = BlockInit.registerBlockWithoutItem(name + "_wall_hanging_sign", () -> new WallHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_SIGN), woodType));
        ItemInit.ITEMS.register(name + "_hanging_sign", () -> new HangingSignItem(hangingSign.get(), wallHangingSign.get(), ItemInit.getItemProperties()));
        RegistryObject<LeavesBlock> leaves = BlockInit.registerBlock(name + "_leaves", () -> BlockInit.leaves(SoundType.GRASS));
        RegistryObject<ModBoatItem> boat = ItemInit.ITEMS.register(name + "_boat", () -> new ModBoatItem(false, boatType, ItemInit.getItemProperties().stacksTo(1)));
        RegistryObject<ModBoatItem> chestBoat = ItemInit.ITEMS.register(name + "_chest_boat", () -> new ModBoatItem(true, boatType, ItemInit.getItemProperties().stacksTo(1)));
        WoodCollection collection = new WoodCollection(name, woodType, sapling, log, strippedLog, wood, strippedWood, planks, stairs, slab, fence, fenceGate, door, trapdoor, button, pressurePlate, sign, wallSign, hangingSign,wallHangingSign, leaves, boat, chestBoat, logsTag, logsTagBlock);
        WOOD_COLLECTIONS.add(collection);
        return collection;
    }

    public BlockFamily getFamily(){
        return new BlockFamily.Builder(planks.get())
                .door(door.get())
                .trapdoor(trapdoor.get())
                .button(button.get())
                .pressurePlate(pressurePlate.get())
                .stairs(stairs.get())
                .slab(slab.get())
                .fence(fence.get())
                .fenceGate(fenceGate.get())
                .sign(sign.get(),wallSign.get())
                .recipeGroupPrefix("wooden")
                .recipeUnlockedBy("has_planks").getFamily();

    }
    public List<RegistryObject<? extends ItemLike>> getAll() {
        return List.of(sapling, log, strippedLog, wood, strippedWood, planks, stairs, slab, fence, fenceGate, door, trapdoor, button, pressurePlate, sign, leaves, hangingSign, boat, chestBoat);
    }
}
