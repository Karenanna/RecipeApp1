package pro.sky.recipeapp1.controllers;

import org.springframework.web.bind.annotation.*;
import pro.sky.recipeapp1.model.Recipe;
import pro.sky.recipeapp1.services.RecipeService;

import java.util.List;

@RequestMapping("/recipe")
@RestController
public class RecipeController {
    private RecipeService  recipeService;

    public RecipeController(RecipeService recipeService) {this.recipeService = recipeService;}


    @PostMapping("/add")
    public String addRecipe(@RequestBody Recipe recipe){
        RecipeService RecipeService = recipeService;
        return "Рецепт добавлен.";
    }

    @GetMapping("/id")
    public String getRecipe(@PathVariable("id") int id) {
        if (recipeService.getRecipe() != null) ;
        return recipeService.getRecipe();

    }
}
