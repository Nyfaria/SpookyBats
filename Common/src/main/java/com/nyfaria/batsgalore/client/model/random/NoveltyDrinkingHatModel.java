package com.nyfaria.batsgalore.client.model.random;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
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

import java.util.List;

public class NoveltyDrinkingHatModel<T extends LivingEntity> extends AgeableListModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "noveltydrinkinghat"), "main");
	private final ModelPart hat;

	public NoveltyDrinkingHatModel(ModelPart root) {
		this.hat = root.getChild("hat");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition hat = partdefinition.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.offset(0.0F, 28.0F, 0.0F));

		PartDefinition bone2 = hat.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(0, 16).addBox(-3.6125F, -7.35F, -3.525F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.6F))
		.texOffs(0, 0).addBox(-3.6575F, -7.395F, -3.48F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.4F))
		.texOffs(0, 16).addBox(-5.45F, -7.2375F, -0.8625F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(4.025F, -7.2375F, -0.8625F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.2875F, -28.75F, -0.575F));


		return LayerDefinition.create(meshdefinition, 32, 32);
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
		return List.of(hat);
	}

	@Override
	protected Iterable<ModelPart> bodyParts() {
		return List.of();
	}
}