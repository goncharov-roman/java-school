package org.roman.dao;

import org.roman.exception.TableNotExistsException;
import org.roman.model.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;

import javax.sql.DataSource;
import java.util.List;

public class IngredientDaoImpl implements IngredientDao {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcOperations parameterJdbcOperations;
    private final RowMapper<Ingredient> ingredientRowMapper;

    @Autowired
    public IngredientDaoImpl(DataSource dataSource, JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.parameterJdbcOperations = new NamedParameterJdbcTemplate(dataSource);
        this.ingredientRowMapper = (resultSet, i) -> {
            Ingredient ingredient = new Ingredient();
            ingredient.setId(resultSet.getInt(1));
            ingredient.setName(resultSet.getString(2));
            ingredient.setQuantity(resultSet.getDouble(3));
            ingredient.setRecipeId(resultSet.getInt(4));
            return ingredient;
        };

        jdbcTemplate.setExceptionTranslator(new TableNotExistsException());
    }

    @Override
    public List<Ingredient> getIngredientsByRecipeId(int id) {
        return jdbcTemplate.query("SELECT * FROM ingredient WHERE recipe_id = ?", this.ingredientRowMapper, id);
    }

    @Override
    public void createIngredients(List<Ingredient> ingredients) {
        parameterJdbcOperations.batchUpdate("INSERT INTO ingredient (name, quantity, recipe_id) VALUES (:name, :quantity, :recipeId)",
                SqlParameterSourceUtils.createBatch(ingredients));
    }

    @Override
    public void deleteIngredients(int recipeId) {
        jdbcTemplate.update("DELETE FROM ingredient WHERE recipe_id = ?", recipeId);
    }
}
