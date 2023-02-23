package pro.sky.recipeapp1.controllers;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pro.sky.recipeapp1.services.FilesService;
import pro.sky.recipeapp1.services.RecipeService;
import pro.sky.recipeapp1.services.impl.IngredientsService;

import java.io.*;

@RestController
@RequestMapping("/files")
public class FilesController {
    private final FilesService filesService;
    private final RecipeService recipeService;
    private final IngredientsService ingredientsService;

    public FilesController(FilesService filesService, RecipeService recipeService, IngredientsService ingredientsService) {
        this.filesService = filesService;
        this.recipeService = recipeService;
        this.ingredientsService = ingredientsService;
    }

    @GetMapping(value = "/export")
    public ResponseEntity<InputStreamResource> downloadDataFile() throws FileNotFoundException {
        File file = filesService.getDataFile();

        if (file.exists()) {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .contentLength(file.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"RecipeLog.json\"")
                    .body(resource);
        } else {
            return ResponseEntity.noContent().build();
        }
    }



    @GetMapping(value = "/exportRecipe", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InputStreamResource> downloadCreateRecipeInTxt() throws FileNotFoundException {
        File file = filesService.getDataFile();

        if (file.exists()) {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .contentLength(file.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"RecipeLog.json\"")
                    .body(resource);
        } else {
            return ResponseEntity.noContent().build();
        }
    }


    @PostMapping(value = "/importrecipe", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadRecipeDataFile(@RequestParam MultipartFile file) throws FileNotFoundException{
        recipeService.importFile(file);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/importingredients", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadIngredientsDataFile(@RequestParam MultipartFile file) throws FileNotFoundException{
        ingredientsService.importFile(file);
        return ResponseEntity.ok().build();
    }
}
