package com.nyfaria.spookybats.item;

import com.nyfaria.spookybats.entity.WitchsBroom;
import com.nyfaria.spookybats.init.EntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;

public class WitchsBroomItem extends Item {
    public WitchsBroomItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext $$0) {
        BlockPos blockPos = $$0.getClickedPos().above();
        WitchsBroom witchsBroom = EntityInit.WITCHS_BROOM.get().create($$0.getLevel());
        witchsBroom.setPos(blockPos.getX() + 0.5, blockPos.getY(), blockPos.getZ() + 0.5);
        witchsBroom.setOwnerUUID($$0.getPlayer().getUUID());
        witchsBroom.setTame(true);
        $$0.getLevel().addFreshEntity(witchsBroom);
        $$0.getItemInHand().shrink(1);
        return InteractionResult.CONSUME;
    }
}
