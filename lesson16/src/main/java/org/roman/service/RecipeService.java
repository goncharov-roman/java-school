package org.roman.service;

import org.roman.model.FullRecipe;
import org.roman.model.Ingredient;
import org.roman.model.Recipe;

import java.util.List;

public interface RecipeService {

    void createRecipe(Recipe recipe, List<Ingredient> ingredients);
    FullRecipe findRecipe(String name);
    void deleteRecipe(String name);
}
