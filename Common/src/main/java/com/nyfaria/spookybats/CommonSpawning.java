package com.nyfaria.spookybats;

import com.nyfaria.spookybats.entity.MonsterBat;
import com.nyfaria.spookybats.entity.api.SpookyBat;
import com.nyfaria.spookybats.init.EntityInit;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.Heightmap;

import java.util.List;

public class CommonSpawning {
    public static List<MobSpawnSettings.SpawnerData> SPOOKY_OAK_FOREST_SPAWNS =
            List.of(
                    new MobSpawnSettings.SpawnerData(EntityInit.PUMPKIN_BAT.get(), 7, 1, 3),
                    new MobSpawnSettings.SpawnerData(EntityInit.CREEPER_BAT.get(), 5, 1, 3),
                    new MobSpawnSettings.SpawnerData(EntityInit.STEVE_BAT.get(), 5, 1, 3),
                    new MobSpawnSettings.SpawnerData(EntityInit.ALEX_BAT.get(), 5, 1, 3),
                    new MobSpawnSettings.SpawnerData(EntityInit.HEROBRINE_BAT.get(), 4, 1, 1),
                    new MobSpawnSettings.SpawnerData(EntityInit.SKELETON_BAT.get(), 5, 1, 3),
                    new MobSpawnSettings.SpawnerData(EntityInit.PLAYER_BAT.get(), 5, 1, 2)
            );
    public static List<MobSpawnSettings.SpawnerData> NETHER_SPAWNS = List.of(
            new MobSpawnSettings.SpawnerData(EntityInit.WITHER_SKELETON_BAT.get(), 5, 1, 3),
            new MobSpawnSettings.SpawnerData(EntityInit.UNDEAD_BAT.get(), 7, 1, 3)
    );
    public static List<MobSpawnSettings.SpawnerData> SWAMP_SPAWNS = List.of(
            new MobSpawnSettings.SpawnerData(EntityInit.WITCH_BAT.get(), 5, 1, 3),
            new MobSpawnSettings.SpawnerData(EntityInit.EXPERIENCE_ORB_BAT.get(), 3, 1, 1)
    );
    public static List<MobSpawnSettings.SpawnerData> MOUNTAIN_SPAWNS = List.of(
            new MobSpawnSettings.SpawnerData(EntityInit.EXPERIENCE_ORB_BAT.get(), 3, 1, 1)
    );
    public static List<MobSpawnSettings.SpawnerData> DEEP_DARK_SPAWNS = List.of(
            new MobSpawnSettings.SpawnerData(EntityInit.SCULK_BAT.get(), 6, 1, 3)
    );
    public static List<MobSpawnSettings.SpawnerData> END_SPAWNS = List.of(
            new MobSpawnSettings.SpawnerData(EntityInit.SHULKER_BAT.get(), 1, 1, 1)
    );
    public static void placements() {
        SpawnPlacements.register(EntityInit.PUMPKIN_BAT.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, SpookyBat::checkSpookyBatSpawnRules);
        SpawnPlacements.register(EntityInit.CREEPER_BAT.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, MonsterBat::checkMonsterBatSpawnRules);
        SpawnPlacements.register(EntityInit.WITCH_BAT.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, SpookyBat::checkSpookyBatSpawnRules);
        SpawnPlacements.register(EntityInit.ALEX_BAT.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, SpookyBat::checkSpookyBatSpawnRules);
        SpawnPlacements.register(EntityInit.HEROBRINE_BAT.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, SpookyBat::checkSpookyBatSpawnRules);
        SpawnPlacements.register(EntityInit.STEVE_BAT.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, SpookyBat::checkSpookyBatSpawnRules);
        SpawnPlacements.register(EntityInit.SKELETON_BAT.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, MonsterBat::checkMonsterBatSpawnRules);
        SpawnPlacements.register(EntityInit.WITHER_SKELETON_BAT.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, SpookyBat::checkSpookyBatSpawnRules);
        SpawnPlacements.register(EntityInit.UNDEAD_BAT.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, SpookyBat::checkSpookyBatSpawnRules);
        SpawnPlacements.register(EntityInit.PLAYER_BAT.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, SpookyBat::checkSpookyBatSpawnRules);
        SpawnPlacements.register(EntityInit.SCULK_BAT.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, SpookyBat::checkSpookyBatSpawnRules);
        SpawnPlacements.register(EntityInit.EXPERIENCE_ORB_BAT.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, SpookyBat::checkSpookyBatSpawnRules);
        SpawnPlacements.register(EntityInit.SLIME_BAT.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, SpookyBat::checkSlimeSpawnRules);
        SpawnPlacements.register(EntityInit.SHULKER_BAT.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, SpookyBat::checkSpookyBatSpawnRules);
    }
}
