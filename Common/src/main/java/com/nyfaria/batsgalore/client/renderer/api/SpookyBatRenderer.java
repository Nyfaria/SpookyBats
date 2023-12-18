package com.nyfaria.batsgalore.client.renderer.api;

import com.mojang.blaze3d.vertex.PoseStack;
import com.nyfaria.batsgalore.client.model.SpookyBatModel;
import com.nyfaria.batsgalore.entity.api.SpookyBat;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

import java.util.HashMap;
import java.util.Map;

public class SpookyBatRenderer<T extends SpookyBat> extends MobRenderer<T, SpookyBatModel<T>> {
    private static final Map<ResourceLocation, ResourceLocation> BAT_LOCATIONS = new HashMap<>();
    private final float scale;

    public SpookyBatRenderer(EntityRendererProvider.Context p_173929_, SpookyBatModel<T> model) {
        this(p_173929_, model, 0.35F);
    }

    public SpookyBatRenderer(EntityRendererProvider.Context p_173929_, SpookyBatModel<T> model, float scale) {
        super(p_173929_, model, 0.25F * (scale / 0.35f));
        this.addLayer(new ItemInHandLayer<>(this, p_173929_.getItemInHandRenderer()));
        this.scale = scale;
    }


    /**
     * Returns the location of an entity's texture.
     */
    public ResourceLocation getTextureLocation(T pEntity) {
        return BAT_LOCATIONS.computeIfAbsent(BuiltInRegistries.ENTITY_TYPE.getKey(pEntity.getType()), (resourceLocation) -> new ResourceLocation(resourceLocation.getNamespace(), "textures/entity/" + resourceLocation.getPath() + ".png"));
    }

    protected void scale(T pLivingEntity, PoseStack pMatrixStack, float pPartialTickTime) {
        pMatrixStack.scale(scale, scale, scale);
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
