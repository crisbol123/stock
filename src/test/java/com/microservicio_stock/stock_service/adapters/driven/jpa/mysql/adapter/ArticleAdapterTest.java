package com.microservicio_stock.stock_service.adapters.driven.jpa.mysql.adapter;

import com.microservicio_stock.stock_service.adapters.driven.jpa.mysql.entity.ArticleEntity;
import com.microservicio_stock.stock_service.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.microservicio_stock.stock_service.adapters.driven.jpa.mysql.entity.MarkEntity;
import com.microservicio_stock.stock_service.adapters.driven.jpa.mysql.mapper.IArticleEntityMapper;
import com.microservicio_stock.stock_service.adapters.driven.jpa.mysql.repository.IArticleRepository;
import com.microservicio_stock.stock_service.domain.model.Article;
import com.microservicio_stock.stock_service.domain.model.Category;
import com.microservicio_stock.stock_service.domain.model.Mark;
import com.microservicio_stock.stock_service.domain.util.PagedResponse;
import com.microservicio_stock.stock_service.adapters.driven.jpa.mysql.exception.NoDataFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ArticleAdapterTest {

    @Mock
    private IArticleRepository iArticleRepository;

    @Mock
    private IArticleEntityMapper iArticleEntityMapper;

    @InjectMocks
    private ArticleAdapter articleAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetPagedArticlesCategoriesNameOrder() {
        // Arrange
        CategoryEntity categoryEntity1 = new CategoryEntity();
        categoryEntity1.setId(1L);
        categoryEntity1.setName("Electronics");
        categoryEntity1.setDescription("Devices and gadgets");

        CategoryEntity categoryEntity2 = new CategoryEntity();
        categoryEntity2.setId(2L);
        categoryEntity2.setName("Books");
        categoryEntity2.setDescription("Various genres");

        MarkEntity markEntity1 = new MarkEntity();
        markEntity1.setId(1L);
        markEntity1.setName("BrandA");

        MarkEntity markEntity2 = new MarkEntity();
        markEntity2.setId(2L);
        markEntity2.setName("BrandB");

        ArticleEntity articleEntity1 = new ArticleEntity();
        articleEntity1.setId(1L);
        articleEntity1.setName("Laptop");
        articleEntity1.setDescription("High-performance laptop");
        articleEntity1.setQuantity(10);
        articleEntity1.setPrice(999.99);
        articleEntity1.setCategories(Set.of(categoryEntity1));
        articleEntity1.setMark(markEntity1);

        ArticleEntity articleEntity2 = new ArticleEntity();
        articleEntity2.setId(2L);
        articleEntity2.setName("Smartphone");
        articleEntity2.setDescription("Latest model smartphone");
        articleEntity2.setQuantity(20);
        articleEntity2.setPrice(699.99);
        articleEntity2.setCategories(Set.of(categoryEntity2));
        articleEntity2.setMark(markEntity2);

        List<ArticleEntity> articleEntities = List.of(articleEntity1, articleEntity2);

        Category category1 = new Category(1L, "Electronics", "Devices and gadgets");
        Category category2 = new Category(2L, "Books", "Various genres");
        List<Category> categories = new ArrayList();
        categories.add(category1);
        categories.add(category2);
        Article article1 = new Article(1L, "Laptop", "High-performance laptop", 10, 999.99, categories, new Mark(1L, "BrandA", "BrandA"));
        Article article2 = new Article(2L, "Smartphone", "Latest model smartphone", 20, 699.99, categories, new Mark(2L, "BrandB", "BrandB"));

        List<Article> articles = new ArrayList();
        articles.add(article1);
        articles.add(article2);

        when(iArticleRepository.findAll()).thenReturn(articleEntities);
        when(iArticleEntityMapper.toModelList(articleEntities)).thenReturn(articles);

        Integer page = 0;
        Integer size = 10;
        boolean ascOrderByName = true;
        String orderBy = "categories.name";

        // Act
        PagedResponse<Article> response = articleAdapter.getPagedArticles(page, size, ascOrderByName, orderBy);

        // Assert
        assertNotNull(response);
        assertEquals(0, response.getCurrentPage());
        assertTrue(response.getTotalPages() > 0);
        assertEquals(articles.size(), response.getTotalElements());
        // Add more assertions based on expected behavior
    }

    @Test
    void testGetPagedArticlesNameOrder() {
        // Arrange
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(1L);
        categoryEntity.setName("Electronics");
        categoryEntity.setDescription("Devices and gadgets");

        MarkEntity markEntity1 = new MarkEntity();
        markEntity1.setId(1L);
        markEntity1.setName("BrandA");

        MarkEntity markEntity2 = new MarkEntity();
        markEntity2.setId(2L);
        markEntity2.setName("BrandB");

        ArticleEntity articleEntity1 = new ArticleEntity();
        articleEntity1.setId(1L);
        articleEntity1.setName("Laptop");
        articleEntity1.setDescription("High-performance laptop");
        articleEntity1.setQuantity(10);
        articleEntity1.setPrice(999.99);
        articleEntity1.setCategories(Set.of(categoryEntity));
        articleEntity1.setMark(markEntity1);

        ArticleEntity articleEntity2 = new ArticleEntity();
        articleEntity2.setId(2L);
        articleEntity2.setName("Smartphone");
        articleEntity2.setDescription("Latest model smartphone");
        articleEntity2.setQuantity(20);
        articleEntity2.setPrice(699.99);
        articleEntity2.setCategories(Set.of(categoryEntity));
        articleEntity2.setMark(markEntity2);

        List<ArticleEntity> articleEntities = List.of(articleEntity1, articleEntity2);

        Category category1 = new Category(1L, "Electronics", "Devices and gadgets");
        Category category2 = new Category(2L, "Books", "Various genres");
        List<Category> categories = new ArrayList();
        categories.add(category1);
        categories.add(category2);
        Article article1 = new Article(1L, "Laptop", "High-performance laptop", 10, 999.99, categories, new Mark(1L, "BrandA", "BrandA"));
        Article article2 = new Article(2L, "Smartphone", "Latest model smartphone", 20, 699.99, categories, new Mark(2L, "BrandB", "BrandB"));

        List<Article> articles = new ArrayList();
        articles.add(article1);
        articles.add(article2);


        when(iArticleRepository.findAll()).thenReturn(articleEntities);
        when(iArticleEntityMapper.toModelList(articleEntities)).thenReturn(articles);

        Integer page = 0;
        Integer size = 10;
        boolean ascOrderByName = true;
        String orderBy = "name";

        // Act
        PagedResponse<Article> response = articleAdapter.getPagedArticles(page, size, ascOrderByName, orderBy);

        // Assert
        assertNotNull(response);
        assertEquals(0, response.getCurrentPage());
        assertTrue(response.getTotalPages() > 0);
        assertEquals(articles.size(), response.getTotalElements());
        // Add more assertions based on expected behavior
    }

    @Test
    void testGetPagedArticlesMarkNameOrder() {
        // Arrange
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(1L);
        categoryEntity.setName("Electronics");
        categoryEntity.setDescription("Devices and gadgets");

        MarkEntity markEntity1 = new MarkEntity();
        markEntity1.setId(1L);
        markEntity1.setName("BrandA");

        MarkEntity markEntity2 = new MarkEntity();
        markEntity2.setId(2L);
        markEntity2.setName("BrandB");

        ArticleEntity articleEntity1 = new ArticleEntity();
        articleEntity1.setId(1L);
        articleEntity1.setName("Laptop");
        articleEntity1.setDescription("High-performance laptop");
        articleEntity1.setQuantity(10);
        articleEntity1.setPrice(999.99);
        articleEntity1.setCategories(Set.of(categoryEntity));
        articleEntity1.setMark(markEntity1);

        ArticleEntity articleEntity2 = new ArticleEntity();
        articleEntity2.setId(2L);
        articleEntity2.setName("Smartphone");
        articleEntity2.setDescription("Latest model smartphone");
        articleEntity2.setQuantity(20);
        articleEntity2.setPrice(699.99);
        articleEntity2.setCategories(Set.of(categoryEntity));
        articleEntity2.setMark(markEntity2);

        List<ArticleEntity> articleEntities = List.of(articleEntity1, articleEntity2);


        Category category1 = new Category(1L, "Electronics", "Devices and gadgets");
        Category category2 = new Category(2L, "Books", "Various genres");
        List<Category> categories = new ArrayList();
        categories.add(category1);
        categories.add(category2);
        Article article1 = new Article(1L, "Laptop", "High-performance laptop", 10, 999.99, categories, new Mark(1L, "BrandA", "BrandA"));
        Article article2 = new Article(2L, "Smartphone", "Latest model smartphone", 20, 699.99, categories, new Mark(2L, "BrandB", "BrandB"));

        List<Article> articles = new ArrayList();
        articles.add(article1);
        articles.add(article2);

        when(iArticleRepository.findAll()).thenReturn(articleEntities);
        when(iArticleEntityMapper.toModelList(articleEntities)).thenReturn(articles);

        Integer page = 0;
        Integer size = 10;
        boolean ascOrderByName = true;
        String orderBy = "mark.name";

        // Act
        PagedResponse<Article> response = articleAdapter.getPagedArticles(page, size, ascOrderByName, orderBy);

        // Assert
        assertNotNull(response);
        assertEquals(0, response.getCurrentPage());
        assertTrue(response.getTotalPages() > 0);
        assertEquals(articles.size(), response.getTotalElements());
        // Add more assertions based on expected behavior
    }

    @Test
    void testGetPagedArticlesNoDataFound() {
        // Arrange
        when(iArticleRepository.findAll()).thenReturn(new ArrayList<>());

        Integer page = 0;
        Integer size = 10;
        boolean ascOrderByName = true;
        String orderBy = "name";

        // Act & Assert
        assertThrows(NoDataFoundException.class, () -> articleAdapter.getPagedArticles(page, size, ascOrderByName, orderBy));
    }
}
