package com.nyfaria.spookybats.client.renderer.api;

import com.mojang.blaze3d.vertex.PoseStack;
import com.nyfaria.spookybats.client.model.SpookyBatModel;
import com.nyfaria.spookybats.entity.SpookyBat;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

import java.util.HashMap;
import java.util.Map;

public class SpookyBatRenderer<T extends SpookyBat> extends MobRenderer<T, SpookyBatModel<T>> {
    private static final Map<ResourceLocation,ResourceLocation> BAT_LOCATIONS = new HashMap<>();

    public SpookyBatRenderer(EntityRendererProvider.Context p_173929_, SpookyBatModel<T> model) {
        super(p_173929_, model, 0.25F);
    }


    /**
     * Returns the location of an entity's texture.
     */
    public ResourceLocation getTextureLocation(T pEntity) {
        return BAT_LOCATIONS.computeIfAbsent(BuiltInRegistries.ENTITY_TYPE.getKey(pEntity.getType()), (resourceLocation) -> new ResourceLocation(resourceLocation.getNamespace(), "textures/entity/" + resourceLocation.getPath() +".png"));
    }

    protected void scale(T pLivingEntity, PoseStack pMatrixStack, float pPartialTickTime) {
        pMatrixStack.scale(0.35F, 0.35F, 0.35F);
    }

    protected void setupRotations(T pEntityLiving, PoseStack pMatrixStack, float pAgeInTicks, float pRotationYaw, float pPartialTicks) {
        if (pEntityLiving.isResting()) {
            pMatrixStack.translate(0.0F, -0.1F, 0.0F);
        } else {
            pMatrixStack.translate(0.0F, Mth.cos(pAgeInTicks * 0.3F) * 0.1F, 0.0F);
        }

        super.setupRotations(pEntityLiving, pMatrixStack, pAgeInTicks, pRotationYaw, pPartialTicks);
    }
}
