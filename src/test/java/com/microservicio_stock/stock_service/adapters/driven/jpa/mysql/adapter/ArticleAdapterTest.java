package com.microservicio_stock.stock_service.adapters.driven.jpa.mysql.adapter;

import com.microservicio_stock.stock_service.adapters.driven.jpa.mysql.entity.ArticleEntity;
import com.microservicio_stock.stock_service.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.microservicio_stock.stock_service.adapters.driven.jpa.mysql.entity.MarkEntity;
import com.microservicio_stock.stock_service.adapters.driven.jpa.mysql.exception.NoDataFoundException;
import com.microservicio_stock.stock_service.adapters.driven.jpa.mysql.mapper.IArticleEntityMapper;
import com.microservicio_stock.stock_service.adapters.driven.jpa.mysql.repository.IArticleRepository;
import com.microservicio_stock.stock_service.domain.model.Article;
import com.microservicio_stock.stock_service.domain.model.Category;
import com.microservicio_stock.stock_service.domain.model.Mark;
import com.microservicio_stock.stock_service.domain.spi.IArticlePersistencePort;
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

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ArticleAdapterTest {

    @InjectMocks
    private ArticleAdapter articleAdapter;

    @Mock
    private IArticleRepository articleRepository;

    @Mock
    private IArticleEntityMapper articleEntityMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveArticle_ShouldSaveArticle() {
        // Arrange
        Category category = new Category(1L, "Category Name", "Category Description");
        Mark mark = new Mark(1L, "Mark Name", "Mark Description");
        Article article = new Article(1L, "Article Name", "Article Description", 10, 100.0, List.of(category), mark);
        ArticleEntity articleEntity = new ArticleEntity();
        articleEntity.setCategories(new HashSet<>()); // Set categories if needed
        articleEntity.setMark(new MarkEntity()); // Set mark if needed

        when(articleEntityMapper.toEntity(article)).thenReturn(articleEntity);

        // Act
        articleAdapter.saveArticle(article);

        // Assert
        verify(articleRepository, times(1)).save(articleEntity);
    }

    @Test
    void getPagedArticles_ShouldReturnPagedArticles() {
        // Arrange
        Integer page = 0;
        Integer size = 10;
        boolean ascOrderByName = true;
        String orderBy = "name";
        Pageable pageable = PageRequest.of(page, size, Sort.by(ascOrderByName ? Sort.Direction.ASC : Sort.Direction.DESC, orderBy));
        Page<ArticleEntity> articleEntities = mock(Page.class);
        when(articleRepository.findAll(pageable)).thenReturn(articleEntities);
        when(articleEntities.isEmpty()).thenReturn(false);

        // Prepare dummy data
        CategoryEntity categoryEntity = new CategoryEntity();
        MarkEntity markEntity = new MarkEntity();
        ArticleEntity articleEntity = new ArticleEntity();
        articleEntity.setCategories(Set.of(categoryEntity));
        articleEntity.setMark(markEntity);

        List<ArticleEntity> articleEntityList = List.of(articleEntity);
        when(articleEntities.getContent()).thenReturn(articleEntityList);
        when(articleEntities.isLast()).thenReturn(false);
        when(articleEntities.getNumber()).thenReturn(page);
        when(articleEntities.getTotalPages()).thenReturn(5);
        when(articleEntities.getTotalElements()).thenReturn(50L);

        Article article = new Article(1L, "Article Name", "Article Description", 10, 100.0, Collections.singletonList(new Category(1L, "Category Name", "Category Description")), new Mark(1L, "Mark Name", "Mark Description"));
        PagedResponse<Article> expectedResponse = new PagedResponse<>(
                List.of(article), // Populate with the converted Article objects if necessary
                page,
                5,
                50L,
                false
        );

        when(articleEntityMapper.toPagedModel(articleEntities, articleEntities.isLast(), articleEntities.getNumber()))
                .thenReturn(expectedResponse);

        // Act
        PagedResponse<Article> result = articleAdapter.getPagedArticles(page, size, ascOrderByName, orderBy);

        // Assert
        assertNotNull(result);
        assertEquals(expectedResponse.getContent(), result.getContent());
        assertEquals(expectedResponse.getCurrentPage(), result.getCurrentPage());
        assertEquals(expectedResponse.getTotalPages(), result.getTotalPages());
        assertEquals(expectedResponse.getTotalElements(), result.getTotalElements());
        assertEquals(expectedResponse.isLastPage(), result.isLastPage());
        verify(articleRepository, times(1)).findAll(pageable);
        verify(articleEntityMapper, times(1)).toPagedModel(articleEntities, articleEntities.isLast(), articleEntities.getNumber());
    }

    @Test
    void getPagedArticles_NoDataFoundException() {
        // Arrange
        Integer page = 0;
        Integer size = 10;
        boolean ascOrderByName = true;
        String orderBy = "name";
        Pageable pageable = PageRequest.of(page, size, Sort.by(ascOrderByName ? Sort.Direction.ASC : Sort.Direction.DESC, orderBy));
        when(articleRepository.findAll(pageable)).thenReturn(Page.empty());

        // Act & Assert
        assertThrows(NoDataFoundException.class, () -> articleAdapter.getPagedArticles(page, size, ascOrderByName, orderBy));
    }
}
