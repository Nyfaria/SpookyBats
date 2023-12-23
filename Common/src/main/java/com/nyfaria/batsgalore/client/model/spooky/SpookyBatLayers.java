package com.nyfaria.batsgalore.client.model.spooky;

import com.nyfaria.batsgalore.Constants;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;

public class SpookyBatLayers {
    public static final ModelLayerLocation EVIL_BAT_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Constants.MODID, "evil_bat"), "main");
    public static final ModelLayerLocation EXPERIENCE_BAT_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Constants.MODID, "experience_bat"), "main");
    public static final ModelLayerLocation UNDEAD_BAT_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Constants.MODID, "undead_bat"), "main");
    public static final ModelLayerLocation WINGED_TURMOIL_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Constants.MODID, "winged_turmoil"), "main");
    public static final ModelLayerLocation WITCH_BAT_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Constants.MODID, "witch_bat"), "main");
    public static final ModelLayerLocation SLIME_BAT_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "slimebat"), "main");
    public static final ModelLayerLocation SKELETON_BAT_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Constants.MODID, "skelton_bat"), "main");
    public static final ModelLayerLocation SHULKER_BAT_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Constants.MODID, "shulker_bat"), "main");
    public static final ModelLayerLocation SCULK_BAT_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Constants.MODID, "sculk_bat"), "main");
    public static final ModelLayerLocation PUMPKIN_BAT_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Constants.MODID, "pumpkin_bat"), "main");
    public static final ModelLayerLocation BAT_OVERLAY_LOCATION = new ModelLayerLocation(new ResourceLocation(Constants.MODID, "bat_overlay"), "overlay");
    public static final ModelLayerLocation GHOST_BAT_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Constants.MODID, "ghost_bat"), "main");
    public static final ModelLayerLocation HAT_BAT_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Constants.MODID, "hat_bat"), "main");

    public static LayerDefinition createEvilBatLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-3.25F, -6.5F, -3.0F, 7.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.25F, 9.5F, 2.5F));

        PartDefinition right_ear = head.addOrReplaceChild("right_ear", CubeListBuilder.create().texOffs(21, 0).addBox(1.5F, -24.0F, 1.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.25F, 14.5F, -2.5F));

        PartDefinition left_ear = head.addOrReplaceChild("left_ear", CubeListBuilder.create().texOffs(21, 0).mirror().addBox(-3.5F, -2.0F, 2.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-0.75F, -7.5F, -3.5F));

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 14).addBox(-3.0F, -4.0F, -2.5F, 6.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 14.0F, 3.0F));

        PartDefinition leftWing = body.addOrReplaceChild("leftWing", CubeListBuilder.create().texOffs(22, 14).addBox(0.0F, -6.5F, 0.0F, 9.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, -0.5F, 0.0F));

        PartDefinition leftWingTip = leftWing.addOrReplaceChild("leftWingTip", CubeListBuilder.create().texOffs(0, 27).addBox(0.0F, -6.5F, 0.0F, 8.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(9.0F, 0.0F, 0.0F));

        PartDefinition rightWing = body.addOrReplaceChild("rightWing", CubeListBuilder.create().texOffs(22, 14).mirror().addBox(-9.0F, -6.5F, 0.0F, 9.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-3.0F, -0.5F, 0.0F));

        PartDefinition rightWingTip = rightWing.addOrReplaceChild("rightWingTip", CubeListBuilder.create().texOffs(0, 27).mirror().addBox(-8.0F, -6.5F, 0.0F, 8.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-9.0F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    public static LayerDefinition createExperienceBatLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-3.25F, 0.5F, -3.0F, 7.0F, 7.0F, 7.0F, new CubeDeformation(0.0F))
            .texOffs(0, 27).addBox(-3.25F, 0.5F, -3.0F, 7.0F, 7.0F, 7.0F, new CubeDeformation(-0.2F)), PartPose.offset(-0.25F, 9.5F, 2.5F));

        PartDefinition right_ear = head.addOrReplaceChild("right_ear", CubeListBuilder.create(), PartPose.offset(0.25F, 14.5F, -2.5F));

        PartDefinition left_ear = head.addOrReplaceChild("left_ear", CubeListBuilder.create(), PartPose.offset(-0.75F, -7.5F, -3.5F));

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 14.0F, 3.0F));

        PartDefinition leftWing = body.addOrReplaceChild("leftWing", CubeListBuilder.create().texOffs(0, 14).addBox(0.0F, -6.5F, 0.0F, 9.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(6.0F, -0.5F, 0.0F));

        PartDefinition leftWingTip = leftWing.addOrReplaceChild("leftWingTip", CubeListBuilder.create().texOffs(18, 14).addBox(0.0F, -6.5F, 0.0F, 8.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(9.0F, 0.0F, 0.0F));

        PartDefinition rightWing = body.addOrReplaceChild("rightWing", CubeListBuilder.create().texOffs(0, 14).mirror().addBox(-9.0F, -6.5F, 0.0F, 9.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-6.0F, -0.5F, 0.0F));

        PartDefinition rightWingTip = rightWing.addOrReplaceChild("rightWingTip", CubeListBuilder.create().texOffs(18, 14).mirror().addBox(-8.0F, -6.5F, 0.0F, 8.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-9.0F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    public static LayerDefinition createUndeadBatLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(-0.25F, 9.5F, 2.5F));

        PartDefinition right_ear = head.addOrReplaceChild("right_ear", CubeListBuilder.create(), PartPose.offset(0.25F, 14.5F, -2.5F));

        PartDefinition left_ear = head.addOrReplaceChild("left_ear", CubeListBuilder.create(), PartPose.offset(-0.75F, -7.5F, -3.5F));

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-3.5F, -7.0F, -3.5F, 7.0F, 6.0F, 7.0F, new CubeDeformation(0.0F))
        .texOffs(21, 0).addBox(-3.5F, -10.0F, -0.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
        .texOffs(21, 0).mirror().addBox(0.5F, -10.0F, -0.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
        .texOffs(21, 0).addBox(-3.5F, -10.0F, -0.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 14.0F, 3.0F));

        PartDefinition leftWing = body.addOrReplaceChild("leftWing", CubeListBuilder.create().texOffs(0, 13).addBox(0.0F, -6.5F, 0.0F, 9.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(3.75F, -4.5F, 0.0F));

        PartDefinition leftWingTip = leftWing.addOrReplaceChild("leftWingTip", CubeListBuilder.create().texOffs(18, 13).addBox(0.0F, -6.5F, 0.0F, 8.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(9.0F, 0.0F, 0.0F));

        PartDefinition rightWing = body.addOrReplaceChild("rightWing", CubeListBuilder.create().texOffs(0, 13).mirror().addBox(-9.0F, -6.5F, 0.0F, 9.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-3.75F, -4.5F, 0.0F));

        PartDefinition rightWingTip = rightWing.addOrReplaceChild("rightWingTip", CubeListBuilder.create().texOffs(18, 13).mirror().addBox(-8.0F, -6.5F, 0.0F, 8.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-9.0F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    public static LayerDefinition createWingedTurmoilLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-5.43F, -11.12F, -3.83F, 11.0F, 11.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.25F, 5.5F, 2.5F));

        PartDefinition right_ear = head.addOrReplaceChild("right_ear", CubeListBuilder.create().texOffs(33, 0).addBox(-2.0F, -4.5F, -1.0F, 4.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(4.9F, -9.93F, -0.17F));

        PartDefinition left_ear = head.addOrReplaceChild("left_ear", CubeListBuilder.create().texOffs(33, 0).mirror().addBox(-2.0F, -4.5F, -1.0F, 4.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-4.4F, -9.93F, -0.17F));

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 42).addBox(-4.01F, -6.48F, -2.335F, 8.0F, 13.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 11.5F, 3.0F));

        PartDefinition leftWing = body.addOrReplaceChild("leftWing", CubeListBuilder.create().texOffs(28, 22).addBox(-0.01F, -10.0F, 0.025F, 14.0F, 20.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, -1.5F, 0.7F));

        PartDefinition leftWingTip = leftWing.addOrReplaceChild("leftWingTip", CubeListBuilder.create().texOffs(0, 22).addBox(-0.01F, -10.0F, 0.025F, 14.0F, 20.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(14.0F, 0.0F, 0.0F));

        PartDefinition rightWing = body.addOrReplaceChild("rightWing", CubeListBuilder.create().texOffs(28, 22).mirror().addBox(-13.99F, -9.99F, 0.025F, 14.0F, 20.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-4.0F, -1.5F, 0.7F));

        PartDefinition rightWingTip = rightWing.addOrReplaceChild("rightWingTip", CubeListBuilder.create().texOffs(0, 22).mirror().addBox(-13.99F, -9.99F, 0.025F, 14.0F, 20.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-14.0F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    public static LayerDefinition createWitchBatLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(21, 19).addBox(-3.25F, -6.5F, -3.0F, 7.0F, 7.0F, 7.0F, new CubeDeformation(0.0F))
            .texOffs(0, 12).addBox(-3.25F, -6.5F, -3.0F, 7.0F, 7.0F, 7.0F, new CubeDeformation(0.4F)), PartPose.offset(-0.25F, 9.5F, 2.5F));

        PartDefinition right_ear = head.addOrReplaceChild("right_ear", CubeListBuilder.create(), PartPose.offset(0.25F, 14.5F, -2.5F));

        PartDefinition left_ear = head.addOrReplaceChild("left_ear", CubeListBuilder.create(), PartPose.offset(-0.75F, -7.5F, -3.5F));

        PartDefinition headwear = head.addOrReplaceChild("headwear", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 10.0F, 3.0F, 10.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.75F, -17.55F, -7.5F));

        PartDefinition hat2 = headwear.addOrReplaceChild("hat2", CubeListBuilder.create().texOffs(33, 5).addBox(0.2618F, 9.8259F, 3.5191F, 7.0F, 4.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.75F, -3.725F, 1.0F, 0.0F, 0.0F, 0.0262F));

        PartDefinition hat3 = hat2.addOrReplaceChild("hat3", CubeListBuilder.create().texOffs(45, 29).addBox(-2.0F, -5.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5257F, 10.6272F, 7.524F, -0.3491F, 0.0F, 0.0F));

        PartDefinition hat4 = hat3.addOrReplaceChild("hat4", CubeListBuilder.create(), PartPose.offsetAndRotation(-1.0257F, -15.3772F, -4.524F, -0.2094F, 0.0F, 0.1047F));

        PartDefinition nose = head.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, 9.0F, -2.6F, 2.0F, 4.0F, 2.0F, new CubeDeformation(-0.1F)), PartPose.offset(0.25F, -11.5F, -2.7F));

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(28, 33).addBox(-3.0F, -4.0F, -2.5F, 6.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 14.0F, 3.0F));

        PartDefinition cube_r1 = body.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 26).addBox(0.0F, 0.0F, -0.001F, 6.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -4.0F, 2.501F, 0.2182F, 0.0F, 0.0F));

        PartDefinition cube_r2 = body.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 26).addBox(-1.0F, -1.0F, -3.0F, 2.0F, 2.0F, 12.0F, new CubeDeformation(0.0F))
            .texOffs(42, 16).addBox(-2.0F, -2.0F, 8.925F, 4.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.95F, -4.55F, -0.3796F, 0.0F, 0.0F));

        PartDefinition leftWing = body.addOrReplaceChild("leftWing", CubeListBuilder.create().texOffs(0, 40).addBox(0.0F, -6.5F, 0.0F, 9.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, -0.5F, 0.0F));

        PartDefinition leftWingTip = leftWing.addOrReplaceChild("leftWingTip", CubeListBuilder.create().texOffs(18, 46).addBox(0.0F, -6.5F, 0.0F, 8.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(9.0F, 0.0F, 0.0F));

        PartDefinition rightWing = body.addOrReplaceChild("rightWing", CubeListBuilder.create().texOffs(0, 40).mirror().addBox(-9.0F, -6.5F, 0.0F, 9.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-3.0F, -0.5F, 0.0F));

        PartDefinition rightWingTip = rightWing.addOrReplaceChild("rightWingTip", CubeListBuilder.create().texOffs(18, 46).mirror().addBox(-8.0F, -6.5F, 0.0F, 8.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-9.0F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    public static LayerDefinition createSlimBatLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-3.25F, -6.5F, -3.0F, 7.0F, 7.0F, 7.0F, new CubeDeformation(0.8F))
            .texOffs(21, 0).addBox(-1.25F, -4.5F, -1.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.6F)), PartPose.offset(-0.25F, 9.5F, 2.5F));

        PartDefinition right_ear = head.addOrReplaceChild("right_ear", CubeListBuilder.create(), PartPose.offset(0.25F, 14.5F, -2.5F));

        PartDefinition left_ear = head.addOrReplaceChild("left_ear", CubeListBuilder.create(), PartPose.offset(-0.75F, -7.5F, -3.5F));

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 14).addBox(-3.0F, -4.0F, -2.5F, 6.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 14.0F, 3.0F));

        PartDefinition rightWing = body.addOrReplaceChild("rightWing", CubeListBuilder.create().texOffs(22, 14).addBox(-9.0F, -6.5F, 0.0F, 9.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -0.5F, 0.0F));

        PartDefinition rightWingTip = rightWing.addOrReplaceChild("rightWingTip", CubeListBuilder.create().texOffs(0, 27).addBox(-8.0F, -6.5F, 0.0F, 8.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-9.0F, 0.0F, 0.0F));

        PartDefinition leftWing = body.addOrReplaceChild("leftWing", CubeListBuilder.create().texOffs(22, 14).mirror().addBox(0.0F, -6.5F, 0.0F, 9.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(3.0F, -0.5F, 0.0F));

        PartDefinition leftWingTip = leftWing.addOrReplaceChild("leftWingTip", CubeListBuilder.create().texOffs(0, 27).mirror().addBox(0.0F, -6.5F, 0.0F, 8.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(9.0F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    public static LayerDefinition createSkeletonBatLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-3.75F, -7.5F, -3.5F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.25F, 9.5F, 2.5F));

        PartDefinition right_ear = head.addOrReplaceChild("right_ear", CubeListBuilder.create().texOffs(28, 1).addBox(2.0F, -25.0F, 1.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.25F, 14.5F, -2.5F));

        PartDefinition left_ear = head.addOrReplaceChild("left_ear", CubeListBuilder.create().texOffs(28, 1).addBox(-4.0F, -3.0F, 2.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.75F, -7.5F, -3.5F));

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 16).addBox(-3.0F, -4.0F, -2.5F, 6.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 14.0F, 3.0F));

        PartDefinition leftWing = body.addOrReplaceChild("leftWing", CubeListBuilder.create().texOffs(22, 17).addBox(0.0F, -6.5F, 0.0F, 9.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, -0.5F, 0.0F));

        PartDefinition leftWingTip = leftWing.addOrReplaceChild("leftWingTip", CubeListBuilder.create().texOffs(0, 30).addBox(0.0F, -6.5F, 0.0F, 8.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(9.0F, 0.0F, 0.0F));

        PartDefinition rightWing = body.addOrReplaceChild("rightWing", CubeListBuilder.create().texOffs(22, 17).mirror().addBox(-9.0F, -6.5F, 0.0F, 9.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-3.0F, -0.5F, 0.0F));

        PartDefinition rightWingTip = rightWing.addOrReplaceChild("rightWingTip", CubeListBuilder.create().texOffs(0, 30).mirror().addBox(-8.0F, -6.5F, 0.0F, 8.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-9.0F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
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

    public static LayerDefinition createSculkBatLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-3.75F, -7.5F, -3.5F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.25F, 10.5F, 0.5F));

        PartDefinition right_ear = head.addOrReplaceChild("right_ear", CubeListBuilder.create().texOffs(24, 0).addBox(-12.0F, -25.0F, 3.0F, 8.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.25F, 14.5F, -2.5F));

        PartDefinition left_ear = head.addOrReplaceChild("left_ear", CubeListBuilder.create().texOffs(24, 0).mirror().addBox(4.0F, -25.0F, 3.0F, 8.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.25F, 14.5F, -2.5F));

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 16).addBox(-3.0F, -4.0F, -2.5F, 6.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 14.0F, 1.0F));

        PartDefinition rightWing = body.addOrReplaceChild("rightWing", CubeListBuilder.create().texOffs(22, 16).addBox(-9.0F, -6.5F, 0.0F, 9.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -0.5F, 0.0F));

        PartDefinition rightWingTip = rightWing.addOrReplaceChild("rightWingTip", CubeListBuilder.create().texOffs(0, 29).addBox(-8.0F, -6.5F, 0.0F, 8.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-9.0F, 0.0F, 0.0F));

        PartDefinition leftWing = body.addOrReplaceChild("leftWing", CubeListBuilder.create().texOffs(22, 16).mirror().addBox(0.0F, -6.5F, 0.0F, 9.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(3.0F, -0.5F, 0.0F));

        PartDefinition leftWingTip = leftWing.addOrReplaceChild("leftWingTip", CubeListBuilder.create().texOffs(0, 29).mirror().addBox(0.0F, -6.5F, 0.0F, 8.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(9.0F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    public static LayerDefinition createPumpkinBatLayer(CubeDeformation pCubeDeformation) {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.75F, -7.5F, -3.5F, 10.0F, 8.0F, 8.0F, pCubeDeformation), PartPose.offset(-0.25F, 9.5F, 2.5F));

        PartDefinition right_ear = head.addOrReplaceChild("right_ear", CubeListBuilder.create().texOffs(28, 0).addBox(3.0F, -25.0F, 1.0F, 3.0F, 4.0F, 2.0F, pCubeDeformation), PartPose.offset(0.25F, 14.5F, -2.5F));

        PartDefinition left_ear = head.addOrReplaceChild("left_ear", CubeListBuilder.create().texOffs(28, 0).mirror().addBox(-5.0F, -3.0F, 2.0F, 3.0F, 4.0F, 2.0F, pCubeDeformation).mirror(false), PartPose.offset(-0.75F, -7.5F, -3.5F));

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 16).addBox(-3.0F, -4.0F, -2.5F, 6.0F, 8.0F, 5.0F, pCubeDeformation), PartPose.offset(0.0F, 14.0F, 3.0F));

        PartDefinition leftWing = body.addOrReplaceChild("leftWing", CubeListBuilder.create().texOffs(22, 16).addBox(0.0F, -6.5F, 0.0F, 9.0F, 13.0F, 0.0F, pCubeDeformation), PartPose.offset(3.0F, -0.5F, 0.0F));

        PartDefinition leftWingTip = leftWing.addOrReplaceChild("leftWingTip", CubeListBuilder.create().texOffs(0, 29).addBox(0.0F, -6.5F, 0.0F, 8.0F, 13.0F, 0.0F, pCubeDeformation), PartPose.offset(9.0F, 0.0F, 0.0F));

        PartDefinition rightWing = body.addOrReplaceChild("rightWing", CubeListBuilder.create().texOffs(22, 16).mirror().addBox(-9.0F, -6.5F, 0.0F, 9.0F, 13.0F, 0.0F, pCubeDeformation).mirror(false), PartPose.offset(-3.0F, -0.5F, 0.0F));

        PartDefinition rightWingTip = rightWing.addOrReplaceChild("rightWingTip", CubeListBuilder.create().texOffs(0, 29).mirror().addBox(-8.0F, -6.5F, 0.0F, 8.0F, 13.0F, 0.0F, pCubeDeformation).mirror(false), PartPose.offset(-9.0F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    public static LayerDefinition createGhostBatLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-3.25F, -6.5F, -3.0F, 7.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.25F, 9.5F, 2.5F));

        PartDefinition left_ear = head.addOrReplaceChild("left_ear", CubeListBuilder.create().texOffs(16, 27).addBox(-3.5F, -2.0F, 2.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.75F, -7.5F, -3.5F));

        PartDefinition right_ear = head.addOrReplaceChild("right_ear", CubeListBuilder.create().texOffs(16, 27).mirror().addBox(0.5F, -2.0F, 2.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(1.25F, -7.5F, -3.5F));

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 14).addBox(-3.0F, -4.0F, -2.5F, 6.0F, 8.0F, 5.0F, new CubeDeformation(0.0F))
            .texOffs(21, 0).addBox(-2.0F, -3.0F, -1.5F, 4.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 14.0F, 4.0F, 0.3927F, 0.0F, 0.0F));

        PartDefinition rightWing = body.addOrReplaceChild("rightWing", CubeListBuilder.create().texOffs(22, 14).addBox(-9.0F, -7.25F, 0.0F, 9.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -0.5F, 0.0F, 0.0F, 0.5672F, 0.0F));

        PartDefinition rightWingTip = rightWing.addOrReplaceChild("rightWingTip", CubeListBuilder.create().texOffs(0, 27).addBox(-8.0F, -7.25F, 0.0F, 8.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.0F, 0.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition leftWing = body.addOrReplaceChild("leftWing", CubeListBuilder.create().texOffs(22, 14).mirror().addBox(0.0F, -7.25F, 0.0F, 9.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, -0.5F, 0.0F, 0.0F, -0.3927F, 0.0F));

        PartDefinition leftWingTip = leftWing.addOrReplaceChild("leftWingTip", CubeListBuilder.create().texOffs(0, 27).mirror().addBox(0.0F, -7.25F, 0.0F, 8.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(9.0F, 0.0F, 0.0F, 0.0F, 0.5672F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    public static LayerDefinition createHatBatLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-3.75F, -7.5F, -3.5F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
        .texOffs(32, 0).addBox(-3.75F, -7.5F, -3.5F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.2F)), PartPose.offset(-0.25F, 9.5F, 2.5F));

        PartDefinition right_ear = head.addOrReplaceChild("right_ear", CubeListBuilder.create().texOffs(28, 0).addBox(2.0F, -25.0F, 1.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.25F, 14.5F, -2.5F));

        PartDefinition left_ear = head.addOrReplaceChild("left_ear", CubeListBuilder.create().texOffs(28, 0).addBox(-4.0F, -3.0F, 2.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.75F, -7.5F, -3.5F));

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 16).addBox(-3.0F, -4.0F, -2.5F, 6.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 14.0F, 3.0F));

        PartDefinition leftWing = body.addOrReplaceChild("leftWing", CubeListBuilder.create().texOffs(22, 16).addBox(0.0F, -6.5F, 0.0F, 9.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, -0.5F, 0.0F));

        PartDefinition leftWingTip = leftWing.addOrReplaceChild("leftWingTip", CubeListBuilder.create().texOffs(0, 29).addBox(0.0F, -6.5F, 0.0F, 8.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(9.0F, 0.0F, 0.0F));

        PartDefinition rightWing = body.addOrReplaceChild("rightWing", CubeListBuilder.create().texOffs(22, 16).mirror().addBox(-9.0F, -6.5F, 0.0F, 9.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-3.0F, -0.5F, 0.0F));

        PartDefinition rightWingTip = rightWing.addOrReplaceChild("rightWingTip", CubeListBuilder.create().texOffs(0, 29).mirror().addBox(-8.0F, -6.5F, 0.0F, 8.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-9.0F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }
}
