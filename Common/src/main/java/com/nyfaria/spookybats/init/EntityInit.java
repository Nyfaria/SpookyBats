package com.nyfaria.spookybats.init;

import com.nyfaria.spookybats.Constants;
import com.nyfaria.spookybats.entity.*;
import com.nyfaria.spookybats.entity.api.SpookyBat;
import com.nyfaria.spookybats.entity.projectile.JackOLanternProjectile;
import com.nyfaria.spookybats.platform.Services;
import com.nyfaria.spookybats.registration.RegistrationProvider;
import com.nyfaria.spookybats.registration.RegistryObject;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.item.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class EntityInit {
    public static final  List<RegistryObject<? extends EntityType<? extends LivingEntity>>> BATS = new ArrayList<>();
    public static final RegistrationProvider<EntityType<?>> ENTITIES = RegistrationProvider.get(Registries.ENTITY_TYPE, Constants.MODID);
    public static final List<AttributesRegister<?>> attributeSuppliers = new ArrayList<>();

    public static final RegistryObject<EntityType<PumpkinBat>> PUMPKIN_BAT = registerEntityWithEgg("pumpkin_bat", ()->EntityType.Builder.of(PumpkinBat::new, MobCategory.MONSTER).sized(0.5F, 0.9F), SpookyBat::createBatAttributes,0xdd993e);
    public static final RegistryObject<EntityType<CreeperBat>> CREEPER_BAT = registerEntityWithEgg("creeper_bat", ()->EntityType.Builder.of(CreeperBat::new, MobCategory.MONSTER).sized(0.5F, 0.9F), SpookyBat::createBatAttributes,0x4fbc44);
    public static final RegistryObject<EntityType<WitchBat>> WITCH_BAT = registerEntityWithEgg("witch_bat", ()->EntityType.Builder.of(WitchBat::new, MobCategory.MONSTER).sized(0.5F, 0.9F), WitchBat::createWitchBatAttributes,0xe388c2);
    public static final RegistryObject<EntityType<SpookyBat>> SCULK_BAT = registerEntityWithEgg("sculk_bat", ()->EntityType.Builder.of(SpookyBat::new, MobCategory.MONSTER).sized(0.5F, 0.9F), SpookyBat::createBatAttributes,0x0e151c, 0x009295);
    public static final RegistryObject<EntityType<GhostBat>> GHOST_BAT = registerEntityWithEgg("ghost_bat", ()->EntityType.Builder.of(GhostBat::new, MobCategory.MONSTER).sized(0.5F, 0.9F), GhostBat::createGhostBatAttributes,0x7c7781, 0xbdbdc4);
    public static final RegistryObject<EntityType<PlayerBat>> STEVE_BAT = registerEntityWithEgg("steve_bat", ()->EntityType.Builder.of(PlayerBat::new, MobCategory.MONSTER).sized(0.5F, 0.9F), PlayerBat::createPlayerBatAttributes,0x679cb8);
    public static final RegistryObject<EntityType<PlayerBat>> ALEX_BAT = registerEntityWithEgg("alex_bat", ()->EntityType.Builder.of(PlayerBat::new, MobCategory.MONSTER).sized(0.5F, 0.9F), PlayerBat::createPlayerBatAttributes,0xf7bd80);
    public static final RegistryObject<EntityType<HerobrineBat>> HEROBRINE_BAT = registerEntityWithEgg("herobrine_bat", ()->EntityType.Builder.of(HerobrineBat::new, MobCategory.MONSTER).sized(0.5F, 0.9F), HerobrineBat::createHerobrineBatAttributes,0xffffff);
    public static final RegistryObject<EntityType<MonsterBat>> SKELETON_BAT = registerEntityWithEgg("skeleton_bat", ()->EntityType.Builder.of(MonsterBat::new, MobCategory.MONSTER).sized(0.5F, 0.9F), SpookyBat::createBatAttributes,0xd3d3d3, 0x828282);
    public static final RegistryObject<EntityType<WitherSkeletonBat>> WITHER_SKELETON_BAT = registerEntityWithEgg("wither_skeleton_bat", ()->EntityType.Builder.of(WitherSkeletonBat::new, MobCategory.MONSTER).sized(0.5F, 0.9F), WitherSkeletonBat::createWitherSkeletonBatAttributes,0x292929,0x151515);
    public static final RegistryObject<EntityType<UndeadBat>> UNDEAD_BAT = registerEntityWithEgg("undead_bat", ()->EntityType.Builder.of(UndeadBat::new, MobCategory.MONSTER).sized(0.5F, 0.9F), SpookyBat::createBatAttributes,0x323133, 0xac6646);
    public static final RegistryObject<EntityType<SpookyBat>> PLAYER_BAT = registerEntityWithEgg("player_bat", ()->EntityType.Builder.of(SpookyBat::new, MobCategory.MONSTER).sized(0.5F, 0.9F), SpookyBat::createBatAttributes,0x423743);
    public static final RegistryObject<EntityType<VoidBat>> VOID_BAT = registerEntityWithEgg("void_bat", ()->EntityType.Builder.of(VoidBat::new, MobCategory.MONSTER).sized(0.5F, 0.9F), SpookyBat::createBatAttributes,0x2b3b39, 0x29615e);
    public static final RegistryObject<EntityType<WitchsBroom>> WITCHS_BROOM = registerLivingEntity("witchs_broom", ()->EntityType.Builder.of(WitchsBroom::new, MobCategory.MISC).sized(1F, 0.4F), WitchsBroom::createAttributes);






    public static final RegistryObject<EntityType<JackOLanternProjectile>> JACK_O_LANTERN_PROJECTILE = registerEntity("jack_o_lantern_projectile", ()->EntityType.Builder.<JackOLanternProjectile>of(JackOLanternProjectile::new, MobCategory.MISC).sized(0.25F, 0.25F).clientTrackingRange(4));


    private static <T extends Entity> RegistryObject<EntityType<T>> registerEntity(String name, Supplier<EntityType.Builder<T>> supplier) {
        return ENTITIES.register(name, () -> supplier.get().build(Constants.MODID + ":" + name));
    }
    private static <T extends LivingEntity> RegistryObject<EntityType<T>> registerLivingEntity(String name, Supplier<EntityType.Builder<T>> supplier,
                                                                                                Supplier<AttributeSupplier.Builder> attributeSupplier) {
        RegistryObject<EntityType<T>> entityTypeSupplier = registerEntity(name, supplier);
        attributeSuppliers.add(new AttributesRegister<>(entityTypeSupplier, attributeSupplier));
        return entityTypeSupplier;
    }
    private static <T extends LivingEntity> RegistryObject<EntityType<T>> registerEntityWithEgg(String name, Supplier<EntityType.Builder<T>> supplier,
                                                                                                Supplier<AttributeSupplier.Builder> attributeSupplier,
                                                                                                int secondaryColor) {
        return registerEntityWithEgg(name, supplier, attributeSupplier, 0x392F24, secondaryColor);
    }
    private static <T extends LivingEntity> RegistryObject<EntityType<T>> registerEntityWithEgg(String name, Supplier<EntityType.Builder<T>> supplier,
                                                                                                Supplier<AttributeSupplier.Builder> attributeSupplier,
                                                                                                int primaryColor, int secondaryColor) {
        RegistryObject<EntityType<T>> entityTypeSupplier = registerLivingEntity(name, supplier, attributeSupplier);
        RegistryObject<Item>item = ItemInit.ITEMS.register(name + "_spawn_egg", () -> Services.PLATFORM.createSpawnEggItem(entityTypeSupplier, primaryColor, secondaryColor));
        ItemInit.SPAWN_EGG_LIST.add(item);
        BATS.add(entityTypeSupplier);
        return entityTypeSupplier;
    }

    public static void loadClass() {
    }


    public record AttributesRegister<E extends LivingEntity>(Supplier<EntityType<E>> entityTypeSupplier, Supplier<AttributeSupplier.Builder> factory) {}
}
