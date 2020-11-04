package org.roman.service;

import org.roman.dao.IngredientDao;
import org.roman.dao.RecipeDao;
import org.roman.model.FullRecipe;
import org.roman.model.Ingredient;
import org.roman.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static java.util.Objects.isNull;

public class RecipeServiceImpl implements RecipeService {

    private final RecipeDao recipeDao;
    private final IngredientDao ingredientDao;

    @Autowired
    public RecipeServiceImpl(RecipeDao recipeDao, IngredientDao ingredientDao) {
        this.recipeDao = recipeDao;
        this.ingredientDao = ingredientDao;
    }

    @Override
    public void createRecipe(Recipe recipe, List<Ingredient> ingredients) {
        int id = recipeDao.createRecipe(recipe);
        ingredients.forEach(i -> i.setRecipeId(id));
        ingredientDao.createIngredients(ingredients);
    }

    @Override
    public FullRecipe findRecipe(String name) {
        FullRecipe fullRecipe = new FullRecipe();

        Recipe recipe = recipeDao.findRecipeByName('%' + name + '%');
        if (isNull(recipe)) {
            return new FullRecipe();
        }
        List<Ingredient> ingredients = ingredientDao.getIngredientsByRecipeId(recipe.getId());

        fullRecipe.setRecipe(recipe);
        fullRecipe.setIngredients(ingredients);

        return fullRecipe;
    }

    @Override
    public void deleteRecipe(String name) {
        int id = recipeDao.findRecipeByName(name).getId();
        recipeDao.deleteRecipe(name);
        ingredientDao.deleteIngredients(id);
    }
}
