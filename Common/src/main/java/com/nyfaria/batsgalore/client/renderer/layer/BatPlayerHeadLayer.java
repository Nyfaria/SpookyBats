package com.nyfaria.batsgalore.client.renderer.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.nyfaria.batsgalore.client.model.HatBatModel;
import com.nyfaria.batsgalore.client.model.SpookyBatModel;
import com.nyfaria.batsgalore.entity.api.SpookyBat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

import java.util.Optional;

public class BatPlayerHeadLayer extends RenderLayer<SpookyBat, SpookyBatModel<SpookyBat>> {
    private Optional<ResourceLocation> resourceLocation = Optional.empty();
    private final HatBatModel<SpookyBat> model;

    public BatPlayerHeadLayer(RenderLayerParent<SpookyBat, SpookyBatModel<SpookyBat>> pRenderer, EntityModelSet pModelSet) {
        super(pRenderer);
        this.model = new HatBatModel<>(pModelSet.bakeLayer(HatBatModel.LAYER_LOCATION));
    }

    @Override
    public void render(PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight, SpookyBat pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTicks, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        HatBatModel<SpookyBat> entitymodel = this.model();
        entitymodel.hideForPlayer();
        entitymodel.prepareMobModel(pLivingEntity, pLimbSwing, pLimbSwingAmount, pPartialTicks);
        this.getParentModel().copyPropertiesTo(entitymodel);
        VertexConsumer vertexconsumer = pBuffer.getBuffer(RenderType.entityCutout(this.getTextureLocation(pLivingEntity)));
        entitymodel.setupAnim(pLivingEntity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch);
        entitymodel.renderToBuffer(pMatrixStack, vertexconsumer, pPackedLight, OverlayTexture.NO_OVERLAY, 0.5F, 0.5F, 0.5F, 1.0F);
    }

    protected ResourceLocation getTextureLocation(SpookyBat bop) {
        resourceLocation.ifPresentOrElse(
                (r) -> {
                },
                () -> resourceLocation = Optional.of(Minecraft.getInstance().player.getSkinTextureLocation()));
        return resourceLocation.get();
    }

    protected HatBatModel<SpookyBat> model() {
        return this.model;
    }
}