package com.nyfaria.batsgalore.block.entity;

import com.nyfaria.batsgalore.init.BlockInit;
import com.nyfaria.batsgalore.init.EntityInit;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SpookyPedestalBlockEntity extends BlockEntity {
    private Item core;
    private ItemStack stack = ItemStack.EMPTY;
    private boolean active = false;
    private int activeStartTick = 0;
    private Direction pillarDirection = Direction.UP;
    private static Map<Direction,Integer> directionStartTickMap = Map.of(
            Direction.NORTH, 100,
            Direction.SOUTH, 200,
            Direction.EAST, 300,
            Direction.WEST, 400
    );

    public SpookyPedestalBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(BlockInit.SPOOKY_PEDESTAL_BLOCK_ENTITY.get(), pWorldPosition, pBlockState);
    }

    public int getCampFireTick(Direction direction) {
        return directionStartTickMap.get(direction);
    }
    public void saveData(CompoundTag pTag) {
        if (core != null) {
            pTag.putString("core", BuiltInRegistries.ITEM.getKey(core).toString());
        }
        pTag.putBoolean("active", active);
        if (pillarDirection != null)
            pTag.putString("pillarDirection", pillarDirection.getName());
        if(!stack.isEmpty()) {
            CompoundTag stackTag = new CompoundTag();
            stack.save(stackTag);
            pTag.put("stack", stackTag);
        }
    }


    public void loadData(CompoundTag pTag) {
        if (pTag.contains("core")) {
            core = BuiltInRegistries.ITEM.get(new ResourceLocation(pTag.getString("core")));
        }
        active = pTag.getBoolean("active");
        if (pTag.contains("pillarDirection")) {
            pillarDirection = Direction.byName(pTag.getString("pillarDirection"));
        }
        if(pTag.contains("Count"))
            stack = ItemStack.of(pTag.getCompound("stack"));
    }

    public Direction getPillarDirection() {
        return pillarDirection;
    }

    public void setPillarDirection(Direction pillarDirection) {
        this.pillarDirection = pillarDirection;
    }

    @Override
    public void load(CompoundTag tag) {
        loadData(tag);
        super.load(tag);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        saveData(pTag);
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag tag = new CompoundTag();
        saveData(tag);
        return tag;
    }


    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    public void updateBlock() {
        BlockState blockState = level.getBlockState(this.getBlockPos());
        this.level.sendBlockUpdated(this.getBlockPos(), blockState, blockState, 3);
        this.setChanged();
    }

    public void setCore(Item item) {
        this.core = item;
        if(item!=null) {
            stack = new ItemStack(item);
        } else {
            stack = ItemStack.EMPTY;
        }
        updateBlock();
    }

    public Item getCore() {
        return this.core;
    }

    public ItemStack getCoreStack() {
        if (stack.isEmpty())
            stack = new ItemStack(core);
        return stack;
    }

    public void checkActivate() {
        boolean east = level.getBlockState(worldPosition.east(26)).is(BlockInit.SPOOKY_PEDESTAL.get());
        boolean west = level.getBlockState(worldPosition.west(26)).is(BlockInit.SPOOKY_PEDESTAL.get());
        boolean north = level.getBlockState(worldPosition.north(26)).is(BlockInit.SPOOKY_PEDESTAL.get());
        boolean south = level.getBlockState(worldPosition.south(26)).is(BlockInit.SPOOKY_PEDESTAL.get());
        pillarDirection = east ? Direction.WEST : west ? Direction.EAST : north ? Direction.SOUTH : south ? Direction.NORTH : Direction.UP;
        int numberActive = 1;
        List<SpookyPedestalBlockEntity> belist = new ArrayList<>();
        switch (pillarDirection) {
            case EAST:
                if (level.getBlockEntity(worldPosition.west(26)) instanceof SpookyPedestalBlockEntity be && be.getCore() != null) {
                    be.setPillarDirection(Direction.WEST);
                    belist.add(be);
                    numberActive++;
                }
                if (level.getBlockEntity(worldPosition.west(13).north(13)) instanceof SpookyPedestalBlockEntity be && be.getCore() != null) {
                    be.setPillarDirection(Direction.NORTH);
                    belist.add(be);
                    numberActive++;
                }
                if (level.getBlockEntity(worldPosition.west(13).south(13)) instanceof SpookyPedestalBlockEntity be && be.getCore() != null) {
                    be.setPillarDirection(Direction.SOUTH);
                    belist.add(be);
                    numberActive++;
                }
                break;
            case WEST:
                if (level.getBlockEntity(worldPosition.east(26)) instanceof SpookyPedestalBlockEntity be && be.getCore() != null) {
                    be.setPillarDirection(Direction.EAST);
                    belist.add(be);
                    numberActive++;
                }
                if (level.getBlockEntity(worldPosition.east(13).north(13)) instanceof SpookyPedestalBlockEntity be && be.getCore() != null) {
                    be.setPillarDirection(Direction.NORTH);
                    belist.add(be);
                    numberActive++;
                }
                if (level.getBlockEntity(worldPosition.east(13).south(13)) instanceof SpookyPedestalBlockEntity be && be.getCore() != null) {
                    be.setPillarDirection(Direction.SOUTH);
                    belist.add(be);
                    numberActive++;
                }
                break;
            case NORTH:
                if (level.getBlockEntity(worldPosition.south(26)) instanceof SpookyPedestalBlockEntity be && be.getCore() != null) {
                    be.setPillarDirection(Direction.SOUTH);
                    belist.add(be);
                    numberActive++;
                }
                if (level.getBlockEntity(worldPosition.south(13).east(13)) instanceof SpookyPedestalBlockEntity be && be.getCore() != null) {
                    be.setPillarDirection(Direction.EAST);
                    belist.add(be);
                    numberActive++;
                }
                if (level.getBlockEntity(worldPosition.south(13).west(13)) instanceof SpookyPedestalBlockEntity be && be.getCore() != null) {
                    be.setPillarDirection(Direction.WEST);
                    belist.add(be);
                    numberActive++;
                }
                break;
            case SOUTH:
                if (level.getBlockEntity(worldPosition.north(26)) instanceof SpookyPedestalBlockEntity be && be.getCore() != null) {
                    be.setPillarDirection(Direction.NORTH);
                    belist.add(be);
                    numberActive++;
                }
                if (level.getBlockEntity(worldPosition.north(13).east(13)) instanceof SpookyPedestalBlockEntity be && be.getCore() != null) {
                    be.setPillarDirection(Direction.EAST);
                    belist.add(be);
                    numberActive++;
                }
                if (level.getBlockEntity(worldPosition.north(13).west(13)) instanceof SpookyPedestalBlockEntity be && be.getCore() != null) {
                    be.setPillarDirection(Direction.WEST);
                    belist.add(be);
                    numberActive++;
                }
                break;
            default:
                break;
        }
        active = numberActive >= 4;
        belist.forEach(be -> be.setActive(active));
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
        updateBlock();
    }

    public void tick(Level level, BlockPos blockPos, BlockState state, SpookyPedestalBlockEntity blockEntity) {
        if (active) {
            if (activeStartTick == blockEntity.getCampFireTick(blockEntity.getPillarDirection())) {
                level.setBlockAndUpdate(blockPos.relative(pillarDirection.getOpposite(), 12).below(3), Blocks.SOUL_CAMPFIRE.defaultBlockState());
            }
            if(activeStartTick == 500){
                active = false;
                activeStartTick = 0;
                if(blockEntity.getPillarDirection() == Direction.NORTH) {
                    BlockPos thePos = blockPos.relative(pillarDirection.getOpposite(), 13).below(1);
                    ((ServerLevel) level).sendParticles(ParticleTypes.EXPLOSION_EMITTER, thePos.getX(), thePos.getY(), thePos.getZ(), 100, 1, 1, 1, 1);
                    EntityInit.WINGED_TURMOIL.get().spawn((ServerLevel) level, blockPos.relative(pillarDirection.getOpposite(), 13).below(1), MobSpawnType.EVENT);
                }
                setCore(null);
            }
            activeStartTick++;
        }
    }
    public static final AABB INFINITE_EXTENT_AABB = new net.minecraft.world.phys.AABB(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);

    public AABB getRenderBoundingBox() {
        return INFINITE_EXTENT_AABB;
    }
}
