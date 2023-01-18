package pro.sky.recipeapp1.controllers;

import org.springframework.web.bind.annotation.*;
import pro.sky.recipeapp1.model.Recipe;
import pro.sky.recipeapp1.services.RecipeService;

import java.util.List;

@RequestMapping("/recipe")
@RestController
public class RecipeController {
    private RecipeService  recipeService;

    public RecipeController(RecipeService recipeService)
    {this.recipeService = recipeService;}


    @PostMapping("/add")
    public Recipe addRecipe(@RequestBody Recipe recipe){
       return RecipeService.addRecipe(recipe);

    }

    @GetMapping("/{id}")
    public Recipe getRecipe(@PathVariable int id) {
        return recipeService.getRecipe(id);
    }
}
