package pro.sky.recipeapp1.services.impl;

import pro.sky.recipeapp1.model.Ingredients;

import java.util.List;

public interface IngredientsService {


    Ingredients getIngredient(Integer id);

    Ingredients addIngredient(Ingredients ingredient);
}




