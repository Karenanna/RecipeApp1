package pro.sky.recipeapp1.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.recipeapp1.model.Ingredients;
import pro.sky.recipeapp1.services.impl.IngredientsService;

import java.util.Collection;

@RestController
@RequestMapping("/ingredients")
public class IngredientsController {

    private IngredientsService ingredientsService;

    public IngredientsController(IngredientsService ingredientsService) {
        this.ingredientsService = ingredientsService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addIngredients(@RequestBody Ingredients ingredients){
       String id = String.valueOf(ingredientsService.addIngredient(ingredients));
        return ResponseEntity.ok(id);

    }
    @GetMapping("/{id}")
    public ResponseEntity <Ingredients> getIngredients(@PathVariable int id) {
        Ingredients ingredients = ingredientsService.getIngredient(id);
        if (ingredients == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredients);

    }
    @PutMapping("/{id}")
    public ResponseEntity<Ingredients> editIngredients(@PathVariable int id, @RequestBody  Ingredients ingredients) {
        Ingredients ingredients1 = IngredientsService.getIngredients(id);
        if (ingredients == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredients);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIngredients(@PathVariable int id, @RequestBody Ingredients ingredients) {
        if (ingredientsService.deleteIngredients(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/All")
    public Collection<Ingredients> getAllIngredients() {
        return ingredientsService.getAll();
    }
}

