package com.nyfaria.batsgalore.client.model;

import com.google.common.collect.ImmutableList;
import com.nyfaria.batsgalore.entity.ModBoat;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.WaterPatchModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.vehicle.Boat;

public class ModBoatModel extends ListModel<ModBoat> implements WaterPatchModel {
    private static final String LEFT_PADDLE = "left_paddle";
    private static final String RIGHT_PADDLE = "right_paddle";
    private static final String WATER_PATCH = "water_patch";
    private static final String BOTTOM = "bottom";
    private static final String BACK = "back";
    private static final String FRONT = "front";
    private static final String RIGHT = "right";
    private static final String LEFT = "left";
    private final ModelPart leftPaddle;
    private final ModelPart rightPaddle;
    private final ModelPart waterPatch;
    private final ImmutableList<ModelPart> parts;

    public ModBoatModel(ModelPart $$0) {
        this.leftPaddle = $$0.getChild("left_paddle");
        this.rightPaddle = $$0.getChild("right_paddle");
        this.waterPatch = $$0.getChild("water_patch");
        this.parts = this.createPartsBuilder($$0).build();
    }

    protected ImmutableList.Builder<ModelPart> createPartsBuilder(ModelPart $$0) {
        ImmutableList.Builder<ModelPart> $$1 = new ImmutableList.Builder();
        $$1.add($$0.getChild("bottom"), $$0.getChild("back"), $$0.getChild("front"), $$0.getChild("right"), $$0.getChild("left"), this.leftPaddle, this.rightPaddle);
        return $$1;
    }

    public static void createChildren(PartDefinition $$0) {
        $$0.addOrReplaceChild("bottom", CubeListBuilder.create().texOffs(0, 0).addBox(-14.0F, -9.0F, -3.0F, 28.0F, 16.0F, 3.0F), PartPose.offsetAndRotation(0.0F, 3.0F, 1.0F, 1.5707964F, 0.0F, 0.0F));
        $$0.addOrReplaceChild("back", CubeListBuilder.create().texOffs(0, 19).addBox(-13.0F, -7.0F, -1.0F, 18.0F, 6.0F, 2.0F), PartPose.offsetAndRotation(-15.0F, 4.0F, 4.0F, 0.0F, 4.712389F, 0.0F));
        $$0.addOrReplaceChild("front", CubeListBuilder.create().texOffs(0, 27).addBox(-8.0F, -7.0F, -1.0F, 16.0F, 6.0F, 2.0F), PartPose.offsetAndRotation(15.0F, 4.0F, 0.0F, 0.0F, 1.5707964F, 0.0F));
        $$0.addOrReplaceChild("right", CubeListBuilder.create().texOffs(0, 35).addBox(-14.0F, -7.0F, -1.0F, 28.0F, 6.0F, 2.0F), PartPose.offsetAndRotation(0.0F, 4.0F, -9.0F, 0.0F, 3.1415927F, 0.0F));
        $$0.addOrReplaceChild("left", CubeListBuilder.create().texOffs(0, 43).addBox(-14.0F, -7.0F, -1.0F, 28.0F, 6.0F, 2.0F), PartPose.offset(0.0F, 4.0F, 9.0F));
        $$0.addOrReplaceChild("left_paddle", CubeListBuilder.create().texOffs(62, 0).addBox(-1.0F, 0.0F, -5.0F, 2.0F, 2.0F, 18.0F).addBox(-1.001F, -3.0F, 8.0F, 1.0F, 6.0F, 7.0F), PartPose.offsetAndRotation(3.0F, -5.0F, 9.0F, 0.0F, 0.0F, 0.19634955F));
        $$0.addOrReplaceChild("right_paddle", CubeListBuilder.create().texOffs(62, 20).addBox(-1.0F, 0.0F, -5.0F, 2.0F, 2.0F, 18.0F).addBox(0.001F, -3.0F, 8.0F, 1.0F, 6.0F, 7.0F), PartPose.offsetAndRotation(3.0F, -5.0F, -9.0F, 0.0F, 3.1415927F, 0.19634955F));
        $$0.addOrReplaceChild("water_patch", CubeListBuilder.create().texOffs(0, 0).addBox(-14.0F, -9.0F, -3.0F, 28.0F, 16.0F, 3.0F), PartPose.offsetAndRotation(0.0F, -3.0F, 1.0F, 1.5707964F, 0.0F, 0.0F));
    }

    public static LayerDefinition createBodyModel() {
        MeshDefinition $$0 = new MeshDefinition();
        PartDefinition $$1 = $$0.getRoot();
        createChildren($$1);
        return LayerDefinition.create($$0, 128, 64);
    }

    public void setupAnim(ModBoat $$0, float $$1, float $$2, float $$3, float $$4, float $$5) {
        animatePaddle($$0, 0, this.leftPaddle, $$1);
        animatePaddle($$0, 1, this.rightPaddle, $$1);
    }

    public ImmutableList<ModelPart> parts() {
        return this.parts;
    }

    public ModelPart waterPatch() {
        return this.waterPatch;
    }

    private static void animatePaddle(ModBoat $$0, int $$1, ModelPart $$2, float $$3) {
        float $$4 = $$0.getRowingTime($$1, $$3);
        $$2.xRot = Mth.clampedLerp(-1.0471976F, -0.2617994F, (Mth.sin(-$$4) + 1.0F) / 2.0F);
        $$2.yRot = Mth.clampedLerp(-0.7853982F, 0.7853982F, (Mth.sin(-$$4 + 1.0F) + 1.0F) / 2.0F);
        if ($$1 == 1) {
            $$2.yRot = 3.1415927F - $$2.yRot;
        }

    }
}
