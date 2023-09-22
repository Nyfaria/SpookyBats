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

public class ItemInit {
    public static final RegistrationProvider<Item> ITEMS = RegistrationProvider.get(Registries.ITEM, Constants.MODID);

    public static final RegistryObject<Item> PUMPKIN_CHOCOLATE_BAR = ITEMS.register("pumpkin_chocolate_bar", () -> new Item(getItemProperties().food(new FoodProperties.Builder().effect(new MobEffectInstance(MobEffects.GLOWING,100),1).build())));
    public static final RegistryObject<Item> TNT_LOLLIPOP = ITEMS.register("tnt_lollipop", () -> new Item(getItemProperties().food(new FoodProperties.Builder().effect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE,100),1).build())));
    public static final RegistryObject<Item> WITCHES_BREW = ITEMS.register("witches_brew", () -> new Item(getItemProperties().food(new FoodProperties.Builder().effect(new MobEffectInstance(MobEffects.INVISIBILITY,100),1).build())));
    public static final RegistrationProvider<CreativeModeTab> CREATIVE_MODE_TABS = RegistrationProvider.get(Registries.CREATIVE_MODE_TAB, Constants.MODID);
    public static final RegistryObject<CreativeModeTab> TAB = CREATIVE_MODE_TABS.register(Constants.MODID, () -> CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
            .icon(() -> new ItemStack(Items.DIRT))
            .displayItems(
                    (itemDisplayParameters, output) -> {
                        ITEMS.getEntries().forEach((registryObject) -> output.accept(new ItemStack(registryObject.get())));
                    }).title(Component.translatable("itemGroup." + Constants.MODID + ".tab"))
            .build());

    public static Item.Properties getItemProperties() {
        return new Item.Properties();
    }

    public static void loadClass() {
    }
}
