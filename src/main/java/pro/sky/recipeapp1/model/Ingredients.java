package pro.sky.recipeapp1.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Ingredients {
    private String name;
    private int count;
    private  String measure;
}
