package pro.sky.recipeapp1.services;

import org.springframework.web.multipart.MultipartFile;
import pro.sky.recipeapp1.model.Recipe;

import java.io.File;
import java.util.Collection;

public interface RecipeService {
    Recipe getRecipe(Integer id);

    Recipe addRecipe(Recipe recipe);

    Collection<Recipe> getAll();


    boolean deleteRecipe(int id);

    void readFromFile();


    void readFromFileRecipe();


    void importFile(MultipartFile file);
}
