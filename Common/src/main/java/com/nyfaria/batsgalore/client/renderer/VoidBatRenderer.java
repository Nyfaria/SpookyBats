package com.nyfaria.batsgalore.client.renderer;

import com.nyfaria.batsgalore.client.model.ModBatModel;
import com.nyfaria.batsgalore.client.renderer.api.ModBatRenderer;
import com.nyfaria.batsgalore.entity.api.ModBat;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import org.jetbrains.annotations.Nullable;

public class VoidBatRenderer extends ModBatRenderer<ModBat> {

    public VoidBatRenderer(EntityRendererProvider.Context p_173929_, ModBatModel<ModBat> model) {
        super(p_173929_, model);
    }

    @Nullable
    @Override
    protected RenderType getRenderType(ModBat $$0, boolean $$1, boolean $$2, boolean $$3) {
        return RenderType.endPortal();
    }
}
