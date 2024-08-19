package com.microservicioStock.stockservices.adapters.driving.http.mapper;

import com.microservicioStock.stockservices.adapters.driving.http.dto.request.AddCategoryRequest;
import com.microservicioStock.stockservices.adapters.driving.http.dto.request.UpdateCategoryRequest;
import com.microservicioStock.stockservices.domain.model.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ICategoryRequestMapper {

    Category addCategoryRequestToCategory(AddCategoryRequest request);

    Category updateCategoryRequestToCategory(UpdateCategoryRequest request);
}
