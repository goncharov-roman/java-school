package org.roman;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.roman.config.JdbcTemplateConfig;
import org.roman.dao.RecipeDao;
import org.roman.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.jdbc.JdbcTestUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringJUnitConfig(JdbcTemplateConfig.class)
class RecipeDaoTest {

    @Autowired
    public JdbcTemplate jdbcTemplate;

    @Autowired
    public RecipeDao recipeDao;

    @AfterEach
    public void clear() {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "recipe");
    }

    @Test
    void testCreateRecipe() {
        Recipe recipe = new Recipe();
        recipe.setName("Recipe 1");
        recipe.setPhoto(new byte[]{1, 2, 3});

        recipeDao.createRecipe(recipe);
        List<Recipe> recipes = recipeDao.getRecipes();
        System.out.println(recipes.toString());

        assertEquals(1, recipes.size());
    }

    @Test
    void testFindRecipe() {
        Recipe recipe = new Recipe();
        recipe.setName("Recipe 1");
        recipe.setPhoto(new byte[]{1, 2, 3});

        recipeDao.createRecipe(recipe);

        Recipe foundRecipe = recipeDao.findRecipeByName("%Recipe%");
        System.out.println(foundRecipe);

        assertEquals("Recipe 1", foundRecipe.getName());
    }

    @Test
    void testDeleteRecipe() {
        Recipe recipe = new Recipe();
        recipe.setName("Recipe 1");
        recipe.setPhoto(new byte[]{1, 2, 3});

        recipeDao.createRecipe(recipe);

        recipeDao.deleteRecipe("Recipe 1");
        List<Recipe> recipes = recipeDao.getRecipes();

        assertEquals(0, recipes.size());
    }
}
