package com.nyfaria.batsgalore.client.model.christmas;// Made with Blockbench 4.9.1
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.nyfaria.batsgalore.Constants;
import com.nyfaria.batsgalore.client.model.ModBatModel;
import com.nyfaria.batsgalore.entity.api.ModBat;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;

public class ReindeerBatModel<T extends ModBat> extends ModBatModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Constants.MODID, "reindeerbat"), "main");

	public ReindeerBatModel(ModelPart root) {
		super(root);
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-3.25F, -6.5F, -3.0F, 7.0F, 7.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(19, 14).addBox(-0.75F, -2.75F, -4.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.25F, 9.5F, 2.5F));

		PartDefinition left_ear = head.addOrReplaceChild("left_ear", CubeListBuilder.create(), PartPose.offset(-0.75F, -7.5F, -3.5F));

		PartDefinition right_ear = head.addOrReplaceChild("right_ear", CubeListBuilder.create(), PartPose.offset(1.25F, -7.5F, -3.5F));

		PartDefinition antler1 = head.addOrReplaceChild("antler1", CubeListBuilder.create().texOffs(0, 35).addBox(-0.5F, -24.0F, 0.0F, 0.0F, 8.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(43, 36).addBox(-7.5F, -24.0F, 0.0F, 0.0F, 8.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(19, 43).addBox(-7.5F, -24.0F, 0.0F, 7.0F, 8.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(43, 3).addBox(-7.5F, -16.0F, 0.0F, 7.0F, 0.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(43, 16).addBox(-7.5F, -24.0F, 7.0F, 7.0F, 8.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(26, 16).addBox(-2.5F, -16.0F, 2.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.25F, 8.5F, -3.0F));

		PartDefinition antler2 = head.addOrReplaceChild("antler2", CubeListBuilder.create().texOffs(0, 35).mirror().addBox(0.5F, -24.0F, 0.0F, 0.0F, 8.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(43, 36).mirror().addBox(7.5F, -24.0F, 0.0F, 0.0F, 8.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(19, 43).mirror().addBox(0.5F, -24.0F, 0.0F, 7.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(43, 3).mirror().addBox(0.5F, -16.0F, 0.0F, 7.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(43, 16).mirror().addBox(0.5F, -24.0F, 7.0F, 7.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(26, 16).mirror().addBox(0.5F, -16.0F, 2.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.25F, 8.5F, -3.0F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(21, 24).addBox(-3.0F, -4.0F, -2.5F, 6.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 14.0F, 3.0F));

		PartDefinition leftWing = body.addOrReplaceChild("leftWing", CubeListBuilder.create().texOffs(28, 0).mirror().addBox(0.0F, -6.5F, 0.0F, 9.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(3.0F, -0.5F, 0.0F));

		PartDefinition leftWingTip = leftWing.addOrReplaceChild("leftWingTip", CubeListBuilder.create().texOffs(0, 29).mirror().addBox(0.0F, -6.5F, 0.0F, 8.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(9.0F, 0.0F, 0.0F));

		PartDefinition rightWing = body.addOrReplaceChild("rightWing", CubeListBuilder.create().texOffs(28, 0).addBox(-9.0F, -6.5F, 0.0F, 9.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -0.5F, 0.0F));

		PartDefinition rightWingTip = rightWing.addOrReplaceChild("rightWingTip", CubeListBuilder.create().texOffs(0, 29).addBox(-8.0F, -6.5F, 0.0F, 8.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-9.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

}