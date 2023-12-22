package com.nyfaria.batsgalore.init.entity;

import com.nyfaria.batsgalore.Constants;
import com.nyfaria.batsgalore.entity.*;
import com.nyfaria.batsgalore.init.ItemInit;
import com.nyfaria.batsgalore.platform.Services;
import com.nyfaria.batsgalore.registration.RegistrationProvider;
import com.nyfaria.batsgalore.registration.RegistryObject;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.item.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class EntityInit {
    public static final List<RegistryObject<? extends EntityType<? extends LivingEntity>>> BATS = new ArrayList<>();
    public static final RegistrationProvider<EntityType<?>> ENTITIES = RegistrationProvider.get(Registries.ENTITY_TYPE, Constants.MODID);
    public static final List<AttributesRegister<?>> attributeSuppliers = new ArrayList<>();

    
  public static final RegistryObject<EntityType<BlockProjectile>> BLOCK_PROJECTILE = registerEntity("block_projectile", () -> EntityType.Builder.<BlockProjectile>of(BlockProjectile::new, MobCategory.MISC).sized(1F, 1F));
    public static final RegistryObject<EntityType<ModBoat>> MOD_BOAT = registerEntity("mod_boat", () -> EntityType.Builder.<ModBoat>of(ModBoat::new, MobCategory.MISC).sized(1.375F, 0.5625F).clientTrackingRange(10));
    public static final RegistryObject<EntityType<ModChestBoat>> MOD_CHEST_BOAT = registerEntity("mod_chest_boat", () -> EntityType.Builder.<ModChestBoat>of(ModChestBoat::new, MobCategory.MISC).sized(1.375F, 0.5625F).clientTrackingRange(10));


    protected static <T extends Entity> RegistryObject<EntityType<T>> registerEntity(String name, Supplier<EntityType.Builder<T>> supplier) {
        return ENTITIES.register(name, () -> supplier.get().build(Constants.MODID + ":" + name));
    }

    protected static <T extends LivingEntity> RegistryObject<EntityType<T>> registerLivingEntity(String name, Supplier<EntityType.Builder<T>> supplier,
                                                                                               Supplier<AttributeSupplier.Builder> attributeSupplier) {
        RegistryObject<EntityType<T>> entityTypeSupplier = registerEntity(name, supplier);
        attributeSuppliers.add(new AttributesRegister<>(entityTypeSupplier, attributeSupplier));
        return entityTypeSupplier;
    }

    protected static <T extends LivingEntity> RegistryObject<EntityType<T>> registerEntityWithEgg(String name, Supplier<EntityType.Builder<T>> supplier,
                                                                                                Supplier<AttributeSupplier.Builder> attributeSupplier,
                                                                                                int secondaryColor) {
        return registerEntityWithEgg(name, supplier, attributeSupplier, 0x392F24, secondaryColor);
    }

    protected static <T extends LivingEntity> RegistryObject<EntityType<T>> registerEntityWithEgg(String name, Supplier<EntityType.Builder<T>> supplier,
                                                                                                Supplier<AttributeSupplier.Builder> attributeSupplier,
                                                                                                int primaryColor, int secondaryColor) {
        RegistryObject<EntityType<T>> entityTypeSupplier = registerLivingEntity(name, supplier, attributeSupplier);
        RegistryObject<Item> item = ItemInit.ITEMS.register(name + "_spawn_egg", () -> Services.PLATFORM.createSpawnEggItem(entityTypeSupplier, primaryColor, secondaryColor));
        ItemInit.SPAWN_EGG_LIST.add(item);
        BATS.add(entityTypeSupplier);

        return entityTypeSupplier;
    }

    public static void loadClass() {
        SpookyBatEntityInit.loadClass();
        ChristmasBatEntityInit.loadClass();
    }


    public record AttributesRegister<E extends LivingEntity>(Supplier<EntityType<E>> entityTypeSupplier,
                                                             Supplier<AttributeSupplier.Builder> factory) {
    }
}
