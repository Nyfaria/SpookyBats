package com.nyfaria.batsgalore.client.model.christmas;

import com.nyfaria.batsgalore.Constants;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;

public class ChristmasBatLayers {
    public static final ModelLayerLocation CANDY_CANE_BAT_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Constants.MODID, "candy_cane_bat"), "main");
    public static final ModelLayerLocation REINDEER_BAT_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Constants.MODID, "reindeer_bat"), "main");
    public static final ModelLayerLocation ELF_BAT_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Constants.MODID, "elf_bat"), "main");

    public static LayerDefinition createCandyCaneBatLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 14.0F, 3.0F));

        PartDefinition cube_r1 = body.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 19).addBox(-4.5F, -4.75F, -1.5F, 3.0F, 9.0F, 3.0F, new CubeDeformation(0.0F))
        .texOffs(0, 0).addBox(1.5F, -4.75F, -1.5F, 3.0F, 16.0F, 3.0F, new CubeDeformation(0.0F))
        .texOffs(25, 23).addBox(-1.5F, -4.75F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -3.25F, 0.5F, 0.0F, 0.0F, 0.1745F));

        PartDefinition leftWing = body.addOrReplaceChild("leftWing", CubeListBuilder.create().texOffs(12, 0).addBox(0.0F, -6.5F, 0.0F, 9.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, -0.5F, 0.0F));

        PartDefinition leftWingTip = leftWing.addOrReplaceChild("leftWingTip", CubeListBuilder.create().texOffs(12, 13).addBox(0.0F, -6.5F, 0.0F, 8.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(9.0F, 0.0F, 0.0F));

        PartDefinition rightWing = body.addOrReplaceChild("rightWing", CubeListBuilder.create().texOffs(12, 0).mirror().addBox(-9.0F, -6.5F, 0.0F, 9.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-7.0F, -0.5F, 0.0F));

        PartDefinition rightWingTip = rightWing.addOrReplaceChild("rightWingTip", CubeListBuilder.create().texOffs(12, 13).mirror().addBox(-8.0F, -6.5F, 0.0F, 8.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-9.0F, 0.0F, 0.0F));

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    public static LayerDefinition createReindeerBatLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-3.25F, -6.5F, -3.0F, 7.0F, 7.0F, 7.0F, new CubeDeformation(0.0F))
        .texOffs(19, 14).addBox(-0.75F, -2.75F, -4.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.25F, 9.5F, 2.5F));

        PartDefinition left_ear = head.addOrReplaceChild("left_ear", CubeListBuilder.create(), PartPose.offset(-0.75F, -7.5F, -3.5F));

        PartDefinition right_ear = head.addOrReplaceChild("right_ear", CubeListBuilder.create(), PartPose.offset(1.25F, -7.5F, -3.5F));

        PartDefinition antler1 = head.addOrReplaceChild("antler1", CubeListBuilder.create().texOffs(0, 35).addBox(-0.5F, -24.0F, 0.0F, 0.01F, 8.0F, 7.0F, new CubeDeformation(0.0F))
        .texOffs(43, 36).addBox(-7.5F, -24.0F, 0.0F, 0.01F, 8.0F, 7.0F, new CubeDeformation(0.0F))
        .texOffs(19, 43).addBox(-7.5F, -24.0F, 0.0F, 7.0F, 8.0F, 0.01F, new CubeDeformation(0.0F))
        .texOffs(43, 3).addBox(-7.5F, -16.0F, 0.0F, 7.0F, 0.01F, 7.0F, new CubeDeformation(0.0F))
        .texOffs(43, 16).addBox(-7.5F, -24.0F, 7.0F, 7.0F, 8.0F, 0.01F, new CubeDeformation(0.0F))
        .texOffs(26, 16).addBox(-2.5F, -16.0F, 2.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.25F, 8.5F, -3.0F));

        PartDefinition antler2 = head.addOrReplaceChild("antler2", CubeListBuilder.create().texOffs(0, 35).mirror().addBox(0.5F, -24.0F, 0.0F, 0.01F, 8.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false)
        .texOffs(43, 36).mirror().addBox(7.5F, -24.0F, 0.0F, 0.0F, 8.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false)
        .texOffs(19, 43).mirror().addBox(0.5F, -24.0F, 0.0F, 7.0F, 8.0F, 0.01F, new CubeDeformation(0.0F)).mirror(false)
        .texOffs(43, 3).mirror().addBox(0.5F, -16.0F, 0.0F, 7.0F, 0.01F, 7.0F, new CubeDeformation(0.0F)).mirror(false)
        .texOffs(43, 16).mirror().addBox(0.5F, -24.0F, 7.0F, 7.0F, 8.0F, 0.01F, new CubeDeformation(0.0F)).mirror(false)
        .texOffs(26, 16).mirror().addBox(0.5F, -16.0F, 2.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.25F, 8.5F, -3.0F));

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(21, 24).addBox(-3.0F, -4.0F, -2.5F, 6.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 14.0F, 3.0F));

        PartDefinition leftWing = body.addOrReplaceChild("leftWing", CubeListBuilder.create().texOffs(28, 0).mirror().addBox(0.0F, -6.5F, 0.0F, 9.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(3.0F, -0.5F, 0.0F));

        PartDefinition leftWingTip = leftWing.addOrReplaceChild("leftWingTip", CubeListBuilder.create().texOffs(0, 29).mirror().addBox(0.0F, -6.5F, 0.0F, 8.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(9.0F, 0.0F, 0.0F));

        PartDefinition rightWing = body.addOrReplaceChild("rightWing", CubeListBuilder.create().texOffs(28, 0).addBox(-9.0F, -6.5F, 0.0F, 9.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -0.5F, 0.0F));

        PartDefinition rightWingTip = rightWing.addOrReplaceChild("rightWingTip", CubeListBuilder.create().texOffs(0, 29).addBox(-8.0F, -6.5F, 0.0F, 8.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-9.0F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }
    public static LayerDefinition createElfBatLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 11).addBox(-3.25F, -6.5F, -3.0F, 7.0F, 7.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-4.25F, -8.5F, -4.0F, 9.0F, 2.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.25F, 9.5F, 2.5F));

        PartDefinition left_ear = head.addOrReplaceChild("left_ear", CubeListBuilder.create(), PartPose.offset(-0.75F, -7.5F, -3.5F));

        PartDefinition cube_r1 = left_ear.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(40, 13).addBox(-5.0F, -2.5F, -1.5F, 2.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -0.25F, 4.5F, -0.3927F, 0.0F, 0.0F));

        PartDefinition right_ear = head.addOrReplaceChild("right_ear", CubeListBuilder.create(), PartPose.offset(1.25F, -7.5F, -3.5F));

        PartDefinition cube_r2 = right_ear.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(40, 13).mirror().addBox(3.0F, -2.5F, -1.5F, 2.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.0F, -0.25F, 4.5F, -0.3927F, 0.0F, 0.0F));

        PartDefinition bone = head.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offsetAndRotation(0.25F, -13.271F, 3.5434F, -0.1484F, 0.0F, 0.0F));

        PartDefinition cube_r3 = bone.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -3.4145F, 1.472F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 38).addBox(-1.5F, -2.4302F, -0.7524F, 3.0F, 5.0F, 3.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.3526F, 0.0F, 0.0F));

        PartDefinition cube_r4 = bone.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 25).addBox(-2.5F, -3.5F, -2.5F, 5.0F, 7.0F, 5.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.271F, -2.2934F, -0.3054F, 0.0F, 0.0F));

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(23, 20).addBox(-3.0F, -4.0F, -2.5F, 6.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 14.0F, 3.0F));

        PartDefinition rightWing = body.addOrReplaceChild("rightWing", CubeListBuilder.create().texOffs(22, 33).addBox(-9.0F, -6.5F, 0.0F, 9.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -0.5F, 0.0F));

        PartDefinition rightWingTip = rightWing.addOrReplaceChild("rightWingTip", CubeListBuilder.create().texOffs(36, 0).addBox(-8.0F, -6.5F, 0.0F, 8.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-9.0F, 0.0F, 0.0F));

        PartDefinition leftWing = body.addOrReplaceChild("leftWing", CubeListBuilder.create().texOffs(22, 33).mirror().addBox(0.0F, -6.5F, 0.0F, 9.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(3.0F, -0.5F, 0.0F));

        PartDefinition leftWingTip = leftWing.addOrReplaceChild("leftWingTip", CubeListBuilder.create().texOffs(36, 0).mirror().addBox(0.0F, -6.5F, 0.0F, 8.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(9.0F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }
}
