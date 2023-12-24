package com.nyfaria.batsgalore.datagen;

import com.nyfaria.batsgalore.block.WoodCollection;
import com.nyfaria.batsgalore.init.TagInit;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(PackOutput generator) {
        super(generator);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> recipeSaver) {
        WoodCollection.WOOD_COLLECTIONS.forEach(collection -> woodCollection(collection, recipeSaver));
    }
    protected void woodCollection(WoodCollection collection, Consumer<FinishedRecipe> recipeSaver){
        planksFromLogs(recipeSaver, collection.planks().get(),collection.logsTag(),4);
        woodFromLogs(recipeSaver, collection.log().get(), collection.log().get());
        woodenBoat(recipeSaver, collection.boat().get(), collection.planks().get());
        chestBoat(recipeSaver, collection.chestBoat().get(), collection.planks().get());
        hangingSign(recipeSaver, collection.hangingSign().get(), collection.sign().get());
        generateRecipes(recipeSaver,collection.getFamily());
    }
}
