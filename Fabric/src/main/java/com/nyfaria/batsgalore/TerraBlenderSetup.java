package com.nyfaria.batsgalore;

import com.nyfaria.batsgalore.CommonClass;
import terrablender.api.TerraBlenderApi;

public class TerraBlenderSetup implements TerraBlenderApi {
    @Override
    public void onTerraBlenderInitialized() {
        CommonClass.setupTerraBlender();
    }
}
