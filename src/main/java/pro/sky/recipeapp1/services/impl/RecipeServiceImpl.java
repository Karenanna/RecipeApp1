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
import pro.sky.recipeapp1.model.Recipe;
import pro.sky.recipeapp1.services.FilesService;
import pro.sky.recipeapp1.services.RecipeService;



import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
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
    public void importFile(MultipartFile file) {
        @PostMapping(value = "/import/{dataNameFile}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
                public ResponseEntity<Void> upLoadeFiles(@PathVariable String dataFileName, @RequestParam MultipartFile file){
            filesService.cleanFile(dataFileName);
            File dataFile = filesService.getDataFile(dataFileName);
            try (FileOutputStream fos = new FileOutputStream(dataFile)){
                IOUtils.copy(file.getInputStream(), fos);
                return ResponseEntity.ok().build();
            } catch (IOException e){
                e.printStackTrace();
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
}




