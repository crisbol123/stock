package com.microservicioStock.stockservices.adapters.driving.http.mapper;

import com.microservicioStock.stockservices.adapters.driving.http.dto.response.CategoryResponse;
import com.microservicioStock.stockservices.domain.model.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ICategoryResponseMapper {

    CategoryResponse toCategoryResponse(Category category);

    List<CategoryResponse> toCategoryResponseList(List<Category> categories);
}