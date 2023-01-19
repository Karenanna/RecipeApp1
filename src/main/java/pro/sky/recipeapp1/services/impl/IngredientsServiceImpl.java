package pro.sky.recipeapp1.services.impl;

import org.springframework.stereotype.Service;
import pro.sky.recipeapp1.model.Ingredients;

import java.util.HashMap;
import java.util.Map;
@Service
public class IngredientsServiceImpl implements IngredientsService {
    private static Integer counter = 0;
    private final Map<Integer, Ingredients> ingredientsMap = new HashMap<>();
    @Override
    public Ingredients getIngredient(Integer id) {
        return ingredientsMap.get(id);
    }
    @Override
    public Ingredients addIngredient(Ingredients ingredient) {
        return ingredientsMap.put(counter++, ingredient);
    }
}


