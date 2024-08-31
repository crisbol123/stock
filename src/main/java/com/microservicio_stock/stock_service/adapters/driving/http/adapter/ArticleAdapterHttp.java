package com.microservicio_stock.stock_service.adapters.driving.http.adapter;

import com.microservicio_stock.stock_service.adapters.driving.http.dto.article.request.AddArticleRequest;
import com.microservicio_stock.stock_service.adapters.driving.http.dto.article.request.FindAllArticlesRequest;
import com.microservicio_stock.stock_service.adapters.driving.http.dto.article.response.ArticleResponse;
import com.microservicio_stock.stock_service.adapters.driving.http.mapper.article.IArticleRequestMapper;
import com.microservicio_stock.stock_service.adapters.driving.http.mapper.article.IArticleResponseMapper;
import com.microservicio_stock.stock_service.domain.api.IArticleServicePort;
import com.microservicio_stock.stock_service.domain.api.ICategoryServicePort;
import com.microservicio_stock.stock_service.domain.api.IMarkServicePort;
import com.microservicio_stock.stock_service.domain.model.Article;
import com.microservicio_stock.stock_service.domain.util.PagedResponse;
import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ArticleAdapterHttp {
    private final IArticleServicePort articleServicePort;
    private final IArticleRequestMapper iArticleRequestMapper;
    private final ICategoryServicePort categoryServicePort;
    private final IMarkServicePort markServicePort;
    private final IArticleResponseMapper articleResponseMapper;

    public void saveArticle(AddArticleRequest addArticleRequest) {
        Article article = iArticleRequestMapper.addArticleRequestToArticle(addArticleRequest, categoryServicePort, markServicePort);
        articleServicePort.saveArticle(article);
    }

    public PagedResponse<ArticleResponse> getPagedArticles(FindAllArticlesRequest request) {

        String orderByConverted = iArticleRequestMapper.convertOrderBy(request.getOrderBy());

        PagedResponse<Article> pagedResponse = articleServicePort.getPagedArticles(request.getPage(), request.getSize(), request.isAscOrderByName(), orderByConverted);

        return articleResponseMapper.toArticleResponsePagedResponse(pagedResponse);
    }
}
