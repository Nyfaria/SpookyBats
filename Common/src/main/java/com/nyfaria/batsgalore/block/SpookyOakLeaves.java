package com.nyfaria.batsgalore.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.DustColorTransitionOptions;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.Vec3;

import java.util.OptionalInt;

public class SpookyOakLeaves extends LeavesBlock {
    public static final IntegerProperty DISTANCE = IntegerProperty.create("long_distance", 1, 20);
    private static ParticleOptions OPTIONS = new DustColorTransitionOptions(Vec3.fromRGB24(15557120).toVector3f(),Vec3.fromRGB24(0).toVector3f(), 1.0F);
    private final int decayDistance;
    public SpookyOakLeaves(Properties $$0, int decayDistance) {
        super($$0);
        this.registerDefaultState(this.stateDefinition.any().setValue(DISTANCE, decayDistance).setValue(PERSISTENT, false).setValue(WATERLOGGED, false));
        this.decayDistance = decayDistance;
    }
    /**
     * @return whether this block needs random ticking.
     */
    public boolean isRandomlyTicking(BlockState pState) {
        return pState.getValue(DISTANCE) == decayDistance && !pState.getValue(PERSISTENT);
    }
    protected boolean decaying(BlockState pState) {
        return !pState.getValue(PERSISTENT) && pState.getValue(DISTANCE) == decayDistance;
    }
    public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
        if (pState.getValue(WATERLOGGED)) {
            pLevel.scheduleTick(pCurrentPos, Fluids.WATER, Fluids.WATER.getTickDelay(pLevel));
        }

        int i = getDistanceAt(pFacingState) + 1;
        if (i != 1 || pState.getValue(DISTANCE) != i) {
            pLevel.scheduleTick(pCurrentPos, this, 1);
        }

        return pState;
    }
    private static BlockState updateDistance(BlockState pState, LevelAccessor pLevel, BlockPos pPos) {
        int i = 7;
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

        for(Direction direction : Direction.values()) {
            blockpos$mutableblockpos.setWithOffset(pPos, direction);
            i = Math.min(i, getDistanceAt(pLevel.getBlockState(blockpos$mutableblockpos)) + 1);
            if (i == 1) {
                break;
            }
        }

        return pState.setValue(DISTANCE, Integer.valueOf(i));
    }
    private static int getDistanceAt(BlockState pNeighbor) {
        return getOptionalDistanceAt(pNeighbor).orElse(7);
    }
    public static OptionalInt getOptionalDistanceAt(BlockState $$0) {
        if ($$0.is(BlockTags.LOGS)) {
            return OptionalInt.of(0);
        } else {
            return $$0.hasProperty(DISTANCE) ? OptionalInt.of((Integer)$$0.getValue(DISTANCE)) : OptionalInt.empty();
        }
    }
    public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        pLevel.setBlock(pPos, updateDistance(pState, pLevel, pPos), 3);
    }
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(LeavesBlock.DISTANCE,DISTANCE, PERSISTENT, WATERLOGGED);
    }
    public void animateTick(BlockState state, Level level, BlockPos blockPos, RandomSource randomSource) {
//        super.animateTick(state, level, blockPos, randomSource);
//        if (randomSource.nextInt(10) == 0) {
//            BlockPos blockpos = blockPos.below();
//            BlockState blockstate = level.getBlockState(blockpos);
//            if (!isFaceFull(blockstate.getCollisionShape(level, blockpos), Direction.UP)) {
////                ParticleUtils.spawnParticleBelow(level, blockPos, randomSource, OPTIONS);
//                double d0 = (double)blockPos.getX() + randomSource.nextDouble();
//                double d1 = (double)blockPos.getY() - 0.05D;
//                double d2 = (double)blockPos.getZ() + randomSource.nextDouble();
//                level.addParticle(OPTIONS, d0, d1, d2, 0.0D, -0.1D, 0.0D);
//            }
//        }
    }
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        FluidState fluidstate = pContext.getLevel().getFluidState(pContext.getClickedPos());
        BlockState blockstate = this.defaultBlockState().setValue(PERSISTENT, Boolean.valueOf(true)).setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER));
        return updateDistance(blockstate, pContext.getLevel(), pContext.getClickedPos());
    }
}
