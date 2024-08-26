package com.microservicio_stock.stock_service.domain.use_cases;

import com.microservicio_stock.stock_service.adapters.driving.http.dto.PagedResponse;
import com.microservicio_stock.stock_service.domain.model.Category;
import com.microservicio_stock.stock_service.domain.spi.ICategoryPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
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
    void saveCategoryTest() {
        Category category = new Category(1L, "Test Category", "Description");

        categoryUseCase.saveCategory(category);

        verify(categoryPersistencePort, times(1)).saveCategory(category);
    }


    @Test
    void getAllCategoriesTest() {
        List<Category> categories = Arrays.asList(
                new Category(1L, "Category 1", "Description 1"),
                new Category(2L, "Category 2", "Description 2")
        );

        when(categoryPersistencePort.getPagedCategories(0, 10, true)).thenReturn(new PagedResponse<>(categories, 2, 0, 10, false, true));

        PagedResponse result = categoryUseCase.getPagedCategories(0, 10, true);
        assertEquals(result.getCurrentPage(), 2);
        verify(categoryPersistencePort, times(1)).getPagedCategories(0, 10, true);
    }
}


