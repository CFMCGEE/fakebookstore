package com.fakebookstore.fakebookstore.controllers;

import com.fakebookstore.fakebookstore.models.Category;
import com.fakebookstore.fakebookstore.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/bookstore/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Object> makeCategory(@Valid @RequestBody Category category) {
        return categoryService.createCategory(category);
    }

    @GetMapping
    public ResponseEntity<Object> retrieveAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> retrieveACategory(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getCategory(id));
    }

    @GetMapping("/search")
    public ResponseEntity<Object> retrieveByBookName(@RequestParam("name") String name) {
        return ResponseEntity.ok(categoryService.search(name));
    }

    @GetMapping("/{id}/book")
    public ResponseEntity<Object> findCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.findCategoryById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> editCategory(@PathVariable Long id, @Valid @RequestBody Category category) {
        return ResponseEntity.ok(categoryService.updateCategory(id, category));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> removeCategory(@PathVariable Long id) {
        return ResponseEntity.accepted().body(categoryService.deleteCategory(id));
    }



}
