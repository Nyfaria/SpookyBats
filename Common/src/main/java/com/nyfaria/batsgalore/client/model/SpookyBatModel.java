package com.nyfaria.batsgalore.client.model;// Made with Blockbench 4.8.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.nyfaria.batsgalore.entity.api.SpookyBat;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import org.joml.Vector3f;

public class SpookyBatModel<T extends SpookyBat> extends HierarchicalModel<T> implements ArmedModel {
	protected final ModelPart root;
	protected final ModelPart head;
	protected final ModelPart body;
	protected final ModelPart rightWing;
	protected final ModelPart leftWing;
	protected final ModelPart rightWingTip;
	protected final ModelPart leftWingTip;

	public SpookyBatModel(ModelPart pRoot) {
		this.root = pRoot;
		this.head = pRoot.getChild("head");
		this.body = pRoot.getChild("body");
		this.rightWing = this.body.getChild("rightWing");
		this.rightWingTip = this.rightWing.getChild("rightWingTip");
		this.leftWing = this.body.getChild("leftWing");
		this.leftWingTip = this.leftWing.getChild("leftWingTip");
	}

	public ModelPart root() {
		return this.root;
	}


	@Override
	public void setupAnim(SpookyBat pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
		if (pEntity.isResting()) {
			this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
			this.head.yRot = (float)Math.PI - pNetHeadYaw * ((float)Math.PI / 180F);
			this.head.zRot = (float)Math.PI;
			this.head.setPos(getHeadPos().x,getHeadPos().y-2.0F, getHeadPos().z);
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
			this.head.setPos(getHeadPos().x,getHeadPos().y, getHeadPos().z);
			this.rightWing.setPos(0.0F, 0.0F, 0.0F);
			this.leftWing.setPos(0.0F, 0.0F, 0.0F);
			this.body.xRot = ((float)Math.PI / 4F) + Mth.cos(pAgeInTicks * 0.1F) * 0.15F;
			this.body.yRot = 0.0F;
			this.rightWing.yRot = Mth.cos(pAgeInTicks * SpookyBat.FLAP_DEGREES_PER_TICK * ((float)Math.PI / 180F)) * (float)Math.PI * 0.25F;
			this.leftWing.yRot = -this.rightWing.yRot;
			this.rightWingTip.yRot = this.rightWing.yRot * 0.5F;
			this.leftWingTip.yRot = -this.rightWing.yRot * 0.5F;
		}
	}

	public Vector3f getHeadPos(){
		return new Vector3f(-0.25F, 11.5F, 0F);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
	@Override
	public void translateToHand(HumanoidArm humanoidArm, PoseStack pPoseStack) {
		this.root.translateAndRotate(pPoseStack);
		this.body.translateAndRotate(pPoseStack);
		this.rightWing.translateAndRotate(pPoseStack);
		this.rightWingTip.translateAndRotate(pPoseStack);
		pPoseStack.scale(0.7F, 0.7F, 0.7F);
		pPoseStack.mulPose(Axis.XP.rotationDegrees(-40.0f));
		pPoseStack.mulPose(Axis.YP.rotationDegrees(-10.0f));
		pPoseStack.translate(0,-0.5f,-0.5f);
	}
}