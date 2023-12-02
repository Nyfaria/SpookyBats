package com.nyfaria.spookybats.client.renderer;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.datafixers.util.Pair;
import com.mojang.math.Axis;
import com.nyfaria.spookybats.Constants;
import com.nyfaria.spookybats.client.model.ModBoatModel;
import com.nyfaria.spookybats.client.model.ModChestBoatModel;
import com.nyfaria.spookybats.entity.ModBoat;
import com.nyfaria.spookybats.entity.api.ModBoatType;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.WaterPatchModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.joml.Quaternionf;

import java.util.Map;
import java.util.stream.Stream;

public class ModBoatRenderer extends EntityRenderer<ModBoat> {
    private final Map<ModBoatType, Pair<ResourceLocation, ListModel<ModBoat>>> boatResources;

    public ModBoatRenderer(EntityRendererProvider.Context $$0, boolean $$1) {
        super($$0);
        this.shadowRadius = 0.8F;
        this.boatResources = Stream.of(ModBoatType.values()).collect(ImmutableMap.toImmutableMap(($$0x) -> $$0x, ($$2) -> Pair.of(new ResourceLocation(Constants.MODID, getTextureLocation($$2, $$1)), this.createBoatModel($$0, $$2, $$1))));
    }

    private ListModel<ModBoat> createBoatModel(EntityRendererProvider.Context $$0, ModBoatType $$1, boolean $$2) {
        ModelLayerLocation $$3 = $$2 ? createChestBoatModelName($$1) : createBoatModelName($$1);
        ModelPart $$4 = $$0.bakeLayer($$3);
        return ($$2 ? new ModChestBoatModel($$4) : new ModBoatModel($$4));
    }

    public static ModelLayerLocation createBoatModelName(ModBoatType $$0) {
        return createLocation("boat/" + $$0.getName(), "main");
    }

    public static ModelLayerLocation createChestBoatModelName(ModBoatType $$0) {
        return createLocation("chest_boat/" + $$0.getName(), "main");
    }

    private static ModelLayerLocation createLocation(String $$0, String $$1) {
        return new ModelLayerLocation(new ResourceLocation(Constants.MODID, $$0), $$1);
    }

    private static String getTextureLocation(ModBoatType $$0, boolean $$1) {
        return $$1 ? "textures/entity/chest_boat/" + $$0.getName() + ".png" : "textures/entity/boat/" + $$0.getName() + ".png";
    }

    public void render(ModBoat $$0, float $$1, float $$2, PoseStack $$3, MultiBufferSource $$4, int $$5) {
        $$3.pushPose();
        $$3.translate(0.0F, 0.375F, 0.0F);
        $$3.mulPose(Axis.YP.rotationDegrees(180.0F - $$1));
        float $$6 = (float) $$0.getHurtTime() - $$2;
        float $$7 = $$0.getDamage() - $$2;
        if ($$7 < 0.0F) {
            $$7 = 0.0F;
        }

        if ($$6 > 0.0F) {
            $$3.mulPose(Axis.XP.rotationDegrees(Mth.sin($$6) * $$6 * $$7 / 10.0F * (float) $$0.getHurtDir()));
        }

        float $$8 = $$0.getBubbleAngle($$2);
        if (!Mth.equal($$8, 0.0F)) {
            $$3.mulPose((new Quaternionf()).setAngleAxis($$0.getBubbleAngle($$2) * 0.017453292F, 1.0F, 0.0F, 1.0F));
        }

        Pair<ResourceLocation, ListModel<ModBoat>> $$9 = this.boatResources.get($$0.getModVariant());
        ResourceLocation $$10 = $$9.getFirst();
        ListModel<ModBoat> $$11 = $$9.getSecond();
        $$3.scale(-1.0F, -1.0F, 1.0F);
        $$3.mulPose(Axis.YP.rotationDegrees(90.0F));
        $$11.setupAnim($$0, $$2, 0.0F, -0.1F, 0.0F, 0.0F);
        VertexConsumer $$12 = $$4.getBuffer($$11.renderType($$10));
        $$11.renderToBuffer($$3, $$12, $$5, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        if (!$$0.isUnderWater()) {
            VertexConsumer $$13 = $$4.getBuffer(RenderType.waterMask());
            if ($$11 instanceof WaterPatchModel) {
                WaterPatchModel $$14 = (WaterPatchModel) $$11;
                $$14.waterPatch().render($$3, $$13, $$5, OverlayTexture.NO_OVERLAY);
            }
        }

        $$3.popPose();
        super.render($$0, $$1, $$2, $$3, $$4, $$5);
    }

    public ResourceLocation getTextureLocation(ModBoat $$0) {
        return (ResourceLocation) ((Pair) this.boatResources.get($$0.getVariant())).getFirst();
    }
}
