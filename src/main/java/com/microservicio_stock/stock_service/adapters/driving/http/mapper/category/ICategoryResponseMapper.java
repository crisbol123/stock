package com.microservicio_stock.stock_service.adapters.driving.http.mapper.category;

import com.microservicio_stock.stock_service.adapters.driving.http.dto.category.response.CategoryResponse;
import com.microservicio_stock.stock_service.domain.model.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ICategoryResponseMapper {

    CategoryResponse toCategoryResponse(Category category);

    List<CategoryResponse> toCategoryResponseList(List<Category> categories);
}