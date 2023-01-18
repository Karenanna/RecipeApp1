package pro.sky.recipeapp1.services;

import pro.sky.recipeapp1.model.Recipe;

import java.util.List;

public interface RecipeService {
    Recipe getRecipe(Integer id);

    static Recipe addRecipe(Recipe recipe) {
    }
}
