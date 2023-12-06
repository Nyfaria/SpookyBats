package com.nyfaria.spookybats.datagen;

import com.nyfaria.spookybats.block.WoodCollection;
import com.nyfaria.spookybats.init.BlockInit;
import com.nyfaria.spookybats.init.ItemInit;
import com.nyfaria.spookybats.init.TagInit;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.StonecutterRecipe;

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
        planksFromLogs(recipeSaver, collection.planks().get(), TagInit.SPOOKY_OAK_LOGS_ITEM,4);
        woodFromLogs(recipeSaver, collection.log().get(), collection.log().get());
        woodenBoat(recipeSaver, ItemInit.SPOOKY_OAK_BOAT_ITEM.get(), collection.planks().get());
        chestBoat(recipeSaver, ItemInit.SPOOKY_OAK_CHEST_BOAT_ITEM.get(), collection.planks().get());
        hangingSign(recipeSaver, collection.hangingSign().get(), collection.sign().get());
        generateRecipes(recipeSaver,collection.getFamily());
    }
}
