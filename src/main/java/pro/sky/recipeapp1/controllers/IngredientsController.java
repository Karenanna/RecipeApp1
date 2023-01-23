package pro.sky.recipeapp1.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.recipeapp1.model.Ingredients;
import pro.sky.recipeapp1.model.Recipe;
import pro.sky.recipeapp1.services.impl.IngredientsService;

import java.util.Collection;

@RestController
@RequestMapping("/ingredients")
@RequiredArgsConstructor
@Tag(name = "Рецепты", description = "CRUD- операции для работы с рецептами")
public class IngredientsController {

    private IngredientsService ingredientsService;

    public IngredientsController(IngredientsService ingredientsService) {
        this.ingredientsService = ingredientsService;
    }

    @PostMapping("/add")
    @Operation(summary = "Добавлен ингредиент")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Ингредиент добавлен")})
    public ResponseEntity<String> addIngredients(@RequestBody Ingredients ingredients){
       String id = String.valueOf(ingredientsService.addIngredient(ingredients));
        return ResponseEntity.ok(id);

    }
    @Operation(summary = "Поиск ингредиентов по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ингредиент был найден")})
    @Parameters(value = {@Parameter(name = "id", example = "1")})
    @GetMapping("/{id}")
    public ResponseEntity <Ingredients> getIngredients(@PathVariable int id) {
        Ingredients ingredients = ingredientsService.getIngredient(id);
        if (ingredients == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredients);

    }
    @PutMapping("/{id}")
    @Operation(summary = "Изменение ингредиентов по id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ингредиент изменен",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Ingredients.class)
                            )
                    }
            )
    })
    @Parameters(value = {
            @Parameter(name = "id",
                    example = "1")
    })
    public ResponseEntity<Ingredients> editIngredients(@PathVariable int id, @RequestBody  Ingredients ingredients) {
        Ingredients ingredients1 = ingredientsService.getIngredient(id);
        if (ingredients == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredients);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление ингредиентов по id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ингредиент удален"
            )
    })
    @Parameters(value = {@Parameter(name = "id", example = "1")})
    public ResponseEntity<Void> deleteIngredients(@PathVariable int id, @RequestBody Ingredients ingredients) {
        if (ingredientsService.deleteIngredients(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/all")
    @Operation(summary = "Получение всех ингредиентов", description = "Поиск производится без параметров")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Ингредиенты получены")})
    ResponseEntity <Collection<Ingredients>> getAllIngredients() {
        return ResponseEntity.ok(ingredientsService.getAll());
    }
}

