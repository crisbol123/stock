package com.microservicio_stock.stock_service.domain.api;

import com.microservicio_stock.stock_service.domain.model.Article;

public interface IArticleServicePort {
    void saveArticle(Article article);
}
