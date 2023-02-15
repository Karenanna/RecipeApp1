package pro.sky.recipeapp1.services;

import java.io.File;
import java.nio.file.Path;

public interface FilesService {

    boolean saveToFile(String json, String dataFileName);

    String readFromFile();

    File getDataFile();

    Path createTempFile(String suffix);
}
