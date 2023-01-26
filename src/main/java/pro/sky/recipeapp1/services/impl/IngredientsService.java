package pro.sky.recipeapp1.services.impl;

import pro.sky.recipeapp1.model.Ingredients;

import java.util.Collection;

public interface IngredientsService {

    Ingredients getIngredient(Integer id);

    Ingredients addIngredient(Ingredients ingredient);

    Collection<Ingredients> getAll();

    boolean deleteIngredients(int id);

    Ingredients removeIngredients(int id);


    void saveToFile();

    void readFromFileIngredients();
}




