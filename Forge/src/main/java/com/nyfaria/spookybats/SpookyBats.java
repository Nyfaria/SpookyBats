package com.nyfaria.spookybats;

import com.nyfaria.spookybats.datagen.ModAdvancementProvider;
import com.nyfaria.spookybats.datagen.ModBlockStateProvider;
import com.nyfaria.spookybats.datagen.ModItemModelProvider;
import com.nyfaria.spookybats.datagen.ModLangProvider;
import com.nyfaria.spookybats.datagen.ModLootTableProvider;
import com.nyfaria.spookybats.datagen.ModRecipeProvider;
import com.nyfaria.spookybats.datagen.ModSoundProvider;
import com.nyfaria.spookybats.datagen.ModTagProvider;
import com.nyfaria.spookybats.datagen.WorldGenProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod(Constants.MODID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class SpookyBats {
    
    public SpookyBats() {
        Constants.LOG.info("Hello Forge world!");
        CommonClass.init();
    }

    @SubscribeEvent
    public static void onGatherData(GatherDataEvent event) {
        PackOutput packOutput = event.getGenerator().getPackOutput();
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        boolean includeServer = event.includeServer();
        boolean includeClient = event.includeClient();
        generator.addProvider(includeServer, new ModRecipeProvider(packOutput));
        generator.addProvider(includeServer, new ModLootTableProvider(packOutput));
        generator.addProvider(includeServer, new ModSoundProvider(packOutput, existingFileHelper));
        generator.addProvider(includeServer, new ModTagProvider.Blocks(packOutput,event.getLookupProvider(), existingFileHelper));
        generator.addProvider(includeServer, new ModTagProvider.Items(packOutput,event.getLookupProvider(), existingFileHelper));
        generator.addProvider(includeClient, new ModBlockStateProvider(packOutput, existingFileHelper));
        generator.addProvider(includeClient, new ModItemModelProvider(packOutput, existingFileHelper));
        generator.addProvider(includeClient, new ModLangProvider(packOutput));
        generator.addProvider(includeServer, new ModAdvancementProvider(packOutput,event.getLookupProvider(), existingFileHelper));
        generator.addProvider(includeServer, new WorldGenProvider(packOutput,event.getLookupProvider()));
    }
}