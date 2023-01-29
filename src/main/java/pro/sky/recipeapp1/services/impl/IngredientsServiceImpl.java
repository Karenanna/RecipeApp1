package pro.sky.recipeapp1.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pro.sky.recipeapp1.model.Ingredients;
import pro.sky.recipeapp1.services.FilesService;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.TreeMap;

@Service
public class IngredientsServiceImpl implements IngredientsService {
    @Value("${path.of.ingredients.date.file}")
    private String dataFilePath;
    @Value("${name.of.ingredients.date.file}")
    private String dataFileName;

    private FilesService filesService;

    public IngredientsServiceImpl(FilesService filesService) {

        this.filesService = filesService;
    }
   @PostConstruct
    private void init() {
        readFromFileIngredients();

    }

    private static Integer counter = 0;
    private TreeMap<Integer, Ingredients> ingredientsMap = new TreeMap<>();

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
    public boolean removeIngredients(int id) {
        return false;
    }


    @Override
    public boolean deleteIngredients(int id) {
        if (ingredientsMap.containsKey(id)) {
            ingredientsMap.remove(id);
            saveToFile();
            return true;
        }
        return false;
    }

    @Override
    public void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(ingredientsMap);
            filesService.saveToFile(json, dataFileName);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);

        }

    }
    @Override
    public void readFromFileIngredients() {
        String json = filesService.readFromFile();
        try {
            ingredientsMap =  new ObjectMapper().readValue(json, new TypeReference<TreeMap<Integer, Ingredients>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}




