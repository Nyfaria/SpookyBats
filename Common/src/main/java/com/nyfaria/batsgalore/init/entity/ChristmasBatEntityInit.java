package com.nyfaria.batsgalore.init.entity;

import com.nyfaria.batsgalore.entity.api.ModBat;
import com.nyfaria.batsgalore.registration.RegistryObject;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class ChristmasBatEntityInit extends EntityInit {

    public static final RegistryObject<EntityType<ModBat>> CANDY_CANE_BAT = registerEntityWithEgg("candy_cane_bat", ()->EntityType.Builder.of(ModBat::new, MobCategory.MONSTER).sized(0.5F, 0.5F).clientTrackingRange(5),ModBat::createBatAttributes,16777215,16711680);
    public static final RegistryObject<EntityType<ModBat>> REINDEER_BAT = registerEntityWithEgg("reindeer_bat", ()->EntityType.Builder.of(ModBat::new, MobCategory.MONSTER).sized(0.5F, 0.9F).clientTrackingRange(5),ModBat::createBatAttributes,16711680);
    public static final RegistryObject<EntityType<ModBat>> ELF_BAT = registerEntityWithEgg("elf_bat", ()->EntityType.Builder.of(ModBat::new, MobCategory.MONSTER).sized(0.5F, 0.9F).clientTrackingRange(5),ModBat::createBatAttributes,1861120,16711680);

    public static void loadClass() {
    }

}
