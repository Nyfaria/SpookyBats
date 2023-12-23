package com.nyfaria.batsgalore.client.model.spooky;// Made with Blockbench 4.8.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.nyfaria.batsgalore.Constants;
import com.nyfaria.batsgalore.client.model.ModBatModel;
import com.nyfaria.batsgalore.entity.WingedTurmoil;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;
import org.joml.Vector3f;

public class WingedTurmoilModel<T extends WingedTurmoil> extends ModBatModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Constants.MODID, "wingedturmoil"), "main");

	public WingedTurmoilModel(ModelPart root) {
		super(root);
	}

	public static LayerDefinition createBodyLayer() {
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
	@Override
	public Vector3f getHeadPos() {
		return super.getHeadPos().add(0,-1.2f,0);
	}
}