package com.microservicio_stock.stock_service.adapters.driving.http.adapter;

import com.microservicio_stock.stock_service.adapters.driving.http.dto.category.request.AddCategoryRequest;
import com.microservicio_stock.stock_service.adapters.driving.http.dto.category.response.CategoryResponse;
import com.microservicio_stock.stock_service.adapters.driving.http.dto.PagedResponse;
import com.microservicio_stock.stock_service.adapters.driving.http.mapper.category.ICategoryRequestMapper;
import com.microservicio_stock.stock_service.adapters.driving.http.mapper.category.ICategoryResponseMapper;
import com.microservicio_stock.stock_service.domain.api.ICategoryServicePort;
import com.microservicio_stock.stock_service.domain.model.Category;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class CategoryAdapterHttp {
    private final ICategoryServicePort categoryServicePort;
    private final ICategoryRequestMapper iCategoryRequestMapper;
    private final ICategoryResponseMapper categoryResponseMapper;

    public void saveCategory(AddCategoryRequest addCategoryRequest) {
        categoryServicePort.saveCategory( iCategoryRequestMapper.addCategoryRequestToCategory(addCategoryRequest));
    }

    public PagedResponse<CategoryResponse> getAllCategories(Integer page, Integer size, Boolean ascOrderByName) {
        List<Category> categories = categoryServicePort.getAllCategories(page, size, ascOrderByName);
        long totalElements = categoryServicePort.getTotalCategories();
        int totalPages = (int) Math.ceil((double) totalElements / size);
        boolean lastPage = page >= totalPages - 1;

        List<CategoryResponse> categoryResponses = categoryResponseMapper.toCategoryResponseList(categories);

        return new PagedResponse<>(
                categoryResponses,
                page,
                totalPages,
                totalElements,
                lastPage,
                ascOrderByName
        );
    }
}
