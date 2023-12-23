package com.nyfaria.batsgalore.client.renderer.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.nyfaria.batsgalore.client.model.HatBatModel;
import com.nyfaria.batsgalore.client.model.ModBatModel;
import com.nyfaria.batsgalore.entity.api.ModBat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

import java.util.Optional;

public class BatPlayerHeadLayer extends RenderLayer<ModBat, ModBatModel<ModBat>> {
    private Optional<ResourceLocation> resourceLocation = Optional.empty();
    private final HatBatModel<ModBat> model;

    public BatPlayerHeadLayer(RenderLayerParent<ModBat, ModBatModel<ModBat>> pRenderer, EntityModelSet pModelSet) {
        super(pRenderer);
        this.model = new HatBatModel<>(pModelSet.bakeLayer(HatBatModel.LAYER_LOCATION));
    }

    @Override
    public void render(PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight, ModBat pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTicks, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        HatBatModel<ModBat> entitymodel = this.model();
        entitymodel.hideForPlayer();
        entitymodel.prepareMobModel(pLivingEntity, pLimbSwing, pLimbSwingAmount, pPartialTicks);
        this.getParentModel().copyPropertiesTo(entitymodel);
        VertexConsumer vertexconsumer = pBuffer.getBuffer(RenderType.entityCutout(this.getTextureLocation(pLivingEntity)));
        entitymodel.setupAnim(pLivingEntity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch);
        entitymodel.renderToBuffer(pMatrixStack, vertexconsumer, pPackedLight, OverlayTexture.NO_OVERLAY, 0.5F, 0.5F, 0.5F, 1.0F);
    }

    protected ResourceLocation getTextureLocation(ModBat bop) {
        resourceLocation.ifPresentOrElse(
                (r) -> {
                },
                () -> resourceLocation = Optional.of(Minecraft.getInstance().player.getSkinTextureLocation()));
        return resourceLocation.get();
    }

    protected HatBatModel<ModBat> model() {
        return this.model;
    }
}