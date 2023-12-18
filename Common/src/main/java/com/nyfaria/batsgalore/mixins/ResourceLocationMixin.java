package com.nyfaria.batsgalore.mixins;

import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ResourceLocation.class)
public abstract class ResourceLocationMixin {

    @Shadow @Final @Mutable
    private String namespace;

    protected ResourceLocationMixin(String $$0, String $$1, @Nullable ResourceLocation.Dummy $$2) {
    }

    @Inject(method = "<init>(Ljava/lang/String;Ljava/lang/String;Lnet/minecraft/resources/ResourceLocation$Dummy;)V", at = @At("TAIL"))
    private void init(String namespace, String path, ResourceLocation.Dummy dummy, CallbackInfo ci) {
        if (namespace.equals("spookybats")){
            this.namespace = "batsgalore";
        }
    }
}
