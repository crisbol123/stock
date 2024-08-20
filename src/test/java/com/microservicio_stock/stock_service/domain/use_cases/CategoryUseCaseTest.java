package com.microservicio_stock.stock_service.domain.use_cases;

import com.microservicio_stock.stock_service.domain.model.Category;
import com.microservicio_stock.stock_service.domain.spi.ICategoryPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
    void getCategoryByIdTest() {
        Long categoryId = 1L;
        Optional<Category> category = Optional.of(new Category(categoryId, "Test Category", "Description"));

        when(categoryPersistencePort.getCategoryById(categoryId)).thenReturn(category);

        Optional<Category> result = categoryUseCase.getCategoryById(categoryId);

        assertEquals(category, result);
        verify(categoryPersistencePort, times(1)).getCategoryById(categoryId);
    }

    @Test
    void getAllCategoriesTest() {
        List<Category> categories = Arrays.asList(
                new Category(1L, "Category 1", "Description 1"),
                new Category(2L, "Category 2", "Description 2")
        );

        when(categoryPersistencePort.getAllCategories(0, 10)).thenReturn(categories);

        List<Category> result = categoryUseCase.getAllCategories(0, 10);

        assertEquals(categories.size(), result.size());
        verify(categoryPersistencePort, times(1)).getAllCategories(0, 10);
    }

    @Test
    void updateCategoryTest() {
        Category category = new Category(1L, "Updated Category", "Updated Description");

        when(categoryPersistencePort.updateCategory(category)).thenReturn(category);

        Category result = categoryUseCase.updateCategory(category);

        assertEquals(category, result);
        verify(categoryPersistencePort, times(1)).updateCategory(category);
    }

    @Test
    void deleteCategoryTest() {
        Long categoryId = 1L;

        categoryUseCase.deleteCategory(categoryId);

        verify(categoryPersistencePort, times(1)).deleteCategory(categoryId);
    }
}
