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
    public String addIngredients(@RequestBody Ingredients ingredients){
        IngredientsService IngredientsService = ingredientsService;
        return "Ингредиент добавлен";

    }

    @GetMapping("/{id}")
    public String getIngredients(@PathVariable() int id) {
        if (ingredientsService.getIngredientsService() != null) {
            return ingredientsService.getIngredientsService();
        }
        return null;
    }
}
