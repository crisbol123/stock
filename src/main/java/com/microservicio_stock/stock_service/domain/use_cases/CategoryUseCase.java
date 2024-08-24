package com.microservicio_stock.stock_service.domain.use_cases;

import com.microservicio_stock.stock_service.domain.api.ICategoryServicePort;
import com.microservicio_stock.stock_service.domain.model.Category;
import com.microservicio_stock.stock_service.domain.spi.ICategoryPersistencePort;

import java.util.List;


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
    public List<Category> getAllCategories(Integer page, Integer size, Boolean ascOrderByName) {
        return categoryPersistencePort.getAllCategories(page, size, ascOrderByName);
    }

    @Override
    public long getTotalCategories() {
        return categoryPersistencePort.getTotalCategories();
    }


}
