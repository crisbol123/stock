package com.microservicio_stock.stock_service.adapters.driving.http.controller;

import com.microservicio_stock.stock_service.adapters.driving.http.adapter.ArticleAdapterHttp;
import com.microservicio_stock.stock_service.adapters.driving.http.dto.article.response.ArticleResponse;
import com.microservicio_stock.stock_service.domain.util.PagedResponse;
import com.microservicio_stock.stock_service.adapters.driving.http.dto.article.request.AddArticleRequest;
import com.microservicio_stock.stock_service.adapters.driving.http.dto.article.request.FindAllArticlesRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
@RequiredArgsConstructor
@Tag(name = "Article", description = "API for managing articles")
public class ArticleRestControllerAdapter {
    private final ArticleAdapterHttp articleAdapterHttp;

    @Operation(summary = "Add a new article", description = "Creates a new article with the provided details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Article created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
    })
    @PostMapping("/")
    public ResponseEntity<Void> saveArticle(@RequestBody @Valid AddArticleRequest addArticleRequest) {
        articleAdapterHttp.saveArticle(addArticleRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Get all articles", description = "Retrieves all articles with pagination and sorting options")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Articles retrieved successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArticleResponse.class))),
            @ApiResponse(responseCode = "404", description = "No articles found", content = @Content)
    })
    @GetMapping("/")
    public ResponseEntity<PagedResponse<ArticleResponse>> getPagedArticles(FindAllArticlesRequest request) {
        PagedResponse<ArticleResponse> response = articleAdapterHttp.getPagedArticles(request);
        return ResponseEntity.ok(response);
    }
}
