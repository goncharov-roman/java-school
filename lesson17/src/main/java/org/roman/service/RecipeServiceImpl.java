package org.roman.service;

import org.roman.model.FullRecipe;
import org.roman.model.Ingredient;
import org.roman.model.Recipe;
import org.roman.repository.IngredientRepository;
import org.roman.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;

    @Autowired
    public RecipeServiceImpl(RecipeRepository recipeRepository, IngredientRepository ingredientRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public void createRecipe(Recipe recipe, List<Ingredient> ingredients) {
        Recipe saved = recipeRepository.save(recipe);
        ingredients.forEach(i -> i.setRecipe(saved));
        ingredientRepository.saveAll(ingredients);
    }

    @Override
    public FullRecipe findRecipe(String name) {
        FullRecipe fullRecipe = new FullRecipe();

        Recipe recipe = recipeRepository.findByNameLike(name);
        if (isNull(recipe)) {
            return new FullRecipe();
        }
        List<Ingredient> ingredients = ingredientRepository.findByRecipeId(recipe.getId());

        fullRecipe.setRecipe(recipe);
        fullRecipe.setIngredients(ingredients);

        return fullRecipe;
    }

    @Override
    public void deleteRecipe(String name) {
        Recipe recipe = recipeRepository.findByNameLike(name);
        recipeRepository.delete(recipe);
        ingredientRepository.deleteByRecipeId(recipe.getId());
    }
}
