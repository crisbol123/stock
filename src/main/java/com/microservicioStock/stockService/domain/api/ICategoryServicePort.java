package com.microservicioStock.stockService.domain.api;

import com.microservicioStock.stockService.domain.model.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoryServicePort {
    void saveCategory(Category category);
    Optional<Category> getCategoryById(Long id);
    List<Category> getAllCategories(Integer page, Integer size);
    Category updateCategory(Category category);
    void deleteCategory(Long id);
}