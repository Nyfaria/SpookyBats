package com.nyfaria.spookybats.datagen;

import com.nyfaria.spookybats.init.ItemInit;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;

import java.util.function.Consumer;

public class ModAdvancementGenerator implements ForgeAdvancementProvider.AdvancementGenerator {
    @Override
    public void generate(HolderLookup.Provider registries, Consumer<Advancement> saver, ExistingFileHelper existingFileHelper) {
        Advancement bop = Advancement.Builder.advancement()
                .display(ItemInit.BAT_WINGS.get(), Component.translatable("advancements.spookybats.massacre"), Component.translatable("advancements.spookybats.massacre.desc"), new ResourceLocation("textures/gui/advancements/backgrounds/husbandry.png"), FrameType.CHALLENGE, true, true, false)
                .addCriterion("bat_wings", InventoryChangeTrigger.TriggerInstance.hasItems(ItemInit.BAT_WINGS.get()))
                .save(saver, "spookybats:bat_massacre");
    }
}
