package pro.sky.recipeapp1.services.impl;

import org.springframework.stereotype.Service;
import pro.sky.recipeapp1.model.Ingredients;

import java.util.HashMap;
import java.util.Map;
@Service
public class IngredientsServiceImpl implements IngredientsService {
    private static Integer counter = 0;
    private final Map<Integer, Ingredients> ingredientsMap = new HashMap<>();


    public Ingredients getIngredient(int id) {
        return ingredientsMap.get(id);
    }


    public Ingredients addIngredients(Ingredients ingredient) {
        return ingredientsMap.put(counter++, ingredient);

    }

    @Override
    public Ingredients getIngredient(Integer id) {
        return null;
    }

    @Override
    public Ingredients getIngredient(Ingredients ingredient) {
        return null;
    }

    @Override
    public String getIngredientsService(int id) {
        return null;
    }
}


