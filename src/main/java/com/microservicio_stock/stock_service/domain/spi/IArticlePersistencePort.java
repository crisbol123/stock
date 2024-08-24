package com.microservicio_stock.stock_service.domain.spi;

import com.microservicio_stock.stock_service.domain.model.Article;

import java.util.List;

public interface IArticlePersistencePort {
    void saveArticle(Article article);

}