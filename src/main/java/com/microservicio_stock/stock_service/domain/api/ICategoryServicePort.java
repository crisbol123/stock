package com.microservicio_stock.stock_service.domain.api;

import com.microservicio_stock.stock_service.domain.util.PagedResponse;
import com.microservicio_stock.stock_service.domain.model.Category;


public interface ICategoryServicePort {

    void saveCategory(Category category);

   PagedResponse<Category> getPagedCategories(Integer page, Integer size, Boolean ascOrderByName);

   Category getCategoryById(Long id);
}
