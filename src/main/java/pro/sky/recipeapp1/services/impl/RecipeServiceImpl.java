package pro.sky.recipeapp1.services.impl;

import org.springframework.stereotype.Service;
import pro.sky.recipeapp1.model.Recipe;
import pro.sky.recipeapp1.services.RecipeService;

import java.util.HashMap;
import java.util.Map;
@Service
public class RecipeServiceImpl implements RecipeService {

    private static Integer counter = 0;
    private final Map<Integer, Recipe> recipeMap = new HashMap<Integer, Recipe>();


    public Recipe getRecipe(int id) {
        return recipeMap.get(id);
    }

    @Override
    public Recipe getRecipe(Integer id) {
        return null;
    }

    @Override
    public Recipe addRecipe(Recipe recipe) {
        return recipeMap.put(counter++, recipe);
    }

    @Override
    public String getRecipe() {
        return null;
    }
}

