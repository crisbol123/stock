package com.microservicio_stock.stock_service.adapters.driving.http.controller;

import com.microservicio_stock.stock_service.adapters.driving.http.adapter.ArticleAdapterHttp;
import com.microservicio_stock.stock_service.adapters.driving.http.dto.PagedResponse;
import com.microservicio_stock.stock_service.adapters.driving.http.dto.article.request.AddArticleRequest;
import com.microservicio_stock.stock_service.adapters.driving.http.dto.article.request.FindAllArticlesRequest;
import com.microservicio_stock.stock_service.domain.model.Article;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleRestControllerAdapter {
    private final ArticleAdapterHttp articleAdapterHttp;

    @PostMapping("/")
    public ResponseEntity<Void> saveArticle(@RequestBody AddArticleRequest addArticleRequest) {
        articleAdapterHttp.saveArticle(addArticleRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping("/")
    public PagedResponse<Article> getPagedArticles(FindAllArticlesRequest request) {
        return articleAdapterHttp.getPagedArticles(request);
    }

}
