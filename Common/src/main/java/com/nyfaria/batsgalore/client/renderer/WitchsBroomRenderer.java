package com.nyfaria.batsgalore.client.renderer;

import com.nyfaria.batsgalore.Constants;
import com.nyfaria.batsgalore.client.model.WitchsBroomModel;
import com.nyfaria.batsgalore.entity.WitchsBroom;
import com.nyfaria.batsgalore.registration.RegistryObject;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.resources.ResourceLocation;

public class WitchsBroomRenderer extends LivingEntityRenderer<WitchsBroom, WitchsBroomModel<WitchsBroom>> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(Constants.MODID,"textures/entity/witchs_broom.png");
    public WitchsBroomRenderer(EntityRendererProvider.Context context) {
        super(context,new WitchsBroomModel<>(context.bakeLayer(WitchsBroomModel.LAYER_LOCATION)) , 0f);
    }

    @Override
    public ResourceLocation getTextureLocation(WitchsBroom witchsBroom) {
        return TEXTURE;
    }
}
