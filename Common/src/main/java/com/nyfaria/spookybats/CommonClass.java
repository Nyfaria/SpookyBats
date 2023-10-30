package com.nyfaria.spookybats;

import com.nyfaria.spookybats.client.model.EvilBatModel;
import com.nyfaria.spookybats.client.model.ExperienceOrbBatModel;
import com.nyfaria.spookybats.client.model.GhostBatModel;
import com.nyfaria.spookybats.client.model.HatBatModel;
import com.nyfaria.spookybats.client.model.PumpkinBatModel;
import com.nyfaria.spookybats.client.model.SculkBatModel;
import com.nyfaria.spookybats.client.model.SkeletonBatModel;
import com.nyfaria.spookybats.client.model.SlimeBatModel;
import com.nyfaria.spookybats.client.model.UndeadBatModel;
import com.nyfaria.spookybats.client.model.WitchBatModel;
import com.nyfaria.spookybats.client.renderer.CreeperBatRenderer;
import com.nyfaria.spookybats.client.renderer.EmissiveBatRenderer;
import com.nyfaria.spookybats.client.renderer.GhostBatRenderer;
import com.nyfaria.spookybats.client.renderer.PlayerBatRenderer;
import com.nyfaria.spookybats.client.renderer.PumpkinBatRenderer;
import com.nyfaria.spookybats.client.renderer.SculkBatRenderer;
import com.nyfaria.spookybats.client.renderer.SlimeBatRenderer;
import com.nyfaria.spookybats.client.renderer.UndeadBatRenderer;
import com.nyfaria.spookybats.client.renderer.VoidBatRenderer;
import com.nyfaria.spookybats.client.renderer.WitchsBroomRenderer;
import com.nyfaria.spookybats.client.renderer.api.SpookyBatRenderer;
import com.nyfaria.spookybats.entity.MonsterBat;
import com.nyfaria.spookybats.entity.api.SpookyBat;
import com.nyfaria.spookybats.init.BlockInit;
import com.nyfaria.spookybats.init.EntityInit;
import com.nyfaria.spookybats.init.ItemInit;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;

import java.util.Map;
import java.util.function.Supplier;

public class CommonClass {



    public static void init() {
        ItemInit.loadClass();
        BlockInit.loadClass();
        EntityInit.loadClass();
    }

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

    }

}