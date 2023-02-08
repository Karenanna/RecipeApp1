package pro.sky.recipeapp1.services.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pro.sky.recipeapp1.services.FilesService;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FilesServiceImpl implements FilesService {
    @Value("${path.to.data.file}")
    private String dataFilePath;
    @Value("${name.of.recipe.data.file}")
    private String dataFileName;


    @Override
    public boolean saveToFile(String json, String dataFileName) {
        Path path = Path.of(dataFilePath, dataFileName);
        try {
            cleanDataFile(dataFileName);
            Files.writeString(path, json);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;

        }

    }

    @Override
    public String readFromFile() {
        try {
            return Files.readString(Path.of(dataFilePath, dataFileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @Override
    public File getDataFile() {
        return new File(dataFilePath+ "/" + dataFileName);
    }


    private boolean cleanDataFile(String dataFileName) {
        try {
            Path path = Path.of(dataFilePath, dataFileName);
            Files.deleteIfExists(path);
            Files.createFile(path);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @PostConstruct
    private void init() {
        readFromFileIngredients();
    }

    private void readFromFileIngredients() {
    }
}
