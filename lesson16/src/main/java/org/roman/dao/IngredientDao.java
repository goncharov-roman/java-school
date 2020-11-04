package org.roman.dao;

import org.roman.model.Ingredient;

import java.util.List;

public interface IngredientDao {

    List<Ingredient> getIngredientsByRecipeId(int id);
    void createIngredients(List<Ingredient> ingredients);
    void deleteIngredients(int recipeId);
}
