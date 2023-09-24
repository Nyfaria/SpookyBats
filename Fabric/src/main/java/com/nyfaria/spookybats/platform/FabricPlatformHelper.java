package com.nyfaria.spookybats.platform;

import com.nyfaria.spookybats.platform.services.IPlatformHelper;
import com.nyfaria.spookybats.registration.RegistryObject;
import net.fabricmc.fabric.api.item.v1.EquipmentSlotProvider;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;

public class FabricPlatformHelper<T extends Mob> implements IPlatformHelper<T> {

    @Override
    public String getPlatformName() {
        return "Fabric";
    }

    @Override
    public boolean isModLoaded(String modId) {

        return FabricLoader.getInstance().isModLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {

        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }



    @Override
    public SpawnEggItem createSpawnEggItem(RegistryObject<EntityType<T>> entityTypeRegistryObject, int primaryColor, int secondaryColor) {
        return new SpawnEggItem(entityTypeRegistryObject.get(), primaryColor, secondaryColor, new SpawnEggItem.Properties());
    }

    @Override
    public Item.Properties getElytraItemProperties() {
        return new FabricItemSettings().equipmentSlot((stack)-> EquipmentSlot.CHEST);
    }
}
