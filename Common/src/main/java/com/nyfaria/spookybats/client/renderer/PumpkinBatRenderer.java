package com.nyfaria.spookybats.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.nyfaria.spookybats.client.model.PumpkinBat;
import com.nyfaria.spookybats.client.renderer.layer.PumpkinBatRenderLayer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ambient.Bat;

import java.util.HashMap;
import java.util.Map;

public class PumpkinBatRenderer extends MobRenderer<Bat, PumpkinBat<Bat>> {
    private static final Map<ResourceLocation,ResourceLocation> BAT_LOCATIONS = new HashMap<>();

    public PumpkinBatRenderer(EntityRendererProvider.Context p_173929_) {
        super(p_173929_, new PumpkinBat<>(p_173929_.bakeLayer(PumpkinBat.LAYER_LOCATION)), 0.25F);
        this.addLayer(new PumpkinBatRenderLayer<>(this));
    }

    /**
     * Returns the location of an entity's texture.
     */
    public ResourceLocation getTextureLocation(Bat pEntity) {
        return BAT_LOCATIONS.computeIfAbsent(BuiltInRegistries.ENTITY_TYPE.getKey(pEntity.getType()), (resourceLocation) -> new ResourceLocation(resourceLocation.getNamespace(), "textures/entity/" + resourceLocation.getPath() +".png"));
    }

    protected void scale(Bat pLivingEntity, PoseStack pMatrixStack, float pPartialTickTime) {
        pMatrixStack.scale(0.35F, 0.35F, 0.35F);
    }

    protected void setupRotations(Bat pEntityLiving, PoseStack pMatrixStack, float pAgeInTicks, float pRotationYaw, float pPartialTicks) {
        if (pEntityLiving.isResting()) {
            pMatrixStack.translate(0.0F, -0.1F, 0.0F);
        } else {
            pMatrixStack.translate(0.0F, Mth.cos(pAgeInTicks * 0.3F) * 0.1F, 0.0F);
        }

        super.setupRotations(pEntityLiving, pMatrixStack, pAgeInTicks, pRotationYaw, pPartialTicks);
    }
}
