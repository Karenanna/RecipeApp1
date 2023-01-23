package pro.sky.recipeapp1.services.impl;

import org.springframework.stereotype.Service;
import pro.sky.recipeapp1.model.Ingredients;

import java.util.Collection;
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

    @Override
    public Collection<Ingredients> getAll() {
        return ingredientsMap.values();
    }



    @Override
    public boolean deleteIngredients(int id) {
        return false;
    }

    public Ingredients removeRecipe(int id) {
        if (ingredientsMap.containsKey(id)) {
            return null;
        }
        return ingredientsMap.remove(id);
    }
}



