package com.microservicio_stock.stock_service.domain.spi;

import com.microservicio_stock.stock_service.domain.util.PagedResponse;
import com.microservicio_stock.stock_service.domain.model.Category;


public interface ICategoryPersistencePort {

    void saveCategory(Category category);

    PagedResponse<Category> getPagedCategories(Integer page, Integer size, boolean ascOrderByName);

    Category getCategoryById(Long id);
}