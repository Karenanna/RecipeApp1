package pro.sky.recipeapp1.services;

public interface FilesService {

    boolean saveToFile(String json, String dataFileName);

    String readFromFile();

    boolean cleanDataFile(String dataFileName);

    String readFromFile(String dataFileName);
}
