package com.microservicio_stock.stock_service.domain.spi;

import com.microservicio_stock.stock_service.domain.model.Category;

import java.util.List;


public interface ICategoryPersistencePort {

    void saveCategory(Category category);

    List<Category> getAllCategories(Integer page, Integer size, boolean ascOrderByName);

    long getTotalCategories();
}