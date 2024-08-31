package com.microservicio_stock.stock_service.adapters.driving.http.mapper.category;

import com.microservicio_stock.stock_service.adapters.driving.http.dto.category.request.AddCategoryRequest;
import com.microservicio_stock.stock_service.domain.api.ICategoryServicePort;
import com.microservicio_stock.stock_service.domain.model.Category;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ICategoryRequestMapper {

    Category addCategoryRequestToCategory(AddCategoryRequest request);

    @Named("idToCategory")
    default Category idToCategory(Long id, @Context ICategoryServicePort categoryServicePort) {
        return categoryServicePort.getCategoryById(id);
    }
}