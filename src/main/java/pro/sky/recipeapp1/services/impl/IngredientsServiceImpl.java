package pro.sky.recipeapp1.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pro.sky.recipeapp1.model.Ingredients;
import pro.sky.recipeapp1.services.FilesService;

import javax.annotation.PostConstruct;
import javax.validation.Path;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.TreeMap;

@Service
public class IngredientsServiceImpl implements IngredientsService {
    private FilesService filesService;
    @Value("${name.of.ingredients.date.file}")
    private String dataFileName;

    @Value("${path.to.data.file}")
    private String  dataFilePath;


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
    public boolean deleteIngredients(int id) {
        if (ingredientsMap.containsKey(id)) {
            ingredientsMap.remove(id);
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
            ingredientsMap = new ObjectMapper().readValue(json, new TypeReference<TreeMap<Integer, Ingredients>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void importFile(MultipartFile file) {
        File dataFile = new File(dataFilePath + "/" + dataFileName);
        try (FileOutputStream fos = new FileOutputStream(dataFile)) {
            IOUtils.copy(file.getInputStream(), fos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}






