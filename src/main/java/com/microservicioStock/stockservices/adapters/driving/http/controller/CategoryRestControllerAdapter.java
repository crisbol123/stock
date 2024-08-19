package com.microservicioStock.stockservices.adapters.driving.http.controller;

import com.microservicioStock.stockservices.adapters.driving.http.dto.request.AddCategoryRequest;
import com.microservicioStock.stockservices.adapters.driving.http.dto.request.UpdateCategoryRequest;
import com.microservicioStock.stockservices.adapters.driving.http.dto.response.CategoryResponse;
import com.microservicioStock.stockservices.adapters.driving.http.mapper.ICategoryRequestMapper;
import com.microservicioStock.stockservices.adapters.driving.http.mapper.ICategoryResponseMapper;
import com.microservicioStock.stockservices.domain.api.ICategoryServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryRestControllerAdapter {
    private final ICategoryServicePort categoryServicePort;
    private final ICategoryRequestMapper categoryRequestMapper;
    private final ICategoryResponseMapper categoryResponseMapper;

    @PostMapping("/")
    public ResponseEntity<Void> addCategory(@RequestBody AddCategoryRequest request) {
        categoryServicePort.saveCategory(categoryRequestMapper.addCategoryRequestToCategory(request));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryResponseMapper.toCategoryResponse(categoryServicePort.getCategoryById(id).get()));
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryResponse>> getAllCategories(@RequestParam Integer page, @RequestParam Integer size) {
        return ResponseEntity.ok(categoryResponseMapper.toCategoryResponseList(categoryServicePort.getAllCategories(page, size)));
    }

    @PutMapping("/")
    public ResponseEntity<CategoryResponse> updateCategory(@RequestBody UpdateCategoryRequest request) {
        return ResponseEntity.ok(categoryResponseMapper.toCategoryResponse(
                categoryServicePort.updateCategory(categoryRequestMapper.updateCategoryRequestToCategory(request))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryServicePort.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
