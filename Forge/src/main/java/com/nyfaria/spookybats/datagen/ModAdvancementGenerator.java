package com.nyfaria.spookybats.datagen;

import com.nyfaria.spookybats.Constants;
import com.nyfaria.spookybats.init.EntityInit;
import com.nyfaria.spookybats.init.ItemInit;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.KilledTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;

import java.util.function.Consumer;

public class ModAdvancementGenerator implements ForgeAdvancementProvider.AdvancementGenerator {
    @Override
    public void generate(HolderLookup.Provider registries, Consumer<Advancement> saver, ExistingFileHelper existingFileHelper) {
        Advancement.Builder batsBuilder = Advancement.Builder.advancement()
                .display(ItemInit.WITCHES_BREW.get(), Component.translatable("advancements.spookybats.bat_death"), Component.translatable("advancements.spookybats.bat_death.desc"), new ResourceLocation("textures/gui/advancements/backgrounds/husbandry.png"), FrameType.TASK, true, true, false)
                .requirements(RequirementsStrategy.OR);
        EntityInit.BATS.forEach(
                (registryObject) -> batsBuilder.addCriterion(registryObject.getId().getPath(), KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(registryObject.get())))
        );
        Advancement bats = batsBuilder.save(saver, Constants.MODID + ":bat_death");
        Advancement.Builder.advancement()
                .parent(bats)
                .display(ItemInit.BAT_WINGS.get(), Component.translatable("advancements.spookybats.massacre"), Component.translatable("advancements.spookybats.massacre.desc"), new ResourceLocation("textures/gui/advancements/backgrounds/husbandry.png"), FrameType.CHALLENGE, true, true, true)
                .addCriterion("bat_wings", InventoryChangeTrigger.TriggerInstance.hasItems(ItemInit.BAT_WINGS.get()))
                .save(saver, Constants.MODID + ":bat_massacre");
        Advancement.Builder builder = Advancement.Builder.advancement()
                .parent(bats)
                .requirements(RequirementsStrategy.AND)
                .display(ItemInit.PUMPKIN_CHOCOLATE_BAR.get(), Component.translatable("advancements.spookybats.candy_collection"), Component.translatable("advancements.spookybats.candy_collection.desc"), new ResourceLocation("textures/gui/advancements/backgrounds/husbandry.png"), FrameType.CHALLENGE, true, true, false);
        ItemInit.CANDY_LIST.forEach(
                (registryObject) -> builder.addCriterion(registryObject.getId().getPath(), InventoryChangeTrigger.TriggerInstance.hasItems(registryObject.get()))
        );
        builder.save(saver, Constants.MODID + ":candy_collection");
        Advancement.Builder turmoilBuilder = Advancement.Builder.advancement()
                .parent(bats)
                .display(ItemInit.STAFF_OF_TURMOIL.get(), Component.translatable("advancements.spookybats.winged_turmoil_kill"), Component.translatable("advancements.spookybats.winged_turmoil_kill.desc"), new ResourceLocation("textures/gui/advancements/backgrounds/husbandry.png"), FrameType.CHALLENGE, true, true, false)
                .addCriterion("kill_winged_turmoil", KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(EntityInit.WINGED_TURMOIL.get())));
        turmoilBuilder.save(saver, Constants.MODID + ":winged_turmoil_kill");
    }
}
