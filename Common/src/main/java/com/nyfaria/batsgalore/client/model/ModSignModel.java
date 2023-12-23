package com.nyfaria.batsgalore.client.model;

import com.nyfaria.batsgalore.Constants;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.resources.ResourceLocation;

public class ModSignModel extends SignRenderer.SignModel {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Constants.MODID, "spookyoaksignmodel"), "main");

	public ModSignModel(ModelPart root) {
		super(root);
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition board = partdefinition.addOrReplaceChild("board", CubeListBuilder.create().texOffs(0, 0).addBox(-12.0F, -27.0F, -1.0F, 24.0F, 12.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(8, 20).addBox(-12.0F, -15.0F, 0.0F, 24.0F, 9.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(8, 14).addBox(-12.0F, -19.0F, -1.0F, 24.0F, 4.0F, 2.0F, new CubeDeformation(0.2F)), PartPose.offset(0.0F, 13.0F, 0.0F));

		PartDefinition stick = partdefinition.addOrReplaceChild("stick", CubeListBuilder.create().texOffs(0, 14).addBox(-1.0F, -26.0F, -1.0F, 2.0F, 14.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

}