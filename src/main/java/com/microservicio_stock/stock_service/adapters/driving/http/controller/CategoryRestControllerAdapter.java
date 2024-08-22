package com.microservicio_stock.stock_service.adapters.driving.http.controller;

import com.microservicio_stock.stock_service.adapters.driving.http.dto.category.request.AddCategoryRequest;
import com.microservicio_stock.stock_service.adapters.driving.http.dto.category.request.UpdateCategoryRequest;
import com.microservicio_stock.stock_service.adapters.driving.http.dto.category.response.CategoryResponse;
import com.microservicio_stock.stock_service.adapters.driving.http.mapper.category.ICategoryRequestMapper;
import com.microservicio_stock.stock_service.adapters.driving.http.mapper.category.ICategoryResponseMapper;
import com.microservicio_stock.stock_service.domain.api.ICategoryServicePort;
import com.microservicio_stock.stock_service.domain.model.Category;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
@Tag(name = "Category", description = "API for managing categories")
public class CategoryRestControllerAdapter {
    private final ICategoryServicePort categoryServicePort;
    private final ICategoryRequestMapper categoryRequestMapper;
    private final ICategoryResponseMapper categoryResponseMapper;

    @Operation(summary = "Add a new category", description = "Creates a new category with the provided details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Category created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
    })
    @PostMapping("/")
    public ResponseEntity<Void> addCategory(@RequestBody AddCategoryRequest request) {
        categoryServicePort.saveCategory(categoryRequestMapper.addCategoryRequestToCategory(request));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Get a category by ID", description = "Retrieves a category by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category retrieved successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryResponse.class))),
            @ApiResponse(responseCode = "404", description = "Category not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategoryById(
            @Parameter(description = "ID of the category to retrieve", required = true)
            @PathVariable Long id) {
        Optional<Category> category = categoryServicePort.getCategoryById(id);
        if(category.isEmpty()){
            throw new NoSuchElementException();
        }
        return ResponseEntity.ok(categoryResponseMapper.toCategoryResponse(category.get()));
    }

    @Operation(summary = "Get all categories", description = "Retrieves all categories with pagination and sorting options")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categories retrieved successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryResponse.class))),
            @ApiResponse(responseCode = "404", description = "No categories found", content = @Content)
    })
    @GetMapping("/")
    public ResponseEntity<List<CategoryResponse>> getAllCategories(
            @Parameter(description = "Page number of the categories to retrieve", required = true)
            @RequestParam Integer page,
            @Parameter(description = "Number of categories per page", required = true)
            @RequestParam Integer size,
            @Parameter(description = "Sort categories by name in ascending order if true, descending if false", required = true)
            @RequestParam Boolean ascOrderByName) {
        return ResponseEntity.ok(categoryResponseMapper.toCategoryResponseList(categoryServicePort.getAllCategories(page, size, ascOrderByName)));
    }

    @Operation(summary = "Update an existing category", description = "Updates an existing category with the provided details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category updated successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryResponse.class))),
            @ApiResponse(responseCode = "404", description = "Category not found", content = @Content)
    })
    @PutMapping("/")
    public ResponseEntity<CategoryResponse> updateCategory(@RequestBody UpdateCategoryRequest request) {
        return ResponseEntity.ok(categoryResponseMapper.toCategoryResponse(
                categoryServicePort.updateCategory(categoryRequestMapper.updateCategoryRequestToCategory(request))));
    }

    @Operation(summary = "Delete a category by ID", description = "Deletes a category by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Category deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Category not found", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(
            @Parameter(description = "ID of the category to delete", required = true)
            @PathVariable Long id) {
        categoryServicePort.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
