package com.microservicio_stock.stock_service.domain.spi;

import com.microservicio_stock.stock_service.domain.util.PagedResponse;
import com.microservicio_stock.stock_service.domain.model.Article;

public interface IArticlePersistencePort {
    void saveArticle(Article article);

    PagedResponse<Article> getPagedArticles(Integer page, Integer size, boolean ascOrderByName, String orderBy);

}