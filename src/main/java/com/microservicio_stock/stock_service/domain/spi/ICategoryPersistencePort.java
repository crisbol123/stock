package com.microservicio_stock.stock_service.domain.spi;

import com.microservicio_stock.stock_service.domain.model.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoryPersistencePort {
    void saveCategory(Category category);
   Optional<Category> getCategoryById(Long id);
    List<Category> getAllCategories(Integer page, Integer size);
    Category updateCategory(Category category);
    void deleteCategory(Long id);
    boolean existsByName(String name);
}