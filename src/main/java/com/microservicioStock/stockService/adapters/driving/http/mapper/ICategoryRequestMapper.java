package com.microservicioStock.stockService.adapters.driving.http.mapper;

import com.microservicioStock.stockService.adapters.driving.http.dto.request.AddCategoryRequest;
import com.microservicioStock.stockService.adapters.driving.http.dto.request.UpdateCategoryRequest;
import com.microservicioStock.stockService.domain.model.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ICategoryRequestMapper {

    Category addCategoryRequestToCategory(AddCategoryRequest request);

    Category updateCategoryRequestToCategory(UpdateCategoryRequest request);
}
