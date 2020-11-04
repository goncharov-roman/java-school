package org.roman;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.roman.config.JdbcTemplateConfig;
import org.roman.dao.IngredientDao;
import org.roman.model.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.jdbc.JdbcTestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringJUnitConfig(JdbcTemplateConfig.class)
class IngredientDaoTest {

    @Autowired
    public JdbcTemplate jdbcTemplate;

    @Autowired
    public IngredientDao ingredientDao;

    @AfterEach
    public void clear() {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "ingredient");
    }

    @Test
    void testCreateIngredients() {
        List<Ingredient> ingredients = new ArrayList<>();

        Ingredient bread = new Ingredient();
        bread.setName("Bread");
        bread.setQuantity(1);
        bread.setRecipeId(0);

        Ingredient water = new Ingredient();
        water.setName("Water");
        water.setQuantity(0.2);
        water.setRecipeId(0);

        ingredients.add(bread);
        ingredients.add(water);

        ingredientDao.createIngredients(ingredients);

        List<Ingredient> ingredientsByRecipe0 = ingredientDao.getIngredientsByRecipeId(0);
        System.out.println(ingredientsByRecipe0);

        assertEquals(2, ingredientsByRecipe0.size());
    }

    @Test
    void testDeleteIngredients() {
        List<Ingredient> ingredients = new ArrayList<>();

        Ingredient bread = new Ingredient();
        bread.setName("Bread");
        bread.setQuantity(1);
        bread.setRecipeId(0);

        ingredients.add(bread);

        ingredientDao.createIngredients(ingredients);

        ingredientDao.deleteIngredients(0);
        List<Ingredient> ingredientsAfterDelete = ingredientDao.getIngredientsByRecipeId(0);

        assertEquals(0, ingredientsAfterDelete.size());
    }
}
