package com.nyfaria.spookybats.client.model;

import com.nyfaria.spookybats.entity.ShulkerBat;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;

public class ShulkerBatModel<T extends ShulkerBat> extends SpookyBatModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "shulker_bat"), "main");
    protected final ModelPart lid;

    public ShulkerBatModel(ModelPart pRoot) {
        super(pRoot);
        this.lid = head.getChild("shulker").getChild("lid");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(-0.25F, 9.5F, 2.5F));

        PartDefinition right_ear = head.addOrReplaceChild("right_ear", CubeListBuilder.create(), PartPose.offset(0.25F, 14.5F, -2.5F));

        PartDefinition left_ear = head.addOrReplaceChild("left_ear", CubeListBuilder.create(), PartPose.offset(-0.75F, -7.5F, -3.5F));

        PartDefinition shulker = head.addOrReplaceChild("shulker", CubeListBuilder.create(), PartPose.offset(0.25F, 9.5F, 0.5F));

        PartDefinition lid = shulker.addOrReplaceChild("lid", CubeListBuilder.create().texOffs(0, 0).addBox(-6.08F, -12.74F, -5.14F, 12.0F, 9.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.78F, -0.78F));

        PartDefinition head2 = shulker.addOrReplaceChild("head2", CubeListBuilder.create().texOffs(36, 0).addBox(-2.59F, 0.7F, -1.81F, 5.18F, 5.18F, 5.18F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.1F, -0.78F));

        PartDefinition base = shulker.addOrReplaceChild("base", CubeListBuilder.create().texOffs(0, 21).addBox(-6.08F, -6.78F, -5.14F, 12.0F, 6.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.78F, -0.78F));

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 14.0F, 3.0F));

        PartDefinition leftWing = body.addOrReplaceChild("leftWing", CubeListBuilder.create().texOffs(18, 39).addBox(0.0F, -6.5F, 0.0F, 9.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, -0.5F, 0.0F));

        PartDefinition leftWingTip = leftWing.addOrReplaceChild("leftWingTip", CubeListBuilder.create().texOffs(48, 10).addBox(0.0F, -6.5F, 0.0F, 8.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(9.0F, 0.0F, 0.0F));

        PartDefinition rightWing = body.addOrReplaceChild("rightWing", CubeListBuilder.create().texOffs(0, 39).addBox(-9.0F, -6.5F, 0.0F, 9.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-8.0F, -0.5F, 0.0F));

        PartDefinition rightWingTip = rightWing.addOrReplaceChild("rightWingTip", CubeListBuilder.create().texOffs(36, 39).addBox(-8.0F, -6.5F, 0.0F, 8.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-9.0F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(ShulkerBat pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        super.setupAnim(pEntity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch);
        float progress = (float) pEntity.getOpenTicks() / pEntity.getMaxOpenTicks();
        this.lid.yRot = 270.0f * progress * 0.017453292F;
        this.lid.setPos(0.0F, 0.78F - progress * 0.5F * 16.0F, -0.78F);
    }
}