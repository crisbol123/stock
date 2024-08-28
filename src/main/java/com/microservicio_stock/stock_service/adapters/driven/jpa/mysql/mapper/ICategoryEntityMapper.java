package com.microservicio_stock.stock_service.adapters.driven.jpa.mysql.mapper;

import com.microservicio_stock.stock_service.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.microservicio_stock.stock_service.domain.util.PagedResponse;
import com.microservicio_stock.stock_service.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ICategoryEntityMapper {

    Category toModel(CategoryEntity categoryEntity);

    CategoryEntity toEntity(Category category);

    List<Category> toModelList(List<CategoryEntity> categoryEntities);

    List<CategoryEntity> toEntityList(List<Category> categories);
@Mapping(target = "lastPage", source = "isLast")
@Mapping(target = "currentPage", source = "number")
    PagedResponse<Category> toPagedModel(Page<CategoryEntity> page, boolean isLast, int number);
}
