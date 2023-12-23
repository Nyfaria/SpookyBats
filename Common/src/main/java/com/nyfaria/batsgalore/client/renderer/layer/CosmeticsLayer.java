package com.nyfaria.batsgalore.client.renderer.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.nyfaria.batsgalore.Constants;
import com.nyfaria.batsgalore.client.model.christmas.ElfHatModel;
import com.nyfaria.batsgalore.client.model.christmas.ReindeerNoseModel;
import com.nyfaria.batsgalore.init.ItemInit;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class CosmeticsLayer<T extends LivingEntity, M extends PlayerModel<T>> extends RenderLayer<T, M> {
    private static final ResourceLocation ELF_HAT_LOCATION = new ResourceLocation(Constants.MODID,"textures/cosmetic/elf_hat.png");
    private static final ResourceLocation REINDEER_NOSE_LOCATION = new ResourceLocation(Constants.MODID,"textures/cosmetic/reindeer_nose.png");

    ElfHatModel<T> elfHatModel;
    ReindeerNoseModel<T> reindeerNoseModel;
    public CosmeticsLayer(RenderLayerParent<T, M> parent, EntityModelSet pModelSet) {
        super(parent);
        this.elfHatModel = new ElfHatModel<>(pModelSet.bakeLayer(ElfHatModel.LAYER_LOCATION));
        this.reindeerNoseModel = new ReindeerNoseModel<>(pModelSet.bakeLayer(ReindeerNoseModel.LAYER_LOCATION));
    }

    @Override
    public void render(PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight, T pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTicks, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        ItemStack itemstack = pLivingEntity.getItemBySlot(EquipmentSlot.HEAD);
        if (shouldRenderElfHat(itemstack, pLivingEntity)) {
            ResourceLocation resourcelocation = getElfHatLocation(itemstack, pLivingEntity);
            pMatrixStack.pushPose();
            this.getParentModel().getHead().translateAndRotate(pMatrixStack);
            VertexConsumer vertexconsumer = ItemRenderer.getArmorFoilBuffer(pBuffer, RenderType.armorCutoutNoCull(resourcelocation), false, itemstack.hasFoil());
            this.elfHatModel.renderToBuffer(pMatrixStack, vertexconsumer, pPackedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
            pMatrixStack.popPose();
        }
        if (shouldRendeReindeerNose(itemstack, pLivingEntity)) {
            ResourceLocation resourcelocation = getReindeerNoseTexture(itemstack, pLivingEntity);
            pMatrixStack.pushPose();
            this.getParentModel().getHead().translateAndRotate(pMatrixStack);
            VertexConsumer vertexconsumer = ItemRenderer.getArmorFoilBuffer(pBuffer, RenderType.eyes(resourcelocation), false, itemstack.hasFoil());
            this.reindeerNoseModel.renderToBuffer(pMatrixStack, vertexconsumer, pPackedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
            pMatrixStack.popPose();
        }
    }

    @Override
    public M getParentModel() {
        return super.getParentModel();
    }

    public boolean shouldRenderElfHat(ItemStack stack, T entity) {
        return stack.getItem() == ItemInit.ELF_HAT.get();
    }
    public boolean shouldRendeReindeerNose(ItemStack stack, T entity) {
        return stack.getItem() == ItemInit.REINDEER_NOSE.get();
    }
    public ResourceLocation getReindeerNoseTexture(ItemStack stack, T entity) {
        return REINDEER_NOSE_LOCATION;
    }
    public ResourceLocation getElfHatLocation(ItemStack stack, T entity) {
        return ELF_HAT_LOCATION;
    }
}
