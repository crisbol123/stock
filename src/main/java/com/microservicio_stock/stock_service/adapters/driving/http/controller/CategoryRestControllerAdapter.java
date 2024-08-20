package com.microservicio_stock.stock_service.adapters.driving.http.controller;

import com.microservicio_stock.stock_service.adapters.driving.http.dto.request.AddCategoryRequest;
import com.microservicio_stock.stock_service.adapters.driving.http.dto.request.UpdateCategoryRequest;
import com.microservicio_stock.stock_service.adapters.driving.http.dto.response.CategoryResponse;
import com.microservicio_stock.stock_service.adapters.driving.http.mapper.ICategoryRequestMapper;
import com.microservicio_stock.stock_service.adapters.driving.http.mapper.ICategoryResponseMapper;
import com.microservicio_stock.stock_service.domain.api.ICategoryServicePort;
import com.microservicio_stock.stock_service.domain.model.Category;
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
        Optional<Category> category = categoryServicePort.getCategoryById(id);
        if(category.isEmpty()){
            throw new NoSuchElementException();
        }
        return ResponseEntity.ok(categoryResponseMapper.toCategoryResponse(category.get()));

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
