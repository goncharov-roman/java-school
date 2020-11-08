package org.roman.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class FullRecipe {

    private Recipe recipe;
    private List<Ingredient> ingredients;
}
