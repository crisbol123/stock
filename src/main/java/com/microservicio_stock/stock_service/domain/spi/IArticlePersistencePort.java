package com.microservicio_stock.stock_service.domain.spi;

import com.microservicio_stock.stock_service.adapters.driving.http.dto.PagedResponse;
import com.microservicio_stock.stock_service.domain.model.Article;

import java.util.List;

public interface IArticlePersistencePort {
    void saveArticle(Article article);

    PagedResponse<Article> getPagedArticles(Integer page, Integer size, boolean ascOrderByName, String orderBy);

}