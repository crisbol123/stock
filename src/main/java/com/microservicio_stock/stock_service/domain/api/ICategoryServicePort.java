package com.microservicio_stock.stock_service.domain.api;

import com.microservicio_stock.stock_service.domain.model.Category;

import java.util.List;


public interface ICategoryServicePort {

    void saveCategory(Category category);

    List<Category> getAllCategories(Integer page, Integer size, Boolean ascOrderByName);

    long getTotalCategories();
}
