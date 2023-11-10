package com.mictlanes.Arvideys.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mictlanes.Arvideys.Models.Category;
import com.mictlanes.Arvideys.Services.CategoryServices;

@RequestMapping(path="/api/category")
@RestController
public class CategoryController {

	private final CategoryServices categoryServices;

    @Autowired
    public CategoryController(CategoryServices categoryServices) {
        this.categoryServices = categoryServices;
    }
    
 // GET all Categorys
    @GetMapping
    public List<Category> getAllCategorys() {
        return categoryServices.findAllCategorys();
    }

    // GET a single Category by id
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        return categoryServices.findCategoryById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
 // POST a new Category
    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        return categoryServices.saveCategory(category);
    }

    // PUT to update an existing Category
    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category categoryDetails) {
        return categoryServices.updateCategory(id, categoryDetails)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE a Category
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryServices.findCategoryById(id)
                .ifPresent(category -> categoryServices.deleteCategory(id));
        return ResponseEntity.ok().build();
    }
}