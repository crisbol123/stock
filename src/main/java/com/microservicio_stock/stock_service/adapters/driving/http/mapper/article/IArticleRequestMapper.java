package com.microservicio_stock.stock_service.adapters.driving.http.mapper.article;

import com.microservicio_stock.stock_service.adapters.driving.http.dto.article.request.AddArticleRequest;
import com.microservicio_stock.stock_service.adapters.driving.http.mapper.category.ICategoryRequestMapper;
import com.microservicio_stock.stock_service.adapters.driving.http.mapper.mark.IMarkRequestMapper;
import com.microservicio_stock.stock_service.domain.api.ICategoryServicePort;
import com.microservicio_stock.stock_service.domain.api.IMarkServicePort;
import com.microservicio_stock.stock_service.domain.model.Article;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.Context;

@Mapper(componentModel = "spring", uses = {ICategoryRequestMapper.class, IMarkRequestMapper.class})
public interface IArticleRequestMapper {

    @Mapping(target = "categories", source = "categoryIds", qualifiedByName = "idToCategory")
    @Mapping(target = "mark", source = "markId", qualifiedByName = "idToMark")
    @Mapping(target = "id", ignore = true)
    Article addArticleRequestToArticle(AddArticleRequest request, @Context ICategoryServicePort categoryServicePort, @Context IMarkServicePort markServicePort);

    @Named("convertOrderBy")
    default String convertOrderBy(String orderBy) {
        switch (orderBy.toLowerCase()) {
            case "article":
                return "name";
            case "category":
                return "categories.name";
            case "mark":
                return "mark.name";
            default:
                throw new IllegalArgumentException("Invalid order by value: " + orderBy);
        }
    }


}
