package pro.sky.recipeapp1.controllers;

import org.springframework.web.bind.annotation.*;
import pro.sky.recipeapp1.services.impl.IngredientsService;

@RestController
@RequestMapping("/ingredients")
public class IngredientsController {

    private IngredientsService ingredientsService;

    public IngredientsController(IngredientsService ingredientsService) {
        this.ingredientsService = ingredientsService;
    }

    @PostMapping("/add")
    public String addIngredients(@RequestParam("name") String name,
                                 @RequestParam("count") int count,
                                 @RequestParam("measure") String measure) {
        ingredientsService.addIngredients(name, count, measure);
        return "Ингредиент" + name + " " + count + " " + measure + "добавлен";

    }

    @GetMapping("/id")
    public String getIngredients(@PathVariable() int id) {
        if (ingredientsService.getIngredientsService() != null) {
            return ingredientsService.getIngredientsService();
        }
        return null;
    }
}
