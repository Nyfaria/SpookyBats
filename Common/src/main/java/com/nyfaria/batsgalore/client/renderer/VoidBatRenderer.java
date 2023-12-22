package com.nyfaria.batsgalore.client.renderer;

import com.nyfaria.batsgalore.client.model.SpookyBatModel;
import com.nyfaria.batsgalore.client.renderer.api.SpookyBatRenderer;
import com.nyfaria.batsgalore.entity.api.ModBat;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import org.jetbrains.annotations.Nullable;

public class VoidBatRenderer extends SpookyBatRenderer<ModBat> {

    public VoidBatRenderer(EntityRendererProvider.Context p_173929_, SpookyBatModel<ModBat> model) {
        super(p_173929_, model);
    }

    @Nullable
    @Override
    protected RenderType getRenderType(ModBat $$0, boolean $$1, boolean $$2, boolean $$3) {
        return RenderType.endPortal();
    }
}
