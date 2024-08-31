package com.microservicio_stock.stock_service.domain.use_cases;

import com.microservicio_stock.stock_service.domain.model.Category;
import com.microservicio_stock.stock_service.domain.spi.ICategoryPersistencePort;
import com.microservicio_stock.stock_service.domain.util.PagedResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

 class CategoryUseCaseTest {

    @Mock
    private ICategoryPersistencePort categoryPersistencePort;

    @InjectMocks
    private CategoryUseCase categoryUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveCategory() {
        Category category = new Category(1L, "CategoryName", "CategoryDescription");

        categoryUseCase.saveCategory(category);

        verify(categoryPersistencePort, times(1)).saveCategory(category);
    }

    @Test
    void testGetPagedCategories() {
        PagedResponse<Category> pagedResponse = new PagedResponse<>(
                List.of(new Category(1L, "CategoryName1", "Description1")),
                0, 1, 1, true
        );

        when(categoryPersistencePort.getPagedCategories(anyInt(), anyInt(), anyBoolean())).thenReturn(pagedResponse);

        PagedResponse<Category> result = categoryUseCase.getPagedCategories(0, 10, true);

        assertEquals(pagedResponse, result);
        verify(categoryPersistencePort, times(1)).getPagedCategories(0, 10, true);
    }

    @Test
    void testGetCategoryById() {
        Category category = new Category(1L, "CategoryName", "CategoryDescription");

        when(categoryPersistencePort.getCategoryById(1L)).thenReturn(category);

        Category result = categoryUseCase.getCategoryById(1L);

        assertEquals(category, result);
        verify(categoryPersistencePort, times(1)).getCategoryById(1L);
    }
}
