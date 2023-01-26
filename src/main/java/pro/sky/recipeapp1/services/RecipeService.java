package pro.sky.recipeapp1.services;

import pro.sky.recipeapp1.model.Recipe;

import java.util.Collection;

public interface RecipeService {
    Recipe getRecipe(Integer id);

    Recipe addRecipe(Recipe recipe);

    Collection<Recipe> getAll();

    Recipe removeRecipe(int id);


    void readFromFile();

    boolean deleteRecipe(int id);
}
