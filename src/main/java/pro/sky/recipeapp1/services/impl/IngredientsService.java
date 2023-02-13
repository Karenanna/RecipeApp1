package pro.sky.recipeapp1.services.impl;

import org.springframework.web.multipart.MultipartFile;
import pro.sky.recipeapp1.model.Ingredients;

import java.util.Collection;

public interface IngredientsService {

    Ingredients getIngredient(Integer id);

    Ingredients addIngredient(Ingredients ingredient);

    Collection<Ingredients> getAll();




    void saveToFile();

    void readFromFileIngredients();

    boolean deleteIngredients(int id);

    void importFile(MultipartFile file);
}




