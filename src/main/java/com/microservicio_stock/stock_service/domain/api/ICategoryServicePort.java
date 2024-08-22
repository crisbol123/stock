package com.microservicio_stock.stock_service.domain.api;

import com.microservicio_stock.stock_service.domain.model.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoryServicePort {
    void saveCategory(Category category);
    Optional<Category> getCategoryById(Long id);
    List<Category> getAllCategories(Integer page, Integer size, Boolean ascOrderByName);
    Category updateCategory(Category category);
    void deleteCategory(Long id);
}