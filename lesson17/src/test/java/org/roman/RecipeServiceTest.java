package org.roman;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.roman.model.FullRecipe;
import org.roman.model.Ingredient;
import org.roman.model.Recipe;
import org.roman.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@EntityScan("org.roman.model")
class RecipeServiceTest {

    @Autowired
    public RecipeService service;

    @Autowired
    public DataSource dataSource;

    private void populate() {
        Recipe recipe = new Recipe();
        recipe.setName("Recipe 1");
        recipe.setPhoto(new byte[]{1, 2, 3});

        List<Ingredient> ingredients = new ArrayList<>();

        Ingredient bread = new Ingredient();
        bread.setName("Bread");
        bread.setQuantity(1);

        Ingredient water = new Ingredient();
        water.setName("Water");
        water.setQuantity(0.2);

        ingredients.add(bread);
        ingredients.add(water);

        service.createRecipe(recipe, ingredients);
    }

    @AfterEach
    public void clear() {
        JdbcTestUtils.deleteFromTables(new JdbcTemplate(dataSource), "recipe", "ingredient");
    }

    @Test
    void testCreateRecipe() {
        populate();

        FullRecipe fullRecipe = service.findRecipe("Recipe 1");
        System.out.println(fullRecipe);

        assertEquals("Recipe 1", fullRecipe.getRecipe().getName());
        assertEquals(2, fullRecipe.getIngredients().size());
    }

    @Test
    void testDeleteRecipe() {
        populate();

        FullRecipe fullRecipe = service.findRecipe("Recipe 1");
        System.out.println(fullRecipe);

        assertTrue(nonNull(fullRecipe));

        service.deleteRecipe("Recipe 1");

        fullRecipe = service.findRecipe("Recipe 1");
        System.out.println(fullRecipe);

        assertTrue(isNull(fullRecipe.getRecipe()));
        assertTrue(isNull(fullRecipe.getIngredients()));
    }
}
