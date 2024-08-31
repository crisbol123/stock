package com.microservicio_stock.stock_service.adapters.driven.jpa.mysql.mapper;
import com.microservicio_stock.stock_service.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.microservicio_stock.stock_service.domain.util.PagedResponse;
import com.microservicio_stock.stock_service.domain.model.Article;
import com.microservicio_stock.stock_service.adapters.driven.jpa.mysql.entity.ArticleEntity;
import com.microservicio_stock.stock_service.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IArticleEntityMapper {
    Article toModel(ArticleEntity entity);
    List<Article> toModelList(List<ArticleEntity> entities);
    ArticleEntity toEntity(Article model);
    Category toModel(CategoryEntity categoryEntity);
    @Mapping(target = "lastPage", source = "isLast")
    @Mapping(target = "currentPage", source = "number")
    PagedResponse<Article> toPagedModel(Page<ArticleEntity> page, boolean isLast, int number);
}