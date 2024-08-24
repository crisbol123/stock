package com.microservicio_stock.stock_service.adapters.driving.http.mapper.category;

import com.microservicio_stock.stock_service.adapters.driving.http.dto.category.request.AddCategoryRequest;
import com.microservicio_stock.stock_service.adapters.driving.http.dto.category.request.UpdateCategoryRequest;
import com.microservicio_stock.stock_service.domain.model.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ICategoryRequestMapper {

    Category addCategoryRequestToCategory(AddCategoryRequest request);

    Category updateCategoryRequestToCategory(UpdateCategoryRequest request);
}
