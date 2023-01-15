package pro.sky.recipeapp1.services;

import java.util.List;

public interface RecipeService {

    String getRecipe();
    String addRecipe (String name, String cookingTime, List<String> ingredients, List<String> instruction);
}
