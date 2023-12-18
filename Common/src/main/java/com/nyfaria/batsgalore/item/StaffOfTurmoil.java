package com.nyfaria.batsgalore.item;

import com.nyfaria.batsgalore.entity.BlockProjectile;
import com.nyfaria.batsgalore.init.TagInit;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class StaffOfTurmoil extends Item {
    public StaffOfTurmoil(Properties $$0) {
        super($$0);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand hand) {
        if (hand == InteractionHand.MAIN_HAND) {
            ItemStack stack = pPlayer.getItemInHand(hand);
            if (stack.getItem() instanceof StaffOfTurmoil) {
                if (stack.getOrCreateTag().contains("stored_block")) {
                    double range = 50;
                    Vec3 eyepos = pPlayer.getEyePosition(0);
                    Vec3 look = pPlayer.getViewVector(0);
                    Vec3 traceVec = eyepos.add(look.x * range, look.y * range, look.z * range);
                    AABB box = new AABB(eyepos, traceVec);
                    BlockPos pos = NbtUtils.readBlockPos(stack.getTag().getCompound("stored_block"));
                    EntityHitResult trace = rayTraceEntities(pLevel, pPlayer, eyepos, traceVec, box, e -> e instanceof LivingEntity);
                    if (trace != null) {
                        if (trace.getType() == HitResult.Type.ENTITY) {

                            BlockProjectile projectile = new BlockProjectile(pLevel, pPlayer, (LivingEntity) trace.getEntity());
                            projectile.setPos(pos.getX(), pos.getY() + 1, pos.getZ());
                            projectile.setBlockState(pLevel.getBlockState(pos));
                            pLevel.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
                            pLevel.addFreshEntity(projectile);
                            stack.getTag().remove("stored_block");

                        }
                    }

                }
            }
        }
        return InteractionResultHolder.sidedSuccess(pPlayer.getItemInHand(hand), pLevel.isClientSide);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        if (context.getHand() == InteractionHand.MAIN_HAND) {
            ItemStack stack = context.getItemInHand();
            if (stack.getItem() instanceof StaffOfTurmoil) {
                if (context.getLevel().getBlockEntity(context.getClickedPos()) == null) {
                    if(!context.getLevel().getBlockState(context.getClickedPos()).is(TagInit.SOT_EXCLUSION)) {
                        if (!stack.getOrCreateTag().contains("stored_block") || !context.getLevel().isLoaded(NbtUtils.readBlockPos(stack.getTag().getCompound("stored_block")))) {
                            stack.getTag().put("stored_block", NbtUtils.writeBlockPos(context.getClickedPos()));
                        }
                    }
                }
            }
        }
        return InteractionResult.sidedSuccess(context.getLevel().isClientSide);
    }

    public static EntityHitResult rayTraceEntities(Level worldIn, Entity projectile, Vec3 startVec, Vec3 endVec, AABB boundingBox, Predicate<Entity> filter) {
        double d0 = Double.MAX_VALUE;
        Entity entity = null;

        for (Entity entity1 : worldIn.getEntities(projectile, boundingBox, filter)) {
            if (entity1.isSpectator()) {
                continue;
            }
            AABB axisalignedbb = entity1.getBoundingBox();
            if (axisalignedbb.getSize() < 0.3) {
                axisalignedbb = axisalignedbb.inflate(0.3);
            }
            Optional<Vec3> optional = axisalignedbb.clip(startVec, endVec);
            if (optional.isPresent()) {
                double d1 = startVec.distanceToSqr(optional.get());
                if (d1 < d0) {
                    entity = entity1;
                    d0 = d1;
                }
            }
        }

        return entity == null ? null : new EntityHitResult(entity);

    }

    @Override
    public void appendHoverText(ItemStack $$0, @Nullable Level $$1, List<Component> tooltip, TooltipFlag $$3) {
        tooltip.add(Component.literal("Right click almost any block and then right click an entity!").withStyle(ChatFormatting.AQUA));
        super.appendHoverText($$0, $$1, tooltip, $$3);
    }
}
