package org.roman.config;

import org.roman.dao.IngredientDao;
import org.roman.dao.IngredientDaoImpl;
import org.roman.dao.RecipeDao;
import org.roman.dao.RecipeDaoImpl;
import org.roman.service.RecipeService;
import org.roman.service.RecipeServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.lob.LobHandler;

import javax.sql.DataSource;

@Configuration
@Import(DataConfiguration.class)
public class JdbcTemplateConfig {

    @Bean
    public RecipeDao recipeDao(DataSource dataSource, LobHandler lobHandler, JdbcTemplate jdbcTemplate) {
        return new RecipeDaoImpl(dataSource, lobHandler, jdbcTemplate);
    }

    @Bean
    public IngredientDao ingredientDao(DataSource dataSource, JdbcTemplate jdbcTemplate) {
        return new IngredientDaoImpl(dataSource, jdbcTemplate);
    }

    @Bean
    public RecipeService recipeService(RecipeDao recipeDao, IngredientDao ingredientDao) {
        return new RecipeServiceImpl(recipeDao, ingredientDao);
    }
}
