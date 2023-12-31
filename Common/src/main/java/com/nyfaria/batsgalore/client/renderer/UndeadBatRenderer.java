package com.nyfaria.batsgalore.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.nyfaria.batsgalore.Constants;
import com.nyfaria.batsgalore.client.model.ModBatModel;
import com.nyfaria.batsgalore.client.renderer.api.ModBatRenderer;
import com.nyfaria.batsgalore.entity.api.ModBat;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class UndeadBatRenderer extends ModBatRenderer<ModBat> {
    public static final ResourceLocation FULL_HEALTH_TEXTURE = new ResourceLocation(Constants.MODID, "textures/entity/undead_bat_full.png");
    public static final ResourceLocation PARTLY_FADED_TEXTURE = new ResourceLocation(Constants.MODID, "textures/entity/undead_bat_part_faded.png");
    public static final ResourceLocation FULLY_FADED_TEXTURE = new ResourceLocation(Constants.MODID, "textures/entity/undead_bat_faded.png");

    public UndeadBatRenderer(EntityRendererProvider.Context context, ModBatModel<ModBat> model) {
        super(context, model);
    }

    @Override
    protected void scale(ModBat pLivingEntity, PoseStack pMatrixStack, float pPartialTickTime) {
        pMatrixStack.pushPose();
        pMatrixStack.scale(1.2f, 1.2f, 1.2f);
        pMatrixStack.popPose();
    }

    @Override
    public ResourceLocation getTextureLocation(ModBat bat) {
        return switch (Mth.floor((bat.getHealth()))) {
            case 6, 5 -> FULL_HEALTH_TEXTURE;
            case 4, 3, 2 -> PARTLY_FADED_TEXTURE;
            default -> FULLY_FADED_TEXTURE;
        };
    }
}
