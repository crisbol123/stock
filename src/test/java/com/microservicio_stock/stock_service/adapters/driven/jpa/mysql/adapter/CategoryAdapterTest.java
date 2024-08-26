package com.microservicio_stock.stock_service.adapters.driven.jpa.mysql.adapter;

import com.microservicio_stock.stock_service.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.microservicio_stock.stock_service.adapters.driven.jpa.mysql.exception.ElementAlreadyExistsException;
import com.microservicio_stock.stock_service.adapters.driven.jpa.mysql.exception.NoDataFoundException;
import com.microservicio_stock.stock_service.adapters.driven.jpa.mysql.mapper.ICategoryEntityMapper;
import com.microservicio_stock.stock_service.adapters.driven.jpa.mysql.repository.ICategoryRepository;
import com.microservicio_stock.stock_service.domain.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CategoryAdapterTest {

    @Mock
    private ICategoryRepository categoryRepository;

    @Mock
    private ICategoryEntityMapper categoryEntityMapper;

    @InjectMocks
    private CategoryAdapter categoryAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveCategory_Success() {
        // Arrange
        Category category = new Category(1L, "Electronics", "Devices and gadgets");
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName("Electronics");

        when(categoryRepository.findIdByName("Electronics")).thenReturn(Optional.empty());
        when(categoryEntityMapper.toEntity(any(Category.class))).thenReturn(categoryEntity);

        // Act
        categoryAdapter.saveCategory(category);

        // Assert
        verify(categoryRepository, times(1)).save(categoryEntity);
    }

    @Test
    void testSaveCategory_AlreadyExists() {
        // Arrange
        Category category = new Category(1L, "Electronics", "Devices and gadgets");

        when(categoryRepository.findIdByName("Electronics")).thenReturn(Optional.of(1L));

        // Act & Assert
        assertThrows(ElementAlreadyExistsException.class, () -> {
            categoryAdapter.saveCategory(category);
        });

        verify(categoryRepository, never()).save(any(CategoryEntity.class));
    }

    @Test
    void testGetAllCategories_Success() {
        // Arrange
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName("Electronics");
        List<CategoryEntity> categoryEntities = List.of(categoryEntity);

        Page<CategoryEntity> page = new PageImpl<>(categoryEntities);
        when(categoryRepository.findAll(any(Pageable.class))).thenReturn(page);

        Category category = new Category(1L, "Electronics", "Devices and gadgets");
        when(categoryEntityMapper.toModelList(categoryEntities)).thenReturn(List.of(category));

        // Act
        List<Category> result = categoryAdapter.getPagedCategories(0, 10, true);

        // Assert
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals("Electronics", result.get(0).getName());
    }

    @Test
    void testGetAllCategories_NoDataFound() {
        // Arrange
        Page<CategoryEntity> emptyPage = new PageImpl<>(Collections.emptyList());
        when(categoryRepository.findAll(any(Pageable.class))).thenReturn(emptyPage);

        // Act & Assert
        assertThrows(NoDataFoundException.class, () -> {
            categoryAdapter.getPagedCategories(0, 10, true);
        });

        verify(categoryEntityMapper, never()).toModelList(anyList());
    }

    @Test
    void testGetTotalCategories() {
        // Arrange
        when(categoryRepository.count()).thenReturn(5L);

        // Act
        long result = categoryAdapter.getTotalCategories();

        // Assert
        assertEquals(5L, result);
        verify(categoryRepository, times(1)).count();
    }
}
