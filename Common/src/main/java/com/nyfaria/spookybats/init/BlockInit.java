package com.nyfaria.spookybats.init;

import com.nyfaria.spookybats.Constants;
import com.nyfaria.spookybats.block.SpookyPedestal;
import com.nyfaria.spookybats.block.entity.SpookyPedestalBlockEntity;
import com.nyfaria.spookybats.registration.RegistrationProvider;
import com.nyfaria.spookybats.registration.RegistryObject;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

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
    public static final RegistryObject<BlockEntityType<SpookyPedestalBlockEntity>> SPOOKY_PEDESTAL_BLOCK_ENTITY = BLOCK_ENTITIES.register("spooky_pedestal", () -> BlockEntityType.Builder.of(SpookyPedestalBlockEntity::new, SPOOKY_PEDESTAL.get()).build(null));

    public static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        return registerBlock(name, block, b -> () -> new BlockItem(b.get(), ItemInit.getItemProperties()));
    }
    protected static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, Function<RegistryObject<T>, Supplier<? extends BlockItem>> item) {
        var reg = BLOCKS.register(name, block);
        ItemInit.ITEMS.register(name, () -> item.apply(reg).get());
        return reg;
    }

    public static void loadClass() {
    }
}
