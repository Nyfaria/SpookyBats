package com.nyfaria.batsgalore.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.nyfaria.batsgalore.Constants;
import com.nyfaria.batsgalore.block.entity.SpookyPedestalBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.entity.EndCrystalRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

public class SpookyPedestalRenderer implements BlockEntityRenderer<SpookyPedestalBlockEntity> {

    public static final ResourceLocation SOUL_FIRE_BEAM = new ResourceLocation(Constants.MODID,"textures/soul_fire_beam.png");
    private static final RenderType BEAM = RenderType.entitySmoothCutout(SOUL_FIRE_BEAM);

    @Override
    public void render(SpookyPedestalBlockEntity spookyPedestalBlockEntity, float pPartialTicks, PoseStack poseStack, MultiBufferSource multiBufferSource, int light, int overlay) {
        if (spookyPedestalBlockEntity.getCore() != null) {
            poseStack.pushPose();
            float wobble = (float) (Math.sin(Minecraft.getInstance().level.getGameTime() % 100 / 100f * Math.PI * 4) * 0.1);
            poseStack.translate(0.5, 1.2 + wobble , 0.5);
            poseStack.mulPose(Axis.YP.rotationDegrees((Minecraft.getInstance().level.getGameTime() % 360) * 6));
            Minecraft.getInstance().getItemRenderer().renderStatic(spookyPedestalBlockEntity.getCoreStack(), ItemDisplayContext.GROUND, light, overlay, poseStack, multiBufferSource, Minecraft.getInstance().level, 0);


            poseStack.popPose();
            if(spookyPedestalBlockEntity.isActive()) {
                poseStack.pushPose();
                BlockPos thePos = spookyPedestalBlockEntity.getBlockPos().relative(spookyPedestalBlockEntity.getPillarDirection().getOpposite(), 12).below(3);
                float f6 = (float) (thePos.getX() - spookyPedestalBlockEntity.getBlockPos().getX());
                float f8 = (float) (thePos.getY() - spookyPedestalBlockEntity.getBlockPos().getY());
                float f9 = (float) (thePos.getZ() - spookyPedestalBlockEntity.getBlockPos().getZ());
                poseStack.translate(0.5, -0.5, 0.5);
                renderCrystalBeams(f6, f8, f9, pPartialTicks, Minecraft.getInstance().player.tickCount, poseStack, multiBufferSource, light);
                poseStack.popPose();
            }
        }
    }

    public static void renderCrystalBeams(float pX, float pY, float pZ, float pPartialTick, int pTickCount, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        float f = Mth.sqrt(pX * pX + pZ * pZ);
        float f1 = Mth.sqrt(pX * pX + pY * pY + pZ * pZ);
        pPoseStack.pushPose();
        pPoseStack.translate(0.0F, 2.0F, 0.0F);
        pPoseStack.mulPose(Axis.YP.rotation((float)(-Math.atan2((double)pZ, (double)pX)) - ((float)Math.PI / 2F)));
        pPoseStack.mulPose(Axis.XP.rotation((float)(-Math.atan2((double)f, (double)pY)) - ((float)Math.PI / 2F)));
        VertexConsumer vertexconsumer = pBuffer.getBuffer(BEAM);
        float f2 = 0.0F - ((float)pTickCount + pPartialTick) * 0.01F;
        float f3 = Mth.sqrt(pX * pX + pY * pY + pZ * pZ) / 32.0F - ((float)pTickCount + pPartialTick) * 0.01F;
        int i = 8;
        float f4 = 0.0F;
        float f5 = 0.75F;
        float f6 = 0.0F;
        PoseStack.Pose posestack$pose = pPoseStack.last();
        Matrix4f matrix4f = posestack$pose.pose();
        Matrix3f matrix3f = posestack$pose.normal();

        for(int j = 1; j <= 8; ++j) {
            float f7 = Mth.sin((float)j * ((float)Math.PI * 2F) / 8.0F) * 0.75F;
            float f8 = Mth.cos((float)j * ((float)Math.PI * 2F) / 8.0F) * 0.75F;
            float f9 = (float)j / 8.0F;
            vertexconsumer.vertex(matrix4f, f4 * 0.2F, f5 * 0.2F, 0.0F).color(0, 0, 0, 255).uv(f6, f2).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(pPackedLight).normal(matrix3f, 0.0F, -1.0F, 0.0F).endVertex();
            vertexconsumer.vertex(matrix4f, f4, f5, f1).color(255, 255, 255, 255).uv(f6, f3).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(pPackedLight).normal(matrix3f, 0.0F, -1.0F, 0.0F).endVertex();
            vertexconsumer.vertex(matrix4f, f7, f8, f1).color(255, 255, 255, 255).uv(f9, f3).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(pPackedLight).normal(matrix3f, 0.0F, -1.0F, 0.0F).endVertex();
            vertexconsumer.vertex(matrix4f, f7 * 0.2F, f8 * 0.2F, 0.0F).color(0, 0, 0, 255).uv(f9, f2).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(pPackedLight).normal(matrix3f, 0.0F, -1.0F, 0.0F).endVertex();
            f4 = f7;
            f5 = f8;
            f6 = f9;
        }

        pPoseStack.popPose();
    }

    @Override
    public boolean shouldRenderOffScreen(SpookyPedestalBlockEntity be) {
        return true;
    }

    @Override
    public boolean shouldRender(SpookyPedestalBlockEntity $$0, Vec3 $$1) {
        return true;
    }
}
