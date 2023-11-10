package com.mictlanes.Arvideys.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mictlanes.Arvideys.Models.Category;
import com.mictlanes.Arvideys.Repository.CategoryRepository;


@Service
public class CategoryServices {

    private final CategoryRepository categoryRepository;

     @Autowired
     public CategoryServices(CategoryRepository categoryRepository) {
            this.categoryRepository = categoryRepository;
        }

     public List<Category> findAllCategorys() {
            return categoryRepository.findAll();
        }

        public Optional<Category> findCategoryById(Long id) {
            return categoryRepository.findById(id);
        }

        public Category saveCategory(Category category) {
            return categoryRepository.save(category);
        }

        public Optional<Category> updateCategory(Long id, Category categoryDetails) {
            return categoryRepository.findById(id).map(existingCategory -> {
                existingCategory.setCategory_name(categoryDetails.getCategory_name());
                return categoryRepository.save(existingCategory);
            });
        }

        public void deleteCategory(Long id) {
            categoryRepository.deleteById(id);
        }

}