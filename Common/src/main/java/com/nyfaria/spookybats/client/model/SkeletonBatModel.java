package com.nyfaria.spookybats.client.model;// Made with Blockbench 4.8.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.nyfaria.spookybats.Constants;
import com.nyfaria.spookybats.client.model.SpookyBatModel;
import com.nyfaria.spookybats.entity.SpookyBat;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;

public class SkeletonBatModel<T extends SpookyBat> extends SpookyBatModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Constants.MODID, "skelton_bat"), "main");

	public SkeletonBatModel(ModelPart root) {
		super(root);
	}

	public static LayerDefinition createBodyLayer() {
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
}