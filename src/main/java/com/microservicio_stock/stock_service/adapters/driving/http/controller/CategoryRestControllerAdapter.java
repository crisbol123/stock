package com.microservicio_stock.stock_service.adapters.driving.http.controller;

import com.microservicio_stock.stock_service.adapters.driving.http.adapter.CategoryAdapterHttp;
import com.microservicio_stock.stock_service.adapters.driving.http.dto.category.request.AddCategoryRequest;
import com.microservicio_stock.stock_service.adapters.driving.http.dto.category.request.FindAllCategoriesRequest;
import com.microservicio_stock.stock_service.adapters.driving.http.dto.category.response.CategoryResponse;
import com.microservicio_stock.stock_service.adapters.driving.http.dto.PagedResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
@Tag(name = "Category", description = "API for managing categories")
public class CategoryRestControllerAdapter {
    private final CategoryAdapterHttp categoryAdapterHttp;


    @Operation(summary = "Add a new category", description = "Creates a new category with the provided details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Category created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
    })
    @PostMapping("/")
    public ResponseEntity<Void> addCategory(@RequestBody AddCategoryRequest request) {
categoryAdapterHttp.saveCategory(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @Operation(summary = "Get all categories", description = "Retrieves all categories with pagination and sorting options")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categories retrieved successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryResponse.class))),
            @ApiResponse(responseCode = "404", description = "No categories found", content = @Content)
    })
    @GetMapping("/")
    public ResponseEntity<PagedResponse<CategoryResponse>> getAllCategories(FindAllCategoriesRequest request) {

        return ResponseEntity.ok(categoryAdapterHttp.getPagedCategories(request));
    }



}
