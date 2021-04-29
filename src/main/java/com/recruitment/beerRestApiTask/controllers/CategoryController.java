package com.recruitment.beerRestApiTask.controllers;

import com.recruitment.beerRestApiTask.domain.Category;
import com.recruitment.beerRestApiTask.services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
//@CrossOrigin(origins = {"http://localhost:4200"})
public class CategoryController {
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/all")
    @ResponseBody
    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.ok(this.categoryService.getAllCategories());
    }

    @PostMapping
    @ResponseBody
    public Category saveCategory(@RequestBody Category category) {
        return this.categoryService.saveCategory(category);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable String id) {
        this.categoryService.deleteCategory(id);
    }
}


