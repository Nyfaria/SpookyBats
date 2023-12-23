package com.nyfaria.batsgalore.client.model.spooky;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.nyfaria.batsgalore.Constants;
import com.nyfaria.batsgalore.entity.WitchsBroom;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;

public class WitchsBroomModel<T extends WitchsBroom> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Constants.MODID, "witchs_broom"), "main");
	private final ModelPart broom;

	public WitchsBroomModel(ModelPart root) {
		this.broom = root.getChild("broom");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition broom = partdefinition.addOrReplaceChild("broom", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5668F, -2.0F, -14.5933F, 4.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5668F, 17.95F, 1.8808F, 0.0F, 3.1416F, 0.0F));

		PartDefinition cube_r1 = broom.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 21).addBox(-1.0F, -1.0F, -3.5F, 2.0F, 2.0F, 7.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.3665F, 0.0F));

		PartDefinition cube_r2 = broom.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(14, 14).addBox(-1.0F, -1.0F, -3.5F, 2.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6527F, 0.0F, 6.2096F, 0.0F, -0.1571F, 0.0F));

		PartDefinition cube_r3 = broom.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 10).addBox(-1.0F, -1.0F, -3.5F, 2.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5668F, 0.0F, -6.1683F, 0.0F, -0.1833F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		broom.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}