package com.microservicio_stock.stock_service.domain.use_cases;

public class ArticleUseCase {
    private final IArticlePersistencePort iArticlePersistencePort;

    public ArticleUseCase(IArticlePersistencePort iArticlePersistencePort) {
        this.iArticlePersistencePort = iArticlePersistencePort;
    }

    public void saveArticle(Article article) {
        iArticlePersistencePort.saveArticle(article);
    }
}
