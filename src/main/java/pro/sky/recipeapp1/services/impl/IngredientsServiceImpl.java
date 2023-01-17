package pro.sky.recipeapp1.services.impl;

import org.springframework.stereotype.Service;
import pro.sky.recipeapp1.model.Ingredients;

import java.util.HashMap;
import java.util.Map;
@Service
public class IngredientsServiceImpl implements IngredientsService{

    private final Map<Integer, Ingredients> ingredientsMap = new HashMap<>();

    public Map<Integer, Ingredients> getIngredientsMapMap() {
        return ingredientsMap;
    }

       public Ingredients getIngredients(Integer id) {
        return ingredientsMap.get(id);

    }


    @Override
    public String getIngredients() {
        return null;
    }

    @Override
    public String getIngredientsService() {
        return null;
    }
}
