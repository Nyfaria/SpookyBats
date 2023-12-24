package com.nyfaria.batsgalore.client.model;

import com.nyfaria.batsgalore.Constants;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;

public class StatueLayers {
    public static final ModelLayerLocation STATUE_OF_TURMOIL_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Constants.MODID, "statue_of_turmoil"), "main");
    public static final ResourceLocation STATUE_OF_TURMOIL_TEXTURE = new ResourceLocation(Constants.MODID, "textures/block/statue_of_turmoil.png");

    public static LayerDefinition createStatueOfTurmoilLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition statue = partdefinition.addOrReplaceChild("statue", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition bat = statue.addOrReplaceChild("bat", CubeListBuilder.create(), PartPose.offset(-3.9228F, -8.25F, -3.2661F));

        PartDefinition head = bat.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 14).addBox(-6.8608F, 2.1728F, -0.3948F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(7.82F, -11.12F, -3.83F));

        PartDefinition right_ear = head.addOrReplaceChild("right_ear", CubeListBuilder.create().texOffs(0, 0).addBox(-12.5084F, 9.2692F, 2.9348F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).mirror().addBox(-6.946F, 9.2692F, 2.9348F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(4.83F, -9.31F, -1.84F));

        PartDefinition left_ear = head.addOrReplaceChild("left_ear", CubeListBuilder.create(), PartPose.offset(-4.47F, -9.31F, -1.84F));

        PartDefinition body = bat.addOrReplaceChild("body", CubeListBuilder.create().texOffs(20, 22).addBox(-6.4506F, 6.3538F, 1.2274F, 5.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.99F, -6.48F, -7.335F, 0.5672F, 0.0F, 0.0F));

        PartDefinition rightWing2 = body.addOrReplaceChild("rightWing2", CubeListBuilder.create().texOffs(16, 33).addBox(-8.0F, -5.5F, 0.0F, 8.0F, 11.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.2444F, 8.9682F, 2.941F, 0.0F, 0.4363F, 0.0F));

        PartDefinition rightWingTip2 = rightWing2.addOrReplaceChild("rightWingTip2", CubeListBuilder.create().texOffs(0, 26).addBox(-8.0F, -5.5F, 0.0F, 8.0F, 11.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.0F, 0.0F, 0.0F, 0.0F, 0.6545F, 0.0F));

        PartDefinition leftWing2 = body.addOrReplaceChild("leftWing2", CubeListBuilder.create().texOffs(16, 33).mirror().addBox(0.0F, -5.5F, 0.0F, 8.0F, 11.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.89F, 8.9682F, 2.941F, 0.0F, -0.4363F, 0.0F));

        PartDefinition leftWingTip2 = leftWing2.addOrReplaceChild("leftWingTip2", CubeListBuilder.create().texOffs(0, 26).mirror().addBox(0.0F, -5.5F, 0.0F, 8.0F, 11.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(8.0F, 0.0F, 0.0F, 0.0F, -0.6545F, 0.0F));

        PartDefinition pedastal = statue.addOrReplaceChild("pedastal", CubeListBuilder.create().texOffs(0, 0).addBox(-14.0F, -2.0F, 2.0F, 12.0F, 2.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(16, 42).addBox(-14.0F, -2.0F, 2.0F, 12.0F, 2.0F, 12.0F, new CubeDeformation(0.2F)), PartPose.offset(8.0F, 0.0F, -8.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }
}
