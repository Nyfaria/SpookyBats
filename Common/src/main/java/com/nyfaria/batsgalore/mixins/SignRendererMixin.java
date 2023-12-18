package com.nyfaria.batsgalore.mixins;

import com.nyfaria.batsgalore.block.WoodCollection;
import com.nyfaria.batsgalore.client.model.SpookyOakSignModel;
import com.nyfaria.batsgalore.init.BlockInit;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.world.level.block.state.properties.WoodType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashMap;
import java.util.Map;

@Mixin(SignRenderer.class)
public class SignRendererMixin {

    @Shadow @Mutable
    private Map<WoodType, SignRenderer.SignModel> signModels;

    @Inject(method = "<init>", at = @At("RETURN"))
    public void addSign(BlockEntityRendererProvider.Context context, CallbackInfo ci) {
        Map<WoodType, SignRenderer.SignModel> map = new HashMap<>(signModels);
        WoodCollection.WOOD_COLLECTIONS.forEach(
                woodCollection -> map.put(woodCollection.woodType(), new SignRenderer.SignModel(context.bakeLayer(SpookyOakSignModel.LAYER_LOCATION)))
        );
        signModels = map;
    }
}
