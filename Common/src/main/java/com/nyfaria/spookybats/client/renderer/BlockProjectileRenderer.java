package com.nyfaria.spookybats.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.nyfaria.spookybats.entity.BlockProjectile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class BlockProjectileRenderer extends EntityRenderer<BlockProjectile> {
    public BlockProjectileRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public void render(BlockProjectile blockProjectile, float yaw, float partialTick, PoseStack stack, MultiBufferSource bufferSource, int light) {
        stack.pushPose();
        stack.translate(-0.5, 0, -0.5);
        Minecraft.getInstance().getBlockRenderer().renderSingleBlock(blockProjectile.getBlockState(), stack, bufferSource, light, OverlayTexture.NO_OVERLAY);
        stack.popPose();
    }

    @Override
    public ResourceLocation getTextureLocation(BlockProjectile blockProjectile) {
        return null;
    }
}
