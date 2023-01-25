package pro.sky.recipeapp1.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pro.sky.recipeapp1.model.Recipe;
import pro.sky.recipeapp1.services.FilesService;
import pro.sky.recipeapp1.services.RecipeService;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

@Service
public class RecipeServiceImpl implements RecipeService {

    @Value("${name.of.recipe.date.file}")
    private String dataFileName;

    private FilesService filesService;

    public RecipeServiceImpl(FilesService filesService) {
        this.filesService = filesService;
    }

    private static Integer counter = 0;
    private TreeMap<Integer, Recipe> recipeMap = new TreeMap<>();

    @Override
    public Recipe getRecipe(Integer id) {

        return recipeMap.get(id);
    }

    @Override
    public Recipe addRecipe(Recipe recipe) {

        return recipeMap.put(counter++, recipe);
    }

    @Override
    public Collection<Recipe> getAll() {
        return recipeMap.values();

    }

    @Override
    public boolean deleteRecipe(int id) {
        return false;
    }

    @Override
    public Recipe removeRecipe(int id) {
        if (recipeMap.containsKey(id)) {
            saveToFile();
            return null;
        }
        return recipeMap.remove(id);
    }
    @Override
    public void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(recipeMap);
            filesService.saveToFile(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);

        }

    }
    @Override
    public void readFromFile() {
        String json = filesService.readFromFile();
        try {
          recipeMap =  new ObjectMapper().readValue(json, new TypeReference<TreeMap<Integer, Recipe>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}

