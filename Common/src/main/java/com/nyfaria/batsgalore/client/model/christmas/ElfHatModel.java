package com.nyfaria.batsgalore.client.model.christmas;// Made with Blockbench 4.9.1
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.nyfaria.batsgalore.Constants;
import net.minecraft.client.model.AgeableListModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

public class ElfHatModel<T extends LivingEntity> extends AgeableListModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Constants.MODID, "elf_hat_model"), "main");
	private final ModelPart hat;

	public ElfHatModel(ModelPart root) {
		this.hat = root.getChild("hat");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition hat = partdefinition.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(0, 0).addBox(-4.5F, -8.9121F, -5.6825F, 9.0F, 2.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.4236F, 0.1947F, -0.1484F, 0.0F, 0.0F));

		PartDefinition left_ear = hat.addOrReplaceChild("left_ear", CubeListBuilder.create(), PartPose.offset(7.0F, -28.1621F, -3.4325F));

		PartDefinition cube_r1 = left_ear.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 24).mirror().addBox(1.5F, 16.773F, 9.4597F, 2.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(0, 24).addBox(10.5F, 16.773F, 9.4597F, 2.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-14.0F, 0.0F, 0.0F, -0.3927F, 0.0F, 0.0F));

		PartDefinition right_ear = hat.addOrReplaceChild("right_ear", CubeListBuilder.create(), PartPose.offset(7.0F, -28.1621F, -3.4325F));

		PartDefinition top = hat.addOrReplaceChild("top", CubeListBuilder.create(), PartPose.offset(-1.0F, -13.9121F, 1.0675F));

		PartDefinition cube_r2 = top.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 11).addBox(-9.5F, 13.2323F, 7.4073F, 5.0F, 7.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.0F, -16.5F, -4.25F, -0.3927F, 0.0F, 0.0F));

		PartDefinition top_2 = top.addOrReplaceChild("top_2", CubeListBuilder.create(), PartPose.offset(1.0F, -5.0163F, -1.3005F));

		PartDefinition cube_r3 = top_2.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(17, 19).addBox(-1.5F, 3.6527F, -1.9684F, 3.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.3927F, 0.0F, 0.0F));

		PartDefinition bell = top_2.addOrReplaceChild("bell", CubeListBuilder.create(), PartPose.offset(0.0F, -0.7141F, 2.7993F));

		PartDefinition cube_r4 = bell.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, 1.6517F, 5.5407F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.7854F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		hat.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	protected Iterable<ModelPart> headParts() {
		return ImmutableList.of(hat);
	}

	@Override
	protected Iterable<ModelPart> bodyParts() {
		return ImmutableList.of();
	}
}