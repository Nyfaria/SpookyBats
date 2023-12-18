package com.nyfaria.batsgalore.block;

import com.nyfaria.batsgalore.block.entity.SpookyPedestalBlockEntity;
import com.nyfaria.batsgalore.init.BlockInit;
import com.nyfaria.batsgalore.init.ItemInit;
import com.nyfaria.batsgalore.item.CoreItem;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class SpookyPedestal extends BaseEntityBlock {
    public SpookyPedestal(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(BlockStateProperties.LIT, false));
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult blockHitResult) {
        if (!level.isClientSide) {
            if (hand == InteractionHand.MAIN_HAND && player.getMainHandItem().getItem() instanceof CoreItem item) {
                if (level.getBlockState(pos.below()).is(item.getPedestalBlock())) {
                    if (level.getBlockEntity(pos) instanceof SpookyPedestalBlockEntity be && be.getCore() == null) {
                        be.setCore(item);
                        be.checkActivate();
                        be.updateBlock();
                        level.setBlockAndUpdate(pos, state.setValue(BlockStateProperties.LIT, true));
                        if (!player.getAbilities().instabuild)
                            player.getMainHandItem().shrink(1);
                    }
                }
            }
        }
        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    @Override
    public RenderShape getRenderShape(BlockState $$0) {
        return RenderShape.MODEL;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BlockStateProperties.LIT);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new SpookyPedestalBlockEntity(blockPos, blockState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        return !level.isClientSide ? createTickerHelper(blockEntityType, BlockInit.SPOOKY_PEDESTAL_BLOCK_ENTITY.get(), (world, pos, pState, be)->be.tick(world,pos,pState,be)) : null;
    }

}
