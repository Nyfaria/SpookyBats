package com.nyfaria.spookybats.client.model;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.nyfaria.spookybats.Constants;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;

public class SpookyOakHangingSignModel extends Model {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Constants.MODID, "hanging_sign/spooky_oak"), "main");
	private final ModelPart bone;

	public SpookyOakHangingSignModel(ModelPart root) {
		super(RenderType::entityCutoutNoCull);
		this.bone = root.getChild("bone");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(0, 12).addBox(-15.0F, -16.0F, 6.0F, 14.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-15.0F, -10.0F, 7.0F, 14.0F, 10.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 22).addBox(-15.0F, 0.0F, 8.0F, 14.0F, 4.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 18).addBox(-15.0F, -2.0F, 7.0F, 14.0F, 2.0F, 2.0F, new CubeDeformation(0.2F))
		.texOffs(0, 26).addBox(-12.0F, -14.0F, 8.0F, 8.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, 24.0F, -8.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}



	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		bone.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}