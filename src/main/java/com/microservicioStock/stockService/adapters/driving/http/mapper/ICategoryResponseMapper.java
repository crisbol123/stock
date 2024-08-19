package com.microservicioStock.stockService.adapters.driving.http.mapper;

import com.microservicioStock.stockService.adapters.driving.http.dto.response.CategoryResponse;
import com.microservicioStock.stockService.domain.model.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ICategoryResponseMapper {

    CategoryResponse toCategoryResponse(Category category);

    List<CategoryResponse> toCategoryResponseList(List<Category> categories);
}