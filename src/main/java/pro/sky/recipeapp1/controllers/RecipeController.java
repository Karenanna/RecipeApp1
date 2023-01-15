package pro.sky.recipeapp1.controllers;

import org.springframework.web.bind.annotation.*;
import pro.sky.recipeapp1.services.RecipeService;

import java.util.List;

@RequestMapping("/recipe")
@RestController
public class RecipeController {
    private RecipeService  recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping("/add")
    public String addRecipe(@RequestParam("name") String name,
                            @RequestParam("weight") int weight,
                            @RequestParam("cookingTime") String cookingTime,
                            @RequestParam("ingredients") List<String> ingredients,
                            @RequestParam("instruction") List<String> instruction) {
        recipeService.addRecipe(name, cookingTime, ingredients, instruction);
        return "Рецепт" + name + "добавлен.";
    }

    @GetMapping("/id")
    public String getRecipe(@PathVariable("id") int id) {
        if (recipeService.getRecipe() != null) ;
        return recipeService.getRecipe();

    }
}
