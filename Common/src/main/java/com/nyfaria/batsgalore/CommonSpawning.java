package com.nyfaria.batsgalore;

import com.nyfaria.batsgalore.entity.MonsterBat;
import com.nyfaria.batsgalore.entity.api.ModBat;
import com.nyfaria.batsgalore.init.entity.ChristmasBatEntityInit;
import com.nyfaria.batsgalore.init.entity.SpookyBatEntityInit;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.Heightmap;

import java.util.List;

public class CommonSpawning {
    public static List<MobSpawnSettings.SpawnerData> WHITE_PINE_FOREST_SPAWNS = List.of(
            new MobSpawnSettings.SpawnerData(ChristmasBatEntityInit.CANDY_CANE_BAT.get(), 7, 1, 3),
            new MobSpawnSettings.SpawnerData(ChristmasBatEntityInit.REINDEER_BAT.get(), 7, 1, 3),
            new MobSpawnSettings.SpawnerData(ChristmasBatEntityInit.ELF_BAT.get(), 7, 1, 3)
    );
    public static List<MobSpawnSettings.SpawnerData> SPOOKY_OAK_FOREST_SPAWNS =
            List.of(
                    new MobSpawnSettings.SpawnerData(SpookyBatEntityInit.PUMPKIN_BAT.get(), 7, 1, 3),
                    new MobSpawnSettings.SpawnerData(SpookyBatEntityInit.CREEPER_BAT.get(), 5, 1, 3),
                    new MobSpawnSettings.SpawnerData(SpookyBatEntityInit.STEVE_BAT.get(), 5, 1, 3),
                    new MobSpawnSettings.SpawnerData(SpookyBatEntityInit.ALEX_BAT.get(), 5, 1, 3),
                    new MobSpawnSettings.SpawnerData(SpookyBatEntityInit.HEROBRINE_BAT.get(), 4, 1, 1),
                    new MobSpawnSettings.SpawnerData(SpookyBatEntityInit.SKELETON_BAT.get(), 5, 1, 3),
                    new MobSpawnSettings.SpawnerData(EntityType.BAT, 4, 1, 2),
                    new MobSpawnSettings.SpawnerData(SpookyBatEntityInit.PLAYER_BAT.get(), 5, 1, 2)
            );
    public static List<MobSpawnSettings.SpawnerData> OVERWORLD_SPAWNS =
            List.of(
              new MobSpawnSettings.SpawnerData(SpookyBatEntityInit.SLIME_BAT.get(), 2, 1,1)
            );
    public static List<MobSpawnSettings.SpawnerData> NETHER_SPAWNS = List.of(
            new MobSpawnSettings.SpawnerData(SpookyBatEntityInit.WITHER_SKELETON_BAT.get(), 5, 1, 3),
            new MobSpawnSettings.SpawnerData(SpookyBatEntityInit.UNDEAD_BAT.get(), 7, 1, 3)
    );
    public static List<MobSpawnSettings.SpawnerData> SWAMP_SPAWNS = List.of(
            new MobSpawnSettings.SpawnerData(SpookyBatEntityInit.WITCH_BAT.get(), 5, 1, 3),
            new MobSpawnSettings.SpawnerData(SpookyBatEntityInit.EXPERIENCE_ORB_BAT.get(), 3, 1, 1)
    );
    public static List<MobSpawnSettings.SpawnerData> MOUNTAIN_SPAWNS = List.of(
            new MobSpawnSettings.SpawnerData(SpookyBatEntityInit.EXPERIENCE_ORB_BAT.get(), 3, 1, 1)
    );
    public static List<MobSpawnSettings.SpawnerData> DEEP_DARK_SPAWNS = List.of(
            new MobSpawnSettings.SpawnerData(SpookyBatEntityInit.SCULK_BAT.get(), 6, 1, 3)
    );
    public static List<MobSpawnSettings.SpawnerData> END_SPAWNS = List.of(
            new MobSpawnSettings.SpawnerData(SpookyBatEntityInit.SHULKER_BAT.get(), 1, 1, 1)
    );
    public static void placements() {
        SpawnPlacements.register(SpookyBatEntityInit.PUMPKIN_BAT.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ModBat::checkModBatSpawnRules);
        SpawnPlacements.register(SpookyBatEntityInit.CREEPER_BAT.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, MonsterBat::checkMonsterBatSpawnRules);
        SpawnPlacements.register(SpookyBatEntityInit.WITCH_BAT.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ModBat::checkModBatSpawnRules);
        SpawnPlacements.register(SpookyBatEntityInit.ALEX_BAT.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ModBat::checkModBatSpawnRules);
        SpawnPlacements.register(SpookyBatEntityInit.HEROBRINE_BAT.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ModBat::checkModBatSpawnRules);
        SpawnPlacements.register(SpookyBatEntityInit.STEVE_BAT.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ModBat::checkModBatSpawnRules);
        SpawnPlacements.register(SpookyBatEntityInit.SKELETON_BAT.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, MonsterBat::checkMonsterBatSpawnRules);
        SpawnPlacements.register(SpookyBatEntityInit.WITHER_SKELETON_BAT.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ModBat::checkModBatSpawnRules);
        SpawnPlacements.register(SpookyBatEntityInit.UNDEAD_BAT.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ModBat::checkModBatSpawnRules);
        SpawnPlacements.register(SpookyBatEntityInit.PLAYER_BAT.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ModBat::checkModBatSpawnRules);
        SpawnPlacements.register(SpookyBatEntityInit.SCULK_BAT.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ModBat::checkModBatSpawnRules);
        SpawnPlacements.register(SpookyBatEntityInit.EXPERIENCE_ORB_BAT.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ModBat::checkModBatSpawnRules);
        SpawnPlacements.register(SpookyBatEntityInit.SLIME_BAT.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ModBat::checkSlimeSpawnRules);
        SpawnPlacements.register(SpookyBatEntityInit.SHULKER_BAT.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ModBat::checkShulkerSpawnRules);
        SpawnPlacements.register(ChristmasBatEntityInit.CANDY_CANE_BAT.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ModBat::checkModBatSpawnRules);
        SpawnPlacements.register(ChristmasBatEntityInit.REINDEER_BAT.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ModBat::checkModBatSpawnRules);
    }
}
