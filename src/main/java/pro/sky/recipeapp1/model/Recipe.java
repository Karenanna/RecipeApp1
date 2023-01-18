package pro.sky.recipeapp1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Recipe {
    private String name;
    private int weight;
    private  String measureUnit;
    private String cookingTime;
    private List<Ingredients>  ingredients;
    private List <String> instruction;
}
