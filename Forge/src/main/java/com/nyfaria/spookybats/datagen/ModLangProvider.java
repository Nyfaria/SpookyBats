package com.nyfaria.spookybats.datagen;

import com.google.common.collect.ImmutableMap;
import com.nyfaria.spookybats.Constants;
import com.nyfaria.spookybats.init.BlockInit;
import com.nyfaria.spookybats.init.EntityInit;
import com.nyfaria.spookybats.init.ItemInit;
import com.nyfaria.spookybats.registration.RegistryObject;
import net.minecraft.data.PackOutput;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.LanguageProvider;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ModLangProvider extends LanguageProvider {
    protected static final Map<String, String> REPLACE_LIST = ImmutableMap.of(
            "tnt", "TNT",
            "sus", "",
            "witchs","Witch's"
    );

    public ModLangProvider(PackOutput gen) {
        super(gen, Constants.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        ItemInit.ITEMS.getEntries().forEach(this::itemLang);
        EntityInit.ENTITIES.getEntries().forEach(this::entityLang);
        BlockInit.BLOCKS.getEntries().stream().filter(block -> !List.of(
                BlockInit.SPOOKY_OAK.wallSign()
        ).contains(block)).forEach(this::blockLang);
        add("itemGroup." + Constants.MODID + ".tab", Constants.MOD_NAME);
        add("advancements." + Constants.MODID + ".massacre", "Bat Massacre");
        add("advancements." + Constants.MODID + ".massacre.desc", "You MONSTER! You killed so many bats, and earned yourself a Pair of Bat Wings!");
        add("advancements." + Constants.MODID + ".bat_death", "Bat Death");
        add("advancements." + Constants.MODID + ".bat_death.desc", "You killed a bat, and earned yourself some candy!");
        add("advancements." + Constants.MODID + ".candy_collection", "Candy Collection");
        add("advancements." + Constants.MODID + ".candy_collection.desc", "Collect all the candy!");
    }

    protected void itemLang(RegistryObject<Item> entry) {
        if (!(entry.get() instanceof BlockItem) || entry.get() instanceof ItemNameBlockItem) {
            addItem(entry, checkReplace(entry));
        }
    }

    protected void blockLang(RegistryObject<Block> entry) {
        addBlock(entry, checkReplace(entry));
    }

    protected void entityLang(RegistryObject<EntityType<?>> entry) {
        addEntityType(entry, checkReplace(entry));
    }

    protected String checkReplace(RegistryObject<?> registryObject) {
        return Arrays.stream(registryObject.getId().getPath().split("_"))
                .map(this::checkReplace)
                .filter(s -> !s.isBlank())
                .collect(Collectors.joining(" "))
                .trim();
    }

    protected String checkReplace(String string) {
        return REPLACE_LIST.containsKey(string) ? REPLACE_LIST.get(string) : StringUtils.capitalize(string);
    }
}
