package com.nyfaria.spookybats.client.model;

import com.nyfaria.spookybats.Constants;
import com.nyfaria.spookybats.entity.api.SpookyBat;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class HerobrineBatModel<T extends SpookyBat> extends SpookyBatModel<T> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Constants.MODID, "herobrine_bat"), "main");

	public HerobrineBatModel(ModelPart pRoot) {
		super(pRoot);
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		// Bat
		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 16).addBox(-3.75F, -7.5F, -3.5F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
			.texOffs(0, 0).addBox(-3.75F, -7.5F, -3.5F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.2F)), PartPose.offset(-0.25F, 9.5F, 2.5F));

		PartDefinition right_ear = head.addOrReplaceChild("right_ear", CubeListBuilder.create().texOffs(32, 4).addBox(2.0F, -25.0F, 1.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.25F, 14.5F, -2.5F));

		PartDefinition left_ear = head.addOrReplaceChild("left_ear", CubeListBuilder.create().texOffs(24, 0).addBox(-4.0F, -3.0F, 2.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.75F, -7.5F, -3.5F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(27, 11).addBox(-3.0F, -4.0F, -2.5F, 6.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 14.0F, 3.0F));

		PartDefinition leftWing = body.addOrReplaceChild("leftWing", CubeListBuilder.create().texOffs(18, 32).addBox(0.0F, -6.5F, 0.0F, 9.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, -0.5F, 0.0F));

		PartDefinition leftWingTip = leftWing.addOrReplaceChild("leftWingTip", CubeListBuilder.create().texOffs(36, 37).addBox(0.0F, -6.5F, 0.0F, 8.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(9.0F, 0.0F, 0.0F));

		PartDefinition rightWing = body.addOrReplaceChild("rightWing", CubeListBuilder.create().texOffs(0, 32).addBox(-9.0F, -6.5F, 0.0F, 9.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -0.5F, 0.0F));

		PartDefinition rightWingTip = rightWing.addOrReplaceChild("rightWingTip", CubeListBuilder.create().texOffs(36, 24).addBox(-8.0F, -6.5F, 0.0F, 8.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-9.0F, 0.0F, 0.0F));

		// Diamond sword
		PartDefinition diamond_sword = partdefinition.addOrReplaceChild("diamond_sword", CubeListBuilder.create().texOffs(0, 16).addBox(-16.0F, -16.0F, 0.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(8, 45).addBox(-13.0F, -15.0F, 0.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(4, 45).addBox(-12.0F, -14.0F, 0.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(0, 45).addBox(-11.0F, -13.0F, 0.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(24, 6).addBox(-15.0F, -13.0F, 0.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(43, 5).addBox(-10.0F, -12.0F, 0.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(28, 45).addBox(-14.0F, -12.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(40, 0).addBox(-9.0F, -11.0F, 0.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(34, 0).addBox(-4.0F, -10.0F, 0.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(0, 0).addBox(-8.0F, -10.0F, 0.0F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(32, 24).addBox(-5.0F, -9.0F, 0.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(4, 0).addBox(-7.0F, -9.0F, 0.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(24, 45).addBox(-4.0F, -8.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(44, 11).addBox(-6.0F, -8.0F, 0.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(16, 45).addBox(-4.0F, -5.0F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(12, 45).addBox(-9.0F, -5.0F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(44, 0).addBox(-3.0F, -4.0F, 0.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(20, 45).addBox(-10.0F, -4.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(0, 20).addBox(-2.0F, -3.0F, 0.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.0F, 24.0F, -4.0F, -2.6068F, -0.9507F, 2.5124F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}
}
