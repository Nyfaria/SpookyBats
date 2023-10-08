package com.nyfaria.spookybats.datagen;

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
        Advancement bats = Advancement.Builder.advancement()
                .display(ItemInit.WITCHES_BREW.get(), Component.translatable("advancements.spookybats.bat_death"), Component.translatable("advancements.spookybats.bat_death.desc"), new ResourceLocation("textures/gui/advancements/backgrounds/husbandry.png"), FrameType.TASK, true, true, false)
                .requirements(RequirementsStrategy.OR)
                .addCriterion("pumpkin_bat", KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(EntityInit.PUMPKIN_BAT.get())))
                .addCriterion("witch_bat", KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(EntityInit.WITCH_BAT.get())))
                .addCriterion("herobrine_bat", KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(EntityInit.HEROBRINE_BAT.get())))
                .addCriterion("skeleton_bat", KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(EntityInit.SKELETON_BAT.get())))
                .addCriterion("creeper_bat", KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(EntityInit.CREEPER_BAT.get())))
                .addCriterion("ghost_bat", KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(EntityInit.GHOST_BAT.get())))
                .addCriterion("sculk_bat", KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(EntityInit.SCULK_BAT.get())))
                .addCriterion("steve_bat", KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(EntityInit.STEVE_BAT.get())))
                .addCriterion("alex_bat", KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(EntityInit.ALEX_BAT.get())))
                .addCriterion("wither_skeleton_bat", KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(EntityInit.WITHER_SKELETON_BAT.get())))
                .addCriterion("undead_bat", KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(EntityInit.UNDEAD_BAT.get())))
                .addCriterion("player_bat", KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(EntityInit.PLAYER_BAT.get())))
                .addCriterion("void_bat", KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(EntityInit.VOID_BAT.get())))
                .save(saver, "spookybats:bat_death");
        Advancement.Builder.advancement()
                .parent(bats)
                .display(ItemInit.BAT_WINGS.get(), Component.translatable("advancements.spookybats.massacre"), Component.translatable("advancements.spookybats.massacre.desc"), new ResourceLocation("textures/gui/advancements/backgrounds/husbandry.png"), FrameType.CHALLENGE, true, true, true)
                .addCriterion("bat_wings", InventoryChangeTrigger.TriggerInstance.hasItems(ItemInit.BAT_WINGS.get()))
                .save(saver, "spookybats:bat_massacre");
        Advancement.Builder builder = Advancement.Builder.advancement()
                .parent(bats)
                .requirements(RequirementsStrategy.AND)
                .display(ItemInit.PUMPKIN_CHOCOLATE_BAR.get(), Component.translatable("advancements.spookybats.candy_collection"), Component.translatable("advancements.spookybats.candy_collection.desc"), new ResourceLocation("textures/gui/advancements/backgrounds/husbandry.png"), FrameType.CHALLENGE, true, true, false);
        ItemInit.CANDY_LIST.forEach(
                (registryObject) -> builder.addCriterion(registryObject.getId().getPath(), InventoryChangeTrigger.TriggerInstance.hasItems(registryObject.get()))
        );
        builder.save(saver, "spookybats:candy_collection");
    }
}
