package com.microservicio_stock.stock_service.adapters.driving.http.mapper.article;

import com.microservicio_stock.stock_service.adapters.driving.http.dto.article.response.ArticleResponse;
import com.microservicio_stock.stock_service.adapters.driving.http.dto.article.response.CategorySummaryResponse;
import com.microservicio_stock.stock_service.domain.model.Article;
import com.microservicio_stock.stock_service.domain.model.Category;
import com.microservicio_stock.stock_service.domain.util.PagedResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface IArticleResponseMapper {

    @Mapping(target = "content", source = "content", qualifiedByName = "mapArticles")
    PagedResponse<ArticleResponse> toArticleResponsePagedResponse(PagedResponse<Article> pagedResponse);

    @Named("mapArticles")
    default List<ArticleResponse> mapArticles(List<Article> articles) {
        return articles.stream()
                .map(this::toArticleResponse)
                .collect(Collectors.toList());
    }

    @Mapping(target = "categories", source = "categories", qualifiedByName = "toCategorySummaryResponseList")
    ArticleResponse toArticleResponse(Article article);

    @Named("toCategorySummaryResponseList")
    default List<CategorySummaryResponse> toCategorySummaryResponseList(List<Category> categories) {
        return categories.stream()
                .map(this::toCategorySummaryResponse)
                .collect(Collectors.toList());
    }

    @Named("toCategorySummaryResponse")
    default CategorySummaryResponse toCategorySummaryResponse(Category category) {
        return new CategorySummaryResponse(category.getId(), category.getName());
    }
}
