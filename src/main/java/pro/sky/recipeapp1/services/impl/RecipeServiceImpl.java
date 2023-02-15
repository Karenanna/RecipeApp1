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
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.TreeMap;

@Service
public class RecipeServiceImpl implements RecipeService {

    @Value("${path.of.recipe.date.file}")
    private String dataFileName;

    private FilesService filesService;

    public RecipeServiceImpl(FilesService filesService) {
        this.filesService = filesService;
    }

    @PostConstruct
    private void init() {
        readFromFileRecipe();
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
        if (recipeMap.containsKey(id)) {
            recipeMap.remove(id);
            saveToFile();
            return true;
        }
        return false;
    }


    @Override
    public void readFromFile() {

    }

    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(recipeMap);
            filesService.saveToFile(json, dataFileName);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);

        }

    }

    public void readFromFileRecipe() {
        String json = filesService.readFromFile();
        try {
            recipeMap = new ObjectMapper().readValue(json, new TypeReference<TreeMap<Integer, Recipe>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Path createRecipeReport(Recipe recipe) throws IOException {
        LinkedHashMap<Integer, Recipe> recipeReport = recipe.getOrDefault(recipe, new LinkedHashMap<>());
        Path path = filesService.createTempFile("report");
        for (Recipe recipe1 : recipeReport.values()) {
            try (Writer writer = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
                writer.append(recipe.getName() + ": " + recipe.getMeasureUnit() + "грам");
                writer.append("\n");
            }
        }
        return path;
    }
}




