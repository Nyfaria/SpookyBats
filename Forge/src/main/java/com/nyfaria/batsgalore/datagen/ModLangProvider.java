package com.nyfaria.batsgalore.datagen;

import com.google.common.collect.ImmutableMap;
import com.nyfaria.batsgalore.Constants;
import com.nyfaria.batsgalore.init.BlockInit;
import com.nyfaria.batsgalore.init.entity.EntityInit;
import com.nyfaria.batsgalore.init.ItemInit;
import com.nyfaria.batsgalore.registration.RegistryObject;
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
                BlockInit.SPOOKY_OAK.wallSign(),
                BlockInit.WHITE_PINE.wallSign(),
                BlockInit.SPOOKY_OAK.hangingWallSign(),
                BlockInit.WHITE_PINE.hangingWallSign()
        ).contains(block)).forEach(this::blockLang);
        add("itemGroup." + Constants.MODID + ".tab", Constants.MOD_NAME);
        add("advancements." + Constants.MODID + ".massacre", "Bat Massacre");
        add("advancements." + Constants.MODID + ".massacre.desc", "You MONSTER! You killed so many bats and earned yourself a pair of Bat Wings!");
        add("advancements." + Constants.MODID + ".bat_death", "Bat Death");
        add("advancements." + Constants.MODID + ".bat_death.desc", "You killed some bats and earned yourself some candy!");
        add("advancements." + Constants.MODID + ".candy_collection", "Candy Collection");
        add("advancements." + Constants.MODID + ".candy_collection.desc", "Collect all the candy!");
        add("biome.batsgalore.spooky_oak_biome", "Spooky Oak Biome");
//        add("item." + Constants.MODID + ".staff_of_turmoil.description", "Right click almost any block and then right click an entity!");
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
