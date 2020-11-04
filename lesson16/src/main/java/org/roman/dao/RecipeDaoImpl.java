package org.roman.dao;

import org.roman.exception.TableNotExistsException;
import org.roman.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.AbstractLobCreatingPreparedStatementCallback;
import org.springframework.jdbc.support.lob.LobCreator;
import org.springframework.jdbc.support.lob.LobHandler;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class RecipeDaoImpl implements RecipeDao {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcOperations parameterJdbcOperations;
    private final RowMapper<Recipe> recipeRowMapper;
    private final LobHandler lobHandler;

    @Autowired
    public RecipeDaoImpl(DataSource dataSource, LobHandler lobHandler, JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.parameterJdbcOperations = new NamedParameterJdbcTemplate(dataSource);
        this.lobHandler = lobHandler;
        this.recipeRowMapper = (resultSet, i) -> {
            Recipe recipe = new Recipe();
            recipe.setId(resultSet.getInt(1));
            recipe.setName(resultSet.getString(2));
            recipe.setPhoto(lobHandler.getBlobAsBytes(resultSet, 3));

            return recipe;
        };

        jdbcTemplate.setExceptionTranslator(new TableNotExistsException());
    }

    @Override
    public int createRecipe(Recipe recipe) {
        jdbcTemplate.execute("INSERT INTO recipe (name, photo) VALUES (?, ?)",
                new AbstractLobCreatingPreparedStatementCallback(lobHandler) {
                    @Override
                    protected void setValues(PreparedStatement preparedStatement, LobCreator lobCreator) throws SQLException {
                        preparedStatement.setString(1, recipe.getName());
                        lobCreator.setBlobAsBytes(preparedStatement, 2, recipe.getPhoto());
                    }
                });
        return findRecipeByName(recipe.getName()).getId();
    }

    @Override
    public List<Recipe> getRecipes() {
        return parameterJdbcOperations.query("SELECT * FROM recipe", this.recipeRowMapper);
    }

    @Override
    public Recipe findRecipeByName(String name) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM recipe WHERE name LIKE ?", this.recipeRowMapper, name);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void deleteRecipe(String name) {
        jdbcTemplate.update("DELETE FROM recipe WHERE name = ?", name);
    }
}
