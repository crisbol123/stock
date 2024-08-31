package com.microservicio_stock.stock_service.adapters.driven.jpa.mysql.adapter;

import com.microservicio_stock.stock_service.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.microservicio_stock.stock_service.adapters.driven.jpa.mysql.exception.ElementAlreadyExistsException;
import com.microservicio_stock.stock_service.adapters.driven.jpa.mysql.exception.NoDataFoundException;
import com.microservicio_stock.stock_service.adapters.driven.jpa.mysql.mapper.ICategoryEntityMapper;
import com.microservicio_stock.stock_service.adapters.driven.jpa.mysql.repository.ICategoryRepository;
import com.microservicio_stock.stock_service.domain.model.Category;
import com.microservicio_stock.stock_service.domain.util.PagedResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryAdapterTest {

    @InjectMocks
    private CategoryAdapter categoryAdapter;

    @Mock
    private ICategoryRepository categoryRepository;

    @Mock
    private ICategoryEntityMapper categoryEntityMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveCategory_ShouldSaveCategory() {
        // Arrange
        Category category = new Category(1L, "Category Name", "Category Description");
        CategoryEntity categoryEntity = new CategoryEntity(1L, "Category Name", "Category Description");

        when(categoryRepository.findIdByName(category.getName())).thenReturn(Optional.empty());
        when(categoryEntityMapper.toEntity(category)).thenReturn(categoryEntity);

        // Act
        categoryAdapter.saveCategory(category);

        // Assert
        verify(categoryRepository, times(1)).save(categoryEntity);
    }

    @Test
    void saveCategory_ShouldThrowElementAlreadyExistsException() {
        // Arrange
        Category category = new Category(1L, "Category Name", "Category Description");
        when(categoryRepository.findIdByName(category.getName())).thenReturn(Optional.of(1L));

        // Act & Assert
        assertThrows(ElementAlreadyExistsException.class, () -> categoryAdapter.saveCategory(category));
    }

    @Test
    void getPagedCategories_ShouldReturnPagedCategories() {
        // Arrange
        Integer page = 0;
        Integer size = 10;
        boolean ascOrderByName = true;
        Pageable pageable = PageRequest.of(page, size, Sort.by(ascOrderByName ? Sort.Direction.ASC : Sort.Direction.DESC, "name"));
        Page<CategoryEntity> categoryEntitiesPage = mock(Page.class);
        when(categoryRepository.findAll(pageable)).thenReturn(categoryEntitiesPage);
        when(categoryEntitiesPage.isEmpty()).thenReturn(false);

        // Prepare dummy data
        CategoryEntity categoryEntity = new CategoryEntity(1L, "Category Name", "Category Description");
        List<CategoryEntity> categoryEntityList = List.of(categoryEntity);
        when(categoryEntitiesPage.getContent()).thenReturn(categoryEntityList);
        when(categoryEntitiesPage.isLast()).thenReturn(false);
        when(categoryEntitiesPage.getNumber()).thenReturn(page);
        when(categoryEntitiesPage.getTotalPages()).thenReturn(5);
        when(categoryEntitiesPage.getTotalElements()).thenReturn(50L);

        Category category = new Category(1L, "Category Name", "Category Description");
        PagedResponse<Category> expectedResponse = new PagedResponse<>(
                List.of(category),
                page,
                5,
                50L,
                false
        );

        when(categoryEntityMapper.toPagedModel(categoryEntitiesPage, categoryEntitiesPage.isLast(), categoryEntitiesPage.getNumber()))
                .thenReturn(expectedResponse);

        // Act
        PagedResponse<Category> result = categoryAdapter.getPagedCategories(page, size, ascOrderByName);

        // Assert
        assertNotNull(result);
        assertEquals(expectedResponse.getContent(), result.getContent());
        assertEquals(expectedResponse.getCurrentPage(), result.getCurrentPage());
        assertEquals(expectedResponse.getTotalPages(), result.getTotalPages());
        assertEquals(expectedResponse.getTotalElements(), result.getTotalElements());
        assertEquals(expectedResponse.isLastPage(), result.isLastPage());
        verify(categoryRepository, times(1)).findAll(pageable);
        verify(categoryEntityMapper, times(1)).toPagedModel(categoryEntitiesPage, categoryEntitiesPage.isLast(), categoryEntitiesPage.getNumber());
    }

    @Test
    void getPagedCategories_NoDataFoundException() {
        // Arrange
        Integer page = 0;
        Integer size = 10;
        boolean ascOrderByName = true;
        Pageable pageable = PageRequest.of(page, size, Sort.by(ascOrderByName ? Sort.Direction.ASC : Sort.Direction.DESC, "name"));
        when(categoryRepository.findAll(pageable)).thenReturn(Page.empty());

        // Act & Assert
        assertThrows(NoDataFoundException.class, () -> categoryAdapter.getPagedCategories(page, size, ascOrderByName));
    }

    @Test
    void getCategoryById_ShouldReturnCategory() {
        // Arrange
        Long id = 1L;
        CategoryEntity categoryEntity = new CategoryEntity(id, "Category Name", "Category Description");
        Category category = new Category(id, "Category Name", "Category Description");

        when(categoryRepository.findById(id)).thenReturn(Optional.of(categoryEntity));
        when(categoryEntityMapper.toModel(categoryEntity)).thenReturn(category);

        // Act
        Category result = categoryAdapter.getCategoryById(id);

        // Assert
        assertNotNull(result);
        assertEquals(category.getId(), result.getId());
        assertEquals(category.getName(), result.getName());
        assertEquals(category.getDescription(), result.getDescription());
        verify(categoryRepository, times(1)).findById(id);
        verify(categoryEntityMapper, times(1)).toModel(categoryEntity);
    }

    @Test
    void getCategoryById_NoDataFoundException() {
        // Arrange
        Long id = 1L;
        when(categoryRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NoDataFoundException.class, () -> categoryAdapter.getCategoryById(id));
    }
}
