package pro.sky.recipeapp1.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class Recipe {
    private String name;
    private int weight;
    private  String measureUnit;
    private String cookingTime;
    private List<String>  ingredients;
    private List <String> instruction;
}
