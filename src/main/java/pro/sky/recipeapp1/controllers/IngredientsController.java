package pro.sky.recipeapp1.controllers;

import org.springframework.web.bind.annotation.*;
import pro.sky.recipeapp1.model.Ingredients;
import pro.sky.recipeapp1.services.impl.IngredientsService;

@RestController
@RequestMapping("/ingredients")
public class IngredientsController {

    private IngredientsService ingredientsService;

    public IngredientsController(IngredientsService ingredientsService) {
        this.ingredientsService = ingredientsService;
    }

    @PostMapping("/add")
    public Ingredients addIngredients(@RequestBody Ingredients ingredients){
       return  IngredientsService.addIngredient(ingredients);

    }
    @GetMapping("/{id}")
    public Ingredients getIngredients(@PathVariable int id) {
        return ingredientsService.getIngredient(id);
    }
}
