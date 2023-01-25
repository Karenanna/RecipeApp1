package pro.sky.recipeapp1.services.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pro.sky.recipeapp1.services.FilesService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FilesServiceImpl implements FilesService {
    @Value("${path.of.data.file}")
    private String dataFilePath;
    @Value("${name.of.recipe.date.file}")
    private String dataFileNameRecipe;
    @Value("${name.of.ingredients.date.file}")
    private String dataFileNameIngredients;

    public boolean saveToFile(String json) {
        try {
            cleanDataFile();
            Files.writeString(Path.of(dataFilePath, dataFileNameRecipe, dataFileNameIngredients), json);
           return true;
        } catch (IOException e) {
            return false;

        }

    }

    public String readFromFile() {
        try {
           return Files.readString(Path.of(dataFilePath,  dataFileNameRecipe, dataFileNameIngredients));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    private boolean cleanDataFile() {
        try {
          Path path = Path.of(dataFilePath,  dataFileNameRecipe, dataFileNameIngredients);
            Files.deleteIfExists(path);
            Files.createFile(path);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
