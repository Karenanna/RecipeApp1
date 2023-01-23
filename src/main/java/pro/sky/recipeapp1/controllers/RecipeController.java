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
import pro.sky.recipeapp1.services.RecipeService;

import java.util.Collection;

@RequestMapping("/recipe")
@RestController
@RequiredArgsConstructor
@Tag(name = "Рецепты", description = "CRUD- операции для работы с рецептами")
public class RecipeController {
    private RecipeService recipeService;



    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }



    @PostMapping("/add")
    @Operation(summary = "Добавление рецепта")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Рецепт добавлен")})
    public ResponseEntity<String> addRecipe(@RequestBody Recipe recipe) {
        String id = String.valueOf(recipeService.addRecipe(recipe));
        return ResponseEntity.ok(id);

    }
    @Operation(summary = "Поиск рецептов по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Рецепт был найден")})
    @Parameters(value = {@Parameter(name = "id", example = "1")})
    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipe(@PathVariable int id) {
        Recipe recipe = recipeService.getRecipe(id);
        if (recipe == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipe);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Изменение рецептов по id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Рецепт изменен",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Recipe.class)
                            )
                    }
            )
    })
    @Parameters(value = {
            @Parameter(name = "id",
            example = "1")
    })
    public ResponseEntity<Recipe> editRecipe(@PathVariable int id, @RequestBody Recipe recipe) {

        Recipe recipe1 = recipeService.getRecipe(id);
        if (recipe == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipe);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление рецептов по id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Рецепт удален"
            )
    })
    @Parameters(value = {@Parameter(name = "id", example = "1")})
    public ResponseEntity<Void> deleteRecipe(@PathVariable int id, @RequestBody Recipe recipe) {
        if (recipeService.deleteRecipe(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/all")
    @Operation(summary = "Получение всех рецептов", description = "Поиск производится без параметров")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Рецепты получены")})
    ResponseEntity<Collection<Recipe>>getAllRecipe(){
       return ResponseEntity.ok(recipeService.getAll());
    }

}
