package com.nyfaria.spookybats.mixins;

import com.nyfaria.spookybats.client.model.SpookyOakHangingSignModel;
import com.nyfaria.spookybats.init.BlockInit;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.world.level.block.state.properties.WoodType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashMap;
import java.util.Map;

@Mixin(HangingSignRenderer.class)
public class HangingSignRendererMixin {

    @Shadow @Mutable
    private Map<WoodType, HangingSignRenderer.HangingSignModel> hangingSignModels;

    @Inject(method = "<init>", at = @At("RETURN"))
    public void addSign(BlockEntityRendererProvider.Context context, CallbackInfo ci) {
        Map<WoodType, HangingSignRenderer.HangingSignModel> map = new HashMap<>(hangingSignModels);
        map.put(BlockInit.SPOOKY_OAK.woodType(), new HangingSignRenderer.HangingSignModel(context.bakeLayer(SpookyOakHangingSignModel.LAYER_LOCATION)));
        hangingSignModels = map;
    }
}
