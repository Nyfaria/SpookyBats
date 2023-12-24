package com.nyfaria.batsgalore.config;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class CommonConfig {
    public static final ForgeConfigSpec CONFIG_SPEC;
    public static final CommonConfig INSTANCE;

    static {
        Pair<CommonConfig, ForgeConfigSpec> pair = new ForgeConfigSpec.Builder().configure(CommonConfig::new);
        CONFIG_SPEC = pair.getRight();
        INSTANCE = pair.getLeft();
    }
    public final ForgeConfigSpec.BooleanValue spookyOakForestGeneration;
    public final ForgeConfigSpec.BooleanValue christmasForestGeneration;

    private CommonConfig(ForgeConfigSpec.Builder builder) {
        builder.push("generation");
        spookyOakForestGeneration = builder.comment("Should the Spooky Oak Forest generate?").define("spookyOakForestGeneration", true);
        christmasForestGeneration = builder.comment("Should the Christmas Forest generate?").define("christmasForestGeneration", true);
        builder.pop();
    }
}
