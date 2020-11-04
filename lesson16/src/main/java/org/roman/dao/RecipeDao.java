package org.roman.dao;

import org.roman.model.Recipe;

import java.util.List;

public interface RecipeDao {

    int createRecipe(Recipe recipe);
    List<Recipe> getRecipes();
    Recipe findRecipeByName(String name);
    void deleteRecipe(String name);
}
