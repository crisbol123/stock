package com.microservicio_stock.stock_service.adapters.driving.http.adapter;

import com.microservicio_stock.stock_service.adapters.driving.http.dto.category.request.AddCategoryRequest;
import com.microservicio_stock.stock_service.adapters.driving.http.dto.category.request.FindAllCategoriesRequest;
import com.microservicio_stock.stock_service.adapters.driving.http.dto.category.response.CategoryResponse;
import com.microservicio_stock.stock_service.adapters.driving.http.dto.PagedResponse;
import com.microservicio_stock.stock_service.adapters.driving.http.mapper.category.ICategoryRequestMapper;
import com.microservicio_stock.stock_service.adapters.driving.http.mapper.category.ICategoryResponseMapper;
import com.microservicio_stock.stock_service.domain.api.ICategoryServicePort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CategoryAdapterHttp {
    private final ICategoryServicePort categoryServicePort;
    private final ICategoryRequestMapper iCategoryRequestMapper;
    private final ICategoryResponseMapper categoryResponseMapper;

    public void saveCategory(AddCategoryRequest addCategoryRequest) {
        categoryServicePort.saveCategory( iCategoryRequestMapper.addCategoryRequestToCategory(addCategoryRequest));
    }

    public PagedResponse<CategoryResponse> getPagedCategories(FindAllCategoriesRequest findAllCategoriesRequest) {
        PagedResponse pagedResponse = categoryServicePort.getPagedCategories(findAllCategoriesRequest.getPage(), findAllCategoriesRequest.getSize(), findAllCategoriesRequest.isAscOrderByName());
        return categoryResponseMapper.toCategoryResponsePagedResponse(pagedResponse);
    }
}
