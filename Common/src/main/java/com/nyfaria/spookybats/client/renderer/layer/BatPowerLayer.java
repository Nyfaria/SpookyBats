package com.nyfaria.spookybats.client.renderer.layer;

import com.nyfaria.spookybats.client.model.PumpkinBatModel;
import com.nyfaria.spookybats.client.model.SpookyBatModel;
import com.nyfaria.spookybats.entity.CreeperBat;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EnergySwirlLayer;
import net.minecraft.resources.ResourceLocation;

public class BatPowerLayer extends EnergySwirlLayer<CreeperBat, SpookyBatModel<CreeperBat>> {
   private static final ResourceLocation POWER_LOCATION = new ResourceLocation("textures/entity/creeper/creeper_armor.png");
   private final SpookyBatModel<CreeperBat> model;

   public BatPowerLayer(RenderLayerParent<CreeperBat, SpookyBatModel<CreeperBat>> pRenderer, EntityModelSet pModelSet) {
      super(pRenderer);
      this.model = new SpookyBatModel<>(pModelSet.bakeLayer(PumpkinBatModel.OVERLAY_LOCATION));
   }

   protected float xOffset(float pTickCount) {
      return pTickCount * 0.01F;
   }

   protected ResourceLocation getTextureLocation() {
      return POWER_LOCATION;
   }

   protected EntityModel<CreeperBat> model() {
      return this.model;
   }
}