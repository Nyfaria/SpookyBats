package com.nyfaria.batsgalore.client.model.random;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.nyfaria.batsgalore.Constants;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;

public class RandomBatLayers {
	public static final ModelLayerLocation BIGGEST_FAN_BAT_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Constants.MODID, "battemplate"), "main");


	public static LayerDefinition createBiggestFanBatLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-3.25F, -6.5F, -3.0F, 7.0F, 7.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(0, 50).addBox(-3.25F, -6.5F, -3.0F, 7.0F, 7.0F, 7.0F, new CubeDeformation(0.2F))
				.texOffs(19, 40).addBox(-3.25F, -6.5F, -3.0F, 7.0F, 7.0F, 7.0F, new CubeDeformation(0.5F))
				.texOffs(29, 58).addBox(-5.0F, -7.25F, -0.75F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(29, 58).addBox(3.5F, -7.25F, -0.75F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.25F, 9.5F, 2.5F));

		PartDefinition right_ear = head.addOrReplaceChild("right_ear", CubeListBuilder.create().texOffs(21, 0).addBox(1.5F, -24.0F, 1.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.25F, 14.5F, -2.5F));

		PartDefinition left_ear = head.addOrReplaceChild("left_ear", CubeListBuilder.create().texOffs(21, 0).mirror().addBox(-3.5F, -2.0F, 2.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-0.75F, -7.5F, -3.5F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 14).addBox(-3.0F, -4.0F, -2.5F, 6.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 14.0F, 3.0F));

		PartDefinition leftWing = body.addOrReplaceChild("leftWing", CubeListBuilder.create().texOffs(22, 14).addBox(0.0F, -6.5F, 0.0F, 9.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, -0.5F, 0.0F));

		PartDefinition leftWingTip = leftWing.addOrReplaceChild("leftWingTip", CubeListBuilder.create().texOffs(0, 27).addBox(0.0F, -6.5F, 0.0F, 8.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(9.0F, 0.0F, 0.0F));

		PartDefinition rightWing = body.addOrReplaceChild("rightWing", CubeListBuilder.create().texOffs(0, 27).mirror().addBox(-17.0F, -6.5F, 0.0F, 8.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-3.0F, -0.5F, 0.0F));

		PartDefinition rightWingTip = rightWing.addOrReplaceChild("rightWingTip", CubeListBuilder.create().texOffs(22, 14).mirror().addBox(0.0F, -6.5F, 0.0F, 9.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-9.0F, 0.0F, 0.0F));

		PartDefinition flag = rightWing.addOrReplaceChild("flag", CubeListBuilder.create(), PartPose.offsetAndRotation(6.0F, 0.0F, 0.0F, -0.3054F, 0.0F, 0.0F));

		PartDefinition cube_r1 = flag.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(36, 29).addBox(-15.0F, -19.75F, -2.75F, 14.0F, 7.0F, 0.0F, new CubeDeformation(0.0F))
				.texOffs(60, 43).addBox(-16.0F, -19.75F, -3.35F, 1.0F, 20.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3054F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

}