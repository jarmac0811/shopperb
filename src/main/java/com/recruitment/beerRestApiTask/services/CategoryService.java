package com.recruitment.beerRestApiTask.services;

import com.recruitment.beerRestApiTask.domain.Category;
import com.recruitment.beerRestApiTask.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository CategoryRepository) {
        this.categoryRepository = CategoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category saveCategory(Category Category) {
        return categoryRepository.save(Category);
    }

    public void deleteCategory(String id) {
        categoryRepository.deleteById(Long.valueOf(id));
    }
}
