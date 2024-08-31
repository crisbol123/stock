package com.microservicio_stock.stock_service.adapters.driving.http.controller;

import com.microservicio_stock.stock_service.adapters.driving.http.adapter.ArticleAdapterHttp;
import com.microservicio_stock.stock_service.adapters.driving.http.dto.article.response.ArticleResponse;
import com.microservicio_stock.stock_service.adapters.driving.http.dto.article.response.CategorySummaryResponse;
import com.microservicio_stock.stock_service.adapters.driving.http.mapper.mark.IMarkResponseMapper;
import com.microservicio_stock.stock_service.domain.util.PagedResponse;
import com.microservicio_stock.stock_service.adapters.driving.http.dto.article.request.AddArticleRequest;
import com.microservicio_stock.stock_service.adapters.driving.http.dto.article.request.FindAllArticlesRequest;
import com.microservicio_stock.stock_service.domain.model.Article;
import com.microservicio_stock.stock_service.domain.model.Category;
import com.microservicio_stock.stock_service.domain.model.Mark;
import com.microservicio_stock.stock_service.adapters.driving.http.mapper.article.IArticleResponseMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

 class ArticleRestControllerAdapterTest {

    @Mock
    private ArticleAdapterHttp articleAdapterHttp;

    @Mock
    private IMarkResponseMapper iMarkResponseMapper;

    @Mock
    private IArticleResponseMapper articleResponseMapper;

    @InjectMocks
    private ArticleRestControllerAdapter articleRestControllerAdapter;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
     void testSaveArticle() {
        AddArticleRequest request = new AddArticleRequest();
        request.setName("Article Name");
        request.setDescription("Article Description");
        request.setQuantity(10);
        request.setPrice(99.99);
        request.setCategoryIds(List.of(1L, 2L));
        request.setMarkId(1L);

        ResponseEntity<Void> response = articleRestControllerAdapter.saveArticle(request);

        // Verify that the status code is CREATED (201)
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
     void testGetPagedArticles() {
        FindAllArticlesRequest request = new FindAllArticlesRequest(0, 10, true, "name");

        // Mock data
        Category category1 = new Category(1L, "Category1", "Description1");
        Category category2 = new Category(2L, "Category2", "Description2");
        Mark mark = new Mark(1L, "Mark1", "Description1");

        Article article = new Article(
                1L,
                "Article Name",
                "Article Description",
                10,
                99.99,
                List.of(category1, category2),
                mark
        );
        List<Article> articles = List.of(article);

        ArticleResponse articleResponse = new ArticleResponse();
        articleResponse.setId(article.getId());
        articleResponse.setName(article.getName());
        articleResponse.setDescription(article.getDescription());
        articleResponse.setQuantity(article.getQuantity());
        articleResponse.setPrice(article.getPrice());
        articleResponse.setCategories(article.getCategories().stream()
                .map(cat -> new CategorySummaryResponse(cat.getId(), cat.getName()))
                .toList());
        articleResponse.setMark(iMarkResponseMapper.toMarkResponse(article.getMark()));

        List<ArticleResponse> articleResponses = List.of(articleResponse);
        when(articleResponseMapper.mapArticles(articles)).thenReturn(articleResponses);
        PagedResponse<ArticleResponse> pagedArticleResponse = new PagedResponse<>(articleResponses, 0, 1, 1, true);

        when(articleAdapterHttp.getPagedArticles(request)).thenReturn(pagedArticleResponse);
        when(articleResponseMapper.toArticleResponse(article)).thenReturn(articleResponse);

        ResponseEntity<PagedResponse<ArticleResponse>> response = articleRestControllerAdapter.getPagedArticles(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().getContent().size());
        assertEquals(article.getId(), response.getBody().getContent().get(0).getId());
        assertEquals(article.getName(), response.getBody().getContent().get(0).getName());
    }}