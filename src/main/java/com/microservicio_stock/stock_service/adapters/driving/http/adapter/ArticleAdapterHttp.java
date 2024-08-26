package com.microservicio_stock.stock_service.adapters.driving.http.adapter;

import com.microservicio_stock.stock_service.adapters.driving.http.dto.PagedResponse;
import com.microservicio_stock.stock_service.adapters.driving.http.dto.article.request.AddArticleRequest;
import com.microservicio_stock.stock_service.adapters.driving.http.dto.article.request.FindAllArticlesRequest;
import com.microservicio_stock.stock_service.adapters.driving.http.mapper.article.IArticleRequestMapper;
import com.microservicio_stock.stock_service.adapters.driving.http.mapper.article.IArticleResponseMapper;
import com.microservicio_stock.stock_service.domain.api.IArticleServicePort;
import com.microservicio_stock.stock_service.domain.api.ICategoryServicePort;
import com.microservicio_stock.stock_service.domain.api.IMarkServicePort;
import com.microservicio_stock.stock_service.domain.model.Article;
import com.microservicio_stock.stock_service.domain.model.Category;
import com.microservicio_stock.stock_service.domain.model.Mark;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ArticleAdapterHttp {
   private final IArticleServicePort articleServicePort;
   private final IArticleRequestMapper IArticleRequestMapper;
    private final ICategoryServicePort categoryServicePort;
    private  final IMarkServicePort markServicePort;
    private final IArticleResponseMapper articleResponseMapper;
    public void saveArticle(AddArticleRequest addArticleRequest) {
        Article article = IArticleRequestMapper.addArticleRequestToArticle(addArticleRequest, categoryServicePort, markServicePort);
        articleServicePort.saveArticle(article);
    }

    public PagedResponse<Article> getPagedArticles(FindAllArticlesRequest request) {
        PagedResponse pagedResponse = articleServicePort.getPagedArticles(request.getPage(), request.getSize(), request.isAscOrderByName(), request.getOrderBy());
        return articleResponseMapper.toArticleResponsePagedResponse(pagedResponse);
    }
}
