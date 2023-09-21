package com.nyfaria.spookybats.client.model;// Made with Blockbench 4.8.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.nyfaria.spookybats.Constants;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ambient.Bat;

public class PumpkinBat<T extends Bat> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Constants.MODID, "pumpkin_bat"), "main");
	private final ModelPart root;
	private final ModelPart head;
	private final ModelPart body;
	private final ModelPart rightWing;
	private final ModelPart leftWing;
	private final ModelPart rightWingTip;
	private final ModelPart leftWingTip;

	public PumpkinBat(ModelPart pRoot) {
		this.root = pRoot;
		this.head = pRoot.getChild("head");
		this.body = pRoot.getChild("body");
		this.rightWing = this.body.getChild("right_wing");
		this.rightWingTip = this.rightWing.getChild("right_wing_tip");
		this.leftWing = this.body.getChild("left_wing");
		this.leftWingTip = this.leftWing.getChild("left_wing_tip");
	}

	public ModelPart root() {
		return this.root;
	}
	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.75F, 2, -3.5F, 10.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.25F, 4.5F, 2.5F));
		head.addOrReplaceChild("right_ear", CubeListBuilder.create().texOffs(28, 0).addBox(3.0F, -15.5F, 1.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.25F, 14.5F, -2.5F));
		head.addOrReplaceChild("left_ear", CubeListBuilder.create().texOffs(28, 0).mirror().addBox(-5.0F, -15.5F, 2.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-0.75F, 14.5F, -3.5F));
		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 16).addBox(-3.0F, -4.0F, -2.5F, 6.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 14.0F, 3.0F));
		PartDefinition leftWing = body.addOrReplaceChild("left_wing", CubeListBuilder.create().texOffs(22, 16).addBox(0.0F, -6.5F, 0.0F, 9.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, -0.5F, 0.0F));
		leftWing.addOrReplaceChild("left_wing_tip", CubeListBuilder.create().texOffs(0, 29).addBox(0.0F, -6.5F, 0.0F, 8.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(9.0F, 0.0F, 0.0F));
		PartDefinition rightWing = body.addOrReplaceChild("right_wing", CubeListBuilder.create().texOffs(22, 16).mirror().addBox(-9.0F, -6.5F, 0.0F, 9.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-3.0F, -0.5F, 0.0F));
		rightWing.addOrReplaceChild("right_wing_tip", CubeListBuilder.create().texOffs(0, 29).mirror().addBox(-8.0F, -6.5F, 0.0F, 8.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-9.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(Bat pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
		if (pEntity.isResting()) {
			this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
			this.head.yRot = (float)Math.PI - pNetHeadYaw * ((float)Math.PI / 180F);
			this.head.zRot = (float)Math.PI;
			this.head.setPos(0.0F, -2.0F, 0.0F);
			this.rightWing.setPos(-3.0F, 0.0F, 3.0F);
			this.leftWing.setPos(3.0F, 0.0F, 3.0F);
			this.body.xRot = (float)Math.PI;
			this.rightWing.xRot = -0.15707964F;
			this.rightWing.yRot = -1.2566371F;
			this.rightWingTip.yRot = -1.7278761F;
			this.leftWing.xRot = this.rightWing.xRot;
			this.leftWing.yRot = -this.rightWing.yRot;
			this.leftWingTip.yRot = -this.rightWingTip.yRot;
		} else {
			this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
			this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
			this.head.zRot = 0.0F;
			this.head.setPos(0.0F, 0.0F, 0.0F);
			this.rightWing.setPos(0.0F, 0.0F, 0.0F);
			this.leftWing.setPos(0.0F, 0.0F, 0.0F);
			this.body.xRot = ((float)Math.PI / 4F) + Mth.cos(pAgeInTicks * 0.1F) * 0.15F;
			this.body.yRot = 0.0F;
			this.rightWing.yRot = Mth.cos(pAgeInTicks * 74.48451F * ((float)Math.PI / 180F)) * (float)Math.PI * 0.25F;
			this.leftWing.yRot = -this.rightWing.yRot;
			this.rightWingTip.yRot = this.rightWing.yRot * 0.5F;
			this.leftWingTip.yRot = -this.rightWing.yRot * 0.5F;
		}

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}