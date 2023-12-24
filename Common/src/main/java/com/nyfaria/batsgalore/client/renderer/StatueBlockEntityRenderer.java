package com.nyfaria.batsgalore.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.nyfaria.batsgalore.block.entity.StatueBlockEntity;
import com.nyfaria.batsgalore.client.model.StatueLayers;
import com.nyfaria.batsgalore.client.model.StatueModel;
import com.nyfaria.batsgalore.init.BlockInit;
import it.unimi.dsi.fastutil.Pair;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;

import java.util.Map;

public class StatueBlockEntityRenderer implements BlockEntityRenderer<StatueBlockEntity> {
    private final Map<Block, Pair<ModelLayerLocation, ResourceLocation>> modelMap = Map.of(
            BlockInit.STATUE_OF_TURMOIL.get(), Pair.of(StatueLayers.STATUE_OF_TURMOIL_LAYER_LOCATION, StatueLayers.STATUE_OF_TURMOIL_TEXTURE)
    );
    private StatueModel model;
    private BlockEntityRendererProvider.Context context;

    public StatueBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        super();
        this.context = context;
    }

    @Override
    public void render(StatueBlockEntity statueBlockEntity, float pPartialTick, PoseStack poseStack, MultiBufferSource multiBufferSource, int pPackedLight, int pPackedOverlay) {
        Block block = statueBlockEntity.getBlockState().getBlock();
        if (model == null) {
            model = new StatueModel(context.bakeLayer(modelMap.get(block).left()));
        }
        poseStack.pushPose();
        int blockLight = LevelRenderer.getLightColor(Minecraft.getInstance().level, statueBlockEntity.getBlockPos());
        VertexConsumer vertexConsumer = multiBufferSource.getBuffer(RenderType.entityCutout(getTextureLocation(block)));
        poseStack.translate(0.5, 1.5, 0.5);
        poseStack.scale(1, -1, -1);
        poseStack.mulPose(Axis.YP.rotationDegrees(statueBlockEntity.getBlockState().getValue(HorizontalDirectionalBlock.FACING).toYRot()));
        model.renderToBuffer(poseStack, vertexConsumer, blockLight, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
        poseStack.popPose();
    }

    private ResourceLocation getTextureLocation(Block block) {
        return modelMap.get(block).right();
    }
}
