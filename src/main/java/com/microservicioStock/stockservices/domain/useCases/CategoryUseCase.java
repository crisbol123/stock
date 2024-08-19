package com.microservicioStock.stockservices.domain.useCases;

import com.microservicioStock.stockservices.domain.api.ICategoryServicePort;
import com.microservicioStock.stockservices.domain.model.Category;
import com.microservicioStock.stockservices.domain.spi.ICategoryPersistencePort;

import java.util.List;
import java.util.Optional;

public class CategoryUseCase implements ICategoryServicePort {

    private final ICategoryPersistencePort categoryPersistencePort;

    public CategoryUseCase(ICategoryPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }

    @Override
    public void saveCategory(Category category) {
        categoryPersistencePort.saveCategory(category);
    }

    @Override
    public Optional<Category> getCategoryById(Long id) {
        return categoryPersistencePort.getCategoryById(id);
    }

    @Override
    public List<Category> getAllCategories(Integer page, Integer size) {
        return categoryPersistencePort.getAllCategories(page, size);
    }

    @Override
    public Category updateCategory(Category category) {

        return categoryPersistencePort.updateCategory(category);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryPersistencePort.deleteCategory(id);
    }
}