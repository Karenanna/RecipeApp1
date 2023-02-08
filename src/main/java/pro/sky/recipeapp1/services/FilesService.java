package pro.sky.recipeapp1.services;

import java.io.File;

public interface FilesService {

    boolean saveToFile(String json, String dataFileName);

    String readFromFile();

    File getDataFile();

    void cleanDataFile();
}
