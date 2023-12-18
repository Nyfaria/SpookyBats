package com.nyfaria.batsgalore.client.model;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.nyfaria.batsgalore.Constants;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.resources.ResourceLocation;

public class SpookyOakHangingSignModel extends HangingSignRenderer.HangingSignModel {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Constants.MODID, "hanging_sign/spooky_oak"), "main2");
    private final ModelPart bone;

    public SpookyOakHangingSignModel(ModelPart root) {
        super(root);
        this.bone = root.getChild("bone");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild("board", CubeListBuilder.create().texOffs(0, 12).addBox(-7.0F, -14.0F, -1.0F, 14.0F, 10.0F, 2.0F).texOffs(0, 28).addBox(-7.0F, -4.0F, 0.0F, 14.0F, 4.0F, 0.0F)
                .texOffs(0, 24).addBox(-7.0F, -6.0F, -1.0F, 14.0F, 2.0F, 2.0F, new CubeDeformation(0.2F)), PartPose.offset(0.0F, 14.0F, 0.0F));
        partdefinition.addOrReplaceChild("plank", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -20.0F, -2.0F, 16.0F, 2.0F, 4.0F)
                , PartPose.offset(0.0F, 14.0F, 0.0F));
        PartDefinition partdefinition1 = partdefinition.addOrReplaceChild("normalChains", CubeListBuilder.create(), PartPose.ZERO);
        partdefinition1.addOrReplaceChild("chainL1", CubeListBuilder.create().texOffs(0, 6).addBox(-1.5F, -14.0F, 0.0F, 3.0F, 6.0F, 0.0F), PartPose.offsetAndRotation(-5.0F, 8.0F, 0.0F, 0.0F, -0.7854F, 0.0F));
        partdefinition1.addOrReplaceChild("chainL2", CubeListBuilder.create().texOffs(6, 6).addBox(-1.5F, -14.0F, 0.0F, 3.0F, 6.0F, 0.0F), PartPose.offsetAndRotation(-5.0F, 8.0F, 0.0F, 0.0F, 0.7854F, 0.0F));
        partdefinition1.addOrReplaceChild("chainR1", CubeListBuilder.create().texOffs(0, 6).addBox(-1.5F, -14.0F, 0.0F, 3.0F, 6.0F, 0.0F), PartPose.offsetAndRotation(5.0F, 8.0F, 0.0F, 0.0F, -0.7854F, 0.0F));
        partdefinition1.addOrReplaceChild("chainR2", CubeListBuilder.create().texOffs(6, 6).addBox(-1.5F, -14.0F, 0.0F, 3.0F, 6.0F, 0.0F), PartPose.offsetAndRotation(5.0F, 8.0F, 0.0F, 0.0F, 0.7854F, 0.0F));
        partdefinition.addOrReplaceChild("vChains", CubeListBuilder.create().texOffs(14, 6).addBox(-6.0F, -20.0F, 0.0F, 12.0F, 6.0F, 0.0F), PartPose.offset(0.0F, 14.0F, 0.0F));
        return LayerDefinition.create(meshdefinition, 64, 32);
    }


    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        bone.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}