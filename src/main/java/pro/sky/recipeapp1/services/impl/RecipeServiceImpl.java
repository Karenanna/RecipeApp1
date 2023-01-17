package pro.sky.recipeapp1.services.impl;

import org.springframework.stereotype.Service;
import pro.sky.recipeapp1.model.Recipe;
import pro.sky.recipeapp1.services.RecipeService;

import java.util.HashMap;
import java.util.Map;
@Service
public class RecipeServiceImpl implements RecipeService {


    private final Map<Integer, String> recipeMap = new HashMap<>();

    public Map<Integer, String> getRecipeMap() {
        return recipeMap;
    }



    public String getRecipe(Integer id) {
        return recipeMap.get(id);


    }

    @Override
    public String getRecipe() {
        return null;
    }

}