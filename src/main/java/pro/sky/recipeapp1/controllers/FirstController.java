package pro.sky.recipeapp1.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {
    @GetMapping
    public String helloWorld() {
        return "Старт";
    }
    @GetMapping("/path/to/info")
    public String info () {
        return "Рецепты";
    }
}
