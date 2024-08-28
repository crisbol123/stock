package com.microservicio_stock.stock_service.adapters.driving.http.adapter;

import com.microservicio_stock.stock_service.adapters.driving.http.dto.article.response.ArticleResponse;
import com.microservicio_stock.stock_service.domain.util.PagedResponse;
import com.microservicio_stock.stock_service.adapters.driving.http.dto.article.request.AddArticleRequest;
import com.microservicio_stock.stock_service.adapters.driving.http.dto.article.request.FindAllArticlesRequest;
import com.microservicio_stock.stock_service.adapters.driving.http.mapper.article.IArticleRequestMapper;
import com.microservicio_stock.stock_service.adapters.driving.http.mapper.article.IArticleResponseMapper;
import com.microservicio_stock.stock_service.domain.api.IArticleServicePort;
import com.microservicio_stock.stock_service.domain.api.ICategoryServicePort;
import com.microservicio_stock.stock_service.domain.api.IMarkServicePort;
import com.microservicio_stock.stock_service.domain.model.Article;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ArticleAdapterHttp {
   private final IArticleServicePort articleServicePort;
   private final IArticleRequestMapper iArticleRequestMapper;
    private final ICategoryServicePort categoryServicePort;
    private  final IMarkServicePort markServicePort;
    private final IArticleResponseMapper articleResponseMapper;
    public void saveArticle(AddArticleRequest addArticleRequest) {
        Article article = iArticleRequestMapper.addArticleRequestToArticle(addArticleRequest, categoryServicePort, markServicePort);
        articleServicePort.saveArticle(article);
    }

    public PagedResponse<ArticleResponse> getPagedArticles(FindAllArticlesRequest request) {
        PagedResponse<Article> pagedResponse = articleServicePort.getPagedArticles(request.getPage(), request.getSize(), request.isAscOrderByName(), request.getOrderBy());
        return articleResponseMapper.toArticleResponsePagedResponse(pagedResponse);
    }
}
