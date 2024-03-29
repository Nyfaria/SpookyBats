package com.nyfaria.batsgalore.init;

import com.nyfaria.batsgalore.Constants;
import com.nyfaria.batsgalore.block.WoodCollection;
import com.nyfaria.batsgalore.entity.api.ModBoatType;
import com.nyfaria.batsgalore.item.BatWings;
import com.nyfaria.batsgalore.item.CoreItem;
import com.nyfaria.batsgalore.item.CosmeticItem;
import com.nyfaria.batsgalore.item.ModBoatItem;
import com.nyfaria.batsgalore.item.StaffOfTurmoil;
import com.nyfaria.batsgalore.item.WitchsBroomItem;
import com.nyfaria.batsgalore.platform.Services;
import com.nyfaria.batsgalore.registration.RegistrationProvider;
import com.nyfaria.batsgalore.registration.RegistryObject;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Blocks;

import java.util.ArrayList;
import java.util.List;

public class ItemInit {
    public static final List<RegistryObject<Item>> CANDY_LIST = new ArrayList<>();
    public static final List<RegistryObject<Item>> SPAWN_EGG_LIST = new ArrayList<>();
    public static final RegistrationProvider<Item> ITEMS = RegistrationProvider.get(Registries.ITEM, Constants.MODID);

    public static final RegistryObject<Item> BAT_WINGS = ITEMS.register("bat_wings", () -> new BatWings(Services.PLATFORM.getElytraItemProperties().rarity(Rarity.UNCOMMON)));

    public static final RegistryObject<Item> PUMPKIN_CHOCOLATE_BAR = registerCandy("pumpkin_chocolate_bar",MobEffects.GLOWING);
    public static final RegistryObject<Item> TNT_LOLLIPOP = registerCandy("tnt_lollipop", MobEffects.DAMAGE_RESISTANCE);
    public static final RegistryObject<Item> WITCHES_BREW = registerCandy("witches_brew", MobEffects.INVISIBILITY);
    public static final RegistryObject<Item> SKULL_CANDY = registerCandy("skull_candy", MobEffects.NIGHT_VISION);
    public static final RegistryObject<Item> WITHER_SKULL_CANDY = registerCandy("wither_skull_candy", MobEffects.WITHER);
    public static final RegistryObject<Item> ZOMBIE_FLESH_LOLLIPOP = registerCandy("zombie_flesh_lollipop", MobEffects.HUNGER);
    public static final RegistryObject<Item> GENERIC_CANDY = registerCandy("generic_candy", MobEffects.ABSORPTION);
    public static final RegistryObject<Item> SUSPICIOUS_CANDY = registerCandy("suspicious_candy", MobEffects.CONFUSION);
    public static final RegistryObject<Item> SCULK_CANDY = registerCandy("sculk_candy", MobEffects.DARKNESS);
    public static final RegistryObject<Item> BITESIZED_JELLY = registerCandy("bitesized_jelly", MobEffects.JUMP);
    public static final RegistryObject<Item> CANDY_CANE = registerCandy("candy_cane", MobEffects.MOVEMENT_SPEED);

    public static final RegistryObject<Item> WITCHS_BROOM = ITEMS.register("witchs_broom", ()->new WitchsBroomItem(getItemProperties().stacksTo(1)));

    public static final RegistryObject<Item> SCULK_CORE = ITEMS.register("sculk_core", ()->new CoreItem(getItemProperties().stacksTo(1), Blocks.REINFORCED_DEEPSLATE));
    public static final RegistryObject<Item> SHULKER_CORE = ITEMS.register("shulker_core", ()->new CoreItem(getItemProperties().stacksTo(1), Blocks.PURPUR_PILLAR));
    public static final RegistryObject<Item> SLIME_CORE = ITEMS.register("slime_core", ()->new CoreItem(getItemProperties().stacksTo(1), Blocks.CHISELED_STONE_BRICKS));
    public static final RegistryObject<Item> UNDEAD_CORE = ITEMS.register("undead_core", ()->new CoreItem(getItemProperties().stacksTo(1), Blocks.GILDED_BLACKSTONE));
    public static final RegistryObject<Item> STAFF_OF_TURMOIL = ITEMS.register("staff_of_turmoil", ()->new StaffOfTurmoil(getItemProperties().stacksTo(1)));
    public static final RegistryObject<Item> ELF_HAT = ITEMS.register("elf_hat", ()->new CosmeticItem(getItemProperties().stacksTo(1), EquipmentSlot.HEAD));
    public static final RegistryObject<Item> REINDEER_NOSE = ITEMS.register("reindeer_nose", ()->new CosmeticItem(getItemProperties().stacksTo(1), EquipmentSlot.HEAD));
    public static final RegistryObject<Item> NOVELTY_DRINKING_HAT = ITEMS.register("novelty_drinking_hat", ()->new CosmeticItem(getItemProperties().stacksTo(1), EquipmentSlot.HEAD));

    public static final RegistrationProvider<CreativeModeTab> CREATIVE_MODE_TABS = RegistrationProvider.get(Registries.CREATIVE_MODE_TAB, Constants.MODID);
    public static final RegistryObject<CreativeModeTab> TAB = CREATIVE_MODE_TABS.register(Constants.MODID, () -> CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
            .icon(() -> new ItemStack(BlockInit.STATUE_OF_TURMOIL.get()))
            .displayItems(
                    (itemDisplayParameters, output) -> {
                        SPAWN_EGG_LIST.forEach((registryObject) -> output.accept(new ItemStack(registryObject.get())));
                        CANDY_LIST.forEach((registryObject) -> output.accept(new ItemStack(registryObject.get())));
                        output.accept(WITCHS_BROOM.get());
                        output.accept(SCULK_CORE.get());
                        output.accept(SHULKER_CORE.get());
                        output.accept(SLIME_CORE.get());
                        output.accept(UNDEAD_CORE.get());
                        output.accept(ELF_HAT.get());
                        output.accept(REINDEER_NOSE.get());
                        output.accept(NOVELTY_DRINKING_HAT.get());
                        output.accept(BlockInit.SPOOKY_PEDESTAL.get());
                        output.accept(BlockInit.STATUE_OF_TURMOIL.get());
                        WoodCollection.WOOD_COLLECTIONS.forEach(collection->collection.getAll().forEach(
                                (registryObject) -> output.accept(new ItemStack(registryObject.get()))
                        ));
                        output.accept(BlockInit.DECORATED_WHITE_PINE_LEAVES.get());
                        output.accept(STAFF_OF_TURMOIL.get());
                    }).title(Component.translatable("itemGroup." + Constants.MODID + ".tab"))
            .build());


    public static Item.Properties getItemProperties() {
        return new Item.Properties();
    }

    public static void loadClass() {
    }

    public static RegistryObject<Item> registerCandy(String name, MobEffect effect) {
        RegistryObject<Item> item = ITEMS.register(name, () -> new Item(getItemProperties().food(new FoodProperties.Builder().alwaysEat().nutrition(1).saturationMod(0.5f).fast().effect(new MobEffectInstance(effect,100),1).build())));
        CANDY_LIST.add(item);
        return item;
    }
}
