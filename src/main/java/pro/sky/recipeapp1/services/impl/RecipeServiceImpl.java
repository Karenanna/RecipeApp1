package pro.sky.recipeapp1.services.impl;

import org.springframework.stereotype.Service;
import pro.sky.recipeapp1.model.Recipe;
import pro.sky.recipeapp1.services.RecipeService;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
@Service
public class RecipeServiceImpl implements RecipeService {

    private static Integer counter = 0;
    private final Map<Integer, Recipe> recipeMap = new HashMap<>();

    @Override
    public Recipe getRecipe(Integer id) {

        return recipeMap.get(id);
    }

    @Override
    public Recipe addRecipe(Recipe recipe) {

        return recipeMap.put(counter++, recipe);
    }

    @Override
    public Collection<Recipe> getAll() {
        return recipeMap.values();

    }

    @Override
    public boolean deleteRecipe(int id) {
        return false;
    }

    @Override
    public Recipe removeRecipe(int id) {
        if (recipeMap.containsKey(id)) {
            return null;
        }
        return recipeMap.remove(id);
    }
}

