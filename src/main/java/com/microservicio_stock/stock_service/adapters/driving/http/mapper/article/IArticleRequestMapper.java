package com.microservicio_stock.stock_service.adapters.driving.http.mapper.article;
import com.microservicio_stock.stock_service.adapters.driving.http.dto.article.request.AddArticleRequest;
import com.microservicio_stock.stock_service.domain.api.IArticleServicePort;
import com.microservicio_stock.stock_service.domain.api.ICategoryServicePort;
import com.microservicio_stock.stock_service.domain.api.IMarkServicePort;
import com.microservicio_stock.stock_service.domain.model.Article;
import com.microservicio_stock.stock_service.domain.model.Category;
import com.microservicio_stock.stock_service.domain.model.Mark;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IArticleRequestMapper {

    @Mapping(target = "categories", source = "categoryIds", qualifiedByName = "mapCategoryIds")
    @Mapping(target = "mark", source = "markId", qualifiedByName = "mapMarkId")
    Article addArticleRequestToArticle(AddArticleRequest request, @Context ICategoryServicePort categoryServicePort, @Context IMarkServicePort markServicePort);

    @Named("mapCategoryIds")
    default List<Category> mapCategoryIds(List<Long> categoryIds, @Context ICategoryServicePort categoryServicePort) {
        return categoryIds.stream().map(categoryServicePort::getCategoryById).toList();
    }

    @Named("mapMarkId")
    default Mark mapMarkId(Long markId, @Context IMarkServicePort markServicePort) {
        return markServicePort.getMarkById(markId);
    }
}

