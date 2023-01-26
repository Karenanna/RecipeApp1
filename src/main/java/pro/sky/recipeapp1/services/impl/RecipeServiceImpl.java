package pro.sky.recipeapp1.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pro.sky.recipeapp1.model.Recipe;
import pro.sky.recipeapp1.services.FilesService;
import pro.sky.recipeapp1.services.RecipeService;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.TreeMap;

@Service
public class RecipeServiceImpl implements RecipeService {

    @Value("${path.of.recipe.date.file}")
    private String dataFilePath;
    @Value("${name.of.recipe.date.file}")
    private String dataFileName;

    private FilesService filesService;

    public RecipeServiceImpl(FilesService filesService) {
        this.filesService = filesService;
    }

    @PostConstruct
    private void init() {
        readFromFile();
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
    public Recipe removeRecipe(int id) {
        if (recipeMap.containsKey(id)) {
            saveToFile();
            return null;
        }
        return recipeMap.remove(id);
    }

    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(recipeMap);
            filesService.saveToFile(json, dataFileName);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);

        }

    }

    public void readFromFile() {
        String json = filesService.readFromFile(dataFileName);
        try {
            recipeMap = new ObjectMapper().readValue(json, new TypeReference<TreeMap<Integer, Recipe>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteRecipe(int id) {
        return false;
    }
}




