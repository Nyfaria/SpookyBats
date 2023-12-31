package com.nyfaria.batsgalore.init.entity;

import com.nyfaria.batsgalore.entity.CreeperBat;
import com.nyfaria.batsgalore.entity.EvilBat;
import com.nyfaria.batsgalore.entity.ExperienceOrbBat;
import com.nyfaria.batsgalore.entity.GhostBat;
import com.nyfaria.batsgalore.entity.HerobrineBat;
import com.nyfaria.batsgalore.entity.MonsterBat;
import com.nyfaria.batsgalore.entity.PlayerBat;
import com.nyfaria.batsgalore.entity.PumpkinBat;
import com.nyfaria.batsgalore.entity.SculkBat;
import com.nyfaria.batsgalore.entity.ShulkerBat;
import com.nyfaria.batsgalore.entity.SlimeBat;
import com.nyfaria.batsgalore.entity.UndeadBat;
import com.nyfaria.batsgalore.entity.VoidBat;
import com.nyfaria.batsgalore.entity.WingedTurmoil;
import com.nyfaria.batsgalore.entity.WitchBat;
import com.nyfaria.batsgalore.entity.WitchsBroom;
import com.nyfaria.batsgalore.entity.WitherSkeletonBat;
import com.nyfaria.batsgalore.entity.api.ModBat;
import com.nyfaria.batsgalore.entity.projectile.JackOLanternProjectile;
import com.nyfaria.batsgalore.registration.RegistryObject;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class SpookyBatEntityInit extends EntityInit {
    /* these are bats */
    public static final RegistryObject<EntityType<PumpkinBat>> PUMPKIN_BAT = registerEntityWithEgg("pumpkin_bat", () -> EntityType.Builder.of(PumpkinBat::new, MobCategory.MONSTER).sized(0.5F, 0.9F), ModBat::createBatAttributes, 0xdd993e);
    public static final RegistryObject<EntityType<CreeperBat>> CREEPER_BAT = registerEntityWithEgg("creeper_bat", () -> EntityType.Builder.of(CreeperBat::new, MobCategory.MONSTER).sized(0.5F, 0.9F), ModBat::createBatAttributes, 0x4fbc44);
    public static final RegistryObject<EntityType<WitchBat>> WITCH_BAT = registerEntityWithEgg("witch_bat", () -> EntityType.Builder.of(WitchBat::new, MobCategory.MONSTER).sized(0.5F, 0.9F), WitchBat::createWitchBatAttributes, 0xe388c2);
    public static final RegistryObject<EntityType<SculkBat>> SCULK_BAT = registerEntityWithEgg("sculk_bat", () -> EntityType.Builder.of(SculkBat::new, MobCategory.MONSTER).sized(0.5F, 0.9F), ModBat::createBatAttributes, 0x0e151c, 0x009295);
    public static final RegistryObject<EntityType<PlayerBat>> STEVE_BAT = registerEntityWithEgg("steve_bat", () -> EntityType.Builder.of(PlayerBat::new, MobCategory.MONSTER).sized(0.5F, 0.9F), PlayerBat::createPlayerBatAttributes, 0x679cb8);
    public static final RegistryObject<EntityType<PlayerBat>> ALEX_BAT = registerEntityWithEgg("alex_bat", () -> EntityType.Builder.of(PlayerBat::new, MobCategory.MONSTER).sized(0.5F, 0.9F), PlayerBat::createPlayerBatAttributes, 0xf7bd80);
    public static final RegistryObject<EntityType<HerobrineBat>> HEROBRINE_BAT = registerEntityWithEgg("herobrine_bat", () -> EntityType.Builder.of(HerobrineBat::new, MobCategory.MONSTER).sized(0.5F, 0.9F), HerobrineBat::createHerobrineBatAttributes, 0xffffff);
    public static final RegistryObject<EntityType<MonsterBat>> SKELETON_BAT = registerEntityWithEgg("skeleton_bat", () -> EntityType.Builder.of(MonsterBat::new, MobCategory.MONSTER).sized(0.5F, 0.9F), ModBat::createBatAttributes, 0xd3d3d3, 0x828282);
    public static final RegistryObject<EntityType<WitherSkeletonBat>> WITHER_SKELETON_BAT = registerEntityWithEgg("wither_skeleton_bat", () -> EntityType.Builder.of(WitherSkeletonBat::new, MobCategory.MONSTER).sized(0.5F, 0.9F), WitherSkeletonBat::createWitherSkeletonBatAttributes, 0x292929, 0x151515);
    public static final RegistryObject<EntityType<SlimeBat>> SLIME_BAT = registerEntityWithEgg("slime_bat", () -> EntityType.Builder.of(SlimeBat::new, MobCategory.MONSTER).sized(0.5F, 0.9F), ModBat::createBatAttributes, 0x48a549);
    public static final RegistryObject<EntityType<UndeadBat>> UNDEAD_BAT = registerEntityWithEgg("undead_bat", () -> EntityType.Builder.of(UndeadBat::new, MobCategory.MONSTER).sized(0.5F, 0.9F), ModBat::createBatAttributes, 0x323133, 0xac6646);
    public static final RegistryObject<EntityType<ModBat>> PLAYER_BAT = registerEntityWithEgg("player_bat", () -> EntityType.Builder.of(ModBat::new, MobCategory.MONSTER).sized(0.5F, 0.9F), ModBat::createBatAttributes, 0x423743);
    public static final RegistryObject<EntityType<ExperienceOrbBat>> EXPERIENCE_ORB_BAT = registerEntityWithEgg("experience_orb_bat", () -> EntityType.Builder.of(ExperienceOrbBat::new, MobCategory.MONSTER).sized(0.5F, 0.9F), ExperienceOrbBat::createXPOrbBatAttributes, 0x75f64a);
    public static final RegistryObject<EntityType<ShulkerBat>> SHULKER_BAT = registerEntityWithEgg("shulker_bat", () -> EntityType.Builder.of(ShulkerBat::new, MobCategory.MONSTER).sized(0.5F, 0.9F), ShulkerBat::createShulkerBatAttributes, 0x976697);



    /* These bats don't naturally spawn */
    public static final RegistryObject<EntityType<GhostBat>> GHOST_BAT = registerEntityWithEgg("ghost_bat", () -> EntityType.Builder.of(GhostBat::new, MobCategory.MONSTER).sized(0.5F, 0.9F), GhostBat::createGhostBatAttributes, 0x7c7781, 0xbdbdc4);
    public static final RegistryObject<EntityType<EvilBat>> EVIL_BAT = registerEntityWithEgg("evil_bat", () -> EntityType.Builder.of(EvilBat::new, MobCategory.MONSTER).sized(0.5F, 0.9F), EvilBat::createEvilBatAttributes, 0x942121);
    public static final RegistryObject<EntityType<VoidBat>> VOID_BAT = registerEntityWithEgg("void_bat", () -> EntityType.Builder.of(VoidBat::new, MobCategory.MONSTER).sized(0.5F, 0.9F), ModBat::createBatAttributes, 0x2b3b39, 0x29615e);
    public static final RegistryObject<EntityType<WingedTurmoil>> WINGED_TURMOIL = registerEntityWithEgg("winged_turmoil", () -> EntityType.Builder.of(WingedTurmoil::new, MobCategory.MONSTER).sized(0.8F, 3.2F), WingedTurmoil::createWingedTurmoilAttributes, 0x942121);

    /* These are not bats */
    public static final RegistryObject<EntityType<WitchsBroom>> WITCHS_BROOM = registerLivingEntity("witchs_broom", () -> EntityType.Builder.of(WitchsBroom::new, MobCategory.MISC).sized(1F, 0.4F), WitchsBroom::createAttributes);
    public static final RegistryObject<EntityType<JackOLanternProjectile>> JACK_O_LANTERN_PROJECTILE = registerEntity("jack_o_lantern_projectile", () -> EntityType.Builder.<JackOLanternProjectile>of(JackOLanternProjectile::new, MobCategory.MISC).sized(0.25F, 0.25F));
    public static void loadClass() {
    }
}
