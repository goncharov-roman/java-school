package org.roman.model;

import java.util.List;

public class FullRecipe {

    private Recipe recipe;
    private List<Ingredient> ingredients;

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return "FullRecipe{" +
                "recipe=" + recipe +
                ", ingredients=" + ingredients +
                '}';
    }
}
