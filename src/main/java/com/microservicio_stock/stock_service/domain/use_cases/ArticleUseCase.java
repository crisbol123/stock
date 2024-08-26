package com.microservicio_stock.stock_service.domain.use_cases;

import com.microservicio_stock.stock_service.adapters.driving.http.dto.PagedResponse;
import com.microservicio_stock.stock_service.domain.api.IArticleServicePort;
import com.microservicio_stock.stock_service.domain.model.Article;
import com.microservicio_stock.stock_service.domain.spi.IArticlePersistencePort;

public class ArticleUseCase implements IArticleServicePort {
    private final IArticlePersistencePort iArticlePersistencePort;

    public ArticleUseCase(IArticlePersistencePort iArticlePersistencePort) {
        this.iArticlePersistencePort = iArticlePersistencePort;
    }

    public void saveArticle(Article article) {
        iArticlePersistencePort.saveArticle(article);
    }

    @Override
    public PagedResponse<Article> getPagedArticles(int page, int size, boolean ascOrderByName, String orderBy) {
        return iArticlePersistencePort.getPagedArticles(page, size, ascOrderByName, orderBy);
    }


}


