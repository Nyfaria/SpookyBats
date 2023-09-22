package com.nyfaria.spookybats.init;

import com.nyfaria.spookybats.Constants;
import com.nyfaria.spookybats.entity.CreeperBat;
import com.nyfaria.spookybats.entity.SpookyBat;
import com.nyfaria.spookybats.platform.Services;
import com.nyfaria.spookybats.registration.RegistrationProvider;
import com.nyfaria.spookybats.registration.RegistryObject;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ambient.Bat;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class EntityInit {
    public static final RegistrationProvider<EntityType<?>> ENTITIES = RegistrationProvider.get(Registries.ENTITY_TYPE, Constants.MODID);
    public static final List<AttributesRegister<?>> attributeSuppliers = new ArrayList<>();
    public static final RegistryObject<EntityType<SpookyBat>> PUMPKIN_BAT = registerEntity("pumpkin_bat", ()->EntityType.Builder.of(SpookyBat::new, MobCategory.MONSTER).sized(0.5F, 0.9F), SpookyBat::createAttributes,0xCB7438);
    public static final RegistryObject<EntityType<CreeperBat>> CREEPER_BAT = registerEntity("creeper_bat", ()->EntityType.Builder.of(CreeperBat::new, MobCategory.MONSTER).sized(0.5F, 0.9F), SpookyBat::createAttributes,0x85C280);
    public static final RegistryObject<EntityType<SpookyBat>> WITCH_BAT = registerEntity("witch_bat", ()->EntityType.Builder.of(SpookyBat::new, MobCategory.MONSTER).sized(0.5F, 0.9F), SpookyBat::createAttributes,0x361758);

    private static <T extends Entity> RegistryObject<EntityType<T>> registerEntity(String name, Supplier<EntityType.Builder<T>> supplier) {
        return ENTITIES.register(name, () -> supplier.get().build(Constants.MODID + ":" + name));
    }

    private static <T extends LivingEntity> RegistryObject<EntityType<T>> registerEntity(String name, Supplier<EntityType.Builder<T>> supplier,Supplier<AttributeSupplier.Builder> attributeSupplier,  int secondaryColor) {
        return registerEntity(name, supplier, attributeSupplier, 0x392F24, secondaryColor);
    }
    private static <T extends LivingEntity> RegistryObject<EntityType<T>> registerEntity(String name, Supplier<EntityType.Builder<T>> supplier,
                                                                                         Supplier<AttributeSupplier.Builder> attributeSupplier, int primaryColor, int secondaryColor) {
        RegistryObject<EntityType<T>> entityTypeSupplier = registerEntity(name, supplier);
        attributeSuppliers.add(new AttributesRegister<>(entityTypeSupplier, attributeSupplier));
        ItemInit.ITEMS.register(name + "_spawn_egg", () -> Services.PLATFORM.createSpawnEggItem(entityTypeSupplier, primaryColor, secondaryColor));
        return entityTypeSupplier;
    }

    public static void loadClass() {
    }


    public record AttributesRegister<E extends LivingEntity>(Supplier<EntityType<E>> entityTypeSupplier, Supplier<AttributeSupplier.Builder> factory) {}
}
