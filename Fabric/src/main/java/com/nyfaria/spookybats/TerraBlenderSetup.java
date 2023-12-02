package com.nyfaria.spookybats;

import terrablender.api.TerraBlenderApi;

public class TerraBlenderSetup implements TerraBlenderApi {
    @Override
    public void onTerraBlenderInitialized() {
        CommonClass.setupTerraBlender();
    }
}
