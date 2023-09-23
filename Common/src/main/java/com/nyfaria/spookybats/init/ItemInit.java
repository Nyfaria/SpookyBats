package com.nyfaria.spookybats.init;

import com.nyfaria.spookybats.Constants;
import com.nyfaria.spookybats.registration.RegistrationProvider;
import com.nyfaria.spookybats.registration.RegistryObject;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.ArrayList;
import java.util.List;

public class ItemInit {
    public static final List<RegistryObject<Item>> ITEM_LIST = new ArrayList<>();
    public static final List<RegistryObject<Item>> SPAWN_EGG_LIST = new ArrayList<>();
    public static final RegistrationProvider<Item> ITEMS = RegistrationProvider.get(Registries.ITEM, Constants.MODID);

    public static final RegistryObject<Item> PUMPKIN_CHOCOLATE_BAR = registerCandy("pumpkin_chocolate_bar",MobEffects.GLOWING);
    public static final RegistryObject<Item> TNT_LOLLIPOP = registerCandy("tnt_lollipop", MobEffects.DAMAGE_RESISTANCE);
    public static final RegistryObject<Item> WITCHES_BREW = registerCandy("witches_brew", MobEffects.INVISIBILITY);
    public static final RegistryObject<Item> SKULL_CANDY = registerCandy("skull_candy", MobEffects.NIGHT_VISION);
    public static final RegistryObject<Item> WITHER_SKULL_CANDY = registerCandy("wither_skull_candy", MobEffects.WITHER);
    public static final RegistryObject<Item> ZOMBIE_FLESH_LOLLIPOP = registerCandy("zombie_flesh_lollipop", MobEffects.HUNGER);
    public static final RegistryObject<Item> GENERIC_CANDY = registerCandy("spider_eye_lollipop", MobEffects.ABSORPTION);
    public static final RegistryObject<Item> SUSPICIOUS_CANDY = registerCandy("suspicious_candy", MobEffects.CONFUSION);

    public static final RegistrationProvider<CreativeModeTab> CREATIVE_MODE_TABS = RegistrationProvider.get(Registries.CREATIVE_MODE_TAB, Constants.MODID);
    public static final RegistryObject<CreativeModeTab> TAB = CREATIVE_MODE_TABS.register(Constants.MODID, () -> CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
            .icon(() -> new ItemStack(Items.DIRT))
            .displayItems(
                    (itemDisplayParameters, output) -> {
                        SPAWN_EGG_LIST.forEach((registryObject) -> output.accept(new ItemStack(registryObject.get())));
                        ITEM_LIST.forEach((registryObject) -> output.accept(new ItemStack(registryObject.get())));
                    }).title(Component.translatable("itemGroup." + Constants.MODID + ".tab"))
            .build());

    public static Item.Properties getItemProperties() {
        return new Item.Properties();
    }

    public static void loadClass() {
    }

    public static RegistryObject<Item> registerCandy(String name, MobEffect effect){
        RegistryObject<Item> item = ITEMS.register(name, () -> new Item(getItemProperties().food(new FoodProperties.Builder().effect(new MobEffectInstance(effect,100),1).build())));
        ITEM_LIST.add(item);
        return item;
    }
}
