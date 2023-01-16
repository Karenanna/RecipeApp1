package pro.sky.recipeapp1.services.impl;

import pro.sky.recipeapp1.model.Recipe;
import pro.sky.recipeapp1.services.RecipeService;

import java.util.HashMap;
import java.util.Map;

public class RecipeServiceImpl implements RecipeService {
     int counter;
     String recipe;
    private final Map<Integer, String> recipeMap = new HashMap<>();

    public Map<Integer, String> getRecipeMap() {
        return recipeMap;

    }


    @Override
    public String getRecipe() {
        return null;
    }
}