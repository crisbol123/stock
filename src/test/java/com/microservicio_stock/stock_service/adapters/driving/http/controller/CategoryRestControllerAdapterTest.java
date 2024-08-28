package com.microservicio_stock.stock_service.adapters.driving.http.controller;

import com.microservicio_stock.stock_service.adapters.driving.http.adapter.CategoryAdapterHttp;
import com.microservicio_stock.stock_service.adapters.driving.http.dto.category.request.AddCategoryRequest;
import com.microservicio_stock.stock_service.adapters.driving.http.dto.category.request.FindAllCategoriesRequest;
import com.microservicio_stock.stock_service.adapters.driving.http.dto.category.response.CategoryResponse;
import com.microservicio_stock.stock_service.domain.util.PagedResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CategoryRestControllerAdapterTest {

    @Mock
    private CategoryAdapterHttp categoryAdapterHttp;

    @InjectMocks
    private CategoryRestControllerAdapter categoryRestControllerAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addCategory_shouldReturnCreatedStatus() {
        // Arrange
        AddCategoryRequest request = new AddCategoryRequest();
        doNothing().when(categoryAdapterHttp).saveCategory(any(AddCategoryRequest.class));

        // Act
        ResponseEntity<Void> response = categoryRestControllerAdapter.addCategory(request);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(categoryAdapterHttp, times(1)).saveCategory(request);
    }

    @Test
    void getAllCategories_shouldReturnPagedCategories() {
        // Arrange
        FindAllCategoriesRequest request = new FindAllCategoriesRequest(1, 2, true);
        PagedResponse<CategoryResponse> pagedResponse = new PagedResponse<>(
                Collections.singletonList(new CategoryResponse()), 1, 1, 1L, true
        );
        when(categoryAdapterHttp.getPagedCategories(any(FindAllCategoriesRequest.class)))
                .thenReturn(pagedResponse);

        // Act
        ResponseEntity<PagedResponse<CategoryResponse>> response = categoryRestControllerAdapter.getAllCategories(request);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pagedResponse, response.getBody());
        verify(categoryAdapterHttp, times(1)).getPagedCategories(request);
    }
}
