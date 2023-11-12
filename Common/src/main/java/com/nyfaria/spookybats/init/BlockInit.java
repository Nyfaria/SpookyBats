package com.nyfaria.spookybats.init;

import com.nyfaria.spookybats.Constants;
import com.nyfaria.spookybats.block.SpookyOakLeaves;
import com.nyfaria.spookybats.block.SpookyPedestal;
import com.nyfaria.spookybats.block.WoodCollection;
import com.nyfaria.spookybats.block.entity.SpookyPedestalBlockEntity;
import com.nyfaria.spookybats.registration.RegistrationProvider;
import com.nyfaria.spookybats.registration.RegistryObject;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.MapColor;

import java.util.function.Function;
import java.util.function.Supplier;

public class BlockInit {
    public static final RegistrationProvider<Block> BLOCKS = RegistrationProvider.get(Registries.BLOCK, Constants.MODID);
    public static final RegistrationProvider<BlockEntityType<?>> BLOCK_ENTITIES = RegistrationProvider.get(Registries.BLOCK_ENTITY_TYPE, Constants.MODID);

    public static RegistryObject<Block> SPOOKY_PEDESTAL = registerBlock("spooky_pedestal", () -> new SpookyPedestal(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .mapColor(DyeColor.GRAY)
            .lightLevel((state) -> state.getValue(BlockStateProperties.LIT) ? 15 : 0)
            )
    );
    public static WoodCollection SPOOKY_OAK = WoodCollection.registerCollection("spooky_oak");

    public static final RegistryObject<BlockEntityType<SpookyPedestalBlockEntity>> SPOOKY_PEDESTAL_BLOCK_ENTITY = BLOCK_ENTITIES.register("spooky_pedestal", () -> BlockEntityType.Builder.of(SpookyPedestalBlockEntity::new, SPOOKY_PEDESTAL.get()).build(null));

    public static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        return registerBlock(name, block, b -> () -> new BlockItem(b.get(), ItemInit.getItemProperties()));
    }
    protected static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, Function<RegistryObject<T>, Supplier<? extends BlockItem>> item) {
        var reg = BLOCKS.register(name, block);
        ItemInit.ITEMS.register(name, () -> item.apply(reg).get());
        return reg;
    }
    public static <T extends Block> RegistryObject<T> registerBlockWithoutItem(String name, Supplier<T> block) {
        var reg = BLOCKS.register(name, block);
        return reg;
    }


    public static RotatedPillarBlock log(MapColor pTopColor, MapColor pBarkColor) {
        return new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor((pState) -> {
            return pState.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? pTopColor : pBarkColor;
        }).strength(2.0F).sound(SoundType.WOOD));
    }

    public static LeavesBlock leaves(SoundType pType) {
        return new SpookyOakLeaves(BlockBehaviour.Properties.of().strength(0.2F).randomTicks().sound(pType).noOcclusion().isValidSpawn(BlockInit::ocelotOrParrot).isSuffocating((blockState, blockGetter, blockPos) -> false).isViewBlocking((blockState, blockGetter, blockPos) -> false));
    }

    public static Boolean ocelotOrParrot(BlockState p_50822_, BlockGetter p_50823_, BlockPos p_50824_, EntityType<?> p_50825_) {
        return (boolean) (p_50825_ == EntityType.OCELOT || p_50825_ == EntityType.PARROT);
    }
    public static void loadClass() {
    }
}
